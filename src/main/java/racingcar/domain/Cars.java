package racingcar.domain;

import dto.CarStatus;

import java.util.List;

public class Cars {
    private static final int MIN_CAR_COUNT = 1;

    private final List<Car> cars;

    public Cars(List<String> carNames) {
        validateNames(carNames);
        this.cars = createCars(carNames);
    }

    public List<CarStatus> snapshot() {
        return cars.stream()
                .map(Car::toStatus)
                .toList();
    }

    private void validateNames(List<String> carNames) {
        validateNotEmpty(carNames);
        validateDuplicate(carNames);
    }

    private void validateNotEmpty(List<String> carNames) {
        if (carNames == null || carNames.size() < MIN_CAR_COUNT) {
            throw new IllegalArgumentException("자동차는 최소 1대 이상이어야 합니다.");
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

    public void race(MoveStrategy strategy) {
        cars.forEach(car -> car.move(strategy.generatePower()));
    }
}
