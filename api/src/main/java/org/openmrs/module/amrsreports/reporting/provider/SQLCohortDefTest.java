package org.openmrs.module.amrsreports.reporting.provider;

import org.apache.commons.io.IOUtils;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.api.APIException;
import org.openmrs.module.amrsreports.cache.MohCacheUtils;
import org.openmrs.module.amrsreports.reporting.CommonICAPCohortLibrary;
import org.openmrs.module.amrsreports.reporting.CommonIndicatorLibrary;
import org.openmrs.module.amrsreports.reporting.CommonSQLCohortLibrary;
import org.openmrs.module.amrsreports.reporting.ReportUtils;
import org.openmrs.module.amrsreports.reporting.cohort.definition.CCCPatientCohortDefinition;
import org.openmrs.module.amrsreports.rule.MohEvaluableNameConstants;
import org.openmrs.module.reporting.ReportingConstants;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.ReportDesignResource;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.renderer.ExcelTemplateRenderer;
import org.openmrs.util.OpenmrsClassLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides mechanisms for rendering the MOH 361A Pre-ART Register
 */
public class SQLCohortDefTest extends ReportProvider {


    private CommonICAPCohortLibrary commonCohorts = new CommonICAPCohortLibrary();
    private CommonSQLCohortLibrary sqlCohortLibrary = new CommonSQLCohortLibrary();

	public SQLCohortDefTest() {
		this.name = "SQL COHORT TEST";
		this.visible = true;
	}

	@Override
	public ReportDefinition getReportDefinition() {

		ReportDefinition report = new PeriodIndicatorReportDefinition();
		report.setName("1SQL COhort Test Design");

        // set up parameters


        //define general cohorts


        String defineMalesWithMinAge = sqlCohortLibrary.malesWithMinAge(40);

        CohortDefinition generalCOhort = new SqlCohortDefinition(defineMalesWithMinAge);
        generalCOhort.setName("General Query for patients");
        generalCOhort.addParameter(new Parameter("reportDate", "Report Date", Date.class));
        generalCOhort.addParameter(new Parameter("endDate", "End Reporting Date", Date.class));
        generalCOhort.addParameter(new Parameter("minAge", "Min Age", Integer.class));
        generalCOhort.addParameter(new Parameter("maxAge", "Max Age", Integer.class));
        generalCOhort.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        generalCOhort.addParameter(new Parameter("gender", "Gender", String.class));

        CohortIndicator testCohort = CommonIndicatorLibrary.createSQLCohortIndicator("This is a general Cohort Query", ReportUtils.map(generalCOhort, "reportDate=${reportDate},minAge=${minAge},locationList=${locationList},gender=${gender}"));
        //createCohortIndicator("Number of patients on medication", ReportUtils.map(commonCohorts.onMedication(concepts), "onDate=${endDate}"));

        Map<String, Object> paramMappings = new HashMap<String, Object>();
        paramMappings.put("reportDate", "${reportDate}");
        paramMappings.put("minAge", "${minAge}");
        paramMappings.put("maxAge", "${maxAge}");
        paramMappings.put("endDate", "${endDate}");
        paramMappings.put("locationList", "${locationList}");
        paramMappings.put("gender", "${gender}");

        CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
        dsd.addParameter(new Parameter("reportDate", "Report Date", Date.class));
        dsd.addParameter(new Parameter("minAge", "Min Age", Integer.class));
        dsd.addParameter(new Parameter("maxAge", "Max Age", Integer.class));
        dsd.addParameter(new Parameter("endDate", "End Reporting Date", Date.class));
        dsd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        dsd.addParameter(new Parameter("gender", "Gender", String.class));

        //set reportDefinition params
        report.addParameter(new Parameter("reportDate", "Report Date", Date.class));
        report.addParameter(new Parameter("minAge", "Min Age", Integer.class));
        report.addParameter(new Parameter("maxAge", "Max Age", Integer.class));
        report.addParameter(new Parameter("endDate", "End Reporting Date", Date.class));
        report.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        report.addParameter(new Parameter("gender", "Gender", String.class));

        //dsd.addDimension("compositionDimension", new Mapped<CohortDefinitionDimension>(compositionDimension,dimensionMappings));
        dsd.addColumn("malesBelow15", "Males Below 15", new Mapped<CohortIndicator>(testCohort, paramMappings), "");
       /* dsd.addColumn("malesAbove15", "Males 15 or more", new Mapped<CohortIndicator>(malesAbove15ind, periodMappings), "");
        dsd.addColumn("femalesBelow15", "Females Below 15", new Mapped<CohortIndicator>(femalesZeroTo14ind, periodMappings), "");
        dsd.addColumn("femalesAbove15", "Females 15 or more", new Mapped<CohortIndicator>(femalesAbove15ind, periodMappings), "");*/
        //Make second  column
        dsd.addColumn("NEWmalesBelow15", "Males Below 15", new Mapped<CohortIndicator>(testCohort, paramMappings), "");
        /*dsd.addColumn("NEWmalesAbove15", "Males 15 or more", new Mapped<CohortIndicator>(malesAbove15indend, periodMappings), "");
        dsd.addColumn("NEWfemalesBelow15", "Females Below 15", new Mapped<CohortIndicator>(femalesZeroTo14indend, periodMappings), "");
        dsd.addColumn("NEWfemalesAbove15", "Females 15 or more", new Mapped<CohortIndicator>(femalesAbove15indend, periodMappings), "");*/
        /**
         * Add columns for peds
         */
       /* dsd.addColumn("malesPedsAt1", "Male Peds up to one year", new Mapped<CohortIndicator>(pedsMalesZeroTo1ind, periodMappings), "");
        dsd.addColumn("malesPedsBtw2n4", "Males peds between 2 and 4", new Mapped<CohortIndicator>(pedsmales2To4ind, periodMappings), "");
        dsd.addColumn("malesPedsBtw5n14", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(pedsmales5To14ind, periodMappings), "");
        dsd.addColumn("femalesPedsAt1", "Female peds at one ", new Mapped<CohortIndicator>(pedsFemalesZeroTo1ind, periodMappings), "");
        dsd.addColumn("femalesPedsBtw2n4", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(pedsFemales2To4ind, periodMappings), "");
        dsd.addColumn("femalesPedsBtw5n14", "Female peds between 5 and 14", new Mapped<CohortIndicator>(pedsFemales5To14ind, periodMappings), "");*/

        /**
         * Fill second column for peds
         */
        /*dsd.addColumn("NEWmalesPedsAt1", "Male Peds up to one year", new Mapped<CohortIndicator>(pedsMalesZeroTo1indend, periodMappings), "");
        dsd.addColumn("NEWmalesPedsBtw2n4", "Males peds between 2 and 4", new Mapped<CohortIndicator>(pedsmales2To4indend, periodMappings), "");
        dsd.addColumn("NEWmalesPedsBtw5n14", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(pedsmales5To14indend, periodMappings), "");
        dsd.addColumn("NEWfemalesPedsAt1", "Female peds at one ", new Mapped<CohortIndicator>(pedsFemalesZeroTo1indend, periodMappings), "");
        dsd.addColumn("NEWfemalesPedsBtw2n4", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(pedsFemales2To4indend, periodMappings), "");
        dsd.addColumn("NEWfemalesPedsBtw5n14", "Female peds between 5 and 14", new Mapped<CohortIndicator>(pedsFemales5To14indend, periodMappings), "");*/


        //add columns for medication
        /*dsd.addColumn("malesBelow15onMedication", "Males below 15 on Medication", new Mapped<CohortIndicator>(malesBelow15OnMedCohortIndi, periodMappings), "");
        dsd.addColumn("malesAbove15onMedication", "Males 15+ on Medication", new Mapped<CohortIndicator>(malesAbove15OnMedCohortIndi, periodMappings), "");
        dsd.addColumn("femalesBelow15onMedication", "FeMales 15+ on Medication", new Mapped<CohortIndicator>(femalesBelow15OnMedCohortIndi, periodMappings), "");
        dsd.addColumn("femalesAbove15onMedication", "FeMales below 15 on Medication", new Mapped<CohortIndicator>(femalesAbove15OnMedCohortIndi, periodMappings), "");
        dsd.addColumn("malesPedsAt1onMedication", "peds below 2 on Medication", new Mapped<CohortIndicator>(pedsMalesZeroTo1OnMedCohortIndi, periodMappings), "");
        dsd.addColumn("femalesPedsAt1onMedication", "Males 15+ on Medication", new Mapped<CohortIndicator>(pedsFemalesZeroTo1OnMedCohortIndi, periodMappings), "");
        dsd.addColumn("malesPedsBtw2n4onMedication", "Males 15+ on Medication", new Mapped<CohortIndicator>(pedsmales2To4OnMedCohortIndi, periodMappings), "");
        dsd.addColumn("femalesPedsBtw2n4onMedication", "Males 15+ on Medication", new Mapped<CohortIndicator>(pedsFemales2To4OnMedCohortIndi, periodMappings), "");
        dsd.addColumn("malesPedsBtw5n14onMedication", "Males 15+ on Medication", new Mapped<CohortIndicator>(pedsmales5To14OnMedCohortIndi, periodMappings), "");
        dsd.addColumn("femalesPedsBtw5n14onMedication", "Males 15+ on Medication", new Mapped<CohortIndicator>(pedsFemales5To14OnMedCohortIndi, periodMappings), "");*/

		report.addDataSetDefinition(dsd, paramMappings);

		return report;
	}

	@Override
	public CohortDefinition getCohortDefinition() {
		return new CCCPatientCohortDefinition();
	}

	@Override
	public ReportDesign getReportDesign() {
		ReportDesign design = new ReportDesign();
		design.setName("Hiv Palliative Care Design");
		design.setReportDefinition(this.getReportDefinition());
		design.setRendererType(ExcelTemplateRenderer.class);

		ReportDesignResource resource = new ReportDesignResource();
		resource.setName("template.xls");
		InputStream is = OpenmrsClassLoader.getInstance().getResourceAsStream("templates/Quarterly_Facility-BasedHIVCare-template.xls");

		if (is == null)
			throw new APIException("Could not find report template.");

		try {
			resource.setContents(IOUtils.toByteArray(is));
		} catch (IOException ex) {
			throw new APIException("Could not create report design for MOH 361B Register.", ex);
		}

		IOUtils.closeQuietly(is);
		design.addResource(resource);

		return design;
	}


}