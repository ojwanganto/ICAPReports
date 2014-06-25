package org.openmrs.module.amrsreports.reporting.converter;

import org.openmrs.module.reporting.data.converter.DataConverter;

/**
 * Converter for formatting WHO Stage column data
 */

public class ICAPFirstWHOStageConverter implements DataConverter {

    private static final String WHO_STAGE_1 = "WHO Stage 1";
    private static final String WHO_STAGE_2 =  "WHO Stage 2";
    private static final String WHO_STAGE_3 =  "WHO Stage 3";
    private static final String WHO_STAGE_4 = "WHO Stage 4";


    @Override
    public Object convert(Object original) {
        Integer o = (Integer)original;
        if (o == null)
            return "";

        switch (o){

            case 1204:
                return  WHO_STAGE_1;
            case 1205:
                return WHO_STAGE_2;
            case 1206:
                return WHO_STAGE_3;
            case 1207:
                return WHO_STAGE_4;
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
