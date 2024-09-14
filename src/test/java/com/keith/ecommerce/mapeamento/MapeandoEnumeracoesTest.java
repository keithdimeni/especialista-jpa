package com.keith.ecommerce.mapeamento;

import com.keith.ecommerce.EntityManagerTest;
import com.keith.ecommerce.model.Cliente;
import com.keith.ecommerce.model.SexoCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapeandoEnumeracoesTest extends EntityManagerTest {

    @Test
    public void testarEnum() {
        Cliente cliente = new Cliente();
//        cliente.setId(4);
        cliente.setNome("Jose Mineiro");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificacao);
        Assertions.assertEquals("Jose Mineiro", clienteVerificacao.getNome());
    }

}
