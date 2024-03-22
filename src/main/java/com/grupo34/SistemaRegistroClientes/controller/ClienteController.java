

package com.grupo34.SistemaRegistroClientes.controller;

// ClienteController.java

import com.grupo34.SistemaRegistroClientes.controller.ClienteController;
import com.grupo34.SistemaRegistroClientes.model.Cliente;
import com.grupo34.SistemaRegistroClientes.model.Hash;
import com.grupo34.SistemaRegistroClientes.model.ListaCliente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {

    private final Hash hash;

    @Autowired
    public ClienteController() {
        this.hash = new Hash(10);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente clienteExistente = hash.buscar(cliente.getCpf());
        if (clienteExistente != null) {
            return ResponseEntity.badRequest().body("CPF já cadastrado");
        } else {
            hash.inserir(cliente);
            return ResponseEntity.ok(cliente);
        }
    }


    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        for (ListaCliente lista : hash.getVetor()) {
            clientes.addAll(lista.listar());
        }
        if (!clientes.isEmpty()) {
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } else {
            // Retorna uma lista vazia com status OK se não houver clientes cadastrados
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }
    }



    @GetMapping("/buscar")
    public ResponseEntity<Cliente> buscarClientePorCPF(@RequestParam String cpf) {
        Cliente cliente = hash.buscar(cpf);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf) {
        boolean removido = hash.remover(cpf);
        if (removido) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}