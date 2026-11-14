public class HealthProfessional {

    /** Counter used to generate ids when the caller does not supply one. */
    private static int idCounter = 0;

    private final String id;
    private final String name;
    private final String[] workingDays;

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
        this.workingDays = workingDays;
    }

    /**
     * Create a health professional and generates next id
     *
     * @param name        the professional's name
     * @param workingDays the days this professional works each week
     */
    public HealthProfessional(String name, String[] workingDays) {
        this(String.valueOf(++idCounter), name, workingDays);
    }

    public String getId() {
        return id;
    }
}
