import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
/*
    * Planner.java:
    * holds lists of courses and assignments and methods to add/remove courses and assignments
    * Authors: Mia Bongiorno and Allison Tang
    
*/
public class Planner{
    private ArrayList<Course> courses;
    private ArrayList<Assignment> assignments;
    
    public Planner(){
        courses = new ArrayList<>();
        assignments = new ArrayList<>();
    }
    //getters for courses and assignments:
    public ArrayList<Course> getCourses() {
        return courses;
    }
    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    // add courses and assignments to the planner:
    public void addCourse(Course course){
        courses.add(course);
    }
    public void addAssignment(Assignment assignment){
        assignments.add(assignment);
    }

    //removes assignment from the planner by name:
    public void removeAssignment(String name){
        for (int i = 0; i <assignments.size(); i++){
            if (assignments.get(i).getName().equalsIgnoreCase(name)){
                assignments.remove(i);
                return;
            }
        }
    }
    //removes entire course from planner by name:
    public void removeCourse(String name){
        for( int i= 0; i <courses.size(); i++){
            if (courses.get(i).getName().equalsIgnoreCase(name)){
                courses.remove(i);
                return;
            }
        }
    }
   
    public void displayPlanner(){
        ArrayList<Event> events = new ArrayList<>();
        int currentEvent = 0; //index to keep track of where we are in the events list as we display

        for (Course course: courses){
            //TODO: figure out had to add recurring events for each course based on the start date and interval (weekly, biweekly, etc.)
            events.add(course);
        }
        for (Assignment assignment: assignments){
            events.add(assignment);
        }
        
        //case of no events
        if (events.isEmpty()) {
            System.out.println("No events scheduled.");
            return;
        }
        events.sort((e1, e2) -> e1.getDateTime().compareTo(e2.getDateTime())); //sort

        //stores the first and last chronological events in the planner to determine the time range of the planner display
        LocalDateTime firstEventTime = events.get(0).getDateTime(); 
        LocalDateTime lastEventTime = events.get(events.size() - 1).getDateTime();
        long weeks = ChronoUnit.DAYS.between(firstEventTime, lastEventTime) / 7;

        //stores the date of the first Monday on or before the first event 
        LocalDateTime firstMonday = firstEventTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        for (int i = 0; i <= weeks; i++) {
            LocalDateTime weekStart = firstMonday.plusWeeks(i);
            LocalDateTime weekEnd = weekStart.plusWeeks(1);
            boolean firstEventinWeek = true;
            boolean firstEventinDay = true;

            while (currentEvent < events.size() && events.get(currentEvent).getDateTime().isBefore(weekEnd)) {
                //weekly header
                if (firstEventinWeek) {
                    System.out.println("Week of " + weekStart.toLocalDate());
                    firstEventinWeek = false;
                }

                //daily header
                if(firstEventinDay) {
                    System.out.println(events.get(currentEvent).getDateTime().getDayOfWeek());
                    firstEventinDay = false;
                }
                events.get(currentEvent).display();
                currentEvent++;
            }
        }
    }
}
