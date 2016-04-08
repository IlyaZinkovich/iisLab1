package by.bsu.iis;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DecisionMaker extends Application {

    private static Stack<Target> targetStack = new Stack<>();
    private static Map<Feature, Boolean> featuresTrueOrFalse = new HashMap<>();
    private static Map<String, Set<String>> featureOptions = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
//        Scanner scanner = new Scanner(new File("rules.txt"));
//        List<Rule> rules = new ArrayList<>();
//        int ruleNumber = 0;
//        while(scanner.hasNext()) {
//            String line = scanner.nextLine();
//            List<String> splits = Arrays.asList(line.split(" "));
//            String split;
//            Rule rule = new Rule();
//            Iterator<String> it = splits.iterator();
//            for (split = it.next(); it.hasNext(); split = it.next()) {
//                if ("then".equals(split)) {
//                    String[] that = it.next().split("=");
//                    Feature thatFeature = new Feature(that[0], that[1]);
//                    featureOptions.putIfAbsent(that[0], new HashSet<>());
//                    featureOptions.get(that[0]).add(that[1]);
//                    featuresTrueOrFalse.put(thatFeature, null);
//                    rule.setThenThat(thatFeature);
//                    rule.setRuleNumber(ruleNumber++);
//                    break;
//                } else {
//                    String[] feature = split.split("=");
//                    featureOptions.putIfAbsent(feature[0], new HashSet<>());
//                    featureOptions.get(feature[0]).add(feature[1]);
//                    Feature f = new Feature(feature[0], feature[1]);
//                    featuresTrueOrFalse.put(f, null);
//                    rule.getIfAllOfThese().add(f);
//                }
//            }
//            rules.add(rule);
//        }
//
//        System.out.println(rules);
//        boolean logical = false;
//        targetStack.push(new Target("family", null));
//        Scanner inScanner = new Scanner(System.in);
//        while (!logical) {
//            boolean found = false;
//            for (Rule r : rules) {
//                if (r.getValue() != null && !r.getValue()) continue;
//                if (targetStack.isEmpty()) {
//                    logical = true;
//                    break;
//                }
//                if (r.getThenThat().getName().equals(targetStack.peek().getFeature())) {
//                    found = true;
//                    if (r.getValue() != null && r.getValue()) {
//                        featuresTrueOrFalse.put(r.getThenThat(), true);
//                        for (Feature f : featuresTrueOrFalse.keySet()) {
//                            if (r.getThenThat().getName().equals(f.getName()) && !r.getThenThat().getValue().equals(f.getValue())) {
//                                featuresTrueOrFalse.put(f, false);
//                            }
//                        }
//                        targetStack.pop();
//                    } else if (r.getValue() != null) {
//                        continue;
//                    } else {
//                        List<Feature> features = r.getIfAllOfThese();
//                        int positiveFeatures = 0;
//                        for (Feature f : features) {
//                            if (featuresTrueOrFalse.get(f) == null) {
//                                targetStack.push(new Target(f.getName(), r.getRuleNumber()));
//                                break;
//                            } else if (featuresTrueOrFalse.get(f).equals(false)) {
//                                r.setValue(false);
//                                break;
//                            }
//                            positiveFeatures++;
//                        }
//                        if (positiveFeatures == features.size()) {
//                            r.setValue(true);
//                        }
//                        break;
//                    }
//                }
//            }
//            if (!found) {
//                if (!targetStack.isEmpty() && targetStack.size() > 1) {
//                    System.out.println(targetStack.peek().getFeature() + " ???");
//                    String answer = inScanner.next();
//                    Target toPop = targetStack.pop();
//                    for (Feature f : featuresTrueOrFalse.keySet()) {
//                        if (f.getName().equals(toPop.getFeature())) {
//                            if (f.getValue().equals(answer)) {
//                                featuresTrueOrFalse.put(f, true);
//                            } else {
//                                featuresTrueOrFalse.put(f, false);
//                            }
//                        }
//                    }
//                } else {
//                    logical = true;
//                }
//            }
//        }
//        for (Feature f : featuresTrueOrFalse.keySet()) {
//            if ("family".equals(f.getName())) {
//                if (featuresTrueOrFalse.get(f) != null) {
//                    System.out.println("family is " + f.getValue() + " : " + featuresTrueOrFalse.get(f));
//                }
//                else {
//                    System.out.println("family is " + f.getValue() + " : " + "nobody knows");
//                }
//            }
//        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view.fxml"));
        primaryStage.setTitle("Choose your Musical Instrument");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.show();
    }
}
