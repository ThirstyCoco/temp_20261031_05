/** Manages the appointments booked at the clinic. */
public class AppointmentManager {

    /** The clinic runs from 08:00 to 16:00 in thirty minute slots. */
    private static final String[] CLINIC_SLOTS = buildSlots();

    /** Builds the daily schedule with a loop so the format cannot be mistyped. */
    private static String[] buildSlots() {
        String[] slots = new String[17];
        int index = 0;

        for (int hour = 8; hour <= 16; hour++) {
            slots[index++] = String.format("%02d00", hour);
            if (hour < 16) {
                slots[index++] = String.format("%02d30", hour);
            }
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
}
