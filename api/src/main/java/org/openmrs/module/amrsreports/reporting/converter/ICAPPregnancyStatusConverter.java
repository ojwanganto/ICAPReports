package org.openmrs.module.amrsreports.reporting.converter;

import org.openmrs.module.reporting.data.converter.DataConverter;

/**
 * Converter for formatting WHO Stage column data
 */

public class ICAPPregnancyStatusConverter implements DataConverter {

	@Override
	public Object convert(Object original) {
        Integer o = (Integer)original;
        if (o == null)
            return "";

            switch (o){
                case 1066:
                    return  "No";
                case 1067:
                    return "Unknown";
                case 1065:
                    return "Yes";

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
