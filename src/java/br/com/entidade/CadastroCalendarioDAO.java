package br.com.entidade;

import br.com.model.Calendario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CadastroCalendarioDAO extends DAO {

    public void inserir(Calendario calendario) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO Calendario (usuarioId, evento, dataHora, lembrete) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, calendario.getUsuarioId());
            pst.setString(2, calendario.getEvento());
            pst.setDate(3, new java.sql.Date(calendario.getDataHora().getTime()));
            pst.setString(4, calendario.getLembrete());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao inserir evento: " + e.getMessage());
        }
    }

    public ArrayList<Calendario> pesquisarTudo() throws Exception {
        ArrayList<Calendario> listaEventos = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM Calendario";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Calendario calendario = new Calendario();
                calendario.setId(rs.getInt("id"));
                calendario.setUsuarioId(rs.getInt("usuarioId"));
                calendario.setEvento(rs.getString("evento"));
                calendario.setDataHora(rs.getDate("dataHora"));
                calendario.setLembrete(rs.getString("lembrete"));
                listaEventos.add(calendario);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar todos os eventos: " + e.getMessage());
        }
        return listaEventos;
    }

    public boolean deletar(Calendario calendario) throws Exception {
        try {
            abrirBanco();
            String query = "DELETE FROM Calendario WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, calendario.getId());
            int rowsAffected = pst.executeUpdate();
            fecharBanco();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Erro ao deletar evento: " + e.getMessage());
            throw new Exception("Erro ao deletar evento", e);
        }
    }

    public void alterar(Calendario calendario) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE Calendario SET usuarioId=?, evento=?, dataHora=?, lembrete=? WHERE id=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, calendario.getUsuarioId());
            pst.setString(2, calendario.getEvento());
            pst.setDate(3, new java.sql.Date(calendario.getDataHora().getTime()));
            pst.setString(4, calendario.getLembrete());
            pst.setInt(5, calendario.getId());
            pst.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao alterar evento: " + e.getMessage());
        }
    }

    public Calendario pesquisar(int id) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM Calendario WHERE id=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Calendario calendario = new Calendario();
                calendario.setId(rs.getInt("id"));
                calendario.setUsuarioId(rs.getInt("usuarioId"));
                calendario.setEvento(rs.getString("evento"));
                calendario.setDataHora(rs.getDate("dataHora"));
                calendario.setLembrete(rs.getString("lembrete"));
                fecharBanco();
                return calendario;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar evento: " + e.getMessage());
        }
        return null;
    }
}
