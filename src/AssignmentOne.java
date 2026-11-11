
public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("=== PROG2004 Assessment 1 ===");

        //create a health professional object
        String[] days = {"Monday", "Wednesday", "Friday"};
        HealthProfessional professional = new HealthProfessional("1001", "Alice Chen", days);

        System.out.println("Created: " + professional);
    }
}
