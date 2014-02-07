package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.Feature;

/**
 * Created by kavith on 2/5/14.
 */
public class FeatureMappingModel implements MappingModel{

    public int WSO2_QAP_FEATURE_ID;

    public String WSO2_QAP_DESCRIPTION;

    public String WSO2_QAP_REDMINE_URL;

    public Feature getEntity(){

        return new Feature(WSO2_QAP_FEATURE_ID, WSO2_QAP_DESCRIPTION, WSO2_QAP_REDMINE_URL);
    }
}
