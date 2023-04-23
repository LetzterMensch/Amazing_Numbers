package numbers;

import java.awt.List;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.""");
        do {
            int n;
            System.out.print("Enter a request: ");
            String request = scanner.nextLine();
            System.out.println();
            if (request.isEmpty()) {
                System.out.println("""
                        Supported requests:
                        - enter a natural number to know its properties;
                        - enter two natural numbers to obtain the properties of the list:
                          * the first parameter represents a starting number;
                          * the second parameter shows how many consecutive numbers are to be processed;
                        - two natural numbers and properties to search for;
                        - a property preceded by minus must not be present in numbers;
                        - separate the parameters with one space;
                        - enter 0 to exit.""");
                continue;
            }
            String[] input = request.toLowerCase().split(" ");
            if (input.length >= 3) {
                n = Integer.parseInt(input[1]);
                if (n < 1) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                Number[] numbers = new Number[n];
                for (int i = 0; i < n; i++) {
                    numbers[i] = new Number();
                }
                try {
                    numbers[0].value = Long.parseLong(input[0]);
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
                ArrayList<String> propertyList = new ArrayList<>(Arrays.asList("buzz", "duck", "palindromic", "gapful",
                        "spy", "even", "odd", "sunny", "square", "jumping", "happy", "sad",
                        "-buzz", "-duck", "-palindromic", "-gapful",
                        "-spy", "-even", "-odd", "-sunny", "-square", "-jumping", "-happy", "-sad"));
                ArrayList<String> properties = new ArrayList<>();
                ArrayList<String> wrongProperties = new ArrayList<>();
                HashMap<String, String> mutallyExclusiveMap = new HashMap<>();
                mutallyExclusiveMap.put("odd", "even");
                mutallyExclusiveMap.put("duck", "spy");
                mutallyExclusiveMap.put("sunny", "square");
                mutallyExclusiveMap.put("happy", "sad");
                mutallyExclusiveMap.put("-odd", "-even");
                mutallyExclusiveMap.put("-duck", "-spy");
                mutallyExclusiveMap.put("-sunny", "-square");
                mutallyExclusiveMap.put("-happy", "-sad");

                for (int i = 2; i < input.length; i++) {
                    if (!propertyList.contains(input[i])) {
                        wrongProperties.add(input[i]);
                    }
                    properties.add(input[i]);
                }
                if (wrongProperties.size() == 1) {
                    System.out.println("The property [" + wrongProperties.get(0).toUpperCase() + "] is wrong.\n" +
                            "Available properties: " + propertyList);
                    continue;
                } else if (wrongProperties.size() > 1) {

                    System.out.printf("The properties " + wrongProperties.toString().toUpperCase() + " are wrong.\n"
                            + "Available properties: " + propertyList);
                    continue;
                }
                boolean mutualExclusive = false;
                for (int i = 0; i < properties.size(); i++) {
                    if (properties.get(i).charAt(0) == '-') {
                        // happy vs -happy
                        if (properties.contains(properties.get(i).substring(1))) {
                            System.out.println("The request contains mutually exclusive properties: " + properties.get(i) +
                                    "\nThere are no numbers with these properties.");
                            mutualExclusive = true;
                            break;
                        }
                    }
                    if (properties.contains(mutallyExclusiveMap.get(properties.get(i)))) {
                        System.out.println("The request contains mutually exclusive properties: " + properties.get(i) +
                                "\nThere are no numbers with these properties.");
                        mutualExclusive = true;
                        break;
                    }
                }
                if (mutualExclusive) {
                    continue;
                }
//                if ((properties.contains("odd") && properties.contains("even"))
//                        || (properties.contains("duck") && properties.contains("spy"))
//                        || (properties.contains("sunny") && properties.contains("square"))
//                        || (properties.contains("happy") && properties.contains("sad"))
//                        || (properties.contains("-odd") && properties.contains("-even"))
//                        || (properties.contains("-duck") && properties.contains("-spy"))
//                        || (properties.contains("-sunny") && properties.contains("-square"))
//                        || (properties.contains("-happy") && properties.contains("-sad"))) {
//
//                }
                for (int i = 0; i < n; i++) {
                    if (i < n - 1) {
                        numbers[i + 1].value = Number.findProperty(propertyList, properties, numbers[i]).value + 1;
                    } else {
                        numbers[i].value = Number.findProperty(propertyList, properties, numbers[i]).value + 1;
                    }
                }
            } else if (input.length == 2) {
                n = Integer.parseInt(input[1]);
                if (n < 1) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                Number[] numbers = new Number[n];
                numbers[0] = new Number();
                try {
                    numbers[0].value = Long.parseLong(input[0]);
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
                for (int i = 0; i < n; i++) {
                    if (i > 0) {
                        numbers[i] = new Number();
                    }
                    numbers[i].value = numbers[0].value + i;
                    numbers[i].checkAllProperties();
                    numbers[i].shortDisplay();
                }
            } else if (input.length == 1) {
                Number number = new Number();
                try {
                    number.value = Long.parseLong(input[0]);
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
                if (number.value < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
                if (number.value != 0) {
                    number.checkAllProperties();
                    number.display();
                } else {
                    System.out.println("Goodbye!");
                    break;
                }
            }
        } while (true);
    }
}
