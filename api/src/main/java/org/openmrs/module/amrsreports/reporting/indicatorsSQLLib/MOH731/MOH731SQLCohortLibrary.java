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

package org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.MOH731;

import org.springframework.stereotype.Component;

/**
 * Library for MOH 731 Report
 */
@Component
public class MOH731SQLCohortLibrary {
    /**
     * Patients enrolled in Care: patients who have Date enrolled in HIV Care
     * patients currently in care: not dead,not TO, not LTFU
     * Cumulative: gets patients since program inception with given conditions
     * on ART :patients with date art started
     * Currently on ART: on ART less TO, LFTU, dead
     *
     */

    /**
     * Define Queries for Enrolment in palliative care
     * 160555 for date enrolled in program concept
     */

    /**
     * Queries for patients enrolled in Care
     * @return
     */
    public String infantsEnrolledInCareBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

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



    public String cumulativeMalesInCare(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:endDate) ";

        return sql;
    }

    public String cumulativeFemalesInCare(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:endDate) ";

        return sql;
    }

    public String cumulativeInfantsInCare(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:endDate) ";

        return sql;
    }


    /**
     * Queries for patients Currently in Care
     * @return
     */
    public String infantsCurrentlyInCareBetweenDatesQry(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in (:locationList)  " +
                "   and obs.person_id not in ( select person_id from person where dead=1 ) " +
                "   and obs.person_id not in ( select person_id from obs where concept_id in (1543,160649) and value_datetime <= (:endDate) ) " +
                "   and obs.person_id not in ( select o.person_id from obs o where concept_id = 5096 and value_datetime >= (:endDate) ) " +
                "   and obs.person_id not in ( select e.patient_id from encounter e group by e.patient_id having max(e.encounter_datetime) between date_add((:endDate),INTERVAL -93 DAY) and (:endDate) ) " +
                "   and value_datetime between (:startDate) and (:endDate)  " ;


        return sql;
    }

    public String malePatientsCurrentlyInCareBetweenDatesQry(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in (:locationList)  " +
                "   and obs.person_id not in ( select person_id from person where dead=1 ) " +
                "   and obs.person_id not in ( select person_id from obs where concept_id in (1543,160649) and value_datetime <= (:endDate) ) " +
                "   and obs.person_id not in ( select o.person_id from obs o where concept_id = 5096 and value_datetime >= (:endDate) ) " +
                "   and obs.person_id not in ( select e.patient_id from encounter e group by e.patient_id having max(e.encounter_datetime) between date_add((:endDate),INTERVAL -93 DAY) and (:endDate) ) " +
                "   and value_datetime between (:startDate) and (:endDate)  " +
                "   and gender='M' " ;

        return sql;
    }

    public String femalePatientsCurrentlyInCareBetweenDatesQry(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in (:locationList)  " +
                "   and obs.person_id not in ( select person_id from person where dead=1 ) " +
                "   and obs.person_id not in ( select person_id from obs where concept_id in (1543,160649) and value_datetime <= (:endDate) ) " +
                "   and obs.person_id not in ( select o.person_id from obs o where concept_id = 5096 and value_datetime >= (:endDate) ) " +
                "   and obs.person_id not in ( select e.patient_id from encounter e group by e.patient_id having max(e.encounter_datetime) between date_add((:endDate),INTERVAL -93 DAY) and (:endDate) ) " +
                "   and value_datetime between (:startDate) and (:endDate)  " +
                "   and gender='F' " ;

        return sql;
    }



    public String cumulativeMalesCurrentlyInCare(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in (:locationList)  " +
                "   and obs.person_id not in ( select person_id from person where dead=1 ) " +
                "   and obs.person_id not in ( select person_id from obs where concept_id in (1543,160649) and value_datetime <= (:startDate) ) " +
                "   and obs.person_id not in ( select o.person_id from obs o where concept_id = 5096 and value_datetime >= (:startDate) ) " +
                "   and obs.person_id not in ( select e.patient_id from encounter e group by e.patient_id having max(e.encounter_datetime) between date_add((:startDate),INTERVAL -93 DAY) and (:startDate) ) " +
                "   and value_datetime <= (:endDate)  " +
                "   and gender='M' " ;

        return sql;
    }

    public String cumulativeFemalesCurrentlyInCare(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in (:locationList)  " +
                "   and obs.person_id not in ( select person_id from person where dead=1 ) " +
                "   and obs.person_id not in ( select person_id from obs where concept_id in (1543,160649) and value_datetime <= (:startDate) ) " +
                "   and obs.person_id not in ( select o.person_id from obs o where concept_id = 5096 and value_datetime >= (:startDate) ) " +
                "   and obs.person_id not in ( select e.patient_id from encounter e group by e.patient_id having max(e.encounter_datetime) between date_add((:startDate),INTERVAL -93 DAY) and (:startDate) ) " +
                "   and value_datetime <= (:endDate)  " +
                "   and gender='F' " ;

        return sql;
    }
    /**
     * Define Queries for Cotrimoxazole
     * 162229 for Q and 1065 for YES answer
     */
    public String malePatientsOnCotrimoxazoleBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=162229 " +
                "  and value_coded=1065 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String femalePatientsOnCotrimoxazoleBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=162229 " +
                "  and value_coded=1065 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnCotrimoxazoleAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=162229 " +
                "  and value_coded=1065 " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime >= (:startDate) ";

        return sql;
    }

    /**
     * Define Queries for exposed infants
     * 162229 for Q and 1065 for YES answer
     */
    public String exposedInfantsBetweenDatesQry(){

        String sql ="select patient_id from encounter e  " +
                "  inner join person p on e.patient_id = p.person_id " +
                "  where encounter_type in (2)  " +
                "  and form_id in (2)  " +
                "  and encounter_datetime between (:startDate) and (:endDate)  " +
                "  and location_id in (:locationList)  " ;

        return sql;
    }

    public String infantsInCareBetweenDatesQry(){
        String sql ="SELECT a.patient_id FROM patient_program a   " +
                " inner join person p ON(a.patient_id=p.person_id)   " +
                " WHERE a.location_id IN(:locationList)    " +
                " AND a.date_completed IS NULL   " +
                " and a.date_enrolled between (:startDate) and (:endDate) ";

        return sql;
    }



    /**
     * Definitions for patients starting ART
     * @return
     */
    public String infantsStartingARTBetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p ON o.person_id=p.person_id   " +
                " WHERE o.concept_id=159599   " +
                " AND o.location_id IN(:locationList)   " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)   ";

        return sql;
    }

    public String malePatientsStartingARTBetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p ON o.person_id=p.person_id   " +
                " WHERE o.concept_id=159599   " +
                " AND o.location_id IN(:locationList)   " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)   " +
                " AND p.gender='M' ";

        return sql;
    }

    public String femalePatientsStartingARTBetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p ON o.person_id=p.person_id   " +
                " WHERE o.concept_id=159599   " +
                " AND o.location_id IN(:locationList)   " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)   " +
                " AND p.gender='F' ";

        return sql;
    }

    /**
     * Definitions for patients Currently on ART
     */

    public String infantsCurrentlyOnARTBetweenDatesQry(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in (:locationList)  " +
                "   and obs.person_id not in ( select person_id from person where dead=1 ) " +
                "   and obs.person_id not in ( select person_id from obs where concept_id in (1543,160649) and value_datetime <= (:endDate) ) " +
                "   and obs.person_id not in ( select o.person_id from obs o where concept_id = 5096 and value_datetime >= (:endDate) ) " +
                "   and obs.person_id not in ( select e.patient_id from encounter e group by e.patient_id having max(e.encounter_datetime) between date_add((:endDate),INTERVAL -93 DAY) and (:endDate) ) " +
                "   and value_datetime between (:startDate) and (:endDate)  " +
                "   and obs.person_id in (select person_id from obs where concept_id = 159599 and value_datetime <= (:endDate)) " ;

        return sql;
    }

    public String malePatientsCurrentlyOnARTBetweenDatesQry(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in (:locationList)  " +
                "   and obs.person_id not in ( select person_id from person where dead=1 ) " +
                "   and obs.person_id not in ( select person_id from obs where concept_id in (1543,160649) and value_datetime <= (:endDate) ) " +
                "   and obs.person_id not in ( select o.person_id from obs o where concept_id = 5096 and value_datetime >= (:endDate) ) " +
                "   and obs.person_id not in ( select e.patient_id from encounter e group by e.patient_id having max(e.encounter_datetime) between date_add((:endDate),INTERVAL -93 DAY) and (:endDate) ) " +
                "   and value_datetime between (:startDate) and (:endDate)  " +
                "   and obs.person_id in (select person_id from obs where concept_id = 159599 and value_datetime <= (:endDate)) "  +
                "   and gender='M' " ;

        return sql;
    }

    public String femalePatientsCurrentlyOnARTBetweenDatesQry(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in (:locationList)  " +
                "   and obs.person_id not in ( select person_id from person where dead=1 ) " +
                "   and obs.person_id not in ( select person_id from obs where concept_id in (1543,160649) and value_datetime <= (:endDate) ) " +
                "   and obs.person_id not in ( select o.person_id from obs o where concept_id = 5096 and value_datetime >= (:endDate) ) " +
                "   and obs.person_id not in ( select e.patient_id from encounter e group by e.patient_id having max(e.encounter_datetime) between date_add((:endDate),INTERVAL -93 DAY) and (:endDate) ) " +
                "   and value_datetime between (:startDate) and (:endDate)  " +
                "   and obs.person_id in (select person_id from obs where concept_id = 159599 and value_datetime <= (:endDate)) "  +
                "   and gender='F' " ;

        return sql;
    }
    //======================================================================

    /**
     * Definitions of patients with ART Revisits
     * @return
     */

    public String infantsWithRevisitsBetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p on o.person_id=p.person_id  " +
                " WHERE o.concept_id=5096  " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)  " +
                " AND o.location_id IN(:locationList)  ";

        return sql;
    }
    public String malePatientsWithRevisitsBetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p on o.person_id=p.person_id  " +
                " WHERE o.concept_id=5096  " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)  " +
                " AND o.location_id IN(:locationList)  " +
                " AND p.gender='M' ";

        return sql;
    }

    public String femalePatientsWithRevisitsBetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p on o.person_id=p.person_id  " +
                " WHERE o.concept_id=5096  " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)  " +
                " AND o.location_id IN(:locationList)  " +
                " AND p.gender='F' ";

        return sql;
    }

    public String pregnantPatientsBetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p on o.person_id=p.person_id  " +
                " inner join patient_program c on o.person_id=c.patient_id " +
                " WHERE o.concept_id=5272 AND o.value_coded=1065  " +
                " AND o.obs_datetime BETWEEN (:startDate) AND (:endDate)  " +
                " AND o.location_id IN(:locationList)  ";

        return sql;
    }

    public String malePatientsEverOnCareQry(){
        String sql ="SELECT p.person_id FROM obs o  " +
                " inner join person p ON o.person_id=p.person_id  " +
                " WHERE o.concept_id=159599 " +
                " AND o.value_datetime <= :endDate  " +
                " AND o.location_id IN(:locationList)  " +
                " AND p.gender='M' ";

        return sql;
    }

    public String femalePatientsEverOnCareQry(){
        String sql ="SELECT p.person_id FROM obs o  " +
                " inner join person p ON o.person_id=p.person_id  " +
                " WHERE o.concept_id=159599 " +
                " AND o.value_datetime <= :endDate  " +
                " AND o.location_id IN(:locationList)  " +
                " AND p.gender='F' ";

        return sql;
    }
    
    //Retention

    public String patientsOnOriginalFirstLineRegimenQry(){
        String sql ="SELECT p.person_id  " +
                "   FROM obs o INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=159599  " +
                "   AND o.concept_id !=160562 " +
                "   and o.value_datetime BETWEEN (:startDate) AND (:endDate) " ;

        return sql;
    }

    public String patientsOnAlternativeFirstLineRegimenQry(){
        String sql ="SELECT p.person_id  " +
                "   FROM obs o INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=159599  " +
                "   AND o.concept_id =160562 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " ;

        return sql;
    }

    public String patientsOnSecondLineOrHigherRegimenQry(){
        String sql ="SELECT p.person_id  " +
                "   FROM obs o INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=160568 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " ;

        return sql;
    }

    public String malePatientsWhoHadTBScreeningQry(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=307 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsWhoHadTBScreeningQry(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=307 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }

}