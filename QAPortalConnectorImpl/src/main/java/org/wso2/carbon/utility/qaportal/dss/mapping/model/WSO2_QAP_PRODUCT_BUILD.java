package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.ProductBuild;

import java.util.Date;

/**
 * Created by kavith on 2/4/14.
 */
public class WSO2_QAP_PRODUCT_BUILD implements MappingModel {

    public int WSO2_QAP_PRODUCT_BUILD_ID;

    public int WSO2_QAP_PRODUCT_VERSION_ID;

    public String WSO2_QAP_BUILD_NAME;

    public Date WSO2_QAP_RELEASE_DATE;

    public int WSO2_QAP_TEST_PLAN_ID;

    public ProductBuild getEntity(){

        return new ProductBuild(WSO2_QAP_PRODUCT_BUILD_ID, WSO2_QAP_BUILD_NAME, WSO2_QAP_PRODUCT_VERSION_ID, WSO2_QAP_RELEASE_DATE, WSO2_QAP_TEST_PLAN_ID);
    }
}
