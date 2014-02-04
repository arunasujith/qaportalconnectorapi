package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/3/14.
 */
public class Feature {

    private int featureId;

    private String description;

    private String redmineURL;

    public Feature(int featureId, String description, String redmineURL) {
        this.featureId = featureId;
        this.description = description;
        this.redmineURL = redmineURL;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRedmineURL() {
        return redmineURL;
    }

    public void setRedmineURL(String redmineURL) {
        this.redmineURL = redmineURL;
    }
}
