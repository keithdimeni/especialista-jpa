package com.keith.ecommerce.relacionamentos;

import com.keith.ecommerce.EntityManagerTest;
import com.keith.ecommerce.model.NotaFiscal;
import com.keith.ecommerce.model.PagamentoCartao;
import com.keith.ecommerce.model.Pedido;
import com.keith.ecommerce.model.StatusPagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class RelacionamentoOneToOneTest extends EntityManagerTest {

    @Test
    public void verificarRelaciomentoPedidoPagamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setNumero("123");
        pagamentoCartao.setStatus(StatusPagamento.PROCESSADO);
        pagamentoCartao.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao.getPagamento());
    }

    @Test
    public void verificarRelaciomentoPedidoNotaFiscal() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setXml("TESTE");
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());


        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao.getNotaFiscal());
    }


}
