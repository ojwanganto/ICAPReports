package org.openmrs.module.amrsreports.reporting.data.evaluator;

import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrsreports.reporting.data.DateARTStartedDataDefinition;
import org.openmrs.module.amrsreports.reporting.data.ICAPDateARTStartedDataDefinition;
import org.openmrs.module.amrsreports.service.MohCoreService;
import org.openmrs.module.reporting.common.ListMap;
import org.openmrs.module.reporting.data.person.EvaluatedPersonData;
import org.openmrs.module.reporting.data.person.definition.PersonDataDefinition;
import org.openmrs.module.reporting.data.person.evaluator.PersonDataEvaluator;
import org.openmrs.module.reporting.dataset.query.service.DataSetQueryService;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Evaluator for ART Eligibility
 */
@Handler(supports = ICAPDateARTStartedDataDefinition.class, order = 50)
public class ICAPDateARTStartedDataEvaluator implements PersonDataEvaluator {

    @Override
    public EvaluatedPersonData evaluate(final PersonDataDefinition definition, final EvaluationContext context) throws EvaluationException {
        EvaluatedPersonData data = new EvaluatedPersonData(definition, context);

        if (context.getBaseCohort().isEmpty())
            return data;

        String hql = "select personId, valueDatetime " +
                "	from Obs" +
                " 	where voided = false" +
                "   	and personId in (:patientIds)" +
                "   	and concept.id = 159599 " ;

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("patientIds", context.getBaseCohort());

        ListMap<Integer, Date> mappedStartDates = makeDatesMapFromHQL(hql, m);

        for (Integer memberId : context.getBaseCohort().getMemberIds()) {

            Date startDates = safeFind(mappedStartDates, memberId);
            data.addData(memberId, startDates);
        }

        return data;
    }

    /**
     * replaces reportDate and personIds with data from private variables before generating a date map
     */
    private ListMap<Integer, Date> makeDatesMapFromHQL(final String query, final Map<String, Object> substitutions) {
        MohCoreService mcs = Context.getService(MohCoreService.class);
        List<Object> queryResult = mcs.executeScrollingHqlQuery(query, substitutions);

        ListMap<Integer, Date> dateListMap = new ListMap<Integer, Date>();
        for (Object o : queryResult) {
            Object[] parts = (Object[]) o;
            if (parts.length == 2) {
                Integer pId = (Integer) parts[0];
                Date date = (Date) parts[1];
                dateListMap.putInList(pId, date);
            }
        }

        return dateListMap;
    }

    protected Date safeFind(final ListMap<Integer, Date> map, final Integer key) {
        Date arsStartDate = null;
        if (map.containsKey(key))
            arsStartDate = map.get(key).get(0);

        return arsStartDate;
    }
}
