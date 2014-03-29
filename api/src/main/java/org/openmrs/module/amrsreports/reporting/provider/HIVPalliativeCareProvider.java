package org.openmrs.module.amrsreports.reporting.provider;

import org.apache.commons.io.IOUtils;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.api.APIException;
import org.openmrs.module.amrsreports.MOHFacility;
import org.openmrs.module.amrsreports.cache.MohCacheUtils;
import org.openmrs.module.amrsreports.reporting.CommonCohortLibrary;
import org.openmrs.module.amrsreports.reporting.CommonICAPCohortLibrary;
import org.openmrs.module.amrsreports.reporting.CommonIndicatorLibrary;
import org.openmrs.module.amrsreports.reporting.ReportUtils;
import org.openmrs.module.amrsreports.reporting.cohort.definition.CCCPatientCohortDefinition;
import org.openmrs.module.amrsreports.rule.MohEvaluableNameConstants;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides mechanisms for rendering the MOH 361A Pre-ART Register
 */
public class HIVPalliativeCareProvider extends ReportProvider {


    private CommonICAPCohortLibrary commonCohorts = new CommonICAPCohortLibrary();

	public HIVPalliativeCareProvider() {
		this.name = "1.0 HIV Palliative Care";
		this.visible = true;
	}

	@Override
	public ReportDefinition getReportDefinition() {

		ReportDefinition report = new PeriodIndicatorReportDefinition();
		report.setName("1.0 HIV Palliative Care");

        // set up parameters
        Parameter facility = new Parameter();
        facility.setName("locationList");
        facility.setType(Location.class);

        //define general cohorts

        CohortDefinition malesZeroTo14Cohort = commonCohorts.malesAgedAtMostAtFacility(14);
        CohortDefinition malesAbove15Cohort = commonCohorts.malesAgedAtLeastAtFacility(15);
        CohortDefinition femalesZeroTo14Cohort = commonCohorts.femalesAgedAtMostAtFacility(14);
        CohortDefinition femalesAbove15Cohort = commonCohorts.femalesAgedAtLeastAtFacility(15);
        CohortDefinition pedsMalesZeroTo1Cohort = commonCohorts.malesAgedAtMostAtFacility(1);
        CohortDefinition pedsFemalesZeroTo1Cohort = commonCohorts.femalesAgedAtMostAtFacility(1);
        CohortDefinition pedsmales2To4Cohort = commonCohorts.malesAgedBetweenAtFacility(2,4);
        CohortDefinition pedsFemales2To4Cohort = commonCohorts.femalesAgedBetweenAtFacility(2,4);
        CohortDefinition pedsmales5To14Cohort = commonCohorts.malesAgedBetweenAtFacility(5,14);
        CohortDefinition pedsFemales5To14Cohort = commonCohorts.femalesAgedBetweenAtFacility(5,14);

        //Cohort for drugs
        Concept currentMedication = MohCacheUtils.getConcept(MohEvaluableNameConstants.CURRENT_MEDICATIONS);
        Concept currentDrug = MohCacheUtils.getConcept(MohEvaluableNameConstants.NEVIRAPINE);
       /* List<Concept> ansList = new ArrayList<Concept>();
        ansList.add(currentDrug);*/
        CohortDefinition malesAgedBelow15OnMedCohort = commonCohorts.malesBelowAgeOnMedication(14,currentMedication,currentDrug);
        CohortDefinition malesAbove15OnMedCohort = commonCohorts.malesAboveAgeOnMedication(15,currentMedication,currentDrug);
        CohortDefinition femalesBelow15onMedCohort = commonCohorts.femalesBelowAgeOnMedication(14,currentMedication,currentDrug);
        CohortDefinition femalesAbove15onMedCohort = commonCohorts.femalesAboveAgeOnMedication(15,currentMedication,currentDrug);

        CohortDefinition pedsMalesZeroTo1onMedCohort = commonCohorts.malesBelowAgeOnMedication(1,currentMedication,currentDrug);
        CohortDefinition pedsfemalesZeroTo1onMedCohort = commonCohorts.femalesBelowAgeOnMedication(1,currentMedication,currentDrug);
        CohortDefinition pedsmales2To4onMedCohort = commonCohorts.malesBetweenAgeOnMedication(2,4,currentMedication,currentDrug);
        CohortDefinition pedsfemales2To4onMedCohort = commonCohorts.femalesBetweenAgeOnMedication(2,4,currentMedication,currentDrug);
        CohortDefinition pedsmales5To14onMedCohort = commonCohorts.malesBetweenAgeOnMedication(5,14,currentMedication,currentDrug);
        CohortDefinition pedsFemales5To14onMedCohort = commonCohorts.femalesBetweenAgeOnMedication(5,14,currentMedication,currentDrug);


        CohortDefinitionDimension compositionDimension = new CohortDefinitionDimension();
        compositionDimension.setName("compositionDimension");
        compositionDimension.addParameter(new Parameter("startDate", "Start Date", Date.class));
        compositionDimension.addParameter(new Parameter("endDate", "End Date", Date.class));
        compositionDimension.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        compositionDimension.addCohortDefinition("malesZeroTo14CohortDimension", ReportUtils.map(malesZeroTo14Cohort, "effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("malesAbove15CohortDimension",  ReportUtils.map(malesAbove15Cohort, "effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("femalesZeroTo14CohortDimension", ReportUtils.map(femalesZeroTo14Cohort, "effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("femalesAbove15CohortDimension", ReportUtils.map(femalesAbove15Cohort, "effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));

        //add cohort dimension for peds
        compositionDimension.addCohortDefinition("pedsMalesZeroTo1CohortDimension", ReportUtils.map(pedsMalesZeroTo1Cohort, "effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("pedsFemalesZeroTo1CohortDimension", ReportUtils.map(pedsFemalesZeroTo1Cohort, "effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("pedsmales2To4CohortDimension", ReportUtils.map(pedsmales2To4Cohort, "effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("pedsFemales2To4CohortDimension", ReportUtils.map(pedsFemales2To4Cohort, "effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("pedsmales5To14CohortDimension", ReportUtils.map(pedsmales5To14Cohort, "effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("pedsFemales5To14CohortDimension", ReportUtils.map(pedsFemales5To14Cohort, "effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));

        //add cohort for medication
        compositionDimension.addCohortDefinition("MalesBelow15OnMedDimension",ReportUtils.map(malesAbove15OnMedCohort,"effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("MalesAbove15OnMedDimension",ReportUtils.map(malesAbove15OnMedCohort,"effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("FemalesBelow15OnMedDimension",ReportUtils.map(malesAbove15OnMedCohort,"effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("FemalesAbove15OnMedDimension",ReportUtils.map(malesAbove15OnMedCohort,"effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("pedsMalesZeroTo1OnMedDimension",ReportUtils.map(malesAbove15OnMedCohort,"effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("pedsFemalesZeroTo1OnMedDimension",ReportUtils.map(malesAbove15OnMedCohort,"effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("pedsmales2To4OnMedDimension",ReportUtils.map(malesAbove15OnMedCohort,"effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("pedsFemales2To4OnMedDimension",ReportUtils.map(malesAbove15OnMedCohort,"effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("pedsmales5To14OnMedDimension",ReportUtils.map(malesAbove15OnMedCohort,"effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));
        compositionDimension.addCohortDefinition("pedsFemales5To14OnMedDimension",ReportUtils.map(malesAbove15OnMedCohort,"effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}"));


        CohortIndicator malesZeroTo14ind = CommonIndicatorLibrary.createCohortIndicatorAtStart("malesZeroTo14CohortIndicator",malesZeroTo14Cohort);

        CohortIndicator malesAbove15ind = CommonIndicatorLibrary.createCohortIndicatorAtStart("malesAbove15CohortIndicator", malesAbove15Cohort);

        CohortIndicator femalesZeroTo14ind = CommonIndicatorLibrary.createCohortIndicatorAtStart("femalesZeroTo14CohortIndicator", femalesZeroTo14Cohort);

        CohortIndicator femalesAbove15ind = CommonIndicatorLibrary.createCohortIndicatorAtStart("femalesAbove15CohortIndicator", femalesAbove15Cohort);

        /**
         * Add indicators for peds
         */
        CohortIndicator pedsMalesZeroTo1ind = CommonIndicatorLibrary.createCohortIndicatorAtStart("pedsMalesZeroTo1CohortIndicator", pedsMalesZeroTo1Cohort);
        CohortIndicator pedsFemalesZeroTo1ind = CommonIndicatorLibrary.createCohortIndicatorAtStart("pedsFemalesZeroTo1CohortIndicator", pedsFemalesZeroTo1Cohort);
        CohortIndicator pedsmales2To4ind = CommonIndicatorLibrary.createCohortIndicatorAtStart("pedsmales2To4CohortIndicator", pedsmales2To4Cohort);
        CohortIndicator pedsFemales2To4ind = CommonIndicatorLibrary.createCohortIndicatorAtStart("pedsFemales2To4CohortIndicator", pedsFemales2To4Cohort);
        CohortIndicator pedsmales5To14ind = CommonIndicatorLibrary.createCohortIndicatorAtStart("pedsmales5To14CohortIndicator", pedsmales5To14Cohort);
        CohortIndicator pedsFemales5To14ind = CommonIndicatorLibrary.createCohortIndicatorAtStart("pedsFemales5To14CohortIndicator", pedsFemales5To14Cohort);

        /**
         * Define indicators for end date
         */

        CohortIndicator malesZeroTo14indend = CommonIndicatorLibrary.createCohortIndicatorAtEnd("malesZeroTo14CohortIndicatorEnd", malesZeroTo14Cohort);

        CohortIndicator malesAbove15indend = CommonIndicatorLibrary.createCohortIndicatorAtEnd("malesAbove15CohortIndicatorEnd", malesAbove15Cohort);

        CohortIndicator femalesZeroTo14indend = CommonIndicatorLibrary.createCohortIndicatorAtEnd("femalesZeroTo14CohortIndicatorEnd", femalesZeroTo14Cohort);

        CohortIndicator femalesAbove15indend = CommonIndicatorLibrary.createCohortIndicatorAtEnd("femalesAbove15CohortIndicatorEnd", femalesAbove15Cohort);

        /**
         * Add indicators for peds
         */
        CohortIndicator pedsMalesZeroTo1indend = CommonIndicatorLibrary.createCohortIndicatorAtEnd("pedsMalesZeroTo1CohortIndicatorEnd", pedsMalesZeroTo1Cohort);
        CohortIndicator pedsFemalesZeroTo1indend = CommonIndicatorLibrary.createCohortIndicatorAtEnd("pedsFemalesZeroTo1CohortIndicatorEnd", pedsFemalesZeroTo1Cohort);
        CohortIndicator pedsmales2To4indend = CommonIndicatorLibrary.createCohortIndicatorAtEnd("pedsmales2To4CohortIndicatorEnd", pedsmales2To4Cohort);
        CohortIndicator pedsFemales2To4indend = CommonIndicatorLibrary.createCohortIndicatorAtEnd("pedsFemales2To4CohortIndicatorEnd", pedsFemales2To4Cohort);
        CohortIndicator pedsmales5To14indend = CommonIndicatorLibrary.createCohortIndicatorAtEnd("pedsmales5To14CohortIndicatorEnd", pedsmales5To14Cohort);
        CohortIndicator pedsFemales5To14indend = CommonIndicatorLibrary.createCohortIndicatorAtEnd("pedsFemales5To14CohortIndicatorEnd", pedsFemales5To14Cohort);

        CohortIndicator malesBelow15OnMedCohortIndi = CommonIndicatorLibrary.createCohortIndicatorAtStart("malesBelow15OnMedCohortIndicator",malesAgedBelow15OnMedCohort);
        CohortIndicator malesAbove15OnMedCohortIndi = CommonIndicatorLibrary.createCohortIndicatorAtStart("malesAbove15OnMedCohortIndicator",malesAbove15OnMedCohort);
        CohortIndicator femalesBelow15OnMedCohortIndi = CommonIndicatorLibrary.createCohortIndicatorAtStart("femalesBelow15OnMedCohortIndicator",femalesBelow15onMedCohort);
        CohortIndicator femalesAbove15OnMedCohortIndi = CommonIndicatorLibrary.createCohortIndicatorAtStart("femalesAbove15OnMedCohortIndicator",femalesAbove15onMedCohort);
        CohortIndicator pedsMalesZeroTo1OnMedCohortIndi = CommonIndicatorLibrary.createCohortIndicatorAtStart("malesZeroTo14CohortIndicator",pedsMalesZeroTo1onMedCohort);
        CohortIndicator pedsFemalesZeroTo1OnMedCohortIndi = CommonIndicatorLibrary.createCohortIndicatorAtStart("pedsMalesZeroTo1OnMedCohortIndicator",pedsfemalesZeroTo1onMedCohort);
        CohortIndicator pedsmales2To4OnMedCohortIndi = CommonIndicatorLibrary.createCohortIndicatorAtStart("pedsmales2To4OnMedCohortIndicator",pedsmales2To4onMedCohort);
        CohortIndicator pedsFemales2To4OnMedCohortIndi = CommonIndicatorLibrary.createCohortIndicatorAtStart("pedsFemales2To4OnMedCohortIndicator",pedsfemales2To4onMedCohort);
        CohortIndicator pedsmales5To14OnMedCohortIndi = CommonIndicatorLibrary.createCohortIndicatorAtStart("pedsmales5To14CohortIndicator",pedsmales5To14onMedCohort);
        CohortIndicator pedsFemales5To14OnMedCohortIndi = CommonIndicatorLibrary.createCohortIndicatorAtStart("pedsFemales5To14CohortIndicator",pedsFemales5To14onMedCohort);

        Map<String, Object> dimensionMappings = new HashMap<String, Object>();
        dimensionMappings.put("startDate", "${startDate}");
        dimensionMappings.put("locationList", "${locationList}");

        Map<String, Object> periodMappings = new HashMap<String, Object>();
        periodMappings.put("startDate", "${startDate}");
        periodMappings.put("endDate", "${endDate}");
        periodMappings.put("locationList", "${locationList}");

        CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
        dsd.addParameter(ReportingConstants.START_DATE_PARAMETER);
        dsd.addParameter(ReportingConstants.END_DATE_PARAMETER);
        dsd.addParameter(facility);

        //set reportDefinition params
        report.addParameter(ReportingConstants.START_DATE_PARAMETER);
        report.addParameter(ReportingConstants.END_DATE_PARAMETER);
        report.addParameter(facility);

        //dsd.addDimension("compositionDimension", new Mapped<CohortDefinitionDimension>(compositionDimension,dimensionMappings));
        dsd.addColumn("malesBelow15", "Males Below 15", new Mapped<CohortIndicator>(malesZeroTo14ind, periodMappings), "");
       /* dsd.addColumn("malesAbove15", "Males 15 or more", new Mapped<CohortIndicator>(malesAbove15ind, periodMappings), "");
        dsd.addColumn("femalesBelow15", "Females Below 15", new Mapped<CohortIndicator>(femalesZeroTo14ind, periodMappings), "");
        dsd.addColumn("femalesAbove15", "Females 15 or more", new Mapped<CohortIndicator>(femalesAbove15ind, periodMappings), "");*/
        //Make second  column
        dsd.addColumn("NEWmalesBelow15", "Males Below 15", new Mapped<CohortIndicator>(malesZeroTo14indend, periodMappings), "");
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