package lotto;

import lotto.controller.LottoController;
import lotto.model.validator.InputHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        InputHandler inputHandler = new InputHandler();
        OutputView outputView = new OutputView();

        LottoController lottoController = new LottoController(inputView, inputHandler, outputView);
        lottoController.run();
    }
}
