package org.openmrs.module.amrsreports.reporting.data;

import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.module.amrsreports.MOHFacility;
import org.openmrs.module.reporting.data.BaseDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonDataDefinition;
import org.openmrs.module.reporting.definition.configuration.ConfigurationPropertyCachingStrategy;
import org.openmrs.module.reporting.evaluation.caching.Caching;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;

import java.util.Date;

/**
 * The last Encounter at a facility
 */
@Caching(strategy=ConfigurationPropertyCachingStrategy.class)
public class FirstEncounterAtFacilityDataDefinition extends BaseDataDefinition implements PersonDataDefinition {


	@Override
	public Class<?> getDataType() {
		return Date.class;
	}
}
