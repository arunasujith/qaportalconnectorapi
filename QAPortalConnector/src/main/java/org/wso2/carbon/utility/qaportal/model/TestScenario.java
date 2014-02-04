package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/3/14.
 */
public class TestScenario {

    private int testScenarioId;

    private String testScenarioName;

    private String description;

    public TestScenario(int testScenarioId, String testScenarioName, String description) {
        this.testScenarioId = testScenarioId;
        this.testScenarioName = testScenarioName;
        this.description = description;
    }

    public int getTestScenarioId() {
        return testScenarioId;
    }

    public void setTestScenarioId(int testScenarioId) {
        this.testScenarioId = testScenarioId;
    }

    public String getTestScenarioName() {
        return testScenarioName;
    }

    public void setTestScenarioName(String testScenarioName) {
        this.testScenarioName = testScenarioName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
