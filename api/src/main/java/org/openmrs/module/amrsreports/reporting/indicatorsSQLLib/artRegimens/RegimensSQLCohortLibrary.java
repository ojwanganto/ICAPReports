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

package org.openmrs.module.amrsreports.reporting.indicatorsSQLLib.artRegimens;

import org.springframework.stereotype.Component;

/**
 * Library for ART Care Follow-Up Cohorts
 */
@Component
public class RegimensSQLCohortLibrary {

    public String getPatientsOnDrug(String drugName){

        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name= :drugName )"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        sql = sql.replaceAll(":drugName", drugName);

        return sql;
    }
    /**
     * Define Queries for ART Regimens
     *
     */
    public String patientsOn10A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='10A=AZT(300)+DDL(125)+IDV/r (400/100)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }
    public String patientsOn10B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='10B=AZT(300)+DDL(200)+IDV/r (400/100)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOn11B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='11B=TDF+AZT(300)+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOn1B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='1B=D4T(40)+3TC+NVP')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }



    public String patientsOn2B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='2B=D4T(40)+3TC+EFV')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }
    public String patientsOn4A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='4A=AZT(300)+DDI(125)+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOn4B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='4B=AZT(300)+DDI(200)+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOn5A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='5A=AZT(300)+DDI(125)+NFV(250)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOn5B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='5B=AZT(300)+DDI(200)+NFV(250)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOn6A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='6A=ABC(300)+DDI(125)+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOn6B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='6B=ABC(300)+DDI(200)+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOn7A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='7A=ABC(300)+DDI(125)+NFV(250)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOn7B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='7B=ABC(300)+DDI(200)+NFV(250)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOn9(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='9-AZT(300)+3TC(150)+IDV/r (400/100)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAF1A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AF1A=AZT+3TC+NVP')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAF1B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AF1B=AZT+3TC+EFV')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAF1C(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AF1C=AZT+3TC+ABC')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAF2A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AF2A=TDF+3TC+NVP')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAF2B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AF2B=TDF+3TC+EFV')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAF2C(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AF2C=TDF+3TC+AZT')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAF3A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AF3A=D4T(30)+3TC+NVP')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAF3B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AF3B=D4T(30)+3TC+EFV')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAF3C(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AF3C=d4T+3TC+ABC')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAS1A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AS1A=AZT(300)+3TC(150)+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAS1B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AS1B=AZT+3TC+ATV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAS2A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AS2A=TDF+3TC+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAS2B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AS2B=TDF+ABC+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAS2C(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AS2C=TDF+3TC+ATV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAS3A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AS3A=ABC+DDI+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnAS4A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='AS4A=d4T+3TC+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnB3B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='B3B=ABC+3TC+NVP (0-<10kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnB3C(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='B3C=ABC+3TC+NVP (10-<20kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnB3L(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='B3L=ABC+3TC+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnC1B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C1B=D4T+3TC+NVP(10-<20kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnC1C(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C1C=D4T+3TC+NVP(20-<30kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnC1D(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C1D=D4T+3TC+NVP(30-<40kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnC2C(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C2C=D4T+3TC+EFV(20-<30kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOnC2D(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C2D=D4T+3TC+EFV(30-<40kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOnC3C(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C3C=AZT+3TC+EFV (20-<30kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOnC3D(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C3D=AZT+3TC+EFV (30-<40kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOnC4B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C4B=AZT+3TC+NVP (10-<20kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOnC4C(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C4C=AZT+3TC+NVP (20-<30kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOnC4D(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C4D=AZT+3TC+NVP (30-<40kg)')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsONC5A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C5A=D4T+ABC+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOnC5B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C5B=D4T+ABC+NFV')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOnC6A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C6A=AZT+ABC+LPV/r')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }


    public String patientsOnC6B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C6B=AZT+ABC+NFV')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOnC7B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='C7B=ABC+DDI+NFV')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOnCF1A(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='CF1A=AZT+3TC+NVP')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

    public String patientsOnCF1B(){
        String sql = "SELECT a.patient_id " +
                " FROM orders a " +
                " JOIN drug_order b ON(a.order_id=b.order_id) " +
                " JOIN drug c ON(b.drug_inventory_id=c.drug_id) " +
                " JOIN cpad_regimen_drug d ON(c.drug_id=d.drug_id) " +
                " JOIN cpad_regimen e ON(d.regimen_id=e.regimen_id) " +
                " WHERE (e.name='CF1B=AZT+3TC+EFV')"+
                "  and a.date_created between (:startDate) and (:endDate) ";

        return sql;
    }

}