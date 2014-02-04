package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/3/14.
 */
public class TestSuit {

    private int testSuitId;

    private String testSuitName;

    private int featureId;

    public TestSuit(int testSuitId, int featureId, String testSuitName) {
        this.testSuitId = testSuitId;
        this.featureId = featureId;
        this.testSuitName = testSuitName;
    }

    public int getTestSuitId() {
        return testSuitId;
    }

    public void setTestSuitId(int testSuitId) {
        this.testSuitId = testSuitId;
    }

    public String getTestSuitName() {
        return testSuitName;
    }

    public void setTestSuitName(String testSuitName) {
        this.testSuitName = testSuitName;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }
}
