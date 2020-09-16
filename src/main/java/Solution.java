import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

import static java.util.Comparator.comparing;

public class Solution {
    public static void main(String[] args) throws IOException {
        doMain(System.in, System.out);
    }

    public static void doMain(InputStream is, PrintStream out) {
        try (Scanner in = new Scanner(is)) {
            int n = in.nextInt();

            NavigableMap<Long, List<Order>> ordersByTime = new TreeMap<>();
            Set<Order> allOrders = new HashSet<>();
            for (int i = 0; i < n; i++) {
                long arrivalTime = in.nextInt();
                long cookTime = in.nextInt();
                Order order = new Order(arrivalTime, cookTime);
                ordersByTime.computeIfAbsent(arrivalTime, a -> new ArrayList<>()).add(order);
                allOrders.add(order);
            }
            out.println(computeMinAvg(ordersByTime));
        }
    }

    private static long computeMinAvg(NavigableMap<Long, List<Order>> ordersByTime) {
        PriorityQueue<Order> outstanding = new PriorityQueue<>(comparing(o -> o.cookTime));
        int ordersCooked = 0;
        long t = 0;
        long totalWaitTime = 0;

        while (!ordersByTime.isEmpty() || !outstanding.isEmpty()) {
            if (outstanding.isEmpty()) { // jump to next arrival time
                Map.Entry<Long, List<Order>> firstEntry = ordersByTime.pollFirstEntry();
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
                arrivals.values().stream().forEach(os -> outstanding.addAll(os));
                arrivals.clear();
            }
        }
        return totalWaitTime / ordersCooked;
    }

    private static final class Order {
        final long arrivalTime;
        final long cookTime;

        Order(long arrivalTime, long cookTime) {
            this.arrivalTime = arrivalTime;
            this.cookTime = cookTime;
        }
    }
}
