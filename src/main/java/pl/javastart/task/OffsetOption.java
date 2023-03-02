package pl.javastart.task;

import java.time.temporal.ChronoUnit;

public enum OffsetOption {
    YEAR("y", ChronoUnit.YEARS),
    MONTH("M", ChronoUnit.MONTHS),
    DAY("d", ChronoUnit.DAYS),
    HOUR("h", ChronoUnit.HOURS),
    MINUTE("m", ChronoUnit.MINUTES),
    SECOND("s", ChronoUnit.SECONDS);

    private final String description;
    private final ChronoUnit period;

    OffsetOption(String description, ChronoUnit period) {
        this.description = description;
        this.period = period;
    }

    public ChronoUnit getPeriod() {
        return period;
    }

    public String getDescription() {
        return description;
    }

    public static OffsetOption fromDescription(String description) {
        OffsetOption[] values = values();
        for (OffsetOption option : values) {
            if (option.getDescription().equals(description)) {
                return option;
            }
        }
        return  null;
    }

    @Override
    public String toString() {
        return "OffsetOptions{" +
                "description='" + description + '\'' +
                '}';
    }
}
