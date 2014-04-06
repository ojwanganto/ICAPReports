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

package org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.artCareFollowup;

import org.openmrs.Location;
import org.openmrs.module.amrsreports.reporting.CommonICAPCohortLibrary;
import org.openmrs.module.amrsreports.reporting.ReportUtils;
import org.openmrs.module.reporting.cohort.definition.AgeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.GenderCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Library for ART Care Follow-Up Cohorts
 */
@Component
public class ArtCareSQLCohortLibrary {

    /**
     * Define Queries for ART Stop date
     * 1679;//160739;
     */
    public String malesWhoStoppedArtBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=1679 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime between (:startDate) and (:endDate) ";

        return sql;
    }
    public String femalesWhoStoppedArtBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=1679 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String malesWhoStoppedArtAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=1679 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime <= (:startDate) ";

        return sql;
    }

    public String femalesWhoStoppedArtAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=1679 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime <= (:startDate) ";

        return sql;
    }


    /**
     * Define Queries for Transfer Out
     * 1285;//160649;
     */
    public String malesWhoTOBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=1285 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime between (:startDate) and (:endDate) ";

        return sql;
    }
    public String femalesWhoTOBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=1285 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String malesWhoTOAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=1285 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime >= (:startDate) ";

        return sql;
    }

    public String femalesWhoTOAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=1285 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime <= (:startDate) ";

        return sql;
    }


    /**
     * Define Queries for Death concept
     * 159;//1543;
     */
    public String malesWhoDEADBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=159 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime between (:startDate) and (:endDate) ";

        return sql;
    }
    public String femalesWhoDEADBetweenDatesQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=159 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String malesWhoDEADAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=159 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime >= (:startDate) ";

        return sql;
    }

    public String femalesWhoDEADAtStartQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=159 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime <= (:startDate) ";

        return sql;
    }
}