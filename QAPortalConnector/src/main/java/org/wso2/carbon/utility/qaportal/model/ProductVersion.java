package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/3/14.
 */
public class ProductVersion {

    private int productVersionId;

    private int productId;

    private String version;

    public ProductVersion(int productVersionId, int productId, String version) {
        this.productVersionId = productVersionId;
        this.productId = productId;
        this.version = version;
    }

    public int getProductVersionId() {
        return productVersionId;
    }

    public void setProductVersionId(int productVersionId) {
        this.productVersionId = productVersionId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
