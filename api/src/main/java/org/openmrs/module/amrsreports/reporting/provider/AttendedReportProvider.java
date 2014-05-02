package org.openmrs.module.amrsreports.reporting.provider;

import org.apache.commons.io.IOUtils;
import org.openmrs.Location;
import org.openmrs.api.APIException;
import org.openmrs.module.amrsreports.reporting.converter.DecimalAgeConverter;
import org.openmrs.module.amrsreports.reporting.data.AgeAtEvaluationDateDataDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.patient.definition.PatientIdDataDefinition;
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
public class AttendedReportProvider extends ReportProvider {

	public AttendedReportProvider() {
		this.name = "Attended Report";
		this.visible = true;
        this.isIndicator= false;
	}

	@Override
	public ReportDefinition getReportDefinition() {

		String nullString = null;
		ReportDefinition report = new PeriodIndicatorReportDefinition();
		report.setName("attended");

		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.setName("attended");

		dsd.addSortCriteria("id", SortCriteria.SortDirection.ASC);
		dsd.addColumn("id", new PatientIdDataDefinition(), nullString);
		dsd.addColumn("name", new PreferredNameDataDefinition(), nullString, new ObjectFormatter());
		dsd.addColumn("sex", new GenderDataDefinition(), nullString);

		AgeAtEvaluationDateDataDefinition add = new AgeAtEvaluationDateDataDefinition();
		dsd.addColumn("age", add, nullString, new DecimalAgeConverter(0));

		report.addDataSetDefinition(dsd,null);

		return report;
	}

	@Override
	public CohortDefinition getCohortDefinition() {
        String sql ="select   e.patient_id  " +
                "  from encounter e  " +
                "  inner join person p  " +
                "  on p.person_id=e.patient_id   " +
                "    where e.voided = 0  " +
                "    and p.voided=0   " +
                "    and e.encounter_datetime between (:startDate) and (:endDate)  " +
                "    and e.location_id in ( :locationList ) ";

        CohortDefinition generalCOhort = new SqlCohortDefinition(sql);
        generalCOhort.setName("Patients who attended during a particular period");

        generalCOhort.addParameter(new Parameter("startDate", "Report Date", Date.class));
        generalCOhort.addParameter(new Parameter("endDate", "End Reporting Date", Date.class));
        generalCOhort.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        return generalCOhort;
	}

	@Override
	public ReportDesign getReportDesign() {
		ReportDesign design = new ReportDesign();
		design.setName("Attended Report Design");
		design.setReportDefinition(this.getReportDefinition());
		design.setRendererType(ExcelTemplateRenderer.class);

		Properties props = new Properties();
		props.put("repeatingSections", "sheet:1,row:6,dataset:attended");

		design.setProperties(props);

		ReportDesignResource resource = new ReportDesignResource();
		resource.setName("template.xls");
		InputStream is = OpenmrsClassLoader.getInstance().getResourceAsStream("templates/AttendedReportTemplate.xls");

		if (is == null)
			throw new APIException("Could not find report template for Attended patients");

		try {
			resource.setContents(IOUtils.toByteArray(is));
		} catch (IOException ex) {
			throw new APIException("Could not create report design for Attended patients", ex);
		}

		IOUtils.closeQuietly(is);
		design.addResource(resource);

		return design;
	}
}