package racingcar.controller;

import racingcar.util.CarNamesParser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingController {
    private final InputView inputView;
    private final OutputView outputView;

    public RacingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String carNamesInput = inputView.readCarNames();
        List<String> carNames = CarNamesParser.parse(carNamesInput);
    }
}
