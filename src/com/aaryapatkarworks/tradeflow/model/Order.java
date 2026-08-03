package com.aaryapatkarworks.tradeflow.model;

import java.time.LocalDateTime;

public class Order {

    private int orderId;

    private User user;

    private Stock stock;

    private int quantity;

    private double price;

    private OrderType orderType;

    private OrderStatus orderStatus;

    private LocalDateTime orderTime;

    public Order(int orderId,
                 User user,
                 Stock stock,
                 int quantity,
                 double price,
                 OrderType orderType,
                 OrderStatus orderStatus) {

        this.orderId = orderId;
        this.user = user;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
        this.orderStatus = orderStatus;

        orderTime = LocalDateTime.now();
    }

    // ---------------- Getters ----------------

    public int getOrderId() {

        return orderId;
    }

    public User getUser() {

        return user;
    }

    public Stock getStock() {

        return stock;
    }

    public int getQuantity() {

        return quantity;
    }

    public double getPrice() {

        return price;
    }

    public OrderType getOrderType() {

        return orderType;
    }

    public OrderStatus getOrderStatus() {

        return orderStatus;
    }

    public LocalDateTime getOrderTime() {

        return orderTime;
    }

    // ---------------- Setter ----------------

    public void setOrderStatus(
            OrderStatus orderStatus) {

        this.orderStatus = orderStatus;
    }

    // ---------------- Display ----------------

    public void displayOrder() {

        System.out.println(
                "\n========== ORDER =========="
        );

        System.out.println(
                "Order ID      : "
                        + orderId
        );

        System.out.println(
                "User          : "
                        + user.getFullName()
        );

        System.out.println(
                "Stock         : "
                        + stock.getCompanyName()
        );

        System.out.println(
                "Symbol        : "
                        + stock.getStockSymbol()
        );

        System.out.println(
                "Order Type    : "
                        + orderType
        );

        System.out.println(
                "Quantity      : "
                        + quantity
        );

        System.out.printf(
                "Price         : ₹%.2f%n",
                price
        );

        System.out.println(
                "Status        : "
                        + orderStatus
        );

        System.out.println(
                "Order Time    : "
                        + orderTime
        );

        System.out.println(
                "============================"
        );
    }
}