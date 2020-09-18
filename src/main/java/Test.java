import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class Test {
    static int minimumAverage(int[][] customers) {

        NavigableMap<Long, List<Order>> ordersByTime = new TreeMap<>();

        for (int[] customer : customers) {
            ordersByTime.computeIfAbsent((long) customer[0], key -> new ArrayList<>())
                    .add(new Order(customer[0], customer[1]));
        }

        Queue<Order> outstanding = new PriorityQueue<>();
        long curTime = ordersByTime.firstEntry().getValue().get(0).arrivalTime;
        int offset = 0;
        long totalWaitTime = 0;


        while (true) {

            while (offset < ordersByTime.size()) {
                Order order = new ArrayList<>(ordersByTime.values())
                        .get(offset).get(0); //orders[offset];
                if (order.arrivalTime <= curTime) {
                    outstanding.add(order);
                    offset++;
                } else {
                    break;
                }
            }

            if (outstanding.size() > 0) {
                Order order = outstanding.poll();
                if (curTime < order.arrivalTime) {
                    curTime = order.arrivalTime;
                } else {
                    totalWaitTime += curTime - order.arrivalTime;
                }

                curTime += order.cookTime;
                totalWaitTime += order.cookTime;
            } else {
                break;
            }
        }

        return (int) (totalWaitTime / ordersByTime.size());
    }

    protected static final class Order implements Comparable<Order> {
        final long arrivalTime;
        final long cookTime;

        Order(long arrivalTime, long cookTime) {
            this.arrivalTime = arrivalTime;
            this.cookTime = cookTime;
        }

        @Override
        public int compareTo(Order o) {
            if (cookTime < o.cookTime) {
                return -1;
            } else if (cookTime > o.cookTime) {
                return 1;
            } else {
                return Long.compare(arrivalTime, o.arrivalTime);
            }
        }
    }

}
