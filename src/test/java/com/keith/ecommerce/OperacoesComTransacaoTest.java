package com.keith.ecommerce;

import com.keith.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void impedirOperacaoComBancoDeDados() {
        Produto produto = entityManager.find(Produto.class, 1);
        entityManager.detach(produto);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Oasis");
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertEquals("Kindle", produtoVerificacao.getNome());
    }

    @Test
    public void inserirObjetoComMerge() {
        Produto produto = new Produto();
        produto.setId(4);
        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("A melhor qualidade de som");
        produto.setPreco(new BigDecimal(1000));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertEquals(4, produtoVerificacao.getId());
    }

    @Test
    public void atualizarObjetoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Oasis");
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertEquals("Kindle Oasis", produtoVerificacao.getNome());
    }

    @Test
    public void atualizarObjeto() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheca o novo Kindle, mais rapido e mais suave");
        produto.setPreco(BigDecimal.valueOf(899));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assertions.assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());
    }

    @Test
    public void removerObjeto() {
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, 3);
        Assertions.assertNull(produtoVerificacao);
    }

    @Test
    public void inserirOPrimeiroObjeto() {
        Produto produto = new Produto();
        produto.setId(2);
        produto.setNome("Camera Canon");
        produto.setDescricao("A melhor definicao para suas fotos");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificacao);
        Assertions.assertEquals(2, produtoVerificacao.getId());
    }

    @Test
    public void abrirEFechartransacao() {
        entityManager.getTransaction().begin();

//         entityManager.persist();
//        entityManager.merge();
//        entityManager.remove();

        entityManager.getTransaction().commit();
    }

}
