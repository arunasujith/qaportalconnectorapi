package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/3/14.
 */
public class TestCase {

    private int testCaseId;

    private String testCaseName;

    private String description;

    private String automationStatus;

    private int autoTestClassId;

    private int qAArtifactId;

    public TestCase(String description, int testCaseId, String testCaseName, String automationStatus, int qAArtifactId, int autoTestClassId) {
        this.description = description;
        this.testCaseId = testCaseId;
        this.testCaseName = testCaseName;
        this.automationStatus = automationStatus;
        this.qAArtifactId = qAArtifactId;
        this.autoTestClassId = autoTestClassId;
    }

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getAutomationStatus() {
        return automationStatus;
    }

    public void setAutomationStatus(String automationStatus) {
        this.automationStatus = automationStatus;
    }

    public int getAutoTestClassId() {
        return autoTestClassId;
    }

    public void setAutoTestClassId(int autoTestClassId) {
        this.autoTestClassId = autoTestClassId;
    }

    public int getqAArtifactId() {
        return qAArtifactId;
    }

    public void setqAArtifactId(int qAArtifactId) {
        this.qAArtifactId = qAArtifactId;
    }
}
