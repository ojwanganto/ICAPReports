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

package org.openmrs.module.amrsreports.reporting;

import org.openmrs.Location;
import org.openmrs.module.amrsreports.reporting.cohort.definition.CurrentlyInCareCohortDefinition;
import org.openmrs.module.amrsreports.reporting.cohort.definition.CurrentlyOnARTCohortDefinition;
import org.openmrs.module.amrsreports.reporting.cohort.definition.PatientsWithRecentEncCohortDefinition;
import org.openmrs.module.amrsreports.reporting.cohort.definition.DeadPatientsCohortDefinition;
import org.openmrs.module.amrsreports.reporting.cohort.definition.TransferOUTCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.AgeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.common.DurationUnit;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Library of ART related cohort definitions
 */
@Component
public class MOH731CohortLibrary {


	private CommonCohortLibrary commonCohortLibrary = new CommonCohortLibrary();


   


}
