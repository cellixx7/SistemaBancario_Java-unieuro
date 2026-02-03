# 🏦 Sistema Bancário - Java (POO)

Este projeto é uma aplicação de console desenvolvida para consolidar conceitos fundamentais de **Programação Orientada a Objetos (POO)** em Java. Criado originalmente como um projeto acadêmico na **Unieuro**, o sistema simula as operações essenciais de uma instituição financeira.

---

## 🚀 Funcionalidades

O sistema permite a gestão de diferentes tipos de contas e operações bancárias padrão:
- **Criação de Contas:** Suporte para Conta Corrente e Conta Poupança.
- **Operações Financeiras:** Depósitos, saques (com validação de saldo) e transferências entre contas.
- **Relatórios:** Exibição de extratos e informações detalhadas do cliente.
- **Cálculo de Taxas:** Implementação de regras de negócio específicas para cada tipo de conta.

---

## 🧠 Conceitos de POO Aplicados

Mais do que apenas um sistema funcional, este projeto demonstra o domínio de:

1. **Abstração:** Modelagem de entidades do mundo real (Contas, Clientes) em classes Java.
2. **Encapsulamento:** Uso rigoroso de modificadores de acesso (`private`, `protected`) e métodos *getters/setters* para proteger a integridade dos dados.
3. **Herança:** Utilização de uma classe base `Conta` para compartilhar comportamentos comuns entre `ContaCorrente` e `ContaPoupanca`.
4. **Polimorfismo:** Sobrescrita de métodos (`@Override`) para comportamentos específicos, como diferentes taxas de saque ou rendimentos.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java (JDK 17 ou superior)
- **Paradigma:** Orientação a Objetos
- **Ferramenta de Build:** Maven (opcional - se usar) / Java Standard Library

---

## 💻 Como Executar

1. Clone o repositório:
   ```bash
   git clone [https://github.com/cellixx7/SistemaBancario_Java-unieuro.git](https://github.com/cellixx7/SistemaBancario_Java-unieuro.git)
   ```

2. Acesse a pasta do projeto:

```bash
cd SistemaBancario_Java-unieuro
```
3. Compile e execute a classe principal:

```bash
javac Main.java
java Main
```
📄 Licença
Este projeto foi desenvolvido para fins didáticos e de portfólio. Sinta-se à vontade para explorar e sugerir melhorias!

Desenvolvido por Marcelo Vaz & Álex Santana 👋

[Slides de apresentação](https://gamma.app/docs/Desvendando-o-Sistema-Bancario-em-Java-Parte-1-oyurvm6gs44au25)

