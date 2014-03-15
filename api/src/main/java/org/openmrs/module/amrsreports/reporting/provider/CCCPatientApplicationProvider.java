package org.openmrs.module.amrsreports.reporting.provider;

import org.apache.commons.io.IOUtils;
import org.openmrs.api.APIException;
import org.openmrs.module.amrsreports.MOHFacility;
import org.openmrs.module.amrsreports.reporting.CommonCohortLibrary;
import org.openmrs.module.amrsreports.reporting.cohort.definition.CCCPatientCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.AgeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.GenderCohortDefinition;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.openmrs.module.reporting.indicator.util.IndicatorUtil;
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
public class CCCPatientApplicationProvider extends ReportProvider {

    private static final String GENDER_MALE="Male";
    private static final String GENDER_FEMALE="Female";
    private CommonCohortLibrary commonCohorts = new CommonCohortLibrary();

	public CCCPatientApplicationProvider() {
		this.name = "CCC Patient Application";
		this.visible = true;
	}

	@Override
	public ReportDefinition getReportDefinition() {

		ReportDefinition report = new PeriodIndicatorReportDefinition();
		report.setName("CCC Patient Application");

        // set up parameters
        Parameter effectiveDate = new Parameter();
        effectiveDate.setName("effectiveDate");
        effectiveDate.setType(Date.class);

        Parameter facility = new Parameter();
        effectiveDate.setName("facility");
        effectiveDate.setType(MOHFacility.class);

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
        compositionDimension.addCohortDefinition("malesZeroTo14CohortDimension", malesZeroTo14, null);
        compositionDimension.addCohortDefinition("malesAbove15CohortDimension", malesAbove15, null);
        compositionDimension.addCohortDefinition("femalesZeroTo14CohortDimension",femalesZeroTo14,null);
        compositionDimension.addCohortDefinition("femalesAbove15CohortDimension",femalesAbove15,null);

        //add cohort dimension for peds
        compositionDimension.addCohortDefinition("pedsMalesZeroTo1CohortDimension",pedsMalesZeroTo1,null);
        compositionDimension.addCohortDefinition("pedsFemalesZeroTo1CohortDimension",pedsFemalesZeroTo1,null);
        compositionDimension.addCohortDefinition("pedsmales2To4CohortDimension",pedsmales2To4,null);
        compositionDimension.addCohortDefinition("pedsFemales2To4CohortDimension",pedsFemales2To4,null);
        compositionDimension.addCohortDefinition("pedsmales5To14CohortDimension",pedsmales5To14,null);
        compositionDimension.addCohortDefinition("pedsFemales5To14CohortDimension",pedsFemales5To14,null);



        CohortIndicator malesZeroTo14ind = CohortIndicator.newCountIndicator("malesZeroTo14CohortIndicator", new Mapped<CohortDefinition>(malesZeroTo14, null), null);
        //malesZeroTo14ind.setParameters(IndicatorUtil.getDefaultParameters());

        CohortIndicator malesAbove15ind = CohortIndicator.newCountIndicator("malesAbove15CohortIndicator", new Mapped<CohortDefinition>(malesAbove15, null), null);
        //malesAbove15ind.setParameters(IndicatorUtil.getDefaultParameters());

        CohortIndicator femalesZeroTo14ind = CohortIndicator.newCountIndicator("femalesZeroTo14CohortIndicator", new Mapped<CohortDefinition>(femalesZeroTo14, null), null);
        //femalesZeroTo14ind.setParameters(IndicatorUtil.getDefaultParameters());

        CohortIndicator femalesAbove15ind = CohortIndicator.newCountIndicator("femalesAbove15CohortIndicator", new Mapped<CohortDefinition>(femalesAbove15, null), null);
        //femalesAbove15ind.setParameters(IndicatorUtil.getDefaultParameters());

        /**
         * Add indicators for peds
         */
        CohortIndicator pedsMalesZeroTo1ind = CohortIndicator.newCountIndicator("pedsMalesZeroTo1CohortIndicator", new Mapped<CohortDefinition>(pedsMalesZeroTo1, null), null);
        CohortIndicator pedsFemalesZeroTo1ind = CohortIndicator.newCountIndicator("pedsFemalesZeroTo1CohortIndicator", new Mapped<CohortDefinition>(pedsFemalesZeroTo1, null), null);
        CohortIndicator pedsmales2To4ind = CohortIndicator.newCountIndicator("pedsmales2To4CohortIndicator", new Mapped<CohortDefinition>(pedsmales2To4, null), null);
        CohortIndicator pedsFemales2To4ind = CohortIndicator.newCountIndicator("pedsFemales2To4CohortIndicator", new Mapped<CohortDefinition>(pedsFemales2To4, null), null);
        CohortIndicator pedsmales5To14ind = CohortIndicator.newCountIndicator("pedsmales5To14CohortIndicator", new Mapped<CohortDefinition>(pedsmales5To14, null), null);
        CohortIndicator pedsFemales5To14ind = CohortIndicator.newCountIndicator("pedsFemales5To14CohortIndicator", new Mapped<CohortDefinition>(pedsFemales5To14, null), null);


        CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();

        dsd.addDimension("compositionDimension", new Mapped<CohortDefinitionDimension>(compositionDimension, null));
        dsd.addColumn("malesBelow15", "Males Below 15", new Mapped<CohortIndicator>(malesZeroTo14ind, null), "");
        dsd.addColumn("malesAbove15", "Males 15 or more", new Mapped<CohortIndicator>(malesAbove15ind, null), "");
        dsd.addColumn("femalesBelow15", "Females Below 15", new Mapped<CohortIndicator>(femalesZeroTo14ind, null), "");
        dsd.addColumn("femalesAbove15", "Females 15 or more", new Mapped<CohortIndicator>(femalesAbove15ind, null), "");

        //Make second  column
        dsd.addColumn("NEWmalesBelow15", "Males Below 15", new Mapped<CohortIndicator>(malesZeroTo14ind, null), "");
        dsd.addColumn("NEWmalesAbove15", "Males 15 or more", new Mapped<CohortIndicator>(malesAbove15ind, null), "");
        dsd.addColumn("NEWfemalesBelow15", "Females Below 15", new Mapped<CohortIndicator>(femalesZeroTo14ind, null), "");
        dsd.addColumn("NEWfemalesAbove15", "Females 15 or more", new Mapped<CohortIndicator>(femalesAbove15ind, null), "");

        /**
         * Add columns for peds
         */
        dsd.addColumn("malesPedsAt1", "Male Peds up to one year", new Mapped<CohortIndicator>(pedsMalesZeroTo1ind, null), "");
        dsd.addColumn("malesPedsBtw2n4", "Males peds between 2 and 4", new Mapped<CohortIndicator>(pedsmales2To4ind, null), "");
        dsd.addColumn("malesPedsBtw5n14", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(pedsmales5To14ind, null), "");
        dsd.addColumn("femalesPedsAt1", "Female peds at one ", new Mapped<CohortIndicator>(pedsFemalesZeroTo1ind, null), "");
        dsd.addColumn("femalesPedsBtw2n4", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(pedsFemales2To4ind, null), "");
        dsd.addColumn("femalesPedsBtw5n14", "Female peds between 5 and 14", new Mapped<CohortIndicator>(pedsFemales5To14ind, null), "");


        /**
         * Fill second column for peds
         */
        dsd.addColumn("NEWmalesPedsAt1", "Male Peds up to one year", new Mapped<CohortIndicator>(pedsMalesZeroTo1ind, null), "");
        dsd.addColumn("NEWmalesPedsBtw2n4", "Males peds between 2 and 4", new Mapped<CohortIndicator>(pedsmales2To4ind, null), "");
        dsd.addColumn("NEWmalesPedsBtw5n14", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(pedsmales5To14ind, null), "");
        dsd.addColumn("NEWfemalesPedsAt1", "Female peds at one ", new Mapped<CohortIndicator>(pedsFemalesZeroTo1ind, null), "");
        dsd.addColumn("NEWfemalesPedsBtw2n4", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(pedsFemales2To4ind, null), "");
        dsd.addColumn("NEWfemalesPedsBtw5n14", "Female peds between 5 and 14", new Mapped<CohortIndicator>(pedsFemales5To14ind, null), "");


      /*  EvaluationContext context = new EvaluationContext();
        context.addParameterValue(ReportingConstants.START_DATE_PARAMETER.getName(), DateUtil.getDateTime(1980, 1, 1));
        context.addParameterValue(ReportingConstants.END_DATE_PARAMETER.getName(), DateUtil.getDateTime(2000, 1, 1));


*/
		Map<String, Object> mappings = new HashMap<String, Object>();
		mappings.put("facility", "${facility}");
		report.addDataSetDefinition(dsd, null);

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

		/*Properties props = new Properties();
		props.put("repeatingSections", "sheet:1,row:7,dataset:allPatients");

		design.setProperties(props);*/

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

    private CompositionCohortDefinition buildCompositionGenderAndAgeCohort(String gender, Integer minAge,Integer maxAge){

        GenderCohortDefinition genderCohort = new GenderCohortDefinition();
        AgeCohortDefinition ageCohort = new AgeCohortDefinition();
        if(gender.equals(CCCPatientApplicationProvider.GENDER_FEMALE)){
            genderCohort.setName("Gender");
            genderCohort.setFemaleIncluded(true);
        }
        else {
            genderCohort.setName("Gender");
            genderCohort.setMaleIncluded(true);
        }

        if(minAge != null && maxAge !=null){
            ageCohort.setName("Age");
            ageCohort.setMinAge(minAge);
            ageCohort.setMaxAge(maxAge);
        }

        if(minAge != null){
            ageCohort.setName("Age");
            ageCohort.setMinAge(minAge);
        }

        if(maxAge !=null){
            ageCohort.setName("Age");
            ageCohort.setMaxAge(maxAge);
        }

        CompositionCohortDefinition ccd = new CompositionCohortDefinition();
        ccd.setName("Composition Cohort For Gender and Age");
        ccd.addSearch("gender",genderCohort,null);
        ccd.addSearch("age",ageCohort,null);
        ccd.setCompositionString("AND");
      return ccd;
    }

    private void hold(){
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