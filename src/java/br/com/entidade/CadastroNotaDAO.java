/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.model.Disciplina;
import br.com.model.Nota;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CadastroNotaDAO extends DAO {

    public void inserir(Nota nota) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO Nota (usuario_id, disciplina_id, nota) VALUES (?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, nota.getUsuarioId());
            pst.setInt(2, nota.getDisciplinaId());
            pst.setDouble(3, nota.getNota());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao inserir nota: " + e.getMessage());
        }
    }

    public ArrayList<Nota> pesquisarTudo() throws Exception {
        ArrayList<Nota> listaNotas = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT id, usuario_id, disciplina_id, nota FROM Nota";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Nota nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setUsuarioId(rs.getInt("usuario_id"));
                nota.setDisciplinaId(rs.getInt("disciplina_id"));
                nota.setNota(rs.getDouble("nota"));
                listaNotas.add(nota);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar todas as notas: " + e.getMessage());
        }
        return listaNotas;
    }

    public boolean deletar(Nota nota) throws Exception {
        try {
            abrirBanco();
            String query = "DELETE FROM Nota WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, nota.getId());
            int rowsAffected = pst.executeUpdate();
            fecharBanco();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Erro ao deletar nota: " + e.getMessage());
            throw new Exception("Erro ao deletar nota", e);
        }
    }

    public void alterar(Nota nota) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE Nota SET usuario_id=?, disciplina_id=?, nota=? WHERE id=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, nota.getUsuarioId());
            pst.setInt(2, nota.getDisciplinaId());
            pst.setDouble(3, nota.getNota());
            pst.setInt(4, nota.getId());
            pst.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao alterar nota: " + e.getMessage());
        }
    }

    public Nota pesquisarPorUsuarioEDisciplina(int usuarioId, int disciplinaId) throws Exception {
        Nota nota = null;
        try {
            abrirBanco();
            String query = "SELECT * FROM Nota WHERE usuario_id=? AND disciplina_id=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, usuarioId);
            pst.setInt(2, disciplinaId);

            // Adicionando logs de depuração
            System.out.println("Executando query: " + query);
            System.out.println("Parâmetros: usuario_id=" + usuarioId + ", disciplina_id=" + disciplinaId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setUsuarioId(rs.getInt("usuario_id"));
                nota.setDisciplinaId(rs.getInt("disciplina_id"));
                nota.setNota(rs.getDouble("nota"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar nota: " + e.getMessage());
            throw e;
        } finally {
            fecharBanco();
        }
        return nota;
    }

    public Nota pesquisar(int id) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM Nota WHERE id=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Nota nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setUsuarioId(rs.getInt("usuario_id"));
                nota.setDisciplinaId(rs.getInt("disciplina_id"));
                nota.setNota(rs.getDouble("nota"));
                fecharBanco();
                return nota;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar nota: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Disciplina> buscarTodasDisciplinas(int usuarioId) throws Exception {
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT id, nome, carga_horaria, nota FROM Disciplina WHERE usuario_id=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, usuarioId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
                disciplina.setNota((float) rs.getDouble("nota"));
                disciplinas.add(disciplina);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao buscar todas as disciplinas: " + e.getMessage());
            throw new Exception("Erro ao buscar todas as disciplinas", e);
        }
        return disciplinas;
    }

}
