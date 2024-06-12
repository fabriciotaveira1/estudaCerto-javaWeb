package br.com.entidade;

import br.com.model.Disciplina;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CadastroDisciplinaDAO extends DAO {

    public void inserir(Disciplina disciplina) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO Disciplina (usuario_id, nome, carga_horaria, professor_responsavel, descricao) VALUES (?, ?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, disciplina.getUsuarioId());
            pst.setString(2, disciplina.getNome());
            pst.setInt(3, disciplina.getCargaHoraria());
            pst.setString(4, disciplina.getProfessor());
            pst.setString(5, disciplina.getDescricao());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao inserir disciplina: " + e.getMessage());
        }
    }

    public void alterarNota(int idUsuario, int idDisciplina, float novaNota) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE Disciplina SET nota = ? WHERE usuario_id = ? AND id = ?";
            pst = con.prepareStatement(query);
            pst.setFloat(1, novaNota);
            pst.setInt(2, idUsuario);
            pst.setInt(3, idDisciplina);
            pst.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar nota da disciplina: " + e.getMessage());
        }
    }

    public ArrayList<Disciplina> pesquisarTudo() throws Exception {
        ArrayList<Disciplina> listaDisciplinas = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT id, nome, carga_horaria, professor_responsavel, descricao, nota FROM Disciplina";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
                disciplina.setProfessor(rs.getString("professor_responsavel"));
                disciplina.setDescricao(rs.getString("descricao"));
                disciplina.setNota(rs.getFloat("nota"));
                listaDisciplinas.add(disciplina);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar todas as disciplinas: " + e.getMessage());
        }
        return listaDisciplinas;
    }

    public void excluirNota(int usuarioId, int disciplinaId) throws Exception {
        try {
            abrirBanco();
            String query = "DELETE FROM Nota WHERE usuario_id = ? AND disciplina_id = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, usuarioId);
            pst.setInt(2, disciplinaId);
            pst.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao excluir nota da disciplina: " + e.getMessage());
        }
    }

    public boolean deletar(Disciplina disciplina) throws Exception {
        try {
            abrirBanco();
            String query = "DELETE FROM Disciplina WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, disciplina.getId());
            int rowsAffected = pst.executeUpdate();
            fecharBanco();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Erro ao deletar disciplina: " + e.getMessage());
            throw new Exception("Erro ao deletar disciplina", e);
        }
    }

    public void alterar(Disciplina disciplina) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE Disciplina SET nome=?, carga_horaria=?, professor_responsavel=?, descricao=? WHERE id=?";
            pst = con.prepareStatement(query);
            pst.setString(1, disciplina.getNome());
            pst.setInt(2, disciplina.getCargaHoraria());
            pst.setString(3, disciplina.getProfessor());
            pst.setString(4, disciplina.getDescricao());
            pst.setInt(5, disciplina.getId());
            pst.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao alterar disciplina: " + e.getMessage());
        }
    }

    public Disciplina pesquisar(int id) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM Disciplina WHERE id=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
                disciplina.setProfessor(rs.getString("professor_responsavel"));
                disciplina.setDescricao(rs.getString("descricao"));
                fecharBanco();
                return disciplina;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar disciplina: " + e.getMessage());
        }
        return null;
    }

}
