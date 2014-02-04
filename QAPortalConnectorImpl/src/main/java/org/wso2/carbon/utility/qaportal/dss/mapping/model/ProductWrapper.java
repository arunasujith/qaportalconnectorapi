package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.Product;

import java.io.Serializable;

/**
 * Created by kavith on 2/3/14.
 */
public class ProductWrapper{

    private int QAP_PRODUCT_ID;

    private String QAP_PRODUCT_NAME;

    public String getQAP_PRODUCT_NAME() {
        return QAP_PRODUCT_NAME;
    }

    public void setQAP_PRODUCT_NAME(String QAP_PRODUCT_NAME) {
        this.QAP_PRODUCT_NAME = QAP_PRODUCT_NAME;
    }

    public int getQAP_PRODUCT_ID() {
        return QAP_PRODUCT_ID;
    }

    public void setQAP_PRODUCT_ID(int QAP_PRODUCT_ID) {
        this.QAP_PRODUCT_ID = QAP_PRODUCT_ID;
    }

    public Product getEntity(){
        return new Product(QAP_PRODUCT_ID,QAP_PRODUCT_NAME);
    }
}
