package com.rule.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shri on 22/11/15.
 */
public class RuleParser {
    //Parse lines one by one
    //Line starting with IF converted into an object of Condition for the rule
    //Line after IF is conditionTrue
    //Line after ELSE is conditionFalse
    RuleProcessor ruleProcessor = new RuleProcessor();

    public Condition getCondition(String strLine) {
        String[] line = strLine.split("\\s+");
        if(line.length < 4 && line.length > 5) {
            System.out.println("Invalid rule condition syntax! It should be IF <input> <operator> <value> where <input> should be single word without quote.");
            return null;
        }
        if(line.length == 5 && !line[4].startsWith("\"")) {
            System.out.println("Invalid rule condition syntax! It should be IF <input> <operator> <value> where <input> should be single word without quote.");
            return null;
        }
        if(!line[0].equals("IF")) {
            System.out.println("A rule must start with IF");
            return null;
        }
        if(ruleProcessor.outputMap.containsKey(line[1])) {
            return new Condition(ruleProcessor.outputMap.get(line[1]),line[3],line[2]);
        } else {
            System.out.println("Could not resolve: "+line[1]);
        }
        return null;
    }
    public Output getOutput(String line) {
        String[] lineArr = line.split(" TO ");
        if(lineArr.length < 2) {
            System.out.println("Invalid set output statement! It should be SET <output> TO <value> where <output> should be single word without quote.");
            return null;
        }
        String[] setPart = lineArr[0].split("\\s+");
        if(!setPart[0].trim().equals("SET")) {
            System.out.println("Statement must start with SET");
            return null;
        }
        String outName = setPart[1].trim();
        String outValue = lineArr[1].trim();
        if(lineArr.length > 2)  {
            //Chances of having TO in the output value inside quote
            int toIndex = line.indexOf(" TO ");
            outValue = line.substring(toIndex+4).trim().replace("\"","");
        }
        return new Output(outValue,outName);
    }
    public static void main(String[] args) throws IOException{
        RuleParser ruleParser = new RuleParser();
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);

        System.out.println("Enter trigger input name: ");
        String inputName = in.readLine();
        System.out.println("Enter trigger input value: ");
        String inputValue = in.readLine();

        ruleParser.ruleProcessor.addInputValue(inputName,inputValue);

        while (true) {
            System.out.println("Enter priority of the rule:");
            String curLine = in.readLine();
            Integer priority;
            try {
                priority = Integer.parseInt(curLine);
            } catch (NumberFormatException e) {
                System.out.println("Please give numerical priority value!");
                continue;
            }
            System.out.println("Enter the rule: ");
            Condition condition = ruleParser.getCondition(in.readLine().trim());
            if(condition == null) {
                continue;
            }
            Output outputTrue = ruleParser.getOutput(in.readLine().trim());
            if(outputTrue == null) {
                continue;
            }
            String els = in.readLine().trim();
            if(!els.equals("ELSE")) {
                System.out.println("Rule requires an ELSE line!");
                continue;
            }
            Output outputFalse = ruleParser.getOutput(in.readLine().trim());
            Rule rule = new Rule(priority,condition,outputTrue,outputFalse);


            ruleParser.ruleProcessor.addRules(rule);
            try {
                ruleParser.ruleProcessor.processRules();
            } catch (Exception e) {
                System.out.println("Exception occurred: "+e.getMessage());
            }
        }



    }
}
