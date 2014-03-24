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

package org.openmrs.module.amrsreports.calculation.library.tb;

import org.openmrs.Concept;
import org.openmrs.Program;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.calculation.result.ObsResult;
import org.openmrs.module.amrsreports.cache.MetadataUtils;
import org.openmrs.module.amrsreports.calculation.BaseEmrCalculation;
import org.openmrs.module.amrsreports.reporting.calculation.BooleanResult;
import org.openmrs.module.amrsreports.reporting.calculation.CalculationUtils;
import org.openmrs.module.amrsreports.reporting.calculation.Calculations;
import org.openmrs.module.amrsreports.reporting.calculation.PatientFlagCalculation;
import org.openmrs.module.amrsreports.rule.MohEvaluableNameConstants;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Calculates which patients are missing TB sputum results
 */
public class MissingTbSputumResultsCalculation extends BaseEmrCalculation implements PatientFlagCalculation {

	/**
	 *
	 */
	@Override
	public String getFlagMessage() {
		return "Missing TB Sputum Results";
	}

	/**
	 * @see org.openmrs.calculation.patient.PatientCalculation#evaluate(java.util.Collection, java.util.Map, org.openmrs.calculation.patient.PatientCalculationContext)
	 * @should determine with missing sputum results
	 * patients are in tb program
	 * disease classification pulmonary tb and results either smear positive or smear negative
	 * there last encounter not having recorded sputum results
	 */
	@Override
	public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> parameterValues, PatientCalculationContext context) {
		// Get TB program
		Program tbProgram = MetadataUtils.getProgram(MohEvaluableNameConstants.ADMITTED_TO_HOSPITAL/*Metadata.Program.TB*/);

		// Get all patients who are alive and in TB program
		Set<Integer> alive = alivePatients(cohort, context);
		Set<Integer> inTbProgram = CalculationUtils.patientsThatPass(Calculations.activeEnrollment(tbProgram, alive, context));
		
		//get last disease classification
		CalculationResultMap lastDiseaseClassiffication = Calculations.lastObs(getConcept("100"/*Dictionary.SITE_OF_TUBERCULOSIS_DISEASE*/), inTbProgram, context);
		
		//get the results for pulmonary tb that is either positive or negative
		CalculationResultMap lastTbPulmonayResult = Calculations.lastObs(getConcept("200"/*Dictionary.RESULTS_TUBERCULOSIS_CULTURE*/), inTbProgram, context);
		
		//get concepts for positive and negative
		Concept smearPositive = getConcept("1065"/*Dictionary.POSITIVE*/);
		Concept smearNegative = getConcept("1066"/*Dictionary.NEGATIVE*/);
		Concept pulmonaryTb = getConcept("1067"/*Dictionary.PULMONARY_TB*/);
		
		//get the last encounter which might have recorded sputum results
		CalculationResultMap lastSputumResults = Calculations.lastObs(getConcept("1099"/*Dictionary.SPUTUM_FOR_ACID_FAST_BACILLI*/), inTbProgram, context);
		
		CalculationResultMap ret = new CalculationResultMap();
		
		for (Integer ptId : cohort) {
			
			boolean missingSputumResults = false;
			
			//get the observation results stored for disease classification and the results
			ObsResult obsResultsClassification = (ObsResult) lastDiseaseClassiffication.get(ptId);
			ObsResult obsResultspulmonry = (ObsResult) lastTbPulmonayResult.get(ptId);
			ObsResult obsResultLastSputumResults = (ObsResult) lastSputumResults.get(ptId);
			
			//make sure no null values are picked to avoid NPE error
			if (obsResultsClassification != null && obsResultspulmonry != null) {
				
				if ((obsResultsClassification.getValue().getValueCoded().equals(pulmonaryTb))
						&& (obsResultLastSputumResults == null)
						&& ((obsResultspulmonry.getValue().getValueCoded().equals(smearPositive)) 
							|| (obsResultspulmonry.getValue().getValueCoded().equals(smearNegative)))) {
					
					missingSputumResults = true;
					
				}
				
			}
			
			ret.put(ptId, new BooleanResult(missingSputumResults, this, context));
		}
		return ret;
	}


}
