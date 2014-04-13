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

package org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.palliativeCare;

import org.springframework.stereotype.Component;

/**
 * Library for ART Care Follow-Up Cohorts
 */
@Component
public class PalliativeCareSQLCohortLibrary {

    /**
     * Define Queries for Enrolment in palliative care
     * 160555 for date enrolled in program concept
     */
    public String malesEnrolledBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }
    public String femalesEnrolledBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String malesEnrolledAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:startDate) ";

        return sql;
    }

    public String femalesEnrolledAtStartQry(){
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
     * Define Queries for Cotrimazole
     * 162229 for Q and 1065 for YES answer
     */
    public String malesOnCotrimozaleBetweenDatesQry(){
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
    public String femalesOnCotrimozaleBetweenDatesQry(){
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

    public String malesOnCotrimozaleAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=162229 " +
                "  and value_coded=1065 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime >= (:startDate) ";

        return sql;
    }

    public String femalesOnCotrimozaleAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=162229 " +
                "  and value_coded=1065 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime <= (:startDate) ";

        return sql;
    }

}