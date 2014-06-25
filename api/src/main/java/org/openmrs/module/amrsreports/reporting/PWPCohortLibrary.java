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

import org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.BaseSQLCohortLibrary;
import org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.preventionWithPositive.PreventionWithPositiveSQLCohortLibrary;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.springframework.stereotype.Component;

/**
 * Library of ART related cohort definitions
 */
@Component
public class PWPCohortLibrary {


	private PreventionWithPositiveSQLCohortLibrary commonSQLLib = new PreventionWithPositiveSQLCohortLibrary();
    private BaseSQLCohortLibrary baseSQLCohortLibrary = new BaseSQLCohortLibrary();

	/*
	* Number of new Patients enrolled into HIV care
	*
	* */

    public CohortDefinition malePatientsTested(Integer minAge){

        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.malePatientsTestedAndReceivedTestResults());
    }

    public CohortDefinition femalePatientsTested(Integer minAge){

        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.femalePatientsTestedAndReceivedTestResults());
    }


    /*
    * TB Screening
    * */
    public CohortDefinition malePatientsWhoReportedDisclosure(Integer minAge){
        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.malePatientsWhoDisclosedStatusToPartner());
    }

    public CohortDefinition femalePatientsWhoReportedDisclosure(Integer minAge){
        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.femalePatientsWhoDisclosedStatusToPartner());
    }


    /*
   * TB Screening with positive results
   * */
    public CohortDefinition malePatientsWhoReceivedCondoms(Integer minAge){
        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.malePatientsWhoReceivedCondoms());
    }

    public CohortDefinition femalePatientsWhoReceivedCondoms(Integer minAge){
        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.femalePatientsWhoReceivedCondoms());
    }

    /*
 * TB Screening with positive results and started on TB Drugs
 * */
    public CohortDefinition malePatientsProvidedWithModernContraceptives(Integer minAge){
        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.malePatientsProvidedWithModernContraceptiveMethods());
    }

    public CohortDefinition femalePatientsProvidedWithModernContraceptives(Integer minAge){
        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.femalePatientsProvidedWithModernContraceptiveMethods());
    }

    /*
* TB Screening with positive results and started on TB Drugs
* */
    public CohortDefinition malePatientsScreenedForSTI(Integer minAge){
        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.malePatientsScreenedForSTIs());
    }

    public CohortDefinition femalePatientsScreenedForSTI(Integer minAge){
        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.femalePatientsScreenedForSTIs());
    }

    /*
* Patients already On TB treatment at enrollment to HIV Care
* */
    public CohortDefinition malePatientsOnPWPPackage(Integer minAge){
        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.malePatientsWithPWPPackage());
    }

    public CohortDefinition femalePatientsOnPWPPackage(Integer minAge){
        return baseSQLCohortLibrary.compositionMinAgeCohort(minAge,commonSQLLib.femalePatientsWithPWPPackage());
    }


}