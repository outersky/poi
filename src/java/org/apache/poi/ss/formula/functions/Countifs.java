/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */


package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.*;

/**
 * Implementation for the function COUNTIFS
 * <p>
 * Syntax: COUNTIFS(criteria_range1, criteria1, [criteria_range2, criteria2])
 * </p>
 */
public class Countifs implements FreeRefFunction {
    public static final FreeRefFunction instance = new Countifs();

//    static int count = 0;
//    public static long times = 0;
    
    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
//        System.err.println("Countifs " + ++count );
//        long time = System.currentTimeMillis();
//        try{
            
        if (args.length == 0 || args.length % 2 != 0) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            // collect pairs of ranges and criteria
            int len = args.length / 2;
            AreaEval[] ae = new AreaEval[len];
            CountUtils.I_MatchPredicate[] mp = new CountUtils.I_MatchPredicate[len];
            for (int i = 0; i < len; i++) {
                ae[i] = convertRangeArg(args[2 * i]);
                mp[i] = Countif.createCriteriaPredicate(args[2 * i + 1],
                        ec.getRowIndex(), ec.getColumnIndex());
            }

            validateCriteriaRanges(ae);

            int result = countMatchingCells(ae, mp);
            return new NumberEval(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
//        }finally{
//            times += System.currentTimeMillis() - time;
//        }
    }

    /**
     * Verify that each <code>criteriaRanges</code> argument contains the same
     * number of rows and columns as the <code>sumRange</code> argument
     *
     * @throws EvaluationException
     *             if
     */
    private static void validateCriteriaRanges(AreaEval[] criteriaRanges) throws EvaluationException {
        for (AreaEval r : criteriaRanges) {
            if (r.getHeight() != criteriaRanges[0].getHeight()
                    || r.getWidth() != criteriaRanges[0].getWidth()) {
                throw EvaluationException.invalidValue();
            }
        }
    }

    private static AreaEval convertRangeArg(ValueEval eval)
            throws EvaluationException {
        if (eval instanceof AreaEval) {
            return (AreaEval) eval;
        }
        if (eval instanceof RefEval) {
            return ((RefEval) eval).offset(0, 0, 0, 0);
        }
        throw new EvaluationException(ErrorEval.VALUE_INVALID);
    }

    /**
     *
     * @param ranges
     *            criteria ranges, each range must be of the same dimensions as
     *            <code>aeSum</code>
     * @param predicates
     *            array of predicates, a predicate for each value in
     *            <code>ranges</code>
     *
     * @return the computed value
     */
    private static int countMatchingCells(AreaEval[] ranges,
                                          CountUtils.I_MatchPredicate[] predicates) {
        int height = ranges[0].getHeight();
        int width = ranges[0].getWidth();

        int result = 0;
        for (int r = 0; r < height; r++) {
            outer: 
            for (int c = 0; c < width; c++) {

                for (int i = 0; i < ranges.length; i++) {
                    AreaEval aeRange = ranges[i];
                    CountUtils.I_MatchPredicate mp = predicates[i];

                    ValueEval relativeValue = aeRange.getRelativeValue(r, c);
                    if (!mp.matches(relativeValue)) {
                        continue outer;
                    }

                }

                // criteria specified are true for that cell.
                result += 1;
            }
        }
        return result;
    }
}