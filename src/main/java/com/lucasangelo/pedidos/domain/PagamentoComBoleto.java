package com.lucasangelo.pedidos.domain;

import java.util.Date;

import com.lucasangelo.pedidos.domain.enums.EstadoPagamento;

public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;
    
    private Date dataVencimento;
    private Date dataPagamento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return this.dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return this.dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}