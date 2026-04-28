
import java.time.LocalDateTime; //for the due date field, track upcoming dates in order
/*
    * Assignment.java:
    * Extends Event.java and contains assignment name, due date, and associated course
    * Authors: Mia Bongiorno and Allison Tang
    
*/
public class Assignment extends Event {
    private LocalDateTime dueDate; //due date field to track upcoming dates in order
    private String associatedCourse; //course field to link assignment to a course (organization and filtering)

    public Assignment(String name, LocalDateTime dueDate, String associatedCourse){
        super(name);
        this.dueDate = dueDate;
        this.associatedCourse = associatedCourse;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getAssociatedCourse() {
        return associatedCourse;
    }
}
    

