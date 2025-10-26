package racingcar.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarNamesParserTest {
    @Test
    @DisplayName("쉼표로 구분된 이름을 파싱한다")
    void parseWithComma() {
        List<String> carNames = CarNamesParser.parse("pobi,crong,jun");

        assertThat(carNames).containsExactly("pobi", "crong", "jun");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            " pobi , crong , jun ",
            "  pobi  ,  crong  ,  jun  ",
            "\tpobi\t,\tcrong\t,\tjun\t"
    })
    @DisplayName("쉼표로 구분된 이름 중 공백을 제거하고 파싱한다")
    void parseWithCommaAndSpaces(String input) {
        List<String> names = CarNamesParser.parse(input);

        assertThat(names).containsExactly("pobi", "crong", "jun");
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi", " pobi ", "  pobi  "})
    @DisplayName("하나의 이름만 입력해도 파싱한다")
    void parseWithSingleName(String input) {
        List<String> names = CarNamesParser.parse(input);

        assertThat(names).containsExactly("pobi");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", " \t ", "  \n  "})
    @DisplayName("빈 문자열이나 null이면 예외가 발생한다")
    void invalidEmptyOrNullCarNames(String input) {
        assertThatThrownBy(() -> CarNamesParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름을 입력해주세요.");
    }

    @Test
    @DisplayName("이름이 많아도 정상 파싱된다")
    void parseWithManyNames() {
        List<String> names = CarNamesParser.parse("a,b,c,d,e,f,g,h,i,j");

        assertThat(names).hasSize(10)
                .containsExactly("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
    }

    @Test
    @DisplayName("5자 이름도 정상 파싱된다")
    void parseWithMaxLengthName() {
        List<String> names = CarNamesParser.parse("abcde,fghij");

        assertThat(names).containsExactly("abcde", "fghij");
    }
}