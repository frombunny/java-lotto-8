package lotto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.model.enums.LottoRank;
import lotto.util.ErrorCode;

public class Lottos {
    private static final int LOTTO_PRICE = 1000;
    private final int purchaseMoney;
    private final List<Lotto> lottos;

    private Lottos(int purchaseMoney, List<Lotto> lottos) {
        validatePurchaseMoney(purchaseMoney);
        this.purchaseMoney = purchaseMoney;
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos generateLottos(int purchaseMoney) {
        int purchaseCount = purchaseMoney / LOTTO_PRICE;

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseCount; i++) {
            lottos.add(Lotto.generate());
        }

        return new Lottos(purchaseMoney, lottos);
    }

    public List<String> getFormattedNumbers() {
        return lottos.stream()
                .map(lotto -> lotto.getNumbers().toString())
                .toList();
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
                .mapToLong(entry -> entry.getKey().getReward() * entry.getValue())
                .sum();

        return (double) totalRewards / purchaseMoney;
    }

    private static void validatePurchaseMoney(int purchaseMoney) {
        if (purchaseMoney % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    ErrorCode.INVALID_MONEY.getFormattedMessage(String.format("%,d", LOTTO_PRICE)));
        }
    }
}
