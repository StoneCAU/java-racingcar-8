package racingcar.domain;

import racingcar.dto.CarStatus;

import java.util.List;

public class Cars {
    private static final int MIN_CAR_COUNT = 1;

    private final List<Car> cars;
    private final MoveStrategy moveStrategy;

    public Cars(List<String> carNames) {
        this(carNames, new RandomMoveStrategy());
    }

    public Cars(List<String> carNames, MoveStrategy moveStrategy) {
        validateNames(carNames);
        this.cars = createCars(carNames);
        this.moveStrategy = moveStrategy;
    }

    public void proceedRound() {
        cars.forEach(car -> car.move(moveStrategy.generatePower()));
    }

    public List<CarStatus> snapshot() {
        return cars.stream()
                .map(Car::toStatus)
                .toList();
    }

    public List<String> findWinners() {
        int maxPosition = findMaxPosition();

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .toList();
    }

    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }

    private void validateNames(List<String> carNames) {
        validateNotEmpty(carNames);
        validateDuplicate(carNames);
    }

    private void validateNotEmpty(List<String> carNames) {
        if (carNames == null || carNames.size() < MIN_CAR_COUNT) {
            throw new IllegalArgumentException("자동차는 최소 " + MIN_CAR_COUNT + "대 이상이어야 합니다.");
        }
    }

    private void validateDuplicate(List<String> carNames) {
        if (hasDuplicate(carNames)) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    private boolean hasDuplicate(List<String> carNames) {
        return carNames.stream()
                .distinct()
                .count() != carNames.size();
    }

    private List<Car> createCars(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .toList();
    }
}
