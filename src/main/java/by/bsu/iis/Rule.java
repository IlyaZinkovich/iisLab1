package by.bsu.iis;


import java.util.ArrayList;
import java.util.List;

public class Rule {

    private List<Feature> ifAllOfThese;
    private Feature thenThat;
    private int ruleNumber;
    private Boolean value;

    public Rule() {
        this.ifAllOfThese = new ArrayList<>();
    }

    public List<Feature> getIfAllOfThese() {
        return ifAllOfThese;
    }

    public void setIfAllOfThese(List<Feature> ifAllOfThese) {
        this.ifAllOfThese = ifAllOfThese;
    }

    public Feature getThenThat() {
        return thenThat;
    }

    public void setThenThat(Feature thenThat) {
        this.thenThat = thenThat;
    }

    public int getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(int ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "ifAllOfThese=" + ifAllOfThese +
                ", thenThat=" + thenThat +
                ", ruleNumber=" + ruleNumber +
                ", value=" + value +
                '}';
    }
}
