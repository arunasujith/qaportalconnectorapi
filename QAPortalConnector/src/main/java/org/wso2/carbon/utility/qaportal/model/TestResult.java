package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/3/14.
 */
public class TestResult {

    private int testResultId;

    private int testCaseId;

    private String testStatus;

    private String automationTestResult;

    private String jira;

    private String testedBy;

    public TestResult(int testResultId, String testStatus, int testCaseId, String jira, String automationTestResult, String testedBy) {
        this.testResultId = testResultId;
        this.testStatus = testStatus;
        this.testCaseId = testCaseId;
        this.jira = jira;
        this.automationTestResult = automationTestResult;
        this.testedBy = testedBy;
    }

    public int getTestResultId() {
        return testResultId;
    }

    public void setTestResultId(int testResultId) {
        this.testResultId = testResultId;
    }

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getAutomationTestResult() {
        return automationTestResult;
    }

    public void setAutomationTestResult(String automationTestResult) {
        this.automationTestResult = automationTestResult;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public String getJira() {
        return jira;
    }

    public void setJira(String jira) {
        this.jira = jira;
    }

    public String getTestedBy() {
        return testedBy;
    }

    public void setTestedBy(String testedBy) {
        this.testedBy = testedBy;
    }
}
