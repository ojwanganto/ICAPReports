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

package org.openmrs.module.amrsreports.calculation.library.mchms;

import org.openmrs.Concept;
import org.openmrs.Program;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.module.amrsreports.cache.MetadataUtils;
import org.openmrs.module.amrsreports.calculation.BaseEmrCalculation;
import org.openmrs.module.amrsreports.calculation.EmrCalculationUtils;
import org.openmrs.module.amrsreports.reporting.calculation.BooleanResult;
import org.openmrs.module.amrsreports.reporting.calculation.CalculationUtils;
import org.openmrs.module.amrsreports.reporting.calculation.Calculations;
import org.openmrs.module.amrsreports.reporting.calculation.PatientFlagCalculation;
import org.openmrs.module.amrsreports.rule.MohEvaluableNameConstants;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Calculates whether a mother has a HIV+ or HIV- HIV status result. Calculation returns true if mother
 * is alive, enrolled in the MCH program and her HIV status is indicated as Not Tested.
 */
public class NotHivTestedCalculation extends BaseEmrCalculation implements PatientFlagCalculation {

	/**
	 * @see
	 */
	@Override
	public String getFlagMessage() {
		return "Not HIV Tested";
	}

	@Override
	public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> parameterValues, PatientCalculationContext context) {

		Program mchmsProgram = MetadataUtils.getProgram(MohEvaluableNameConstants.ADMITTED_TO_HOSPITAL/*Metadata.Program.MCHMS*/);

		Set<Integer> alive = alivePatients(cohort, context);
		Set<Integer> inMchmsProgram = CalculationUtils.patientsThatPass(Calculations.activeEnrollment(mchmsProgram, alive, context));

		CalculationResultMap lastHivStatusObss = Calculations.lastObs(getConcept(MohEvaluableNameConstants.ADMITTED_TO_HOSPITAL/*Dictionary.HIV_STATUS*/), inMchmsProgram, context);

		CalculationResultMap ret = new CalculationResultMap();
		for (Integer ptId : cohort) {
			boolean notHivTested = false;

			// Is patient alive and in MCH program?
			if (inMchmsProgram.contains(ptId)) {
				Concept lastHivStatus = EmrCalculationUtils.codedObsResultForPatient(lastHivStatusObss, ptId);
				if (lastHivStatus != null) {
					notHivTested = lastHivStatus.equals("1234"/*Dictionary.getConcept(Dictionary.NOT_HIV_TESTED)*/);
				}
			}
			ret.put(ptId, new BooleanResult(notHivTested, this, context));
		}
		return ret;
	}
}
