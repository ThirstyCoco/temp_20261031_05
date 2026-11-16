import java.util.Objects;

/** A patient who books appointments with a health professional. */
public class Patient {

    private final String name;
    private final String phone;
    /** False when any of the values passed to the constructor were invalid. */
    private final boolean valid;

    /** Creates a patient from a name and a ten digit phone number. */
    public Patient(String name, String phone) {
        this.name = name;
        this.phone = (phone == null) ? null : phone.trim();
        this.valid = validate();
    }

    /** Checks the values stored in this object and reports anything invalid. */
    private boolean validate() {
        boolean ok = true;

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Warning: patient name must not be empty.");
            ok = false;
        }
        if (phone == null || !phone.matches("\\d{10}")) {
            System.out.println("Warning: phone number \"" + phone
                    + "\" must contain exactly 10 digits.");
            ok = false;
        }
        return ok;
    }

    /** Returns the patient's name. */
    public String getName() {
        return name;
    }

    /** Returns the patient's phone number. */
    public String getPhone() {
        return phone;
    }

    /** Tells whether this patient was created from acceptable values. */
    public boolean isValid() {
        return valid;
    }

    /** Two patients are the same person when they share a phone number. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Patient other = (Patient) o;
        return Objects.equals(phone, other.phone);
    }

    /** Uses the same field as equals so the two methods stay consistent. */
    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }

    /** Returns a readable description used whenever this patient is printed. */
    @Override
    public String toString() {
        return "Patient[Name: " + name + ", Phone: " + phone + "]";
    }
}
