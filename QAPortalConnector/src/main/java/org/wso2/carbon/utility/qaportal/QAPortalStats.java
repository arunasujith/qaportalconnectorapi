package org.wso2.carbon.utility.qaportal;

import org.wso2.carbon.utility.qaportal.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kavith on 2/10/14.
 */
public class QAPortalStats {

    public static String TEST_STATUS_IN_PROGRESS = "IN PROGRESS";

    public static String TEST_STATUS_PASSED = "PASSED";

    public static String TEST_STATUS_FAILED = "FAILED";

    public static String TEST_STATUS_NOT_EXECUTED = "NOT EXECUTED";

    public static Map<String,Double> getTestStatus(List<TestResult> results){

        double total = results.size();

        int inPogressCount = 0, passedCount = 0, failedCount = 0, notExecutedCount = 0;

        Map<String,Double> map = new HashMap<String, Double>();

        for(TestResult tR : results)
        {
            if(tR.getTestStatus().equals(TEST_STATUS_IN_PROGRESS)){
                inPogressCount++;
            } else if(tR.getTestStatus().equals(TEST_STATUS_PASSED)){
                passedCount++;
            } else if(tR.getTestStatus().equals(TEST_STATUS_FAILED)){
                failedCount++;
            } else if(tR.getTestStatus().equals(TEST_STATUS_NOT_EXECUTED)){
                notExecutedCount++;
            }

        }
        map.put(TEST_STATUS_IN_PROGRESS, (inPogressCount/total)*100);
        map.put(TEST_STATUS_PASSED, (passedCount/total)*100);
        map.put(TEST_STATUS_FAILED, (failedCount/total)*100);
        map.put(TEST_STATUS_NOT_EXECUTED, (notExecutedCount/total)*100);

        return map;

    }

    public static Map<String, Map<String,Integer>> getTestStatusByOwners(QAPortal portal, ProductBuild build){

        Map<String, Map<String,Integer>> map = new HashMap<String, Map<String, Integer>>();

        Map<String, List<TestCase>> data = getTestCasesByOwner(portal,getTestScenariosForBuild(portal, build));

        List<TestResult> results = portal.getTestResultsByBuildId(build.getBuildId());


        /////////////////////////////////continue

        return map;
    }

    private static Map<String, Integer> getTestStatusSummary(List<TestCase> testCases, List<TestResult> testResults){

        Map<String, Integer> data = new HashMap<String, Integer>();

        data.put(TEST_STATUS_PASSED,new Integer(0));
        data.put(TEST_STATUS_FAILED,new Integer(0));
        data.put(TEST_STATUS_IN_PROGRESS,new Integer(0));

        for(TestResult tR: testResults){

            if(tR.getTestStatus().equals(TEST_STATUS_PASSED)){

            }else if(tR.getTestStatus().equals(TEST_STATUS_FAILED)){

            }else if(tR.getTestStatus().equals(TEST_STATUS_IN_PROGRESS)){

            }
            testResults.remove(tR);
        }

        int notExecuted = testCases.size() - testResults.size();
        data.put(TEST_STATUS_NOT_EXECUTED,new Integer(notExecuted));

        return data;
    }

    private static Map<String, List<TestCase>> getTestCasesByOwner(QAPortal portal,  List<TestSuitScenarioAssociation> testScenarios){

        Map<String, List<TestCase>> data = new HashMap<String, List<TestCase>>();

        for(TestSuitScenarioAssociation entry:testScenarios)
        {

            if(data.containsKey(entry.getOwner())){

                data.get(entry.getOwner()).addAll(portal.getTestCasesByTestScenarioId(entry.getTestScenarioId()));
            }
            else
            {
                data.put(entry.getOwner(), portal.getTestCasesByTestScenarioId(entry.getTestScenarioId()));
            }
        }

        return data;
    }

    private static Map<String, List<TestCase>> getTestCasesByPriority(QAPortal portal,  List<TestSuitScenarioAssociation> testScenarios){

        Map<String, List<TestCase>> data = new HashMap<String, List<TestCase>>();

        for(TestSuitScenarioAssociation entry:testScenarios)
        {

            if(data.containsKey(entry.getPriority())){

                data.get(entry.getPriority()).addAll(portal.getTestCasesByTestScenarioId(entry.getTestScenarioId()));
            }
            else
            {
                data.put(entry.getPriority(), portal.getTestCasesByTestScenarioId(entry.getTestScenarioId()));
            }
        }

        return data;
    }

    private static List<TestSuitScenarioAssociation> getTestScenariosForBuild(QAPortal portal, ProductBuild build){

        TestPlan plan = portal.getTestPlanById(build.getTestPlanId());

        List<TestSuit> testSuits = portal.getTestSuitsByTestPlanId(plan.getTestPlanId());

        List<TestSuitScenarioAssociation> testScenarios = new ArrayList<TestSuitScenarioAssociation>();

        for(TestSuit tS : testSuits)
        {
            testScenarios.addAll(portal.getTestScenariosByTestSuitId(tS.getTestSuitId()));

        }
        return testScenarios;

    }

}
