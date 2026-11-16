import java.util.ArrayList;
import java.util.Collections;
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

        // the other three types of professional 
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

        //every type stored and processed through one common type 
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

        //the patient recorded on an appointment 
        Patient bob = new Patient("Bob Lee", "0412345678");
        Patient bobAgain = new Patient("Robert Lee", "0412345678");

        System.out.println("\n-- Part 4: patient details --");
        System.out.println(bob);
        System.out.println("Same phone -> same patient? " + bob.equals(bobAgain));

        System.out.println("\n-- invalid patient input --");
        new Patient("   ", "0412345678");
        new Patient("Carol Ng", "12345");

        //an appointment links a patient to a professional and a time 
        Patient carol = new Patient("Carol Ng", "0433221100");
        Appointment first = new Appointment(bob, alice, "0930");
        Appointment clash = new Appointment(carol, alice, "0930");

        System.out.println("\n-- Part 4: appointments --");
        System.out.println(first);
        System.out.println(clash);
        System.out.println("Same professional and time? " + first.conflictsWith(clash));

        System.out.println("\n-- invalid appointment input --");
        new Appointment(bob, alice, "9:30");

        //a day's appointments sorted from earliest to latest 
        List<Appointment> day = new ArrayList<>();
        day.add(new Appointment(bob, priya, "1400"));
        day.add(new Appointment(carol, alice, "0830"));
        day.add(new Appointment(bob, tom, "1130"));
        day.add(new Appointment(carol, david, "0900"));

        System.out.println("\n-- Part 4: before sorting --");
        displayAppointments(day);

        Collections.sort(day);

        System.out.println("\n-- Part 4: after sorting --");
        displayAppointments(day);

        //the clinic runs to a fixed daily schedule of time slots 
        String[] slots = AppointmentManager.getClinicSlots();

        System.out.println("\n-- Part 5: clinic time slots --");
        System.out.println("Slots per day: " + slots.length);
        System.out.println("First slot: " + slots[0] + ", last slot: " + slots[slots.length - 1]);
        System.out.println("Is 0930 a valid slot? " + AppointmentManager.isValidSlot("0930"));
        System.out.println("Is 0945 a valid slot? " + AppointmentManager.isValidSlot("0945"));
        System.out.println("Is 1700 a valid slot? " + AppointmentManager.isValidSlot("1700"));

        //booking appointments and rejecting double bookings 
        AppointmentManager manager = new AppointmentManager();

        System.out.println("\n-- Part 5: booking appointments --");
        printResult(manager.addAppointment(bob, alice, "0930"));
        printResult(manager.addAppointment(carol, alice, "1100"));
        printResult(manager.addAppointment(carol, alice, "0930"));
        printResult(manager.addAppointment(bob, priya, "0930"));
        printResult(manager.addAppointment(bob, alice, "0945"));

        System.out.println("\n-- Part 5: appointments currently booked --");
        manager.printAppointments(manager.getAppointments());

        //viewing a subset of the appointments 
        System.out.println("\n-- Part 5: appointments for professional 1001 --");
        manager.printAppointments(manager.filterByProfessionalId("1001"));

        System.out.println("\n-- Part 5: appointments for phone 0433221100 --");
        manager.printAppointments(manager.filterByPatientPhone("0433221100"));

        System.out.println("\n-- Part 5: appointments for an unknown professional --");
        manager.printAppointments(manager.filterByProfessionalId("9999"));

        // Sorting can be applied to everything or to a filtered subset
        System.out.println("\n-- Part 5: every appointment sorted by time --");
        manager.printAppointments(manager.getSortedAppointments());

        System.out.println("\n-- Part 5: one patient's appointments sorted by time --");
        manager.printAppointments(manager.sortByTime(manager.filterByPatientPhone("0433221100")));

        //cancelling appointments by two different inputs 
        System.out.println("\n-- Part 5: cancel the appointment of professional 1001 at 0930 --");
        printCancelled(manager.cancelByProfessionalAndTime("1001", "0930"));

        System.out.println("\n-- Part 5: cancel every appointment for phone 0433221100 --");
        printCancelled(manager.cancelByPatientPhone("0433221100"));

        System.out.println("\n-- Part 5: cancel an appointment that does not exist --");
        printCancelled(manager.cancelByProfessionalAndTime("9999", "0930"));

        System.out.println("\n-- Part 5: appointments left after cancelling --");
        manager.printAppointments(manager.getAppointments());

        //the whole system working end to end 
        AppointmentManager clinic = new AppointmentManager();
        Patient mia = new Patient("Mia Wong", "0400111222");
        Patient noah = new Patient("Noah Bell", "0400333444");

        clinic.addAppointment(mia, alice, "0900");
        clinic.addAppointment(noah, priya, "1000");
        clinic.addAppointment(mia, tom, "1130");
        clinic.addAppointment(noah, sara, "1400");
        clinic.addAppointment(mia, david, "0830");

        System.out.println("\n-- Part 6: every appointment booked today --");
        manager.printAppointments(clinic.getAppointments());

        System.out.println("\n-- Part 6: appointments with professional 2001 --");
        manager.printAppointments(clinic.filterByProfessionalId("2001"));

        System.out.println("\n-- Part 6: appointments for phone 0400111222 --");
        manager.printAppointments(clinic.filterByPatientPhone("0400111222"));

        System.out.println("\n-- Part 6: the day in time order --");
        manager.printAppointments(clinic.getSortedAppointments());

        System.out.println("\n-- Part 6: after cancelling the 1000 appointment --");
        printCancelled(clinic.cancelByProfessionalAndTime("2001", "1000"));
        manager.printAppointments(clinic.getAppointments());

        //what happens when an operation cannot be completed 
        System.out.println("\n-- Part 6: error and edge cases --");

        System.out.println("\n1. Same professional, same time slot:");
        printResult(clinic.addAppointment(noah, david, "0830"));

        System.out.println("\n2. Same time slot, different professional, which is allowed:");
        printResult(clinic.addAppointment(noah, sara, "0830"));

        System.out.println("\n3. Cancelling an appointment that does not exist:");
        printCancelled(clinic.cancelByProfessionalAndTime("9999", "1200"));

        System.out.println("\n4. Time slot in the wrong format:");
        printResult(clinic.addAppointment(noah, alice, "8:30"));

        System.out.println("\n5. Time slot outside the clinic's opening hours:");
        printResult(clinic.addAppointment(noah, alice, "1730"));

        System.out.println("\n6. Appointment with an invalid patient:");
        Patient invalid = new Patient("", "123");
        printResult(clinic.addAppointment(invalid, alice, "1500"));

        System.out.println("\n-- Part 6: the collection after the error cases --");
        manager.printAppointments(clinic.getAppointments());
    }

    /** Prints how many appointments a cancellation removed. */
    private static void printCancelled(int count) {
        if (count == 0) {
            System.out.println("No matching appointment found.");
        } else {
            System.out.println("Cancelled " + count + " appointment(s).");
        }
    }

    /** Prints the outcome of a booking attempt, where null means it was booked. */
    private static void printResult(String warning) {
        if (warning == null) {
            System.out.println("Booked.");
        } else {
            System.out.println(warning);
        }
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

    /** Prints every appointment in the list, in the order it appears. */
    private static void displayAppointments(List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}
