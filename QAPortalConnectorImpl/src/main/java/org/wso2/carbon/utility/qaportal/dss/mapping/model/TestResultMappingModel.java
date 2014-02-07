package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.TestResult;

/**
 * Created by kavith on 2/5/14.
 */
public class TestResultMappingModel implements MappingModel {

    public int WSO2_QAP_TEST_RESULT_ID;

    public int WSO2_QAP_TEST_PLAN_ID;

    public String WSO2_QAP_TEST_CASE_NAME;

    public int WSO2_QAP_TEST_CASE_ID;

    public String WSO2_QAP_TEST_STATUS;

    public String WSO2_QAP_AUTOMATION_TEST_RESULT;

    public String WSO2_QAP_JIRA;

    public String WSO2_QAP_TESTED_BY;

    public TestResult getEntity(){

        return new TestResult(WSO2_QAP_TEST_RESULT_ID, WSO2_QAP_TEST_STATUS, WSO2_QAP_TEST_CASE_ID, WSO2_QAP_JIRA,
                                WSO2_QAP_AUTOMATION_TEST_RESULT, WSO2_QAP_TESTED_BY);
    }

}
