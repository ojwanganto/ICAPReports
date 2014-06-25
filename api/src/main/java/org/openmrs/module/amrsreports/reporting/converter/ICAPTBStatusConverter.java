package org.openmrs.module.amrsreports.reporting.converter;

import org.openmrs.module.amrsreports.cache.MohCacheUtils;
import org.openmrs.module.amrsreports.reporting.common.ObsRepresentation;
import org.openmrs.module.amrsreports.rule.MohEvaluableNameConstants;
import org.openmrs.module.reporting.data.converter.DataConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Converter for formatting WHO Stage column data
 */

public class ICAPTBStatusConverter implements DataConverter {

    private static final String COMPLETED = "Completed";
    private static final String SUSPECT =  "Suspect";
    private static final String DIAGNOSED =  "Diagnosed";
    private static final String NO_SIGNS = "No Signs";
    private static final String ON_TREATMENT = "On Treatment";
    private static final String NOT_ASSESSED = "Not Assessed";

    @Override
    public Object convert(Object original) {
        Integer o = (Integer)original;
        if (o == null)
            return "";

        switch (o){

            case 1663:
                return  COMPLETED;
            case 142177:
                return SUSPECT;
            case 1661:
                return DIAGNOSED;
            case 1660:
                return NO_SIGNS;
            case 1662:
                return ON_TREATMENT;
            case 160737:
                return NOT_ASSESSED;
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
