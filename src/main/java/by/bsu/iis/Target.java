package by.bsu.iis;

/**
 * Created by Ilya_Zinkovich on 04/06/2016.
 */
public class Target {
    private String feature;
    private Integer ruleNumber;

    public Target(String feature, Integer ruleNumber) {
        this.feature = feature;
        this.ruleNumber = ruleNumber;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Integer getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(Integer ruleNumber) {
        this.ruleNumber = ruleNumber;
    }
}
