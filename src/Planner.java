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
   

}
