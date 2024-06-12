package br.com.bean;

import br.com.model.Disciplina;
import br.com.entidade.CadastroDisciplinaDAO;
import br.com.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;

@WebServlet(name = "ServletGerenciarDisciplinas", urlPatterns = {"/ServletGerenciarDisciplinas"})
public class ServletGerenciarDisciplinas extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("adicionar".equals(action)) {
            adicionarDisciplina(request, response);
        } else if ("editar".equals(action)) {
            editarDisciplina(request, response);
        } else if ("excluir".equals(action)) {
            excluirDisciplina(request, response);
        } else {
            enviarMensagem(response, "Ação desconhecida!");
        }
    }

    private void adicionarDisciplina(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Obtém os parâmetros do formulário

            String nome = request.getParameter("nome");
            int cargaHoraria = Integer.parseInt(request.getParameter("cargaHoraria"));
            String professor = request.getParameter("professor");
            String descricao = request.getParameter("descricao");

            HttpSession session = request.getSession();
            // Recupera o atributo da sessão
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario == null) {
                response.sendRedirect("index.html");
            } else {
                // O usuário está na sessão, podemos prosseguir com a criação da disciplina
                Disciplina disciplina = new Disciplina();
                disciplina.setUsuarioId(usuario.getId()); // Define o ID do usuário na disciplina
                disciplina.setNome(nome);
                disciplina.setCargaHoraria(cargaHoraria);
                disciplina.setProfessor(professor);
                disciplina.setDescricao(descricao);
                // Chama o método do DAO para inserir a disciplina
                CadastroDisciplinaDAO dao = new CadastroDisciplinaDAO();
                dao.inserir(disciplina);

                // Envia uma mensagem de sucesso como parte da resposta da servlet
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('Disciplina adicionada com sucesso');");
                out.println("history.back();");
                out.println("window.location.reload();"); // Adiciona o refresh da página após a alteração
                out.println("</script>");
            }

        } catch (Exception e) {
            // Envia uma mensagem de erro
            enviarMensagem(response, "Erro ao adicionar disciplina: " + e.getMessage());
        }
    }

    private void editarDisciplina(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Obtém os parâmetros do formulário
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            int cargaHoraria = Integer.parseInt(request.getParameter("cargaHoraria"));
            String professor = request.getParameter("professor");
            String descricao = request.getParameter("descricao");

            // Cria um objeto Disciplina
            Disciplina disciplina = new Disciplina();
            disciplina.setId(id);
            disciplina.setNome(nome);
            disciplina.setCargaHoraria(cargaHoraria);
            disciplina.setProfessor(professor);
            disciplina.setDescricao(descricao);

            // Chama o método do DAO para editar a disciplina
            CadastroDisciplinaDAO dao = new CadastroDisciplinaDAO();
            dao.alterar(disciplina);

            // Envia uma mensagem de sucesso como parte da resposta da servlet
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('Disciplina alterada com sucesso');");
            out.println("window.location.href = 'html/sessoes/usuario/sessoesUsuarioDisciplina.jsp';"); // Redireciona para a mesma página
            out.println("</script>");

        } catch (NumberFormatException e) {
            // Se ocorrer um erro ao converter os parâmetros para inteiros
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros inválidos.");
        } catch (Exception e) {
            // Se ocorrer outro erro durante a edição da disciplina
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao editar disciplina: " + e.getMessage());
        }
    }

    private void excluirDisciplina(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Obtém o parâmetro do formulário
            int id = Integer.parseInt(request.getParameter("id"));

            // Cria um objeto Disciplina
            Disciplina disciplina = new Disciplina();
            disciplina.setId(id);

            // Chama o método do DAO para excluir a disciplina
            CadastroDisciplinaDAO dao = new CadastroDisciplinaDAO();
            dao.deletar(disciplina);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('Disciplina excluída com sucesso');");
            out.println("history.back();");
            out.println("window.location.reload();"); // Adiciona o refresh da página após a alteração
            out.println("</script>");

        } catch (Exception e) {
            // Envia uma mensagem de erro
            enviarMensagem(response, "Erro ao excluir disciplina: " + e.getMessage());
        }
    }

    private void enviarMensagem(HttpServletResponse response, String mensagem) throws IOException {
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            out.write(mensagem);
            out.flush();
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
        return "Servlet para gerenciar disciplinas.";
    }
}
