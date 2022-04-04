package com.yihui.experimental.optional;

import com.yihui.experimental.util.CommonUtil;
import lombok.Data;

import java.util.Optional;

import static com.yihui.experimental.AppMain.logger;

/**
 *
 */
public final class Case001 {
    private Case001() {
    }

    @Data
    private static class Order {
        private Customer customer;
    }

    @Data
    private static class Customer {
        private String customerId;
    }

    private static String myMethod(final String s) {
        logger.warn("myMethod is running");
        return s + " is presented.";
    }

    private static void testOption(final Order order) {
        String r = Optional.ofNullable(order)
                .map(Order::getCustomer)
                .map(Customer::getCustomerId)
                .map(Case001::myMethod)
                .orElse("");

        logger.warn("get result is: " + r);
    }

    private static void case01() {
        CommonUtil.runCase(() -> {
            final Order o = new Order();
            final Customer c = new Customer();
            testOption(o);
        }, Case001.class.getName() + ":case01");
    }

    private static void case02() {
        CommonUtil.runCase(() -> {
            final Order o = new Order();
            final Customer c = new Customer();
            o.setCustomer(c);
            c.setCustomerId("ABC");
            testOption(o);
        }, Case001.class.getName() + ":case02");
    }

    /**
     *
     */
    public static void main() {
        case01();
        case02();
    }
}
