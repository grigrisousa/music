#  Sistema de Streaming de Música

##  Descrição do Projeto

Este projeto foi desenvolvido para a disciplina de Programação Orientada a Objetos (POO).

O sistema simula uma plataforma de streaming de música em console, permitindo o gerenciamento de músicas, playlists e usuários com diferentes tipos de acesso.

O projeto foi desenvolvido em Java utilizando os principais conceitos de Programação Orientada a Objetos.

---

# Funcionalidades

## Cadastro e gerenciamento de músicas

* Cadastro de músicas
* Listagem de músicas
* Busca de músicas por título

## Sistema de playlists

* Criação de playlists
* Adição de músicas em playlists
* Playlist automática

## Tipos de usuários

* Usuário Free
* Usuário Premium

## Reprodução de músicas

* Reprodução de músicas
* Pausar reprodução
* Parar reprodução
* Histórico de reprodução

##  Sistema Premium

* Download de músicas
* Remoção de downloads
* Verificação de músicas baixadas

## Estatísticas

* Quantidade de usuários Free
* Quantidade de usuários Premium
* Total de músicas cadastradas

---

# Conceitos de POO Aplicados

## Encapsulamento

Uso de atributos privados com métodos getters.

## Herança

As classes `UsuarioFree`, `UsuarioPremium` e `PlaylistAutomatica` herdam comportamento de suas superclasses.

## Polimorfismo

Uso de referências genéricas como:

```java
Usuario usuarioLogado;
```

## Classes Abstratas

* `Usuario`
* `ItemReproducao`

## Interfaces

* `Reproduzivel`
* `Baixavel`

## Sobrescrita de Métodos

Uso de `@Override` em métodos sobrescritos.

---

# Estrutura de Pacotes

```text
src/
├── br/
│   └── com/
│       └── streaming/
│           ├── modelo/
│           ├── principal/
│           ├── servico/
│           └── util/
```

---

# Tecnologias Utilizadas

* Java
* IntelliJ IDEA
* Programação Orientada a Objetos

---

# Como Executar o Projeto

1. Clone ou baixe o projeto
2. Abra o projeto no IntelliJ IDEA
3. Execute a classe:

```text
StreamingMusica.java
```

4. Utilize o menu no console para navegar pelo sistema.

---

# Autor

* Nome: Ingrid Ferreira de Sousa
* RA: 45999279

---

# Histórico

##  Checkpoint 1

* Estrutura inicial do sistema
* Cadastro de músicas
* Listagem de músicas

## Checkpoint 2

* Criação de playlists
* Associação entre músicas e playlists

## Checkpoint 3

* Implementação de herança
* Classes `UsuarioFree` e `UsuarioPremium`

## Checkpoint 4

* Implementação de polimorfismo
* Reprodução de músicas
* Sistema de estatísticas

## Checkpoint 5

* Implementação de interfaces
* Sistema de downloads
* Organização em pacotes

## Checkpoint Final

* Finalização do sistema
* Histórico de reprodução
* README.md
* Aplicação completa de conceitos de POO

---

#  Usuários de Teste

| Usuário | Tipo    |
| ------- | ------- |
| Ingrid  | Free    |
| Carlos  | Premium |
| Ana     | Free    |

---

#  Músicas de Teste

* Bohemian Rhapsody - Queen
* Billie Jean - Michael Jackson
* Shape of You - Ed Sheeran

---

#  Objetivo Acadêmico

Este projeto teve como objetivo praticar:

* Programação Orientada a Objetos
* Organização em pacotes
* Interfaces
* Herança
* Polimorfismo
* Classes abstratas
* Estruturação de sistemas em Java

---

# Status do Projeto

*Projeto finalizado
*Funcionalidades implementadas
*Estrutura organizada
*Código compilando corretamente

---

# Desenvolvedora

Projeto desenvolvido por Ingrid Ferreira de Sousa para fins acadêmicos.
