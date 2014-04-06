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
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Program;
import org.openmrs.api.PatientSetService;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrsreports.cache.MetadataUtils;
import org.openmrs.module.amrsreports.cache.MohCacheUtils;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.EligibleForArtCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.InitialArtStartDateCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.OnAlternateFirstLineArtCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.OnArtCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.OnOriginalFirstLineArtCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.OnSecondLineArtCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.PregnantAtArtStartCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.TbPatientAtArtStartCalculation;
import org.openmrs.module.amrsreports.calculation.library.hiv.art.WhoStageAtArtStartCalculation;
import org.openmrs.module.amrsreports.reporting.CommonICAPCohortLibrary;
import org.openmrs.module.amrsreports.reporting.ReportUtils;
import org.openmrs.module.amrsreports.reporting.calculation.CalculationCohortDefinition;
import org.openmrs.module.amrsreports.rule.MohEvaluableNameConstants;
import org.openmrs.module.amrsreports.subcohorts.DateCalculationCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * Library of ART care follow up related cohort definitions
 */
@Component
public class ArtCareFollowUpCohortLibrary {


	private CommonICAPCohortLibrary commonCohorts = new CommonICAPCohortLibrary();

    /**
     * Patients who have stopped from care
     * @return the cohort definition
     */
    public CohortDefinition stoppedARTCohort() {
        Concept artStopDate = Context.getConceptService().getConcept(160739);
        CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
        cd.setName("Stopped ART Care");
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
        cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
        cd.setQuestion(artStopDate);
        return cd;

    }

    /**
     * Patients who have transferred out from care
     * @return the cohort definition
     */
    public CohortDefinition transferOutCohort() {
        Concept transferOut = Context.getConceptService().getConcept(160649);
        CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
        cd.setName("Transfered out of Care");
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
        cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
        cd.setQuestion(transferOut);
        return cd;

    }

    /**
     * Patients who have transferred out from care
     * @return the cohort definition
     */
    public CohortDefinition deceasedCohort() {
        Concept dateOfDeath = Context.getConceptService().getConcept(1543);
        CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
        cd.setName("Transfered out of Care");
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
        cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
        cd.setQuestion(dateOfDeath);
        return cd;

    }

    //====================================================
    /**
     * Males above a given limit of age at a facility
     */
    public CohortDefinition malesAboveAgeWithConcepts(int minAge,Concept question, Concept... answers){
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        cd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.addSearch("HasObs",ReportUtils.map(commonCohorts.hasObs(question, answers),"onOrBefore=${onOrBefore},onOrAfter=${effectiveDate}"));
        cd.addSearch("malesAtFacility",ReportUtils.map(commonCohorts.malesAgedAtLeastAtFacility(minAge),"effectiveDate=${onOrBefore},locationList=${locationList},onOrBefore=${onOrBefore}"));
        cd.setCompositionString("HasObs AND malesAtFacility");
        return cd;
    }

    /**
     * Males Below a given limit of age at a facility
     */
    public CohortDefinition malesBelowAgeWithConcepts(int maxAge,Concept question, Concept... answers){
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        cd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.addSearch("HasObs",ReportUtils.map(commonCohorts.hasObs(question, answers),"onOrBefore=${onOrBefore},onOrAfter=${effectiveDate}"));
        cd.addSearch("malesAtFacility",ReportUtils.map(commonCohorts.malesAgedAtMostAtFacility(maxAge),"effectiveDate=${effectiveDate},locationList=${locationList},onOrBefore=${onOrBefore}"));
        cd.setCompositionString("HasObs AND malesAtFacility");
        return cd;
    }

    /**
     * Males with a given range of age at a facility
     */
    public CohortDefinition malesBetweenAgeWithConcepts(int minAge,int maxAge,Concept question, Concept... answers){
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        cd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.addSearch("HasObs",ReportUtils.map(commonCohorts.hasObs(question, answers),"onOrBefore=${onOrBefore},onOrAfter=${effectiveDate}"));
        cd.addSearch("malesAtFacility",ReportUtils.map(commonCohorts.malesAgedBetweenAtFacility(minAge, maxAge),"effectiveDate=${effectiveDate},locationList=${locationList},onOrBefore=${onOrBefore}"));
        cd.setCompositionString("HasObs AND malesAtFacility");
        return cd;
    }

    //---------------------------------------------------------------------
    /**
     * Females with a minimum age limit at a facility
     */
    public CohortDefinition femalesAboveAgeWithConcepts(int minAge,Concept question, Concept... answers){
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        cd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.addSearch("HasObs",ReportUtils.map(commonCohorts.hasObs(question, answers),"onOrBefore=${onOrBefore},onOrAfter=${effectiveDate}"));
        cd.addSearch("femalesAtFacility",ReportUtils.map(commonCohorts.femalesAgedAtLeastAtFacility(minAge),"effectiveDate=${effectiveDate},locationList=${locationList},onOrBefore=${onOrBefore}"));
        cd.setCompositionString("HasObs AND femalesAtFacility");
        return cd;
    }

    /**
     * Females below a given age limit at a facility
     */
    public CohortDefinition femalesBelowAgeWithConcepts(int maxAge,Concept question, Concept... answers){
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        cd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.addSearch("HasObs",ReportUtils.map(commonCohorts.hasObs(question, answers),"onOrBefore=${onOrBefore},onOrAfter=${effectiveDate}"));
        cd.addSearch("femalesAtFacility",ReportUtils.map(commonCohorts.femalesAgedAtMostAtFacility(maxAge),"effectiveDate=${effectiveDate},locationList=${locationList},onOrBefore=${onOrBefore}"));
        cd.setCompositionString("HasObs AND femalesAtFacility");
        return cd;
    }

    /**
     * Females of a given range of age at a facility
     */
    public CohortDefinition femalesBetweenAgeWithConcepts(int minAge,int maxAge,Concept question, Concept... answers){
        CompositionCohortDefinition cd = new CompositionCohortDefinition();
        cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        cd.addParameter(new Parameter("locationList", "List of Locations", Location.class));
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.addSearch("HasObs",ReportUtils.map(commonCohorts.hasObs(question, answers),"onOrBefore=${onOrBefore},onOrAfter=${effectiveDate}"));
        cd.addSearch("femalesAtFacility",ReportUtils.map(commonCohorts.femalesAgedBetweenAtFacility(minAge, maxAge),"effectiveDate=${effectiveDate},locationList=${locationList},onOrBefore=${onOrBefore}"));
        cd.setCompositionString("HasObs AND femalesAtFacility");
        return cd;
    }





    //====================================================
    /**
     * Patients who have died
     * @return the cohort definition
     */
    public CohortDefinition causeOfDeath() {

        Concept concept = MohCacheUtils.getConcept(MohEvaluableNameConstants.CAUSE_FOR_DEATH);
        CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
        cd.setName("Cause of Death Cohort Definition");
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
        cd.setQuestion(concept);
        return cd;
    }

    /**
     * Patients who have died
     * @return the cohort definition
     */
    public CohortDefinition deathReportedBy() {

        Concept concept = MohCacheUtils.getConcept(MohEvaluableNameConstants.DEATH_REPORTED_BY);
        CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
        cd.setName("Cause of Death Cohort Definition");
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
        cd.setQuestion(concept);
        return cd;
    }

    /**
     * Patients who have died
     * @return the cohort definition
     */
    public CohortDefinition dateOfDeath() {

        Concept concept = MohCacheUtils.getConcept(MohEvaluableNameConstants.DATE_OF_DEATH);
        CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
        cd.setName("Date of Death Cohort Definition");
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
        cd.setQuestion(concept);
        return cd;
    }

    /**
     * Patients who have died
     * @return the cohort definition
     */
    public CohortDefinition tbTreatmentOutcome() {

        Concept concept = MohCacheUtils.getConcept(MohEvaluableNameConstants.OUTCOME_AT_END_OF_TUBERCULOSIS_TREATMENT);
        CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
        cd.setName("Date of Death Cohort Definition");
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
        cd.addValue(MohCacheUtils.getConcept(MohEvaluableNameConstants.DEAD));
        cd.setQuestion(concept);
        return cd;
    }
    /**
     * Patients who have died
     * @return the cohort definition
     */
    public CohortDefinition reasonForMissedVisit() {

        Concept concept = MohCacheUtils.getConcept(MohEvaluableNameConstants.REASON_FOR_MISSED_VISIT);
        CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
        cd.setName("Date of Death Cohort Definition");
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
        cd.addValue(MohCacheUtils.getConcept(MohEvaluableNameConstants.DEAD));
        cd.setQuestion(concept);
        return cd;
    }

    /**
     * Patients who have died
     * @return the cohort definition
     */
    public CohortDefinition reasonExitedCare() {

        Concept concept = MohCacheUtils.getConcept(MohEvaluableNameConstants.REASON_EXITED_CARE);
        CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
        cd.setName("Date of Death Cohort Definition");
        cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
        cd.addValue(MohCacheUtils.getConcept(MohEvaluableNameConstants.PATIENT_DIED));
        cd.setQuestion(concept);
        return cd;
    }

    /**
     * composition cohort for dead from death reporting
     * @return the cohort definition
     */
    public CohortDefinition deathReporting() {

        CompositionCohortDefinition ccd = new CompositionCohortDefinition();
        ccd.setName("Death Reporting Composition Cohort");
        ccd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        ccd.addSearch("causeOfDeath", ReportUtils.map(causeOfDeath(), "onOrBefore=${onOrBefore}"));
        ccd.addSearch("deathReportedBy", ReportUtils.map(deathReportedBy(), "onOrBefore=${onOrBefore}"));
        ccd.addSearch("dateOfDeath", ReportUtils.map(dateOfDeath(), "onOrBefore=${onOrBefore}"));
        ccd.setCompositionString("OR");
        return ccd;
    }

    public CohortDefinition dead(){
        CompositionCohortDefinition ccd = new CompositionCohortDefinition();
        ccd.setName("Dead Patients Cohort Definition");
        ccd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        ccd.addSearch("deathReporting",ReportUtils.map(deathReporting(),"onOrBefore=${onOrBefore}"));
        ccd.addSearch("reasonExitedCare",ReportUtils.map(reasonExitedCare(),"onOrBefore=${onOrBefore}"));
        ccd.addSearch("reasonForMissedVisit",ReportUtils.map(reasonForMissedVisit(),"onOrBefore=${onOrBefore}"));
        ccd.addSearch("tbTreatmentOutcome",ReportUtils.map(tbTreatmentOutcome(),"onOrBefore=${onOrBefore}"));
        ccd.setCompositionString("OR");
        return ccd;
    }


    public CohortDefinition transferCareToOtherCentre() {
        CodedObsCohortDefinition transferCareToOtherCentre = new CodedObsCohortDefinition();
        transferCareToOtherCentre.setName("Transfer Care to other center");
        transferCareToOtherCentre.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        transferCareToOtherCentre.setTimeModifier(PatientSetService.TimeModifier.ANY);
        transferCareToOtherCentre.setQuestion(MohCacheUtils.getConcept(MohEvaluableNameConstants.TRANSFER_CARE_TO_OTHER_CENTER));
        return transferCareToOtherCentre;
    }

    public CohortDefinition exitCareDueToTO() {
        CodedObsCohortDefinition reasonExitedCare = new CodedObsCohortDefinition();
        reasonExitedCare.setName("Reason exited care");
        reasonExitedCare.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        reasonExitedCare.setTimeModifier(PatientSetService.TimeModifier.ANY);
        reasonExitedCare.setQuestion(MohCacheUtils.getConcept(MohEvaluableNameConstants.REASON_EXITED_CARE));
        reasonExitedCare.addValue(MohCacheUtils.getConcept(MohEvaluableNameConstants.PATIENT_TRANSFERRED_OUT));
        return reasonExitedCare;
    }

    public CohortDefinition tbTreatmentDefaulter() {
        CodedObsCohortDefinition tbTreatmentOutcome = new CodedObsCohortDefinition();
        tbTreatmentOutcome.setName("Outcome of TB Treatment");
        tbTreatmentOutcome.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        tbTreatmentOutcome.setTimeModifier(PatientSetService.TimeModifier.ANY);
        tbTreatmentOutcome.setQuestion(MohCacheUtils.getConcept(MohEvaluableNameConstants.OUTCOME_AT_END_OF_TUBERCULOSIS_TREATMENT));
        tbTreatmentOutcome.addValue(MohCacheUtils.getConcept(MohEvaluableNameConstants.PATIENT_DEFAULTED));
        return tbTreatmentOutcome;
    }

    public CohortDefinition noPlanToComeToClinic(){
        CodedObsCohortDefinition planTocomeToClinic = new CodedObsCohortDefinition();
        planTocomeToClinic.setName("Plan to return to clinic");
        planTocomeToClinic.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        planTocomeToClinic.setTimeModifier(PatientSetService.TimeModifier.ANY);
        planTocomeToClinic.setQuestion(MohCacheUtils.getConcept(MohEvaluableNameConstants.PLAN_TO_RETURN_TO_CLINIC));
        planTocomeToClinic.addValue(MohCacheUtils.getConcept(MohEvaluableNameConstants.NO));
        return planTocomeToClinic;
    }
    /**
     * Patients who have transferred out to other clinics
     * @return the cohort definition
     * ampath concepts are 1285, 1596-1594, 6206-1595, 1579-1066
     */
    public CohortDefinition transferOut() {

        CompositionCohortDefinition ccd = new CompositionCohortDefinition();
        ccd.setName("Transfer In Cohort Definition");
        ccd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
        ccd.addSearch("ToToOtherCenter",ReportUtils.map(transferCareToOtherCentre(),"onOrBefore=${onOrBefore}"));
        ccd.addSearch("exitCareDueToTO",ReportUtils.map(exitCareDueToTO(),"onOrBefore=${onOrBefore}"));
        ccd.addSearch("tbTreatmentDefaulter",ReportUtils.map(tbTreatmentDefaulter(),"onOrBefore=${onOrBefore}"));
        ccd.addSearch("noPlanToComeToClinic",ReportUtils.map(noPlanToComeToClinic(),"onOrBefore=${onOrBefore}"));
        ccd.setCompositionString("OR");
        return ccd;

    }

	/**
	 * Patients referred from the given entry point onto the HIV program
	 * @param entryPoints the entry point concepts
	 * @return the cohort definition
	 */
	public CohortDefinition referredFrom(Concept... entryPoints) {
		EncounterType hivEnrollEncType = MetadataUtils.getEncounterType(MohEvaluableNameConstants.ADMITTED_TO_HOSPITAL/*Metadata.EncounterType.HIV_ENROLLMENT*/);
		Concept methodOfEnrollment = MohCacheUtils.getConcept(MohEvaluableNameConstants.ADMITTED_TO_HOSPITAL);//Dictionary.getConcept(Dictionary.METHOD_OF_ENROLLMENT);

		CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
		cd.setName("entered care between dates");
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
		cd.setQuestion(methodOfEnrollment);
		cd.setValueList(Arrays.asList(entryPoints));
		cd.setOperator(SetComparator.IN);
		cd.setEncounterTypeList(Collections.singletonList(hivEnrollEncType));
		return cd;

	}

	/**
	 * Patients referred from the given entry point onto the HIV program
	 * @param entryPoints the entry point concepts
	 * @return the cohort definition
	 */
	public CohortDefinition referredNotFrom(Concept... entryPoints) {
		EncounterType hivEnrollEncType = MetadataUtils.getEncounterType(MohEvaluableNameConstants.ADMITTED_TO_HOSPITAL/*Metadata.EncounterType.HIV_ENROLLMENT*/);
		Concept methodOfEnrollment = MohCacheUtils.getConcept(MohEvaluableNameConstants.ADMITTED_TO_HOSPITAL);//Dictionary.getConcept(Dictionary.METHOD_OF_ENROLLMENT);

		CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
		cd.setName("entered care between dates");
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
		cd.setQuestion(methodOfEnrollment);
		cd.setValueList(Arrays.asList(entryPoints));
		cd.setOperator(SetComparator.NOT_IN);
		cd.setEncounterTypeList(Collections.singletonList(hivEnrollEncType));
		return cd;
	}

	/**
	 * Patients who were enrolled in HIV care (including transfers) between ${onOrAfter} and ${onOrBefore}
	 * @return the cohort definition
	 */
	public CohortDefinition enrolled() {
		return commonCohorts.enrolled(MetadataUtils.getProgram(MohEvaluableNameConstants.ADMITTED_TO_HOSPITAL/*Metadata.Program.HIV*/));
	}

	/**
	 * Patients who were enrolled in HIV care (excluding transfers) between ${onOrAfter} and ${onOrBefore}
	 * @return the cohort definition
	 */
	public CohortDefinition enrolledExcludingTransfers() {
		return commonCohorts.enrolledExcludingTransfers(MetadataUtils.getProgram(MohEvaluableNameConstants.ADMITTED_TO_HOSPITAL/*Metadata.Program.HIV*/));
	}

	/**
	 * Patients who were enrolled in HIV care (excluding transfers) from the given entry points between ${onOrAfter} and ${onOrBefore}
	 * @return the cohort definition
	 */
	public CohortDefinition enrolledExcludingTransfersAndReferredFrom(Concept... entryPoints) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("enrolled excluding transfers in HIV care from entry points between dates");
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		cd.addSearch("enrolledExcludingTransfers", ReportUtils.map(enrolledExcludingTransfers(), "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}"));
		cd.addSearch("referredFrom", ReportUtils.map(referredFrom(entryPoints), "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}"));
		cd.setCompositionString("enrolledExcludingTransfers AND referredFrom");
		return cd;
	}

	/**
	 * Patients who were enrolled in HIV care (excluding transfers) not from the given entry points between ${onOrAfter} and ${onOrBefore}
	 * @return the cohort definition
	 */
	public CohortDefinition enrolledExcludingTransfersAndNotReferredFrom(Concept... entryPoints) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("enrolled excluding transfers in HIV care not from entry points between dates");
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		cd.addSearch("enrolledExcludingTransfers", ReportUtils.map(enrolledExcludingTransfers(), "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}"));
		cd.addSearch("referredNotFrom", ReportUtils.map(referredNotFrom(entryPoints), "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}"));
		cd.setCompositionString("enrolledExcludingTransfers AND referredNotFrom");
		return cd;
	}

	/**
	 * Patients who were pregnant when they started ART
	 * @return the cohort definition
	 */
	public CohortDefinition pregnantAtArtStart() {
		CalculationCohortDefinition cd = new CalculationCohortDefinition(new PregnantAtArtStartCalculation());
		cd.setName("pregnant at start of ART");
		return cd;
	}

	/**
	 * Patients who were TB patients when they started ART
	 * @return the cohort definition
	 */
	public CohortDefinition tbPatientAtArtStart() {
		CalculationCohortDefinition cd = new CalculationCohortDefinition(new TbPatientAtArtStartCalculation());
		cd.setName("TB patient at start of ART");
		return cd;
	}

	/**
	 * Patients with given WHO stage when started ART
	 * @return the cohort definition
	 */
	public CohortDefinition whoStageAtArtStart(int stage) {
		CalculationCohortDefinition cd = new CalculationCohortDefinition(new WhoStageAtArtStartCalculation());
		cd.setName("who stage " + stage + " at start of ART");
		cd.setWithResult(stage);
		return cd;
	}

	/**
	 * Patients who started ART between ${onOrAfter} and ${onOrBefore}
	 * @return the cohort definition
	 */
	public CohortDefinition startedArt() {
		DateCalculationCohortDefinition cd = new DateCalculationCohortDefinition(new InitialArtStartDateCalculation());
		cd.setName("started ART between dates");
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		return cd;
	}

	/**
	 * Patients who started ART while pregnant between ${onOrAfter} and ${onOrBefore}
	 * @return the cohort definition
	 */
	public CohortDefinition startedArtWhilePregnant() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("started ART while pregnant between dates");
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		cd.addSearch("startedArt", ReportUtils.map(startedArt(), "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}"));
		cd.addSearch("pregnantAtArtStart", ReportUtils.map(pregnantAtArtStart()));
		cd.setCompositionString("startedArt AND pregnantAtArtStart");
		return cd;
	}

	/**
	 * Patients who started ART while being a TB patient between ${onOrAfter} and ${onOrBefore}
	 * @return the cohort definition
	 */
	public CohortDefinition startedArtWhileTbPatient() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("started ART while being TB patient between dates");
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		cd.addSearch("startedArt", ReportUtils.map(startedArt(), "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}"));
		cd.addSearch("tbPatientAtArtStart", ReportUtils.map(tbPatientAtArtStart()));
		cd.setCompositionString("startedArt AND tbPatientAtArtStart");
		return cd;
	}

	/**
	 * Patients who started ART with the given WHO stage between ${onOrAfter} and ${onOrBefore}
	 * @return the cohort definition
	 */
	public CohortDefinition startedArtWithWhoStage(int stage) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("started ART with WHO stage between dates");
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		cd.addSearch("startedArt", ReportUtils.map(startedArt(), "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}"));
		cd.addSearch("withWhoStage", ReportUtils.map(whoStageAtArtStart(stage)));
		cd.setCompositionString("startedArt AND withWhoStage");
		return cd;
	}

	/**
	 * Patients who are eligible for ART on ${onDate}
	 * @return the cohort definition
	 */
	public CohortDefinition eligibleForArt() {
		CalculationCohortDefinition cd = new CalculationCohortDefinition(new EligibleForArtCalculation());
		cd.setName("eligible for ART on date");
		cd.addParameter(new Parameter("onDate", "On Date", Date.class));
		return cd;
	}

	/**
	 * Patients who are on ART on ${onDate}
	 * @return the cohort definition
	 */
	public CohortDefinition onArt() {
		CalculationCohortDefinition cd = new CalculationCohortDefinition(new OnArtCalculation());
		cd.setName("on ART on date");
		cd.addParameter(new Parameter("onDate", "On Date", Date.class));
		return cd;
	}

	/**
	 * Patients who are on ART and pregnant on ${onDate}
	 * @return the cohort definition
	 */
	public CohortDefinition onArtAndPregnant() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("on ART and pregnant on date");
		cd.addParameter(new Parameter("onDate", "On Date", Date.class));
		cd.addSearch("onArt", ReportUtils.map(onArt(), "onDate=${onDate}"));
		cd.addSearch("pregnant", ReportUtils.map(commonCohorts.pregnant(), "onDate=${onDate}"));
		cd.setCompositionString("onArt AND pregnant");
		return cd;
	}

	/**
	 * Patients who are on ART and not pregnant on ${onDate}
	 * @return the cohort definition
	 */
	public CohortDefinition onArtAndNotPregnant() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("on ART and not pregnant on date");
		cd.addParameter(new Parameter("onDate", "On Date", Date.class));
		cd.addSearch("onArt", ReportUtils.map(onArt(), "onDate=${onDate}"));
		cd.addSearch("pregnant", ReportUtils.map(commonCohorts.pregnant(), "onDate=${onDate}"));
		cd.setCompositionString("onArt AND NOT pregnant");
		return cd;
	}

	/**
	 * Patients who are taking their original first line regimen on ${onDate}
	 * @return the cohort definition
	 */
	public CohortDefinition onOriginalFirstLine() {
		CalculationCohortDefinition cd = new CalculationCohortDefinition(new OnOriginalFirstLineArtCalculation());
		cd.setName("on original first line regimen on date");
		cd.addParameter(new Parameter("onDate", "On Date", Date.class));
		return cd;
	}

	/**
	 * Patients who are taking an alternate first line regimen on ${onDate}
	 * @return the cohort definition
	 */
	public CohortDefinition onAlternateFirstLine() {
		CalculationCohortDefinition cd = new CalculationCohortDefinition(new OnAlternateFirstLineArtCalculation());
		cd.setName("on alternate first line regimen on date");
		cd.addParameter(new Parameter("onDate", "On Date", Date.class));
		return cd;
	}

	/**
	 * Patients who are taking a second line regimen on ${onDate}
	 * @return the cohort definition
	 */
	public CohortDefinition onSecondLine() {
		CalculationCohortDefinition cd = new CalculationCohortDefinition(new OnSecondLineArtCalculation());
		cd.setName("on second line regimen on date");
		cd.addParameter(new Parameter("onDate", "On Date", Date.class));
		return cd;
	}

	/**
	 * Patients who are in the "12 month net cohort" on ${onDate}
	 * @return the cohort definition
	 */
	public CohortDefinition netCohort12Months() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("in 12 net cohort on date");
		cd.addParameter(new Parameter("onDate", "On Date", Date.class));
		cd.addSearch("startedArt12MonthsAgo", ReportUtils.map(startedArt(), "onOrAfter=${onDate-13m},onOrBefore=${onDate-12m}"));
		cd.addSearch("transferredOut", ReportUtils.map(commonCohorts.transferredOut(), "onOrAfter=${onDate-13m}"));
		cd.setCompositionString("startedArt12MonthsAgo AND NOT transferredOut");
		return cd;
	}

	/**
	 * Patients who are in HIV care and are on the specified medication on ${onDate}
	 * @return
	 */
	public CohortDefinition inHivProgramAndOnMedication(Concept... concepts) {
		Program hivProgram = MetadataUtils.getProgram(MohEvaluableNameConstants.ADMITTED_TO_HOSPITAL/*Metadata.Program.HIV*/);
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("in HIV program and on medication between dates");
		cd.addParameter(new Parameter("onDate", "On Date", Date.class));
		cd.addSearch("inProgram", ReportUtils.map(commonCohorts.inProgram(hivProgram), "onDate=${onDate}"));
		cd.addSearch("onMedication", ReportUtils.map(commonCohorts.onMedication(concepts), "onDate=${onDate}"));
		cd.setCompositionString("inProgram AND onMedication");
		return cd;
	}
}