public class Lawyer {
    private String name;
    private String specialization;

    // Constructor to initialize the attributes
    public Lawyer(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    // Getters (optional, useful for further development)
    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    // Override toString method to provide a string representation
    @Override
    public String toString() {
        return name + "," + specialization;
    }
}
