package org.wso2.carbon.utility.qaportal.dss.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public QAPortalDataServiceConsumerImpl() {
        client= new HttpClientWrapper("https", "192.168.3.24", 9443, "admin", "admin");
    }

    public QAPortalDataServiceConsumerImpl(String transport, String host, int port, String username, String password){

        client= new HttpClientWrapper(transport, host, port, username, password);
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> productList = new ArrayList<Product>();

        try {
            String json = client.get(Services.PRODUCT_SERVICE, Resources.ALL_PRODUCTS);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_PRODUCTCollection").path("WSO2_QAP_PRODUCT");

            productList = JsonUtil.getPOJOListFromJson(arrayNode, ProductMappingModel.class, Product.class);

        }
        catch (IOException e){

            log.warn(e.getMessage());
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<ProductVersion> getProductVersionsByProductId(int productId) {

        List<ProductVersion> productVersionList = new ArrayList<ProductVersion>();

        try {
            String json = client.get(Services.PRODUCT_VERSION_SERVICE,
                                                                Resources.PRODUCT_VERSIONS_BY_PRODUCT_ID + productId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json,"WSO2_QAP_PRODUCT_VERSIONCollection")
                                                                                .path("WSO2_QAP_PRODUCT_VERSION");

            productVersionList = JsonUtil.getPOJOListFromJson(arrayNode, ProductVersionMappingModel.class,
                                                                                        ProductVersion.class);
        }
        catch (IOException e) {

            log.warn(e.getMessage());
            e.printStackTrace();
        }

        return productVersionList;
    }

    @Override
    public List<ProductBuild> getProductBuildsByVersionId(int productVersionId) {

        List<ProductBuild> productBuildsList = new ArrayList<ProductBuild>();

        try {
            String json = client.get(Services.PRODUCT_BUILD_SERVICE, Resources.BUILDS_BY_VERSION_ID + productVersionId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_PRODUCT_BUILDCollection")
                                                                                    .path("WSO2_QAP_PRODUCT_BUILD");

            ObjectMapper mapper = JsonUtil.getDefaultMapper().setDateFormat(JsonUtil.DATE_FORMAT_I);

            productBuildsList = JsonUtil.getPOJOListFromJson(mapper, arrayNode, ProductBuildMappingModel.class,
                    ProductBuild.class);
        }
        catch (IOException e) {

            log.warn(e.getMessage());
            e.printStackTrace();
        }

        return productBuildsList;
    }

    @Override
    public List<Feature> getProductFeaturesByVersion(int productVersionId) {

        List<Feature> featureList = new ArrayList<Feature>();

        try {
            String json = client.get(Services.FEATURE_SERVICE, Resources.FEATURES_BY_VERSION + productVersionId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_FEATURECollection").path("WSO2_QAP_FEATURE");

            featureList = JsonUtil.getPOJOListFromJson(arrayNode, FeatureMappingModel.class, Feature.class);
        }
        catch (IOException e) {

            log.warn(e.getMessage());
            e.printStackTrace();
        }

        return featureList;
    }

    @Override
    public ProductBuild getProductBuild(int productBuildId) {

        ProductBuild productBuild = null;

        try {
            String json = client.get(Services.PRODUCT_BUILD_SERVICE, Resources.PRODUCT_BUILD_BY_ID + productBuildId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_PRODUCT_BUILDCollection")
                                                                                .path("WSO2_QAP_PRODUCT_BUILD");

            ObjectMapper mapper = JsonUtil.getDefaultMapper().setDateFormat(JsonUtil.DATE_FORMAT_II);

            productBuild = JsonUtil.getPOJOFromJson(mapper, arrayNode, ProductBuildMappingModel.class,
                                                                                ProductBuild.class);
        }
        catch (IOException e) {

            log.warn(e.getMessage());
            e.printStackTrace();
        }

        return productBuild;
    }

    @Override
    public Product getProductById(int productId) {

        Product product = null;

        try {
            String json = client.get(Services.PRODUCT_SERVICE, Resources.PRODUCT_BY_ID + productId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_PRODUCTCollection")
                    .path("WSO2_QAP_PRODUCT");

            product = JsonUtil.getPOJOFromJson( arrayNode, ProductMappingModel.class,
                    Product.class);
        }
        catch (IOException e) {

            log.warn(e.getMessage());
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public TestPlan getTestPlanById(int testPlanId) {

        TestPlan testPlan = null;

        try {
            String json = client.get(Services.TEST_PLAN_SERVICE, Resources.TEST_PLAN_BY_ID + testPlanId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_TEST_PLANCollection")
                    .path("WSO2_QAP_TEST_PLAN");

            testPlan = JsonUtil.getPOJOFromJson( arrayNode, TestPlanMappingModel.class,
                    TestPlan.class);
        }
        catch (IOException e) {

            log.warn(e.getMessage());
            e.printStackTrace();
        }

        return testPlan;
    }

    @Override
    public List<TestSuit> getTestSuitsByTestPlanId(int testPlanId) {

        List<TestSuit> testSuits = new ArrayList<TestSuit>();

        try {
            String json = client.get(Services.TEST_PLAN_TEST_SUIT_MAPPING_SERVICE,
                                                                        Resources.TEST_SUITS_BY_TEST_PLAN + testPlanId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_TEST_PLAN_TEST_SUIT_MAPPINGCollection")
                                                                        .path("WSO2_QAP_TEST_PLAN_TEST_SUIT_MAPPING");

            testSuits = JsonUtil.getPOJOListFromJson(arrayNode, TestSuitMappingModel.class, TestSuit.class);

        }
        catch (IOException e) {

            log.warn(e.getMessage());
            e.printStackTrace();
        }

        return testSuits;
    }

    @Override
    public List<TestSuitScenarioAssociation> getTestScenariosByTestSuitId(int testSuitId) {

        List<TestSuitScenarioAssociation> testScenarios = new ArrayList<TestSuitScenarioAssociation>();

        try {
            String json = client.get(Services.TEST_SUIT_TEST_SCENARIO_MAPPING_SERVICE,
                    Resources.TEST_SCENARIOS_BY_TEST_SUIT_ID + testSuitId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_TEST_SUIT_TEST_SCENARIO_MAPPINGCollection")
                    .path("WSO2_QAP_TEST_SUIT_TEST_SCENARIO_MAPPING");

            testScenarios = JsonUtil.getPOJOListFromJson(arrayNode, TestSuitScenarioAssociationMappingModel.class, TestSuitScenarioAssociation.class);

        }
        catch (IOException e) {

            log.warn(e.getMessage());
            e.printStackTrace();
        }

        return testScenarios;
    }

    @Override
    public TestScenario getTestScenarioById(int testScenarioId) {

        TestScenario testScenario = null;
        String json = "{}";

        try {
            json = client.get(Services.TEST_SCENARIO_SERVICE, Resources.TEST_SCENARIO_BY_ID + testScenarioId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_TEST_SCENARIOCollection")
                    .path("WSO2_QAP_TEST_SCENARIO");

            testScenario = JsonUtil.getPOJOFromJson( arrayNode, TestScenarioMappingModel.class,
                    TestScenario.class);
        }
        catch (IOException e) {

            log.warn(e.getMessage() + "\nServer Response:" + json);
        }

        return testScenario;
    }

    @Override
    public List<TestCase> getTestCasesByTestScenarioId(int testScenarioId) {
        List<TestCase> testCaseListList= new ArrayList<TestCase>();

        String json ="{}";

        try {
            json = client.get(Services.TEST_SCENARIO_TEST_CASE_MAPPING_SERVICE,
                    Resources.TEST_CASES_BY_SCENARIO + testScenarioId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json,"WSO2_QAP_TEST_SCENARIO_TEST_CASE_MAPPINGCollection")
                    .path("WSO2_QAP_TEST_SCENARIO_TEST_CASE_MAPPING");

            testCaseListList = JsonUtil.getPOJOListFromJson(arrayNode, TestCaseMappingModel.class, TestCase.class);

        }
        catch (IOException e) {

            log.warn(e.getMessage() + "\nServer Response:" + json);
        }

        return testCaseListList;
    }

    @Override
    public List<TestResult> getTestResultsByBuildId(int productBuildId) {

        List<TestResult> testResultsList= new ArrayList<TestResult>();

        String json ="{}";

        try {
            json = client.get(Services.TEST_RESULT_SERVICE,
                                                        Resources.TEST_RESULTS_BY_BUILD_ID + productBuildId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json,"WSO2_QAP_TEST_RESULTCollection")
                                                                     .path("WSO2_QAP_TEST_RESULT");

            testResultsList = JsonUtil.getPOJOListFromJson(arrayNode, TestResultMappingModel.class, TestResult.class);

        }
        catch (IOException e) {

            log.warn(e.getMessage() + "\nServer Response:" + json);
        }

        return testResultsList;
    }

    @Override
    public TestResult getTestResultByTestCaseAndBuild(int productBuildId, int testCaseId) {
        TestResult testResult = null;

        String json ="{}";

        try {
            json = client.get(Services.TEST_RESULT_SERVICE,
                    Resources.TEST_RESULT_BY_TEST_CASE_AND_BUILD + productBuildId+"/"+testCaseId);

            JsonNode node = JsonUtil.getNamedNode(json,"WSO2_QAP_TEST_RESULTFORBUILDCollection")
                    .path("WSO2_QAP_TEST_RESULTFORBUILD");

            testResult = JsonUtil.getPOJOListFromJson(node, TestResultMappingModel.class, TestResult.class).get(0);

        }
        catch (IOException e) {

            log.warn(e.getMessage() + "\nServer Response:" + json);
        }

        return testResult;
    }

    @Override
    public TestCase getTestCaseById(int testCaseId) {

        TestCase testCase = null;
        String json = "{}";

        try {
            json = client.get(Services.TEST_CASE_SERVICE, Resources.TEST_CASE_BY_ID + testCaseId);

            JsonNode arrayNode = JsonUtil.getNamedNode(json, "WSO2_QAP_TEST_CASECollection")
                    .path("WSO2_QAP_TEST_CASE");

            testCase = JsonUtil.getPOJOFromJson( arrayNode, TestCaseMappingModel.class,
                    TestCase.class);
        }
        catch (IOException e) {

            log.warn(e.getMessage() + "\nServer Response:" + json);
        }

        return testCase;
    }

    @Override
    public ProductBuild getLatestBuildOfProduct(int productId) {

        ProductBuild latestBuild = null;


        List<ProductVersion> versions = getProductVersionsByProductId(productId);

        List<ProductBuild> builds = new ArrayList<ProductBuild>();

        for(ProductVersion pV: versions){

            builds.addAll(getProductBuildsByVersionId(pV.getProductVersionId()));
        }

        latestBuild = builds.get(0);

        for(ProductBuild pB:builds){

            if(latestBuild==null){
                latestBuild = pB;
                continue;
            }

            if(pB!=null && pB.getReleaseDate().after(latestBuild.getReleaseDate())){

                latestBuild = pB;
            }
        }

        return latestBuild;
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
            ex.printStackTrace();
        }
    }
}
