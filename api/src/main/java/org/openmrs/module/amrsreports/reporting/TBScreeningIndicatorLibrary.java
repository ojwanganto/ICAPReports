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
import org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.MOH711.MOH711SQLCohortLibrary;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.stereotype.Component;

/**
 * Library of MOH 731 related indicator definitions. All indicators require parameters ${startDate} and ${endDate}
 */
@Component
public class TBScreeningIndicatorLibrary {


    private TBScreeningCohortLibrary baseCohorts = new TBScreeningCohortLibrary();



    //indicators for Enrolled in care
    public CohortIndicator malesZeroToOneEnrolledInCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledInCareBetweenDatesQry(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesTwoToFourEnrolledInCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledInCareBetweenDatesQry(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesFiveToFourteenEnrolledInCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledInCareBetweenDatesQry(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator males15AndAboveEnrolledInCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledInCareBetweenDatesQry(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }


    public CohortIndicator femalesZeroToOneEnrolledInCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledInCareBetweenDatesQry(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesTwoToFourEnrolledInCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledInCareBetweenDatesQry(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesFiveToFourteenEnrolledInCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledInCareBetweenDatesQry(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator females15AndAboveEnrolledInCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledInCareBetweenDatesQry(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }



    //2. Number of patients screened for active TB at enrollment into HIV care
    public CohortIndicator malesZeroToOneWhoHadTBScreening() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWhoHadTBScreeningQry(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesTwoToFourWhoHadTBScreening() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWhoHadTBScreeningQry(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesFiveToFourteenWhoHadTBScreening() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWhoHadTBScreeningQry(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator males15AndAboveWhoHadTBScreening() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWhoHadTBScreeningQry(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }


    public CohortIndicator femalesZeroToOneWhoHadTBScreening() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWhoHadTBScreeningQry(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesTwoToFourWhoHadTBScreening() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWhoHadTBScreeningQry(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesFiveToFourteenWhoHadTBScreening() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWhoHadTBScreeningQry(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator females15AndAboveWhoHadTBScreening() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWhoHadTBScreeningQry(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }


    //4. Number of patients who were screened for active TB at enrollment into HIV Care who screened positive for active TB

    public CohortIndicator malesZeroToOneWhoHadTBScreeningWithPositiveResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWhoHadTBScreeningWithPositiveResults(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesTwoToFourWhoHadTBScreeningWithPositiveResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWhoHadTBScreeningWithPositiveResults(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesFiveToFourteenWhoHadTBScreeningWithPositiveResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWhoHadTBScreeningWithPositiveResults(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator males15AndAboveWhoHadTBScreeningWithPositiveResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWhoHadTBScreeningWithPositiveResults(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }


    public CohortIndicator femalesZeroToOneWhoHadTBScreeningWithPositiveResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWhoHadTBScreeningWithPositiveResults(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesTwoToFourWhoHadTBScreeningWithPositiveResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWhoHadTBScreeningWithPositiveResults(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesFiveToFourteenWhoHadTBScreeningWithPositiveResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWhoHadTBScreeningWithPositiveResults(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator females15AndAboveWhoHadTBScreeningWithPositiveResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWhoHadTBScreeningWithPositiveResults(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }


    /*6. Number of patients who screened positive at enrollment
    into HIV Care and were subsequently diagnosed with active TB and
    started on treatment for active TB disease*/

    public CohortIndicator malesZeroToOneWithPositiveResultsStartedOnTBDrugs() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWithPositiveResultsStartedOnTBDrugs(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesTwoToFourWithPositiveResultsStartedOnTBDrugs() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWithPositiveResultsStartedOnTBDrugs(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesFiveToFourteenWithPositiveResultsStartedOnTBDrugs() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWithPositiveResultsStartedOnTBDrugs(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator males15AndAboveWithPositiveResultsStartedOnTBDrugs() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsWithPositiveResultsStartedOnTBDrugs(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }


    public CohortIndicator femalesZeroToOneWithPositiveResultsStartedOnTBDrugs() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWithPositiveResultsStartedOnTBDrugs(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesTwoToFourWithPositiveResultsStartedOnTBDrugs() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWithPositiveResultsStartedOnTBDrugs(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesFiveToFourteenWithPositiveResultsStartedOnTBDrugs() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWithPositiveResultsStartedOnTBDrugs(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator females15AndAboveWithPositiveResultsStartedOnTBDrugs() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsWithPositiveResultsStartedOnTBDrugs(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    /**
     * 8a. Patients who were newly enrolled in HIV Care, diagnosed with active TB disease
     * and started treatment during the reporting quarter regardless of screening status on treatment for
     */

    public CohortIndicator malesZeroToOneOnTBDrugsRegardlessOfResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
            ReportUtils.map(baseCohorts.malePatientsOnTBDrugsRegardlessOfResults(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
}

    public CohortIndicator malesTwoToFourOnTBDrugsRegardlessOfResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsOnTBDrugsRegardlessOfResults(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesFiveToFourteenOnTBDrugsRegardlessOfResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsOnTBDrugsRegardlessOfResults(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator males15AndAboveOnTBDrugsRegardlessOfResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsOnTBDrugsRegardlessOfResults(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }


    public CohortIndicator femalesZeroToOneOnTBDrugsRegardlessOfResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsOnTBDrugsRegardlessOfResults(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesTwoToFourOnTBDrugsRegardlessOfResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsOnTBDrugsRegardlessOfResults(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesFiveToFourteenOnTBDrugsRegardlessOfResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsOnTBDrugsRegardlessOfResults(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator females15AndAboveOnTBDrugsRegardlessOfResults() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsOnTBDrugsRegardlessOfResults(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }


    /**
     * 8c. Patients who were enrolled before the reporting quarter
     * but were diagnosed and started on
     * treatment during the reporting quarter regardless of screening status
     */

    public CohortIndicator malesZeroToOneOnTBTreatmentAtEnrollmentToHIVCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsOnTBTreatmentAtEnrollmentToHIVCare(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesTwoToFourOnTBTreatmentAtEnrollmentToHIVCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsOnTBTreatmentAtEnrollmentToHIVCare(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesFiveToFourteenOnTBTreatmentAtEnrollmentToHIVCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsOnTBTreatmentAtEnrollmentToHIVCare(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator males15AndAboveOnTBTreatmentAtEnrollmentToHIVCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsOnTBTreatmentAtEnrollmentToHIVCare(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }


    public CohortIndicator femalesZeroToOneOnTBTreatmentAtEnrollmentToHIVCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsOnTBTreatmentAtEnrollmentToHIVCare(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesTwoToFourOnTBTreatmentAtEnrollmentToHIVCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsOnTBTreatmentAtEnrollmentToHIVCare(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesFiveToFourteenOnTBTreatmentAtEnrollmentToHIVCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsOnTBTreatmentAtEnrollmentToHIVCare(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator females15AndAboveOnTBTreatmentAtEnrollmentToHIVCare() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsOnTBTreatmentAtEnrollmentToHIVCare(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    /**
     * 9. Number of new HIV patients
     * newly diagnosed with active TB who started treatment
     * for active TB disease and who initiated cotrimoxazole prophylaxis
     */

    public CohortIndicator malesZeroToOneEnrolledB4ButStartedTduringQuarter() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledB4ButStartedTduringQuarter(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesTwoToFourEnrolledB4ButStartedTduringQuarter() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledB4ButStartedTduringQuarter(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesFiveToFourteenEnrolledB4ButStartedTduringQuarter() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledB4ButStartedTduringQuarter(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator males15AndAboveEnrolledB4ButStartedTduringQuarter() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledB4ButStartedTduringQuarter(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }


    public CohortIndicator femalesZeroToOneEnrolledB4ButStartedTDuringQuarter() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledB4ButStartedTDuringQuarter(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesTwoToFourEnrolledB4ButStartedTDuringQuarter() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledB4ButStartedTDuringQuarter(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesFiveToFourteenEnrolledB4ButStartedTDuringQuarter() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledB4ButStartedTDuringQuarter(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator females15AndAboveEnrolledB4ButStartedTDuringQuarter() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledB4ButStartedTDuringQuarter(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    /**
     * 10. Proportion of patients diagnosed with active TB who were started on cotrimoxazole prophylaxis
     */

    public CohortIndicator malesZeroToOneEnrolledTBCotrimoxazole() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledTBCotrimoxazole(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesTwoToFourEnrolledTBCotrimoxazole() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledTBCotrimoxazole(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator malesFiveToFourteenEnrolledTBCotrimoxazole() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledTBCotrimoxazole(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator males15AndAboveEnrolledTBCotrimoxazole() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.malePatientsEnrolledTBCotrimoxazole(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }


    public CohortIndicator femalesZeroToOneEnrolledTBCotrimoxazole() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 0 - 1 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledTBCotrimoxazole(0,1), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesTwoToFourEnrolledTBCotrimoxazole() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 2 - 4 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledTBCotrimoxazole(2,4), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator femalesFiveToFourteenEnrolledTBCotrimoxazole() {
        return CommonIndicatorLibrary.createCohortIndicator("Males 5 - 14 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledTBCotrimoxazole(5,14), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

    public CohortIndicator females15AndAboveEnrolledTBCotrimoxazole() {
        return CommonIndicatorLibrary.createCohortIndicator("Males => 15 Enrolled in care",
                ReportUtils.map(baseCohorts.femalePatientsEnrolledTBCotrimoxazole(15,200), "startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
    }

}