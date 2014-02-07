package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.TestSuitScenarioAssociation;

/**
 * Created by kavith on 2/7/14.
 */
public class TestSuitScenarioAssociationMappingModel implements MappingModel{

    public int WSO2_QAP_TEST_SCENARIO_ID;

    public int WSO2_QAP_TEST_SUIT_ID;

    public String WSO2_QAP_PRIORITY;

    public String WSO2_QAP_OWNER;

    @Override
    public Object getEntity() {
        return new TestSuitScenarioAssociation(WSO2_QAP_TEST_SUIT_ID, WSO2_QAP_TEST_SCENARIO_ID, WSO2_QAP_OWNER, WSO2_QAP_PRIORITY);
    }
}
