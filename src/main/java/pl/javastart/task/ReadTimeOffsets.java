package pl.javastart.task;

import java.time.LocalDateTime;

public class ReadTimeOffsets {

    public static LocalDateTime splitInputString(String inputString) {
        int inputLength = inputString.length();
        String[] signs = inputString.split("");
        String numberString = "";
        String operationString = "";
        LocalDateTime dateTimeNow = LocalDateTime.now();

        for (int i = 1; i < inputLength; i++) {
            char sign = signs[i].charAt(0);
            if (Character.isLetter(sign)) {
                dateTimeNow = calcDate(dateTimeNow, operationString, numberString, signs[i]);
                numberString = "";
            } else if (Character.isDigit(sign)) {
                numberString += sign;
            } else {
                operationString = signs[i];
            }

        }
        return dateTimeNow;
    }

    private static LocalDateTime calcDate(LocalDateTime dateTimeNow, String operationString, String numberString, String offset) {
        return switch (Operation.fromDescription(operationString)) {
            case ADD ->
                    dateTimeNow.plus(Integer.parseInt(numberString), OffsetOption.fromDescription(offset).getPeriod());
            case SUBTRACT ->
                    dateTimeNow.minus(Integer.parseInt(numberString), OffsetOption.fromDescription(offset).getPeriod());
        };

    }
}
