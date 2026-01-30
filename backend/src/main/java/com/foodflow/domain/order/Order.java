package com.foodflow.domain.order;

import com.foodflow.domain.order.exception.BusinessErrorCode;
import com.foodflow.domain.order.exception.BusinessException;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private final UUID id;
    private final Instant createdAt;
    private OrderStatus status;

    private Order(UUID id, Instant createdAt, OrderStatus status) {
        this.id = Objects.requireNonNull(id, "id é obrigatório");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt é obrigatório");
        this.status = Objects.requireNonNull(status, "status é obrigatório");
    }

    public static Order create() {
        return new Order(UUID.randomUUID(), Instant.now(), OrderStatus.DRAFT);
    }

    public UUID getId() { return id; }
    public Instant getCreatedAt() { return createdAt; }
    public OrderStatus getStatus() { return status; }

    public void confirm() {
        requireStatus(OrderStatus.DRAFT, "Apenas pedidos em RASCUNHO (DRAFT) podem ser confirmados.");
        this.status = OrderStatus.CONFIRMED;
    }

    public void pay() {
        requireStatus(OrderStatus.CONFIRMED, "Apenas pedidos CONFIRMADOS podem ser pagos.");
        this.status = OrderStatus.PAID;
    }

    public void ship() {
        requireStatus(OrderStatus.CONFIRMED, "Apenas pedidos CONFIRMADOS podem ser enviados.");
        this.status = OrderStatus.SHIPPED;
    }

    public void deliver() {
        requireStatus(OrderStatus.SHIPPED, "Apenas pedidos ENVIADOS podem ser entregues.");
        this.status = OrderStatus.DELIVERED;
    }

    public void cancel() {
        if (status == OrderStatus.DRAFT || status == OrderStatus.CONFIRMED) {
            this.status = OrderStatus.CANCELED;
            return;
        }

        throw new BusinessException(
                BusinessErrorCode.ORDER_CANNOT_CANCEL,
                "Não é possível cancelar um pedido no status: " + status,
                Map.of(
                        "currentStatus", status.name(),
                        "orderId", id.toString()
                )
        );
    }

    private void requireStatus(OrderStatus expected, String message) {
        if (this.status != expected) {
            throw new BusinessException(
                    BusinessErrorCode.ORDER_INVALID_STATUS_TRANSITION,
                    message + " Status atual: " + this.status,
                    Map.of(
                            "expectedStatus", expected.name(),
                            "currentStatus", this.status.name(),
                            "orderId", id.toString()
                    )
            );
        }
    }
}
