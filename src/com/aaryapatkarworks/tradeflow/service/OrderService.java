package com.aaryapatkarworks.tradeflow.service;

import com.aaryapatkarworks.tradeflow.exception.InvalidQuantityException;
import com.aaryapatkarworks.tradeflow.exception.StockNotFoundException;
import com.aaryapatkarworks.tradeflow.model.*;

import java.util.ArrayList;

public class OrderService {

    private ArrayList<Order> orders;

    private TradingService tradingService;

    private int nextOrderId;

    public OrderService(TradingService tradingService) {

        this.tradingService = tradingService;

        orders = new ArrayList<>();

        nextOrderId = 1001;
    }

    // ---------------- Getter ----------------

    public ArrayList<Order> getOrders() {

        return orders;
    }

    // ---------------- Create Order ----------------

    public Order createOrder(User user,
                             Stock stock,
                             int quantity,
                             OrderType type) {

        Order order = new Order(

                nextOrderId++,

                user,

                stock,

                quantity,

                stock.getCurrentPrice(),

                type,

                OrderStatus.PENDING
        );

        orders.add(order);

        user.addOrder(order);

        return order;
    }

    // ---------------- Display Orders ----------------

    public void displayOrders(User user) {

        System.out.println(
                "\n========== ORDER HISTORY ==========\n"
        );

        if (user.getOrders().isEmpty()) {

            System.out.println("No orders available.");

            return;
        }

        for (Order order : user.getOrders()) {

            order.displayOrder();
        }
    }

    // ---------------- Execute ----------------

    public void executeOrder(Order order)
            throws StockNotFoundException,
            InvalidQuantityException {

        if (order.getOrderType() == OrderType.BUY) {

            tradingService.buyStock(

                    order.getUser(),

                    order.getStock().getStockSymbol(),

                    order.getQuantity()
            );
        }

        else {

            tradingService.sellStock(

                    order.getUser(),

                    order.getStock().getStockSymbol(),

                    order.getQuantity()
            );
        }

        order.setOrderStatus(OrderStatus.EXECUTED);
    }

    public void placeBuyOrder(User user,
                              Stock stock,
                              int quantity)
            throws StockNotFoundException,
            InvalidQuantityException {

        Order order = createOrder(

                user,

                stock,

                quantity,

                OrderType.BUY
        );

        executeOrder(order);
    }

    public void placeSellOrder(User user,
                               Stock stock,
                               int quantity)
            throws StockNotFoundException,
            InvalidQuantityException {

        Order order = createOrder(

                user,

                stock,

                quantity,

                OrderType.SELL
        );

        executeOrder(order);
    }


}