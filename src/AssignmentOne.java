
public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("=== PROG2004 Assessment 1 ===");

        // Part 1: both constructors are used here so the ids can be compared
        String[] days = {"Monday", "Wednesday", "Friday"};

        HealthProfessional first = new HealthProfessional("1001", "Alice Chen", days);
        HealthProfessional second = new HealthProfessional("Bob Lee", days);
        HealthProfessional third = new HealthProfessional("Carol Ng", days);

        System.out.println("Caller-supplied id: " + first.getId());
        System.out.println("Auto-generated id:  " + second.getId());
        System.out.println("Auto-generated id:  " + third.getId());
    }
}
