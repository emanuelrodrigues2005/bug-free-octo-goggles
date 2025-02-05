package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.ContratoRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IContratoRepository;

public class ContratoController {
    private static ContratoController instancia;
    private final IContratoRepository repositorioContrato;
    
    public ContratoController() {
        this.repositorioContrato = ContratoRepository.getInstancia();
    }

    public static ContratoController getInstancia() {
        if (instancia == null) {
            instancia = new ContratoController();
        }
        return instancia;
    }
    
    // public void pagarParcela(Cliente cliente, GrupoConsorcio grupoAssociado, Boleto boleto) {
    //     if (getContratoByCPFNomeGrupo(cliente, grupoAssociado) != null & !(getContratoByCPFNomeGrupo(cliente, grupoAssociado).getListaBoletosPagos().contains(boleto))) {
    //         if (boleto.getStatusBoleto() == StatusBoletoEnum.PAGO) {
    //             Contrato contrato = getContratoByCPFNomeGrupo(cliente, grupoAssociado);
    //             updateContrato(cliente, contrato.getParcelasPagas() + 1, contrato.getSaldoDevedor() - contrato.getGrupoAssociado().getValorParcela(), boleto);
    //             System.out.println("A parcela foi paga com sucesso.");
    //         } else if (boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE) {
    //             System.out.println("O boleto ainda está pendente de pagamento. Operação negada");
    //         } else if (boleto.getStatusBoleto() == StatusBoletoEnum.ATRASADO) {
    //             System.out.println("O boleto ainda está pendente de pagamento e está atrasado no momento. Operação negada");
    //         } 
    //     } else {
    //         System.out.println("O cliente não foi encontrado ou o boleto já foi pago.");
    //     }
    // } // reestruturar método para receber Contrato e Boleto como parametros 

    // public void cancelarContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
    //     if (getContratoByCPFNomeGrupo(cliente, grupoAssociado) != null) {
    //         Contrato contrato = getContratoByCPFNomeGrupo(cliente, grupoAssociado);
    //         contrato.setStatusContrato(StatusContratoEnum.ENCERRADO);
    //         contrato.setSaldoDevolucao(calcularSaldoDevolucao(contrato));
    //         System.out.printf("Contrato cancelado com sucesso, suas parcelas pagas serão devolvidas após o término do consórcio.\n" );
    //     } else {
    //         System.out.println("Contrato não encontrado não pôde ser cancelado.");
    //     }
    // } // refazer a estrutura do método para parâmetro único (Contrato)
}
