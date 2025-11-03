package lotto.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import lotto.model.Lotto;

public enum LottoResult {
    FIRST(6, (bonus) -> true),
    SECOND(5, (bonus) -> bonus),
    THIRD(5, (bonus) -> !bonus),
    FOURTH(4, (bonus) -> true),
    FIFTH(3, (bonus) -> true),
    LOSE(0, (bonus) -> true);

    private final int winningNumbersCount;
    private final Predicate<Boolean> bonusCondition;

    LottoResult(int winningNumbersCount, Predicate<Boolean> bonusCondition) {
        this.winningNumbersCount = winningNumbersCount;
        this.bonusCondition = bonusCondition;
    }

    public static LottoResult of(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
        int matchCount = lotto.countMatchedNumbers(winningNumbers);
        boolean hasBonusNumber = lotto.hasBonusNumber(bonusNumber);

        return Arrays.stream(values())
                .filter(lottoResult -> lottoResult.isMatch(matchCount, hasBonusNumber))
                .findFirst()
                .orElse(LOSE);
    }

    private boolean isMatch(int matchCount, boolean hasBonusNumber) {
        return this.winningNumbersCount == matchCount && this.bonusCondition.test(hasBonusNumber);
    }
}
