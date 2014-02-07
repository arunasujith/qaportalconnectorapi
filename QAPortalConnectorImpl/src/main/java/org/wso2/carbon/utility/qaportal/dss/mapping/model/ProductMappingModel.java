package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.Product;

import java.io.Serializable;

/**
 * Created by kavith on 2/3/14.
 */
public class ProductMappingModel implements MappingModel{

    public int WSO2_QAP_PRODUCT_ID;

    public String  WSO2_QAP_PRODUCT_NAME;

    public Product getEntity(){
        return new Product(WSO2_QAP_PRODUCT_ID,WSO2_QAP_PRODUCT_NAME);
    }
}
