# CriptoMail - Cliente de email

## Projeto desenvolvido como atividade de avaliação da disciplina de "Segurança em Sistemas de Informação" do curso de SI - UFG

## Breve descrição
Trata-se de uma aplicação de email que se propoe a fazer a troca segura de mensagens entre remetente e destinatário, para tal faz uso de alguns conceitos de segurança de informação.

## Para fazer o clone
`https://github.com/JonesQuito/cliente-email.git`

## Para rodar o projeto
##### Basta executar o .jar disponível no diretório dist
OBS: A aplicação vai pedir usuário e senha, portanto é necessário criar antes um usuário no servidor de email. A aplicação serve está disponível no seguinte repositório `https://github.com/JonesQuito/servidor-email.git`. Fazer o clone e iniciar a plicação conforme orientação disponível no README.md da mesma.

## Conceitos envolvidos
- Criptografia
- Chave simétrica (AES)
- Chave pública e privada (RSA)
- Segurança da Informação

## Requisitos implementados
- [X] Permitir o usuário enviar uma mensagem
- [X] Calcular o hash da mensagem
- [X] Criptografar o hash com a chave privada do remetente
- [X] Criptografar a mensagem com uma chave simétrica (AES)
- [X] Criptografar a chave AES com a chave pública do destinatário (RSA)
- [X] Empacotar os dados do email
- [X] Enviar o email a um servidor de e-mail
