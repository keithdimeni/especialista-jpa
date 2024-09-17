package com.keith.ecommerce.relacionamentos;

import com.keith.ecommerce.EntityManagerTest;
import com.keith.ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelaciomentoManyToOneTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamentoPedidoCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setTotal(BigDecimal.valueOf(10));
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao.getCliente());
    }

    @Test
    public void verificarRelacionamentoItemPedidoPedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setTotal(BigDecimal.valueOf(10));
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(3);

        entityManager.getTransaction().begin();
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());

        Assertions.assertNotNull(itemPedidoVerificacao.getPedido());
        Assertions.assertNotNull(itemPedidoVerificacao.getProduto());
    }

}
