import java.time.LocalDateTime; //for the due date field
/*
    * Event.java:
    * Parent class for Course.java and Assignment.java
    * Holds the shared name field that both Course and Assignment have
    * Authors: Mia Bongiorno and Allison Tang
    
*/
public class Event {
    private String name;
    private LocalDateTime dateTime; 
    
    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime; //initialize dateTime to current time, can be updated later if needed
    }
    
    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    //abstract display method 
    public void display() {
    }
}
