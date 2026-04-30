/*
    * Course.java:
    * Child class for Course objects
    * Holds course-specific information
    * Authors: Mia Bongiorno and Allison Tang
    
*/

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Course extends Event {
    private String instructor;
    private int credits;
    private LocalDateTime lastClass; 
    private List<Integer> meetingDays; //days of the week the class meets
    
    public Course(String name, String instructor, int credits, LocalDateTime startDateTime, LocalDateTime lastClass, List<Integer> meetingDays) {
        super(name, startDateTime); //initialize name and time fields in Event class
        this.instructor = instructor;
        this.credits = credits;
        this.lastClass = lastClass;
        this.meetingDays = meetingDays;
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
    
    public List<Integer> getMeetingDays() {
        return meetingDays;
    }
    
   // public void display() {
        //System.out.println(getDateTime().toString() + " - " + getName() + " | Instructor: " + instructor + " | Credits: " + credits);
   // }
   //Mia: made the display for course and assignments for view schedule a bit cleaner
   public void display() {
    System.out.println(getDateTime().toLocalTime() + " - " + getName() + " (Class)");
}

    public List<LocalDateTime> getOccurrences() {
        List<LocalDateTime> dates = new ArrayList<>();
        LocalDateTime current = getDateTime();
        while (!current.isAfter(lastClass)) { //loop through each date from the start date to the last class date
            if (meetingDays.contains(current.getDayOfWeek().getValue())) { //if the current date is one of the meeting days, add it to the list of occurrences
                dates.add(current);
            }
            current = current.plusDays(1); //increment the current date by one day
        }
        return dates;
    }
}
