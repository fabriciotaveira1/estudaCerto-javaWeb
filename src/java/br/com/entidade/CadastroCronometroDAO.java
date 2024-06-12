package br.com.entidade;

import br.com.model.CronometroDeEstudos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

public class CadastroCronometroDAO extends DAO {

    public void inserir(CronometroDeEstudos cronometro) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO CronometroDeEstudos (usuarioId, atividade, tempoGasto, data) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, cronometro.getUsuarioId());
            pst.setString(2, cronometro.getAtividade());
            pst.setTime(3, cronometro.getTempoGasto());
            pst.setDate(4, new java.sql.Date(cronometro.getData().getTime()));
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao inserir cronometro de estudos: " + e.getMessage());
        }
    }

    public ArrayList<CronometroDeEstudos> pesquisarTudo() throws Exception {
        ArrayList<CronometroDeEstudos> listaCronometros = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM CronometroDeEstudos";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CronometroDeEstudos cronometro = new CronometroDeEstudos();
                cronometro.setId(rs.getInt("id"));
                cronometro.setUsuarioId(rs.getInt("usuarioId"));
                cronometro.setAtividade(rs.getString("atividade"));
                cronometro.setTempoGasto(rs.getTime("tempoGasto"));
                cronometro.setData(rs.getDate("data"));
                listaCronometros.add(cronometro);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar todos os cronometros de estudos: " + e.getMessage());
        }
        return listaCronometros;
    }

    public boolean deletar(CronometroDeEstudos cronometro) throws Exception {
        try {
            abrirBanco();
            String query = "DELETE FROM CronometroDeEstudos WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, cronometro.getId());
            int rowsAffected = pst.executeUpdate();
            fecharBanco();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Erro ao deletar cronometro de estudos: " + e.getMessage());
            throw new Exception("Erro ao deletar cronometro de estudos", e);
        }
    }

    public void alterar(CronometroDeEstudos cronometro) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE CronometroDeEstudos SET usuarioId=?, atividade=?, tempoGasto=?, data=? WHERE id=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, cronometro.getUsuarioId());
            pst.setString(2, cronometro.getAtividade());
            pst.setTime(3, cronometro.getTempoGasto());
            pst.setDate(4, new java.sql.Date(cronometro.getData().getTime()));
            pst.setInt(5, cronometro.getId());
            pst.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao alterar cronometro de estudos: " + e.getMessage());
        }
    }

    public CronometroDeEstudos pesquisar(int id) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM CronometroDeEstudos WHERE id=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                CronometroDeEstudos cronometro = new CronometroDeEstudos();
                cronometro.setId(rs.getInt("id"));
                cronometro.setUsuarioId(rs.getInt("usuarioId"));
                cronometro.setAtividade(rs.getString("atividade"));
                cronometro.setTempoGasto(rs.getTime("tempoGasto"));
                cronometro.setData(rs.getDate("data"));
                fecharBanco();
                return cronometro;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar cronometro de estudos: " + e.getMessage());
        }
        return null;
    }
}
