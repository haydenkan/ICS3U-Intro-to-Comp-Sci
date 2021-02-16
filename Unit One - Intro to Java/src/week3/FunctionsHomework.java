package week3;

public class FunctionsHomework {
    public static void main(String[] args) {
        questionOne();
        questionTwo();
        questionThree();
        questionFour();
        questionFive();
    }

    private static void questionOne() {
        int cost = 985;
        double salesTax = 1.055;
        double total = cost * salesTax;
        System.out.println("1. $" + total);
    }

    private static void questionTwo() {
        double length = 4.5;
        double width = 2.3;
        double area = length * width;
        double perimeter = length * 2 + width * 2;
        System.out.println("2. Area: " + area + " Perimeter: " + perimeter);
    }

    private static void questionThree() {
        int minutesInAYear = 1 * 60 * 24 * 365;
        System.out.println("3. Minutes in a year: " + minutesInAYear);
    }

    private static void questionFour() {
        int secondsInAYear = 60 * 60 * 24 * 365;
        double lightYear = secondsInAYear * (3 * Math.pow( 10,8));
        System.out.println("4. " + lightYear);
    }

    private static void questionFive() {
        //System.out.println("5. " + Math.round(110.0  / (44+110) * 100, 2));
    }
}