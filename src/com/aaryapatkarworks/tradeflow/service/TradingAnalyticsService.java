package com.aaryapatkarworks.tradeflow.service;

import com.aaryapatkarworks.tradeflow.model.Order;
import com.aaryapatkarworks.tradeflow.model.OrderType;
import com.aaryapatkarworks.tradeflow.model.OrderStatus;
import com.aaryapatkarworks.tradeflow.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TradingAnalyticsService {

        // ---------------- Order Count ----------------

        public int getTotalOrders(User user) {

            return user.getOrders().size();
        }

    // ---------------- Count Orders by Status ----------------

    private int countOrdersByStatus(
            User user,
            OrderStatus status) {

        int count = 0;

        for (Order order : user.getOrders()) {

            if (order.getOrderStatus() == status) {

                count++;
            }
        }

        return count;
    }

        // ---------------- BUY Orders ----------------

        public int getBuyOrderCount(User user) {

            int count = 0;

            ArrayList<Order> orders =
                    user.getOrders();

            for (Order order : orders) {

                if (order.getOrderType()
                        == OrderType.BUY) {

                    count++;
                }
            }

            return count;
        }

        // ---------------- SELL Orders ----------------

        public int getSellOrderCount(User user) {

            int count = 0;

            ArrayList<Order> orders =
                    user.getOrders();

            for (Order order : orders) {

                if (order.getOrderType()
                        == OrderType.SELL) {

                    count++;
                }
            }

            return count;
        }



        // ---------------- Trading Volume ----------------

        public double getTradingVolume(User user) {

            double volume = 0;

            ArrayList<Order> orders =
                    user.getOrders();

            for (Order order : orders) {

                volume +=
                        order.getQuantity()
                                * order.getPrice();
            }

            return volume;
        }

        // ---------------- Display Analytics ----------------

        public void displayTradingAnalytics(User user) {

            System.out.println(
                    "\n========== TRADING ANALYTICS ==========\n"
            );

            System.out.println(
                    "Trader         : "
                            + user.getFullName()
            );

            System.out.println(
                    "Total Orders   : "
                            + getTotalOrders(user)
            );

            System.out.println(
                    "BUY Orders     : "
                            + getBuyOrderCount(user)
            );

            System.out.println(
                    "SELL Orders    : "
                            + getSellOrderCount(user)
            );

            System.out.printf(
                    "Trading Volume : ₹%.2f%n",
                    getTradingVolume(user)
            );

            System.out.println(
                    "Most Traded    : "
                            + getMostTradedStock(user)
            );

            System.out.println(
                    "Successful     : "
                            + getSuccessfulOrderCount(user)
            );

            System.out.printf(
                    "Success Rate   : %.2f%%%n",
                    getOrderSuccessRate(user)
            );

            System.out.println(
                    "Cancelled      : "
                            + getCancelledOrderCount(user)
            );

            System.out.println(
                    "Rejected       : "
                            + getRejectedOrderCount(user)
            );

            displayStockTradingActivity(user);

            System.out.println(
                    "\n========================================"
            );
        }

    // ---------------- Successful Orders ----------------

    public int getSuccessfulOrderCount(User user) {

        return countOrdersByStatus(
                user,
                OrderStatus.EXECUTED
        );
    }

    // ---------------- Cancelled Orders ----------------

    public int getCancelledOrderCount(User user) {

        return countOrdersByStatus(
                user,
                OrderStatus.CANCELLED
        );
    }

    // ---------------- Rejected Orders ----------------

    public int getRejectedOrderCount(User user) {

        return countOrdersByStatus(
                user,
                OrderStatus.REJECTED
        );
    }

    // ---------------- Order Success Rate ----------------

    public double getOrderSuccessRate(User user) {

        int totalOrders =
                getTotalOrders(user);

        if (totalOrders == 0) {

            return 0;
        }

        return ((double) getSuccessfulOrderCount(user)
                / totalOrders) * 100;
    }

    // ---------------- Most Traded Stock ----------------

    public String getMostTradedStock(User user) {

        ArrayList<Order> orders =
                user.getOrders();

        if (orders.isEmpty()) {

            return "N/A";
        }

        String mostTradedStock = null;

        int highestCount = 0;

        for (Order currentOrder : orders) {

            String currentSymbol =
                    currentOrder.getStock()
                            .getStockSymbol();

            int currentCount = 0;

            for (Order order : orders) {

                if (order.getStock()
                        .getStockSymbol()
                        .equalsIgnoreCase(currentSymbol)) {

                    currentCount++;
                }
            }

            if (currentCount > highestCount) {

                highestCount = currentCount;

                mostTradedStock = currentSymbol;
            }
        }

        return mostTradedStock;
    }

    // ---------------- Stock Trading Activity ----------------

    public void displayStockTradingActivity(User user) {

        ArrayList<Order> orders =
                user.getOrders();

        System.out.println(
                "\n========== STOCK TRADING ACTIVITY ==========\n"
        );

        if (orders.isEmpty()) {

            System.out.println(
                    "No trading activity available."
            );

            System.out.println(
                    "\n============================================"
            );

            return;
        }

        HashMap<String, Integer> stockActivity =
                new HashMap<>();

        for (Order order : orders) {

            String symbol =
                    order.getStock()
                            .getStockSymbol()
                            .toUpperCase();

            stockActivity.put(
                    symbol,
                    stockActivity.getOrDefault(symbol, 0) + 1
            );
        }

        for (Map.Entry<String, Integer> entry :
                stockActivity.entrySet()) {

            System.out.printf(
                    "%-12s : %d orders%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }

        System.out.println(
                "\n============================================"
        );
    }

}
