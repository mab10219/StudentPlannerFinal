/*
    * Course.java:
    * Child class for Course objects
    * Holds course-specific information
    * Authors: Mia Bongiorno and Allison Tang
    
*/

import java.time.LocalDateTime;

public class Course extends Event {
    private String instructor;
    private int credits;
    private LocalDateTime lastClass; 
    private int interval; //time interval between classes, in days (for recurring events)
    
    public Course(String name, String instructor, int credits, LocalDateTime startDateTime, LocalDateTime lastClass) {
        super(name, startDateTime); //initialize name and time fields in Event class
        this.instructor = instructor;
        this.credits = credits;
        this.lastClass = lastClass;
    }
    
    public String getInstructor() {
        return instructor;
    }
    
    public int getCredits(){
        return credits;
    }
    
    public LocalDateTime getLastClass() {
        return lastClass;
    }
    
    public void display() {
        System.out.println(getDateTime().toString() + " - " + getName() + " | Instructor: " + instructor + " | Credits: " + credits);
    }
}
