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


        String reportDate = sdf.format(context.getEvaluationDate());
        List<Location> locationList = new ArrayList<Location>();
        locationList.addAll(definition.getFacility().getLocations());
        context.addParameterValue("locationList", locationList);

        String sql ="select  o.person_id " +
                " from obs o " +
                " where o.voided = 0 " +
                " and ((o.concept_id in (1040, 1030, 1042) and o.value_coded = 703) " +
                " or (o.concept_id = 6042 and o.value_coded = 1169))" +
                " and o.location_id in ( :locationList )";

        String encounterData = " union select patient_id " +
                " from encounter " +
                " where  voided=0  " +
                " and encounter_type in(1,2,3,4,13) " +
                " and  location_id in ( :locationList ) ";

        sql = sql + encounterData;


        /*add transfer ins*/

        for (Location location : locationList) {
            String personAttributeQuery =
                    " union " +
                            " select pa.person_id" +
                            " from person_attribute pa " +
                            "   join encounter e " +
                            "     on e.patient_id = pa.person_id" +
                            "       and e.voided = 0" +
                            "       and e.location_id in ( :locationList )" +
                            " where (pa.voided = 0" +
                            "        or (pa.voided = 1 and pa.void_reason like 'New value: %'))" +
                            "   and pa.person_attribute_type_id = 7" +
                            "   and pa.value = '" + location.getLocationId() + "'" +
                            "   and pa.date_created <= ':reportDate'";

            sql = sql + personAttributeQuery;
        }



        SqlCohortDefinition sqlCohortDefinition = new SqlCohortDefinition(sql.replaceAll(":reportDate", reportDate));
        Cohort results = Context.getService(CohortDefinitionService.class).evaluate(sqlCohortDefinition, context);

        return new EvaluatedCohort(results, sqlCohortDefinition, context);
    }
}
