package racingcar.domain;

public class TestMoveStrategy implements MoveStrategy {
    private final int[] powers;
    private int index = 0;

    public TestMoveStrategy(int... powers) {
        this.powers = powers;
    }

    @Override
    public int generatePower() {
        return powers[index++];
    }
}