📚 Projeto de Estudo: Sistema de Gestão de Chamados com Java Spring
Como parte do meu processo de aprendizado em Java com Spring Boot, desenvolvi um sistema simples de gestão de chamados, onde é possível cadastrar clientes e associá-los a diferentes tipos de demandas. O sistema é flexível o suficiente para ser usado em diversos contextos — como manutenção de software ou registro de demandas internas.
✅ Funcionalidades principais:
 - Cadastro de clientes e chamados
 - Relacionamento entre clientes e demandas
 - Upload e armazenamento de imagens no S3
 - Envio automático de e-mails de notificação ao registrar um chamado
🛠️ Tecnologias e ferramentas aplicadas:
 - Spring Boot (estrutura principal do projeto)
 - PostgreSQL (banco de dados relacional)
 - Flyway (gerenciamento de migrations)
 - MapStruct (conversão entre entidades e DTOs)
 - Amazon S3 (armazenamento de imagens anexadas)
 - RabbitMQ (disparo de mensagens para notificações por e-mail)
 - Spring Boot Starter Mail (envio de e-mails)

Para inicializar o projeto e essencial acessa a pasta raiz e rodar o comando
docker compose -f ./docker-compose.yml up -d

e apois isso pode rodar o projeto e acessar 
http://localhost:8080/swagger-ui/index.html#/

Onde será listado o swagger com os EP da api.
