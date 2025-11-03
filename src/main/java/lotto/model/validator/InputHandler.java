package lotto.model.validator;

import java.util.Arrays;
import java.util.List;
import lotto.util.ErrorCode;

public class InputHandler {
    public List<Integer> parseWinningNumbers(String input) {
        validateBlank(input);

        String[] numbers = input.split(",");

        return Arrays.stream(numbers)
                .map(String::trim)
                .peek(this::validateNumeric)
                .map(Integer::parseInt)
                .toList();
    }

    public int parseInputToNumber(String input){
        validateBlank(input);
        validateNumeric(input);
        return Integer.parseInt(input);
    }

    private void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.INPUT_BLANK.getMessage());
        }
    }

    private void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.NON_NUMERIC.getMessage());
        }
    }
}
