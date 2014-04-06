package org.openmrs.module.amrsreports.reporting.provider;

import org.apache.commons.io.IOUtils;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrsreports.reporting.ArtCareFollowUpCohortLibrary;
import org.openmrs.module.amrsreports.reporting.CommonIndicatorLibrary;
import org.openmrs.module.amrsreports.reporting.RegimensCohortLibrary;
import org.openmrs.module.amrsreports.reporting.ReportUtils;
import org.openmrs.module.amrsreports.reporting.cohort.definition.CCCPatientCohortDefinition;
import org.openmrs.module.amrsreports.reporting.cohort.definition.RegimensCohortDefinition;
import org.openmrs.module.reporting.ReportingConstants;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.CohortIndicator;
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

/**
 * Provides mechanisms for rendering the MOH 361A Pre-ART Register
 */
public class RegimensProvider extends ReportProvider {

    private ArtCareFollowUpCohortLibrary commonCohorts = new ArtCareFollowUpCohortLibrary();
    private RegimensCohortLibrary sqlCohort = new RegimensCohortLibrary();
    private static final Integer ART_STOP_DATE_CONCEPT = 1679;//160739;
    private static final Integer TRANSFER_OUT_CONCEPT = 1285;//160649;
    private static final Integer DEATH_CONCEPT = 159;//1543;

	public RegimensProvider() {
		this.name = "Regimens";
		this.visible = true;
	}

	@Override
	public ReportDefinition getReportDefinition() {


        ReportDefinition report = new PeriodIndicatorReportDefinition();
        report.setName("Regimens");

        // set up parameters
        Parameter facility = new Parameter();
        facility.setName("locationList");
        facility.setType(Location.class);

        //define general cohorts
        Concept artStopDateConcept = Context.getConceptService().getConcept(ART_STOP_DATE_CONCEPT);
        Concept transferOut = Context.getConceptService().getConcept(TRANSFER_OUT_CONCEPT);
        Concept deathConcept = Context.getConceptService().getConcept(DEATH_CONCEPT);

        CohortDefinition malesZeroTo14Cohort = sqlCohort.createCohortDefinition("Sql Cohort Test",sqlCohort.getQry());
       CohortDefinition malesAbove15Cohort = sqlCohort.compositionAgeCohort(0,14);
        /* CohortDefinition femalesZeroTo14Cohort = commonCohorts.femalesBelowAgeWithConcepts(14,artStopDateConcept);
        CohortDefinition femalesAbove15Cohort = commonCohorts.femalesAboveAgeWithConcepts(15,artStopDateConcept);
        CohortDefinition pedsMalesZeroTo1Cohort = commonCohorts.malesBetweenAgeWithConcepts(0,1,artStopDateConcept);
        CohortDefinition pedsFemalesZeroTo1Cohort = commonCohorts.femalesBetweenAgeWithConcepts(0,1,artStopDateConcept);
        CohortDefinition pedsmales2To4Cohort = commonCohorts.malesBetweenAgeWithConcepts(2,4,artStopDateConcept);
        CohortDefinition pedsFemales2To4Cohort = commonCohorts.femalesBetweenAgeWithConcepts(2,4,artStopDateConcept);
        CohortDefinition pedsmales5To14Cohort = commonCohorts.malesBetweenAgeWithConcepts(5,14,artStopDateConcept);
        CohortDefinition pedsFemales5To14Cohort = commonCohorts.femalesBetweenAgeWithConcepts(5,14,artStopDateConcept);

        //add cohorts for transfer out
        CohortDefinition malesZeroTo14TCohort = commonCohorts.malesBelowAgeWithConcepts(14,transferOut);
        CohortDefinition malesAbove15TCohort = commonCohorts.malesAboveAgeWithConcepts(15,transferOut);
        CohortDefinition femalesZeroTo14TCohort = commonCohorts.femalesBelowAgeWithConcepts(14,transferOut);
        CohortDefinition femalesAbove15TCohort = commonCohorts.femalesAboveAgeWithConcepts(15,transferOut);
        CohortDefinition pedsMalesZeroTo1TCohort = commonCohorts.malesBetweenAgeWithConcepts(0,1,transferOut);
        CohortDefinition pedsFemalesZeroTo1TCohort = commonCohorts.femalesBetweenAgeWithConcepts(0,1,transferOut);
        CohortDefinition pedsmales2To4TCohort = commonCohorts.malesBetweenAgeWithConcepts(2,4,transferOut);
        CohortDefinition pedsFemales2To4TCohort = commonCohorts.femalesBetweenAgeWithConcepts(2,4,transferOut);
        CohortDefinition pedsmales5To14TCohort = commonCohorts.malesBetweenAgeWithConcepts(5,14,transferOut);
        CohortDefinition pedsFemales5To14TCohort = commonCohorts.femalesBetweenAgeWithConcepts(5,14,transferOut);

        //Define cohort for the dead
        CohortDefinition malesZeroTo14DCohort = commonCohorts.malesBelowAgeWithConcepts(14,deathConcept);
        CohortDefinition malesAbove15DCohort = commonCohorts.malesAboveAgeWithConcepts(15,deathConcept);
        CohortDefinition femalesZeroTo14DCohort = commonCohorts.femalesBelowAgeWithConcepts(14,deathConcept);
        CohortDefinition femalesAbove15DCohort = commonCohorts.femalesAboveAgeWithConcepts(15,deathConcept);
        CohortDefinition pedsMalesZeroTo1DCohort = commonCohorts.malesBetweenAgeWithConcepts(0,1,deathConcept);
        CohortDefinition pedsFemalesZeroTo1DCohort = commonCohorts.femalesBetweenAgeWithConcepts(0,1,deathConcept);
        CohortDefinition pedsmales2To4DCohort = commonCohorts.malesBetweenAgeWithConcepts(2,4,deathConcept);
        CohortDefinition pedsFemales2To4DCohort = commonCohorts.femalesBetweenAgeWithConcepts(2,4,deathConcept);
        CohortDefinition pedsmales5To14DCohort = commonCohorts.malesBetweenAgeWithConcepts(5,14,deathConcept);
        CohortDefinition pedsFemales5To14DCohort = commonCohorts.femalesBetweenAgeWithConcepts(5,14,deathConcept);*/


        CohortIndicator malesZeroTo14ind = CommonIndicatorLibrary.createCohortIndicator("malesZeroTo14CohortIndicator",ReportUtils.map(malesZeroTo14Cohort,""));

        CohortIndicator malesAbove15ind = CommonIndicatorLibrary.createCohortIndicator("malesAbove15CohortIndicator", ReportUtils.map(malesAbove15Cohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));

        /*CohortIndicator femalesZeroTo14ind = CommonIndicatorLibrary.createCohortIndicator("femalesZeroTo14CohortIndicator", ReportUtils.map(femalesZeroTo14Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator femalesAbove15ind = CommonIndicatorLibrary.createCohortIndicator("femalesAbove15CohortIndicator", ReportUtils.map(femalesAbove15Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator pedsMalesZeroTo1ind = CommonIndicatorLibrary.createCohortIndicator("pedsMalesZeroTo1CohortIndicator", ReportUtils.map(pedsMalesZeroTo1Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemalesZeroTo1ind = CommonIndicatorLibrary.createCohortIndicator("pedsFemalesZeroTo1CohortIndicator", ReportUtils.map(pedsFemalesZeroTo1Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsmales2To4ind = CommonIndicatorLibrary.createCohortIndicator("pedsmales2To4CohortIndicator", ReportUtils.map(pedsmales2To4Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemales2To4ind = CommonIndicatorLibrary.createCohortIndicator("pedsFemales2To4CohortIndicator", ReportUtils.map(pedsFemales2To4Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsmales5To14ind = CommonIndicatorLibrary.createCohortIndicator("pedsmales5To14CohortIndicator", ReportUtils.map(pedsmales5To14Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemales5To14ind = CommonIndicatorLibrary.createCohortIndicator("pedsFemales5To14CohortIndicator", ReportUtils.map(pedsFemales5To14Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));


        //indicators for transfers
        CohortIndicator malesZeroTo14Tind = CommonIndicatorLibrary.createCohortIndicator("malesZeroTo14CohortIndicator",ReportUtils.map(malesZeroTo14TCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator malesAbove15Tind = CommonIndicatorLibrary.createCohortIndicator("malesAbove15CohortIndicator", ReportUtils.map(malesAbove15TCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator femalesZeroTo14Tind = CommonIndicatorLibrary.createCohortIndicator("femalesZeroTo14CohortIndicator", ReportUtils.map(femalesZeroTo14TCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator femalesAbove15Tind = CommonIndicatorLibrary.createCohortIndicator("femalesAbove15CohortIndicator", ReportUtils.map(femalesAbove15TCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator pedsMalesZeroTo1Tind = CommonIndicatorLibrary.createCohortIndicator("pedsMalesZeroTo1CohortIndicator", ReportUtils.map(pedsMalesZeroTo1TCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemalesZeroTo1Tind = CommonIndicatorLibrary.createCohortIndicator("pedsFemalesZeroTo1CohortIndicator", ReportUtils.map(pedsFemalesZeroTo1TCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsmales2To4Tind = CommonIndicatorLibrary.createCohortIndicator("pedsmales2To4CohortIndicator", ReportUtils.map(pedsmales2To4TCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemales2To4Tind = CommonIndicatorLibrary.createCohortIndicator("pedsFemales2To4CohortIndicator", ReportUtils.map(pedsFemales2To4TCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsmales5To14Tind = CommonIndicatorLibrary.createCohortIndicator("pedsmales5To14CohortIndicator", ReportUtils.map(pedsmales5To14TCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemales5To14Tind = CommonIndicatorLibrary.createCohortIndicator("pedsFemales5To14CohortIndicator", ReportUtils.map(pedsFemales5To14TCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));


        //indicators for dead
        CohortIndicator malesZeroTo14Dind = CommonIndicatorLibrary.createCohortIndicator("malesZeroTo14CohortIndicator",ReportUtils.map(malesZeroTo14DCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator malesAbove15Dind = CommonIndicatorLibrary.createCohortIndicator("malesAbove15CohortIndicator", ReportUtils.map(malesAbove15DCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator femalesZeroTo14Dind = CommonIndicatorLibrary.createCohortIndicator("femalesZeroTo14CohortIndicator", ReportUtils.map(femalesZeroTo14DCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator femalesAbove15Dind = CommonIndicatorLibrary.createCohortIndicator("femalesAbove15CohortIndicator", ReportUtils.map(femalesAbove15DCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator pedsMalesZeroTo1Dind = CommonIndicatorLibrary.createCohortIndicator("pedsMalesZeroTo1CohortIndicator", ReportUtils.map(pedsMalesZeroTo1DCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemalesZeroTo1Dind = CommonIndicatorLibrary.createCohortIndicator("pedsFemalesZeroTo1CohortIndicator", ReportUtils.map(pedsFemalesZeroTo1DCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsmales2To4Dind = CommonIndicatorLibrary.createCohortIndicator("pedsmales2To4CohortIndicator", ReportUtils.map(pedsmales2To4DCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemales2To4Dind = CommonIndicatorLibrary.createCohortIndicator("pedsFemales2To4CohortIndicator", ReportUtils.map(pedsFemales2To4DCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsmales5To14Dind = CommonIndicatorLibrary.createCohortIndicator("pedsmales5To14CohortIndicator", ReportUtils.map(pedsmales5To14DCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemales5To14Dind = CommonIndicatorLibrary.createCohortIndicator("pedsFemales5To14CohortIndicator", ReportUtils.map(pedsFemales5To14DCohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
*/
        /**
         * Define indicators for end date
         */

       /* CohortIndicator malesZeroTo14indend = CommonIndicatorLibrary.createCohortIndicator("malesZeroTo14CohortIndicatorEnd", ReportUtils.map(malesZeroTo14Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator malesAbove15indend = CommonIndicatorLibrary.createCohortIndicator("malesAbove15CohortIndicatorEnd", ReportUtils.map(malesAbove15Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator femalesZeroTo14indend = CommonIndicatorLibrary.createCohortIndicator("femalesZeroTo14CohortIndicatorEnd", ReportUtils.map(femalesZeroTo14Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator femalesAbove15indend = CommonIndicatorLibrary.createCohortIndicator("femalesAbove15CohortIndicatorEnd", ReportUtils.map(femalesAbove15Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));

        CohortIndicator pedsMalesZeroTo1indend = CommonIndicatorLibrary.createCohortIndicator("pedsMalesZeroTo1CohortIndicatorEnd", ReportUtils.map(pedsMalesZeroTo1Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemalesZeroTo1indend = CommonIndicatorLibrary.createCohortIndicator("pedsFemalesZeroTo1CohortIndicatorEnd", ReportUtils.map(pedsFemalesZeroTo1Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsmales2To4indend = CommonIndicatorLibrary.createCohortIndicator("pedsmales2To4CohortIndicatorEnd", ReportUtils.map(pedsmales2To4Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemales2To4indend = CommonIndicatorLibrary.createCohortIndicator("pedsFemales2To4CohortIndicatorEnd", ReportUtils.map(pedsFemales2To4Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsmales5To14indend = CommonIndicatorLibrary.createCohortIndicator("pedsmales5To14CohortIndicatorEnd", ReportUtils.map(pedsmales5To14Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
        CohortIndicator pedsFemales5To14indend = CommonIndicatorLibrary.createCohortIndicator("pedsFemales5To14CohortIndicatorEnd", ReportUtils.map(pedsFemales5To14Cohort,"effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}"));
*/



        Map<String, Object> periodMappings = new HashMap<String, Object>();
        periodMappings.put("startDate", "${startDate}");
        periodMappings.put("endDate", "${endDate}");
        periodMappings.put("locationList", "${locationList}");


        CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
        dsd.addParameter(ReportingConstants.START_DATE_PARAMETER);
        dsd.addParameter(ReportingConstants.END_DATE_PARAMETER);
        dsd.addParameter(facility);

        dsd.addColumn("ms14", "Males Below 15", new Mapped<CohortIndicator>(malesZeroTo14ind, periodMappings), "");
        dsd.addColumn("ms15", "Males 15 or more", new Mapped<CohortIndicator>(malesAbove15ind, periodMappings), "");
         /*dsd.addColumn("fs14", "Females Below 15", new Mapped<CohortIndicator>(femalesZeroTo14ind, periodMappings), "");
        dsd.addColumn("fs15", "Females 15 or more", new Mapped<CohortIndicator>(femalesAbove15ind, periodMappings), "");

        dsd.addColumn("mps01", "Male Peds up to one year", new Mapped<CohortIndicator>(pedsMalesZeroTo1ind, periodMappings), "");
        dsd.addColumn("mps24", "Males peds between 2 and 4", new Mapped<CohortIndicator>(pedsmales2To4ind, periodMappings), "");
        dsd.addColumn("mps514", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(pedsmales5To14ind, periodMappings), "");
        dsd.addColumn("fps01", "Female peds at one ", new Mapped<CohortIndicator>(pedsFemalesZeroTo1ind, periodMappings), "");
        dsd.addColumn("fps24", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(pedsFemales2To4ind, periodMappings), "");
        dsd.addColumn("fps514", "Female peds between 5 and 14", new Mapped<CohortIndicator>(pedsFemales5To14ind, periodMappings), "");

        //add columns for transfer out
        dsd.addColumn("mt14", "Males Below 15", new Mapped<CohortIndicator>(malesZeroTo14Tind, periodMappings), "");
        dsd.addColumn("mt15", "Males 15 or more", new Mapped<CohortIndicator>(malesAbove15Tind, periodMappings), "");
        dsd.addColumn("ft14", "Females Below 15", new Mapped<CohortIndicator>(femalesZeroTo14Tind, periodMappings), "");
        dsd.addColumn("ft15", "Females 15 or more", new Mapped<CohortIndicator>(femalesAbove15Tind, periodMappings), "");

        dsd.addColumn("mpt01", "Male Peds up to one year", new Mapped<CohortIndicator>(pedsMalesZeroTo1Tind, periodMappings), "");
        dsd.addColumn("mpt24", "Males peds between 2 and 4", new Mapped<CohortIndicator>(pedsmales2To4Tind, periodMappings), "");
        dsd.addColumn("mpt514", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(pedsmales5To14Tind, periodMappings), "");
        dsd.addColumn("fpt01", "Female peds at one ", new Mapped<CohortIndicator>(pedsFemalesZeroTo1Tind, periodMappings), "");
        dsd.addColumn("fpt24", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(pedsFemales2To4Tind, periodMappings), "");
        dsd.addColumn("fpt514", "Female peds between 5 and 14", new Mapped<CohortIndicator>(pedsFemales5To14Tind, periodMappings), "");

        //Add columns for dead
        dsd.addColumn("md14", "Males Below 15", new Mapped<CohortIndicator>(malesZeroTo14Dind, periodMappings), "");
        dsd.addColumn("md15", "Males 15 or more", new Mapped<CohortIndicator>(malesAbove15Dind, periodMappings), "");
        dsd.addColumn("fd14", "Females Below 15", new Mapped<CohortIndicator>(femalesZeroTo14Dind, periodMappings), "");
        dsd.addColumn("fd15", "Females 15 or more", new Mapped<CohortIndicator>(femalesAbove15Dind, periodMappings), "");

        dsd.addColumn("mpd01", "Male Peds up to one year", new Mapped<CohortIndicator>(pedsMalesZeroTo1Dind, periodMappings), "");
        dsd.addColumn("mpd24", "Males peds between 2 and 4", new Mapped<CohortIndicator>(pedsmales2To4Dind, periodMappings), "");
        dsd.addColumn("mpd514", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(pedsmales5To14Dind, periodMappings), "");
        dsd.addColumn("fpd01", "Female peds at one ", new Mapped<CohortIndicator>(pedsFemalesZeroTo1Dind, periodMappings), "");
        dsd.addColumn("fpd24", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(pedsFemales2To4Dind, periodMappings), "");
        dsd.addColumn("fpd514", "Female peds between 5 and 14", new Mapped<CohortIndicator>(pedsFemales5To14Dind, periodMappings), "");
*/

       /* dsd.addColumn("N14", "Males Below 15", new Mapped<CohortIndicator>(malesZeroTo14indend, periodMappings), "");
        dsd.addColumn("N15", "Males 15 or more", new Mapped<CohortIndicator>(malesAbove15indend, periodMappings), "");
        dsd.addColumn("N16", "Females Below 15", new Mapped<CohortIndicator>(femalesZeroTo14indend, periodMappings), "");
        dsd.addColumn("N17", "Females 15 or more", new Mapped<CohortIndicator>(femalesAbove15indend, periodMappings), "");

        dsd.addColumn("N27", "Male Peds up to one year", new Mapped<CohortIndicator>(pedsMalesZeroTo1indend, periodMappings), "");
        dsd.addColumn("N28", "Males peds between 2 and 4", new Mapped<CohortIndicator>(pedsmales2To4indend, periodMappings), "");
        dsd.addColumn("N29", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(pedsmales5To14indend, periodMappings), "");
        dsd.addColumn("N30", "Female peds at one ", new Mapped<CohortIndicator>(pedsFemalesZeroTo1indend, periodMappings), "");
        dsd.addColumn("N31", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(pedsFemales2To4indend, periodMappings), "");
        dsd.addColumn("N32", "Female peds between 5 and 14", new Mapped<CohortIndicator>(pedsFemales5To14indend, periodMappings), "");

*/


		report.addDataSetDefinition(dsd, periodMappings);

		return report;
	}

	@Override
	public CohortDefinition getCohortDefinition() {
		return new RegimensCohortDefinition();
	}

	@Override
	public ReportDesign getReportDesign() {
		ReportDesign design = new ReportDesign();
		design.setName("ART Care Report Design");
		design.setReportDefinition(this.getReportDefinition());
		design.setRendererType(ExcelTemplateRenderer.class);

		ReportDesignResource resource = new ReportDesignResource();
		resource.setName("template.xls");
		InputStream is = OpenmrsClassLoader.getInstance().getResourceAsStream("templates/RegimensReportTemplate.xls");

		if (is == null)
			throw new APIException("Could not find report template.");

		try {
			resource.setContents(IOUtils.toByteArray(is));
		} catch (IOException ex) {
			throw new APIException("Could not create report design for ART Care Report.", ex);
		}

		IOUtils.closeQuietly(is);
		design.addResource(resource);

		return design;
	}
}