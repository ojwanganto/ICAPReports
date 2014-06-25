package org.openmrs.module.amrsreports.reporting.provider;

import org.apache.commons.io.IOUtils;
import org.openmrs.Location;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrsreports.reporting.converter.DateListCustomConverter;
import org.openmrs.module.amrsreports.reporting.converter.DecimalAgeConverter;
import org.openmrs.module.amrsreports.reporting.converter.ICAPTBStatusConverter;
import org.openmrs.module.amrsreports.reporting.data.AgeAtEvaluationDateDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.ICAPCCCNoDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.ICAPEnrollmentDateDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.ICAPLastAppointmentDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.ICAPTBStatusDataDefinition;
import org.openmrs.module.amrsreports.util.MOHReportUtil;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.patient.definition.PatientIdDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.ReportDesignResource;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.renderer.ExcelTemplateRenderer;
import org.openmrs.util.OpenmrsClassLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Provides mechanisms for rendering the Eligible and not on ARV
 */
public class LFTUReportProvider extends ReportProvider {

	public LFTUReportProvider() {
		this.name = "Lost To Follow-Up and Unknown";
		this.visible = true;
        this.isIndicator= false;
	}

	@Override
	public ReportDefinition getReportDefinition() {

		String nullString = null;
		ReportDefinition report = new PeriodIndicatorReportDefinition();
		report.setName("LTFU");

		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.setName("LTFU");

		dsd.addSortCriteria("enrollmentDate", SortCriteria.SortDirection.ASC);
		dsd.addColumn("PatientCCC", new ICAPCCCNoDataDefinition(), nullString);
        dsd.addColumn("lastAppointment", new ICAPLastAppointmentDataDefinition(), nullString,new DateListCustomConverter("yyyy-MM-dd"));
        dsd.addColumn("name", new PreferredNameDataDefinition(), nullString, new ObjectFormatter());
        dsd.addColumn("enrollmentDate", new ICAPEnrollmentDateDataDefinition(), nullString,new DateListCustomConverter("yyyy-MM-dd"));
        dsd.addColumn("tbstatus", new ICAPTBStatusDataDefinition(), nullString,new ICAPTBStatusConverter());
        dsd.addColumn("cohort", new ICAPEnrollmentDateDataDefinition(), nullString,new DateListCustomConverter("MM-yyyy"));

		report.addDataSetDefinition(dsd,null);

		return report;
	}

	@Override
	public CohortDefinition getCohortDefinition() {

        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in (:locationList)  " +
                "   and obs.person_id not in ( select person_id from person where dead=1 ) " +
                "   and obs.person_id not in ( select person_id from obs where concept_id in (1543,160649) and value_datetime between (:startDate) and (:endDate) ) " +
                "   and obs.person_id not in ( select o.person_id from obs o where concept_id = 5096 and value_datetime between (:startDate) and (:endDate) ) " +
                "   and obs.person_id not in ( select e.patient_id from encounter e group by e.patient_id having max(e.encounter_datetime) between date_add((:endDate),INTERVAL -93 DAY) and (:endDate) ) " +
                "   and value_datetime between (:startDate) and (:endDate) ";


        CohortDefinition generalCOhort = new SqlCohortDefinition(sql);
        generalCOhort.setName("LTFU and Unknown");

        generalCOhort.addParameter(new Parameter("startDate", "Report Date", Date.class));
        generalCOhort.addParameter(new Parameter("endDate", "End Reporting Date", Date.class));
        generalCOhort.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        return generalCOhort;
	}

	@Override
	public ReportDesign getReportDesign() {
		ReportDesign design = new ReportDesign();
		design.setName("LTFU Design");
		design.setReportDefinition(this.getReportDefinition());
		design.setRendererType(ExcelTemplateRenderer.class);

		Properties props = new Properties();
		props.put("repeatingSections", "sheet:1,row:6,dataset:LTFU");

		design.setProperties(props);

		ReportDesignResource resource = new ReportDesignResource();
		resource.setName("template.xls");
		InputStream is = OpenmrsClassLoader.getInstance().getResourceAsStream("templates/LFTUReportTemplate.xls");

		if (is == null)
			throw new APIException("Could not find report template for LTFU");

		try {
			resource.setContents(IOUtils.toByteArray(is));
		} catch (IOException ex) {
			throw new APIException("Could not create report design for LTFU", ex);
		}

		IOUtils.closeQuietly(is);
		design.addResource(resource);

		return design;
	}
}