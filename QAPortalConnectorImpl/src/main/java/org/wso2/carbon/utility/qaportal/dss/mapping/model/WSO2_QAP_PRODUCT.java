package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.Product;

import java.io.Serializable;

/**
 * Created by kavith on 2/3/14.
 */
public class WSO2_QAP_PRODUCT implements Serializable{

    public int WSO2_QAP_PRODUCT_ID;

    public String  WSO2_QAP_PRODUCT_NAME;

    public int getWSO2_QAP_PRODUCT_ID() {
        return WSO2_QAP_PRODUCT_ID;
    }

    public void setWSO2_QAP_PRODUCT_ID(int WSO2_QAP_PRODUCT_ID) {
        this.WSO2_QAP_PRODUCT_ID = WSO2_QAP_PRODUCT_ID;
    }

    public String getWSO2_QAP_PRODUCT_NAME() {
        return WSO2_QAP_PRODUCT_NAME;
    }

    public void setWSO2_QAP_PRODUCT_NAME(String WSO2_QAP_PRODUCT_NAME) {
        this.WSO2_QAP_PRODUCT_NAME = WSO2_QAP_PRODUCT_NAME;
    }

    public Product getEntity(){
        return new Product(WSO2_QAP_PRODUCT_ID,WSO2_QAP_PRODUCT_NAME);
    }
}
