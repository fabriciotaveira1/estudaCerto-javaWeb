package br.com.bean;

import br.com.entidade.CadastroUsuarioDAO;
import br.com.model.Usuario;
import br.com.services.SegurancaUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ServletCadastrarUsuario", urlPatterns = {"/ServletCadastrarUsuario"})
public class ServletCadastrarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            
            String nome = request.getParameter("name");
            String email = request.getParameter("email");
            String senha = request.getParameter("password");
            String tipoUsuario = request.getParameter("tipo_usuario");

            // Validação da senha no lado do cliente
            if (!senhaSegura(senha)) {
                out.println("<script>alert('A senha fornecida não atende aos critérios de segurança. Por favor, escolha uma senha mais forte.');");
                out.println("history.back();</script>");
                return;
            }

            // Validar se o e-mail é único usando o método emailUnico do DAO
            CadastroUsuarioDAO dao = new CadastroUsuarioDAO();
            if (!dao.emailUnico(email)) {
                out.println("<script>alert('O e-mail fornecido já está em uso. Por favor, escolha outro e-mail.');");
                out.println("history.back();</script>");
                return;
            }

            try {
                String senhaHash = SegurancaUtils.criptografarSenha(senha); // Converter a senha para hash
                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenhaHash(senhaHash); // Definir senha como hash
                usuario.setTipoUsuario(tipoUsuario);
                dao.inserir(usuario);
                out.println("<script>alert('Usuário cadastrado com sucesso!');</script>");
                response.setHeader("Refresh", "1; URL=index.html");// Redirecionar para a página de login após o cadastro
            } catch (Exception e) {
                out.println("<script>alert('Erro ao cadastrar usuário: " + e.getMessage() + "');");
                out.println("history.back();</script>");
            }
        } catch (Exception ex) {
            Logger.getLogger(ServletCadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para validar a segurança da senha no lado do cliente
    private boolean senhaSegura(String senha) {
        // Verifica se a senha tem pelo menos 8 caracteres
        if (senha.length() < 8) {
            return false;
        }

        // Verifica se a senha contém pelo menos uma letra maiúscula
        if (!senha.matches(".*[A-Z].*")) {
            return false;
        }

        // Verifica se a senha contém pelo menos uma letra minúscula
        if (!senha.matches(".*[a-z].*")) {
            return false;
        }

        // Verifica se a senha contém pelo menos um número
        if (!senha.matches(".*\\d.*")) {
            return false;
        }

        return true; // Se a senha atender a todos os critérios de segurança, retorna true
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
        return "Short description";
    }
}
