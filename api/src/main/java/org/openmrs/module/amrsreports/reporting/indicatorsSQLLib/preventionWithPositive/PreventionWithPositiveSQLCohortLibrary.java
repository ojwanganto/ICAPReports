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

package org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.preventionWithPositive;

import org.springframework.stereotype.Component;

/**
 * Library for MOH 731 Report
 */
@Component
public class PreventionWithPositiveSQLCohortLibrary {

    /**
     * Queries for tested and received test results
     * @return
     */

    public String malePatientsTestedAndReceivedTestResults(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='M' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }

    public String femalePatientsTestedAndReceivedTestResults(){
        String sql ="select obs.person_id from obs " +
                "  inner join person p " +
                "  on p.person_id=obs.person_id  " +
                "  where concept_id=160555 " +
                "  and gender='F' " +
                "  and location_id in(:locationList) " +
                "  and value_datetime between (:startDate) and (:endDate) ";

        return sql;
    }


    /*
    * Patients who disclosed status to partners
    * */
    public String malePatientsWhoDisclosedStatusToPartner(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id =159423 and o.value_coded=1065 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsWhoDisclosedStatusToPartner(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id =159423 and o.value_coded=1065 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }


    /*
   * Patients who received condoms
   * */
    public String malePatientsWhoReceivedCondoms(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=374 " +
                "   and o.value_coded =190 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsWhoReceivedCondoms(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=374 " +
                "   and o.value_coded =190 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }

    /*
 * Patients provided with modern contraceptive methods
 * */
    public String malePatientsProvidedWithModernContraceptiveMethods(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=374 " +
                "   and o.value_coded in (5279,5278,5275,5622,5276,780,78796,1472,907,1489,136452,159837,160570,136163)" +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsProvidedWithModernContraceptiveMethods(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=374 " +
                "   and o.value_coded in (5279,5278,5275,5622,5276,780,78796,1472,907,1489,136452,159837,160570,136163)" +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }

    /*
* Patients screened for STIs
* */
    public String malePatientsScreenedForSTIs(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=161558 " +
                "   and o.value_coded =1065 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsScreenedForSTIs(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=161558 " +
                "   and o.value_coded =1065 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }

    /*
* Patients provided minimum Pwp package
* */
    public String malePatientsWithPWPPackage(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=307 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='M' " ;

        return sql;
    }

    public String femalePatientsWithPWPPackage(){
        String sql ="SELECT p.person_id FROM obs o  " +
                "   INNER JOIN person p  " +
                "   ON o.person_id=p.person_id  " +
                "   WHERE o.concept_id=307 " +
                "   and o.obs_datetime BETWEEN (:startDate) AND (:endDate) " +
                "   and p.gender='F' " ;

        return sql;
    }

}