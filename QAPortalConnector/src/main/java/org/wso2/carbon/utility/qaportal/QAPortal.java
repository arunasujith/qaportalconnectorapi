package org.wso2.carbon.utility.qaportal;

import org.wso2.carbon.utility.qaportal.model.Product;
import org.wso2.carbon.utility.qaportal.model.ProductBuild;
import org.wso2.carbon.utility.qaportal.model.ProductVersion;

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
    public List<ProductBuild> getProductVersionBuilds(int productVersionId);

}
