package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.TestSuit;

/**
 * Created by kavith on 2/5/14.
 */
public class TestSuitMappingModel implements MappingModel {

    public int WSO2_QAP_TEST_SUIT_ID;

    public int WSO2_QAP_FEATURE_ID;

    public String WSO2_QAP_TEST_SUIT_NAME;

    public TestSuit getEntity(){

        return new TestSuit(WSO2_QAP_TEST_SUIT_ID, WSO2_QAP_FEATURE_ID, WSO2_QAP_TEST_SUIT_NAME);
    }
}
