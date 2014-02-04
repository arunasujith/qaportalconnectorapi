package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/3/14.
 */
public class TestPlan {

    private int testPlanId;

    private int productVersionId;

    private String planningStatus;

    public TestPlan(int testPlanId, int productVersionId, String planningStatus) {
        this.testPlanId = testPlanId;
        this.productVersionId = productVersionId;
        this.planningStatus = planningStatus;
    }

    public int getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(int testPlanId) {
        this.testPlanId = testPlanId;
    }

    public String getPlanningStatus() {
        return planningStatus;
    }

    public void setPlanningStatus(String planningStatus) {
        this.planningStatus = planningStatus;
    }

    public int getProductVersionId() {
        return productVersionId;
    }

    public void setProductVersionId(int productVersionId) {
        this.productVersionId = productVersionId;
    }
}
