package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.util.ErrorCode;

public class Lotto {
    private static final int MAX_NUMBER = 45;
    private static final int MIN_NUMBER = 1;
    private static final int REQUIRED_LOTTO_NUMBERS = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto generate() {
        List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, REQUIRED_LOTTO_NUMBERS);
        return new Lotto(randomNumbers);
    }

    public int countMatchedNumbers(List<Integer> winningNumbers) {
        Set<Integer> intersection = new HashSet<>(numbers);
        intersection.retainAll(winningNumbers);
        return intersection.size();
    }

    public boolean hasBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicateNumber(numbers);
        validateNumberRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_LOTTO_NUMBERS) {
            throw new IllegalArgumentException(
                    ErrorCode.INVALID_LOTTO_SIZE.getFormattedMessage(REQUIRED_LOTTO_NUMBERS));
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(number -> number < MIN_NUMBER || number > MAX_NUMBER)) {
            throw new IllegalArgumentException(
                    ErrorCode.NUMBER_OUT_OF_RANGE.getFormattedMessage(MIN_NUMBER, MAX_NUMBER));
        }
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(ErrorCode.NUMBER_DUPLICATE.getMessage());
        }
    }
}
