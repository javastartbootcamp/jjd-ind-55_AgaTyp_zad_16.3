package pl.javastart.task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));

    }

    public void run(Scanner scanner) {
        // uzupełnij rozwiązanie. Korzystaj z przekazanego w parametrze scannera

        ZonedDateTime localDateTime = getDateTime(scanner);
        printZoneDateTime(localDateTime);

    }

    private void printZoneDateTime(ZonedDateTime localDateTime) {
        DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (TimeZone timeZone : TimeZone.values()) {
            ZonedDateTime zoneDateTime = localDateTime.withZoneSameInstant(timeZone.getTimeZoneId());
            String zoneDateTimeFormatted = zoneDateTime.format(dateTimePattern);
            System.out.println(timeZone.getDescription() + ": " + zoneDateTimeFormatted);
        }

    }

    private ZonedDateTime getDateTime(Scanner scanner) {
        ZoneId localZone = ZoneId.systemDefault();
        boolean error = true;
        LocalDateTime dateTime = null;

        do {
            System.out.println("Podaj datę:");
            String inputString = scanner.nextLine();
            if (inputString.startsWith("t")) {
                dateTime = convertTimeWithOffset(inputString);
                error = false;
            } else {
                String[] dateAndTime = inputString.split(" ");
                String dateInput = dateAndTime[0].replace(".", "-");
                if (dateInput.split("-").length != 3) {
                    System.out.println("Zły format daty.");
                } else {
                    error = false;
                    dateTime = convertTimeFromInput(dateAndTime, dateInput);
                }
            }

        } while (error);

        return ZonedDateTime.of(dateTime, localZone);

    }

    private LocalDateTime convertTimeWithOffset(String inputString) {
        return ReadTimeOffsets.splitInputString(inputString);

    }

    private LocalDateTime convertTimeFromInput(String[] dateAndTime, String dateInput) {
        String timeInput = "00:00:00";
        LocalDate createdDate;

        DateTimeFormatter datePatternDayFirst = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter datePatternDayLast = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("HH:mm:ss");

        if (dateAndTime.length == 2) {
            timeInput = dateAndTime[1];
        }
        try {
            createdDate = LocalDate.parse(dateInput, datePatternDayFirst);
        } catch (DateTimeParseException e) {
            createdDate = LocalDate.parse(dateInput, datePatternDayLast);
        }

        LocalTime createTime = LocalTime.parse(timeInput, timePattern);
        return LocalDateTime.of(createdDate, createTime);
    }
}
