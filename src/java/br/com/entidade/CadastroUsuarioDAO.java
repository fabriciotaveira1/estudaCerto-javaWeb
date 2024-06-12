package br.com.entidade;

import br.com.model.Usuario;
import br.com.services.SegurancaUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CadastroUsuarioDAO extends DAO {

    public void inserir(Usuario usuario) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO Usuario (nome, email, senha, tipo_usuario) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenhaHash()); // Usar senha criptografada
            pst.setString(4, usuario.getTipoUsuario());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
        }
    }

    public ArrayList<Usuario> pesquisarTudo() throws Exception {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT id, nome, email, tipo_usuario FROM Usuario"; // Removida a senha da consulta SQL
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                listaUsuarios.add(usuario);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar todos os usuarios: " + e.getMessage());
        }
        return listaUsuarios;
    }

    public boolean deletar(Usuario usuario) throws Exception {
        try {
            abrirBanco();
            String query = "DELETE FROM Usuario WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, usuario.getId());
            int rowsAffected = pst.executeUpdate();
            fecharBanco();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Erro ao deletar usuario: " + e.getMessage());
            throw new Exception("Erro ao deletar usuario", e);
        }
    }

    public void alterar(Usuario usuario) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE Usuario SET nome=?, email=?, senha=?, tipo_usuario=? WHERE id=?";
            pst = con.prepareStatement(query);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenhaHash()); // Usar senha criptografada
            pst.setString(4, usuario.getTipoUsuario());
            pst.setInt(5, usuario.getId());
            pst.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao alterar usuario: " + e.getMessage());
        }
    }

    public Usuario pesquisar(int id) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM Usuario WHERE id=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenhaHash(rs.getString("senha"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                fecharBanco();
                return usuario;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar usuario: " + e.getMessage());
        }
        return null;
    }

    public Usuario autenticar(String email, String senha) throws Exception {
        Usuario usuario = null;
        try {
            abrirBanco();
            String query = "SELECT id, nome, email, senha,tentativas_login_incorretas, conta_suspensa   FROM usuario WHERE email=?";
            pst = con.prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String senhaHash = rs.getString("senha");
                if (SegurancaUtils.verificarSenha(senha, senhaHash)) { // Comparar senha fornecida com a senha criptografada
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTentativasLoginIncorretas(rs.getInt("tentativas_login_incorretas"));
                    usuario.setContaSuspensa(rs.getBoolean("conta_suspensa"));
                }
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao autenticar: " + e.getMessage());
            fecharBanco();
            throw e;
        }
        return usuario;
    }

    public void incrementarTentativasLoginIncorretas(String email) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE usuario SET tentativas_login_incorretas = tentativas_login_incorretas + 1 WHERE email=?";
            pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao incrementar tentativas de login incorretas: " + e.getMessage());
            throw e;
        } finally {
            fecharBanco();
        }
    }

    public int getTentativasLoginIncorretas(String email) throws Exception {
        int tentativas = 0;
        try {
            abrirBanco();
            String query = "SELECT tentativas_login_incorretas FROM usuario WHERE email=?";
            pst = con.prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tentativas = rs.getInt("tentativas_login_incorretas");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao obter tentativas de login incorretas: " + e.getMessage());
            fecharBanco();
            throw e;
        }
        return tentativas;
    }

    public void suspenderConta(String email) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE usuario SET conta_suspensa = true WHERE email=?";
            pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao suspender conta: " + e.getMessage());
            throw e;
        } finally {
            fecharBanco();
        }
    }

    public boolean emailUnico(String email) throws Exception {
        boolean isEmailUnico = false;
        try {
            abrirBanco();
            String query = "SELECT COUNT(*) AS total FROM Usuario WHERE email = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                isEmailUnico = total == 0; // Se o total for 0, significa que o e-mail é único
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar e-mail único: " + e.getMessage());
            throw e; // Lançar a exceção para que seja tratada na servlet
        } finally {
            fecharBanco();
        }
        return isEmailUnico; // Retorna true se o e-mail for único, false caso contrário
    }

}
