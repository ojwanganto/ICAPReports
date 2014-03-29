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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Program;
import org.openmrs.api.PatientSetService;
import org.openmrs.module.reporting.cohort.definition.AgeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.GenderCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.ProgramEnrollmentCohortDefinition;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.common.TimeQualifier;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;


/**
 * Library of common cohort definitions
 */
@Component
public class CommonSQLCohortLibrary {

    private final Log log = LogFactory.getLog(this.getClass());

    public String malesWithMinAge(Integer minAge){
        String sql ="SELECT person_id,birthdate, YEAR(:reportDate) - YEAR(birthdate) - " +
                "  (CASE WHEN " +
                "    MONTH(birthdate) > MONTH(:reportDate) OR " +
                "    (MONTH(birthdate) = MONTH(:reportDate) AND DAY(birthdate) > DAY(:reportDate)) " +
                "      THEN 1 " +
                "      ELSE 0 " +
                "  END) as age from person " +
                "  INNER JOIN encounter ON encounter.patient_id = person.person_id " +
                "  where gender= 'M' " +
                "  and encounter.location_id in ( :locationList ) " +
                "  and encounter.encounter_datetime <= :reportDate " +
                "  and birthdate is not null " +
                "  having age >= :minAge ";

        String hql = sql.replaceAll(":minAge", String.valueOf(minAge));
        log.info("This is the sql: "+sql);
        return hql;
    }


    public String malesWithMaxAge(Integer maxAge){
        String sql ="SELECT person_id,birthdate, YEAR(:reportDate) - YEAR(birthdate) - " +
                "  (CASE WHEN " +
                "    MONTH(birthdate) > MONTH(:reportDate) OR " +
                "    (MONTH(birthdate) = MONTH(:reportDate) AND DAY(birthdate) > DAY(:reportDate)) " +
                "      THEN 1 " +
                "      ELSE 0 " +
                "  END) as age from person " +
                "  INNER JOIN encounter ON encounter.patient_id = person.person_id " +
                "  where gender= 'M' " +
                "  and encounter.location_id in ( :locationList ) " +
                "  and encounter.encounter_datetime <= :reportDate " +
                "  and birthdate is not null " +
                "  having age <= :maxAge ";

        String hql = sql.replaceAll(":maxAge", String.valueOf(maxAge));
        log.info("This is the sql: "+sql);
        return hql;
    }

    public String malesWithAgeOfRange(Integer minAge,Integer maxAge){
        String sql ="SELECT person_id,birthdate, YEAR(:reportDate) - YEAR(birthdate) - " +
                "  (CASE WHEN " +
                "    MONTH(birthdate) > MONTH(:reportDate) OR " +
                "    (MONTH(birthdate) = MONTH(:reportDate) AND DAY(birthdate) > DAY(:reportDate)) " +
                "      THEN 1 " +
                "      ELSE 0 " +
                "  END) as age from person " +
                "  INNER JOIN encounter ON encounter.patient_id = person.person_id " +
                "  where gender= 'M' " +
                "  and encounter.location_id in ( :locationList ) " +
                "  and encounter.encounter_datetime <= :reportDate " +
                "  and birthdate is not null " +
                "  having age >= :minAge and age <= :maxAge ";

        sql.replaceAll(":minAge", String.valueOf(minAge));
        sql.replaceAll(":maxAge", String.valueOf(maxAge));
        log.info("This is the sql: "+sql);
        return sql;
    }

    public String malesWithAgeOfRangeBetweenAndEncountersBetweenDates(Integer minAge,Integer maxAge){
        String sql ="SELECT person_id,birthdate, YEAR(:reportDate) - YEAR(birthdate) - " +
                "  (CASE WHEN " +
                "    MONTH(birthdate) > MONTH(:reportDate) OR " +
                "    (MONTH(birthdate) = MONTH(:reportDate) AND DAY(birthdate) > DAY(:reportDate)) " +
                "      THEN 1 " +
                "      ELSE 0 " +
                "  END) as age from person " +
                "  INNER JOIN encounter ON encounter.patient_id = person.person_id " +
                "  where gender= 'M' " +
                "  and encounter.location_id in ( :locationList ) " +
                "  and encounter.encounter_datetime BETWEEN (:reportDate AND :endDate) " +
                "  and birthdate is not null " +
                "  having age >= :minAge and age <= :maxAge ";

        sql.replaceAll(":minAge", String.valueOf(minAge));
        sql.replaceAll(":maxAge", String.valueOf(maxAge));
        log.info("This is the sql: "+sql);
        return sql;
    }

    /**
     * define cohorts for females
     * @return
     */
    public String femalesWithMinAge(Integer minAge){
        String sql ="SELECT person_id,birthdate, YEAR(:reportDate) - YEAR(birthdate) - " +
                "  (CASE WHEN " +
                "    MONTH(birthdate) > MONTH(:reportDate) OR " +
                "    (MONTH(birthdate) = MONTH(:reportDate) AND DAY(birthdate) > DAY(:reportDate)) " +
                "      THEN 1 " +
                "      ELSE 0 " +
                "  END) as age from person " +
                "  INNER JOIN encounter ON encounter.patient_id = person.person_id " +
                "  where gender= 'F' " +
                "  and encounter.location_id in ( :locationList ) " +
                "  and encounter.encounter_datetime <= :reportDate " +
                "  and birthdate is not null " +
                "  having age >= :minAge ";

        String hql = sql.replaceAll(":minAge", String.valueOf(minAge));
        log.info("This is the sql: "+sql);
        return hql;
    }


    public String femalesWithMaxAge(Integer maxAge){
        String sql ="SELECT person_id,birthdate, YEAR(:reportDate) - YEAR(birthdate) - " +
                "  (CASE WHEN " +
                "    MONTH(birthdate) > MONTH(:reportDate) OR " +
                "    (MONTH(birthdate) = MONTH(:reportDate) AND DAY(birthdate) > DAY(:reportDate)) " +
                "      THEN 1 " +
                "      ELSE 0 " +
                "  END) as age from person " +
                "  INNER JOIN encounter ON encounter.patient_id = person.person_id " +
                "  where gender= 'F' " +
                "  and encounter.location_id in ( :locationList ) " +
                "  and encounter.encounter_datetime <= :reportDate " +
                "  and birthdate is not null " +
                "  having age <= :maxAge ";

        String hql = sql.replaceAll(":maxAge", String.valueOf(maxAge));
        log.info("This is the sql: "+sql);
        return hql;
    }

    public String femalesWithAgeOfRange(Integer minAge,Integer maxAge){
        String sql ="SELECT person_id,birthdate, YEAR(:reportDate) - YEAR(birthdate) - " +
                "  (CASE WHEN " +
                "    MONTH(birthdate) > MONTH(:reportDate) OR " +
                "    (MONTH(birthdate) = MONTH(:reportDate) AND DAY(birthdate) > DAY(:reportDate)) " +
                "      THEN 1 " +
                "      ELSE 0 " +
                "  END) as age from person " +
                "  INNER JOIN encounter ON encounter.patient_id = person.person_id " +
                "  where gender= 'F' " +
                "  and encounter.location_id in ( :locationList ) " +
                "  and encounter.encounter_datetime <= :reportDate " +
                "  and birthdate is not null " +
                "  having age >= :minAge and age <= :maxAge ";

        sql.replaceAll(":minAge", String.valueOf(minAge));
        sql.replaceAll(":maxAge", String.valueOf(maxAge));
        log.info("This is the sql: "+sql);
        return sql;
    }

    public String femalesWithAgeOfRangeBetweenAndEncountersBetweenDates(Integer minAge,Integer maxAge){
        String sql ="SELECT person_id,birthdate, YEAR(:reportDate) - YEAR(birthdate) - " +
                "  (CASE WHEN " +
                "    MONTH(birthdate) > MONTH(:reportDate) OR " +
                "    (MONTH(birthdate) = MONTH(:reportDate) AND DAY(birthdate) > DAY(:reportDate)) " +
                "      THEN 1 " +
                "      ELSE 0 " +
                "  END) as age from person " +
                "  INNER JOIN encounter ON encounter.patient_id = person.person_id " +
                "  where gender= 'F' " +
                "  and encounter.location_id in ( :locationList ) " +
                "  and encounter.encounter_datetime BETWEEN (:reportDate AND :endDate) " +
                "  and birthdate is not null " +
                "  having age >= :minAge and age <= :maxAge ";

        sql.replaceAll(":minAge", String.valueOf(minAge));
        sql.replaceAll(":maxAge", String.valueOf(maxAge));
        log.info("This is the sql: "+sql);
        return sql;
    }

    public String all (){
        String hql ="SELECT person_id,birthdate, YEAR(:reportDate) - YEAR(birthdate) - " +
                "  (CASE WHEN " +
                "    MONTH(birthdate) > MONTH(:reportDate) OR " +
                "    (MONTH(birthdate) = MONTH(:reportDate) AND DAY(birthdate) > DAY(:reportDate)) " +
                "      THEN 1 " +
                "      ELSE 0 " +
                "  END) as age from person " +
                "  INNER JOIN encounter ON encounter.patient_id = person.person_id " +
                "  where gender= :gender " +
                "  and encounter.location_id in ( :locationList ) " +
                "  and encounter.encounter_datetime <= :reportDate " +
                "  and birthdate is not null " +
                "  having age >= :minAge ";

        return hql;
    }

}