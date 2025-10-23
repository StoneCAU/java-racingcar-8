package racingcar.view;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String RESULT_TITLE = "실행 결과";

    public void printResultMessage() {
        printNewLine();
        System.out.println(RESULT_TITLE);
    }

    public void printNewLine() {
        System.out.print(NEW_LINE);
    }
}
