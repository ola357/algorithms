import java.util.Arrays;
import java.util.List;

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

    }
}
