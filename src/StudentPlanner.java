import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    
    //storage for courses
    private static final String COURSE_FILE_NAME = "courses.bin";
    //storage for assignments
    private static final String ASSIGNMENT_FILE_NAME = "assignments.bin";

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n1. Add Event");
            System.out.println("2. Search Events");
            System.out.println("3. View Schedule");
            System.out.println("4. Import Existing Data");
            System.out.println("5. Save Current Data");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> AddEvent();
                case "2" -> SearchEvents();
                case "3" -> ViewSchedule();
                case "4" -> ImportExistingData();
                case "5" -> SaveCurrentData();
                case "6" -> running = false;
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
            try{
                for (int i = 0; i < daysArray.length; i++) {
                    days.add(Integer.parseInt(daysArray[i].trim()));
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid input for meeting days. Please enter a comma-separated list of integers (1 for Monday, 2 for Tuesday, ..., 7 for Sunday).");
                return;
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
            try{
            //TO DO: add error handling for course input (check if course exists in planner)
                planner.addAssignment(new Assignment(name, dueDate, course));

            }catch(Exception e){
                System.out.println("Course " + course + " not found in planner. Please add the course before adding an assignment.");
                return;
            }

        } else {
            System.out.println("Invalid event type.");
        }
    }
    //SearchEvents()searches for a course by name and displays all associated assignments
    //and offers the option to delete a course or assignment from the results
    private static void SearchEvents() {
        System.out.print("Enter the course you would like to search by: ");
        String searchName = scanner.nextLine().trim();
        boolean foundEvent = false;
        System.out.println("\nSearch Results:");
        System.out.println(".........................................");

        //search loop for matching course:
        for (Course c: planner.getCourses()){
            if (c.getName().equalsIgnoreCase(searchName)){
                foundEvent = true;
                System.out.println("Course: " + c.getName() + " | Instructor: " + c.getInstructor() + " | Credits: " + c.getCredits());
            }
        } 
        //loop to find assignments associated to found courses:
        for (Assignment a: planner.getAssignments()){
            if( a.getAssociatedCourse().equalsIgnoreCase(searchName)){
                foundEvent = true;
                System.out.println("Assignment: " + a.getName() + " | Due: " + 
                    a.getDateTime().format(dateTimeFormatter) + " | Course: " + a.getAssociatedCourse());
            }
        }
        if (!foundEvent) {
            System.out.println("No results for " + searchName);
            System.out.println("Please press enter to go back to main page");
            scanner.nextLine();
        }
        //delete choice functionality:
        else{
            System.out.println("Please choose an option: ");
            System.out.println("...........................");
            System.out.println("\n1) Delete a Course");
            System.out.println("2) Delete an Assignment");
            System.out.println("3) Back to main window");
            System.out.print("Enter your choice: ");

            String deleteChoice = scanner.nextLine().trim();

            //user chooses to delete a course:
            if(deleteChoice.equals("1")){
                System.out.print("Enter the name of the course name to delete: ");
                String deleteName = scanner.nextLine();

                try{
                    planner.removeCourse(deleteName);
                }catch(Exception e){
                    System.out.println("Course not found. Please make sure to enter the course name exactly as it appears in the search results.");
                    return;
                }

                //delete all assignments associated with the deleted course
                for(Assignment a: planner.getAssignments()){
                    if( a.getAssociatedCourse().equalsIgnoreCase(deleteName)){
                        planner.removeAssignment(a.getName());
                    }
                }

                System.out.println("Course sucessfully deleted. Press enter to go back to main window.");
                scanner.nextLine();
                
            }
            //user chooses to delete an assignment:
            else if(deleteChoice.equals("2")){
                System.out.print("Enter the name of the assignment to delete: ");
                String deleteName = scanner.nextLine();
                planner.removeAssignment(deleteName);
                System.out.println("Assignment sucessfully deleted. Press enter to go back to main window.");
                scanner.nextLine();

            }
        }
    }

    private static void ViewSchedule() {
        planner.displayPlanner();
    }

    private static void ImportExistingData() {
        try{
            //opens the data file
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(COURSE_FILE_NAME));
            ObjectInputStream in2 = new ObjectInputStream(new FileInputStream(ASSIGNMENT_FILE_NAME));

            ArrayList<Course> courses = (ArrayList<Course>) in.readObject();
            ArrayList<Assignment> assignments = (ArrayList<Assignment>) in2.readObject();

            planner.addAssignmentList(assignments);
            planner.addCourseList(courses);

            in.close();
            in2.close();

            System.out.println("Schedule imported successfully! Press enter to go back to main window.");
            scanner.nextLine();
        }catch(Exception e){
            //to do: add error handling for invalid file format, etc.
            System.out.println("Error! Could not import data. Please make sure the file is in the correct format and try again.");
        }
    }

    private static void SaveCurrentData() {
        try{
            //opens data.bin
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(COURSE_FILE_NAME));
            ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream(ASSIGNMENT_FILE_NAME));

            //writes addressbook arraylist into the file
            out.writeObject(planner.getCourses());
            out2.writeObject(planner.getAssignments());

            //finish writing
            out.close();
            out2.close();
            
            System.out.println("Schedule saved successfully! Press enter to go back to main window.");
            scanner.nextLine();
        }catch(Exception e){
            System.out.println("Error! Couldn't save planner.");
        }
    }
}