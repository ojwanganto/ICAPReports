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

package org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.tbScreening;

import org.springframework.stereotype.Component;

/**
 * Library for MOH 731 Report
 */
@Component
public class TBScreeningSQLCohortLibrary {

    /**
     * Queries for patients enrolled in Care
     * @return
     */

    public String malePatientsEnrolledInCareBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String femalePatientsEnrolledInCareBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }


    /*
    * TB Screening
    * */
    public String malePatientsWhoHadTBScreeningQry(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id in(307,160555) " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsWhoHadTBScreeningQry(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id in(307,160555) " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }


    /*
   * TB Screening with positive results
   * */
    public String malePatientsWhoHadTBScreeningWithPositiveResults(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=160555 " +
                "   and (o.concept_id=307 and o.value_coded =703) " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsWhoHadTBScreeningWithPositiveResults(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=160555 " +
                "   and (o.concept_id=307 and o.value_coded =703) " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }

    /*
 * TB Screening with positive results and started on TB Drugs
 * */
    public String malePatientsWithPositiveResultsStartedOnTBDrugs(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=160555 " +
                "   and (o.concept_id=307 and o.value_coded =703) " +
                "   and (o.concept_id=1659 and o.value_coded =1662) " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsWithPositiveResultsStartedOnTBDrugs(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=160555 " +
                "   and (o.concept_id=307 and o.value_coded =703) " +
                "   and (o.concept_id=1659 and o.value_coded =1662) " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }

    /*
* TB Screening with positive results and started on TB Drugs
* */
    public String malePatientsOnTBDrugsRegardlessOfResults(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=160555 " +
                "   and (o.concept_id=307 and o.value_coded =703) " +
                "   and o.concept_id=1659  " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsOnTBDrugsRegardlessOfResults(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=160555 " +
                "   and (o.concept_id=307 and o.value_coded =703) " +
                "   and o.concept_id=1659  " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }

    /*
* Patients already On TB treatment at enrollment to HIV Care
* */
    public String malePatientsOnTBTreatmentAtEnrollmentToHIVCare(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=307 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsOnTBTreatmentAtEnrollmentToHIVCare(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=307 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }


    /*
* Patients enrolled in HIV care before reporting quarter but were diagnosed
* and started on treatment during the quarter
* regardless of screening status
* */
    public String malePatientsEnrolledB4ButStartedTduringQuarter(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=307 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsEnrolledB4ButStartedTDuringQuarter(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=307 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }

    /*
* Patients newly enrolled in HIV care, diagnosed with TB a
* and started on treatment during the quarter
* and also put on Cotrimoxazole
* */
    public String malePatientsEnrolledTBCotrimoxazole(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=160555 " +
                "   and o.concept_id=1659 and o.value_coded = 1661 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsEnrolledTBCotrimoxazole(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=160555 " +
                "   and o.concept_id=1659 and o.value_coded = 1661 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }

}