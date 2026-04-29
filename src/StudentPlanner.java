import java.time.LocalDateTime;

public class StudentPlanner {
    public static void main(String[] args) throws Exception {
        Planner planner = new Planner();
        planner.addCourse(new Course("English", "Smith", 3, LocalDateTime.of(2026, 4, 1, 10, 0), LocalDateTime.of(2026, 5, 28, 10, 0)));
        planner.addAssignment(new Assignment("Essay", LocalDateTime.of(2026, 4, 30, 23, 59), "English"));
        planner.displayPlanner();
    }
}
