package com.rule.processor;

import org.junit.Test;

import java.util.List;

/*
 * Created by shri on 21/11/15.
 *
 */


public class RuleProcessorTest {
    RuleParser ruleParser = new RuleParser();
    @Test
    public void testRuleParsing() {
        /*
            IF <input_value> < 10
        SET <output_value> TO “Hello”
    ELSE
        SET <output_value> TO “SORRY"
         */
        ruleParser.ruleProcessor.addInputValue("input_value","7");

        String condStr = "IF input_value < 10";
        String condTru = "SET output_value TO \"Hello\"";
        String condFal = "SET output_value TO \"SORRY\"";
        Condition cond = ruleParser.getCondition(condStr);
        Output outTrue = ruleParser.getOutput(condTru);
        Output outFalse = ruleParser.getOutput(condFal);
        Rule rule = new Rule(1,cond,outTrue,outFalse);
        ruleParser.ruleProcessor.addRules(rule);
        Output out = ruleParser.ruleProcessor.processRules();
        assert out.outputName.equals("output_value") && out.outputValue.equals("\"Hello\"");

    }
    @Test
    public void testChaining() {
        /*
IF input_value < 10
        SET output_value TO “Hello”
ELSE
        SET output_value TO “SORRY"

IF output_value == "SORRY"
    SET output_value1 TO "NO THANKS"
ELSE
    SET output_value1 TO "THANKS"

         */
        ruleParser.ruleProcessor.addInputValue("input_value","11");

        String condStr = "IF input_value < 10";
        String condTru = "SET output_value TO \"Hello\"";
        String condFal = "SET output_value TO \"SORRY\"";
        Condition cond = ruleParser.getCondition(condStr);
        Output outTrue = ruleParser.getOutput(condTru);
        Output outFalse = ruleParser.getOutput(condFal);
        Rule rule = new Rule(2,cond,outTrue,outFalse);
        ruleParser.ruleProcessor.addRules(rule);

        ruleParser.ruleProcessor.processRules();

        String condStr1 = "IF output_value == \"SORRY\"";
        String condTru1 = "SET output_value1 TO \"NO THANKS\"";
        String condFal1 = "SET output_value1 TO \"THANKS\"";
        Condition cond1 = ruleParser.getCondition(condStr1);
        Output outTrue1 = ruleParser.getOutput(condTru1);
        Output outFalse1 = ruleParser.getOutput(condFal1);
        Rule rule1 = new Rule(3,cond1,outTrue1,outFalse1);
        ruleParser.ruleProcessor.addRules(rule1);

        Output out = ruleParser.ruleProcessor.processRules();
        System.out.println("OUTPUT returned="+out.outputName+" "+out.outputValue);
        assert out.outputName.equals("output_value1") && out.outputValue.equals("\"NO THANKS\"");

    }
    @Test
    public void testIntLessThan() {
        Condition condition = new Condition("2","5","<");
        Rule rule = new Rule(1,condition,new Output("99","test"),new Output("77","test"));
        try {
            assert (rule.execute() == rule.conditionTrue);
        } catch (Exception e) {
            assert false;
        }
    }
    @Test
    public void testIntLessThanEqual() {
        Condition condition = new Condition("22","22","<=");
        Rule rule = new Rule(1,condition,new Output("199","test"),new Output("177","test"));

        try {
            assert (rule.execute() == rule.conditionTrue);
        } catch (Exception e) {
            assert false;
        }
    }
    @Test
    public void testIntGreaterThan() {
        Condition condition = new Condition("25","22",">");
        Rule rule = new Rule(1,condition,new Output("19","test"),new Output("17","test"));

        try {
            assert (rule.execute() == rule.conditionTrue);
        } catch (Exception e) {
            assert false;
        }
    }
    @Test
    public void testIntGreaterThanEqual() {
        Condition condition = new Condition("25","25",">=");
        Rule rule = new Rule(1,condition,new Output("19","test"),new Output("17","test"));

        try {
            assert (rule.execute() == rule.conditionTrue);
        } catch (Exception e) {
            assert false;
        }
    }
    @Test
    public void testIntEqual() {
        Condition condition = new Condition("25","25","==");
        Rule rule = new Rule(1,condition,new Output("19","test"),new Output("17","test"));

        try {
            assert (rule.execute() == rule.conditionTrue);
        } catch (Exception e) {
            assert false;
        }
    }
    @Test
    public void testIntNotEqual() {
        Condition condition = new Condition("55","25","!=");
        Rule rule = new Rule(1,condition,new Output("19","test"),new Output("17","test"));

        try {
            assert (rule.execute() == rule.conditionTrue);
        } catch (Exception e) {
            assert false;
        }

    }
    @Test
    public void testStrEqual() {
        Condition condition = new Condition("ProcessorMain","ProcessorMain","==");
        Rule rule = new Rule(1,condition,new Output("8","test"),new Output("17","test"));

        try {
            assert (rule.execute() == rule.conditionTrue);
        } catch (Exception e) {
            assert false;
        }
    }
    @Test
    public void testStrNotEqual() {
        Condition condition = new Condition("Processor","ProcessorMain","!=");
        Rule rule = new Rule(1,condition,new Output("8","test"),new Output("17","test"));

        try {
            assert (rule.execute() == rule.conditionTrue);
        } catch (Exception e) {
            assert false;
        }
    }
    @Test
    public void testUnsupportedOperatorForAll() {
        Condition condition = new Condition("ProcessorMain","ProcessorMain","+=");
        Rule rule = new Rule(1,condition,new Output("8","test"),new Output("17","test"));

        try {
            assert (rule.execute() == rule.conditionTrue);
        } catch (Exception e) {
            assert true;
        }
    }
    @Test
    public void testUnsupportedOperatorString() {
        Condition condition = new Condition("Processor","ProcessorMain","<");
        Rule rule = new Rule(1,condition,new Output("8","test"),new Output("17","test"));

        try {
            assert (rule.execute() == rule.conditionTrue);
        } catch (Exception e) {
            assert true;
        }
    }

}
