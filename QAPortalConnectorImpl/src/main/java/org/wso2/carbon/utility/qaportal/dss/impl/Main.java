package org.wso2.carbon.utility.qaportal.dss.impl;

import org.wso2.carbon.utility.qaportal.QAPortal;
import org.wso2.carbon.utility.qaportal.dss.util.JsonUtil;
import org.wso2.carbon.utility.qaportal.model.Product;

import java.util.List;

/**
 * Created by kavith on 2/3/14.+
 */
public class Main {

    public static void main(String args[]){

        QAPortal portal = new QAPortalDataServiceConsumerImpl("https", "192.168.3.24", 9443, "admin", "admin");


        print("All the products in portal ",                  portal.getAllProducts()                                    );

        print("Product versions of ESB",                      portal.getProductVersionsByProductId(20)                   );

        print("Builds details for product version id 30 " ,   portal.getProductBuildsByVersionId(30)                     );

        print("Test results for build id 20 ",                portal.getTestResultsByBuildId(20)                         );

        print("Features by product version id 30",            portal.getProductFeaturesByVersion(30)                     );

        print("Test Suits by test plan id 8",                 portal.getTestSuitsByTestPlanId(8)                         );

        print("Product build details of build id 20",         portal.getProductBuild(20)                                 );

        print("Product details of product id 2",              portal.getProductById(2)                                   );

        print("TestPlan details of testPlan id 7",            portal.getTestPlanById(7)                                  );

        print("Test Scenario details by id 4",                portal.getTestScenarioById(4)                              );
    }









    private static void print(String label, List<? extends Object> list){

        System.out.println("****************************** " + label + ": Start *****************************\n");

        for(Object obj:list)
        {
            printJson(obj);
        }

        System.out.println("\n============================== " + label + ": End ===============================\n\n\n");
    }

    private static void print(String label, Object obj){

        System.out.println("****************************** " + label + ": Start *****************************\n");

            printJson(obj);

        System.out.println("\n============================== " + label + ": End ===============================\n\n\n");
    }

    private static void printJson(Object obj){

        System.out.println(JsonUtil.getJsonFromPojo(obj));
    }
}
