package com.chamados.chamados.controller;

import com.chamados.chamados.domain.clientes.ClienteRequestDTO;
import com.chamados.chamados.domain.clientes.Clientes;
import com.chamados.chamados.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cliente")
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping()
    public ResponseEntity<Clientes> addOrUpdateCliente(@RequestBody ClienteRequestDTO body){
        Clientes newCliente = this.clienteService.createOrAlterCliente(body);
        return  ResponseEntity.ok(newCliente);
    }

    @GetMapping("")
    public ResponseEntity<List<Clientes>> getAllClientes(){
        List<Clientes> clientes = this.clienteService.getAll();

        return ResponseEntity.ok(clientes);
    }

    @DeleteMapping("{clienteId}")
    public ResponseEntity<Void> removeCliente(@PathVariable("clienteId") UUID clienteId){
        this.clienteService.deleteClienteId(clienteId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{clienteId}")
    public ResponseEntity<Clientes> getByIdCliente(@PathVariable("clienteId") UUID id){
        Clientes cliente = this.clienteService.getById(id);
        return ResponseEntity.ok(cliente);
    }
}
