
public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("=== PROG2004 Assessment 1 ===");

        String[] days = {"Monday", "Wednesday", "Friday"};

        //the first concrete type of professional
        GeneralPractitioner alice = new GeneralPractitioner("1001", "Alice Chen", days, "Room 3");
        System.out.println(alice);
        System.out.println(alice.prescribe("Bob Lee", "Amoxicillin"));

        // The second constructor lets the parent class supply the id
        GeneralPractitioner david = new GeneralPractitioner("David Park", days, "Room 1");
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
    }
}
