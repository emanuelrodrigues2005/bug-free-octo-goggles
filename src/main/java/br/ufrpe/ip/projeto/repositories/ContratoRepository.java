package br.ufrpe.ip.projeto.repositories;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.interfaces.IContratoRepository;
import br.ufrpe.ip.projeto.models.Cliente;

public class ContratoRepository implements IContratoRepository{
    private static ContratoRepository instancia;
    private ArrayList<Contrato> contratos;
    

    public ContratoRepository() {
        this.contratos = new ArrayList<>();
    }
    
    public static ContratoRepository getInstancia() {
        if (instancia == null) {
            instancia = new ContratoRepository();
        }
        return instancia;
    }
    
    @Override
    public List<Contrato> getAllContratos() {
        return contratos;
    } // exceptions: semContratosRegistrados
    @Override
    public List<Contrato> getAllContratosByCPF(Cliente cliente) {
        return cliente.getContratos();
    } // exceptions: clienteInvalido
    @Override
    public Contrato getContratoByCPFNomeGrupo(Cliente cliente, GrupoConsorcio grupoAssociado) {
        for (Contrato contrato : contratos) {
            if (contrato.getCliente().getCpf().equalsIgnoreCase(cliente.getCpf()) && 
                contrato.getGrupoAssociado().getNomeGrupo().equalsIgnoreCase(grupoAssociado.getNomeGrupo())) {
                return contrato;
            }
        }
        return null;
    } // exceptions: clienteInvalido, grupoInvalido, contratoInexistente
    @Override
    public List<Contrato> getContratosByNomeGrupo(GrupoConsorcio grupoAssociado) {
        return grupoAssociado.getListaContratos();
    } // exceptions: grupoInvalido
    @Override
    public void createContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        Contrato contrato = new Contrato(cliente, grupoAssociado);
        contratos.add(contrato);
    } // exceptions: contratoDuplicado, clienteInvalido, grupoInvalido
    @Override
    public void updateParcelasPagas(Contrato contrato) {
        contrato.setParcelasPagas(contrato.getParcelasPagas() + 1);
    } // exceptions: contratoInexistente
    @Override
    public void updateSaldoDevedor(Contrato contrato) {
        contrato.setSaldoDevedor(contrato.getSaldoDevedor() - contrato.getGrupoAssociado().getValorParcela());
    } // exceptions: contratoInexistente
    @Override
    public void updateValorPago(Contrato contrato) {
        contrato.setValorPago(contrato.getValorPago() + contrato.getGrupoAssociado().getValorParcela());
    } // exceptions: contratoInexistente
    @Override
    public void updateSaldoDevolução(Contrato contrato) {
        contrato.setSaldoDevolucao(calcularSaldoDevolucao(contrato));
    } // exceptions: contratoInexistente

    private double calcularSaldoDevolucao(Contrato contrato) {
        return (contrato.getGrupoAssociado().getValorTotal() / contrato.getGrupoAssociado().getNumeroParticipantes()) * contrato.getParcelasPagas();
    } // exceptions: contratoInexistente
    @Override
    public void deleteContrato(Contrato contrato) {
        contratos.remove(contrato);
    } // exceptions: contratoInexistente

}