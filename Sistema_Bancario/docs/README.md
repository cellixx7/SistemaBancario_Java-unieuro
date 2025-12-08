# Documentação do Sistema Bancário

## Visão Geral
Este sistema simula operações bancárias básicas, como cadastro de usuários, login, depósitos, transferências e consulta de histórico de transações. A interface gráfica foi desenvolvida com Java Swing.

---

## Estrutura de Pastas
- **banco/**: Lógica principal do banco e gerenciamento de usuários.
- **cliente/**: (não utilizado no código principal)
- **contas/**: (não utilizado no código principal)
- **excecoes/**: (não utilizado no código principal)
- **main/**: Ponto de entrada do sistema.
- **transacao/**: Registro de transações bancárias.
- **ui/**: Interfaces gráficas (Login, Operações, Histórico, Principal).
- **usuario/**: Classe de usuário e operações financeiras.

---

## Classes Principais

### Banco
- **Atributos:**
  - `usuarios`: Lista de usuários cadastrados.
- **Métodos:**
  - `cadastrarUsuario(Usuario u)`: Adiciona novo usuário se CPF e email não existirem.
  - `login(String email, String senha)`: Autentica usuário.
  - `buscarPorCPF(String cpf)`: Busca usuário pelo CPF.
  - `buscarPorEmail(String email)`: Busca usuário pelo email.
  - `transferir(String cpfOrigem, String cpfDestino, double valor)`: Realiza transferência entre usuários.

### Usuario
- **Atributos:**
  - `cpf`, `nome`, `email`, `senha`, `dataNascimento`, `saldo`, `historico`
- **Construtor:**
  - Recebe dados do usuário e saldo inicial.
- **Métodos:**
  - `depositar(double valor)`: Realiza depósito e registra no histórico.
  - `receberTransferencia(double valor, String origemCpf, String origemNome)`: Recebe transferência e registra no histórico.
  - `transferir(double valor, Usuario destino)`: Realiza transferência para outro usuário.
  - Getters para todos os atributos.

### Transacao
- **Atributos:**
  - `tipo`, `valor`, `data`, `origem`, `destino`
- **Construtores:**
  - Permite registrar tipo, valor, origem e destino.
- **Métodos:**
  - `toString()`: Exibe detalhes da transação (data, tipo, origem, destino, valor).
  - `exibir()`: Imprime detalhes no console.

### Interfaces Gráficas (UI)

#### LoginUI
- Tela para login e cadastro de usuários.
- Valida campos obrigatórios e mostra mensagens de erro/sucesso.

#### MainUI
- Tela principal após login.
- Exibe saldo, permite acessar operações, histórico e logout.
- Atualiza saldo automaticamente após operações.

#### OpUi
- Tela para depósito e transferência.
- Valida campos, mostra mensagens e atualiza saldo na tela principal.

#### HistoricoUi
- Exibe histórico de transações do usuário.
- Mostra detalhes de cada operação (data, tipo, origem, destino, valor).

---

## Funcionamento
1. **Cadastro/Login:** Usuário se cadastra ou faz login.
2. **Tela Principal:** Exibe saldo e opções.
3. **Operações:** Usuário pode depositar ou transferir dinheiro.
4. **Histórico:** Usuário pode consultar todas as operações realizadas.
5. **Tratamento de Erros:** Todas as telas validam campos obrigatórios e exibem mensagens amigáveis.

---

## Observações
- O sistema não utiliza banco de dados, todos os dados ficam em memória.
- O histórico mostra detalhes completos das transações.
- O saldo é atualizado automaticamente após cada operação.

---

## Como Compilar e Executar (Windows PowerShell)

Este projeto usa `module-info.java` e pode ser compilado e executado sem Maven/Gradle.

1) Abra o PowerShell e posicione-se na raiz do projeto (a pasta que contém `src`).

2) Gere a lista de fontes e compile todos os `.java` para a pasta `bin`:

```powershell
cd "D:\CyLeX\Projetos\SistemaBancario_Java-unieuro\Sistema_Bancario"
Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -d bin @sources.txt
```

3) Execute a aplicação usando o módulo `Sistema_Bancario`. O ponto de entrada é `br.com.sistemabancario.main.Main`:

```powershell
java -p bin -m Sistema_Bancario/br.com.sistemabancario.main.Main
```

Observações:
- O comando acima assume que o `module-info.java` declara `module Sistema_Bancario { requires java.desktop; }`.
- Se preferir executar diretamente a classe `MainUI` (não modular), pode executar:

```powershell
java -cp bin br.com.sistemabancario.ui.MainUI
```

Se ocorrerem erros de compilação, verifique se todos os arquivos `.java` estão dentro de `src` seguindo sua estrutura de pacotes.

---
