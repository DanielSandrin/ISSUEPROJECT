package com.chamados.chamados.service;

import com.chamados.chamados.domain.chamados.Chamado;
import com.chamados.chamados.domain.chamados.ChamadoRequestDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarNotificacao(Chamado chamado){
        String mensagem = "Novo chamado cadastrado para o cliente - " + chamado.getCliente().getName();
        rabbitTemplate.convertAndSend("chamados","notificacao",mensagem);
    }
}
