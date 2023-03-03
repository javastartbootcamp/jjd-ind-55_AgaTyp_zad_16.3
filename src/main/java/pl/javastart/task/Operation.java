package pl.javastart.task;

public enum Operation {
    ADD("+"),
    SUBTRACT("-");

    private final String description;

    Operation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Operation fromDescription(String description) {
        Operation[] values = values();
        for (Operation operation : values) {
            if (operation.getDescription().equals(description)) {
                return operation;
            }
        }
        return  null;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "description='" + description + '\'' +
                '}';
    }
}
