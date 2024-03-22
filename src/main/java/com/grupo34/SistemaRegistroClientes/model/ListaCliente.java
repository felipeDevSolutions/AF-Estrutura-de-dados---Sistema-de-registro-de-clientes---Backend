package com.grupo34.SistemaRegistroClientes.model;

import java.util.ArrayList;
import java.util.List;

public class ListaCliente {
    private No inicio;
    private int tamanho;

    public ListaCliente() {
        this.inicio = null;
        this.tamanho = 0;
    }

    public void inserir(Cliente cliente) {
        No novoNo = new No(cliente);
        novoNo.proximoNo = inicio;
        inicio = novoNo;
        tamanho++;
    }

    public boolean remover(String cpf) {
        if (inicio == null) {
            return false;
        }
        if (inicio.cliente.getCpf().equals(cpf)) {
            inicio = inicio.proximoNo;
            tamanho--;
            return true;
        }
        No atual = inicio;
        while (atual.proximoNo != null) {
            if (atual.proximoNo.cliente.getCpf().equals(cpf)) {
                atual.proximoNo = atual.proximoNo.proximoNo;
                tamanho--;
                return true;
            }
            atual = atual.proximoNo;
        }
        return false;
    }

    public Cliente buscar(String cpf) {
        No atual = inicio;
        while (atual != null) {
            if (atual.cliente.getCpf().equals(cpf)) {
                return atual.cliente;
            }
            atual = atual.proximoNo;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        No atual = inicio;
        while (atual != null) {
            output.append(atual.cliente).append(" ");
            atual = atual.proximoNo;
        }
        return output.toString();
    }

    public int getTamanho() {
        return tamanho;
    }

    private class No {
        private Cliente cliente;
        private No proximoNo;

        public No(Cliente cliente) {
            this.cliente = cliente;
            this.proximoNo = null;
        }
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        No atual = inicio;
        while (atual != null) {
            clientes.add(atual.cliente);
            atual = atual.proximoNo;
        }
        return clientes;
    }
}

