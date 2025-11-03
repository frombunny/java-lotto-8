package lotto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.model.enums.LottoRank;

public class Lottos {
    private static final int LOTTO_PRICE = 1000;
    private final int purchaseAmount;
    private final List<Lotto> lottos;

    private Lottos(int purchaseAmount, List<Lotto> lottos) {
        validatePurchaseAmount(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos generateLottos(int purchaseAmount) {
        int generateCount = purchaseAmount / LOTTO_PRICE;

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < generateCount; i++) {
            lottos.add(Lotto.generate());
        }

        return new Lottos(purchaseAmount, lottos);
    }

    public LottoResult getLottoResult(List<Integer> winningNumbers, int bonusNumber) {
        Map<LottoRank, Integer> lottoRanks = new HashMap<>();
        for (Lotto lotto : lottos) {
            LottoRank rank = LottoRank.determineRank(lotto, winningNumbers, bonusNumber);
            lottoRanks.put(rank, lottoRanks.getOrDefault(rank, 0) + 1);
        }

        double earningRate = getEarningRate(lottoRanks);

        return LottoResult.of(lottoRanks, earningRate);
    }

    private double getEarningRate(Map<LottoRank, Integer> lottoRanks) {
        long totalRewards = lottoRanks.entrySet().stream()
                .mapToLong(entry-> entry.getKey().getReward()* entry.getValue())
                .sum();

        return (double) totalRewards / purchaseAmount;
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력할 수 있습니다.");
        }
    }
}
