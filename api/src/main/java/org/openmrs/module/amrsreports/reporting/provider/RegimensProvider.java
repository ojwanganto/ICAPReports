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
import org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.BaseSQLCohortLibrary;
import org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.artRegimens.RegimensSQLCohortLibrary;
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
 * Provides mechanisms for rendering the Regimen Report
 */
public class RegimensProvider extends ReportProvider {

    private RegimensCohortLibrary baseCohorts = new RegimensCohortLibrary();


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

        CohortIndicator  childrenOn10ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn10ACohortInd", ReportUtils.map(baseCohorts.childrenOn10ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn10BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn10BCohortInd", ReportUtils.map(baseCohorts.childrenOn10BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn11BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn11BCohortInd", ReportUtils.map(baseCohorts.childrenOn11BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn1BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn1BCohortInd", ReportUtils.map(baseCohorts.childrenOn1BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn2BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn2BCohortInd", ReportUtils.map(baseCohorts.childrenOn2BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn4ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn4ACohortInd", ReportUtils.map(baseCohorts.childrenOn4ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn4BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn4BCohortInd", ReportUtils.map(baseCohorts.childrenOn4BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn5ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn5ACohortInd", ReportUtils.map(baseCohorts.childrenOn5ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn5BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn5BCohortInd", ReportUtils.map(baseCohorts.childrenOn5BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn6ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn6ACohortInd", ReportUtils.map(baseCohorts.childrenOn6ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn6BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn6BCohortInd", ReportUtils.map(baseCohorts.childrenOn6BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn7ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn7ACohortInd", ReportUtils.map(baseCohorts.childrenOn7ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn7BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOn7BCohortInd", ReportUtils.map(baseCohorts.childrenOn7BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOn9CohortInd =  CommonIndicatorLibrary.createCohortIndicator("childrenOn9CohortInd", ReportUtils.map(baseCohorts.childrenOn9Cohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAF1ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAF1ACohortInd", ReportUtils.map(baseCohorts.childrenOnAF1ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAF1BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAF1BCohortInd", ReportUtils.map(baseCohorts.childrenOnAF1BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAF1CCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAF1CCohortInd", ReportUtils.map(baseCohorts.childrenOnAF1CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAF2ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAF2ACohortInd", ReportUtils.map(baseCohorts.childrenOnAF2ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAF2BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAF2BCohortInd", ReportUtils.map(baseCohorts.childrenOnAF2BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAF2CCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAF2CCohortInd", ReportUtils.map(baseCohorts.childrenOnAF2CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAF3ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAF3ACohortInd", ReportUtils.map(baseCohorts.childrenOnAF3ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAF3BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAF3BCohortInd", ReportUtils.map(baseCohorts.childrenOnAF3BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAF3CCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAF3CCohortInd", ReportUtils.map(baseCohorts.childrenOnAF3CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAS1ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAS1ACohortInd", ReportUtils.map(baseCohorts.childrenOnAS1ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAS1BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAS1BCohortInd", ReportUtils.map(baseCohorts.childrenOnAS1BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAS2ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAS2ACohortInd", ReportUtils.map(baseCohorts.childrenOnAS2ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAS2BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAS2BCohortInd", ReportUtils.map(baseCohorts.childrenOnAS2BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAS2CCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAS2CCohortInd", ReportUtils.map(baseCohorts.childrenOnAS2CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAS3ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAS3ACohortInd", ReportUtils.map(baseCohorts.childrenOnAS3ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnAS4ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnAS4ACohortInd", ReportUtils.map(baseCohorts.childrenOnAS4ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnB3BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnB3BCohortInd", ReportUtils.map(baseCohorts.childrenOnB3BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnB3CCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnB3CCohortInd", ReportUtils.map(baseCohorts.childrenOnB3CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnB3LCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnB3LCohortInd", ReportUtils.map(baseCohorts.childrenOnB3LCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC1BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC1BCohortInd", ReportUtils.map(baseCohorts.childrenOnC1BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC1CCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC1CCohortInd", ReportUtils.map(baseCohorts.childrenOnC1CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC1DCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC1DCohortInd", ReportUtils.map(baseCohorts.childrenOnC1DCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC2CCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC2CCohortInd", ReportUtils.map(baseCohorts.childrenOnC2CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC2DCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC2DCohortInd", ReportUtils.map(baseCohorts.childrenOnC2DCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC3CCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC3CCohortInd", ReportUtils.map(baseCohorts.childrenOnC3CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC3DCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC3DCohortInd", ReportUtils.map(baseCohorts.childrenOnC3DCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC4BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC4BCohortInd", ReportUtils.map(baseCohorts.childrenOnC4BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC4CCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC4CCohortInd", ReportUtils.map(baseCohorts.childrenOnC4CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC4DCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC4DCohortInd", ReportUtils.map(baseCohorts.childrenOnC4DCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC5ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC5ACohortInd", ReportUtils.map(baseCohorts.childrenOnC5ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC5BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC5BCohortInd", ReportUtils.map(baseCohorts.childrenOnC5BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC6ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC6ACohortInd", ReportUtils.map(baseCohorts.childrenOnC6ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC6BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC6BCohortInd", ReportUtils.map(baseCohorts.childrenOnC6BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnC7BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnC7BCohortInd", ReportUtils.map(baseCohorts.childrenOnC7BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnCF1ACohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnCF1ACohortInd", ReportUtils.map(baseCohorts.childrenOnCF1ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  childrenOnCF1BCohortInd = CommonIndicatorLibrary.createCohortIndicator("childrenOnCF1BCohortInd", ReportUtils.map(baseCohorts.childrenOnCF1BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));


        CohortIndicator  adultsOn10ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn10ACohortInd", ReportUtils.map(baseCohorts.adultsOn10ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn10BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn10BCohortInd", ReportUtils.map(baseCohorts.adultsOn10BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn11BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn11BCohortInd", ReportUtils.map(baseCohorts.adultsOn11BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn1BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn1BCohortInd", ReportUtils.map(baseCohorts.adultsOn1BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn2BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn2BCohortInd", ReportUtils.map(baseCohorts.adultsOn2BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn4ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn4ACohortInd", ReportUtils.map(baseCohorts.adultsOn4ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn4BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn4BCohortInd", ReportUtils.map(baseCohorts.adultsOn4BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn5ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn5ACohortInd", ReportUtils.map(baseCohorts.adultsOn5ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn5BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn5BCohortInd", ReportUtils.map(baseCohorts.adultsOn5BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn6ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn6ACohortInd", ReportUtils.map(baseCohorts.adultsOn6ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn6BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn6BCohortInd", ReportUtils.map(baseCohorts.adultsOn6BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn7ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn7ACohortInd", ReportUtils.map(baseCohorts.adultsOn7ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn7BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOn7BCohortInd", ReportUtils.map(baseCohorts.adultsOn7BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOn9CohortInd =  CommonIndicatorLibrary.createCohortIndicator("adultsOn9CohortInd", ReportUtils.map(baseCohorts.adultsOn9Cohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAF1ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAF1ACohortInd", ReportUtils.map(baseCohorts.adultsOnAF1ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAF1BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAF1BCohortInd", ReportUtils.map(baseCohorts.adultsOnAF1BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAF1CCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAF1CCohortInd", ReportUtils.map(baseCohorts.adultsOnAF1CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAF2ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAF2ACohortInd", ReportUtils.map(baseCohorts.adultsOnAF2ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAF2BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAF2BCohortInd", ReportUtils.map(baseCohorts.adultsOnAF2BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAF2CCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAF2CCohortInd", ReportUtils.map(baseCohorts.adultsOnAF2CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAF3ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAF3ACohortInd", ReportUtils.map(baseCohorts.adultsOnAF3ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAF3BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAF3BCohortInd", ReportUtils.map(baseCohorts.adultsOnAF3BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAF3CCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAF3CCohortInd", ReportUtils.map(baseCohorts.adultsOnAF3CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAS1ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAS1ACohortInd", ReportUtils.map(baseCohorts.adultsOnAS1ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAS1BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAS1BCohortInd", ReportUtils.map(baseCohorts.adultsOnAS1BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAS2ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAS2ACohortInd", ReportUtils.map(baseCohorts.adultsOnAS2ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAS2BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAS2BCohortInd", ReportUtils.map(baseCohorts.adultsOnAS2BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAS2CCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAS2CCohortInd", ReportUtils.map(baseCohorts.adultsOnAS2CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAS3ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAS3ACohortInd", ReportUtils.map(baseCohorts.adultsOnAS3ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnAS4ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnAS4ACohortInd", ReportUtils.map(baseCohorts.adultsOnAS4ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnB3BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnB3BCohortInd", ReportUtils.map(baseCohorts.adultsOnB3BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnB3CCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnB3CCohortInd", ReportUtils.map(baseCohorts.adultsOnB3CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnB3LCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnB3LCohortInd", ReportUtils.map(baseCohorts.adultsOnB3LCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC1BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC1BCohortInd", ReportUtils.map(baseCohorts.adultsOnC1BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC1CCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC1CCohortInd", ReportUtils.map(baseCohorts.adultsOnC1CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC1DCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC1DCohortInd", ReportUtils.map(baseCohorts.adultsOnC1DCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC2CCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC2CCohortInd", ReportUtils.map(baseCohorts.adultsOnC2CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC2DCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC2DCohortInd", ReportUtils.map(baseCohorts.adultsOnC2DCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC3CCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC3CCohortInd", ReportUtils.map(baseCohorts.adultsOnC3CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC3DCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC3DCohortInd", ReportUtils.map(baseCohorts.adultsOnC3DCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC4BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC4BCohortInd", ReportUtils.map(baseCohorts.adultsOnC4BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC4CCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC4CCohortInd", ReportUtils.map(baseCohorts.adultsOnC4CCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC4DCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC4DCohortInd", ReportUtils.map(baseCohorts.adultsOnC4DCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC5ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC5ACohortInd", ReportUtils.map(baseCohorts.adultsOnC5ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC5BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC5BCohortInd", ReportUtils.map(baseCohorts.adultsOnC5BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC6ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC6ACohortInd", ReportUtils.map(baseCohorts.adultsOnC6ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC6BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC6BCohortInd", ReportUtils.map(baseCohorts.adultsOnC6BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnC7BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnC7BCohortInd", ReportUtils.map(baseCohorts.adultsOnC7BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnCF1ACohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnCF1ACohortInd", ReportUtils.map(baseCohorts.adultsOnCF1ACohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        CohortIndicator  adultsOnCF1BCohortInd = CommonIndicatorLibrary.createCohortIndicator("adultsOnCF1BCohortInd", ReportUtils.map(baseCohorts.adultsOnCF1BCohort,"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));


        Map<String, Object> periodMappings = new HashMap<String, Object>();
        periodMappings.put("startDate", "${startDate}");
        periodMappings.put("endDate", "${endDate}");
        periodMappings.put("locationList", "${locationList}");


        CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
        dsd.addParameter(ReportingConstants.START_DATE_PARAMETER);
        dsd.addParameter(ReportingConstants.END_DATE_PARAMETER);
        dsd.addParameter(facility);

        dsd.addColumn("10A", "Males Below 15", new Mapped<CohortIndicator>(childrenOn10ACohortInd, periodMappings), "");
        dsd.addColumn("10B", "Males 15 or more", new Mapped<CohortIndicator>(childrenOn10BCohortInd, periodMappings), "");
         dsd.addColumn("11B", "Females Below 15", new Mapped<CohortIndicator>(childrenOn11BCohortInd, periodMappings), "");
        dsd.addColumn("1B", "Females 15 or more", new Mapped<CohortIndicator>(childrenOn1BCohortInd, periodMappings), "");

        dsd.addColumn("2B", "Male Peds up to one year", new Mapped<CohortIndicator>(childrenOn2BCohortInd, periodMappings), "");
        dsd.addColumn("4A", "Males peds between 2 and 4", new Mapped<CohortIndicator>(childrenOn4ACohortInd, periodMappings), "");
        dsd.addColumn("4B", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(childrenOn4BCohortInd, periodMappings), "");
        dsd.addColumn("5A", "Female peds at one ", new Mapped<CohortIndicator>(childrenOn5ACohortInd, periodMappings), "");
        dsd.addColumn("5B", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(childrenOn5BCohortInd, periodMappings), "");
        dsd.addColumn("6A", "Female peds between 5 and 14", new Mapped<CohortIndicator>(childrenOn6ACohortInd, periodMappings), "");

        //add columns for transfer out
        dsd.addColumn("6B", "Males Below 15", new Mapped<CohortIndicator>(childrenOn6BCohortInd, periodMappings), "");
        dsd.addColumn("7A", "Males 15 or more", new Mapped<CohortIndicator>(childrenOn7ACohortInd, periodMappings), "");
        dsd.addColumn("7B", "Females Below 15", new Mapped<CohortIndicator>(childrenOn7BCohortInd, periodMappings), "");
        dsd.addColumn("9", "Females 15 or more", new Mapped<CohortIndicator>(childrenOn9CohortInd, periodMappings), "");

        dsd.addColumn("AF1A", "Male Peds up to one year", new Mapped<CohortIndicator>(childrenOnAF1ACohortInd, periodMappings), "");
        dsd.addColumn("AF1B", "Males peds between 2 and 4", new Mapped<CohortIndicator>(childrenOnAF1BCohortInd, periodMappings), "");
        dsd.addColumn("AF1C", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(childrenOnAF1CCohortInd, periodMappings), "");
        dsd.addColumn("AF2A", "Female peds at one ", new Mapped<CohortIndicator>(childrenOnAF2ACohortInd, periodMappings), "");
        dsd.addColumn("AF2B", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(childrenOnAF2BCohortInd, periodMappings), "");
        dsd.addColumn("AF2C", "Female peds between 5 and 14", new Mapped<CohortIndicator>(childrenOnAF2CCohortInd, periodMappings), "");

        //Add columns for dead
        dsd.addColumn("AF3A", "Males Below 15", new Mapped<CohortIndicator>(childrenOnAF3ACohortInd, periodMappings), "");
        dsd.addColumn("AF3B", "Males 15 or more", new Mapped<CohortIndicator>(childrenOnAF3BCohortInd, periodMappings), "");
        dsd.addColumn("AF3C", "Females Below 15", new Mapped<CohortIndicator>(childrenOnAF3CCohortInd, periodMappings), "");
        dsd.addColumn("AS1A", "Females 15 or more", new Mapped<CohortIndicator>(childrenOnAS1ACohortInd, periodMappings), "");

        dsd.addColumn("AS1B", "Male Peds up to one year", new Mapped<CohortIndicator>(childrenOnAS1BCohortInd, periodMappings), "");
        dsd.addColumn("AS2A", "Males peds between 2 and 4", new Mapped<CohortIndicator>(childrenOnAS2ACohortInd, periodMappings), "");
        dsd.addColumn("AS2B", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(childrenOnAS2BCohortInd, periodMappings), "");
        dsd.addColumn("AS2C", "Female peds at one ", new Mapped<CohortIndicator>(childrenOnAS2CCohortInd, periodMappings), "");
        dsd.addColumn("AS3A", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(childrenOnAS3ACohortInd, periodMappings), "");
        dsd.addColumn("AS4A", "Female peds between 5 and 14", new Mapped<CohortIndicator>(childrenOnAS4ACohortInd, periodMappings), "");

        dsd.addColumn("B3B", "Males Below 15", new Mapped<CohortIndicator>(childrenOnB3BCohortInd, periodMappings), "");
        dsd.addColumn("B3C", "Males 15 or more", new Mapped<CohortIndicator>(childrenOnB3CCohortInd, periodMappings), "");
        dsd.addColumn("B3L", "Females Below 15", new Mapped<CohortIndicator>(childrenOnB3LCohortInd, periodMappings), "");
        dsd.addColumn("C1B", "Females 15 or more", new Mapped<CohortIndicator>(childrenOnC1BCohortInd, periodMappings), "");

        dsd.addColumn("C1C", "Male Peds up to one year", new Mapped<CohortIndicator>(childrenOnC1CCohortInd, periodMappings), "");
        dsd.addColumn("C1D", "Males peds between 2 and 4", new Mapped<CohortIndicator>(childrenOnC1DCohortInd, periodMappings), "");
        dsd.addColumn("C2C", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(childrenOnC2CCohortInd, periodMappings), "");
        dsd.addColumn("C2D", "Female peds at one ", new Mapped<CohortIndicator>(childrenOnC2DCohortInd, periodMappings), "");
        dsd.addColumn("C3C", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(childrenOnC3CCohortInd, periodMappings), "");
        dsd.addColumn("C3D", "Female peds between 5 and 14", new Mapped<CohortIndicator>(childrenOnC3DCohortInd, periodMappings), "");

        dsd.addColumn("C4B", "Male Peds up to one year", new Mapped<CohortIndicator>(childrenOnC4BCohortInd, periodMappings), "");
        dsd.addColumn("C4C", "Males peds between 2 and 4", new Mapped<CohortIndicator>(childrenOnC4CCohortInd, periodMappings), "");
        dsd.addColumn("C4D", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(childrenOnC4DCohortInd, periodMappings), "");
        dsd.addColumn("C5A", "Female peds at one ", new Mapped<CohortIndicator>(childrenOnC5ACohortInd, periodMappings), "");
        dsd.addColumn("C5B", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(childrenOnC5BCohortInd, periodMappings), "");
        dsd.addColumn("C6A", "Female peds between 5 and 14", new Mapped<CohortIndicator>(childrenOnC6ACohortInd, periodMappings), "");

        dsd.addColumn("C6B", "Male Peds up to one year", new Mapped<CohortIndicator>(childrenOnC6BCohortInd, periodMappings), "");
        dsd.addColumn("C7B", "Males peds between 2 and 4", new Mapped<CohortIndicator>(childrenOnC7BCohortInd, periodMappings), "");
        dsd.addColumn("CF1A", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(childrenOnCF1ACohortInd, periodMappings), "");
        dsd.addColumn("CF1B", "Female peds at one ", new Mapped<CohortIndicator>(childrenOnCF1BCohortInd, periodMappings), "");


        //Add columns for adults

        dsd.addColumn("10AA", "Males Below 15", new Mapped<CohortIndicator>(adultsOn10ACohortInd, periodMappings), "");
        dsd.addColumn("10BB", "Males 15 or more", new Mapped<CohortIndicator>(adultsOn10BCohortInd, periodMappings), "");
        dsd.addColumn("11BB", "Females Below 15", new Mapped<CohortIndicator>(adultsOn11BCohortInd, periodMappings), "");
        dsd.addColumn("1BB", "Females 15 or more", new Mapped<CohortIndicator>(adultsOn1BCohortInd, periodMappings), "");

        dsd.addColumn("2BB", "Male Peds up to one year", new Mapped<CohortIndicator>(adultsOn2BCohortInd, periodMappings), "");
        dsd.addColumn("4AA", "Males peds between 2 and 4", new Mapped<CohortIndicator>(adultsOn4ACohortInd, periodMappings), "");
        dsd.addColumn("4BB", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(adultsOn4BCohortInd, periodMappings), "");
        dsd.addColumn("5AA", "Female peds at one ", new Mapped<CohortIndicator>(adultsOn5ACohortInd, periodMappings), "");
        dsd.addColumn("5BB", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(adultsOn5BCohortInd, periodMappings), "");
        dsd.addColumn("6AA", "Female peds between 5 and 14", new Mapped<CohortIndicator>(adultsOn6ACohortInd, periodMappings), "");

        //add columns for transfer out
        dsd.addColumn("6BB", "Males Below 15", new Mapped<CohortIndicator>(adultsOn6BCohortInd, periodMappings), "");
        dsd.addColumn("7AA", "Males 15 or more", new Mapped<CohortIndicator>(adultsOn7ACohortInd, periodMappings), "");
        dsd.addColumn("7BB", "Females Below 15", new Mapped<CohortIndicator>(adultsOn7BCohortInd, periodMappings), "");
        dsd.addColumn("99", "Females 15 or more", new Mapped<CohortIndicator>(adultsOn9CohortInd, periodMappings), "");

        dsd.addColumn("AF1AA", "Male Peds up to one year", new Mapped<CohortIndicator>(adultsOnAF1ACohortInd, periodMappings), "");
        dsd.addColumn("AF1BB", "Males peds between 2 and 4", new Mapped<CohortIndicator>(adultsOnAF1BCohortInd, periodMappings), "");
        dsd.addColumn("AF1CC", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(adultsOnAF1CCohortInd, periodMappings), "");
        dsd.addColumn("AF2AA", "Female peds at one ", new Mapped<CohortIndicator>(adultsOnAF2ACohortInd, periodMappings), "");
        dsd.addColumn("AF2BB", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(adultsOnAF2BCohortInd, periodMappings), "");
        dsd.addColumn("AF2CC", "Female peds between 5 and 14", new Mapped<CohortIndicator>(adultsOnAF2CCohortInd, periodMappings), "");

        //Add columns for dead
        dsd.addColumn("AF3AA", "Males Below 15", new Mapped<CohortIndicator>(adultsOnAF3ACohortInd, periodMappings), "");
        dsd.addColumn("AF3BB", "Males 15 or more", new Mapped<CohortIndicator>(adultsOnAF3BCohortInd, periodMappings), "");
        dsd.addColumn("AF3CC", "Females Below 15", new Mapped<CohortIndicator>(adultsOnAF3CCohortInd, periodMappings), "");
        dsd.addColumn("AS1AA", "Females 15 or more", new Mapped<CohortIndicator>(adultsOnAS1ACohortInd, periodMappings), "");

        dsd.addColumn("AS1BB", "Male Peds up to one year", new Mapped<CohortIndicator>(adultsOnAS1BCohortInd, periodMappings), "");
        dsd.addColumn("AS2AA", "Males peds between 2 and 4", new Mapped<CohortIndicator>(adultsOnAS2ACohortInd, periodMappings), "");
        dsd.addColumn("AS2BB", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(adultsOnAS2BCohortInd, periodMappings), "");
        dsd.addColumn("AS2CC", "Female peds at one ", new Mapped<CohortIndicator>(adultsOnAS2CCohortInd, periodMappings), "");
        dsd.addColumn("AS3AA", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(adultsOnAS3ACohortInd, periodMappings), "");
        dsd.addColumn("AS4AA", "Female peds between 5 and 14", new Mapped<CohortIndicator>(adultsOnAS4ACohortInd, periodMappings), "");

        dsd.addColumn("B3BB", "Males Below 15", new Mapped<CohortIndicator>(adultsOnB3BCohortInd, periodMappings), "");
        dsd.addColumn("B3CC", "Males 15 or more", new Mapped<CohortIndicator>(adultsOnB3CCohortInd, periodMappings), "");
        dsd.addColumn("B3LL", "Females Below 15", new Mapped<CohortIndicator>(adultsOnB3LCohortInd, periodMappings), "");
        dsd.addColumn("C1BB", "Females 15 or more", new Mapped<CohortIndicator>(adultsOnC1BCohortInd, periodMappings), "");

        dsd.addColumn("C1CC", "Male Peds up to one year", new Mapped<CohortIndicator>(adultsOnC1CCohortInd, periodMappings), "");
        dsd.addColumn("C1DD", "Males peds between 2 and 4", new Mapped<CohortIndicator>(adultsOnC1DCohortInd, periodMappings), "");
        dsd.addColumn("C2CC", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(adultsOnC2CCohortInd, periodMappings), "");
        dsd.addColumn("C2DD", "Female peds at one ", new Mapped<CohortIndicator>(adultsOnC2DCohortInd, periodMappings), "");
        dsd.addColumn("C3CC", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(adultsOnC3CCohortInd, periodMappings), "");
        dsd.addColumn("C3DD", "Female peds between 5 and 14", new Mapped<CohortIndicator>(adultsOnC3DCohortInd, periodMappings), "");

        dsd.addColumn("C4BB", "Male Peds up to one year", new Mapped<CohortIndicator>(adultsOnC4BCohortInd, periodMappings), "");
        dsd.addColumn("C4CC", "Males peds between 2 and 4", new Mapped<CohortIndicator>(adultsOnC4CCohortInd, periodMappings), "");
        dsd.addColumn("C4DD", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(adultsOnC4DCohortInd, periodMappings), "");
        dsd.addColumn("C5AA", "Female peds at one ", new Mapped<CohortIndicator>(adultsOnC5ACohortInd, periodMappings), "");
        dsd.addColumn("C5BB", "Female peds  btw 2 and 4", new Mapped<CohortIndicator>(adultsOnC5BCohortInd, periodMappings), "");
        dsd.addColumn("C6AA", "Female peds between 5 and 14", new Mapped<CohortIndicator>(adultsOnC6ACohortInd, periodMappings), "");

        dsd.addColumn("C6BB", "Male Peds up to one year", new Mapped<CohortIndicator>(adultsOnC6BCohortInd, periodMappings), "");
        dsd.addColumn("C7BB", "Males peds between 2 and 4", new Mapped<CohortIndicator>(adultsOnC7BCohortInd, periodMappings), "");
        dsd.addColumn("CF1AA", "Male Ped btw 5 and 14", new Mapped<CohortIndicator>(adultsOnCF1ACohortInd, periodMappings), "");
        dsd.addColumn("CF1BB", "Female peds at one ", new Mapped<CohortIndicator>(adultsOnCF1BCohortInd, periodMappings), "");


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
		design.setName("Regimen Report Design");
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
			throw new APIException("Could not create report design for Regimen Report.", ex);
		}

		IOUtils.closeQuietly(is);
		design.addResource(resource);

		return design;
	}
}