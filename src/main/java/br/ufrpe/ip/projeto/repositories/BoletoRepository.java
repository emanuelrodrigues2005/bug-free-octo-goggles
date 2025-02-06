package br.ufrpe.ip.projeto.repositories;

import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.repositories.interfaces.IBoletoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class BoletoRepository implements IBoletoRepository {
    private static BoletoRepository instance;
    private ArrayList<Boleto> boletos;

    private BoletoRepository() {
        this.boletos = new ArrayList<>();
    }

    public static BoletoRepository getInstance() {
        if (instance == null) {
            instance = new BoletoRepository();
        }
        return instance;
    }

    @Override
    public ArrayList<Boleto> getAllBoletos() {
        if (boletos.isEmpty()) {
            return null;
        }
        return boletos;
    }

    @Override
    public int getIdBoleto(Contrato contrato, int numeroParcela) {
        for (Boleto boleto : boletos) {
            if (boleto.getContratoBoleto().equals(contrato) &&
                    boleto.getContratoBoleto().getCliente().getCpf().equals(contrato.getCliente().getCpf()) &&
                    boleto.getNumeroParcela() == numeroParcela) {
                return boleto.getIdBoleto();
            }
        }
        return -1;
    }

    @Override
    public Boleto getBoletoById(int idBoleto) {
        for (Boleto boleto : boletos) {
            if (boleto.getIdBoleto() == idBoleto) {
                return boleto;
            }
        }
        return null;
    }

    @Override
    public Boleto getBoletoByContrato(Contrato contrato) {
        for (Boleto boleto : boletos) {
            if(boleto.getContratoBoleto().getIdContrato() == contrato.getIdContrato()) {
                return boleto;
            }
        }
        return null;
    }

    @Override
    public Boleto createBoleto(Contrato contrato, LocalDate dataVencimento, int numeroParcela) {
        Boleto boleto = new Boleto(contrato, dataVencimento, numeroParcela);
        boletos.add(boleto);
        return boleto;
    }

    @Override
    public void updateDataPagamento(Boleto boleto) {
        boleto.setDataPagamento(LocalDate.now());
    }

    @Override
    public void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto) {
        boleto.setStatusBoleto(statusBoleto);
    }

    @Override
    public void deleteBoleto(int idBoleto) {
        boletos.remove(getBoletoById(idBoleto));
    }
}