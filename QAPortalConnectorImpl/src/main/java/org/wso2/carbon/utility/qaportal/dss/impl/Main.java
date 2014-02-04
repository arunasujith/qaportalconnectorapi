package org.wso2.carbon.utility.qaportal.dss.impl;

import org.wso2.carbon.utility.qaportal.QAPortal;

/**
 * Created by kavith on 2/3/14.
 */
public class Main {

    public static void main(String args[]){

        QAPortal portal = new QAPortalDSSServiceClient("https","192.168.3.24",9443,
                "admin", "admin");

        portal.getProductVersions(10);
    }
}
