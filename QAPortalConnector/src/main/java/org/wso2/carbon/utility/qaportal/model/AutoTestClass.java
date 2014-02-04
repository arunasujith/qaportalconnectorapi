package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/3/14.
 */
public class AutoTestClass {

    private int autoTestClassId;

    private String testClassName;

    private String testClassLocation;

    public AutoTestClass(int autoTestClassId, String testClassLocation, String testClassName) {
        this.autoTestClassId = autoTestClassId;
        this.testClassLocation = testClassLocation;
        this.testClassName = testClassName;
    }

    public int getAutoTestClassId() {
        return autoTestClassId;
    }

    public void setAutoTestClassId(int autoTestClassId) {
        this.autoTestClassId = autoTestClassId;
    }

    public String getTestClassName() {
        return testClassName;
    }

    public void setTestClassName(String testClassName) {
        this.testClassName = testClassName;
    }

    public String getTestClassLocation() {
        return testClassLocation;
    }

    public void setTestClassLocation(String testClassLocation) {
        this.testClassLocation = testClassLocation;
    }
}
