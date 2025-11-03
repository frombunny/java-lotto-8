package lotto.model;

import java.util.Arrays;
import java.util.List;

public class WinningNumbersParser {

    public List<Integer> parseWinningNumbers(String input) {
        String[] numbers = input.split(",");

        return Arrays.stream(numbers)
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
