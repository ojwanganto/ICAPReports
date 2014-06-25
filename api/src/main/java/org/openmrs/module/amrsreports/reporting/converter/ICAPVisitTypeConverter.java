package org.openmrs.module.amrsreports.reporting.converter;

import org.openmrs.module.reporting.data.converter.DataConverter;

/**
 * Converter for formatting WHO Stage column data
 */

public class ICAPVisitTypeConverter implements DataConverter {

	@Override
	public Object convert(Object original) {
        Integer o = (Integer)original;
        if (o == null)
            return "New";

            switch (o){
                case 160523:
                    return  "Follow-Up";
                case 160446:
                    return "Antenatal";
                case 160528:
                    return "PMTCT";
                case 160527:
                    return "Kaposi Sarcoma";
                case 159893:
                    return "Post-Natal";
                case 160524:
                    return "ART";
                case 160521:
                    return "Pharmacy";
                case 160522:
                    return "Emergency";
                case 160526:
                    return "Early Infant Diagnosis";
                case 160525:
                    return "Pre-ART";
                case 160529:
                    return "TB";
        }

        return "New";

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
