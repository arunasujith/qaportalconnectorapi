package org.wso2.carbon.utility.qaportal.dss.mapping.model;

import org.wso2.carbon.utility.qaportal.model.Artifact;

/**
 * Created by kavith on 2/5/14.
 */
public class WSO2_QAP_ARTIFACT {

    public int WSO2_QAP_ARTIFACT_ID;

    public String WSO2_QAP_ARTIFACT_NAME;

    public String WSO2_QAP_DESCRIPTION;

    public String WSO2_QAP_LOCATION;


    public Artifact getEntity(){

        return new Artifact(WSO2_QAP_ARTIFACT_ID, WSO2_QAP_DESCRIPTION, WSO2_QAP_ARTIFACT_NAME, WSO2_QAP_LOCATION);
    }

}
