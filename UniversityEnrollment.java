import java.util.HashMap;
import java.util.Scanner;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

public class UniversityEnrollment {
    private static final int MAX_ENROLLMENT = 2;
    private static HashMap<String, Integer> courseEnrollments = new HashMap<>();
    private static HashMap<String, String> prerequisites = new HashMap<>();

    public static void main(String[] args) {
        // Defining course prerequisites
        prerequisites.put("Advanced Java", "Core Java");
        prerequisites.put("Machine Learning", "Mathematics");

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enroll in Course: ");
            String course = scanner.nextLine();

            System.out.print("Prerequisite: ");
            String prerequisite = scanner.nextLine();

            if (prerequisites.containsKey(course) && !prerequisites.get(course).equals(prerequisite)) {
                throw new PrerequisiteNotMetException("Error: PrerequisiteNotMetException - Complete " + prerequisites.get(course) + " before enrolling in " + course + ".");
            }

            int enrolledCount = courseEnrollments.getOrDefault(course, 0);
            if (enrolledCount >= MAX_ENROLLMENT) {
                throw new CourseFullException("Error: CourseFullException - The course is full.");
            }

            courseEnrollments.put(course, enrolledCount + 1);
            System.out.println("Enrollment successful for " + course + ".");

        } catch (PrerequisiteNotMetException | CourseFullException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
