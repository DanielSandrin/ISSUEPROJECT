package com.chamados.chamados.service;

import com.chamados.chamados.domain.clientes.ClienteRequestDTO;
import com.chamados.chamados.domain.clientes.Clientes;
import com.chamados.chamados.mapper.ClienteMapper;
import com.chamados.chamados.repositories.ClientesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private ClienteMapper mapper;

    public Clientes createOrAlterCliente(ClienteRequestDTO data){
        Clientes cliente = null;

        if(data.id() != null){
            cliente = clientesRepository.findById(data.id())
                    .orElseThrow(()-> new IllegalArgumentException("Cliente não encontrado"));

            mapper.updateEntity(data,cliente);
        }else{
            cliente = mapper.toEntity(data);
        }

        return clientesRepository.save(cliente);
    }



    public List<Clientes> getAll(){
        List<Clientes> clientes = clientesRepository.findAll();

        return  clientes;
    }

    public Clientes getById(UUID id){
        Clientes cliente = clientesRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("Cliente não encontrado"));

        return cliente;
    }

    public void deleteClienteId(UUID id){
        try {
            if(!clientesRepository.existsById(id)){
                throw new EntityNotFoundException("Cliente com ID " + id + " não encontrado" );
            }

            clientesRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Erro ao remover a chamado " + e.getMessage());
        }
    }
}
