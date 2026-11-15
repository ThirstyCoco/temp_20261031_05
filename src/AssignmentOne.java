import java.util.ArrayList;
import java.util.List;


public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("=== PROG2004 Assessment 1 ===");

        String[] days = {"Monday", "Wednesday", "Friday"};

        // the first concrete type of professional 
        GeneralPractitioner alice = new GeneralPractitioner("1001", "Alice Chen", days, "Room 3");
        System.out.println(alice);
        System.out.println(alice.prescribe("Bob Lee", "Amoxicillin"));

        // The second constructor lets the parent class supply the id
        GeneralPractitioner david = new GeneralPractitioner("1002", "David Park", days, "Room 1");
        System.out.println(david);

        //the other three types of professional 
        Specialist priya = new Specialist("2001", "Priya Nair", days, "Cardiology");
        Nurse tom = new Nurse("3001", "Tom Reed", days, "Ward B");
        Dietitian sara = new Dietitian("4001", "Sara Kim", days, "Low sodium");

        System.out.println("\n-- other professional types --");
        System.out.println(priya);
        System.out.println(tom);
        System.out.println(sara);

        // Each type performs the behaviour that belongs only to it
        System.out.println("\n-- type-specific behaviour --");
        System.out.println(priya.orderDiagnosticTest("ECG"));
        System.out.println(tom.takeBloodSample("Bob Lee"));
        System.out.println(sara.createMealPlan("Bob Lee"));

        //every type stored and processed through one common type ---
        List<HealthProfessional> professionals = new ArrayList<>();
        professionals.add(alice);
        professionals.add(david);
        professionals.add(new GeneralPractitioner("1003", "Grace Liu", days, "Room 2"));
        professionals.add(priya);
        professionals.add(new Specialist("2002", "Omar Haddad", days, "Radiology"));
        professionals.add(tom);
        professionals.add(new Nurse("3002", "Lena Vos", days, "Ward C"));
        professionals.add(sara);

        System.out.println("\n-- Part 3: all professionals in one collection --");
        displayAll(professionals);
        System.out.println("Total professionals: " + professionals.size());
    }

    /** Prints one professional. The object decides how it describes itself. */
    private static void display(HealthProfessional professional) {
        System.out.println(professional);
    }

    /** Prints every professional in the list, whatever its specific type is. */
    private static void displayAll(List<HealthProfessional> professionals) {
        for (HealthProfessional professional : professionals) {
            display(professional);
        }
    }
}
