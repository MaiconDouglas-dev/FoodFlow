package com.foodflow.domain.order;

import com.foodflow.domain.order.exception.BusinessErrorCode;
import com.foodflow.domain.order.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void novoPedido_iniciaEmDraft() {
        Order order = Order.create();
        assertEquals(OrderStatus.DRAFT, order.getStatus());
    }

    @Test
    void podeConfirmar_aPartirDeDraft() {
        Order order = Order.create();
        order.confirm();
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void podeEnviar_apenasAposConfirmado() {
        Order order = Order.create();
        order.confirm();
        order.ship();
        assertEquals(OrderStatus.SHIPPED, order.getStatus());
    }

    @Test
    void podeEntregar_apenasAposEnviado() {
        Order order = Order.create();
        order.confirm();
        order.ship();
        order.deliver();
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    void naoPodeEnviar_quandoNaoConfirmado() {
        Order order = Order.create();

        BusinessException ex = assertThrows(BusinessException.class, order::ship);

        assertEquals(BusinessErrorCode.ORDER_INVALID_STATUS_TRANSITION, ex.getCode());
        assertTrue(ex.getMessage().contains("Status atual: DRAFT"));
    }

    @Test
    void cancelar_emDraft_ok() {
        Order order = Order.create();
        order.cancel();
        assertEquals(OrderStatus.CANCELED, order.getStatus());
    }

    @Test
    void cancelar_aposEnviado_deveFalhar() {
        Order order = Order.create();
        order.confirm();
        order.ship();

        BusinessException ex = assertThrows(BusinessException.class, order::cancel);

        assertEquals(BusinessErrorCode.ORDER_CANNOT_CANCEL, ex.getCode());
        assertTrue(ex.getMessage().contains("status: SHIPPED"));
    }
}
