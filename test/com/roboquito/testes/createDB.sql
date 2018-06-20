/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Jones
 * Created: 16/06/2018
 */
show databases;
use servidor_email;

use servidor_email;
create table chave(
    id integer primary key auto_increment,
    owner varchar(255),
    chave VARBINARY(255)
);

show tables;
describe cliente;