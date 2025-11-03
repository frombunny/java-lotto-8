package lotto.util;

public enum ErrorCode {
    INPUT_BLANK("입력 값은 비어 있을 수 없습니다."),
    NON_NUMERIC("숫자만 입력할 수 있습니다."),
    INVALID_MONEY("구입 금액은 %,d원 단위로 입력해야 합니다."),
    NUMBER_OUT_OF_RANGE("로또 번호는 %,d부터 %,d 사이의 숫자여야 합니다."),
    NUMBER_DUPLICATE("로또 번호는 중복될 수 없습니다."),
    INVALID_LOTTO_SIZE("로또 번호는 %,d개여야 합니다.");

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_MESSAGE_PREFIX + message;
    }

    public String getFormattedMessage(Object... args) {
        return ERROR_MESSAGE_PREFIX + String.format(message, args);
    }
}