package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.TestScenario;

/**
 * Created by kavith on 2/5/14.
 */
public class WSO2_QAP_TEST_SCENARIO implements MappingModel {

    public int WSO2_QAP_TEST_SCENARIO_ID;

    public String WSO2_QAP_TEST_SCENARIO_NAME;

    public String WSO2_QAP_DESCRIPTION;

    public TestScenario getEntity(){

        return new TestScenario(WSO2_QAP_TEST_SCENARIO_ID, WSO2_QAP_TEST_SCENARIO_NAME, WSO2_QAP_DESCRIPTION);
    }
}
