package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.TestCase;

/**
 * Created by kavith on 2/5/14.
 */
public class TestCaseMappingModel implements MappingModel {

    public int WSO2_QAP_TEST_CASE_ID;

    public String  WSO2_QAP_TEST_CASE_NAME;

    public String  WSO2_QAP_DESCRIPTION;

    public String  WSO2_QAP_AUTOMATION_STATUS;

    public int  WSO2_QAP_AUTO_TEST_CLASS_ID;

    public int  WSO2_QAP_ARTIFACT_ID;

    public TestCase getEntity(){

        return new TestCase(WSO2_QAP_DESCRIPTION,WSO2_QAP_TEST_CASE_ID,WSO2_QAP_TEST_CASE_NAME, WSO2_QAP_AUTOMATION_STATUS,WSO2_QAP_ARTIFACT_ID,WSO2_QAP_AUTO_TEST_CLASS_ID);
    }

}
