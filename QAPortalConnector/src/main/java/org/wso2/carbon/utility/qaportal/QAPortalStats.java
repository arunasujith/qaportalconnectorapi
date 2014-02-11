package org.wso2.carbon.utility.qaportal;

import org.wso2.carbon.utility.qaportal.model.TestResult;

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

    public static Map<String,Double> getTestStatus(List<TestResult> results){

        double total = results.size();

        int inPogressCount = 0, passedCount = 0, failedCount = 0;

        Map<String,Double> map = new HashMap<String, Double>();

        for(TestResult tR : results)
        {
            if(tR.getTestStatus().equals(TEST_STATUS_IN_PROGRESS)){
                inPogressCount++;
            } else if(tR.getTestStatus().equals(TEST_STATUS_PASSED)){
                passedCount++;
            } else if(tR.getTestStatus().equals(TEST_STATUS_FAILED)){
                failedCount++;
            }
        }

        map.put(TEST_STATUS_IN_PROGRESS, (inPogressCount/total)*100);
        map.put(TEST_STATUS_PASSED, (passedCount/total)*100);
        map.put(TEST_STATUS_FAILED, (failedCount/total)*100);

        return map;

    }
}
