package br.com.sistemabancario.banco;

import br.com.sistemabancario.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;

public class Banco {

    private List<Usuario> usuarios = new ArrayList<>();

    /**
     * Cadastra um novo usuário no banco se CPF e email ainda não existirem.
     * @param u usuário a cadastrar
     * @return true se cadastrado com sucesso, false se CPF/email já existem
     */
    public boolean cadastrarUsuario(Usuario u) {
        if (buscarPorCPF(u.getCpf()) != null) return false;
        if (buscarPorEmail(u.getEmail()) != null) return false;

        usuarios.add(u);
        return true;
    }

    /**
     * Tenta autenticar um usuário pelo email e senha.
     * @param email email do usuário
     * @param senha senha do usuário
     * @return o usuário autenticado ou null se não encontrado
     */
    public Usuario login(String email, String senha) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }

    /** Busca um usuário pelo CPF. */
    public Usuario buscarPorCPF(String cpf) {
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) return u;
        }
        return null;
    }

    /** Busca um usuário pelo email. */
    public Usuario buscarPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) return u;
        }
        return null;
    }

    /**
     * Realiza transferência entre usuários identificados por CPF.
     * Valida existência de ambos os usuários antes de delegar a operação.
     * @param cpfOrigem CPF do usuário de origem
     * @param cpfDestino CPF do usuário destino
     * @param valor valor a transferir
     * @throws Exception em caso de usuário inexistente ou regra do usuário
     */
    public void transferir(String cpfOrigem, String cpfDestino, double valor) throws Exception {
        Usuario origem = buscarPorCPF(cpfOrigem);
        Usuario destino = buscarPorCPF(cpfDestino);

        if (origem == null)
            throw new Exception("Usuário de origem não encontrado.");

        if (destino == null)
            throw new Exception("Usuário de destino não encontrado.");

        // Chama a regra existente no usuario
        origem.transferir(valor, destino);
    }
}
