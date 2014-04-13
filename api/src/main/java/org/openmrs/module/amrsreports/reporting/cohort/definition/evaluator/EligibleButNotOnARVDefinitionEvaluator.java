package org.openmrs.module.amrsreports.reporting.cohort.definition.evaluator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Cohort;
import org.openmrs.Location;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrsreports.reporting.cohort.definition.EligibleButNotOnARVCohortDefinition;
import org.openmrs.module.reporting.cohort.EvaluatedCohort;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.evaluator.CohortDefinitionEvaluator;
import org.openmrs.module.reporting.cohort.definition.service.CohortDefinitionService;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Evaluator for Eligible and not on ARV
 */
@Handler(supports = {EligibleButNotOnARVCohortDefinition.class})
public class EligibleButNotOnARVDefinitionEvaluator implements CohortDefinitionEvaluator {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final Log log = LogFactory.getLog(this.getClass());

    @Override
    public EvaluatedCohort evaluate(CohortDefinition cohortDefinition, EvaluationContext context) throws EvaluationException {

        EligibleButNotOnARVCohortDefinition definition = (EligibleButNotOnARVCohortDefinition) cohortDefinition;

        if (definition == null)
            return null;


		if (definition.getFacility() == null)
			return null;
       /*ARV Date Medically Eligible:162227
        ARV Date Started on 1st Line:159599
*/

        String reportDate = sdf.format(context.getEvaluationDate());
        List<Location> locationList = new ArrayList<Location>();
        locationList.addAll(definition.getFacility().getLocations());
        context.addParameterValue("locationList", locationList);
        context.addParameterValue("startDate", context.getEvaluationDate());

       String sql ="select  o.person_id  " +
               "  from obs o  " +
               "  inner join person p  " +
               "  on p.person_id=o.person_id   " +
               "    where o.voided = 0  " +
               "    and p.voided=0   " +
               "    and (o.concept_id = 162227 and (value_datetime between (:startDate) and (:endDate)))  " +
               "    and concept_id<>159599 " +
               "    and o.location_id in ( :locationList ) ";

       //sql = sql.replaceAll(":startDate", reportDate);
        SqlCohortDefinition sqlCohortDefinition = new SqlCohortDefinition(sql);
        Cohort results = Context.getService(CohortDefinitionService.class).evaluate(sqlCohortDefinition, context);
        log.info("SQL Passed: "+ sql);
        return new EvaluatedCohort(results, sqlCohortDefinition, context);
    }
}
