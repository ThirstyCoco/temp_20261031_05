
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
    }
}
