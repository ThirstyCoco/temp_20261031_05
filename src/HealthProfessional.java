
public class HealthProfessional {

    /** Counter used to generate ids when the caller does not supply one. */
    private static int idCounter = 0;

    private final String id;
    private final String name;
    private final String[] workingDays;
    /** False when any of the values passed to the constructor were invalid. */
    private final boolean valid;

    /**
     * Creates a health professional with an id supplied by the caller.
     *
     * @param id          the professional's id, which must contain digits only
     * @param name        the professional's name
     * @param workingDays the days this professional works each week
     */
    public HealthProfessional(String id, String name, String[] workingDays) {
        this.id = id;
        this.name = name;
        // Copy the array so later changes to the caller's array cannot reach us.
        this.workingDays = (workingDays == null) ? new String[0] : workingDays.clone();
        this.valid = validate();
    }

    /**
     * Creates a health professional and generates the next id automatically.
     *
     * @param name        the professional's name
     * @param workingDays the days this professional works each week
     */
    public HealthProfessional(String name, String[] workingDays) {
        this(String.valueOf(++idCounter), name, workingDays);
    }

    /**
     * Since A1 doesn't know anything about 
     * error handling, this method is used 
     * to give a warning for invalid values.
     *
     * @return true when every value is acceptable
     */
    private boolean validate() {
        boolean ok = true;

        if (id == null || !id.matches("\\d+")) {
            System.out.println("Warning: id \"" + id + "\" must contain digits only.");
            ok = false;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Warning: name must not be empty.");
            ok = false;
        }
        if (workingDays.length == 0) {
            System.out.println("Warning: at least one working day is required.");
            ok = false;
        }
        return ok;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the working days, so external code cannot change the
     * array this object holds.
     *
     * @return a copy of the working days
     */
    public String[] getWorkingDays() {
        return workingDays.clone();
    }

    public boolean isValid() {
        return valid;
    }

    /**
     * Tells whether this professional works on the given day.
     *
     * @param day the day to check, for example "Monday"
     * @return true when the day appears in the professional's working days
     */
    public boolean worksOn(String day) {
        if (day == null) {
            return false;
        }
        for (String workingDay : workingDays) {
            if (workingDay.equalsIgnoreCase(day)) {
                return true;
            }
        }
        return false;
    }
}
