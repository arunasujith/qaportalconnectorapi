package org.wso2.carbon.utility.qaportal.dss.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.utility.qaportal.QAPortal;
import org.wso2.carbon.utility.qaportal.dss.mapping.model.ProductWrapper;
import org.wso2.carbon.utility.qaportal.dss.util.HttpClientWrapper;
import org.wso2.carbon.utility.qaportal.dss.util.JsonUtil;
import org.wso2.carbon.utility.qaportal.dss.util.Services;
import org.wso2.carbon.utility.qaportal.model.Product;
import org.wso2.carbon.utility.qaportal.model.ProductBuild;
import org.wso2.carbon.utility.qaportal.model.ProductVersion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kavith on 2/3/14.
 */
public class QAPortalDSSServiceClient implements QAPortal{

    private Log log = LogFactory.getLog(QAPortalDSSServiceClient.class);

    private HttpClientWrapper client;

    public QAPortalDSSServiceClient(String transport, String host, int port, String username, String password){

        client= new HttpClientWrapper(transport, host, port, username, password);
    }
    @Override
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<Product>();

        List<ProductWrapper> productWrapperList = new ArrayList<ProductWrapper>();

        try {
            String json = client.get(Services.PRODUCT_SERVICE, "view/products");
            //productWrapperList = JsonUtil.getPOJOListFromJson(json, ProductWrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<ProductVersion> getProductVersions(int i) {
        return null;
    }

    @Override
    public List<ProductBuild> getProductVersionBuilds(int i) {
        return null;
    }
}
