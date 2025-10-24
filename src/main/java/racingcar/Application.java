package racingcar;

import racingcar.controller.RacingController;
import racingcar.domain.MoveStrategy;
import racingcar.domain.RandomMoveStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MoveStrategy moveStrategy = new RandomMoveStrategy();

        RacingController racingController = new RacingController(new InputView(), new OutputView(), moveStrategy);
        racingController.run();
    }
}