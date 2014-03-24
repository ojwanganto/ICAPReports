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

package org.openmrs.module.amrsreports.reporting;

import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Program;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Library of common indicator definitions. All indicators require parameters ${startDate} and ${endDate}
 */
@Component
public class CommonIndicatorLibrary {


	//CommonCohortLibrary commonCohorts = new CommonCohortLibrary();

	/**
	 * Number of patients enrolled in the given program (including transfers)
	 * @param program the program
	 * @return the indicator
	 */
	/*public CohortIndicator enrolled(Program program) {
		return createCohortIndicator("Number of new patients enrolled in " + program.getName() + " including transfers",
				ReportUtils.map(commonCohorts.enrolledExcludingTransfers(program), "onOrAfter=${startDate},onOrBefore=${endDate}"));
	}
*/
	/**
	 * Number of patients enrolled in the given program (excluding transfers)
	 * @param program the program
	 * @return the indicator
	 */
	/*public CohortIndicator enrolledExcludingTransfers(Program program) {
		return createCohortIndicator("Number of new patients enrolled in " + program.getName() + " excluding transfers",
				ReportUtils.map(commonCohorts.enrolledExcludingTransfers(program), "onOrAfter=${startDate},onOrBefore=${endDate}"));
	}*/

	/**
	 * Number of patients ever enrolled in the given program (including transfers) up to ${endDate}
	 * @param program the program
	 * @return the indicator
	 */
	/*public CohortIndicator enrolledCumulative(Program program) {
		return createCohortIndicator("Number of patients ever enrolled in " + program.getName() + " excluding transfers",
				ReportUtils.map(commonCohorts.enrolled(program), "enrolledOnOrBefore=${endDate}"));
	}*/

	/**
	 * Number of patients on the specified medication
	 * @param concepts the drug concepts
	 * @return the indicator
	 */
	/*public CohortIndicator onMedication(Concept... concepts) {
		return createCohortIndicator("Number of patients on medication", ReportUtils.map(commonCohorts.onMedication(concepts), "onDate=${endDate}"));
	}*/

	/**
	 * Utility method to create a new cohort indicator
	 * @param description the indicator description
	 * @return the cohort indicator
	 */
    public static CohortIndicator createCohortIndicator(String description, Mapped<CohortDefinition> mappedCohort) {
        CohortIndicator ind = new CohortIndicator(description);
        ind.addParameter(new Parameter("startDate", "Start Date", Date.class));
        ind.addParameter(new Parameter("endDate", "End Date", Date.class));
        ind.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        ind.setType(CohortIndicator.IndicatorType.COUNT);
        ind.setCohortDefinition(mappedCohort);
        return ind;
    }

    public static CohortIndicator createCohortIndicatorAtStart(String description, CohortDefinition  mappedCohort) {
        CohortIndicator ind = new CohortIndicator(description);
        ind.addParameter(new Parameter("startDate", "Start Date", Date.class));
        ind.addParameter(new Parameter("endDate", "End Date", Date.class));
        ind.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        ind.setType(CohortIndicator.IndicatorType.COUNT);
        ind.setCohortDefinition(mappedCohort, "effectiveDate=${startDate},locationList=${locationList},onOrBefore=${startDate}");
        return ind;
    }

    public static CohortIndicator createCohortIndicatorAtEnd(String description, CohortDefinition mappedCohort) {
        CohortIndicator ind = new CohortIndicator(description);
        ind.addParameter(new Parameter("startDate", "Start Date", Date.class));
        ind.addParameter(new Parameter("endDate", "End Date", Date.class));
        ind.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        ind.setType(CohortIndicator.IndicatorType.COUNT);
        ind.setCohortDefinition(mappedCohort, "effectiveDate=${endDate},locationList=${locationList},onOrBefore=${endDate}");
        return ind;
    }

    private void sampleIndicatorReport(){
        /*GenderCohortDefinition malesCohort = new GenderCohortDefinition();
		malesCohort.setName("Gender = Male");
		malesCohort.setMaleIncluded(true);

		GenderCohortDefinition femalesCohort = new GenderCohortDefinition();
		femalesCohort.setName("Gender = Female");
		femalesCohort.setFemaleIncluded(true);

		AgeCohortDefinition pedsCohort = new AgeCohortDefinition();
		pedsCohort.setName("Age < 15");
		pedsCohort.addParameter(new Parameter("effectiveDate", "Date", Date.class));
		pedsCohort.setMaxAge(14);

		CohortIndicator malesIndicator = new CohortIndicator("Count of males");
		malesIndicator.setCohortDefinition(malesCohort, "");

		CohortIndicator femalesIndicator = new CohortIndicator("Count of females");
		femalesIndicator.setCohortDefinition(femalesCohort, "");

		CohortIndicator pedsIndicator = new CohortIndicator("Count of peds");
		pedsIndicator.addParameter(new Parameter("date", "Date", Date.class));
		pedsIndicator.setCohortDefinition(pedsCohort, "effectiveDate=${date}");

		cohortDsd1 = new CohortIndicatorDataSetDefinition();
		cohortDsd1.setName("Cohort DSD1");
		cohortDsd1.addColumn("test-1", "Count of males", new Mapped<CohortIndicator>(malesIndicator, null), "");
		cohortDsd1.addColumn("test-3", "Count of peds", new Mapped<CohortIndicator>(pedsIndicator, null), "");

		cohortDsd2 = new CohortIndicatorDataSetDefinition();
		cohortDsd2.setName("Cohort DSD2");
		cohortDsd1.addColumn("test-2", "Count of females", new Mapped<CohortIndicator>(femalesIndicator, null), "");

		evaluationContext = new EvaluationContext();
		evaluationContext.addParameterValue("date", TestUtils.date(2012, 1, 1));
		evaluationContext.setBaseCohort(new Cohort(Context.getPatientService().getAllPatients()));





		==================================================================================================
        AgeCohortDefinition lessThanOne = new AgeCohortDefinition();
		lessThanOne.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
		lessThanOne.setMaxAge(1);

		CohortIndicator lessThanOneAtStart = new CohortIndicator();
		lessThanOneAtStart.addParameter(ReportingConstants.START_DATE_PARAMETER);
		lessThanOneAtStart.addParameter(ReportingConstants.END_DATE_PARAMETER);
		lessThanOneAtStart.setUuid(UUID.randomUUID().toString());
		Map<String, Object> mappings = new HashMap<String, Object>();
		mappings.put("effectiveDate", "${startDate}");
		lessThanOneAtStart.setCohortDefinition(lessThanOne, mappings);

		Map<String, Object> periodMappings = new HashMap<String, Object>();
		periodMappings.put("startDate", "${startDate}");
		periodMappings.put("endDate", "${endDate}");
		periodMappings.put("location", "${location}");




		==================================================================================================

		*/
    }
}