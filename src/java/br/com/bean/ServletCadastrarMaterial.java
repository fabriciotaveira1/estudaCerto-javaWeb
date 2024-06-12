package br.com.bean;

import br.com.entidade.CadastroMaterialDAO;
import br.com.model.Material;
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
import java.util.Date;

@WebServlet(name = "ServletCadastrarMaterial", urlPatterns = {"/ServletCadastrarMaterial"})
public class ServletCadastrarMaterial extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        if ("adicionar".equals(action)) {
            adicionarMaterial(request, response);
        } else if ("editar".equals(action)) {
            editarMaterial(request, response);
        } else if ("excluir".equals(action)) {
            excluirMaterial(request, response);
        } else {
            enviarMensagem(response, "Ação desconhecida!");
        }
    }

    private void adicionarMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Obtém os parâmetros do formulário
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");

            HttpSession session = request.getSession();
            // Recupera o atributo da sessão
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario == null) {
                response.sendRedirect("index.html");
            } else {
                // O usuário está na sessão, podemos prosseguir com a criação do material de aprendizagem
                Material material = new Material();

                Date dataAtual = new Date();
                material.setDataAdicao(dataAtual);
                material.setUsuarioId(usuario.getId()); // Define o ID do usuário no material de aprendizagem
                material.setTitulo(titulo);
                material.setDescricao(descricao);

                // Chama o método do DAO para inserir o material de aprendizagem
                CadastroMaterialDAO dao = new CadastroMaterialDAO();
                dao.inserir(material);

                // Envia uma mensagem de sucesso como parte da resposta da servlet
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('Material de aprendizagem adicionado com sucesso');");
                out.println("history.back();");
                out.println("window.location.reload();"); // Adiciona o refresh da página após a alteração
                out.println("</script>");
            }

        } catch (Exception e) {
            // Envia uma mensagem de erro
            enviarMensagem(response, "Erro ao adicionar material de aprendizagem: " + e.getMessage());
        }
    }

    private void editarMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Obtém os parâmetros do formulário
            int id = Integer.parseInt(request.getParameter("materialId"));
            String titulo = request.getParameter("tituloEditar");
            String descricao = request.getParameter("descricaoEditar");

            // Cria um objeto Material
            Material material = new Material();
            Date dataAtual = new Date();
            material.setDataAdicao(dataAtual);
            material.setId(id);
            material.setTitulo(titulo);
            material.setDescricao(descricao);

            // Chama o método do DAO para editar o material de aprendizagem
            CadastroMaterialDAO dao = new CadastroMaterialDAO();
            dao.alterar(material);

            // Envia uma mensagem de sucesso como parte da resposta da servlet
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('Material de aprendizagem alterado com sucesso');");
            out.println("window.history.back();");
            out.println("</script>");

        } catch (NumberFormatException e) {
            // Se ocorrer um erro ao converter os parâmetros
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros inválidos.");
        } catch (Exception e) {
            // Se ocorrer outro erro durante a edição do material de aprendizagem
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao editar material de aprendizagem: " + e.getMessage());
        }
    }

    private void excluirMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Obtém o parâmetro do formulário
            int id = Integer.parseInt(request.getParameter("id"));

            // Cria um objeto Material
            Material material = new Material();
            material.setId(id);

            // Chama o método do DAO para excluir o material de aprendizagem
            CadastroMaterialDAO dao = new CadastroMaterialDAO();
            dao.deletar(material);

            // Envia uma mensagem de sucesso e redireciona para a página principal ou de listagem
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('Material de aprendizagem excluído com sucesso');");
            out.println("history.back();");
            out.println("</script>");

        } catch (Exception e) {
            // Envia uma mensagem de erro
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('Erro ao excluir material de aprendizagem: " + e.getMessage() + "');");
            out.println("history.back();");
            out.println("</script>");
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
        return "Servlet para cadastrar materiais de aprendizagem.";
    }
}
