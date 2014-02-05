package org.wso2.carbon.utility.qaportal.dss.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.utility.qaportal.QAPortal;
import org.wso2.carbon.utility.qaportal.dss.mapping.model.*;
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
public class QAPortalDataServiceConsumerImpl implements QAPortal{

    private Log log = LogFactory.getLog(QAPortalDataServiceConsumerImpl.class);

    private HttpClientWrapper client;

    public QAPortalDataServiceConsumerImpl(String transport, String host, int port, String username, String password){

        client= new HttpClientWrapper(transport, host, port, username, password);
    }
    @Override
    public List<Product> getAllProducts() {

        List<Product> productList = new ArrayList<Product>();

        try {
            String json = client.get(Services.PRODUCT_SERVICE, Resources.ALL_PRODUCTS);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_PRODUCTCollection").path("WSO2_QAP_PRODUCT");

            if(arrayNode.isArray())
            {
                productList = JsonUtil.getPOJOListFromJson(arrayNode,
                                        new TypeReference<List<WSO2_QAP_PRODUCT>>() {}, Product.class);
            }
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
    public List<ProductVersion> getProductVersionsByProductId(int productId) {

        List<ProductVersion> productVersionList = new ArrayList<ProductVersion>();

        try {
            String json = client.get(Services.PRODUCT_VERSION_SERVICE, Resources.PRODUCT_VERSIONS_BY_PRODUCT_ID +productId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json,"WSO2_QAP_PRODUCT_VERSIONCollection").path("WSO2_QAP_PRODUCT_VERSION");

            if(arrayNode.isArray())
            {
                productVersionList = JsonUtil.getPOJOListFromJson(arrayNode,
                                        new TypeReference<List<WSO2_QAP_PRODUCT_VERSION>>() {}, ProductVersion.class);
            }
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
    public List<ProductBuild> getProductBuildsByVersionId(int productVersionId) {

        List<ProductBuild> productBuildsList = new ArrayList<ProductBuild>();

        try {
            String json = client.get(Services.PRODUCT_BUILD_SERVICE, Resources.BUILDS_BY_VERSION_ID +productVersionId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_PRODUCT_BUILDCollection").path("WSO2_QAP_PRODUCT_BUILD");

            if(arrayNode.isArray())
            {
                productBuildsList = JsonUtil.getPOJOListFromJson(arrayNode,
                    new TypeReference<List<WSO2_QAP_PRODUCT_BUILD>>() {}, ProductBuild.class);
            }
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

        return productBuildsList;
    }

    @Override
    public List<Feature> getProductFeaturesByVersion(int productVersionId) {

        List<Feature> featureList = new ArrayList<Feature>();

        try {
            String json = client.get(Services.FEATURE_SERVICE, Resources.FEATURES_BY_VERSSION +productVersionId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_FEATURECollection").path("WSO2_QAP_FEATURE");

            if(arrayNode.isArray())
            {
                featureList = JsonUtil.getPOJOListFromJson(arrayNode,
                        new TypeReference<List<WSO2_QAP_FEATURE>>() {}, Feature.class);
            }
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

        return featureList;
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

        List<TestSuit> testSuits = new ArrayList<TestSuit>();

        try {
            String json = client.get(Services.TEST_SUIT_SERVICE, Resources.FEATURES_BY_VERSSION +testPlanId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_FEATURECollection").path("WSO2_QAP_FEATURE");

            if(arrayNode.isArray())
            {
                testSuits = JsonUtil.getPOJOListFromJson(arrayNode,
                        new TypeReference<List<WSO2_QAP_TEST_SUIT>>() {}, TestSuit.class);
            }
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

        return testSuits;
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

        List<TestResult> testResultsList= new ArrayList<TestResult>();

        try {
            String json = client.get(Services.TEST_RESULT_SERVICE, Resources.TEST_RESULTS_BY_BUILD_ID +productBuildId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json,"WSO2_QAP_TEST_RESULTCollection").path("WSO2_QAP_TEST_RESULT");

            if(arrayNode.isArray())
            {
                testResultsList = JsonUtil.getPOJOListFromJson(arrayNode,
                    new TypeReference<List<WSO2_QAP_TEST_RESULT>>() {}, TestResult.class);
            }
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

        return testResultsList;
    }

    @Override
    public TestCase getTestCaseById(int testCaseId) {
        return null;
    }

    private void handleExceptions(Exception ex)
    {
        if (ex instanceof JsonMappingException){
            ex.printStackTrace();
        }
        if (ex instanceof JsonParseException){
            ex.printStackTrace();
        }
        if (ex instanceof IOException) {
            ex.printStackTrace();;
        }

    }
}
