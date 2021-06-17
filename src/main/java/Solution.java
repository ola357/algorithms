import static java.util.Comparator.comparing;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

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
            out.println(evaluate(ordersByTime));
        }
    }
    static int minimumAverage(int[][] customers) {
        NavigableMap<Long, List<Order>> ordersByTime = new TreeMap<>();

        for (int[] orders : customers) {
            ordersByTime.computeIfAbsent((long) orders[0], key -> new ArrayList<>())
                    .add(new Order(orders[0], orders[1]));
        }
        return (int) evaluate(ordersByTime);
    }

    private static long evaluate(NavigableMap<Long, List<Order>> ordersByArrivalTime) {

        PriorityQueue<Order> ordersByCookTimePriority = new PriorityQueue<>(comparing(order -> order.cookTime));

        int ordersCooked = 0;
        long elapsedTime = 0;
        long totalWaitingTime = 0;

        while (!ordersByArrivalTime.isEmpty() || !ordersByCookTimePriority.isEmpty()) {
            if (ordersByCookTimePriority.isEmpty()) {
                // start cooking OR jump to next arrival time
                //[0,1,2]
                //[3,9,6]
                Entry<Long, List<Order>> firstEntry = ordersByArrivalTime.pollFirstEntry();
                ordersByCookTimePriority.addAll(firstEntry.getValue());
                elapsedTime = firstEntry.getKey();
            } else {
                // cook something OR jump to now+cookTime
                var nextOrderToBeCooked = ordersByCookTimePriority.poll();
                ordersCooked++;

                // record wait time for the order now being cooked
                totalWaitingTime += (elapsedTime - nextOrderToBeCooked.arrivalTime) + nextOrderToBeCooked.cookTime;

                // move time to when we next make a choice, when the oven is free again
                elapsedTime += nextOrderToBeCooked.cookTime;

                // store orders that will arrive before current pizza order is cooked
                NavigableMap<Long, List<Order>> arrivals = ordersByArrivalTime.headMap(elapsedTime, true);

                for (List<Order> os : arrivals.values()) {
                    ordersByCookTimePriority.addAll(os);
                }
                arrivals.clear();
            }
        }
        return totalWaitingTime / ordersCooked;
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
