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

package org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.MOH711;

import org.springframework.stereotype.Component;

/**
 * Library for MOH 731 Report
 */
@Component
public class MOH711SQLCohortLibrary {
    /**
     * Entry Point: 160540
     * WHO Stage: 5356
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



    public String malePatientsEnrolledInCareThroPMTCTBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and (concept_id=160540 and value_coded=160538) " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String femalePatientsEnrolledInCareThroPMTCTBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and (concept_id=160540 and value_coded=160538) " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }


    public String malePatientsEnrolledInCareThroVCTBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and (concept_id=160540 and value_coded=160539) " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String femalePatientsEnrolledInCareThroVCTBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and (concept_id=160540 and value_coded=160539) " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }


    public String malePatientsEnrolledInCareThroTBBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and (concept_id=160540 and value_coded=160541) " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String femalePatientsEnrolledInCareThroTBBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and (concept_id=160540 and value_coded=160541) " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String malePatientsEnrolledInCareThroInpatientBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and (concept_id=160540 and value_coded=160536) " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String femalePatientsEnrolledInCareThroInpatientBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and (concept_id=160540 and value_coded=160536) " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String malePatientsEnrolledInCareThroCWCBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String femalePatientsEnrolledInCareThroCWCBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String malePatientsEnrolledInCareThroAllOthersBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and (concept_id=160540 and value_coded=5622) " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String femalePatientsEnrolledInCareThroAllOthersBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and (concept_id=160540 and value_coded=5622) " +
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


    //======================================================================================================

    //======================================================================================================

    public String malePatientsStartingARTWHO1BetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p ON o.person_id=p.person_id   " +
                " WHERE o.concept_id=159599   " +
                " AND o.location_id IN(:locationList)   " +
                " and (concept_id=5356 and value_coded=1204) " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)   " +
                " AND p.gender='M' ";

        return sql;
    }

    public String femalePatientsStartingARTWHO1BetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p ON o.person_id=p.person_id   " +
                " WHERE o.concept_id=159599   " +
                " AND o.location_id IN(:locationList)   " +
                " and (concept_id=5356 and value_coded=1204) " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)   " +
                " AND p.gender='F' ";

        return sql;
    }

    public String malePatientsStartingARTWHO2BetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p ON o.person_id=p.person_id   " +
                " WHERE o.concept_id=159599   " +
                " AND o.location_id IN(:locationList)   " +
                " and (concept_id=5356 and value_coded=1205) " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)   " +
                " AND p.gender='M' ";

        return sql;
    }

    public String femalePatientsStartingARTWHO2BetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p ON o.person_id=p.person_id   " +
                " WHERE o.concept_id=159599   " +
                " AND o.location_id IN(:locationList)   " +
                " and (concept_id=5356 and value_coded=1205) " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)   " +
                " AND p.gender='F' ";

        return sql;
    }

    public String malePatientsStartingARTWHO3BetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p ON o.person_id=p.person_id   " +
                " WHERE o.concept_id=159599   " +
                " AND o.location_id IN(:locationList)   " +
                " and (concept_id=5356 and value_coded=1206) " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)   " +
                " AND p.gender='M' ";

        return sql;
    }

    public String femalePatientsStartingARTWHO3BetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p ON o.person_id=p.person_id   " +
                " WHERE o.concept_id=159599   " +
                " AND o.location_id IN(:locationList)   " +
                " and (concept_id=5356 and value_coded=1206) " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)   " +
                " AND p.gender='F' ";

        return sql;
    }


    public String malePatientsStartingARTWHO4BetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p ON o.person_id=p.person_id   " +
                " WHERE o.concept_id=159599   " +
                " AND o.location_id IN(:locationList)   " +
                " and (concept_id=5356 and value_coded=1207) " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)   " +
                " AND p.gender='M' ";

        return sql;
    }

    public String femalePatientsStartingARTWHO4BetweenDatesQry(){
        String sql ="SELECT o.person_id FROM obs o   " +
                " inner join person p ON o.person_id=p.person_id   " +
                " WHERE o.concept_id=159599   " +
                " AND o.location_id IN(:locationList)   " +
                " and (concept_id=5356 and value_coded=1207) " +
                " AND o.value_datetime BETWEEN (:startDate) AND (:endDate)   " +
                " AND p.gender='F' ";

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
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "   and concept_id not in (160649,1543) " +
                "   and value_datetime <= (:endDate) ";

        return sql;
    }

    public String malePatientsCurrentlyInCareBetweenDatesQry(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "   and concept_id not in (160649,1543) " +
                "   and value_datetime <= (:endDate) " +
                "   and gender='M' " ;

        return sql;
    }

    public String femalePatientsCurrentlyInCareBetweenDatesQry(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "   and concept_id not in (160649,1543) " +
                "   and value_datetime <= (:endDate) " +
                "   and gender='F' " ;

        return sql;
    }



    public String cumulativeMalesCurrentlyInCare(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "   and concept_id not in (160649,1543) " +
                "   and value_datetime <= (:endDate) " +
                "   and gender='M' " ;

        return sql;
    }

    public String cumulativeFemalesCurrentlyInCare(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "   and concept_id not in (160649,1543) " +
                "   and value_datetime <= (:endDate) "+
                "   and gender='F' " ;

        return sql;
    }

    public String cumulativeInfantsCurrentlyInCare(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "   and concept_id not in (160649,1543) " +
                "   and value_datetime <= (:endDate) ";

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
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "  and concept_id not in (160649,1543) " +
                "  and (concept_id = 159599 and value_datetime between (:startDate) and (:endDate)) " ;

        return sql;
    }

    public String malePatientsCurrentlyOnARTBetweenDatesQry(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "  and concept_id not in (160649,1543) " +
                "  and (concept_id = 159599 and value_datetime between (:startDate) and (:endDate)) " +
                "  and gender='M' " ;

        return sql;
    }

    public String femalePatientsCurrentlyOnARTBetweenDatesQry(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "  and concept_id not in (160649,1543) " +
                "  and (concept_id = 159599 and value_datetime between (:startDate) and (:endDate)) " +
                "  and gender='F' " ;

        return sql;
    }



    public String cumulativeMalesCurrentlyOnART(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "  and concept_id not in (160649,1543) " +
                "  and (concept_id = 159599 and value_datetime <= (:endDate)) " +
                "  and gender='M' " ;

        return sql;
    }

    public String cumulativeFemalesCurrentlyOnART(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "  and concept_id not in (160649,1543) " +
                "  and (concept_id = 159599 and value_datetime <= (:endDate)) " +
                "  and gender='F' " ;

        return sql;
    }

    public String cumulativeInfantsCurrentlyOnART(){
        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:endDate) and date_add(:endDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "  and concept_id not in (160649,1543) " +
                "  and (concept_id = 159599 and value_datetime <= (:endDate)) ";

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