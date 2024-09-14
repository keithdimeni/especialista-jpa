package com.keith.ecommerce;

import com.keith.ecommerce.model.Cliente;
import com.keith.ecommerce.model.SexoCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DesafioClienteTest extends EntityManagerTest{

    @Test
    public void inserirRegistro() {
        Cliente cliente = new Cliente();
//        cliente.setId(3);
        cliente.setNome("Felicidade Biengo Dimeni");
         cliente.setSexo(SexoCliente.FEMININO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 3);
        Assertions.assertEquals("Felicidade Biengo Dimeni", clienteVerificacao.getNome());
    }

    @Test
    public void consultarRegistroPorIdentificacao() {
        Cliente cliente = new Cliente();

        entityManager.getTransaction().begin();
        entityManager.find(Cliente.class, 2);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 2);
        Assertions.assertEquals("Augusto Dimeni", clienteVerificacao.getNome());
    }

    @Test
    public void atualizarRegisto() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Mauricio Dimeni");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin   ();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertEquals("Mauricio Dimeni", clienteVerificacao.getNome());
    }

    @Test
    public void excluirRegistro() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 1);
        Assertions.assertNull(clienteVerificacao);
    }

}
