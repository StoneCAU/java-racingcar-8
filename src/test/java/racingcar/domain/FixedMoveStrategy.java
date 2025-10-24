package racingcar.domain;

public class FixedMoveStrategy implements MoveStrategy {
    private final int power;

    public FixedMoveStrategy(int power) {
        this.power = power;
    }

    @Override
    public int generatePower() {
        return power;
    }
}
