package numbers;

import java.util.ArrayList;
import java.util.HashMap;

public class Number {
    Long value;
    boolean isOdd;
    boolean isBuzz;
    boolean isDuck;
    boolean isPalindromic;
    boolean isSpy;
    boolean isGapful;
    boolean isSquare;
    boolean isSunny;
    boolean isJumping;
    boolean isHappy;

    Number(Long value) {
        this.value = value;
        this.isOdd = false;
        this.isBuzz = false;
        this.isDuck = false;
        this.isPalindromic = true;
        this.isGapful = false;
        this.isSpy = false;
        this.isSquare = false;
        this.isSunny = false;
        this.isJumping = true;
        this.isHappy = false;
    }

    Number() {
        this.isOdd = false;
        this.isBuzz = false;
        this.isDuck = false;
        this.isPalindromic = true;
        this.isGapful = false;
        this.isSpy = false;
        this.isSquare = false;
        this.isSunny = false;
        this.isJumping = true;
        this.isHappy = false;
    }

    public void checkAllProperties() {
        //Odd checking
        if ((this.value % 2) != 0) {
            this.isOdd = true;
        }
        long lastDigit = this.value % 10;
        // Buzz checking
        if ((lastDigit == 7) || ((this.value % 7 == 0))) {
            this.isBuzz = true;
        }
        // Duck checking
        boolean isLegitNum = false;
        for (int i = 0; i < this.value.toString().length(); i++) {
            if (this.value.toString().charAt(i) == '0' && !isLegitNum) {
                i++;
            } else if (this.value.toString().charAt(i) != '0' && !isLegitNum) {
                isLegitNum = true;
            }
            if (this.value.toString().charAt(i) == '0' && isLegitNum) {
                this.isDuck = true;
                break;
            }
        }
        // Palindromic Checking
        for (int i = 0; i < Math.round((float) this.value.toString().length() / 2); i++) {
            if (this.value.toString().charAt(i) != this.value.toString().charAt(this.value.toString().length() - i - 1)) {
                this.isPalindromic = false;
                break;
            }
        }
        // Gapful checking
        if (this.value.toString().length() >= 3) {
            int concat = Character.getNumericValue(this.value.toString().charAt(0)) * 10 +
                    Character.getNumericValue(
                            this.value.toString().charAt(
                                    this.value.toString().length() - 1));
            if (this.value % concat == 0) {
                this.isGapful = true;
            }
        }
        //Spy Checking
        char[] chars = this.value.toString().toCharArray();
        int[] digits = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            digits[i] = Character.getNumericValue(chars[i]);
        }
        int sum = 0;
        int product = 1;
        for (int digit : digits) {
            sum += digit;
            product *= digit;
        }
        if (sum == product) {
            isSpy = true;
        }
        //Square Checking
        if (Math.pow((int) Math.sqrt(this.value), 2) == this.value) {
            isSquare = true;
        }
        //Sunny Checking
        long n = this.value + 1;
        if (Math.pow((int) Math.sqrt(n), 2) == (n)) {
            isSunny = true;
        }
        //Jumping checking
        int first = Character.getNumericValue(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if (Math.abs(Character.getNumericValue(chars[i]) - first) == 1) {
                first = Character.getNumericValue(chars[i]);
                isJumping = true;
            } else {
                isJumping = false;
                break;
            }
        }
        //Happy checking
        boolean endingFlag = false;
        sum = 0;
        ArrayList<Integer> seenNumber = new ArrayList<>();
        do {
            for (char aChar : chars) {
                sum += Math.pow(Character.getNumericValue(aChar), 2);
            }
            if (seenNumber.contains(sum)) {
                break;
            } else {
                seenNumber.add(sum);
            }
            if (sum == 1) {
                this.isHappy = true;
                break;
            }
            if (sum == this.value) {
                break;
            }
            chars = String.valueOf(sum).toCharArray();
            sum = 0;
        } while (true);
    }

    public static String boolToString(boolean a) {
        if (a) {
            return "true";
        } else {
            return "false";
        }
    }

    public void display() {
        System.out.println("Properties of " + this.value);
        System.out.printf("%12s: %s\n", "buzz", Number.boolToString(this.isBuzz));
        System.out.printf("%12s: %s\n", "duck", Number.boolToString(this.isDuck));
        System.out.printf("%12s: %s\n", "palindromic", Number.boolToString(this.isPalindromic));
        System.out.printf("%12s: %s\n", "gapful", Number.boolToString(this.isGapful));
        System.out.printf("%12s: %s\n", "spy", Number.boolToString(this.isSpy));
        System.out.printf("%12s: %s\n", "square", Number.boolToString(this.isSquare));
        System.out.printf("%12s: %s\n", "sunny", Number.boolToString(this.isSunny));
        System.out.printf("%12s: %s\n", "jumping", Number.boolToString(this.isJumping));
        System.out.printf("%12s: %s\n", "happy", Number.boolToString(this.isHappy));
        System.out.printf("%12s: %s\n", "sad", Number.boolToString(!this.isHappy));
        System.out.printf("%12s: %s\n", "even", Number.boolToString(!this.isOdd));
        System.out.printf("%12s: %s\n", "odd", Number.boolToString(this.isOdd));
    }

    public void shortDisplay() {
        System.out.printf("%d is ", this.value);
        if (this.isBuzz) {
            System.out.print("buzz, ");
        }
        if (this.isDuck) {
            System.out.print("duck, ");
        }
        if (this.isPalindromic) {
            System.out.print("palindromic, ");
        }
        if (this.isGapful) {
            System.out.print("gapful, ");
        }
        if (this.isSpy) {
            System.out.print("spy, ");
        }
        if (this.isSquare) {
            System.out.print("square, ");
        }
        if (this.isSunny) {
            System.out.print("sunny, ");
        }
        if (this.isJumping) {
            System.out.print("jumping, ");
        }
        if (this.isHappy) {
            System.out.print("happy, ");
        } else {
            System.out.print("sad, ");
        }
        if (this.isOdd) {
            System.out.println("odd");
        } else {
            System.out.println("even");
        }

    }

    public static Number findProperty(ArrayList<String> propertyList, ArrayList<String> properties, Number number) {
        boolean manyProperties;
        do {
            HashMap<String, Integer> numberMap = new HashMap<>();
            for (String s : propertyList) {
                numberMap.put(s, 0);
            }
            number.checkAllProperties();
            if (number.isSpy) numberMap.put("spy", 1);
            if (number.isPalindromic) numberMap.put("palindromic", 1);
            if (number.isBuzz) numberMap.put("buzz", 1);
            if (number.isOdd) {
                numberMap.put("odd", 1);
            } else {
                numberMap.put("even", 1);
            }
            if (number.isDuck) numberMap.put("duck", 1);
            if (number.isGapful) numberMap.put("gapful", 1);
            if (number.isJumping) numberMap.put("jumping", 1);
            if (number.isSquare) numberMap.put("square", 1);
            if (number.isSunny) numberMap.put("sunny", 1);
            if (number.isHappy) numberMap.put("happy", 1);
            else {
                numberMap.put("sad", 1);
            }
            if (properties.size() == 1) {
                if (properties.get(0).charAt(0) != '-') {
                    if (numberMap.get(properties.get(0)) == 1) {
                        number.shortDisplay();
                        return number;
                    }
                } else {
                    if (numberMap.get(properties.get(0).substring(1)) == 0) {
                        number.shortDisplay();
                        return number;
                    }
                }
                number = new Number(number.value + 1);
            } else {
                manyProperties = true;
                for (String property : properties) {
                    if (property.charAt(0) == '-') {
                        if (!(numberMap.get(property.substring(1)) == 0)) {
                            manyProperties = false;
                            break;
                        }
                    } else {
                        if (!(numberMap.get(property) == 1)) {
                            manyProperties = false;
                            break;
                        }
                    }
                }
                if (manyProperties) {
                    number.shortDisplay();
                    return number;
                } else {
                    number = new Number(number.value + 1);
                }
            }
        } while (true);
    }
}
