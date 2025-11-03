package lotto.view;

import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.enums.LottoRank;

public class OutputView {
    public void printPurchaseResult(int count, Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.\n", count);
        lottos.getFormattedNumbers().forEach(System.out::println);
    }

    public void printLottoResult(LottoResult lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("---");

        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.LOSE) {
                continue;
            }

            int count = lottoResult.lottoRanks().getOrDefault(rank, 0);
            System.out.printf("%s - %d개%n", rank.getFormattedResult(), count);
        }

        System.out.printf("총 수익률은 %.1f%%입니다.%n", lottoResult.earningRate() * 100);
    }
}
