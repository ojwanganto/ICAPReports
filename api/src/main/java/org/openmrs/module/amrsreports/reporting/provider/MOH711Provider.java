package org.openmrs.module.amrsreports.reporting.provider;

import org.apache.commons.io.IOUtils;
import org.openmrs.Location;
import org.openmrs.api.APIException;
import org.openmrs.module.amrsreports.reporting.MOH711IndicatorLibrary;
import org.openmrs.module.amrsreports.reporting.MOH731IndicatorLibrary;
import org.openmrs.module.amrsreports.reporting.cohort.definition.CCCPatientCohortDefinition;
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
public class MOH711Provider extends ReportProvider {

    private MOH711IndicatorLibrary indicatorLibrary = new MOH711IndicatorLibrary();

	public MOH711Provider() {
		this.name = "MOH 711";
		this.visible = true;
	}

	@Override
	public ReportDefinition getReportDefinition() {


        ReportDefinition report = new PeriodIndicatorReportDefinition();
        report.setName("MOH 711");

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

        //Patients below 15 enrollment
        dsd.addColumn("1-01", "Males Below 15 Enrolled through PMTCT", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15EnrolledInCarePMTCT(), periodMappings), "");
        dsd.addColumn("2-01", "Males Below 15 Enrolled through VCT", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15EnrolledInCareVCT(), periodMappings), "");
        dsd.addColumn("3-01", "Males Below 15 Enrolled through TB ", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15EnrolledInCareTB(), periodMappings), "");
        dsd.addColumn("4-01", "Males Below 15 Enrolled through In Patient", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15EnrolledInCareInpatient(), periodMappings), "");
        dsd.addColumn("5-01", "Males Below 15 Enrolled through CWC", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15EnrolledInCarePMTCT(), periodMappings), "");
        dsd.addColumn("6-01", "Males Below 15 Enrolled through Others", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15EnrolledInCareAllOthers(), periodMappings), "");

        dsd.addColumn("1-02", "Females Below 15 Enrolled through PMTCT", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15EnrolledInCarePMTCT(), periodMappings), "");
        dsd.addColumn("2-02", "Females Below 15 Enrolled through VCT", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15EnrolledInCareVCT(), periodMappings), "");
        dsd.addColumn("3-02", "Females Below 15 Enrolled through TB ", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15EnrolledInCareTB(), periodMappings), "");
        dsd.addColumn("4-02", "Females Below 15 Enrolled through In Patient", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15EnrolledInCareInpatient(), periodMappings), "");
        dsd.addColumn("5-02", "Females Below 15 Enrolled through CWC", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15EnrolledInCarePMTCT(), periodMappings), "");
        dsd.addColumn("6-02", "Females Below 15 Enrolled through Others", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15EnrolledInCareAllOthers(), periodMappings), "");

        //patients 15+ enrollment
        dsd.addColumn("1-03", "Males 15+ Enrolled through PMTCT", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveEnrolledInCarePMTCT(), periodMappings), "");
        dsd.addColumn("2-03", "Males 15+ Enrolled through VCT", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveEnrolledInCareVCT(), periodMappings), "");
        dsd.addColumn("3-03", "Males 15+ Enrolled through TB ", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveEnrolledInCareTB(), periodMappings), "");
        dsd.addColumn("4-03", "Males 15+ Enrolled through In Patient", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveEnrolledInCareInpatient(), periodMappings), "");
        dsd.addColumn("5-03", "Males 15+ Enrolled through CWC", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveEnrolledInCarePMTCT(), periodMappings), "");
        dsd.addColumn("6-03", "Males 15+ Enrolled through Others", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveEnrolledInCareAllOthers(), periodMappings), "");

        dsd.addColumn("1-04", "Females 15+ Enrolled through PMTCT", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveEnrolledInCarePMTCT(), periodMappings), "");
        dsd.addColumn("2-04", "Females 15+ Enrolled through VCT", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveEnrolledInCareVCT(), periodMappings), "");
        dsd.addColumn("3-04", "Females 15+ Enrolled through TB ", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveEnrolledInCareTB(), periodMappings), "");
        dsd.addColumn("4-04", "Females 15+ Enrolled through In Patient", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveEnrolledInCareInpatient(), periodMappings), "");
        dsd.addColumn("5-04", "Females 15+ Enrolled through CWC", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveEnrolledInCarePMTCT(), periodMappings), "");
        dsd.addColumn("6-04", "Females 15+ Enrolled through Others", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveEnrolledInCareAllOthers(), periodMappings), "");


        //Patients Enrolled in Care
        dsd.addColumn("7-01", "Males Below 15 Enrolled in HIV Care", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15EnrolledInCare(), periodMappings), "");
        dsd.addColumn("7-02", "Females Below 15 Enrolled in HIV Care", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15EnrolledInCare(), periodMappings), "");
        dsd.addColumn("7-03", "Males 15+ Enrolled in HIV Care", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveEnrolledInCare(), periodMappings), "");
        dsd.addColumn("7-04", "Females 15+ Enrolled in HIV Care", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveEnrolledInCare(), periodMappings), "");

        // WHO STAGE
        dsd.addColumn("8-01", "Males Below 15 Starting ARVs with WHO Stage 1 ", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15StartingARTWHO1(), periodMappings), "");
        dsd.addColumn("9-01", "Males Below 15 Starting ARVs with WHO Stage 2", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15StartingARTWHO2(), periodMappings), "");
        dsd.addColumn("10-01", "Males Below 15 Starting ARVs with WHO Stage 3 ", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15StartingARTWHO3(), periodMappings), "");
        dsd.addColumn("11-01", "Males Below 15 Starting ARVs with WHO Stage 4", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15StartingARTWHO4(), periodMappings), "");

        dsd.addColumn("8-02", "Females Below 15 Starting ARVs with WHO Stage 1 ", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15StartingARTWHO1(), periodMappings), "");
        dsd.addColumn("9-02", "Females Below 15 Starting ARVs with WHO Stage 2", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15StartingARTWHO2(), periodMappings), "");
        dsd.addColumn("10-02", "Females Below 15 Starting ARVs with WHO Stage 3 ", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15StartingARTWHO3(), periodMappings), "");
        dsd.addColumn("11-02", "Females Below 15 Starting ARVs with WHO Stage 4", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15StartingARTWHO4(), periodMappings), "");

        dsd.addColumn("8-03", "Males 15+ Starting ARVs with WHO Stage 1 ", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveStartingARTWHO1(), periodMappings), "");
        dsd.addColumn("9-03", "Males 15+ Starting ARVs with WHO Stage 2", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveStartingARTWHO2(), periodMappings), "");
        dsd.addColumn("10-03", "Males 15+ Starting ARVs with WHO Stage 3 ", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveStartingARTWHO3(), periodMappings), "");
        dsd.addColumn("11-03", "Males 15+ Starting ARVs with WHO Stage 4", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveStartingARTWHO4(), periodMappings), "");

        dsd.addColumn("8-04", "Females 15+ Starting ARVs with WHO Stage 1 ", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveStartingARTWHO1(), periodMappings), "");
        dsd.addColumn("9-04", "Females 15+ Starting ARVs with WHO Stage 2", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveStartingARTWHO2(), periodMappings), "");
        dsd.addColumn("10-04", "Females 15+ Starting ARVs with WHO Stage 3 ", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveStartingARTWHO3(), periodMappings), "");
        dsd.addColumn("11-04", "Females 15+ Starting ARVs with WHO Stage 4", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveStartingARTWHO4(), periodMappings), "");

        //Cumulative started on ARVs
        dsd.addColumn("12-01", "Males Below 15 Enrolled in HIV Care", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15EverOnCare(), periodMappings), "");
        dsd.addColumn("12-02", "Females Below 15 Enrolled in HIV Care", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15EverOnCare(), periodMappings), "");
        dsd.addColumn("12-03", "Males 15+ Enrolled in HIV Care", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveEverOnCare(), periodMappings), "");
        dsd.addColumn("12-04", "Females 15+ Enrolled in HIV Care", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveEverOnCare(), periodMappings), "");

        //Currently on ARVs
        dsd.addColumn("13-02", "Females Below 15 On ARVs and pregnant", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("13-04", "Females 15+ On ARVs and pregnant", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");

        dsd.addColumn("14-01", "Males Below 15 On ARVs", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15CurrentlyOnART(), periodMappings), "");
        dsd.addColumn("14-02", "Females Below 15 On ARVs", new Mapped<CohortIndicator>(indicatorLibrary.femalesBelow15CurrentlyOnART(), periodMappings), "");
        dsd.addColumn("14-03", "Males 15+ On ARVs", new Mapped<CohortIndicator>(indicatorLibrary.males15AndAboveCurrentlyOnART(), periodMappings), "");
        dsd.addColumn("14-04", "Females 15+ On ARVs", new Mapped<CohortIndicator>(indicatorLibrary.females15AndAboveCurrentlyOnART(), periodMappings), "");

        //Eligible and not on ARVs
        dsd.addColumn("15-01", "Males Below 15 Eligible and not On ARVs", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("15-02", "Females Below 15 Eligible and not  On ARVs", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("15-03", "Males 15+ Eligible and not  On ARVs", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("15-04", "Females 15+ Eligible and not On ARVs", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");

        //Post Exposure prophylaxis
        dsd.addColumn("16-01", "Males Below 15 Sexual Assault PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("16-02", "Females Below 15 Sexual Assault PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("16-03", "Males 15+ Sexual Assault PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("16-04", "Females 15+ Sexual Assault PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");

        dsd.addColumn("17-01", "Males Below 15 Occupational PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("17-02", "Females Below 15 Occupational PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("17-03", "Males 15+ Occupational PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("17-04", "Females 15+ Occupational PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");

        dsd.addColumn("18-01", "Males Below 15 All Other PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("18-02", "Females Below 15 All Other PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("18-03", "Males 15+ All Other PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");
        dsd.addColumn("18-04", "Females 15+ All Other PEP", new Mapped<CohortIndicator>(indicatorLibrary.malesBelow15OnCotrimoxazole(), periodMappings), "");


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
		InputStream is = OpenmrsClassLoader.getInstance().getResourceAsStream("templates/moh711.xls");

		if (is == null)
			throw new APIException("Could not find report template.");

		try {
			resource.setContents(IOUtils.toByteArray(is));
		} catch (IOException ex) {
			throw new APIException("Could not create report design for MOH 711 Report.", ex);
		}

		IOUtils.closeQuietly(is);
		design.addResource(resource);

		return design;
	}
}