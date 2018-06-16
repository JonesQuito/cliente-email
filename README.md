# CriptoMail - Cliente de email

## Projeto desenvolvido como atividade de avalia��o da disciplina de "Seguran�a em Sistemas de Informa��o" do curso de SI - UFG

## Breve descri��o
Trata-se de uma aplica��o de email que se propoe a fazer a troca segura de mensagens entre remetente e destinat�rio, para tal faz uso de alguns conceitos de seguran�a de informa��o.

## Para fazer o clone
`https://github.com/JonesQuito/cliente-email.git`

## Para rodar o projeto
##### Basta executar o .jar dispon�vel no diret�rio dist
OBS: A aplica��o vai pedir usu�rio e senha, portanto � necess�rio criar antes um usu�rio no servidor de email. A aplica��o serve est� dispon�vel no seguinte reposit�rio `https://github.com/JonesQuito/servidor-email.git`. Fazer o clone e iniciar a plica��o conforme orienta��o dispon�vel no README.md da mesma.

## Conceitos envolvidos
- Criptografia
- Chave sim�trica (AES)
- Chave p�blica e privada (RSA)
- Seguran�a da Informa��o

## Requisitos implementados
- [X] Permitir o usu�rio enviar uma mensagem
- [X] Calcular o hash da mensagem
- [X] Criptografar o hash com a chave privada do remetente
- [X] Criptografar a mensagem com uma chave sim�trica (AES)
- [X] Criptografar a chave AES com a chave p�blica do destinat�rio (RSA)
- [X] Empacotar os dados do email
- [X] Enviar o email a um servidor de e-mail
