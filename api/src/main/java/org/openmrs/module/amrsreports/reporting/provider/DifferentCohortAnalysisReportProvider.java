package org.openmrs.module.amrsreports.reporting.provider;

import org.apache.commons.io.IOUtils;
import org.openmrs.Location;
import org.openmrs.api.APIException;
import org.openmrs.module.amrsreports.reporting.CommonIndicatorLibrary;
import org.openmrs.module.amrsreports.reporting.ReportUtils;
import org.openmrs.module.amrsreports.reporting.cohort.definition.CCCPatientCohortDefinition;
import org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.BaseSQLCohortLibrary;
import org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.cohortAnalysis.CohortAnalysisSQLCohortLibrary;
import org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.palliativeCare.PalliativeCareSQLCohortLibrary;
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
public class DifferentCohortAnalysisReportProvider extends ReportProvider {

    private BaseSQLCohortLibrary baseSQLCohortLibrary = new BaseSQLCohortLibrary();
    private CohortAnalysisSQLCohortLibrary sqlQueries = new CohortAnalysisSQLCohortLibrary();

	public DifferentCohortAnalysisReportProvider() {
		this.name = "Different Cohorts Analysis";
		this.visible = true;
	}

	@Override
	public ReportDefinition getReportDefinition() {

		ReportDefinition report = new PeriodIndicatorReportDefinition();
		report.setName("Different Cohorts Analysis");

        // set up parameters
        Parameter facility = new Parameter();
        facility.setName("locationList");
        facility.setType(Location.class);

        /*Define cohorts 12 months from reporting period*/
        CohortDefinition originalCohortAtStart = baseSQLCohortLibrary.createCohortDefinition("Original Cohort At Start",sqlQueries.patientsEnrolledInCare(12));
        CohortDefinition transferInAtStart = baseSQLCohortLibrary.createCohortDefinition("TI Cohort At Start",sqlQueries.patientsWhoTransferredIN(12));
        CohortDefinition transferOutAtStart = baseSQLCohortLibrary.createCohortDefinition("TO Cohort At Start",sqlQueries.patientsWhoTransferredOut(12));
        CohortDefinition originalFirstLineAtStart = baseSQLCohortLibrary.createCohortDefinition("Original First line Cohort At Start",sqlQueries.patientsOnOriginalFirstLineRegimen(12));
        CohortDefinition alternativeFirstLineAtStart = baseSQLCohortLibrary.createCohortDefinition("Alternative First Line Cohort At Start",sqlQueries.patientsOnAlternativeFirstLineRegimen(12));
        CohortDefinition secondLineAtStart =baseSQLCohortLibrary.createCohortDefinition("Second Line Cohort At Start",sqlQueries.patientsOnSecondLineOrHigher(12));
        CohortDefinition stoppedAtStart = baseSQLCohortLibrary.createCohortDefinition("Stopped Cohort At Start",sqlQueries.patientsWhoStoppedArt(12));
        CohortDefinition lostAtStart = baseSQLCohortLibrary.createCohortDefinition("Lost Cohort At Start",sqlQueries.patientsLostToFollowUP(12));
        CohortDefinition diedAtStart = baseSQLCohortLibrary.createCohortDefinition("Dead Cohort At Start",sqlQueries.patientsWhoDied(12));
        CohortDefinition aliveOnARTAtStart = baseSQLCohortLibrary.createCohortDefinition("Currently on ART Cohort At Start",sqlQueries.patientsCurrentlyOnART(12));

        CohortIndicator originalCohortAtStartInd = CommonIndicatorLibrary.createCohortIndicator("originalCohortAtStartInd",ReportUtils.map(originalCohortAtStart,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator transferInAtStartInd = CommonIndicatorLibrary.createCohortIndicator("transferInAtStartInd", ReportUtils.map(transferInAtStart,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator transferOutAtStartInd = CommonIndicatorLibrary.createCohortIndicator("transferOutAtStartInd", ReportUtils.map(transferOutAtStart,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator originalFirstLineAtStartInd = CommonIndicatorLibrary.createCohortIndicator("originalFirstLineAtStartInd", ReportUtils.map(originalFirstLineAtStart,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator alternativeFirstLineAtStartInd = CommonIndicatorLibrary.createCohortIndicator("alternativeFirstLineAtStartInd",ReportUtils.map(alternativeFirstLineAtStart,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator secondLineAtStartInd = CommonIndicatorLibrary.createCohortIndicator("secondLineAtStartInd", ReportUtils.map(secondLineAtStart,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator stoppedAtStartInd = CommonIndicatorLibrary.createCohortIndicator("stoppedAtStartInd", ReportUtils.map(stoppedAtStart,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator lostAtStartInd = CommonIndicatorLibrary.createCohortIndicator("lostAtStartInd", ReportUtils.map(lostAtStart,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator diedAtStartInd = CommonIndicatorLibrary.createCohortIndicator("diedAtStartInd", ReportUtils.map(diedAtStart,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator aliveOnARTAtStartInd = CommonIndicatorLibrary.createCohortIndicator("aliveOnARTAtStartInd", ReportUtils.map(aliveOnARTAtStart,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));


        /*Define cohorts 24 months from reporting period*/
        CohortDefinition originalCohortAt12 = baseSQLCohortLibrary.createCohortDefinition("Original Cohort At 12",sqlQueries.patientsEnrolledInCare(24));
        CohortDefinition transferInAt12 = baseSQLCohortLibrary.createCohortDefinition("TI Cohort At 12",sqlQueries.patientsWhoTransferredIN(24));
        CohortDefinition transferOutAt12 = baseSQLCohortLibrary.createCohortDefinition("TO Cohort At 12",sqlQueries.patientsWhoTransferredOut(24));
        CohortDefinition originalFirstLineAt12 = baseSQLCohortLibrary.createCohortDefinition("Original First line Cohort At 12",sqlQueries.patientsOnOriginalFirstLineRegimen(24));
        CohortDefinition alternativeFirstLineAt12 = baseSQLCohortLibrary.createCohortDefinition("Alternative First Line Cohort At 12",sqlQueries.patientsOnAlternativeFirstLineRegimen(24));
        CohortDefinition secondLineAt12 =baseSQLCohortLibrary.createCohortDefinition("Second Line Cohort At 12",sqlQueries.patientsOnSecondLineOrHigher(24));
        CohortDefinition stoppedAt12 = baseSQLCohortLibrary.createCohortDefinition("Stopped Cohort At 12",sqlQueries.patientsWhoStoppedArt(24));
        CohortDefinition lostAt12 = baseSQLCohortLibrary.createCohortDefinition("Lost Cohort At 12",sqlQueries.patientsLostToFollowUP(24));
        CohortDefinition diedAt12 = baseSQLCohortLibrary.createCohortDefinition("Dead Cohort At 12",sqlQueries.patientsWhoDied(24));
        CohortDefinition aliveOnARTAt12 = baseSQLCohortLibrary.createCohortDefinition("Currently on ART Cohort At 12",sqlQueries.patientsCurrentlyOnART(24));

        CohortIndicator originalCohortAt12Ind = CommonIndicatorLibrary.createCohortIndicator("originalCohortAt12Ind",ReportUtils.map(originalCohortAt12,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator transferInAt12Ind = CommonIndicatorLibrary.createCohortIndicator("transferInAt12Ind", ReportUtils.map(transferInAt12,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator transferOutAt12Ind = CommonIndicatorLibrary.createCohortIndicator("transferOutAt12Ind", ReportUtils.map(transferOutAt12,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator originalFirstLineAt12Ind = CommonIndicatorLibrary.createCohortIndicator("originalFirstLineAt12Ind", ReportUtils.map(originalFirstLineAt12,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator alternativeFirstLineAt12Ind = CommonIndicatorLibrary.createCohortIndicator("alternativeFirstLineAt12Ind",ReportUtils.map(alternativeFirstLineAt12,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator secondLineAt12Ind = CommonIndicatorLibrary.createCohortIndicator("secondLineAt12Ind", ReportUtils.map(secondLineAt12,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator stoppedAt12Ind = CommonIndicatorLibrary.createCohortIndicator("stoppedAt12Ind", ReportUtils.map(stoppedAt12,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator lostAt12Ind = CommonIndicatorLibrary.createCohortIndicator("lostAt12Ind", ReportUtils.map(lostAt12,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator diedAt12Ind = CommonIndicatorLibrary.createCohortIndicator("diedAt12Ind", ReportUtils.map(diedAt12,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator aliveOnARTAt12Ind = CommonIndicatorLibrary.createCohortIndicator("aliveOnARTAt12Ind", ReportUtils.map(aliveOnARTAt12,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));


         /*Define cohorts 36 months from reporting period*/
        CohortDefinition originalCohortAt36 = baseSQLCohortLibrary.createCohortDefinition("Original Cohort At 36",sqlQueries.patientsEnrolledInCare(36));
        CohortDefinition transferInAt36 = baseSQLCohortLibrary.createCohortDefinition("TI Cohort At 36",sqlQueries.patientsWhoTransferredIN(36));
        CohortDefinition transferOutAt36 = baseSQLCohortLibrary.createCohortDefinition("TO Cohort At 36",sqlQueries.patientsWhoTransferredOut(36));
        CohortDefinition originalFirstLineAt36 = baseSQLCohortLibrary.createCohortDefinition("Original First line Cohort At 36",sqlQueries.patientsOnOriginalFirstLineRegimen(36));
        CohortDefinition alternativeFirstLineAt36 = baseSQLCohortLibrary.createCohortDefinition("Alternative First Line Cohort At 36",sqlQueries.patientsOnAlternativeFirstLineRegimen(36));
        CohortDefinition secondLineAt36 =baseSQLCohortLibrary.createCohortDefinition("Second Line Cohort At 36",sqlQueries.patientsOnSecondLineOrHigher(36));
        CohortDefinition stoppedAt36 = baseSQLCohortLibrary.createCohortDefinition("Stopped Cohort At 36",sqlQueries.patientsWhoStoppedArt(36));
        CohortDefinition lostAt36 = baseSQLCohortLibrary.createCohortDefinition("Lost Cohort At 36",sqlQueries.patientsLostToFollowUP(36));
        CohortDefinition diedAt36 = baseSQLCohortLibrary.createCohortDefinition("Dead Cohort At 36",sqlQueries.patientsWhoDied(36));
        CohortDefinition aliveOnARTAt36 = baseSQLCohortLibrary.createCohortDefinition("Currently on ART Cohort At 36",sqlQueries.patientsCurrentlyOnART(36));

        CohortIndicator originalCohortAt36Ind = CommonIndicatorLibrary.createCohortIndicator("originalCohortAt36Ind",ReportUtils.map(originalCohortAt36,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator transferInAt36Ind = CommonIndicatorLibrary.createCohortIndicator("transferInAt36Ind", ReportUtils.map(transferInAt36,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator transferOutAt36Ind = CommonIndicatorLibrary.createCohortIndicator("transferOutAt36Ind", ReportUtils.map(transferOutAt36,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator originalFirstLineAt36Ind = CommonIndicatorLibrary.createCohortIndicator("originalFirstLineAt36Ind", ReportUtils.map(originalFirstLineAt36,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator alternativeFirstLineAt36Ind = CommonIndicatorLibrary.createCohortIndicator("alternativeFirstLineAt36Ind",ReportUtils.map(alternativeFirstLineAt36,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator secondLineAt36Ind = CommonIndicatorLibrary.createCohortIndicator("secondLineAt36Ind", ReportUtils.map(secondLineAt36,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator stoppedAt36Ind = CommonIndicatorLibrary.createCohortIndicator("stoppedAt36Ind", ReportUtils.map(stoppedAt36,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator lostAt36Ind = CommonIndicatorLibrary.createCohortIndicator("lostAt36Ind", ReportUtils.map(lostAt36,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator diedAt36Ind = CommonIndicatorLibrary.createCohortIndicator("diedAt36Ind", ReportUtils.map(diedAt36,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator aliveOnARTAt36Ind = CommonIndicatorLibrary.createCohortIndicator("aliveOnARTAt36Ind", ReportUtils.map(aliveOnARTAt36,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));


        Map<String, Object> periodMappings = new HashMap<String, Object>();
        periodMappings.put("startDate", "${startDate}");
        periodMappings.put("endDate", "${endDate}");
        periodMappings.put("locationList", "${locationList}");


        CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
        dsd.addParameter(ReportingConstants.START_DATE_PARAMETER);
        dsd.addParameter(ReportingConstants.END_DATE_PARAMETER);
        dsd.addParameter(facility);


        dsd.addColumn("originalCohort12", "Original Cohort", new Mapped<CohortIndicator>(originalCohortAtStartInd, periodMappings), "");
        dsd.addColumn("transferIn12", "Patients who transferred In", new Mapped<CohortIndicator>(transferInAtStartInd, periodMappings), "");
        dsd.addColumn("transferOut12", "Patients who transferred out", new Mapped<CohortIndicator>(transferOutAtStartInd, periodMappings), "");
        dsd.addColumn("firstLineRegimen12", "Patients on original first line regimen", new Mapped<CohortIndicator>(originalFirstLineAtStartInd, periodMappings), "");
        dsd.addColumn("alternativeFirstLineRegimen12", "Patients on alternative first line regimen", new Mapped<CohortIndicator>(alternativeFirstLineAtStartInd, periodMappings), "");
        dsd.addColumn("secondLineRegimen12", "Patients on second line regimen or higher", new Mapped<CohortIndicator>(secondLineAtStartInd, periodMappings), "");
        dsd.addColumn("stopped12", "Patients who stopped ART", new Mapped<CohortIndicator>(stoppedAtStartInd, periodMappings), "");
        dsd.addColumn("lost12", "Lost patients", new Mapped<CohortIndicator>(lostAtStartInd, periodMappings), "");
        dsd.addColumn("died12", "Dead patients", new Mapped<CohortIndicator>(diedAtStartInd, periodMappings), "");
        dsd.addColumn("currentlyOnART12", "Patients who are alive and on ART", new Mapped<CohortIndicator>(aliveOnARTAtStartInd, periodMappings), "");


        dsd.addColumn("originalCohort24", "Original Cohort", new Mapped<CohortIndicator>(originalCohortAt12Ind, periodMappings), "");
        dsd.addColumn("transferIn24", "Patients who transferred In", new Mapped<CohortIndicator>(transferInAt12Ind, periodMappings), "");
        dsd.addColumn("transferOut24", "Patients who transferred out", new Mapped<CohortIndicator>(transferOutAt12Ind, periodMappings), "");
        dsd.addColumn("firstLineRegimen24", "Patients on original first line regimen", new Mapped<CohortIndicator>(originalFirstLineAt12Ind, periodMappings), "");
        dsd.addColumn("alternativeFirstLineRegimen24", "Patients on alternative first line regimen", new Mapped<CohortIndicator>(alternativeFirstLineAt12Ind, periodMappings), "");
        dsd.addColumn("secondLineRegimen24", "Patients on second line regimen or higher", new Mapped<CohortIndicator>(secondLineAt12Ind, periodMappings), "");
        dsd.addColumn("stopped24", "Patients who stopped ART", new Mapped<CohortIndicator>(stoppedAt12Ind, periodMappings), "");
        dsd.addColumn("lost24", "Lost patients", new Mapped<CohortIndicator>(lostAt12Ind, periodMappings), "");
        dsd.addColumn("died24", "Dead patients", new Mapped<CohortIndicator>(diedAt12Ind, periodMappings), "");
        dsd.addColumn("currentlyOnART24", "Patients who are alive and on ART", new Mapped<CohortIndicator>(aliveOnARTAt12Ind, periodMappings), "");


        dsd.addColumn("originalCohort36", "Original Cohort", new Mapped<CohortIndicator>(originalCohortAt36Ind, periodMappings), "");
        dsd.addColumn("transferIn36", "Patients who transferred In", new Mapped<CohortIndicator>(transferInAt36Ind, periodMappings), "");
        dsd.addColumn("transferOut36", "Patients who transferred out", new Mapped<CohortIndicator>(transferOutAt36Ind, periodMappings), "");
        dsd.addColumn("firstLineRegimen36", "Patients on original first line regimen", new Mapped<CohortIndicator>(originalFirstLineAt36Ind, periodMappings), "");
        dsd.addColumn("alternativeFirstLineRegimen36", "Patients on alternative first line regimen", new Mapped<CohortIndicator>(alternativeFirstLineAt36Ind, periodMappings), "");
        dsd.addColumn("secondLineRegimen36", "Patients on second line regimen or higher", new Mapped<CohortIndicator>(secondLineAt36Ind, periodMappings), "");
        dsd.addColumn("stopped36", "Patients who stopped ART", new Mapped<CohortIndicator>(stoppedAt36Ind, periodMappings), "");
        dsd.addColumn("lost36", "Lost patients", new Mapped<CohortIndicator>(lostAt36Ind, periodMappings), "");
        dsd.addColumn("died36", "Dead patients", new Mapped<CohortIndicator>(diedAt36Ind, periodMappings), "");
        dsd.addColumn("currentlyOnART36", "Patients who are alive and on ART", new Mapped<CohortIndicator>(aliveOnARTAt36Ind, periodMappings), "");

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
		design.setName("Different Cohorts Analysis Design");
		design.setReportDefinition(this.getReportDefinition());
		design.setRendererType(ExcelTemplateRenderer.class);

		ReportDesignResource resource = new ReportDesignResource();
		resource.setName("template.xls");
		InputStream is = OpenmrsClassLoader.getInstance().getResourceAsStream("templates/DifferentCohortAnalysisReportTemplate.xls");

		if (is == null)
			throw new APIException("Could not find report template.");

		try {
			resource.setContents(IOUtils.toByteArray(is));
		} catch (IOException ex) {
			throw new APIException("Could not create report design for Different Cohort Analysis Report Template.", ex);
		}

		IOUtils.closeQuietly(is);
		design.addResource(resource);

		return design;
	}


}