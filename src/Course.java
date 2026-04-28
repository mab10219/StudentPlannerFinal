/*
    * Course.java:
    * Child class for Course objects
    * Holds course-specific information
    * Authors: Mia Bongiorno and Allison Tang
    
*/

import java.time.LocalDateTime;

public class Course extends Event {
    private String name;
    private String instructor;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    public Course(String name, String instructor, int credits, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public LocalDateTime getEndDate() {
        return endDate;
    }

}
