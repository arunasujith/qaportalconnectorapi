package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/5/14.
 */
public class TestSuitScenarioAssociation {

    private int testSuitId;

    private int testScenarioId;

    private String priority;

    private String owner;

    public TestSuitScenarioAssociation(int testScenarioId, int testSuitId, String owner, String priority) {
        this.testScenarioId = testScenarioId;
        this.testSuitId = testSuitId;
        this.owner = owner;
        this.priority = priority;
    }

    public int getTestSuitId() {
        return testSuitId;
    }

    public void setTestSuitId(int testSuitId) {
        this.testSuitId = testSuitId;
    }

    public int getTestScenarioId() {
        return testScenarioId;
    }

    public void setTestScenarioId(int testScenarioId) {
        this.testScenarioId = testScenarioId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
