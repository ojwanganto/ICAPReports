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

package org.openmrs.module.amrsreports.reporting.indicatorsSQLLib;

import org.openmrs.Location;
import org.openmrs.module.amrsreports.reporting.CommonICAPCohortLibrary;
import org.openmrs.module.amrsreports.reporting.ReportUtils;
import org.openmrs.module.reporting.cohort.definition.AgeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.GenderCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.DurationUnit;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Library of Regimens
 */
@Component
public class BaseSQLCohortLibrary {

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

    public CohortDefinition agedBetweenInMonths(int minAge,int maxAge) {
        AgeCohortDefinition cd = new AgeCohortDefinition();
        cd.setName("aged Between");
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        cd.setMaxAge(maxAge);
        cd.setMinAge(minAge);
        cd.setMaxAgeUnit(DurationUnit.MONTHS);
        cd.setMinAgeUnit(DurationUnit.MONTHS);
        return cd;
    }

    public CohortDefinition agedMinInMonths(int minAge) {
        AgeCohortDefinition cd = new AgeCohortDefinition();
        cd.setName("aged Between");
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));

        cd.setMinAge(minAge);
        cd.setMinAgeUnit(DurationUnit.MONTHS);
        return cd;
    }

    public CohortDefinition agedMaxInMonths(int maxAge) {
        AgeCohortDefinition cd = new AgeCohortDefinition();
        cd.setName("aged Between");
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        cd.setMaxAge(maxAge);
        cd.setMaxAgeUnit(DurationUnit.MONTHS);
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

    public CohortDefinition compositionAgeCohort(Integer minAge,Integer maxAge,String qryString){
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.setName("Composition Cohort for age and sql cohort definitions");
        cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        cd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        cd.addParameter(new Parameter("endDate", "End Date", Date.class));

        cd.addSearch("SqlCohortDefinition",ReportUtils.map(createCohortDefinition("SqlCohortDefinition",qryString),"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        cd.addSearch("AgeCohortDefinition",ReportUtils.map(agedBetween(minAge,maxAge),"effectiveDate=${endDate}"));
        cd.setCompositionString("SqlCohortDefinition AND AgeCohortDefinition");
        return cd;
    }
    public CohortDefinition compositionAgeInMonthsCohort(Integer minAge,Integer maxAge,String qryString){
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.setName("Composition Cohort for age and sql cohort definitions");
        cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        cd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        cd.addParameter(new Parameter("endDate", "End Date", Date.class));

        cd.addSearch("SqlCohortDefinition",ReportUtils.map(createCohortDefinition("SqlCohortDefinition",qryString),"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        cd.addSearch("AgeCohortDefinition",ReportUtils.map(agedBetweenInMonths(minAge,maxAge),"effectiveDate=${endDate}"));
        cd.setCompositionString("SqlCohortDefinition AND AgeCohortDefinition");
        return cd;
    }

    public CohortDefinition compositionMaxAgeCohort(Integer maxAge,String qryString){
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.setName("Composition Cohort for age and sql cohort definitions");
        cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        cd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        cd.addParameter(new Parameter("endDate", "End Date", Date.class));

        cd.addSearch("SqlCohortDefinition",ReportUtils.map(createCohortDefinition("SqlCohortDefinition",qryString),"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        cd.addSearch("AgeCohortDefinition",ReportUtils.map(agedAtMost(maxAge),"effectiveDate=${endDate}"));
        cd.setCompositionString("SqlCohortDefinition AND AgeCohortDefinition");
        return cd;
    }

    public CohortDefinition compositionMinAgeCohort(Integer minAge,String qryString){
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.setName("Composition Cohort for age and sql cohort definitions");
        cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        cd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        cd.addParameter(new Parameter("endDate", "End Date", Date.class));

        cd.addSearch("SqlCohortDefinition",ReportUtils.map(createCohortDefinition("SqlCohortDefinition",qryString),"startDate=${startDate},locationList=${locationList},endDate=${endDate}"));
        cd.addSearch("AgeCohortDefinition",ReportUtils.map(agedAtLeast(minAge),"effectiveDate=${endDate}"));
        cd.setCompositionString("SqlCohortDefinition AND AgeCohortDefinition");
        return cd;
    }


}