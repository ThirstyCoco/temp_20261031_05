import java.util.Arrays;
public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("=== PROG2004 Assessment 1 ===");

        // a valid professional created with the caller-supplied id
        String[] days = {"Monday", "Wednesday", "Friday"};
        HealthProfessional first = new HealthProfessional("1001", "Alice Chen", days);

        System.out.println("Name:  " + first.getName());
        System.out.println("Days:  " + Arrays.toString(first.getWorkingDays()));
        System.out.println("Works on Monday? " + first.worksOn("Monday"));
        System.out.println("Works on Sunday? " + first.worksOn("Sunday"));
        System.out.println("Valid? " + first.isValid());

        // invalid values are reported instead of being accepted silently
        System.out.println("\n-- invalid input --");
        new HealthProfessional("A100", "Bob Lee", days);
        new HealthProfessional("1002", "   ", days);
        new HealthProfessional("1003", "Carol Ng", new String[0]);

        //the returned array is a copy, so changing it has no effect
        String[] copy = first.getWorkingDays();
        copy[0] = "Sunday";
        System.out.println("\nOriginal array still intact? " + first.worksOn("Monday"));
    }
}
