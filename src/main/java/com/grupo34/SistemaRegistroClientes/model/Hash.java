package com.grupo34.SistemaRegistroClientes.model;


public class Hash {
    private int operador;
    private ListaCliente[] vetor;

    public Hash(int operador) {
        this.operador = operador;
        this.vetor = new ListaCliente[operador];
        for (int i = 0; i < operador; i++) {
            vetor[i] = new ListaCliente();
        }
    }

    // Método para retornar o vetor de ListaCliente
    public ListaCliente[] getVetor() {
        return vetor;
    }

    public void inserir(Cliente cliente) {
        int chave = calcularChave(cliente.getCpf());
        vetor[chave].inserir(cliente);
    }

    public boolean remover(String cpf) {
        int chave = calcularChave(cpf);
        return vetor[chave].remover(cpf);
    }

    public Cliente buscar(String cpf) {
        int chave = calcularChave(cpf);
        return vetor[chave].buscar(cpf);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < operador; i++) {
            output.append(i).append(": ").append(vetor[i]).append("\n");
        }
        return output.toString();
    }

    private int calcularChave(String cpf) {
        return Math.abs(cpf.hashCode()) % operador;
    }
}

