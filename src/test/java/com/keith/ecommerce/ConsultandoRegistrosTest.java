package com.keith.ecommerce;

import com.keith.ecommerce.model.Produto;
import org.junit.jupiter.api.*;

public class ConsultandoRegistrosTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador() {
        Produto produto = entityManager.find(Produto.class, 1);
//        Produto produto = entityManager.getReference(Produto.class, 1);

        Assertions.assertNotNull(produto);
        Assertions.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void atualizarReferencia() {
        Produto produto = entityManager.find(Produto.class, 1);

        produto.setNome("Tablet");
        Assertions.assertEquals("Tablet", produto.getNome());

        entityManager.refresh(produto);
        Assertions.assertEquals("Kindle", produto.getNome());
    }

}
