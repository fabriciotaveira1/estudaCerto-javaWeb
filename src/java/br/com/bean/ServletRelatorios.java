package br.com.bean;

import br.com.entidade.CadastroCronometroDAO;
import br.com.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class ServletRelatorios
 */
@WebServlet(name = "ServletRelatorios", urlPatterns = {"/ServletRelatorios"})
public class ServletRelatorios extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRelatorios() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            throws ServletException, IOException, Exception {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario == null) {
                response.sendRedirect(request.getContextPath() + "/index.html");
                return;
            }

            try {
                CadastroCronometroDAO dao = new CadastroCronometroDAO();

                // Obtendo o tempo total de estudo do usuário
                String tempoTotalEstudo = dao.calcularTempoTotalEstudo(usuario.getId());

                // Obtendo as datas que foram estudadas pelo usuário
                List<Date> datasEstudadas = dao.listarDatasEstudadas(usuario.getId());

                // Definindo os atributos para a página JSP
                request.setAttribute("tempoTotalEstudo", tempoTotalEstudo);
                request.setAttribute("datasEstudadas", datasEstudadas);

                // Encaminhando a requisição para a página relatorios.jsp
                request.getRequestDispatcher("/html/sessoes/usuario/relatorios.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace(); // Pode ser melhorado com um logger
                response.sendRedirect(request.getContextPath() + "/erro.jsp");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para fornecer dados de relatórios e estatísticas de estudo do usuário.";
    }
}
