package com.grupo34.SistemaRegistroClientes.model;


public class No {
    Cliente cliente; // Atributo cliente do tipo Cliente
    No proximoNo; // Atributo para o próximo No

    public No(Cliente cliente) {
        this.cliente = cliente;
        this.proximoNo = null;
    }
}

