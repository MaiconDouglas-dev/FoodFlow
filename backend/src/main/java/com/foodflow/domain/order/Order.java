package com.foodflow.domain.order;

import com.foodflow.domain.order.exception.BusinessException;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private final UUID id;
    private final Instant createdAt;
    private OrderStatus status;

    private Order(UUID id, Instant createdAt, OrderStatus status) {
        this.id = Objects.requireNonNull(id, "id is required");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt is required");
        this.status = Objects.requireNonNull(status, "status is required");
    }

    /** Factory: creates a new order in DRAFT */
    public static Order create() {
        return new Order(UUID.randomUUID(), Instant.now(), OrderStatus.DRAFT);
    }

    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    // -----------------------
    // State transitions
    // -----------------------

    /** DRAFT -> CONFIRMED */
    public void confirm() {
        ensureStatus(OrderStatus.DRAFT, "Only DRAFT orders can be confirmed");
        this.status = OrderStatus.CONFIRMED;
    }

    /**
     * (Opcional para o futuro)
     * CONFIRMED -> PAID
     * (Os testes ainda não usam isso)
     */
    public void pay() {
        ensureStatus(OrderStatus.CONFIRMED, "Only CONFIRMED orders can be paid");
        this.status = OrderStatus.PAID;
    }

    /** CONFIRMED -> SHIPPED  (contrato do seu teste) */
    public void ship() {
        ensureStatus(OrderStatus.CONFIRMED, "Only CONFIRMED orders can be shipped");
        this.status = OrderStatus.SHIPPED;
    }

    /** SHIPPED -> DELIVERED */
    public void deliver() {
        ensureStatus(OrderStatus.SHIPPED, "Only SHIPPED orders can be delivered");
        this.status = OrderStatus.DELIVERED;
    }

    /** (DRAFT ou CONFIRMED) -> CANCELED */
    public void cancel() {
        if (status == OrderStatus.DRAFT || status == OrderStatus.CONFIRMED) {
            this.status = OrderStatus.CANCELED;
            return;
        }
        throw new BusinessException("Cannot cancel order in status: " + status);
    }

    // -----------------------
    // Guards (business rules)
    // -----------------------

    private void ensureStatus(OrderStatus expected, String message) {
        if (this.status != expected) {
            // O teste usa contains(), então pode ter detalhe extra, mas precisa conter o trecho base.
            throw new BusinessException(message + " (current: " + this.status + ")");
        }
    }
}
