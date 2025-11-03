package lotto.model.validator;

import java.util.Arrays;
import java.util.List;
import lotto.util.ErrorCode;

public class InputHandler {
    private static final int MAX_NUMBER = 45;
    private static final int MIN_NUMBER = 1;
    private static final int REQUIRED_LOTTO_NUMBERS = 6;
    private static final int LOTTO_PRICE = 1000;

    public int parsePurchaseMoney(String input) {
        validateBlank(input);
        validateNumeric(input);

        int number = Integer.parseInt(input);
        validatePurchaseMoney(number);
        return number;
    }

    public int parseBonusNumber(String input) {
        validateBlank(input);
        validateNumeric(input);

        int number = Integer.parseInt(input);
        validateNumberInRange(number);
        return number;
    }

    public List<Integer> parseWinningNumbers(String input) {
        validateBlank(input);

        String[] numbers = input.split(",");

        List<Integer> parsedNumbers = Arrays.stream(numbers)
                .map(String::trim)
                .peek(this::validateNumeric)
                .map(Integer::parseInt)
                .peek(this::validateNumberInRange)
                .toList();

        validateWinningNumbersSize(parsedNumbers);
        validateDuplicateNumber(parsedNumbers);

        return parsedNumbers;
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

    private void validateNumberInRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(
                    ErrorCode.NUMBER_OUT_OF_RANGE.getFormattedMessage(MIN_NUMBER, MAX_NUMBER));
        }
    }

    private void validateWinningNumbersSize(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_LOTTO_NUMBERS) {
            throw new IllegalArgumentException(
                    ErrorCode.INVALID_LOTTO_SIZE.getFormattedMessage(REQUIRED_LOTTO_NUMBERS));
        }
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(ErrorCode.NUMBER_DUPLICATE.getMessage());
        }
    }

    private void validatePurchaseMoney(int purchaseMoney) {
        if (purchaseMoney % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    ErrorCode.INVALID_MONEY.getFormattedMessage(LOTTO_PRICE));
        }
    }
}
