package org.openmrs.module.amrsreports.reporting.provider;

import org.apache.commons.io.IOUtils;
import org.openmrs.Location;
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientIdentifierType;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrsreports.AmrsReportsConceptNames;
import org.openmrs.module.amrsreports.MOHFacility;
import org.openmrs.module.amrsreports.cache.MohCacheUtils;
import org.openmrs.module.amrsreports.reporting.cohort.definition.EligibleButNotOnARVCohortDefinition;
import org.openmrs.module.amrsreports.reporting.cohort.definition.Moh361BCohortDefinition;
import org.openmrs.module.amrsreports.reporting.converter.ARTMonthZeroConverter;
import org.openmrs.module.amrsreports.reporting.converter.ARVPatientSnapshotReasonConverter;
import org.openmrs.module.amrsreports.reporting.converter.DateListCustomConverter;
import org.openmrs.module.amrsreports.reporting.converter.DecimalAgeConverter;
import org.openmrs.module.amrsreports.reporting.converter.FormattedDateSetConverter;
import org.openmrs.module.amrsreports.reporting.converter.IntervalObsValueNumericConverter;
import org.openmrs.module.amrsreports.reporting.converter.ObsRepresentationValueNumericConverter;
import org.openmrs.module.amrsreports.reporting.converter.PersonAddressConverter;
import org.openmrs.module.amrsreports.reporting.converter.TBStatusConverter;
import org.openmrs.module.amrsreports.reporting.converter.TbTreatmentStartDateConverter;
import org.openmrs.module.amrsreports.reporting.converter.WHOStageConverter;
import org.openmrs.module.amrsreports.reporting.data.ARTSerialNumberDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.ARTTransferStatusDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.AgeAtEvaluationDateDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.CtxStartDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.DateARTStartedDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.EligibilityForARTDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.INHStartDateDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.ObsNearestARVStartDateDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.PmtctPregnancyDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.SortedObsSinceOtherDefinitionDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.TBStatusDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.TbTreatmentStartDateDataDefinition;
import org.openmrs.module.amrsreports.service.MohCoreService;
import org.openmrs.module.amrsreports.util.MOHReportUtil;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.data.MappedData;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DateConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.converter.PropertyConverter;
import org.openmrs.module.reporting.data.patient.definition.PatientIdDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonAttributeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonIdDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredAddressDataDefinition;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Provides mechanisms for rendering the Eligible and not on ARV
 */
public class EligibleButNotOnARVReportProvider extends ReportProvider {

	public EligibleButNotOnARVReportProvider() {
		this.name = "Eligible but not on ARV";
		this.visible = true;
	}

	@Override
	public ReportDefinition getReportDefinition() {

		String nullString = null;
		MohCoreService service = Context.getService(MohCoreService.class);

		ReportDefinition report = new PeriodIndicatorReportDefinition();
		report.setName("Eligible not on ARV");

		// set up the DSD
		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.setName("allPatients");
		// sort by serial number, then by date
		dsd.addSortCriteria("id", SortCriteria.SortDirection.ASC);


		// set up the columns ...
		// Patient ID

		dsd.addColumn("id", new PatientIdDataDefinition(), nullString);

		//  Patient's Name
		dsd.addColumn("name", new PreferredNameDataDefinition(), nullString, new ObjectFormatter());

		//  Sex
		dsd.addColumn("sex", new GenderDataDefinition(), nullString);
		//  Age
		AgeAtEvaluationDateDataDefinition add = new AgeAtEvaluationDateDataDefinition();
		dsd.addColumn("age", add, nullString, new DecimalAgeConverter(0));

		report.addDataSetDefinition(dsd,null);

		return report;
	}

	@Override
	public CohortDefinition getCohortDefinition() {
		return new EligibleButNotOnARVCohortDefinition();
	}

	@Override
	public ReportDesign getReportDesign() {
		ReportDesign design = new ReportDesign();
		design.setName("Eligible Not on ARV Register Design");
		design.setReportDefinition(this.getReportDefinition());
		design.setRendererType(ExcelTemplateRenderer.class);

		Properties props = new Properties();
		props.put("repeatingSections", "sheet:1,row:6,dataset:allPatients");

		design.setProperties(props);

		ReportDesignResource resource = new ReportDesignResource();
		resource.setName("template.xls");
		InputStream is = OpenmrsClassLoader.getInstance().getResourceAsStream("templates/EligibleNotOnARVReportTemplate.xls");

		if (is == null)
			throw new APIException("Could not find report template for eligible and not on ARV.");

		try {
			resource.setContents(IOUtils.toByteArray(is));
		} catch (IOException ex) {
			throw new APIException("Could not create report design for Eligible and not on ARV", ex);
		}

		IOUtils.closeQuietly(is);
		design.addResource(resource);

		return design;
	}
}