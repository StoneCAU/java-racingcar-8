package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TryCountTest {
    @Test
    @DisplayName("시도 횟수를 생성한다")
    void createTryCount() {
        TryCount tryCount = new TryCount("5");

        assertThat(tryCount.hasCount()).isTrue();
    }

    @Test
    @DisplayName("시도 횟수가 빈 문자열이면 예외가 발생한다")
    void invalidEmptyTryCount() {
        assertThatThrownBy(() -> new TryCount(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 숫자여야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 null이면 예외가 발생한다")
    void invalidNullTryCount() {
        assertThatThrownBy(() -> new TryCount(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("시도 횟수는 숫자여야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 공백이면 예외가 발생한다")
    void invalidBlankTryCount() {
        assertThatThrownBy(() -> new TryCount("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 숫자여야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 숫자가 아니면 예외가 발생한다")
    void notNumberTryCount() {
        assertThatThrownBy(() -> new TryCount("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 숫자여야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 0이면 예외가 발생한다")
    void invalidZeroTryCount() {
        assertThatThrownBy(() -> new TryCount("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟수는 1 이상이어야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 음수면 예외가 발생한다")
    void invalidNegativeTryCount() {
        assertThatThrownBy(() -> new TryCount("-5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟수는 1 이상이어야 합니다.");
    }

    @Test
    @DisplayName("앞뒤 공백이 포함되어도 정상 파싱된다")
    void shouldTrimInputAndCreateSuccessfully() {
        assertThatCode(() -> new TryCount(" 5")).doesNotThrowAnyException();
        assertThatCode(() -> new TryCount("5 ")).doesNotThrowAnyException();
        assertThatCode(() -> new TryCount("  5  ")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("횟수를 모두 소진하면 false를 반환한다")
    void decreaseCountUntilZero() {
        TryCount tryCount = new TryCount("2");

        tryCount.decrease();
        assertThat(tryCount.hasCount()).isTrue();

        tryCount.decrease();
        assertThat(tryCount.hasCount()).isFalse();
    }

    @Test
    @DisplayName("while 루프로 hasCount()를 사용해 횟수를 모두 소진한다")
    void loopDecreasesCountToZero() {
        TryCount tryCount = new TryCount("3");
        int executedRounds = 0;

        while (tryCount.hasCount()) {
            executedRounds++;
            tryCount.decrease();
        }

        assertThat(executedRounds).isEqualTo(3);
        assertThat(tryCount.hasCount()).isFalse();
    }
}
