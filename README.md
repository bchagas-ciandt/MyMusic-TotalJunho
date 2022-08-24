

<div align="center"><img src="https://glamrap.pl/wp-content/uploads/2019/04/mymusic_logo.jpg" title="source:my music" /></div>

<div align="center"><h1>My Music Junho 2022</h1>  </div>
<div align="center"><h2> CI&Ters Team </h2>   </div>

*******
Sumário
1. [Sobre o Projeto](#sobreOProgeto)
2. [Arquitetura](#arquitetura)
3. [Versionamento de Código](#versionamentoDeCodigo)
4. [Tecnologias e Ferramentas](#tecnologiasEFerramentas)
5. [Funcionalidades](#funcionalidades)
6. [Banco de Dados](#BancoDeDados)
7. [Autenticação](#autenticacao)
8. [Gerenciamento do Projeto](#gerenciamentoDoProjeto)
9. [Colaboradores](#colaboradores)

*******

<div id='sobreOProgeto'/> 
<h3> Sobre o Projeto </h3>

O MyMusic API é uma aplicação responsável por gerenciar as musicas favoritas do usuário. 
E foi desenvolvida para aplicar o conteúdo estudado no Bootcamp Java Junho 2022 da CI&T. 
O grupo intitulado CI&Ters Team teve como desafio desenvolver funcionalidades para refatorar uma aplicação legada utilizando o mesmo banco de dados.

<div id='arquitetura'/> 
<h3> Arquitetura </h3>

Foi eleito pelo grupo o Padrão de Camadas para a arquitetura. Por ser uma das mais conhecidas e aplicadas na construção de softwares, a Arquitetura em Camadas foi uma escolha consensual na equipe.
Neste modelo a organização das responsabilidades fica separada por seu propósito, o que é chamado de camada.

<div align="center"><img src="https://1.bp.blogspot.com/-rETQcIDxSk8/XYRNiAc886I/AAAAAAAAA-I/EQv8YL_7BmAlHe29teIvZKsjO7PdAzGowCLcBGAsYHQ/s1600/layers.png" title="source:Padrão de Camadas" /></div>

<div id='versionamentoDeCodigo'/> 
<h3> Versionamento de Código </h3>

Para controle de versão, o Git foi a ferramenta utilizada neste projeto. E a metodologia de versionamento adotada foi o Git Flow. Deste modo, o fluxo de trabalho se deu com duas branches principais, a Main e a Developer, que eram permanentes, e com as Features, que eram branches que existiam para o desenvolvimento das taskes e encerradas após o merge com a Developer. A Developer, por sua vez, era mergeada à Main após concluída a funcionalidade.

<div id='tecnologiasEFerramentas'/> 
<h3> Tecnologias e Ferramentas </h3>

* Java 11;
* Maven;
* Spring boot 2.7.2;
* JPA/Hibernate;
* JUnit;
* Mockito;
* Pitest;
* JaCoCo;
* SQLite;
* Swagger;
* Postman;
* Git & GitHub;
* Intellij IDEA.

<div id='funcionalidades'/> 
<h3> Funcionalidades </h3>

- [] Autenticação e autorização (Login/Logout);
- [] Permite ao usuário buscar músicas por parâmetro no banco de dados;
- [] Permitir que o usuário adicione músicas à sua playlist;
- [] Permitir que o usuário remova músicas de sua playlist;
- [] Implementação de cache na busca de músicas;
- [] Alta cobertura de testes.

<div id='BancoDeDados'/> 
<h3> Banco de Dados </h3>

Foi estabelecido que o SGBD seria o SQLite e disponibilizado ao grupo uma estrutura inicial com uma base de dados pré-definida e populada, a qual deveria ser mantida com a seguinte modelagem:
<div align="center"><img src="https://i.imgur.com/yfMGrur.png" title="source:modelagem imgur" /></div>

<div id='autenticacao'/> 
<h3> Autenticação </h3>

Utilização da API token-provider e seus endpoints para gerar os tokens dos usuários.

<div id='gerenciamentoDoProjeto'/> 
<h3> Gerenciamento do Projeto </h3>

Agestão do projeto segiu os seguintes ritos:

* Daily;
* Alinhamentos técnicos;
* Planning;
* Demo e Retrospective;
* Rotação semanal do Tech Lead.

<div id='colaboradores'/> 
<h3> Colaboradores </h3>

* Bruno Gabriel de Oliveira Chagas @bchagas;  **[*Bruno- GitHub*](https://github.com/bchagas99)**
* Elane Garcia de Brito @elanegarcia; **[*Elane-GitHub*](https://github.com/garciaelane)**
* Eliabe Dafne Vieira de Oliveira @eliabe; **[*Eliabe- GitHub*](https://github.com/eliabe-ciandt)**
* Jehan Lucas Vieira e Silva @jehan; **[*Jehan-GitHub*](https://github.com/Jehanlucas)**
* Jezielle de Fátima Farias da Cunha @jezielle. **[*Jezielle-GitHub*](https://github.com/jezielleciandt)**
