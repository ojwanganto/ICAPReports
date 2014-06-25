package org.openmrs.module.amrsreports.reporting.data;

import org.openmrs.module.reporting.common.Age;
import org.openmrs.module.reporting.data.BaseDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonDataDefinition;
import org.openmrs.module.reporting.definition.configuration.ConfigurationPropertyCachingStrategy;
import org.openmrs.module.reporting.evaluation.caching.Caching;

/**
 * Evaluates age of a person at the date of report evaluation, provided by the context
 */
@Caching(strategy=ConfigurationPropertyCachingStrategy.class)
public class ICAPEntryPointDataDefinition extends BaseDataDefinition implements PersonDataDefinition {

	public static final long serialVersionUID = 1L;


	/**
	 * @see org.openmrs.module.reporting.data.DataDefinition#getDataType()
	 */
	public Class<?> getDataType() {
		return Integer.class;
	}

}
