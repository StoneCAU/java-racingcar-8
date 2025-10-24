package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CarsTest {
    @Test
    @DisplayName("자동차 목록을 생성한다")
    public void createCars() {
        Cars cars = new Cars(List.of("pobi", "crong", "jun"));
        List<CarStatus> statuses = cars.snapshot();

        assertThat(statuses)
                .hasSize(3)
                .extracting("name")
                .containsExactly("pobi", "crong", "jun");

        assertThat(statuses)
                .allMatch(status -> status.position() == 0);
    }

    @Test
    @DisplayName("자동차가 0대 이면 예외가 발생한다")
    public void invalidSizeCars() {
        assertThatThrownBy(() -> new Cars(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차는 최소 1대 이상이어야 합니다.");
    }

    @Test
    @DisplayName("자동차 목록이 null이면 예외가 발생한다")
    public void invalidNullCars() {
        assertThatThrownBy(() -> new Cars(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차는 최소 1대 이상이어야 합니다.");
    }

    @Test
    @DisplayName("자동차 이름이 중복되면 예외가 발생한다")
    public void duplicatedCarName() {
        assertThatThrownBy(() -> new Cars(List.of("pobi", "crong", "pobi")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("모든 자동차가 정지한다")
    void allCarsStopWhenPowerBelowThreshold() {
        Cars cars = new Cars(List.of("pobi", "crong", "jun"));
        MoveStrategy neverMove = () -> 2;

        cars.proceedRound(neverMove);

        List<CarStatus> statuses = cars.snapshot();
        assertThat(statuses)
                .extracting(CarStatus::position)
                .containsExactly(0, 0, 0);
    }

    @Test
    @DisplayName("각 자동차가 주어진 power에 따라 이동한다")
    void proceedRoundCarsAccordingToPower() {
        Cars cars = new Cars(List.of("pobi", "crong", "jun"));
        MoveStrategy strategy = new TestMoveStrategy(4, 3, 9);

        cars.proceedRound(strategy);

        List<CarStatus> statuses = cars.snapshot();
        assertThat(statuses)
                .extracting(CarStatus::position)
                .containsExactly(1, 0, 1);
    }

    @Test
    @DisplayName("여러 라운드가 진행되면 위치가 누적 증가한다")
    void accumulatePositionAcrossRounds() {
        Cars cars = new Cars(List.of("pobi", "crong"));
        MoveStrategy alwaysMove = () -> 9;

        cars.proceedRound(alwaysMove);
        cars.proceedRound(alwaysMove);
        cars.proceedRound(alwaysMove);

        List<CarStatus> statuses = cars.snapshot();
        assertThat(statuses)
                .extracting(CarStatus::position)
                .containsExactly(3, 3);
    }

    @Test
    @DisplayName("최대 위치를 가진 자동차가 단독 우승자로 선정된다")
    void findSingleWinner() {
        Cars cars = new Cars(List.of("pobi", "crong", "jun"));
        MoveStrategy strategy = new TestMoveStrategy(9, 0, 0);

        cars.proceedRound(strategy);

        assertThat(cars.findWinners()).containsExactly("pobi");
    }

    @Test
    @DisplayName("최대 위치가 같은 자동차는 공동 우승자로 선정된다")
    void findMultipleWinners() {
        Cars cars = new Cars(List.of("pobi", "crong", "jun"));
        MoveStrategy strategy = new TestMoveStrategy(9, 9, 3);

        cars.proceedRound(strategy);

        assertThat(cars.findWinners()).containsExactlyInAnyOrder("pobi", "crong");
    }

    @Test
    @DisplayName("snapshot은 내부 상태를 복사하여 반환한다")
    void snapshotIsImmutableCopy() {
        Cars cars = new Cars(List.of("pobi", "crong"));
        List<CarStatus> first = cars.snapshot();

        cars.proceedRound(() -> 9);

        List<CarStatus> second = cars.snapshot();

        assertThat(first.getFirst().position()).isEqualTo(0);
        assertThat(second.getFirst().position()).isEqualTo(1);
    }
}
