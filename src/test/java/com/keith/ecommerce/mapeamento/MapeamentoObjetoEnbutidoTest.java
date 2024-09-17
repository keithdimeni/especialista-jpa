package com.keith.ecommerce.mapeamento;

import com.keith.ecommerce.EntityManagerTest;
import com.keith.ecommerce.model.EnderecoEntregaPedido;
import com.keith.ecommerce.model.Pedido;
import com.keith.ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEnbutidoTest extends EntityManagerTest {

    @Test
    public void analisarMapeamentoObjetoEmbutido() {

        EnderecoEntregaPedido enderecoEntrega = new EnderecoEntregaPedido();
        enderecoEntrega.setCep("01226010");
        enderecoEntrega.setLogradouro("Rua das Palmeiras");
        enderecoEntrega.setNumero("123");
        enderecoEntrega.setCompleto("Apto 1");
        enderecoEntrega.setBairro("Vila Buarque");
        enderecoEntrega.setCidade("São Paulo");
        enderecoEntrega.setEstado("SP");

        Pedido pedido = new Pedido();
//        pedido.setId(1);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setDataConclusao(LocalDateTime.now());
//        pedido.setNotaFiscal(876897988);
        pedido.setTotal(BigDecimal.valueOf(600));
        pedido.setStatus(StatusPedido.PAGO);
        pedido.setEnderecoEntrega(enderecoEntrega);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao);
        Assertions.assertEquals(876897988, pedidoVerificacao.getNotaFiscal());
        Assertions.assertNotNull(pedidoVerificacao.getEnderecoEntrega());


    }

}
