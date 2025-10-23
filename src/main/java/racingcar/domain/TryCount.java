package racingcar.domain;

public class TryCount {
    private static final int MIN_VALUE = 1;

    private final int value;

    public TryCount(String input) {
        validateNotEmpty(input);
        int number = parseToInt(input);
        validateRange(number);
        this.value = number;
    }

    public int getValue() {
        return value;
    }

    private void validateNotEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("시도 횟수를 입력해주세요.");
        }
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 합니다.");
        }
    }

    private void validateRange(int number) {
        if (number < MIN_VALUE) {
            throw new IllegalArgumentException(String.format("시도 횟수는 %d 이상이어야 합니다.", MIN_VALUE));
        }
    }
}