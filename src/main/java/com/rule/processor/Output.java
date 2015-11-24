package com.rule.processor;

/**
 * Created by shri on 21/11/15.
 */
class Output {
    String outputValue;
    String outputName;

    public Output(String ov,String name) {
        outputValue = ov;
        outputName = name;
    }
    public void setOutput(String val) {
        outputValue = val;
    }
    public void setOutputName(String val) {
        outputName = val;
    }
    public String getOutput() {
        return outputValue;
    }
}
