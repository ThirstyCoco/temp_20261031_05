//A dietitian, who prepares meal plans for patients. 
public class Dietitian extends HealthProfessional {

    private final String dietType;

    /** Creates a dietitian with an id supplied by the caller. */
    public Dietitian(String id, String name, String[] workingDays, String dietType) {
        super(id, name, workingDays);
        this.dietType = (dietType == null || dietType.trim().isEmpty()) ? "General" : dietType;
    }

    /** Creates a dietitian and lets the parent class generate the id. */
    public Dietitian(String name, String[] workingDays, String dietType) {
        super(name, workingDays);
        this.dietType = (dietType == null || dietType.trim().isEmpty()) ? "General" : dietType;
    }

    /** Returns the type of diet this dietitian specialises in. */
    public String getDietType() {
        return dietType;
    }

    /** Identifies this professional as a dietitian. */
    @Override
    public String getRole() {
        return "Dietitian";
    }

    /** Creates a meal plan, which only dietitians do in this service. */
    public String createMealPlan(String patientName) {
        if (patientName == null || patientName.trim().isEmpty()) {
            return "Meal plan not created: a patient name is required.";
        }
        return getName() + " created a " + dietType.toLowerCase()
                + " meal plan for " + patientName + ".";
    }

    /** Adds the diet type to the details provided by the parent class. */
    @Override
    public String toString() {
        return super.toString() + " | Diet type: " + dietType;
    }
}
