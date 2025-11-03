package lotto.controller;

import java.util.List;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.validator.InputHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final InputHandler inputHandler;
    private final OutputView outputView;

    public LottoController(InputView inputView, InputHandler inputHandler, OutputView outputView) {
        this.inputView = inputView;
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public void run() {
        int purchaseMoney = getPurchaseMoney();

        Lottos lottos = Lottos.generateLottos(purchaseMoney);
        outputView.printPurchaseResult(lottos.getPurchaseCount(), lottos);

        List<Integer> winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber();

        LottoResult lottoResult = lottos.getLottoResult(winningNumbers, bonusNumber);

        outputView.printLottoResult(lottoResult);
    }

    private int getPurchaseMoney() {
        while (true) {
            try {
                String input = inputView.getPurchaseMoney();
                return inputHandler.parsePurchaseMoney(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Integer> getWinningNumbers() {
        while (true) {
            try {
                String input = inputView.getWinningNumbers();
                return inputHandler.parseWinningNumbers(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getBonusNumber(){
        while (true) {
            try {
                String input = inputView.getBonusNumber();
                return inputHandler.parseBonusNumber(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
