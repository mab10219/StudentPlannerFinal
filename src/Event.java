/*
    * Event.java:
    * Parent class for Course.java and Assignment.java
    * Holds the shared name field that both Course and Assignment have
    * Authors: Mia Bongiorno and Allison Tang
    
*/
public class Event {
    private String name;
    
    public Event(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
}
