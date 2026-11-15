/** A booking that links a patient to a health professional at a specific time. */
public class Appointment implements Comparable<Appointment> {

    private final Patient patient;
    private final HealthProfessional professional;
    private final String timeSlot;
    /** False when any of the values passed to the constructor were invalid. */
    private final boolean valid;

    /** Creates an appointment from a patient, a professional and a four digit time slot. */
    public Appointment(Patient patient, HealthProfessional professional, String timeSlot) {
        this.patient = patient;
        this.professional = professional;
        this.timeSlot = timeSlot;
        this.valid = validate();
    }

    /** Checks that every detail an appointment needs is present and usable. */
    private boolean validate() {
        boolean ok = true;

        if (patient == null || !patient.isValid()) {
            System.out.println("Warning: a valid patient is required for an appointment.");
            ok = false;
        }
        if (professional == null || !professional.isValid()) {
            System.out.println("Warning: a valid health professional is required for an appointment.");
            ok = false;
        }
        if (timeSlot == null || !timeSlot.matches("\\d{4}")) {
            System.out.println("Warning: time slot \"" + timeSlot
                    + "\" must be four digits, for example 0930.");
            ok = false;
        }
        return ok;
    }

    public Patient getPatient() {
        return patient;
    }

    public HealthProfessional getProfessional() {
        return professional;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public boolean isValid() {
        return valid;
    }

    /** Orders appointments from the earliest time slot to the latest. */
    @Override
    public int compareTo(Appointment other) {
        return this.timeSlot.compareTo(other.timeSlot);
    }

    /** Tells whether another appointment takes the same professional at the same time. */
    public boolean conflictsWith(Appointment other) {
        if (other == null) {
            return false;
        }
        return this.timeSlot.equals(other.timeSlot)
                && this.professional.equals(other.professional);
    }

    /** Returns a readable description of this appointment, including the patient
        and the health professional chosen for it. */
    @Override
    public String toString() {
        return "Appointment[" + timeSlot + "] " + patient + " with " + professional;
    }
}
