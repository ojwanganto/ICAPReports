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

package org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.cohortAnalysis;

import org.springframework.stereotype.Component;

/**
 * Library for MOH 731 Report
 */
@Component
public class CohortAnalysisSQLCohortLibrary {
    /**
     * Entry Point: 160540
     * WHO Stage: 5356
     * @return
     */

    public String patientsEnrolledInCare(Integer interval){

        if(interval !=null){

            if(interval > 0){
                   String sql ="select obs.person_id from obs " +
                        "  inner join person p " +
                        "  on p.person_id=obs.person_id  " +
                        "  where concept_id=160555 " +
                        "  and location_id in(:locationList) " +
                        "  and value_datetime <= date_add(:startDate,INTERVAL " + interval + " MONTH) ";
                   return sql;
        }
        }

        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between  (:startDate) and (:endDate) ";



        return sql;

    }

    /**
     * Queries for patients Currently in Care
     * @return
     */
    public String patientsCurrentlyInCare(Integer interval){

        if(interval !=null){

            if(interval > 0){
                String sql ="select obs.person_id from obs  " +
                        "   inner join person p  " +
                        "   on p.person_id=obs.person_id   " +
                        "   where concept_id=160555  " +
                        "   and location_id in(:locationList)  " +
                        "   and obs.person_id  in ( " +
                        "      select e.patient_id from encounter e  " +
                        "      inner join obs o on o.person_id=e.patient_id  " +
                        "      group by e.patient_id  " +
                        "      having max(e.encounter_datetime) between (:startDate) and date_add(:startDate,INTERVAL -93 DAY)  " +
                        "        union select o.person_id from obs o  " +
                        "            inner join person p  " +
                        "            on p.person_id=o.person_id  " +
                        "            where o.voided=0  " +
                        "            and concept_id=5096  " +
                        "            and value_datetime is not null  " +
                        "            and value_datetime >=(:endDate) " +
                        "   ) " +
                        "  and concept_id not in (160649,1543) " +
                        "  and value_datetime <= date_add(:startDate,INTERVAL " + interval + " MONTH) ";
                return sql;
            }
        }

        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in(:locationList)  " +
                "   and obs.person_id  in ( " +
                "      select e.patient_id from encounter e  " +
                "      inner join obs o on o.person_id=e.patient_id  " +
                "      group by e.patient_id  " +
                "      having max(e.encounter_datetime) between (:startDate) and date_add(:startDate,INTERVAL -93 DAY)  " +
                "        union select o.person_id from obs o  " +
                "            inner join person p  " +
                "            on p.person_id=o.person_id  " +
                "            where o.voided=0  " +
                "            and concept_id=5096  " +
                "            and value_datetime is not null  " +
                "            and value_datetime >=(:endDate) " +
                "   ) " +
                "   and concept_id not in (160649,1543) " +
                "   and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }


    /**
     * Definitions for patients Currently on ART
     */


    public String patientsCurrentlyOnART(Integer interval){

        if(interval !=null){

            if(interval > 0){
                String sql ="select obs.person_id from obs  " +
                        "   inner join person p  " +
                        "   on p.person_id=obs.person_id   " +
                        "   where concept_id=160555  " +
                        "   and location_id in(:locationList)  " +
                        "   and obs.person_id  in ( " +
                        "      select e.patient_id from encounter e  " +
                        "      inner join obs o on o.person_id=e.patient_id  " +
                        "      group by e.patient_id  " +
                        "      having max(e.encounter_datetime) between (:startDate) and date_add(:startDate,INTERVAL -93 DAY)  " +
                        "        union select o.person_id from obs o  " +
                        "            inner join person p  " +
                        "            on p.person_id=o.person_id  " +
                        "            where o.voided=0  " +
                        "            and concept_id=5096  " +
                        "            and value_datetime is not null  " +
                        "            and value_datetime >=(:endDate) " +
                        "   ) " +
                        "  and concept_id not in (160649,1543) " +
                        "  and (concept_id = 159599 and value_datetime <= date_add(:startDate,INTERVAL " + interval + " MONTH)) ";
                return sql;
            }
        }

        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in (:locationList)  " +
                "   and obs.person_id not in ( select person_id from person where dead=1 ) " +
                "   and obs.person_id not in ( select person_id from obs where concept_id in (1543,160649) and value_datetime <= (:startDate) ) " +
                "   and obs.person_id not in ( select o.person_id from obs o where concept_id = 5096 and value_datetime >= (:startDate) ) " +
                "   and obs.person_id not in ( select e.patient_id from encounter e group by e.patient_id having max(e.encounter_datetime) between date_add((:startDate),INTERVAL -93 DAY) and (:startDate) ) " +
                "   and value_datetime between (:startDate) and (:endDate)  " +
                "   and (concept_id = 159599 and value_datetime <= (:startDate)) ";

        return sql;
    }

    //Retention

    public String patientsOnOriginalFirstLineRegimen(Integer interval){

        if(interval !=null){

            if(interval > 0){
                String sql ="SELECT p.person_id  " +
                        "   FROM obs o INNER JOIN person p  " +
                        "   ON o.person_id=p.person_id  " +
                        "   WHERE o.concept_id=159599   " +
                        "   AND o.concept_id !=160562 " +
                        "   and value_datetime <= date_add(:startDate,INTERVAL " + interval + " MONTH) ";
                return sql;
            }
        }

        String sql ="SELECT p.person_id  " +
                "   FROM obs o INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE  o.concept_id=160555 AND o.concept_id=159599  " +
                "   AND o.concept_id !=160562 " +
                "   and o.value_datetime between (:startDate) and (:endDate)  " ;

        return sql;
    }

    public String patientsOnAlternativeFirstLineRegimen(Integer interval){

        if(interval !=null){

            if(interval > 0){
                String sql ="SELECT p.person_id  " +
                        "   FROM obs o INNER JOIN person p  " +
                        "   ON o.person_id=p.person_id  " +
                        "   WHERE o.concept_id=159599  " +
                        "   AND o.concept_id =160562 " +
                        "   and obs_datetime <= date_add(:startDate,INTERVAL " + interval + " MONTH) ";
                return sql;
            }
        }


        String sql ="SELECT p.person_id  " +
                "   FROM obs o INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=160555 AND o.concept_id=159599  " +
                "   AND o.concept_id =160562 " +
                "   and o.obs_datetime between (:startDate) and (:endDate)  " ;

        return sql;
    }

    public String patientsOnSecondLineOrHigher(Integer interval){

        if(interval !=null){

            if(interval > 0){
                String sql ="SELECT p.person_id  " +
                        "   FROM obs o INNER JOIN person p  " +
                        "   ON o.person_id=p.person_id  " +
                        "   WHERE o.concept_id=160568 " +
                        "   and obs_datetime <= date_add(:startDate,INTERVAL " + interval + " MONTH) ";
                return sql;
            }
        }

        String sql ="SELECT p.person_id  " +
                "   FROM obs o INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=160555 AND o.concept_id=160568 " +
                "   and o.obs_datetime between (:startDate) and (:endDate)  " ;

        return sql;
    }

    public String patientsWhoStoppedArt(Integer interval){

        if(interval !=null){

            if(interval > 0){
                String sql ="select o.person_id from obs o " +
                        "  inner join person p " +
                        "  on p.person_id=o.person_id  " +
                        "  where o.concept_id=160739 " +
                        "  and location_id in(:locationList) " +
                        "  and obs_datetime <= date_add(:startDate,INTERVAL " + interval + " MONTH) ";
                return sql;
            }
        }

        String sql ="select o.person_id from obs o " +
                "  inner join person p " +
                "  on p.person_id=o.person_id  " +
                "  where o.concept_id=160555 AND o.concept_id=160739 " +
                "  and o.location_id in(:locationList) " +
                "  and o.obs_datetime between (:startDate) and (:endDate)  ";

        return sql;
    }


    /**
     * Define Queries for Transfer Out
     * 1285;//160649;
     */
    public String patientsWhoTransferredOut(Integer interval){

        if(interval !=null){

            if(interval > 0){
                String sql ="select obs.person_id from obs " +
                        "  inner join person p " +
                        "  on p.person_id=obs.person_id  " +
                        "  where concept_id=160649 " +
                        "  and location_id in(:locationList) " +
                        "  and obs_datetime <= date_add(:startDate,INTERVAL " + interval + " MONTH) ";
                return sql;
            }
        }

        String sql ="select o.person_id from obs o" +
                "  inner join person p " +
                "  on p.person_id=o.person_id  " +
                "  where o.concept_id=160555 AND o.concept_id=160649 " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsWhoTransferredIN(Integer interval){

        if(interval !=null){

            if(interval > 0){
                String sql ="select obs.person_id from obs " +
                        "  inner join person p " +
                        "  on p.person_id=obs.person_id  " +
                        "  where concept_id=160534 " +
                        "  and location_id in(:locationList) " +
                        "  and value_datetime <= date_add(:startDate,INTERVAL " + interval + " MONTH) ";
                return sql;
            }
        }
        String sql ="select o.person_id from obs o " +
                "  inner join person p " +
                "  on p.person_id=o.person_id  " +
                "  where o.concept_id=160555 AND o.concept_id=160534 " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsWhoDied(Integer interval){

        if(interval !=null){

            if(interval > 0){
                String sql ="select obs.person_id from obs " +
                        "  inner join person p " +
                        "  on p.person_id=obs.person_id  " +
                        "  where concept_id=1543 " +
                        "  and location_id in(:locationList) " +
                        "  and obs_datetime <= date_add(:startDate,INTERVAL " + interval + " MONTH) ";
                return sql;
            }
        }

        String sql ="select o.person_id from obs o " +
                "  inner join person p " +
                "  on p.person_id=o.person_id  " +
                "  where o.concept_id=160555 AND o.concept_id=1543 " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsLostToFollowUP(Integer interval){

        if(interval !=null){

            if(interval > 0){
                String sql ="select obs.person_id from obs  " +
                        "   inner join person p  " +
                        "   on p.person_id=obs.person_id   " +
                        "   where concept_id=160555  " +
                        "   and location_id in (:locationList)  " +
                        "   and obs.person_id not in ( " +
                        "      select e.patient_id from encounter e  " +
                        "      inner join obs o on o.person_id=e.patient_id  " +
                        "      group by e.patient_id  " +
                        "      having max(e.encounter_datetime) between (:startDate) and date_add(:startDate,INTERVAL -93 DAY)  " +
                        "        union select o.person_id from obs o  " +
                        "            inner join person p  " +
                        "            on p.person_id=o.person_id  " +
                        "            where o.voided=0  " +
                        "            and concept_id=5096  " +
                        "            and value_datetime is not null  " +
                        "            and value_datetime >=(:startDate) " +
                        "   ) " +
                        "  and concept_id not in (160649,1543) " +
                        "  and value_datetime <= date_add(:startDate,INTERVAL " + interval + " MONTH) ";
                return sql;
            }
        }

        String sql ="select obs.person_id from obs  " +
                "   inner join person p  " +
                "   on p.person_id=obs.person_id   " +
                "   where concept_id=160555  " +
                "   and location_id in (:locationList)  " +
                "   and obs.person_id not in ( select person_id from person where dead=1 ) " +
                "   and obs.person_id not in ( select person_id from obs where concept_id in (1543,160649) and value_datetime <= (:startDate) ) " +
                "   and obs.person_id not in ( select o.person_id from obs o where concept_id = 5096 and value_datetime >= (:startDate) ) " +
                "   and obs.person_id not in ( select e.patient_id from encounter e group by e.patient_id having max(e.encounter_datetime) between date_add((:startDate),INTERVAL -93 DAY) and (:startDate) ) " +
                "   and value_datetime between (:startDate) and (:endDate) ";
        return sql;

    }



}