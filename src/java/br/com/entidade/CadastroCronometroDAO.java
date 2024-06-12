package br.com.entidade;

import br.com.model.CronometroDeEstudos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CadastroCronometroDAO extends DAO {

    public void inserir(CronometroDeEstudos cronometro) throws SQLException, Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO CronometroDeEstudos (usuario_id, atividade, tempo_gasto, data) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, cronometro.getUsuarioId());
            pst.setString(2, cronometro.getAtividade());
            pst.setTime(3, cronometro.getTempoGasto());
            pst.setDate(4, cronometro.getData());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cronômetro de estudos: " + e.getMessage());
            throw e;
        } finally {
            fecharBanco();
        }
    }

    public ArrayList<CronometroDeEstudos> pesquisarTudo() throws SQLException, Exception {
        ArrayList<CronometroDeEstudos> listaCronometros = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM CronometroDeEstudos";
            PreparedStatement pst = con.prepareStatement(query);
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
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar todos os cronômetros de estudos: " + e.getMessage());
            throw e;
        } finally {
            fecharBanco();
        }
        return listaCronometros;
    }

    public boolean deletar(CronometroDeEstudos cronometro) throws SQLException, Exception {
        try {
            abrirBanco();
            String query = "DELETE FROM CronometroDeEstudos WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, cronometro.getId());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar cronômetro de estudos: " + e.getMessage());
            throw e;
        } finally {
            fecharBanco();
        }
    }

    public void alterar(CronometroDeEstudos cronometro) throws SQLException, Exception {
        try {
            abrirBanco();
            String query = "UPDATE CronometroDeEstudos SET usuarioId=?, atividade=?, tempoGasto=?, data=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, cronometro.getUsuarioId());
            pst.setString(2, cronometro.getAtividade());
            pst.setTime(3, cronometro.getTempoGasto());
            pst.setDate(4, cronometro.getData());
            pst.setInt(5, cronometro.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar cronômetro de estudos: " + e.getMessage());
            throw e;
        } finally {
            fecharBanco();
        }
    }

    public CronometroDeEstudos pesquisar(int id) throws SQLException, Exception {
        CronometroDeEstudos cronometro = null;
        try {
            abrirBanco();
            String query = "SELECT * FROM CronometroDeEstudos WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cronometro = new CronometroDeEstudos();
                cronometro.setId(rs.getInt("id"));
                cronometro.setUsuarioId(rs.getInt("usuarioId"));
                cronometro.setAtividade(rs.getString("atividade"));
                cronometro.setTempoGasto(rs.getTime("tempoGasto"));
                cronometro.setData(rs.getDate("data"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar cronômetro de estudos: " + e.getMessage());
            throw e;
        } finally {
            fecharBanco();
        }
        return cronometro;
    }

    public String calcularTempoTotalEstudo(int usuarioId) throws SQLException, Exception {
        String tempoTotalEstudo = "00:00:00"; // Tempo inicial como string
        try {
            abrirBanco();
            String query = "SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(tempo_gasto))) AS tempoTotal FROM CronometroDeEstudos WHERE usuario_id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, usuarioId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tempoTotalEstudo = rs.getString("tempoTotal");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao calcular tempo total de estudo: " + e.getMessage());
            throw e;
        } finally {
            fecharBanco();
        }
        return tempoTotalEstudo;
    }

    public List<Date> listarDatasEstudadas(int usuarioId) throws SQLException, Exception {
        List<Date> datasEstudadas = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT DISTINCT DATE(data) AS dataEstudo FROM CronometroDeEstudos WHERE usuario_id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, usuarioId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date dataEstudo = rs.getDate("dataEstudo");
                datasEstudadas.add(dataEstudo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar datas estudadas: " + e.getMessage());
            throw e;
        } finally {
            fecharBanco();
        }
        return datasEstudadas;
    }

}
