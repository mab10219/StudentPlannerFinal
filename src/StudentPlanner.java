import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
/*
    * StudentPlanner.java:
    * Main class to run the student planner program, contains the main method and user interface
    * Authors: Mia Bongiorno and Allison Tang
    
*/

public class StudentPlanner {
    private static Planner planner = new Planner();
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n1. Add Event");
            System.out.println("2. Search Events");
            System.out.println("3. View Schedule");
            System.out.println("4. Quit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> AddEvent();
                case "2" -> SearchEvents();
                case "3" -> ViewSchedule();
                case "4" -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void AddEvent() {
        System.out.print("What type of event would you like to add? Type 'C' for course and 'A' for assignment: ");
        String eventType = scanner.nextLine().trim().toUpperCase();

        if (eventType.equals("C")) {
            System.out.print("What is the course name? ");
            String name = scanner.nextLine();

            System.out.print("Instructor: ");
            String instructor = scanner.nextLine();

            System.out.print("Credits: ");
            int credits;
            try {
                credits = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid credit format. Please enter a valid integer.");
                return;
            }

            System.out.print("Please input the date and time of the first class in the following format (YYYY-MM-DD HH:MM): ");
            LocalDateTime startDateTime;
            try {
                startDateTime = LocalDateTime.parse(scanner.nextLine().trim(), dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm");
                return;
            }

            System.out.print("Please input the date and time of the last class in the following format (YYYY-MM-DD HH:MM): ");
            LocalDateTime lastClass;
            try {
                lastClass = LocalDateTime.parse(scanner.nextLine().trim(), dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm");
                return;
            }

            System.out.print("On what days does the class meet? Please input as a comma-separated list of integers (1 for Monday, 2 for Tuesday, ..., 7 for Sunday): ");
            String daysInput = scanner.nextLine();
            String[] daysArray = daysInput.split(",");
            List<Integer> days = new ArrayList<>();
            for (int i = 0; i < daysArray.length; i++) {
                days.add(Integer.parseInt(daysArray[i].trim()));
            }

            planner.addCourse(new Course(name, instructor, credits, startDateTime, lastClass, days));
        } else if (eventType.equals("A")) {
            System.out.print("Assignment Name: ");
            String name = scanner.nextLine();

            System.out.print("Please input the due date and time in the following format (YYYY-MM-DD HH:MM): ");
            LocalDateTime dueDate;
            try {
                dueDate = LocalDateTime.parse(scanner.nextLine().trim(), dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm");
                return;
            }

            System.out.print("Associated Course: ");
            String course = scanner.nextLine();
            //TO DO: add error handling for course input (check if course exists in planner)

            planner.addAssignment(new Assignment(name, dueDate, course));
        } else {
            System.out.println("Invalid event type.");
        }
    }

    private static void SearchEvents() {
    }

    private static void ViewSchedule() {
        planner.displayPlanner();
    }
}