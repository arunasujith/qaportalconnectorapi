package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/3/14.
 */
public class Jira {

    private int jiraId;

    private String url;

    private String description;

    public Jira(int jiraId, String url, String description) {
        this.jiraId = jiraId;
        this.url = url;
        this.description = description;
    }

    public int getJiraId() {
        return jiraId;
    }

    public void setJiraId(int jiraId) {
        this.jiraId = jiraId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
