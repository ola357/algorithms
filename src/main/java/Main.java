import java.util.Arrays;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("######## BIRTHDAY CANDLES CAKE ############");
        BirthdayCakeCandles birthdayCakeCandles = new BirthdayCakeCandles();
        System.out.println(birthdayCakeCandles.birthdayCakeCandles(List.of(1,2,6,6,3,4,5,5,6)));
        System.out.println("---------------------------------------");
        System.out.println("---------------------------------------");
        System.out.println("########ICECREAM PARLOR############");
        IceCreamParlor icp = new IceCreamParlor();
        int m =4;
        int[] arr = new int[]{2,2,4,3};
        System.out.println(Arrays.toString(icp.iceCreamParlor(m,arr)));

        MinimumAverageWaitingTime.Order o0 = new MinimumAverageWaitingTime.Order(961148050, 385599125);
        MinimumAverageWaitingTime.Order o1 = new MinimumAverageWaitingTime.Order(951133776, 376367013);
        MinimumAverageWaitingTime.Order o2 = new MinimumAverageWaitingTime.Order(283280121, 782916802);
        MinimumAverageWaitingTime.Order o3 = new MinimumAverageWaitingTime.Order(317664929, 898415172);
        MinimumAverageWaitingTime.Order o4 = new MinimumAverageWaitingTime.Order(980913391, 847912645);
        //----------------------------------------------------------------------------------------------------------------

        NavigableMap<Long, List<MinimumAverageWaitingTime.Order>> map = new TreeMap<>();

        map.put(0L, List.of(o1));
        map.put(1L, List.of(o2));
        map.put(2L, List.of(o3));
        map.put(3L, List.of(o4));
        map.put(4L, List.of(o0));
        int [][] data = new int[][]{{961148050, 385599125},{951133776, 376367013},{283280121, 782916802}, {317664929, 898415172}, {980913391, 847912645}};
        System.out.println(MinimumAverageWaitingTime.minimumAverage(data));
        ;



    }
}
