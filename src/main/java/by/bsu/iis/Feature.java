package by.bsu.iis;


public class Feature {

    private String name;
    private String value;

    public Feature(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Feature() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feature)) return false;

        Feature feature = (Feature) o;

        if (getName() != null ? !getName().equals(feature.getName()) : feature.getName() != null) return false;
        return !(getValue() != null ? !getValue().equals(feature.getValue()) : feature.getValue() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
