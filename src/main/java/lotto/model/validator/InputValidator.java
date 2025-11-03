package lotto.model.validator;

public class InputValidator {
    public void validateNumeric(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력할 수 있습니다.");
        }
    }

    private boolean isNumeric(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력 값은 비어 있을 수 없습니다.");
        }

        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
