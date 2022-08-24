

<div align="center"><img src="https://glamrap.pl/wp-content/uploads/2019/04/mymusic_logo.jpg" title="source:my music" /></div>

<div align="center"><h2>My Music Julho 2022</h2>  </div>
<div align="center"><h4> CI&Ters Team </h4>   </div>

<h5> Sobre o projeto </h5>

O MyMusic API é uma aplicação responsável por gerenciar as musicas favoritas do usuário. 
E foi desenvolvida para aplicar o conteúdo estudado no Bootcamp Java Junho 2022 da CI&T. 
O grupo CI&Ters Team teve como desafio desenvolver funcionalidades para refatorar uma aplicação legada utilizando o mesmo banco de dados.

<h5> Arquitetura </h5>

Foi eleito pelo grupo o Padrão de Camadas como arquitetura. Por ser uma das mais conhecidas e aplicadas na construção de softwares, a Arquitetura em Camadas foi uma escolha consensual na equipe.
Neste modelo a organização das responsabilidades fica separada por seu propósito, o que é chamado de camada.

<div align="center"><img src="https://1.bp.blogspot.com/-rETQcIDxSk8/XYRNiAc886I/AAAAAAAAA-I/EQv8YL_7BmAlHe29teIvZKsjO7PdAzGowCLcBGAsYHQ/s1600/layers.png" title="source:Padrão de Camadas" /></div>

<h5> Versionamento de Código </h5>

Para controle de versão, o Git foi a ferramenta utilizada neste projeto. E a metodologia de versionamento adotada foi o Git Flow. Deste modo, o fluxo de trabalho se deu com duas branches principais, a Main e a Developer, que eram permanentes, e com as Features, que eram branches que existiam para o desenvolvimento das taskes e eram encerradas após o merge com a Developer. A Developer, por sua vez, era mergeada à Main após concluída a funcionalidade.

<h5> Tecnologias e Ferramentas </h5>

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

<h5> Funcionalidades </h5>

- [] Autenticação e autorização (Login/Logout);
- [] Permite ao usuário buscar músicas por parâmetro no banco de dados;
- [] Permitir que o usuário adicione músicas à sua playlist;
- [] Permitir que o usuário remova músicas de sua playlist;
- [] Implementação de cache na busca de músicas;
- [] Alta cobertura de testes.

<h5> Banco de Dados </h5>

Foi estabelecido que o o SGBD seria o SQLite e disponibilizado ao grupo uma estrutura inicial com uma base de dados pré-definida e populada, a qual deveria ser mantida com a seguinte modelagem:
<div align="center"><img src="https://i.imgur.com/yfMGrur.png" title="source:modelagem imgur" /></div>

<h5> Autenticação </h5>

Utilização da API token-provider e seus endpoints para gerar os tokens dos usuários.

<h5> Gerenciamento do Projeto </h5>

Agestão do projeto segiu os seguintes ritos:

* Daily;
* Alinhamentos técnicos;
* Planning;
* Demo e Retrospective;
* Rotação semanal do Tech Lead.

<h5> Colaboradores </h5>

* Bruno Gabriel de Oliveira Chagas @bchagas;
* Elane Garcia de Brito @elanegarcia;
* Eliabe Dafne Vieira de Oliveira @eliabe;
* Jehan Lucas Vieira e Silva @jehan;
* Jezielle de Fátima Farias da Cunha @jezielle.
