package racingcar.domain;

public class TryCount {
    private static final int MIN_TRY_COUNT = 1;

    private int count;

    public TryCount(String input) {
        this.count = parseToInt(input.trim());
        validateRange(count);
    }

    public boolean hasCount() {
        return count > 0;
    }

    public void decrease() {
        count--;
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 합니다.");
        }
    }

    private void validateRange(int number) {
        if (number < MIN_TRY_COUNT) {
            throw new IllegalArgumentException("시도 횟수는 " + MIN_TRY_COUNT + " 이상이어야 합니다.");
        }
    }
}