package racingcar.view;

import dto.CarStatus;

import java.util.List;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String RESULT_TITLE = "실행 결과";
    private static final String RESULT_FORMAT = "%s : %s";
    private static final String POSITION_SYMBOL = "-";

    public void printResultMessage() {
        printNewLine();
        System.out.println(RESULT_TITLE);
    }

    public void printNewLine() {
        System.out.print(NEW_LINE);
    }

    public void printRoundResult(List<CarStatus> statuses) {
        statuses.forEach(status ->
                System.out.printf(RESULT_FORMAT + NEW_LINE,
                        status.name(),
                        POSITION_SYMBOL.repeat(status.position()))
        );
        printNewLine();
    }
}
