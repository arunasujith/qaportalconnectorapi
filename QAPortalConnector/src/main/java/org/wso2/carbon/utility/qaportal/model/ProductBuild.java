package org.wso2.carbon.utility.qaportal.model;

import java.util.Date;

/**
 * Created by kavith on 2/3/14.
 */
public class ProductBuild {

    private int buildId;

    private int productVersionId;

    private String buildName;

    private Date releaseDate;

    private int testPlanId;

    public ProductBuild(int buildId, String buildName, int productVersionId, Date releaseDate, int testPlanId) {
        this.buildId = buildId;
        this.buildName = buildName;
        this.productVersionId = productVersionId;
        this.releaseDate = releaseDate;
        this.testPlanId = testPlanId;
    }

    public int getProductVersionId() {
        return productVersionId;
    }

    public void setProductVersionId(int productVersionId) {
        this.productVersionId = productVersionId;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public int getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(int testPlanId) {
        this.testPlanId = testPlanId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
