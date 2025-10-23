package racingcar.view;

import dto.CarStatus;
import java.util.List;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String RESULT_HEADER_MESSAGE = "실행 결과";
    private static final String CAR_STATUS_FORMAT = "%s : %s";
    private static final String POSITION_MARK = "-";

    public void printResultMessage() {
        printNewLine();
        System.out.println(RESULT_HEADER_MESSAGE);
    }

    public void printNewLine() {
        System.out.print(NEW_LINE);
    }

    public void printRoundResult(List<CarStatus> statuses) {
        statuses.forEach(status ->
                System.out.printf(CAR_STATUS_FORMAT + NEW_LINE,
                        status.name(),
                        POSITION_MARK.repeat(status.position()))
        );
        printNewLine();
    }
}
