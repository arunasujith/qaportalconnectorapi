package org.wso2.carbon.utility.qaportal.model;

/**
 * Created by kavith on 2/3/14.
 */
public class Sample {

    private int sampleId;

    private String sampleName;

    private String description;

    private String uRL;

    public Sample(int sampleId, String description, String sampleName, String uRL) {
        this.sampleId = sampleId;
        this.description = description;
        this.sampleName = sampleName;
        this.uRL = uRL;
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getuRL() {
        return uRL;
    }

    public void setuRL(String uRL) {
        this.uRL = uRL;
    }
}
