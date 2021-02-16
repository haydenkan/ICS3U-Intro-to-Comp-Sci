package week3;

import java.util.Scanner;

public class CrossCountryAssignment {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        /**
         * processRunner is called and the Scanner "in" is passed through it so the inputs can be taken with the scanner
         * processRunner is called three times because there are three runners.
         */
        processRunner(in);
        processRunner(in);
        processRunner(in);
        in.close();
    }

    private static void processRunner(Scanner in){
        /**
         * Takes keyboard inputs from user for name and splits
         * Stores these values in Strings
         * After pressing entering an input and pressing enter, the console will prompt a new input until each requirement has been answered.
         */
        System.out.print("Please enter runner's first name: ");
        String fName = in.nextLine();
        
        System.out.print("Please enter runner's last name: ");
        String lName = in.nextLine();

        System.out.print("Please enter the time to the first mile: ");
        String splitOneTime = in.nextLine();

        System.out.print("Please enter the time to the end of the second mile: ");
        String splitTwoTime = in.nextLine();

        System.out.print("Please enter the time to complete the five kilometers: ");
        String totalTime = in.nextLine();

        /**
         * Calls the convertToSeconds method, inputs the individual times, and stores the return values in doubles
         */
        double splitOneSeconds = convertToSeconds(splitOneTime);
        double splitTwoSeconds = convertToSeconds(splitTwoTime);
        double totalTimeSeconds = convertToSeconds(totalTime);

        /**
         * Takes the return values from the convertToSeconds method which are stored in doubles
         * Inputs these values in the subtractTimes method to get the split times, and stores the return values in doubles.
         */
        double splitOne = splitOneSeconds;
        double splitTwo = subtractTimes(splitOneSeconds, splitTwoSeconds);
        double splitThree = subtractTimes(splitTwoSeconds, totalTimeSeconds);
        /**
         * the displaySummary method is called here, and the arguments passed are fName, lName, splitOne, splitTwo, splitThree, and totalTimeSeconds. 
         * These arguments are passed through because the processRunner method is private, so this is the only other way to easily transfer runner data.
         */
        displaySummary(fName, lName, splitOne, splitTwo, splitThree, totalTimeSeconds); 
    }

    /**
     * Requires the parameter timeString because it is how the method gets the times.
     * this method is not a void because it needs to return something (the converted time).
     * @param timeString
     * @return
     */
    private static double convertToSeconds(String timeString){
        /**
         * Finds the first instance of a colon in the string
         * Then takes all numbers that are before the index where the colon (":") is and converts it from minutes to seconds and from a string to an integer. This code works for every time input because all times inputted are in the format m:s.ms, which means the minutes will always be displayed before the colon.
         * Finally takes all numbers that are after the index where the colon (":") is and converts it to a string. This value is already in seconds and milliseconds.
         * Returns the sum of the minutes (which are in seconds) and the seconds (which are in seconds.milliseconds).
         */
        int colon = timeString.indexOf(":");
        int minutesAsSeconds = Integer.parseInt(timeString.substring(0,colon)) * 60;
        double seconds = Double.parseDouble(timeString.substring(colon+1));
        
        return minutesAsSeconds + seconds;
    }
    /**
     * Finds the difference between the two split times and returns the value. 
     * This was put into a separate method because I wanted to keep the processRunner method less cluttered.
     * @param firstSplitTime
     * @param secondSplitTime
     * @return
     */
    private static double subtractTimes(double firstSplitTime, double secondSplitTime){
        return secondSplitTime - firstSplitTime; 
    } 
 
    private static void displaySummary(String firstName, String lastName, double splitOne, double splitTwo, double splitThree, double totalTime){ //Takes all the required values to print them out
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~ \n" + firstName + " " + lastName + "'s summary\n~~~~~~~~~~~~~~~~~~~~~~~"); //Prints out dividers and the name of the runner. I put this in one println because 
        /**
         * Formats the split times and total time into minutes:seconds.milliseconds. The string needs to be formatted because 
         * I converted the minutes into an integer because the conversion would truncate the (seconds/60). The truncation would give me the desired result in minutes.
         * I used the modulus operator for the seconds beacuse it would give me the remainder of turning all possible seconds into minutes.
         * %d outputs a decimal integer, the colon separates the minutes and seconds, and the %06.3f means that there needs to be six characters (00.000) with three decimal points (the milliseconds: .000), and the float means that you can pass a double through the String.format().
         */
        String splitOneFormatted = String.format("%d:%06.3f", (int)splitOne / 60, splitOne%60); 
        String splitTwoFormatted = String.format("%d:%06.3f", (int)splitTwo / 60, splitTwo%60); 
        String splitThreeFormatted = String.format("%d:%06.3f", (int)splitThree / 60, splitThree%60); 
        String totalTimeFormatted = String.format("%d:%06.3f", (int)totalTime / 60, totalTime%60);
        System.out.println("First split: " + splitOneFormatted + "\nSecond split: " + splitTwoFormatted + "\nThird split: " + splitThreeFormatted + "\nTotal time: " + totalTimeFormatted + "\n~~~~~~~~~~~~~~~~~~~~~~~");
        
        
    }

}
