import java.util.List;

public class BirthdayCakeCandles {
    public int birthdayCakeCandles(List<Integer> candles){

        Integer max = candles.stream()
                .reduce(Integer.MIN_VALUE, Integer::max);

       return (int) candles.stream()
               .filter(candle -> candle.equals(max))
               .count();
    }
}