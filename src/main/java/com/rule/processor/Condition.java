package com.rule.processor;

import java.util.DoubleSummaryStatistics;

/**
 * Created by shri on 21/11/15.
 */
class  Condition implements ConditionInterface{
    private String leftSide;
    private String rightSide;
    private String operator;
    public Condition(String ls, String rs, String op) {
        leftSide = ls;
        rightSide = rs;
        operator = op;
    }
    public boolean evaluate() throws InvalidOperator {

        switch (operator) {
            case "==": {
                return leftSide.equals(rightSide);
            }
            case "!=": {
                return !leftSide.equals(rightSide);
            }
            case ">": {
                Integer lhsInt = null;
                Integer rhsInt = null;
                try {
                    lhsInt = Integer.parseInt(leftSide);
                    rhsInt = Integer.parseInt(rightSide);
                } catch (Exception e) {

                }
                if(lhsInt != null && rhsInt != null) {
                    return (lhsInt > rhsInt);
                } else {
                    throw new InvalidOperator("Operator: " + operator + " is not supported for input: " + leftSide + " and " + rightSide);
                }
            }
            case "<": {
                Integer lhsInt = null;
                Integer rhsInt = null;
                try {
                    lhsInt = Integer.parseInt(leftSide);
                    rhsInt = Integer.parseInt(rightSide);
                } catch (Exception e) {

                }
                if(lhsInt != null && rhsInt != null) {
                    return (lhsInt < rhsInt);
                } else {
                    throw new InvalidOperator("Operator: " + operator + " is not supported for input: " + leftSide + " and " + rightSide);
                }
            }
            case ">=": {
                Integer lhsInt = null;
                Integer rhsInt = null;
                try {
                    lhsInt = Integer.parseInt(leftSide);
                    rhsInt = Integer.parseInt(rightSide);
                } catch (Exception e) {

                }
                if(lhsInt != null && rhsInt != null) {
                    return (lhsInt >= rhsInt);
                } else {
                    throw new InvalidOperator("Operator: " + operator + " is not supported for input: " + leftSide + " and " + rightSide);
                }
            }
            case "<=": {
                Integer lhsInt = null;
                Integer rhsInt = null;
                try {
                    lhsInt = Integer.parseInt(leftSide);
                    rhsInt = Integer.parseInt(rightSide);
                } catch (Exception e) {

                }
                if(lhsInt != null && rhsInt != null) {
                    return (lhsInt <= rhsInt);
                } else {
                    throw new InvalidOperator("Operator: " + operator + " is not supported for input: " + leftSide + " and " + rightSide);
                }
            }
        }
        throw new InvalidOperator("Operator: " + operator + " is not supported for input: " + leftSide + " and " + rightSide);
    }
}
