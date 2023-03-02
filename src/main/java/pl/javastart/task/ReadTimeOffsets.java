package pl.javastart.task;

import java.time.Duration;
import java.time.LocalDateTime;

public class ReadTimeOffsets {

    public static LocalDateTime splitInputString(String inputString) {
        int inputLength = inputString.length();
        String[] signs = inputString.split("");
        String numberString = "";
        String operationString = "";
        LocalDateTime dateTimeNow = LocalDateTime.now();

//        System.out.println(dateTimeNow);
        for (int i = 1; i < inputLength; i++) {
//            System.out.println(signs[i]);

            char sign = signs[i].charAt(0);
            if (Character.isLetter(sign)) {
//                System.out.println(OffsetOption.fromDescription(signs[i]));
//                System.out.println(numberString);
                dateTimeNow = calcDate(dateTimeNow, operationString, numberString, signs[i]);
                numberString = "";
            } else if (Character.isDigit(sign)) {
                numberString += sign;
            } else {
                operationString = signs[i];
//                System.out.println(Operation.fromDescription(signs[i]));

            }

        }
//        System.out.println(dateTimeNow);
        return dateTimeNow;
    }

    private static LocalDateTime calcDate(LocalDateTime dateTimeNow, String operationString, String numberString, String offset) {
        LocalDateTime dateTime;
        switch (Operation.fromDescription(operationString)) {
            case ADD :
                dateTime = dateTimeNow.plus(Duration.of(Integer.parseInt(numberString), OffsetOption.fromDescription(offset).getPeriod()));
                break;
            case SUBTRACT:
                dateTime = dateTimeNow.minus(Duration.of(Integer.parseInt(numberString), OffsetOption.fromDescription(offset).getPeriod()));
                break;
            default:
                dateTime = dateTimeNow;
                break;
        }
//        System.out.println(dateTime);
        return dateTime;

    }
}
