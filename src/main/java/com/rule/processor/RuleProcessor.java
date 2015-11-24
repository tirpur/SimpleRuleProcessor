package com.rule.processor;

import java.util.*;

/**
 * Created by shri on 21/11/15.
 */
public class RuleProcessor {
    private ArrayList<Rule> rules;
    Map<String,String> outputMap;

    public RuleProcessor() {
        rules = new ArrayList<>();
        outputMap = new HashMap<>();
    }
    //add single rule
    public void addRules(Rule rule) {
        rules.add(rule);
        Collections.sort(rules);
    }
    //add list of rules
    public void addRules(ArrayList<Rule> rules2Add) {
        for(Rule rule:rules2Add) {
            rules.add(rule);
        }
    }
    public void addInputValue(String name,String val) {
        outputMap.put(name,val);
    }
    public Output processRules() {
//        ArrayList<Output> outList = new ArrayList<>();
        //execute all the rules ordered by priority
        Output finalOutput = null;
        for(Rule rule:rules) {
            try {
                Output out = rule.execute();
                //store the output name and its value to be referenced in next rule.
                outputMap.put(out.outputName,out.outputValue);
//                outList.add(out);
                finalOutput = out;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if(finalOutput != null)
            System.out.println("\nFinal output: "+finalOutput.outputName+" SET TO "+finalOutput.outputValue+"\n");
        return finalOutput;
    }
}
