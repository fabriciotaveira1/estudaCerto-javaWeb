/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.model.Material;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CadastroMaterialDAO extends DAO {

    public void inserir(Material material) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO MateriaisDeAprendizagem (usuario_id, titulo, descricao, data_adicao) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, material.getUsuarioId());
            pst.setString(2, material.getTitulo());
            pst.setString(3, material.getDescricao());
            pst.setDate(4, new java.sql.Date(material.getDataAdicao().getTime()));
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao inserir material de aprendizagem: " + e.getMessage());
        }
    }

    public ArrayList<Material> pesquisarTudo() throws Exception {
        ArrayList<Material> listaMateriais = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM MateriaisDeAprendizagem";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Material material = new Material();
                material.setId(rs.getInt("id"));
                material.setTitulo(rs.getString("titulo"));
                material.setDescricao(rs.getString("descricao"));
                material.setDataAdicao(rs.getDate("data_adicao"));
                listaMateriais.add(material);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar todos os materiais de aprendizagem: " + e.getMessage());
        }
        return listaMateriais;
    }

    public boolean deletar(Material material) throws Exception {
        try {
            abrirBanco();
            String query = "DELETE FROM MateriaisDeAprendizagem WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, material.getId());
            int rowsAffected = pst.executeUpdate();
            fecharBanco();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Erro ao deletar material de aprendizagem: " + e.getMessage());
            throw new Exception("Erro ao deletar material de aprendizagem", e);
        }
    }

    public void alterar(Material material) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE MateriaisDeAprendizagem SET usuario_id=?, titulo=?, descricao=?, data_adicao=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, material.getUsuarioId());
            pst.setString(2, material.getTitulo());
            pst.setString(3, material.getDescricao());
            pst.setDate(4, new java.sql.Date(material.getDataAdicao().getTime()));
            pst.setInt(5, material.getId());
            pst.executeUpdate();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao alterar material de aprendizagem: " + e.getMessage());
        }
    }

    public Material pesquisar(int id) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM MateriaisDeAprendizagem WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Material material = new Material();
                material.setId(rs.getInt("id"));
                material.setUsuarioId(rs.getInt("usuario_id"));
                material.setTitulo(rs.getString("titulo"));
                material.setDescricao(rs.getString("descricao"));
                material.setDataAdicao(rs.getDate("data_adicao"));
                fecharBanco();
                return material;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar material de aprendizagem: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Material> buscarTodosMateriais() throws Exception {
        ArrayList<Material> materiais = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT id, usuario_id, titulo, descricao, data_adicao FROM MateriaisDeAprendizagem";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Material material = new Material();
                material.setId(rs.getInt("id"));
                material.setUsuarioId(rs.getInt("usuario_id"));
                material.setTitulo(rs.getString("titulo"));
                material.setDescricao(rs.getString("descricao"));
                material.setDataAdicao(rs.getDate("data_adicao"));
                materiais.add(material);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro ao buscar todos os materiais de aprendizagem: " + e.getMessage());
            throw new Exception("Erro ao buscar todos os materiais de aprendizagem", e);
        }
        return materiais;
    }
}
