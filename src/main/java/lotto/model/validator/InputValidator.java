package lotto.model.validator;

import lotto.util.ErrorCode;

public class InputValidator {
    public void validateNumeric(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException(ErrorCode.NON_NUMERIC.getMessage());
        }
    }

    private boolean isNumeric(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.INPUT_BLANK.getMessage());
        }

        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
