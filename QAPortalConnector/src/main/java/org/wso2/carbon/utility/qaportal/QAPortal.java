package org.wso2.carbon.utility.qaportal;

import org.wso2.carbon.utility.qaportal.model.*;

import java.util.List;

/**
 * Created by kavith on 2/3/14.
 */
public interface QAPortal {

    /**
     * Get all the products which are tracked in the QA Portal
     *
     * @return  List of Products
     */
    public List<Product> getProducts();

    /**
     * Get product versions for a particular product
     *
     * @param productId  QA Portal product ID
     *
     * @return
     */
    public List<ProductVersion> getProductVersions(int productId);

    /**
     * Get product builds for a particular product version
     *
     * @param productVersionId
     *
     * @return
     */
    public List<ProductBuild> getProductBuilds(int productVersionId);

    /**
     * Get features of a particular product version
     *
     * @param productVersionId  Id of the product version record
     *
     * @return A list of Features
     */
    public List<Feature> getProductFeatures(int productVersionId);

    /**
     * Get a product build entity by product build id
     *
     * @param productBuildId
     *
     * @return
     */
    public ProductBuild getProductBuild(int productBuildId);

    /**
     * Get a product instance by product id
     *
     * @param productId
     *
     * @return
     */
    public Product getProductById(int productId);

    /**
     *
     * @param testPlanId
     *
     * @return
     */
    public TestPlan getTestPlanById(int testPlanId);

    /**
     *
     * @param testPlanId
     *
     * @return
     */
    public List<TestSuit> getTestSuitsByTestPlanId(int testPlanId);

    /**
     *
     * @param testSuitId
     *
     * @return
     */
    public List<TestSuitScenarioAssociation> getTestScenariosByTestSuitId(int testSuitId);

    /**
     *
     * @param testScenarioId
     *
     * @return
     */
    public TestScenario getTestScenarioById(int testScenarioId);

    /**
     *
     * @param testScenarioId
     *
     * @return
     */
    public List<TestCase> getTestCasesByTestScenarioId(int testScenarioId);

    /**
     *
     * @param productBuildId
     *
     * @return
     */
    public List<TestResult> getTestResultsByBuildId(int productBuildId);

    /**
     *
     * @param testCaseId
     *
     * @return
     */
    public TestCase getTestCaseById(int testCaseId);


}
