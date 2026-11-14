import java.util.Arrays;


public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("=== PROG2004 Assessment 1 ===");

        String[] days = {"Monday", "Wednesday", "Friday"};

        //creation, getters and behaviour ---
        HealthProfessional alice = new HealthProfessional("1001", "Alice Chen", days);
        System.out.println("Name:  " + alice.getName());
        System.out.println("Days:  " + Arrays.toString(alice.getWorkingDays()));
        System.out.println("Works on Monday? " + alice.worksOn("Monday"));
        System.out.println("Works on Sunday? " + alice.worksOn("Sunday"));
        System.out.println("Valid? " + alice.isValid());

        //invalid values are reported, not accepted silently ---
        System.out.println("\n-- invalid input --");
        new HealthProfessional("A100", "Bob Lee", days);
        new HealthProfessional("1002", "   ", days);
        new HealthProfessional("1003", "Carol Ng", new String[0]);

        //the returned array is a copy ---
        String[] copy = alice.getWorkingDays();
        copy[0] = "Sunday";
        System.out.println("\nOriginal array still intact? " + alice.worksOn("Monday"));

        //printing an object gives readable details ---
        System.out.println("\n-- printing an object --");
        System.out.println(alice);

        //equals and hashCode are based on the id ---
        HealthProfessional aliceCopy = new HealthProfessional("1001", "A. Chen", days);
        HealthProfessional bob = new HealthProfessional("1002", "Bob Lee", days);
        System.out.println("Same id -> equals?      " + alice.equals(aliceCopy));
        System.out.println("Different id -> equals? " + alice.equals(bob));
        System.out.println("Same id -> hashCode?    " + (alice.hashCode() == aliceCopy.hashCode()));
    }
}
