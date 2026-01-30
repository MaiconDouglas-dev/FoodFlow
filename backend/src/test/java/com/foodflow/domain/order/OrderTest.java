package com.foodflow.domain.order;

import com.foodflow.domain.order.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void newOrder_startsAsDraft() {
        Order order = Order.create();
        assertEquals(OrderStatus.DRAFT, order.getStatus());
    }

    @Test
    void canConfirm_fromDraft() {
        Order order = Order.create();
        order.confirm();
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void canShip_onlyAfterConfirmed() {
        Order order = Order.create();
        order.confirm();
        order.ship();
        assertEquals(OrderStatus.SHIPPED, order.getStatus());
    }

    @Test
    void canDeliver_onlyAfterShipped() {
        Order order = Order.create();
        order.confirm();
        order.ship();
        order.deliver();
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    void cannotShip_whenNotConfirmed() {
        Order order = Order.create();
        BusinessException ex = assertThrows(BusinessException.class, order::ship);
        assertTrue(ex.getMessage().contains("Only CONFIRMED orders can be shipped"));
    }

    @Test
    void cancel_fromDraft_isOk() {
        Order order = Order.create();
        order.cancel();
        assertEquals(OrderStatus.CANCELED, order.getStatus());
    }

    @Test
    void cancel_afterShipped_throws() {
        Order order = Order.create();
        order.confirm();
        order.ship();

        BusinessException ex = assertThrows(BusinessException.class, order::cancel);
        assertTrue(ex.getMessage().contains("Cannot cancel"));
    }
}
