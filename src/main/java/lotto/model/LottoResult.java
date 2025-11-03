package lotto.model;

import java.util.Map;
import lotto.model.enums.LottoRank;

public record LottoResult(
        Map<LottoRank, Integer> lottoRanks,
        double earningRate
) {
    public static LottoResult of(Map<LottoRank, Integer> lottoRanks, double earningRate) {
        return new LottoResult(lottoRanks, earningRate);
    }
}
