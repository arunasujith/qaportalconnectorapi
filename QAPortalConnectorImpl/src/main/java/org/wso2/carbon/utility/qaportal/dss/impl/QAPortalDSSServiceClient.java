package org.wso2.carbon.utility.qaportal.dss.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.utility.qaportal.QAPortal;
import org.wso2.carbon.utility.qaportal.dss.mapping.model.WSO2_QAP_PRODUCT;
import org.wso2.carbon.utility.qaportal.dss.mapping.model.WSO2_QAP_PRODUCT_VERSION;
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

        List<WSO2_QAP_PRODUCT> productWrapperList = new ArrayList<WSO2_QAP_PRODUCT>();

        try {
            String json = client.get(Services.PRODUCT_SERVICE, "view/products");

            JsonNode node = JsonUtil.getNamedNode(json,"WSO2_QAP_PRODUCTCollection");

             productWrapperList = JsonUtil.getPOJOListFromJson(node.path("WSO2_QAP_PRODUCT"),new TypeReference<List<WSO2_QAP_PRODUCT>>() {});

        }
        catch (JsonMappingException jme){
            jme.printStackTrace();
        }
        catch (JsonParseException jpe){
            jpe.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<ProductVersion> getProductVersions(int productId) {

        List<ProductVersion> productVersionList = new ArrayList<ProductVersion>();

        List<WSO2_QAP_PRODUCT_VERSION> productVersionWrapperList = new ArrayList<WSO2_QAP_PRODUCT_VERSION>();

        try {
            String json = client.get(Services.PRODUCT_VERSION_SERVICE, "get/version_by_id/"+productId);

            JsonNode node = JsonUtil.getNamedNode(json,"WSO2_QAP_PRODUCT_VERSIONCollection");

            productVersionWrapperList = JsonUtil.getPOJOListFromJson(node.path("WSO2_QAP_PRODUCT_VERSION"),new TypeReference<List<WSO2_QAP_PRODUCT_VERSION>>() {});

        }
        catch (JsonMappingException jme){
            jme.printStackTrace();
        }
        catch (JsonParseException jpe){
            jpe.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return productVersionList;
    }

    @Override
    public List<ProductBuild> getProductVersionBuilds(int versionId) {
        return null;
    }
}
