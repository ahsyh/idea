package com.yihui.experimental;

import lombok.Data;

import java.util.Optional;

public class AppMain {

    @Data
    private static class Order {
        private Customer customer;
    }

    @Data
    private static class Customer {
        private String customerId;
    }

    private static String myMethod(String s) {
        System.out.println("myMethod is running");
        return s + " is presented.";
    }

    private static void testOption(final Order order) {
        String r = Optional.ofNullable(order)
                .map(Order::getCustomer)
                .map(Customer::getCustomerId)
                .map(AppMain::myMethod)
                .orElse("");

        System.out.println("get result is: " + r);
    }

    public static void main(String[] args) {
        final Order o = new Order();
        final Customer c = new Customer();
//        o.setCustomer(c);
//        c.setCustomerId("ABC");
        testOption(o);
    }
}
