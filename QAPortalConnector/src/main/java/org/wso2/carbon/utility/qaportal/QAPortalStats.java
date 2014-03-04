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

    public static String TEST_AUTOMATION_STATUS_COVERED = "YES";

    public static String TEST_AUTOMATION_STATUS_NOT_COVERED = "NO";

    public static Map<String,Integer> getTestStatus(QAPortal portal, ProductBuild build){

        List<TestResult> results =  portal.getTestResultsByBuildId(build.getBuildId());

        int total = getTestCasesForBuild(portal, build).size();

        int inPogressCount = 0, passedCount = 0, failedCount = 0;

        Map<String,Integer> map = new HashMap<String, Integer>();

        for(TestResult tR : results)
        {
            if(tR == null){
                continue;
            }
            if(tR.getTestStatus().equals(TEST_STATUS_IN_PROGRESS)){
                inPogressCount++;
            } else if(tR.getTestStatus().equals(TEST_STATUS_PASSED)){
                passedCount++;
            } else if(tR.getTestStatus().equals(TEST_STATUS_FAILED)){
                failedCount++;
            }
        }
        map.put(TEST_STATUS_IN_PROGRESS, inPogressCount);
        map.put(TEST_STATUS_PASSED, passedCount);
        map.put(TEST_STATUS_FAILED, failedCount);
        map.put(TEST_STATUS_NOT_EXECUTED, (total - results.size()));

        return map;
    }

    public static Map<String,Integer> getAutomationTestStatus(QAPortal portal, ProductBuild build){

        Map<String,Integer> map = new HashMap<String, Integer>();

        map.put(TEST_STATUS_PASSED,new Integer(0));
        map.put(TEST_STATUS_FAILED,new Integer(0));

        List<TestCase> allTests = getTestCasesForBuild(portal, build);

        for(TestCase tC:allTests){


            TestResult tR = portal.getTestResultByTestCaseAndBuild(build.getBuildId(), tC.getTestCaseId());


            if(tR == null){
                continue;
            }

            if(tR.getTestStatus().equals(TEST_STATUS_PASSED)){
                map.put(TEST_STATUS_PASSED, map.get(TEST_STATUS_PASSED) + 1);

            }else if(tR.getTestStatus().equals(TEST_STATUS_FAILED)){

                map.put(TEST_STATUS_FAILED, map.get(TEST_STATUS_FAILED) + 1);

            }

        }

        return map;

    }

   public static Map<String,Integer> getAutomationTestCoverage(QAPortal portal, ProductBuild build){
       Map<String,Integer> map = new HashMap<String, Integer>();

       map.put(TEST_AUTOMATION_STATUS_COVERED,new Integer(0));
       map.put(TEST_AUTOMATION_STATUS_NOT_COVERED,new Integer(0));

       List<TestCase> allTests = getTestCasesForBuild(portal, build);

       for(TestCase tC:allTests){

           TestResult tR = portal.getTestResultByTestCaseAndBuild(build.getBuildId(), tC.getTestCaseId());

           if(tR == null){
               continue;
           }

           if(tR.getTestStatus().equals(TEST_AUTOMATION_STATUS_COVERED)){
               map.put(TEST_AUTOMATION_STATUS_COVERED, map.get(TEST_AUTOMATION_STATUS_COVERED) + 1);

           }else if(tR.getTestStatus().equals(TEST_AUTOMATION_STATUS_NOT_COVERED)){

               map.put(TEST_AUTOMATION_STATUS_NOT_COVERED, map.get(TEST_AUTOMATION_STATUS_NOT_COVERED) + 1);

           }

       }

       return map;

    }

    public static Map<String, Map<String,Integer>> getTestStatusByOwners(QAPortal portal, ProductBuild build){

        Map<String, Map<String,Integer>> map = new HashMap<String, Map<String, Integer>>();

        Map<String, List<TestCase>> data = getTestCasesByOwner(portal,getTestScenariosForBuild(portal, build));

        for (Map.Entry<String, List<TestCase>> entry : data.entrySet())
        {
            map.put(entry.getKey(), getTestStatusSummary(portal, entry.getValue(), build));

        }

        return map;
    }

    public static Map<String, Map<String,Integer>> getTestStatusByPriority(QAPortal portal, ProductBuild build){

        Map<String, Map<String,Integer>> map = new HashMap<String, Map<String, Integer>>();

        Map<String, List<TestCase>> data = getTestCasesByPriority(portal, getTestScenariosForBuild(portal, build));

        for (Map.Entry<String, List<TestCase>> entry : data.entrySet())
        {
            map.put(entry.getKey(), getTestStatusSummary(portal, entry.getValue(), build));

        }

        return map;

    }

    private static Map<String, Integer> getTestStatusSummary(QAPortal portal, List<TestCase> testCases, ProductBuild build){

        Map<String, Integer> data = new HashMap<String, Integer>();
        List<TestResult> testResults = new ArrayList<TestResult>();

        data.put(TEST_STATUS_PASSED,new Integer(0));
        data.put(TEST_STATUS_FAILED,new Integer(0));
        data.put(TEST_STATUS_IN_PROGRESS,new Integer(0));

        for(TestCase tC:testCases){

            testResults.add(portal.getTestResultByTestCaseAndBuild(build.getBuildId(),tC.getTestCaseId()));

        }

        int notExecuted = testCases.size() - testResults.size();
        data.put(TEST_STATUS_NOT_EXECUTED,new Integer(notExecuted));

        for(TestResult tR: testResults){

            if(tR == null){
                continue;
            }

            if(tR.getTestStatus().equals(TEST_STATUS_PASSED)){
               data.put(TEST_STATUS_PASSED, data.get(TEST_STATUS_PASSED) + 1);

            }else if(tR.getTestStatus().equals(TEST_STATUS_FAILED)){

                data.put(TEST_STATUS_FAILED, data.get(TEST_STATUS_FAILED) + 1);

            }else if(tR.getTestStatus().equals(TEST_STATUS_IN_PROGRESS)){

                data.put(TEST_STATUS_IN_PROGRESS, data.get(TEST_STATUS_IN_PROGRESS) + 1);
            }
        }


        return data;
    }

    private static Map<String, List<TestCase>> getTestCasesByOwner(QAPortal portal,  List<TestSuitScenarioAssociation> testScenarios){

        Map<String, List<TestCase>> data = new HashMap<String, List<TestCase>>();

        for(TestSuitScenarioAssociation entry:testScenarios)
        {

            if(entry == null){
                continue;
            }
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

            if(entry == null){
                continue;
            }
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

    private static List<TestCase> getTestCasesForBuild(QAPortal portal, ProductBuild build){

        List<TestCase> testCaseList = new ArrayList<TestCase>();

        for(TestSuitScenarioAssociation tS:getTestScenariosForBuild(portal,build)){

            if(tS!=null)
            {
            testCaseList.addAll(portal.getTestCasesByTestScenarioId(tS.getTestScenarioId()));
            }
        }

        return testCaseList;

    }


}
