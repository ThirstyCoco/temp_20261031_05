
public class GeneralPractitioner extends HealthProfessional {

    private final String clinicRoom;

    /*
      Creates a general practitioner with an id supplied by the caller.
     
      @param id          the professional's id, which must contain digits only
      @param name        the professional's name
      @param workingDays the days this professional works each week
      @param clinicRoom  the room this GP consults in
     */
    public GeneralPractitioner(String id, String name, String[] workingDays, String clinicRoom) {
        super(id, name, workingDays);
        this.clinicRoom = normaliseRoom(clinicRoom);
    }

    /*
      Creates a general practitioner and lets the parent class generate the id.
     
      @param name        the professional's name
      @param workingDays the days this professional works each week
      @param clinicRoom  the room this GP consults in
     */
    public GeneralPractitioner(String name, String[] workingDays, String clinicRoom) {
        super(name, workingDays);
        this.clinicRoom = normaliseRoom(clinicRoom);
    }

    /*
      Keeps the room handling in one place, so both constructors behave
      the same way when no room is supplied.
     
      @param clinicRoom the room passed by the caller
      @return a usable room name
     */
    private static String normaliseRoom(String clinicRoom) {
        if (clinicRoom == null || clinicRoom.trim().isEmpty()) {
            return "Unassigned";
        }
        return clinicRoom;
    }

    public String getClinicRoom() {
        return clinicRoom;
    }

    /*
      Identifies this professional as a general practitioner.
     
      @return the role name
     */
    @Override
    public String getRole() {
        return "General Practitioner";
    }

    /*
      Issues a prescription. This behaviour belongs only to general
      practitioners, so other types do not have it.
     
      @param patientName the patient receiving the drug
      @param drug        the drug being prescribed
      @return a message describing the prescription
     */
    public String prescribe(String patientName, String drug) {
        if (patientName == null || drug == null) {
            return "Prescription not issued: patient and drug are required.";
        }
        return getName() + " prescribed " + drug + " to " + patientName + ".";
    }

    /*
      Adds this GP's own detail to the details provided by the parent class.
     
      @return the details of this general practitioner
     */
    @Override
    public String toString() {
        return super.toString() + " | Clinic room: " + clinicRoom;
    }
}
