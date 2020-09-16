import java.util.ArrayList;
import java.util.List;


public class IceCreamParlor {

    public int[] iceCreamParlor(int m, int[] arr){
        List<Integer> al = new ArrayList<>();
        for (int value : arr) {
            al.add(value);
        }
       // Arrays.stream(arr).boxed().
        for (Integer i: al) {
            int x = al.indexOf(i);
            int y = al.size();
            List<Integer> nl = al.subList(x+1, y);
          if(  nl.contains(m-i))
              return new int[]{x+1, nl.indexOf(m-i)+x+2};
        }

        return  null;
    }
    
}
