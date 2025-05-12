package com.chamados.chamados.service;

import com.chamados.chamados.domain.chamados.Chamado;
import com.chamados.chamados.domain.chamados.ChamadoRequestDTO;
import com.chamados.chamados.domain.clientes.Clientes;
import com.chamados.chamados.mapper.ChamadoMapper;
import com.chamados.chamados.repositories.ChamadoRepository;
import com.chamados.chamados.repositories.ClientesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChamadoService {

    @Value("${aws.bucket.name}")
    private String _bucketName;
    @Autowired
    private ImagemService imagemService;
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private ChamadoMapper mapper;
    @Autowired
    private AWSService awsService;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    public Chamado createOrUpdateChamado(ChamadoRequestDTO data){
        Chamado chamado = null;
        Clientes cliente = clientesRepository.findById(data.clienteId())
                .orElseThrow(()-> new IllegalArgumentException("Cliente não encontrado"));

        if(data.id() != null){
            chamado = chamadoRepository.findById(data.id())
                    .orElseThrow(()-> new IllegalArgumentException("Chamado não encontrado"));

            mapper.updateEntity(data,chamado);
        }else{
            chamado = mapper.toEntity(data);
        }

        chamado.setCliente(cliente);

        chamado = chamadoRepository.save(chamado);

        rabbitMQSender.enviarNotificacao(chamado);

        return chamado;
    }

    public List<Chamado> getAll(){
        List<Chamado> chamados = chamadoRepository.findAll();

        return  chamados;
    }

    public Chamado getById(UUID id){

        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Cliente não encontrado"));

        return chamado;
    }

    public void deleteChamadoId(UUID id){
        try {
            Chamado chamado = chamadoRepository.findById(id)
                    .orElseThrow(()-> new IllegalArgumentException("Chamado não localizado!"));

            if(chamado == null){
                throw new EntityNotFoundException("Chamdo com ID " + id + " não encontrado" );
            }

            awsService.deleteAllFiles(_bucketName,chamado.getId().toString());
            chamadoRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Erro ao remover a chamado " + e.getMessage());
        }
    }
}
