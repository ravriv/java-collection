// Expt 1

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the month: ");
        int month = input.nextInt();

        System.out.print("Enter the date: ");
        int date = input.nextInt();

        System.out.print("Enter the year: ");
        int year = input.nextInt();
        
        String monthString;
        switch (month) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "Invalid month";
        }
        
        System.out.println("Result: " + monthString + " " + date + ", " + year);
    }
}

// Expt 2

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the month: ");
        int month = input.nextInt();

        System.out.print("Enter the date: ");
        int date = input.nextInt();

        System.out.print("Enter the year: ");
        int year = input.nextInt();

        try {
            LocalDate enteredDate = LocalDate.of(year, month, date);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
            String formattedDate = enteredDate.format(formatter);
            System.out.println("Formatted Date: " + formattedDate);
        } catch (DateTimeException e) {
            System.out.println("Invalid date input.");
        }
    }
}