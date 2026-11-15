/** A general practitioner, the only type that can issue prescriptions. */
public class GeneralPractitioner extends HealthProfessional {

    private final String clinicRoom;

    /** Creates a general practitioner with an id supplied by the caller. */
    public GeneralPractitioner(String id, String name, String[] workingDays, String clinicRoom) {
        super(id, name, workingDays);
        this.clinicRoom = normaliseRoom(clinicRoom);
    }

    /** Creates a general practitioner and lets the parent class generate the id. */
    public GeneralPractitioner(String name, String[] workingDays, String clinicRoom) {
        super(name, workingDays);
        this.clinicRoom = normaliseRoom(clinicRoom);
    }

    /** Keeps the room handling in one place so both constructors behave the same. */
    private static String normaliseRoom(String clinicRoom) {
        if (clinicRoom == null || clinicRoom.trim().isEmpty()) {
            return "Unassigned";
        }
        return clinicRoom;
    }

    public String getClinicRoom() {
        return clinicRoom;
    }

    /** Identifies this professional as a general practitioner. */
    @Override
    public String getRole() {
        return "General Practitioner";
    }

    /** Issues a prescription. Only general practitioners have this behaviour. */
    public String prescribe(String patientName, String drug) {
        if (patientName == null || drug == null) {
            return "Prescription not issued: patient and drug are required.";
        }
        return getName() + " prescribed " + drug + " to " + patientName + ".";
    }

    /** Adds the clinic room to the details provided by the parent class. */
    @Override
    public String toString() {
        return super.toString() + " | Clinic room: " + clinicRoom;
    }
}
