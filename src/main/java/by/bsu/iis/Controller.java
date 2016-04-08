package by.bsu.iis;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Button next;
    @FXML
    private ComboBox<String> options;
    @FXML
    private ListView<String> list;

    private Stack<Target> targetStack = new Stack<>();
    private Map<Feature, Boolean> featuresTrueOrFalse = new HashMap<>();
    private Map<String, Set<String>> featureOptions = new HashMap<>();
    private List<Rule> rules = new ArrayList<>();
    private boolean logical = false;
    private String target;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("rules.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int ruleNumber = 0;
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            List<String> splits = Arrays.asList(line.split(" "));
            String split;
            Rule rule = new Rule();
            Iterator<String> it = splits.iterator();
            for (split = it.next(); it.hasNext(); split = it.next()) {
                if ("then".equals(split)) {
                    String[] that = it.next().split("=");
                    Feature thatFeature = new Feature(that[0], that[1]);
                    featureOptions.putIfAbsent(that[0], new HashSet<>());
                    featureOptions.get(that[0]).add(that[1]);
                    featuresTrueOrFalse.put(thatFeature, null);
                    rule.setThenThat(thatFeature);
                    rule.setRuleNumber(ruleNumber++);
                    break;
                } else {
                    String[] feature = split.split("=");
                    featureOptions.putIfAbsent(feature[0], new HashSet<>());
                    featureOptions.get(feature[0]).add(feature[1]);
                    Feature f = new Feature(feature[0], feature[1]);
                    featuresTrueOrFalse.put(f, null);
                    rule.getIfAllOfThese().add(f);
                }
            }
            rules.add(rule);
        }

        featureOptions.keySet().forEach(key -> options.getItems().add(key));


    }

    @FXML
    public void onNext() {
        if (targetStack.isEmpty()) {
            target = options.getValue();
            targetStack.push(new Target(options.getValue(), null));
        } else {
            Target toPop = targetStack.pop();
            for (Feature f : featuresTrueOrFalse.keySet()) {
                if (f.getName().equals(toPop.getFeature())) {
                    if (f.getValue().equals(options.getValue())) {
                        featuresTrueOrFalse.put(f, true);
                    } else {
                        featuresTrueOrFalse.put(f, false);
                    }
                }
            }
        }
        while (!logical) {
            boolean found = false;
            for (Rule r : rules) {
                if (r.getValue() != null && !r.getValue()) continue;
                if (targetStack.isEmpty()) {
                    logical = true;
                    break;
                }
                if (r.getThenThat().getName().equals(targetStack.peek().getFeature())) {
                    found = true;
                    if (r.getValue() != null && r.getValue()) {
                        featuresTrueOrFalse.put(r.getThenThat(), true);
                        for (Feature f : featuresTrueOrFalse.keySet()) {
                            if (r.getThenThat().getName().equals(f.getName()) && !r.getThenThat().getValue().equals(f.getValue())) {
                                featuresTrueOrFalse.put(f, false);
                            }
                        }
                        targetStack.pop();
                    } else if (r.getValue() != null) {
                        continue;
                    } else {
                        List<Feature> features = r.getIfAllOfThese();
                        int positiveFeatures = 0;
                        for (Feature f : features) {
                            if (featuresTrueOrFalse.get(f) == null) {
                                targetStack.push(new Target(f.getName(), r.getRuleNumber()));
                                break;
                            } else if (featuresTrueOrFalse.get(f).equals(false)) {
                                r.setValue(false);
                                break;
                            }
                            positiveFeatures++;
                        }
                        if (positiveFeatures == features.size()) {
                            r.setValue(true);
                        }
                        break;
                    }
                }
            }
            if (!found) {
                if (!targetStack.isEmpty() && targetStack.size() > 1) {
                    options.getItems().clear();
                    featureOptions.get(targetStack.peek().getFeature()).forEach(f -> options.getItems().add(f));
                    title.setText(targetStack.peek().getFeature() + " ?");
                    return;
                } else {
                    logical = true;
                }
            }
        }
        if (logical) {
            StringBuilder results = new StringBuilder();
            for (Feature f : featuresTrueOrFalse.keySet()) {
                if (target.equals(f.getName())) {
                    if (featuresTrueOrFalse.get(f) != null) {
                        list.getItems().add(target + " is " + f.getValue() + " : " + featuresTrueOrFalse.get(f));
                    } else {
                        list.getItems().add(target + " is " + f.getValue() + " : " + "nobody knows");
                    }
                }
            }
            title.setText(results.toString());
            options.setVisible(false);
            next.setVisible(false);
        }
    }
}