package com.rule.processor;

/**
 * Created by shri on 21/11/15.
 */
public class Rule implements Comparable<Rule>{
    Integer priority;
    Condition condition;
    Output conditionTrue;
    Output conditionFalse;

    public Rule(Integer priority, Condition condition, Output conditionTrue, Output conditionFalse) {
        this.priority = priority;
        this.condition = condition;
        this.conditionTrue = conditionTrue;
        this.conditionFalse = conditionFalse;
    }

    public int compareTo(Rule rule) {
        return priority.compareTo(rule.priority);
    }
    public Output execute() throws InvalidOperator {
        try {
            if (condition.evaluate()) {
                return conditionTrue;
            } else {
                return conditionFalse;
            }
        } catch (InvalidOperator e) {
            System.out.println("Exception has been thrown: "+e.getMessage());
            throw e;
        }
    }
}


