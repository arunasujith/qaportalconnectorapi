package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.ProductVersion;

/**
 * Created by kavith on 2/4/14.
 */
public class WSO2_QAP_PRODUCT_VERSION implements MappingModel {

    public int WSO2_QAP_PRODUCT_VERSION_ID;

    public int WSO2_QAP_PRODUCT_ID;

    public String WSO2_QAP_PRODUCT_VERSION;

    public ProductVersion getEntity()
    {
      return new ProductVersion(WSO2_QAP_PRODUCT_VERSION_ID,WSO2_QAP_PRODUCT_ID,WSO2_QAP_PRODUCT_VERSION);
    }
}
