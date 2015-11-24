# SimpleRuleProcessor

Description:
============
SimpleRuleProcessor is simple rule execution util which can:
1. Execute rule based on the priority.
2. Uses variable name for the input condition and output values.
3. One rule can be chained to another such that output value of one rule can be used as part of input value of another rule and hence forth.


Environment:
============
1. Uses Maven for build and dependency management.
2. Compile and tested using Java 1.8
3. Uses Junit for unit tests
4. Uses JSON format for rules to be fed


Usage:
=======

1. To compile, test and create jar run:
mvn clean; mvn compile; mvn package

2. To test only run:
mvn test

3. Run the created uber jar from commandline i.e
java -jar RuleProcessor-jar-with-dependencies.jar

4. Upon running jar it gives the console where user can feed RULES in loop.
You can provide condition left side as variable name and rule will execute using a lookup that remember its variable name and latest value.
For example:
We can run following rules:

IF input_value < 10
        SET output_value TO "Hello"
ELSE
        SET output_value TO “SORRY"

and

IF output_value == "SORRY"
    SET output_value1 TO "NO THANKS"
ELSE
    SET output_value1 TO "THANKS"

With priority 1 and 2 respectively.
So here the condition input value of the first rule has to be keyed-in as trigger input value.

Snapshot of this run is as below:

shri@shri-lappy:~/MyRuleProcessor$ java -jar /home/shri/MyRuleProcessor/target/RuleProcessor-jar-with-dependencies.jar 

Enter trigger input name: 
input_value
Enter trigger input value: 
7
Enter priority of the rule:
10
Enter the rule: 
IF input_value < 10
        SET output_value TO "Hello"
ELSE
        SET output_value TO “SORRY"

Final output: output_value SET TO "Hello"

Enter priority of the rule:
14
Enter the rule: 
IF output_value == "SORRY"
    SET output_value1 TO "NO THANKS"
ELSE
    SET output_value1 TO "THANKS"

Final output: output_value1 SET TO "THANKS"

Enter priority of the rule:
^Cshri@shri-lappy:~/MyRuleProcessor$ 
