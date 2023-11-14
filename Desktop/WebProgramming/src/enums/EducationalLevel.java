package enums;

public enum EducationalLevel {
    CHILDREN("Children (4-6 years)"), YOUTH("Youth (7-12 years)"), TEENAGER("Teenager (13-17 years)");

    private final String description;

    EducationalLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}