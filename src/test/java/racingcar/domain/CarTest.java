package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CarTest {
    @Test
    @DisplayName("자동차를 생성한다")
    void createCar() {
        Car car = new Car("pobi");

        assertAll(
                () -> assertThat(car.getName()).isEqualTo("pobi"),
                () -> assertThat(car.getPosition()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("이름이 빈 문자열이면 예외가 발생한다")
    void invalidEmptyCarName() {
        assertThatThrownBy(() -> new Car(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 비어 있을 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 null이면 예외가 발생한다")
    void invalidNullCarName() {
        assertThatThrownBy(() -> new Car(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 비어 있을 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 5자를 초과하면 예외가 발생한다")
    void invalidLongCarName() {
        assertThatThrownBy(() -> new Car("polarbear"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 5자를 초과할 수 없습니다.");
    }

    @Test
    @DisplayName("파워가 4 이상이면 전진한다")
    void moveForwardWhenPowerIsAtLeastThreshold() {
        Car car = new Car("pobi");

        car.move(4);

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("파워가 4 미만이면 정지한다")
    void stopWhenPowerIsBelowThreshold() {
        Car car = new Car("pobi");

        car.move(3);

        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("여러 번 전진할 수 있다")
    void moveMultipleTimes() {
        Car car = new Car("pobi");

        car.move(4);
        car.move(5);
        car.move(9);

        assertThat(car.getPosition()).isEqualTo(3);
    }

    @Test
    @DisplayName("CarStatus로 변환한다")
    void convertToCarStatus() {
        Car car = new Car("pobi");
        car.move(4);
        car.move(5);

        CarStatus status = car.toStatus();

        assertAll(
                () -> assertThat(status.name()).isEqualTo("pobi"),
                () -> assertThat(status.position()).isEqualTo(2)
        );
    }
}