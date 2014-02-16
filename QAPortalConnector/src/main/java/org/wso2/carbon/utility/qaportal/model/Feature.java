package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/3/14.
 */
public class Feature {

    private int featureId;

    private String description;

    private String redmineURL;

    private String subject;

    public Feature(int featureId, String description, String redmineURL, String subject) {
        this.featureId = featureId;
        this.description = description;
        this.redmineURL = redmineURL;
        this.subject = subject;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
