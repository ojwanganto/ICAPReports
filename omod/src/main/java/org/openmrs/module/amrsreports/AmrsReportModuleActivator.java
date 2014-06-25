/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.amrsreports;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.Activator;
import org.openmrs.module.amrsreports.reporting.provider.ARTCareFollowUpProvider;
import org.openmrs.module.amrsreports.reporting.provider.ARTCareProvider;
import org.openmrs.module.amrsreports.reporting.provider.AdolescentOnCareAndARTCareProvider;
import org.openmrs.module.amrsreports.reporting.provider.AttendedReportProvider;
import org.openmrs.module.amrsreports.reporting.provider.BookedAndAttendedReportProvider;
import org.openmrs.module.amrsreports.reporting.provider.BookedReportProvider;
import org.openmrs.module.amrsreports.reporting.provider.DailyAttendanceProvider;
import org.openmrs.module.amrsreports.reporting.provider.DifferentCohortAnalysisReportProvider;
import org.openmrs.module.amrsreports.reporting.provider.EMTCTReportProvider;
import org.openmrs.module.amrsreports.reporting.provider.EligibleButNotOnARVReportProvider;
import org.openmrs.module.amrsreports.reporting.provider.HIVPalliativeCareProvider;
import org.openmrs.module.amrsreports.reporting.provider.ICAPMOH361AReportProvider;
import org.openmrs.module.amrsreports.reporting.provider.ICAPMOH361BReportProvider;
import org.openmrs.module.amrsreports.reporting.provider.LFTUReportProvider;
import org.openmrs.module.amrsreports.reporting.provider.MOH711Provider;
import org.openmrs.module.amrsreports.reporting.provider.MOH731Provider;
import org.openmrs.module.amrsreports.reporting.provider.PreventionWithPositiveProvider;
import org.openmrs.module.amrsreports.reporting.provider.RegimensProvider;
import org.openmrs.module.amrsreports.reporting.provider.SameCohortAnalysisReportProvider;
import org.openmrs.module.amrsreports.reporting.provider.TBScreeningReportProvider;
import org.openmrs.module.amrsreports.service.ReportProviderRegistrar;
import org.openmrs.module.amrsreports.util.TaskRunnerThread;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
@SuppressWarnings("deprecation")
public class AmrsReportModuleActivator implements Activator {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * @see org.openmrs.module.Activator#startup()
	 */
	public void startup() {
		log.info("Starting AMRS Reporting Module");

		// TODO use some classpath or Spring magic to acquire these automatically

        ReportProviderRegistrar.getInstance().registerReportProvider(new ARTCareProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new EligibleButNotOnARVReportProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new ARTCareFollowUpProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new HIVPalliativeCareProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new RegimensProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new MOH731Provider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new MOH711Provider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new BookedReportProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new BookedAndAttendedReportProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new LFTUReportProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new AttendedReportProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new EMTCTReportProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new DifferentCohortAnalysisReportProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new SameCohortAnalysisReportProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new TBScreeningReportProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new PreventionWithPositiveProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new ICAPMOH361AReportProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new ICAPMOH361BReportProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new AdolescentOnCareAndARTCareProvider());
        ReportProviderRegistrar.getInstance().registerReportProvider(new DailyAttendanceProvider());

	}

	/**
	 * @see org.openmrs.module.Activator#shutdown()
	 */
	public void shutdown() {
		log.info("Shutting down AMRS Reporting Module");

		try {
			TaskRunnerThread.destroyInstance();
		} catch (Throwable throwable) {
			log.warn("problem destroying Task Runner Thread instance", throwable);
		}
	}

}
