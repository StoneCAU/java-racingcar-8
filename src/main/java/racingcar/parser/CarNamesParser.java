package racingcar.parser;

import java.util.Arrays;
import java.util.List;

public class CarNamesParser {
    private static final String DELIMITER = ",";

    private CarNamesParser() {
    }

    public static List<String> parse(String input) {
        validateNotEmpty(input);
        return parseToList(input);
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("자동차 이름을 입력해주세요.");
        }
    }

    private static List<String> parseToList(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }
}