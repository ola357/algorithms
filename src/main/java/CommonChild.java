import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class CommonChild {

    public static int commonChild(String s1, String s2) {
        // Write your code here
        // step 1: remove uncommon characters from each string
        var s3 = s1.split("");
        var s4 = s2.split("");
        var s5 = Arrays.asList(s3);
        var s6 = Arrays.asList(s4);
        for (int i = 0; i < s3.length; i++) {
            boolean index = Arrays.binarySearch(s3, s3[i]) < 0;
            if (index) {
               //Arrays.
            }
        }
        return -1;
    }


    public  static int commonChild(String s1, String s2, int r){
        if(s1.equals(s2)) {
            return s1.length();
        }
       var queue = stripUncommonLetters(s1, s2);
        return -1;
    }

    public static Queue<String> stripUncommonLetters(String s1, String s2) {
        var s3 = s1.split("");
        var s4 = s2.split("");
        var s5 = new StringBuilder(s1);
        var s6 = new StringBuilder(s2);

        for (int i = 0; i < s3.length; i++) {
            if (!s2.contains(s3[i])) {
               s5.replace(i, i+1,"5");
            }

            if (!s1.contains(s4[i])) {
                s6.replace(i, i+1,"5");
            }
        }

        System.out.println(s5.toString());
        System.out.println(s6.toString());
        System.out.println(s5.toString().replace("5", ""));
        Queue<String> queue = new PriorityQueue<>(Comparator.comparingInt(String::length));
        queue.add(s5.toString().replace("5", ""));
        queue.add(s6.toString().replace("5", ""));
        return queue;
    }

    public static void main(String[] args) {
        CommonChild.commonChild("hiaxy", "hiiha", 2);
    }

}
