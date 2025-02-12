package br.ufrpe.ip.projeto.repositories;

import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.repositories.interfaces.IClienteRepository;

import java.util.ArrayList;

public class ClienteRepository implements IClienteRepository {
    private ArrayList<Cliente> clientes;
    private static ClienteRepository instance = null;

    private ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public static ClienteRepository getInstance() {
        if(ClienteRepository.instance == null) {
            ClienteRepository.instance = new ClienteRepository();
        }
        return ClienteRepository.instance;
    }

    @Override
    public ArrayList<Cliente> getAllClientes() {
        if(clientes.isEmpty()) {
            return null;
        }
        return clientes;
    }

    @Override
    public Cliente getClienteByCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if(cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

   @Override
   public void createCliente(String nomeCliente, String cpfCliente, String telefoneCliente, String emailCliente) {
        Cliente cliente = new Cliente(nomeCliente, cpfCliente, telefoneCliente, emailCliente);
        this.clientes.add(cliente);
   }

   @Override
    public void updateNome(Cliente cliente, String nome) {
        cliente.setNome(nome);
    }

    @Override
    public void updateTelefone(Cliente cliente, String telefone) {
        cliente.setTelefone(telefone);
    }

    @Override
    public void updateEmail(Cliente cliente, String email) {
        cliente.setEmail(email);
    }

   @Override
    public void deleteCliente(String cpf) {
        clientes.remove(getClienteByCpf(cpf));
   }
}