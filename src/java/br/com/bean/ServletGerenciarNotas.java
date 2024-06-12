package br.com.bean;

import br.com.entidade.CadastroNotaDAO;
import br.com.model.Nota;
import br.com.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletGerenciarNotas", urlPatterns = {"/ServletGerenciarNotas"})
public class ServletGerenciarNotas extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("editar".equals(action)) {
            editarNota(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação desconhecida!");
        }
    }

    private void editarNota(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int disciplinaId = Integer.parseInt(request.getParameter("disciplinaId"));
            double novaNota = Double.parseDouble(request.getParameter("novaNota"));
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            int usuarioId = usuario.getId();

            CadastroNotaDAO cadastroNotaDAO = new CadastroNotaDAO();
            Nota notaExistente = cadastroNotaDAO.pesquisarPorUsuarioEDisciplina(usuarioId, disciplinaId);

            if (notaExistente != null) {
                notaExistente.setNota(novaNota);
                cadastroNotaDAO.alterar(notaExistente);

                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('Nota editada com sucesso!');");
                out.println("window.history.back();");
                out.println("</script>");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Nota não encontrada.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros inválidos.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao editar nota: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gerenciar notas.";
    }
}
