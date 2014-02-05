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
import org.wso2.carbon.utility.qaportal.dss.util.Resources;
import org.wso2.carbon.utility.qaportal.dss.util.Services;
import org.wso2.carbon.utility.qaportal.model.*;

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

        try {
            String json = client.get(Services.PRODUCT_SERVICE, Resources.ALL_PRODUCTS);

            JsonNode node = JsonUtil.getNamedNode(json, "WSO2_QAP_PRODUCTCollection");

            productList = JsonUtil.getPOJOListFromJson(node.path("WSO2_QAP_PRODUCT"),
                                        new TypeReference<List<WSO2_QAP_PRODUCT>>() {}, Product.class);

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

        try {
            String json = client.get(Services.PRODUCT_VERSION_SERVICE, Resources.PRODUCT_VERSIONS+productId);

            JsonNode node = JsonUtil.getNamedNode(json,"WSO2_QAP_PRODUCT_VERSIONCollection");

            productVersionList = JsonUtil.getPOJOListFromJson(node.path("WSO2_QAP_PRODUCT_VERSION"),
                                        new TypeReference<List<WSO2_QAP_PRODUCT_VERSION>>() {}, ProductVersion.class);

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
    public List<ProductBuild> getProductBuilds(int productVersionId) {
        return null;
    }

    @Override
    public List<Feature> getProductFeatures(int productVersionId) {
        return null;
    }

    @Override
    public ProductBuild getProductBuild(int productBuildId) {
        return null;
    }

    @Override
    public Product getProductById(int productId) {
        return null;
    }

    @Override
    public TestPlan getTestPlanById(int testPlanId) {
        return null;
    }

    @Override
    public List<TestSuit> getTestSuitsByTestPlanId(int testPlanId) {
        return null;
    }

    @Override
    public List<TestSuitScenarioAssociation> getTestScenariosByTestSuitId(int testSuitId) {
        return null;
    }

    @Override
    public TestScenario getTestScenarioById(int testScenarioId) {
        return null;
    }

    @Override
    public List<TestCase> getTestCasesByTestScenarioId(int testScenarioId) {
        return null;
    }

    @Override
    public List<TestResult> getTestResultsByBuildId(int productBuildId) {
        return null;
    }

    @Override
    public TestCase getTestCaseById(int testCaseId) {
        return null;
    }
}
