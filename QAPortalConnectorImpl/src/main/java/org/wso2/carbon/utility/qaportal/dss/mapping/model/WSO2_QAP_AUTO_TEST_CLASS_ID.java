package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.AutoTestClass;

/**
 * Created by kavith on 2/5/14.
 */
public class WSO2_QAP_AUTO_TEST_CLASS_ID implements MappingModel {

    public int WSO2_QAP_AUTO_TEST_CLASS_ID;

    public String WSO2_QAP_AUTO_TEST_CLASS_NAME;

    public String WSO2_QAP_AUTO_TEST_CLASS_LOCATION;

    public AutoTestClass getEntity(){

        return new AutoTestClass(WSO2_QAP_AUTO_TEST_CLASS_ID, WSO2_QAP_AUTO_TEST_CLASS_LOCATION, WSO2_QAP_AUTO_TEST_CLASS_NAME);
    }
}
