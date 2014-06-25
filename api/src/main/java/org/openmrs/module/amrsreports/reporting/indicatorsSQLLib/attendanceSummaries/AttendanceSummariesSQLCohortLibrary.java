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

package org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.attendanceSummaries;

import org.springframework.stereotype.Component;

/**
 * Library for Daily attendance Report
 */
@Component
public class AttendanceSummariesSQLCohortLibrary {


        /**
         * new patients -- patients enrolled on care on a particular date
         */

        public String malesNewlyEnrolledOnCare(){
            String sql ="select e.patient_id from encounter e " +
                    "  inner join person p " +
                    "  on p.person_id=e.patient_id  " +
                    "  where  e.encounter_datetime = (:startDate) " +
                    "  and gender='M' " +
                    "  and e.patient_id not in (select patient_id from encounter " +
                    "  where encounter_datetime < (:startDate) )";

            return sql;
        }
        public String femalesNewlyEnrolledOnCare(){
            String sql ="select e.patient_id from encounter e " +
                    "  inner join person p " +
                    "  on p.person_id=e.patient_id  " +
                    "  where  e.encounter_datetime = (:startDate) " +
                    "  and gender='F' " +
                    "  and e.patient_id not in (select patient_id from encounter " +
                    "  where encounter_datetime < (:startDate) )";

            return sql;
        }


    /**
     * patients with revisits
     */

    public String malesWithRevisits(){
        String sql ="select e.patient_id from encounter e " +
                "  inner join person p " +
                "  on p.person_id=e.patient_id  " +
                "  where  e.encounter_datetime = (:startDate) " +
                "  and gender='M' " +
                "  and e.patient_id  in (select patient_id from encounter " +
                "  where encounter_datetime < (:startDate) )";

        return sql;
    }
    public String femalesWithRevisits(){
        String sql ="select e.patient_id from encounter e " +
                "  inner join person p " +
                "  on p.person_id=e.patient_id  " +
                "  where  e.encounter_datetime = (:startDate) " +
                "  and gender='F' " +
                "  and e.patient_id  in (select patient_id from encounter " +
                "  where encounter_datetime < (:startDate) )";

        return sql;
    }


    /**
     * patients on care
     */
    public String malesOnCare(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:startDate) ";

        return sql;
    }

    public String femalesOnCare(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:startDate) ";

        return sql;
    }

    /**
     * patients on ART
     */
    public String malesOnART(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:startDate) ";

        return sql;
    }

    public String femalesOnART(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:startDate) ";

        return sql;
    }



}