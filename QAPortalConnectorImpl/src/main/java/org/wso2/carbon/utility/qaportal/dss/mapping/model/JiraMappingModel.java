package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.Jira;

/**
 * Created by kavith on 2/5/14.
 */
public class JiraMappingModel implements MappingModel {

    public int WSO2_QAP_JIRA_ID;

    public String WSO2_QAP_JIRA_URL;

    public String WSO2_QAP_JIRA_DESCRIPTION;

    public Jira getEntity(){

        return new Jira(WSO2_QAP_JIRA_ID, WSO2_QAP_JIRA_URL, WSO2_QAP_JIRA_DESCRIPTION);
    }
}
