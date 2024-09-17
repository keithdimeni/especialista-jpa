package com.keith.ecommerce.relacionamentos;

import com.keith.ecommerce.EntityManagerTest;
import com.keith.ecommerce.model.Categoria;
import com.keith.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class RelacionamentoManyToManyTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Produto produto = entityManager.find(Produto.class, 1);
        Categoria categoria = entityManager.find(Categoria.class, 1);

        entityManager.getTransaction().begin();
        produto.setCategorias(Arrays.asList(categoria));
        entityManager.getTransaction().commit();
        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assertions.assertFalse(categoriaVerificacao.getProdutos().isEmpty());
    }

}
