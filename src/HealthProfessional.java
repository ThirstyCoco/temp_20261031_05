import java.util.Arrays;
import java.util.Objects;

/** A health professional. Holds the details every type of professional shares. */
public abstract class HealthProfessional {

    /** Counter used to generate ids when the caller does not supply one. */
    private static int idCounter = 0;

    /** The only day names the health service recognises. */
    private static final String[] VALID_DAYS = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    private final String id;
    private final String name;
    private final String[] workingDays;
    /** False when any of the values passed to the constructor were invalid. */
    private final boolean valid;

    /** Creates a professional from an id, a name and the days worked each week. */
    protected HealthProfessional(String id, String name, String[] workingDays) {
        this.id = id;
        this.name = name;
        // Copy the array so later changes to the caller's array cannot reach us.
        this.workingDays = (workingDays == null) ? new String[0] : workingDays.clone();
        this.valid = validate();
    }

    /** Creates a professional and lets the class generate the next id. */
    protected HealthProfessional(String name, String[] workingDays) {
        this(String.valueOf(++idCounter), name, workingDays);
    }

    /** Checks the stored values, reports anything invalid, and stores the official
        spelling of every recognised working day. */
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
        for (int i = 0; i < workingDays.length; i++) {
            String officialName = officialDay(workingDays[i]);
            if (officialName == null) {
                System.out.println("Warning: \"" + workingDays[i]
                        + "\" is not a day of the week.");
                ok = false;
            } else {
                workingDays[i] = officialName;
            }
        }
        return ok;
    }

    /** Returns the official spelling of a day, or null when the name is unknown. */
    private static String officialDay(String day) {
        if (day == null) {
            return null;
        }
        for (String validDay : VALID_DAYS) {
            if (validDay.equalsIgnoreCase(day.trim())) {
                return validDay;
            }
        }
        return null;
    }

    /** Every specific type has to say what kind of professional it is. */
    public abstract String getRole();

    /** Returns the id of this professional. */
    public String getId() {
        return id;
    }

    /** Returns the professional's name. */
    public String getName() {
        return name;
    }

    /** Returns a copy of the working days so external code cannot change them. */
    public String[] getWorkingDays() {
        return workingDays.clone();
    }

    /** Tells whether this professional was created from acceptable values. */
    public boolean isValid() {
        return valid;
    }

    /** Tells whether this professional works on the given day, for example "Monday". */
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

    /** Returns a readable description; the type comes from getRole(). */
    @Override
    public String toString() {
        return getRole() + "[ID: " + id
                + ", Name: " + name
                + ", Days: " + Arrays.toString(workingDays) + "]";
    }

    /** Two professionals are the same person when they have the same id. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HealthProfessional other = (HealthProfessional) o;
        return Objects.equals(id, other.id);
    }

    /** Uses the same field as equals so the two methods stay consistent. */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
