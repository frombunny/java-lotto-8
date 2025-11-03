package lotto.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import lotto.model.Lotto;

public enum LottoRank {
    LOSE(0, (bonus) -> true, 0),
    FIFTH(3, (bonus) -> true, 5000),
    FOURTH(4, (bonus) -> true, 50000),
    THIRD(5, (bonus) -> !bonus, 1500000),
    SECOND(5, (bonus) -> bonus, 30000000),
    FIRST(6, (bonus) -> true, 2000000000);

    private final int winningNumbersCount;
    private final Predicate<Boolean> bonusCondition;
    private final long reward;

    LottoRank(int winningNumbersCount, Predicate<Boolean> bonusCondition, long reward) {
        this.winningNumbersCount = winningNumbersCount;
        this.bonusCondition = bonusCondition;
        this.reward = reward;
    }

    public long getReward() {
        return reward;
    }

    public String getFormattedResult() {
        String formattedReward = String.format("%,d원", reward);

        if (this == SECOND) {
            String bonusText = ", 보너스 볼 일치";
            return String.format("%d개 일치%s (%s)", winningNumbersCount, bonusText, formattedReward);
        }

        return String.format("%d개 일치 (%s)", winningNumbersCount, formattedReward);
    }

    public static LottoRank determineRank(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
        int matchCount = lotto.countMatchedNumbers(winningNumbers);
        boolean hasBonusNumber = lotto.hasBonusNumber(bonusNumber);

        return Arrays.stream(values())
                .filter(lottoRank -> lottoRank.isMatch(matchCount, hasBonusNumber))
                .findFirst()
                .orElse(LOSE);
    }

    private boolean isMatch(int matchCount, boolean hasBonusNumber) {
        return this.winningNumbersCount == matchCount && this.bonusCondition.test(hasBonusNumber);
    }
}
