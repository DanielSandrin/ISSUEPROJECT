package com.chamados.chamados.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListenerService {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "ISSUE")
    public void receberMensagemNovoChamado(String mensagem){
        emailService.enviarEmail("danielsandrincampos@gmail.com",
                "Novo Chamado Cadastrado",mensagem);
    }
}
