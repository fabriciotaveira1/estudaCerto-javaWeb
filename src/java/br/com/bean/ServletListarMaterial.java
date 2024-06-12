/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.bean;

import br.com.entidade.CadastroMaterialDAO;
import br.com.model.Material;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author victo
 */
@WebServlet(name = "ServletListarMaterial", urlPatterns = {"/ServletListarMaterial"})
public class ServletListarMaterial extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                // Aqui você deve obter o id do usuário logado, supondo que esteja disponível na sessão
                int usuarioId = 1; // Substitua 1 pelo id do usuário logado

                // Instanciando o DAO para acessar o banco de dados
                CadastroMaterialDAO dao = new CadastroMaterialDAO();

                // Buscando a lista de materiais do usuário logado
                ArrayList<Material> materiais = dao.pesquisarTudo();

                // Definindo o atributo "materiais" na requisição
                request.setAttribute("materiais", materiais);

                // Encaminhando a requisição para a página material.jsp
                request.getRequestDispatcher("html/sessoes/usuario/material.jsp").forward(request, response);
            } catch (Exception e) {
                // Em caso de erro, redireciona para uma página de erro ou imprime o erro no console
                e.printStackTrace();
                response.sendRedirect("erro.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}