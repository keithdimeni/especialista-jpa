package com.keith.ecommerce.relacionamentos;

import com.keith.ecommerce.EntityManagerTest;
import com.keith.ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AutoRelaciomentoTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamentoPedidoCliente() {
        Categoria categoriaPai = new Categoria();
        categoriaPai.setNome("Eletronicos");

        Categoria categoria = new Categoria();
        categoria.setNome("Computador");
        categoria.setCategoriaPai(categoriaPai);


        entityManager.getTransaction().begin();
        entityManager.persist(categoriaPai);
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assertions.assertNotNull(categoriaVerificacao.getCategoriaPai());

        Categoria categoriaPaiVerificacao = entityManager.find(Categoria.class, categoriaPai.getId());
        Assertions.assertFalse(categoriaPaiVerificacao.getCategorias().isEmpty());

    }

}
