package racingcar.controller;

import racingcar.dto.CarStatus;
import racingcar.domain.Cars;
import racingcar.domain.MoveStrategy;
import racingcar.domain.TryCount;
import racingcar.parser.CarNamesParser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final MoveStrategy moveStrategy;

    public RacingController(InputView inputView, OutputView outputView, MoveStrategy moveStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.moveStrategy = moveStrategy;
    }

    public void run() {
        Cars cars = inputCars();
        TryCount tryCount = inputTryCount();

        playGame(cars, tryCount);
    }

    private Cars inputCars() {
        String carNamesInput = inputView.readCarNames();
        List<String> carNames = CarNamesParser.parse(carNamesInput);
        return new Cars(carNames);
    }

    private TryCount inputTryCount() {
        String tryCountInput = inputView.readTryCount();
        return new TryCount(tryCountInput);
    }

    private void playGame(Cars cars, TryCount tryCount) {
        outputView.printResultMessage();
        runRace(cars, tryCount);
        printWinners(cars);
    }

    private void runRace(Cars cars, TryCount tryCount) {
        while (tryCount.hasCount()) {
            cars.race(moveStrategy);
            List<CarStatus> statuses = cars.snapshot();
            outputView.printRoundResult(statuses);
            tryCount.decrease();
        }
    }

    private void printWinners(Cars cars) {
        List<String> winners = cars.findWinners();
        outputView.printWinners(winners);
    }
}
