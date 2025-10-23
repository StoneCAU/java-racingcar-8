package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMoveStrategy implements MoveStrategy {
    private final static int MIN = 0;
    private final static int MAX= 9;

    @Override
    public int generatePower() {
        return Randoms.pickNumberInRange(MIN, MAX);
    }
}
