# 🚀 Centro de Comando de Sondas Espaciais

Bem-vindo ao **Centro de Comando de Sondas**, um sistema robusto desenvolvido em Java aplicando os princípios de **Domain-Driven Design (DDD)** para gerenciar missões de exploração e mineração em terrenos remotos.

---

## 📋 Sobre o Projeto

Este projeto foi concebido para simular o controle operacional de sondas espaciais. O sistema permite a criação, monitoramento e execução de rotinas autônomas para diferentes tipos de sondas, garantindo que as operações sejam realizadas dentro dos limites de segurança (bateria e capacidade).

---

## 👥 Integrantes do Grupo

Conheça os desenvolvedores por trás deste projeto:

- **Antonio Neto** (RM: 561777)
- **Felipe Mendes** (RM: 563524)
- **Mauro Carlos** (RM: 556645)

### ✨ Funcionalidades Principais

- **🛰️ Cadastro de Sondas**: Registro de sondas do tipo *Exploradora* ou *Mineradora*.
- **📊 Monitoramento em Tempo Real**: Listagem detalhada de todas as sondas em operação, incluindo posição (coordenadas X, Y), nível de bateria e status de carga/sensores.
- **🤖 Rotinas Autônomas**: Movimentação de sondas para coordenadas específicas com execução de tarefas automáticas conforme o tipo de sonda e terreno:
    - **Sondas Exploradoras**: Realizam varredura de área utilizando sensores.
    - **Sondas Mineradoras**: Coletam recursos valiosos do solo.
- **🔋 Gerenciamento de Energia**: Sistema de recarga conectando as sondas à base central.
- **🛡️ Validações Robustas**: Tratamento de exceções personalizadas para garantir que nenhuma sonda opere sem bateria ou exceda limites físicos.

---

## 🛠️ Tecnologias e Arquitetura

O projeto utiliza uma arquitetura limpa inspirada em **Hexagonal/DDD**, garantindo separação de responsabilidades e facilidade de manutenção.

- **Linguagem**: Java 17+
- **Paradigma**: Orientação a Objetos
- **Padrões**: 
    - Domain-Driven Design (DDD)
    - Value Objects
    - Repositories (In-memory)
    - Service Layer
- **Interface**: Console CLI interativo e colorido.

---

## 🚀 Como Executar

Para rodar o projeto localmente, siga os passos abaixo:

1. **Pré-requisitos**: Certifique-se de ter o JDK 17 ou superior instalado.
2. **Clone o repositório**:
   ```bash
   git clone https://github.com/Shadow-WIzzards-Money-Gang/GS_Java_DDD.git
   ```
3. **Compilação e Execução**:
   - Abra o projeto em sua IDE de preferência (IntelliJ IDEA, Eclipse ou VS Code).
   - Navegue até o arquivo principal: `src/br/com/fiap/space/Main.java`.
   - Execute a classe `Main`.
4. **Interação**: Utilize o menu numérico no console para navegar pelas funcionalidades do Centro de Comando.

---
