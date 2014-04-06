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

import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Program;
import org.openmrs.api.PatientSetService;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrsreports.cache.MetadataUtils;
import org.openmrs.module.amrsreports.cache.MohCacheUtils;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.EligibleForArtCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.InitialArtStartDateCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.OnAlternateFirstLineArtCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.OnArtCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.OnOriginalFirstLineArtCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.OnSecondLineArtCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.PregnantAtArtStartCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.TbPatientAtArtStartCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.WhoStageAtArtStartCalculation;
import org.openmrs.module.amrsreports.reporting.calculation.CalculationCohortDefinition;
import org.openmrs.module.amrsreports.rule.MohEvaluableNameConstants;
import org.openmrs.module.amrsreports.subcohorts.DateCalculationCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.AgeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.GenderCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * Library of Regimens
 */
@Component
public class RegimensCohortLibrary {


	private CommonICAPCohortLibrary commonCohorts = new CommonICAPCohortLibrary();

    public String getQry(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=1679 " +
                "  and obs_datetime between '2009-01-01' and '2014-01-01'";

        return sql;
    }

    public String getQryBetweenDates(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=1679 " +
                "  and location_id in(:locationList) " +
                "  and obs_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    /**
     * Patients who are female
     * @return the cohort definition
     */
    public CohortDefinition females() {
        GenderCohortDefinition cd = new GenderCohortDefinition();
        cd.setName("females");
        cd.setFemaleIncluded(true);
        return cd;
    }

    /**
     * Patients who are male
     * @return the cohort definition
     */
    public CohortDefinition males() {
        GenderCohortDefinition cd = new GenderCohortDefinition();
        cd.setName("males");
        cd.setMaleIncluded(true);
        return cd;
    }

    /**
     * Patients who at most maxAge years old on ${effectiveDate}
     * @return the cohort definition
     */
    public CohortDefinition agedAtMost(int maxAge) {
        AgeCohortDefinition cd = new AgeCohortDefinition();
        cd.setName("aged at most");
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        cd.setMaxAge(maxAge);
        return cd;
    }

    /**
     * Patients who are between x and y years old on ${effectiveDate}
     * @return the cohort definition
     */
    public CohortDefinition agedBetween(int minAge,int maxAge) {
        AgeCohortDefinition cd = new AgeCohortDefinition();
        cd.setName("aged Between");
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        cd.setMaxAge(maxAge);
        cd.setMinAge(minAge);
        return cd;
    }

    /**
     * Patients who are at least minAge years old on ${effectiveDate}
     * @return the cohort definition
     */
    public CohortDefinition agedAtLeast(int minAge) {
        AgeCohortDefinition cd = new AgeCohortDefinition();
        cd.setName("aged at least");
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        cd.setMinAge(minAge);
        return cd;
    }


    public CohortDefinition createCohortDefinition(String desc,String sqlString){

        CohortDefinition generalCOhort = new SqlCohortDefinition(sqlString);
        generalCOhort.setName(desc);

        generalCOhort.addParameter(new Parameter("startDate", "Report Date", Date.class));
        generalCOhort.addParameter(new Parameter("endDate", "End Reporting Date", Date.class));
        generalCOhort.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        return generalCOhort;
    }

    public CohortDefinition compositionAgeCohort(Integer minAge,Integer maxAge){
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.setName("Composition Cohort for age and sql cohort definitions");
        cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        cd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        cd.addParameter(new Parameter("endDate", "End Date", Date.class));

        cd.addSearch("SqlCohortDefinition",ReportUtils.map(createCohortDefinition("SqlCohortDefinition",getQryBetweenDates()),"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        cd.addSearch("AgeCohortDefinition",ReportUtils.map(agedBetween(minAge,maxAge),"effectiveDate=${endDate}"));
        cd.setCompositionString("SqlCohortDefinition AND AgeCohortDefinition");
        return cd;
    }


}