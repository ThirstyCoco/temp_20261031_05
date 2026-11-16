import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Manages the appointments booked at the clinic. */
public class AppointmentManager {

    /** The clinic opens at this hour and closes at this hour. */
    private static final int OPEN_HOUR = 8;
    private static final int CLOSE_HOUR = 16;
    /** Each bookable appointment lasts this many minutes. */
    private static final int SLOT_MINUTES = 30;

    /** The clinic's daily schedule, built from the three settings above. */
    private static final String[] CLINIC_SLOTS = buildSlots();

    private final List<Appointment> appointments = new ArrayList<>();

    /** Builds the daily schedule from the opening hours and the slot length. */
    private static String[] buildSlots() {
        int totalSlots = (CLOSE_HOUR - OPEN_HOUR) * 60 / SLOT_MINUTES + 1;
        String[] slots = new String[totalSlots];

        for (int i = 0; i < totalSlots; i++) {
            int minutesFromOpen = i * SLOT_MINUTES;
            int hour = OPEN_HOUR + minutesFromOpen / 60;
            int minute = minutesFromOpen % 60;
            slots[i] = String.format("%02d%02d", hour, minute);
        }
        return slots;
    }

    /** Returns a copy of the clinic's daily time slots. */
    public static String[] getClinicSlots() {
        return CLINIC_SLOTS.clone();
    }

    /** Tells whether the given time is one of the clinic's bookable slots. */
    public static boolean isValidSlot(String timeSlot) {
        if (timeSlot == null) {
            return false;
        }
        for (String slot : CLINIC_SLOTS) {
            if (slot.equals(timeSlot)) {
                return true;
            }
        }
        return false;
    }

    /** Books an appointment. Returns null when it was booked, otherwise a warning. */
    public String addAppointment(Patient patient, HealthProfessional professional,
                                 String timeSlot) {
        if (patient == null || professional == null) {
            return "Appointment not booked: patient and health professional are required.";
        }
        if (!patient.isValid()) {
            return "Appointment not booked: the patient details are invalid.";
        }
        if (!professional.isValid()) {
            return "Appointment not booked: the health professional details are invalid.";
        }
        if (!isValidSlot(timeSlot)) {
            return "Appointment not booked: \"" + timeSlot + "\" is not a clinic time slot.";
        }

        Appointment candidate = new Appointment(patient, professional, timeSlot);

        // The same professional can never be booked twice in one time slot
        for (Appointment existing : appointments) {
            if (candidate.conflictsWith(existing)) {
                return "Appointment not booked: " + professional.getName()
                        + " is already booked at " + timeSlot + ".";
            }
        }

        appointments.add(candidate);
        return null;
    }

    /** Returns a copy of every appointment currently booked. */
    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    /** Returns the appointments booked with the professional that has this id. */
    public List<Appointment> filterByProfessionalId(String professionalId) {
        List<Appointment> matching = new ArrayList<>();
        if (professionalId == null) {
            return matching;
        }
        for (Appointment appointment : appointments) {
            if (professionalId.equals(appointment.getProfessional().getId())) {
                matching.add(appointment);
            }
        }
        return matching;
    }

    /** Returns the appointments booked for the patient with this phone number. */
    public List<Appointment> filterByPatientPhone(String phone) {
        List<Appointment> matching = new ArrayList<>();
        if (phone == null) {
            return matching;
        }
        for (Appointment appointment : appointments) {
            if (phone.equals(appointment.getPatient().getPhone())) {
                matching.add(appointment);
            }
        }
        return matching;
    }

    /** Returns the given appointments sorted from earliest to latest. */
    public List<Appointment> sortByTime(List<Appointment> appointments) {
        if (appointments == null) {
            return new ArrayList<>();
        }
        // Sort a copy, so the caller's list keeps its original order
        List<Appointment> sorted = new ArrayList<>(appointments);
        Collections.sort(sorted);
        return sorted;
    }

    /** Returns every booked appointment sorted from earliest to latest. */
    public List<Appointment> getSortedAppointments() {
        return sortByTime(appointments);
    }

    /** Prints the given appointments, or a message when there are none. */
    public void printAppointments(List<Appointment> appointments) {
        if (appointments == null || appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    /** Cancels the appointment of this professional at this time. Returns how many were removed. */
    public int cancelByProfessionalAndTime(String professionalId, String timeSlot) {
        List<Appointment> matching = new ArrayList<>();
        if (professionalId != null && timeSlot != null) {
            for (Appointment appointment : appointments) {
                if (professionalId.equals(appointment.getProfessional().getId())
                        && timeSlot.equals(appointment.getTimeSlot())) {
                    matching.add(appointment);
                }
            }
        }
        return cancelAll(matching);
    }

    /** Cancels every appointment of this patient. Returns how many were removed. */
    public int cancelByPatientPhone(String phone) {
        return cancelAll(filterByPatientPhone(phone));
    }

    /** Removes the given appointments from the clinic. Returns how many were removed. */
    private int cancelAll(List<Appointment> toRemove) {
        if (toRemove == null || toRemove.isEmpty()) {
            return 0;
        }
        appointments.removeAll(toRemove);
        return toRemove.size();
    }
}
