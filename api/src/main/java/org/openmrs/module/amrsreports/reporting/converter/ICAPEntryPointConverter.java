package org.openmrs.module.amrsreports.reporting.converter;

import org.openmrs.module.reporting.data.converter.DataConverter;

/**
 * Converter for formatting WHO Stage column data
 */

public class ICAPEntryPointConverter implements DataConverter {

    private static final String PMTCT = "PMTCT";
    private static final String VCT =  "VCT";
    private static final String TB =  "TB";
    private static final String ADULT_INPATIENT = "Adult Inpatient";
    private static final String PEDIATRIC_INPATIENT = "Pediatric Inpatient";
    private static final String OUTPATIENT = "Outpatient";
    private static final String CBO = "CBO";
    private static final String UNDER_FIVE = "Under Five Clinic";
    private static final String OUTREACH = "Outreach Program";
    private static final String STI_CLINIC = "STI Clinic";
    private static final String PRIVATE_COMPANY = "Private Company";
    private static final String IV_OUTREACH_PROGRAM = "Intravenous Venous Drug Use Outreach Program";
    private static final String ADOLESCENT_PROGRAM = "Adolescent Outreach Program";
    private static final String SEX_WORKER_OUTREACH_PROGRAM = "Sex Worker Outreach Program";
    private static final String SELF_REFERRAL = "Self-Referral";
    private static final String NUTRITION_PROGRAM= "Nutrition Program";
    private static final String OTHERS = "Other Non-Coded";
    private static final String VACCINATION_SERVICE = "Vaccination Service";
    private static final String PRIVATE_HOME_BASED_CARE = "Private Home-based  Care";
    private static final String MATERNAL_AND_CHILD_HEALTH_PROGRAM = "Maternal and Child Health Program";
    private static final String VMCC = "Voluntary Male Circumcision Clinic";

	@Override
	public Object convert(Object original) {
        Integer o = (Integer)original;
        if (o == null)
            return "";

            switch (o){
                case 160538:
                    return  PMTCT;
                case 160539:
                    return VCT;
                case 160541:
                    return TB;
                case 160536:
                    return ADULT_INPATIENT;
                case 160537:
                    return PEDIATRIC_INPATIENT;
                case 160542:
                    return OUTPATIENT;
                case 160543:
                    return  CBO;
                case 160544:
                    return UNDER_FIVE;
                case 160545:
                    return OUTREACH;
                case 160546:
                    return STI_CLINIC;
                case 160547:
                    return PRIVATE_COMPANY;
                case 160548:
                    return IV_OUTREACH_PROGRAM;
                case 160549:
                    return  ADOLESCENT_PROGRAM;
                case 160550:
                    return SEX_WORKER_OUTREACH_PROGRAM;
                case 160551:
                    return SELF_REFERRAL;
                case 160552:
                    return NUTRITION_PROGRAM;
                case 5622:
                    return OTHERS;
                case 160564:
                    return VACCINATION_SERVICE;
                case 161359:
                    return  PRIVATE_HOME_BASED_CARE;
                case 159937:
                    return MATERNAL_AND_CHILD_HEALTH_PROGRAM;
                case 162223:
                    return VMCC;

        }

        return null;

	}

	@Override
	public Class<?> getInputDataType() {
		return Integer.class;
	}

	@Override
	public Class<?> getDataType() {
		return String.class;
	}
}
