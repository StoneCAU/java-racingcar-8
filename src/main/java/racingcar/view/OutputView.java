package racingcar.view;

import racingcar.dto.CarStatus;
import java.util.List;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String RESULT_HEADER_MESSAGE = "실행 결과";
    private static final String CAR_STATUS_FORMAT = "%s : %s";
    private static final String POSITION_MARK = "-";
    private static final String WINNER_ANNOUNCEMENT_FORMAT = "최종 우승자 : %s";
    private static final String WINNER_DELIMITER = ", ";

    public void printResultHeader() {
        printNewLine();
        System.out.println(RESULT_HEADER_MESSAGE);
    }

    public void printRoundResult(List<CarStatus> statuses) {
        statuses.forEach(status ->
                System.out.printf(CAR_STATUS_FORMAT + NEW_LINE,
                        status.name(),
                        POSITION_MARK.repeat(status.position()))
        );
        printNewLine();
    }

    public void printWinners(List<String> winners) {
        String joinedNames = String.join(WINNER_DELIMITER, winners);
        System.out.printf(WINNER_ANNOUNCEMENT_FORMAT + NEW_LINE, joinedNames);
    }

    private void printNewLine() {
        System.out.print(NEW_LINE);
    }
}
