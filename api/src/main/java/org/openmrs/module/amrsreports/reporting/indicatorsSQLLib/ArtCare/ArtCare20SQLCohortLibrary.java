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

package org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.ArtCare;

import org.springframework.stereotype.Component;

/**
 * Library for ART Care Follow-Up Cohorts
 */
@Component
public class ArtCare20SQLCohortLibrary {

    /**
     * Define Queries for Enrolment in palliative care
     * 160555 for date enrolled in program concept
     * or 160534 for transfer in
     * or 159599 for first line regimen start date
     */
    public String malesEnrolledBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id in(160555,160534,159599) " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }
    public String femalesEnrolledBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id in(160555,160534,159599) " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String malesEnrolledAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id in(160555,160534,159599) " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:startDate) ";

        return sql;
    }

    public String femalesEnrolledAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id in(160555,160534,159599) " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:startDate) ";

        return sql;
    }


    /**
     * Define Queries for Transfers
     * 160534 for Q and value_datetime as ans
     */
    public String malesTIBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160534 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }
    public String femalesTIBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160534 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String malesTIAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160534 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime >= (:startDate) ";

        return sql;
    }

    public String femalesTIAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160534 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:startDate) ";

        return sql;
    }

    /**
     * Define Queries for patients started on first line regimens
     * 159599 for Q and value_datetime as ans
     */
    public String malesStartedOnFirstLineARTBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=159599 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }
    public String femalesStartedOnFirstLineARTBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=159599 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String malesStartedOnFirstLineARTAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=159599 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime >= (:startDate) ";

        return sql;
    }

    public String femalesStartedOnFirstLineARTAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=159599 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime <= (:startDate) ";

        return sql;
    }
}