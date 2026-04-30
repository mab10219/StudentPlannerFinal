
import java.time.LocalDateTime; //for the due date field, track upcoming dates in order
/*
    * Assignment.java:
    * Extends Event.java and contains assignment name, due date, and associated course
    * Authors: Mia Bongiorno and Allison Tang
    
*/
public class Assignment extends Event {
    private String associatedCourse; //course field to link assignment to a course (organization and filtering)

    public Assignment(String name, LocalDateTime dueDate, String associatedCourse){
        super(name, dueDate);
        this.associatedCourse = associatedCourse;
    }

    public String getAssociatedCourse() {
        return associatedCourse;
    }

    //public void display() {
        //System.out.println(getDateTime().toLocalTime().toString() + " - " + getName() + " | Course: " + associatedCourse);
    //}
    //Mia: made the display for course and assignments for view schedule a bit cleaner
    public void display() {
        System.out.println(getDateTime().toLocalTime() + " - " + getName() + " (Assignment) | Course: " + associatedCourse);
    }
}
    

