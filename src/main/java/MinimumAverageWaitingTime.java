import java.util.*;
import java.util.Map.Entry;

import static java.util.Comparator.comparing;

public class MinimumAverageWaitingTime {

    static int minimumAverage(int[][] customers){

        NavigableMap<Long, List<Order>> ordersByTime  = new TreeMap<>();

        for (int[] customer : customers) {
            ordersByTime.computeIfAbsent((long) customer[0], key -> new ArrayList<>())
                    .add(new Order(customer[0], customer[1]));
        }

        Queue<Order> outstanding = new PriorityQueue<>();
        int ordersCooked = 0;
        long t = 0;
        long totalWaitTime = 0;

        while (!ordersByTime.isEmpty() || !outstanding.isEmpty()) {
            if (outstanding.isEmpty()) { // jump to next arrival time
                Entry<Long, List<Order>> firstEntry = ordersByTime.pollFirstEntry();
                outstanding.addAll(firstEntry.getValue());
                t = firstEntry.getKey();
            } else { // cook something, jump to now+cookTime
                Order nextToCook = outstanding.poll();
                ordersCooked++;

                // record wait time for the order now being cooked
                totalWaitTime += (t - nextToCook.arrivalTime) + nextToCook.cookTime;

                // move time to when we next make a choice, when the oven is free again
                t += nextToCook.cookTime;

                // record orders that will arrive before this pizza is cooked
                NavigableMap<Long, List<Order>> arrivals = ordersByTime.headMap(t, true);
                arrivals.values().forEach(os -> outstanding.addAll(os));
                arrivals.clear();
            }
        }
        return (int) (totalWaitTime / ordersCooked);
    }

    protected static final class Order implements Comparable<Order>{
        final long arrivalTime;
        final long cookTime;

        Order(long arrivalTime, long cookTime) {
            this.arrivalTime = arrivalTime;
            this.cookTime = cookTime;
        }

        @Override
        public int compareTo(Order o) {
            if(cookTime < o.cookTime) {
                return -1;
            } else if(cookTime > o.cookTime) {
                return 1;
            } else {
                return Long.compare(arrivalTime, o.arrivalTime);
            }
            //return Long.compare(cookTime, o.cookTime);
            //return Long.compare(this.cookTime, o.cookTime);
        }
    }

}
