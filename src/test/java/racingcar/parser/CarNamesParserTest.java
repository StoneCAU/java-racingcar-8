package racingcar.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CarNamesParserTest {
    @Test
    @DisplayName("쉼표로 구분된 이름을 파싱한다")
    void parseWithComma() {
        List<String> carNames = CarNamesParser.parse("pobi,crong,jun");

        assertThat(carNames).containsExactly("pobi", "crong", "jun");
    }

    @Test
    @DisplayName("쉼표로 구분된 이름 중 공백을 제거하고 파싱한다")
    void parseWithCommaAndSpaces() {
        List<String> names = CarNamesParser.parse(" pobi , crong , jun ");

        assertThat(names).containsExactly("pobi", "crong", "jun");
    }

    @Test
    @DisplayName("하나의 이름만 입력해도 파싱한다")
    void parseWithSingleName() {
        List<String> names = CarNamesParser.parse("pobi");

        assertThat(names).containsExactly("pobi");
    }

    @Test
    @DisplayName("빈 문자열 입력 시 예외가 발생한다")
    void invalidEmptyCarNames() {
        assertThatThrownBy(() -> CarNamesParser.parse(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름을 입력해주세요.");
    }

    @Test
    @DisplayName("null이면 예외가 발생한다")
    void invalidNullCarNames() {
        assertThatThrownBy(() -> CarNamesParser.parse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름을 입력해주세요.");
    }
}
