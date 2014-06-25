package org.openmrs.module.amrsreports.reporting.provider;

import org.apache.commons.io.IOUtils;
import org.openmrs.Location;
import org.openmrs.api.APIException;
import org.openmrs.module.amrsreports.reporting.MOH731CohortLibrary;
import org.openmrs.module.amrsreports.reporting.MOH731IndicatorLibrary;
import org.openmrs.module.amrsreports.reporting.cohort.definition.CCCPatientCohortDefinition;
import org.openmrs.module.amrsreports.reporting.cohort.definition.CurrentlyInCareCohortDefinition;
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
public class MOH731Provider extends ReportProvider {

    private MOH731IndicatorLibrary indicatorLibrary = new MOH731IndicatorLibrary();
    private MOH731CohortLibrary cohortLibrary = new MOH731CohortLibrary();

	public MOH731Provider() {
		this.name = "MOH 731";
		this.visible = true;
	}

	@Override
	public ReportDefinition getReportDefinition() {


        ReportDefinition report = new PeriodIndicatorReportDefinition();
        report.setName("MOH 731");

        // set up parameters
        Parameter facility = new Parameter();
        facility.setName("locationList");
        facility.setType(Location.class);

        //define general cohorts


        Map<String, Object> periodMappings = new HashMap<String, Object>();
        periodMappings.put("startDate", "${startDate}");
        periodMappings.put("endDate", "${endDate}");
        periodMappings.put("locationList", "${locationList}");


        CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
        dsd.addParameter(ReportingConstants.START_DATE_PARAMETER);
        dsd.addParameter(ReportingConstants.END_DATE_PARAMETER);
        dsd.addParameter(facility);

        //dsd.addColumn("HV03-01", "Hiv Exposed Infant (Within 2 months)", new Mapped<CohortIndicator>(indicatorLibrary.infantsExposedWithin2Months(), periodMappings), "");
        //dsd.addColumn("HV03-02", "Hiv Exposed Infant (Eligible for ctx at 2 months)", new Mapped<CohortIndicator>(indicatorLibrary.infantsExposedAt2Months(), periodMappings), "");
        dsd.addColumn("HV03-03", "Males On CTX  Below 15", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("HV03-05", "Males On CTX 15 years & older", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveOnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("HV03-04", "Females On CTX  Below 15", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("HV03-06", "Females On CTX 15 years & older", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveOnCotrimoxazole(), periodMappings), "");

        dsd.addColumn("HV03-08", "Enrolled in Care: < 1 yr", new Mapped<CohortIndicator>(indicatorLibrary.infantsEnrolledInCare(), periodMappings), "");
        dsd.addColumn("HV03-09", "Males Enrolled in Care: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15EnrolledInCare(), periodMappings), "");
        dsd.addColumn("HV03-11", "Males Enrolled in Care: >= 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveEnrolledInCare(), periodMappings), "");
        dsd.addColumn("HV03-10", "Females Enrolled in Care: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15EnrolledInCare(), periodMappings), "");
        dsd.addColumn("HV03-12", "Females Enrolled in Care: >= 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveEnrolledInCare(), periodMappings), "");

        dsd.addColumn("HV03-14", "Currently in Care: < 1 yr", new Mapped<CohortIndicator>(indicatorLibrary.cohortIndicatorCount("infants in care",cohortLibrary.infantsCurrentlyInCare()), periodMappings), "");
        dsd.addColumn("HV03-15", "Males Currently in Care: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.cohortIndicatorCount("Males Below 15 in care",cohortLibrary.malesAgedAtMostXCurrentlyInCare(14)), periodMappings), "");
        dsd.addColumn("HV03-17", "Males Currently in Care: >= 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.cohortIndicatorCount("Males 15 and older in care",cohortLibrary.malesAgedAtLeastXCurrentlyInCare(15)), periodMappings), "");
        dsd.addColumn("HV03-16", "Females Currently in Care: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.cohortIndicatorCount("Females below 15 in care",cohortLibrary.femalesAgedAtMostXCurrentlyInCare(14)), periodMappings), "");
        dsd.addColumn("HV03-18", "Females Currently in Care: >= 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.cohortIndicatorCount("Females 15 and above in care",cohortLibrary.femalesAgedAtLeastXCurrentlyInCare(15)), periodMappings), "");

        dsd.addColumn("HV03-20", "Starting ART: < 1 yr", new Mapped<CohortIndicator>(indicatorLibrary.infantsStartingART(), periodMappings), "");
        dsd.addColumn("HV03-21", "Males Starting ART: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15StartingART(), periodMappings), "");
        dsd.addColumn("HV03-23", "Males Starting ART: >= 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveStartingART(), periodMappings), "");
        dsd.addColumn("HV03-22", "Females Starting ART: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15StartingART(), periodMappings), "");
        dsd.addColumn("HV03-24", "Females Starting ART: >= 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveStartingART(), periodMappings), "");
/*
        dsd.addColumn("HV03-26", "Starting ART-Pregnant: < 15 yrs", new Mapped<CohortIndicator>(pedsmales2To4ind, periodMappings), "");
        dsd.addColumn("HV03-27", "Starting ART-TB Patient: >= 15 yrs old", new Mapped<CohortIndicator>(pedsmales5To14ind, periodMappings), "");
*/
        dsd.addColumn("HV03-28", "Revisits on ART: < 1 yr", new Mapped<CohortIndicator>(indicatorLibrary.infantsWithARTRevisits(), periodMappings), "");
        dsd.addColumn("HV03-29", "Male Revisits on ART: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15WithARTRevisits(), periodMappings), "");
        dsd.addColumn("HV03-31", "Male Revisits on ART: >= 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveWithARTRevisits(), periodMappings), "");
        dsd.addColumn("HV03-30", "Female Revisits on ART: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15WithARTRevisits(), periodMappings), "");
        dsd.addColumn("HV03-32", "Female Revisits on ART: >= 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveWithARTRevisits(), periodMappings), "");

        //Currently on ART
        dsd.addColumn("HV03-34", "Currently on ART: < 1 yr", new Mapped<CohortIndicator>(indicatorLibrary.cohortIndicatorCount("infants Currently on ART", cohortLibrary.infantsCurrentlyOnART()), periodMappings), "");
        dsd.addColumn("HV03-35", "Males Currently on ART: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.cohortIndicatorCount("Males Below 15 on ART", cohortLibrary.malesAgedAtMostXCurrentlyOnART(14)), periodMappings), "");
        dsd.addColumn("HV03-37", "Males Currently on ART: >= 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.cohortIndicatorCount("Males Above 15 on ART", cohortLibrary.malesAgedAtLeastXCurrentlyOnART(15)), periodMappings), "");
        dsd.addColumn("HV03-36", "Females Currently on ART: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.cohortIndicatorCount("Females Below 15 on ART",cohortLibrary.femalesAgedAtMostXCurrentlyOnART(14)), periodMappings), "");
       dsd.addColumn("HV03-38", "Females Currently on ART: >= 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.cohortIndicatorCount("Females Above 15 on ART ", cohortLibrary.malesAgedAtLeastXCurrentlyOnART(15)), periodMappings), "");
       /* */ //Cumulative Ever on ART
        dsd.addColumn("HV03-40", "Males Ever on ART: < 1 yr", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15EverOnCare(), periodMappings), "");
        dsd.addColumn("HV03-42", "Males Ever on ART: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveEverOnCare(), periodMappings), "");
        dsd.addColumn("HV03-41", "Females Ever on ART: < 1 yr", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15EverOnCare(), periodMappings), "");
        dsd.addColumn("HV03-43", "Females Ever on ART: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveEverOnCare(), periodMappings), "");


        //Survival and Retention on ART at 12 months
        dsd.addColumn("HV03-45", "ART Net Cohort at 12 months ", new Mapped<CohortIndicator>(indicatorLibrary.patientsOnOriginalFirstLine(), periodMappings), "");
        dsd.addColumn("HV03-46", "On Original 1st Line at 12 months ", new Mapped<CohortIndicator>(indicatorLibrary.patientsOnOriginalFirstLine(), periodMappings), "");
        dsd.addColumn("HV03-47", "On alternative 1st Line at 12 months ", new Mapped<CohortIndicator>(indicatorLibrary.patientsOnAlternativeFirstLine(), periodMappings), "");
        dsd.addColumn("HV03-48", "On 2nd Line (or higher) at 12 months ", new Mapped<CohortIndicator>(indicatorLibrary.patientsOnSecondLineAndHigherRegimen(), periodMappings), "");


        //Screening
        dsd.addColumn("HV03-50", "Males Screened for TB: < 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15ScreenedForTB(), periodMappings), "");
        dsd.addColumn("HV03-52", "Males Screened for TB: >= 15 yrs", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveScreenedForTB(), periodMappings), "");
        dsd.addColumn("HV03-51", "Females Screened for TB: < 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15ScreenedForTB(), periodMappings), "");
        dsd.addColumn("HV03-53", "Females Screened for TB: >= 15 yrs old", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveScreenedForTB(), periodMappings), "");

       /* //prevention with positives
        dsd.addColumn("HV09-04", "Modern contraceptive methods", new Mapped<CohortIndicator>(pedsMalesZeroTo1ind, periodMappings), "");
        dsd.addColumn("HV09-05", "Provided with condoms", new Mapped<CohortIndicator>(pedsmales2To4ind, periodMappings), "");

        //HIV care visits
        dsd.addColumn("HV03-70", "Females: >= 18 yrs old", new Mapped<CohortIndicator>(pedsmales5To14ind, periodMappings), "");
        dsd.addColumn("HV03-71", "Scheduled", new Mapped<CohortIndicator>(pedsMalesZeroTo1ind, periodMappings), "");
        dsd.addColumn("HV03-72", "Unscheduled", new Mapped<CohortIndicator>(pedsmales2To4ind, periodMappings), "");
*/

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
		design.setName("ART Care Report Design");
		design.setReportDefinition(this.getReportDefinition());
		design.setRendererType(ExcelTemplateRenderer.class);

		ReportDesignResource resource = new ReportDesignResource();
		resource.setName("template.xls");
		InputStream is = OpenmrsClassLoader.getInstance().getResourceAsStream("templates/moh731.xls");

		if (is == null)
			throw new APIException("Could not find report template.");

		try {
			resource.setContents(IOUtils.toByteArray(is));
		} catch (IOException ex) {
			throw new APIException("Could not create report design for MOH 731 Report.", ex);
		}

		IOUtils.closeQuietly(is);
		design.addResource(resource);

		return design;
	}
}