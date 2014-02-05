package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.TestPlan;

/**
 * Created by kavith on 2/5/14.
 */
public class WSO2_QAP_TEST_PLAN implements MappingModel {

    public int WSO2_QAP_TEST_PLAN_ID;

    public int WSO2_QAP_PRODUCT_VERSION_ID;

    public String WSO2_QAP_PLANNING_STATUS;

    public TestPlan getEntity(){

        return new TestPlan(WSO2_QAP_TEST_PLAN_ID, WSO2_QAP_PRODUCT_VERSION_ID, WSO2_QAP_PLANNING_STATUS);
    }
}
