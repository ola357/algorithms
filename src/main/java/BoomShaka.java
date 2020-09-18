import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BoomShaka {
    private static class Order implements Comparable<Order>{

        long t;

        long l;

        public Order(long t, long l) {
            this.t = t;
            this.l = l;
        }

        @Override
        public int compareTo(Order o) {

            if(l < o.l) {
                return -1;
            } else if(l > o.l) {
                return 1;
            } else {
                return Long.compare(t, o.t);
            }
        }
    }
    static int minimumAverage(int[][] customers) {
        Order[] orders = new Order[customers.length];
       for (int[] order: customers){
          Arrays.fill(orders, new Order(order[0], order[1]));

       }
        return (int) getMinAvgTime(orders);
    }

    private static long getMinAvgTime(Order[] orders) {

        Arrays.sort(orders, Comparator.comparingLong(o -> o.t));

        long curTime = orders[0].t;
        int offset = 0;
        long totalWaitTime = 0;
        PriorityQueue<Order> heap = new PriorityQueue<>();

        while (true) {

            while(offset < orders.length) {
                Order order = orders[offset];
                if(order.t <= curTime) {
                    heap.add(order);
                    offset++;
                } else {
                    break;
                }
            }

            if(heap.size() > 0) {
                Order order = heap.poll();
                if(curTime < order.t) {
                    curTime = order.t;
                } else {
                    totalWaitTime += curTime - order.t;
                }

                curTime += order.l;
                totalWaitTime += order.l;
            } else {
                break;
            }
        }

        return totalWaitTime / orders.length;
    }
}
