package com.keith.ecommerce.relacionamentos;

import com.keith.ecommerce.EntityManagerTest;
import com.keith.ecommerce.model.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemovendoEntidadesReferenciadasTest extends EntityManagerTest {

    @Test
    public void removerEntidadeRelacionada() {

        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assertions.assertFalse(pedido.getItemPedidos().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItemPedidos().forEach(items -> entityManager.remove(items));
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
        Assertions.assertNull(pedidoVerificacao);
    }
}
