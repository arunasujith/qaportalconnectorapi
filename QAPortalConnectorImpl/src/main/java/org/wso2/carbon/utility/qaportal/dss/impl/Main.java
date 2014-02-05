package org.wso2.carbon.utility.qaportal.dss.impl;

import org.wso2.carbon.utility.qaportal.QAPortal;
import org.wso2.carbon.utility.qaportal.dss.util.JsonUtil;
import org.wso2.carbon.utility.qaportal.model.Product;

import java.util.List;

/**
 * Created by kavith on 2/3/14.
 */
public class Main {

    public static void main(String args[]){

        QAPortal portal = new QAPortalDataServiceConsumerImpl("https", "192.168.3.24", 9443, "admin", "admin");


        print("All the products in portal ",                  portal.getAllProducts()                                    );

        print("Product versions of ESB",                      portal.getProductVersionsByProductId(20)                   );

        print("Builds details for product version id 30 " ,   portal.getProductBuildsByVersionId(30)                     );

        print("Test results for build id 20 ",                portal.getTestResultsByBuildId(20)                         );
         
        print("Features by product version id 30",            portal.getProductFeaturesByVersion(30)                     );
    }









    private static void print(String label, List<? extends Object> list){

        System.out.println("****************************** " + label + ": Start *****************************\n");

        for(Object obj:list)
        {
            printJson(obj);
        }

        System.out.println("\n============================== " + label + ": End ===============================\n\n\n");
    }

    private static void printJson(Object obj){

        System.out.println(JsonUtil.getJsonFromPojo(obj));
    }
}
