package org.openmrs.module.amrsreports.reporting.provider;

import org.apache.commons.io.IOUtils;
import org.openmrs.Location;
import org.openmrs.api.APIException;
import org.openmrs.module.amrsreports.MOHFacility;
import org.openmrs.module.amrsreports.reporting.CommonCohortLibrary;
import org.openmrs.module.amrsreports.reporting.ReportUtils;
import org.openmrs.module.amrsreports.reporting.cohort.definition.CCCPatientCohortDefinition;
import org.openmrs.module.reporting.ReportingConstants;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
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
public class HIVPalliativeCareProvider extends ReportProvider {


    private CommonCohortLibrary commonCohorts = new CommonCohortLibrary();

	public HIVPalliativeCareProvider() {
		this.name = "1.0 HIV Palliative Care";
		this.visible = true;
	}

	@Override
	public ReportDefinition getReportDefinition() {

		ReportDefinition report = new PeriodIndicatorReportDefinition();
		report.setName("CCC Patient Application");

        // set up parameters
        Parameter facility = new Parameter();
        facility.setName("locationList");
        facility.setType(Location.class);

        //define general cohorts
        /*Males (0-14 cohort)*/

        CohortDefinition malesZeroTo14 = commonCohorts.malesAgedAtMostX(14);

        /*Males (15 and above)*/
        CohortDefinition malesAbove15 = commonCohorts.malesAgedAtLeastX(15);

        /*Females (0-14 cohort)*/

        CohortDefinition femalesZeroTo14 = commonCohorts.femalesAgedAtMostX(14);

         /*Females (15 and above)*/

        CohortDefinition femalesAbove15 = commonCohorts.femalesAgedAtLeastX(15);

        //define cohorts for peds

        /**
         * cohort for peds up to one year old
        */
        CohortDefinition pedsMalesZeroTo1 = commonCohorts.malesAgedAtMostX(1);
        CohortDefinition pedsFemalesZeroTo1 = commonCohorts.femalesAgedAtMostX(1);

        /**
         * Peds aged between 2 and 4
         */
        CohortDefinition pedsmales2To4 = commonCohorts.malesAgedBetween(2,4);
        CohortDefinition pedsFemales2To4 = commonCohorts.femalesAgedBetween(2,4);

        /**
         * Peds aged between 5 and 14
         */
        CohortDefinition pedsmales5To14 = commonCohorts.malesAgedBetween(5,14);
        CohortDefinition pedsFemales5To14 = commonCohorts.femalesAgedBetween(5,14);

        CohortDefinitionDimension compositionDimension = new CohortDefinitionDimension();
        compositionDimension.setName("compositionDimension");
        compositionDimension.addParameter(new Parameter("startDate", "Start Date", Date.class));
        compositionDimension.addParameter(new Parameter("locationList", "List of Locations", MOHFacility.class));
        compositionDimension.addCohortDefinition("malesZeroTo14CohortDimension", ReportUtils.map(malesZeroTo14, "effectiveDate=${startDate},locationList=${locationList}"));
        compositionDimension.addCohortDefinition("malesAbove15CohortDimension",  ReportUtils.map(malesAbove15, "effectiveDate=${startDate},locationList=${locationList}"));
        compositionDimension.addCohortDefinition("femalesZeroTo14CohortDimension", ReportUtils.map(femalesZeroTo14, "effectiveDate=${startDate},locationList=${locationList}"));
        compositionDimension.addCohortDefinition("femalesAbove15CohortDimension", ReportUtils.map(femalesAbove15, "effectiveDate=${startDate},locationList=${locationList}"));

        //add cohort dimension for peds
        compositionDimension.addCohortDefinition("pedsMalesZeroTo1CohortDimension", ReportUtils.map(pedsMalesZeroTo1, "effectiveDate=${startDate},locationList=${locationList}"));
        compositionDimension.addCohortDefinition("pedsFemalesZeroTo1CohortDimension", ReportUtils.map(pedsFemalesZeroTo1, "effectiveDate=${startDate},locationList=${locationList}"));
        compositionDimension.addCohortDefinition("pedsmales2To4CohortDimension", ReportUtils.map(pedsmales2To4, "effectiveDate=${startDate},locationList=${locationList}"));
        compositionDimension.addCohortDefinition("pedsFemales2To4CohortDimension", ReportUtils.map(pedsFemales2To4, "effectiveDate=${startDate},locationList=${locationList}"));
        compositionDimension.addCohortDefinition("pedsmales5To14CohortDimension", ReportUtils.map(pedsmales5To14, "effectiveDate=${startDate},locationList=${locationList}"));
        compositionDimension.addCohortDefinition("pedsFemales5To14CohortDimension", ReportUtils.map(pedsFemales5To14, "effectiveDate=${startDate},locationList=${locationList}"));

        CohortIndicator malesZeroTo14ind = createCohortIndicator("malesZeroTo14CohortIndicator",ReportUtils.map(malesZeroTo14, "effectiveDate=${startDate},locationList=${locationList}"));

        CohortIndicator malesAbove15ind = createCohortIndicator("malesAbove15CohortIndicator", ReportUtils.map(malesAbove15, "effectiveDate=${startDate},locationList=${locationList}"));

        CohortIndicator femalesZeroTo14ind = createCohortIndicator("femalesZeroTo14CohortIndicator", ReportUtils.map(femalesZeroTo14, "effectiveDate=${startDate},locationList=${locationList}"));

        CohortIndicator femalesAbove15ind = createCohortIndicator("femalesAbove15CohortIndicator", ReportUtils.map(femalesAbove15, "effectiveDate=${startDate},locationList=${locationList}"));

        /**
         * Add indicators for peds
         */
        CohortIndicator pedsMalesZeroTo1ind = createCohortIndicator("pedsMalesZeroTo1CohortIndicator", ReportUtils.map(pedsMalesZeroTo1, "effectiveDate=${startDate},locationList=${locationList}"));
        CohortIndicator pedsFemalesZeroTo1ind = createCohortIndicator("pedsFemalesZeroTo1CohortIndicator",ReportUtils.map(pedsFemalesZeroTo1, "effectiveDate=${startDate},locationList=${locationList}"));
        CohortIndicator pedsmales2To4ind = createCohortIndicator("pedsmales2To4CohortIndicator", ReportUtils.map(pedsmales2To4, "effectiveDate=${startDate},locationList=${locationList}"));
        CohortIndicator pedsFemales2To4ind = createCohortIndicator("pedsFemales2To4CohortIndicator", ReportUtils.map(pedsFemales2To4, "effectiveDate=${startDate},locationList=${locationList}"));
        CohortIndicator pedsmales5To14ind = createCohortIndicator("pedsmales5To14CohortIndicator", ReportUtils.map(pedsmales5To14, "effectiveDate=${startDate},locationList=${locationList}"));
        CohortIndicator pedsFemales5To14ind = createCohortIndicator("pedsFemales5To14CohortIndicator", ReportUtils.map(pedsFemales5To14, "effectiveDate=${startDate},locationList=${locationList}"));
        Map<String, Object> dimensionMappings = new HashMap<String, Object>();
        dimensionMappings.put("startDate", "${startDate}");
        dimensionMappings.put("locationList", "${locationList}");
        Map<String, Object> periodMappings = new HashMap<String, Object>();
        periodMappings.put("startDate", "${startDate}");
        periodMappings.put("endDate", "${endDate}");
        periodMappings.put("locationList", "${locationList}");

        Map<String, Object> indMappings = new HashMap<String, Object>();
        periodMappings.put("startDate", "${startDate}");
        periodMappings.put("endDate", "${endDate}");
        periodMappings.put("locationList", "${locationList}");

        CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
        dsd.addParameter(ReportingConstants.START_DATE_PARAMETER);
        dsd.addParameter(ReportingConstants.END_DATE_PARAMETER);
        dsd.addParameter(facility);

        dsd.addDimension("compositionDimension", new Mapped<CohortDefinitionDimension>(compositionDimension,dimensionMappings));
        dsd.addColumn("malesBelow15", "Males Below 15", new Mapped<CohortIndicator>(malesZeroTo14ind, periodMappings), "");
        dsd.addColumn("malesAbove15", "Males 15 or more", new Mapped<CohortIndicator>(malesAbove15ind, periodMappings), "");
        dsd.addColumn("femalesBelow15", "Females Below 15", new Mapped<CohortIndicator>(femalesZeroTo14ind, periodMappings), "");
        dsd.addColumn("femalesAbove15", "Females 15 or more", new Mapped<CohortIndicator>(femalesAbove15ind, periodMappings), "");
        //Make second  column
        dsd.addColumn("NEWmalesBelow15", "Males Below 15", new Mapped<CohortIndicator>(malesZeroTo14ind, periodMappings), "");
        dsd.addColumn("NEWmalesAbove15", "Males 15 or more", new Mapped<CohortIndicator>(malesAbove15ind, periodMappings), "");
        dsd.addColumn("NEWfemalesBelow15", "Females Below 15", new Mapped<CohortIndicator>(femalesZeroTo14ind, periodMappings), "");
        dsd.addColumn("NEWfemalesAbove15", "Females 15 or more", new Mapped<CohortIndicator>(femalesAbove15ind, periodMappings), "");
        /**
         * Add columns for peds
         */
        dsd.addColumn("malesPedsAt1", "Male Peds up to one year", new Mapped<CohortIndicator>(pedsMalesZeroTo1ind, periodMappings), "");
        dsd.addColumn("malesPedsBtw2n4", "Males peds between 2 and 4", new Mapped<CohortIndicator>(pedsmales2To4ind, periodMappings), "");
        dsd.addColumn("malesPedsBtw5n14", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(pedsmales5To14ind, periodMappings), "");
        dsd.addColumn("femalesPedsAt1", "Female peds at one ", new Mapped<CohortIndicator>(pedsFemalesZeroTo1ind, periodMappings), "");
        dsd.addColumn("femalesPedsBtw2n4", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(pedsFemales2To4ind, periodMappings), "");
        dsd.addColumn("femalesPedsBtw5n14", "Female peds between 5 and 14", new Mapped<CohortIndicator>(pedsFemales5To14ind, periodMappings), "");

        /**
         * Fill second column for peds
         */
        dsd.addColumn("NEWmalesPedsAt1", "Male Peds up to one year", new Mapped<CohortIndicator>(pedsMalesZeroTo1ind, periodMappings), "");
        dsd.addColumn("NEWmalesPedsBtw2n4", "Males peds between 2 and 4", new Mapped<CohortIndicator>(pedsmales2To4ind, periodMappings), "");
        dsd.addColumn("NEWmalesPedsBtw5n14", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(pedsmales5To14ind, periodMappings), "");
        dsd.addColumn("NEWfemalesPedsAt1", "Female peds at one ", new Mapped<CohortIndicator>(pedsFemalesZeroTo1ind, periodMappings), "");
        dsd.addColumn("NEWfemalesPedsBtw2n4", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(pedsFemales2To4ind, periodMappings), "");
        dsd.addColumn("NEWfemalesPedsBtw5n14", "Female peds between 5 and 14", new Mapped<CohortIndicator>(pedsFemales5To14ind, periodMappings), "");

		report.addDataSetDefinition(dsd, periodMappings);

		return report;
	}

	@Override
	public CohortDefinition getCohortDefinition() {
		return new CCCPatientCohortDefinition();
	}

	@Override
	public ReportDesign getReportDesign() {
		ReportDesign design = new ReportDesign();
		design.setName("CCC Patient App Register Design");
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

    public static CohortIndicator createCohortIndicator(String description, Mapped<CohortDefinition> mappedCohort) {
        CohortIndicator ind = new CohortIndicator(description);
        ind.addParameter(new Parameter("startDate", "Start Date", Date.class));
        ind.addParameter(new Parameter("endDate", "End Date", Date.class));
        ind.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        ind.setType(CohortIndicator.IndicatorType.COUNT);
        ind.setCohortDefinition(mappedCohort);
        return ind;
    }

    private void sampleIndicatorReport(){
        /*GenderCohortDefinition malesCohort = new GenderCohortDefinition();
		malesCohort.setName("Gender = Male");
		malesCohort.setMaleIncluded(true);

		GenderCohortDefinition femalesCohort = new GenderCohortDefinition();
		femalesCohort.setName("Gender = Female");
		femalesCohort.setFemaleIncluded(true);

		AgeCohortDefinition pedsCohort = new AgeCohortDefinition();
		pedsCohort.setName("Age < 15");
		pedsCohort.addParameter(new Parameter("effectiveDate", "Date", Date.class));
		pedsCohort.setMaxAge(14);

		CohortIndicator malesIndicator = new CohortIndicator("Count of males");
		malesIndicator.setCohortDefinition(malesCohort, "");

		CohortIndicator femalesIndicator = new CohortIndicator("Count of females");
		femalesIndicator.setCohortDefinition(femalesCohort, "");

		CohortIndicator pedsIndicator = new CohortIndicator("Count of peds");
		pedsIndicator.addParameter(new Parameter("date", "Date", Date.class));
		pedsIndicator.setCohortDefinition(pedsCohort, "effectiveDate=${date}");

		cohortDsd1 = new CohortIndicatorDataSetDefinition();
		cohortDsd1.setName("Cohort DSD1");
		cohortDsd1.addColumn("test-1", "Count of males", new Mapped<CohortIndicator>(malesIndicator, null), "");
		cohortDsd1.addColumn("test-3", "Count of peds", new Mapped<CohortIndicator>(pedsIndicator, null), "");

		cohortDsd2 = new CohortIndicatorDataSetDefinition();
		cohortDsd2.setName("Cohort DSD2");
		cohortDsd1.addColumn("test-2", "Count of females", new Mapped<CohortIndicator>(femalesIndicator, null), "");

		evaluationContext = new EvaluationContext();
		evaluationContext.addParameterValue("date", TestUtils.date(2012, 1, 1));
		evaluationContext.setBaseCohort(new Cohort(Context.getPatientService().getAllPatients()));





		==================================================================================================
        AgeCohortDefinition lessThanOne = new AgeCohortDefinition();
		lessThanOne.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
		lessThanOne.setMaxAge(1);

		CohortIndicator lessThanOneAtStart = new CohortIndicator();
		lessThanOneAtStart.addParameter(ReportingConstants.START_DATE_PARAMETER);
		lessThanOneAtStart.addParameter(ReportingConstants.END_DATE_PARAMETER);
		lessThanOneAtStart.setUuid(UUID.randomUUID().toString());
		Map<String, Object> mappings = new HashMap<String, Object>();
		mappings.put("effectiveDate", "${startDate}");
		lessThanOneAtStart.setCohortDefinition(lessThanOne, mappings);

		Map<String, Object> periodMappings = new HashMap<String, Object>();
		periodMappings.put("startDate", "${startDate}");
		periodMappings.put("endDate", "${endDate}");
		periodMappings.put("location", "${location}");




		==================================================================================================

		*/
    }
}