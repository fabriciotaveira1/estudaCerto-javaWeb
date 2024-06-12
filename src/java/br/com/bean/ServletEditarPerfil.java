package br.com.bean;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import br.com.model.Usuario; // Certifique-se de que a importação esteja correta
import br.com.entidade.CadastroUsuarioDAO; // Certifique-se de que a importação esteja correta
import br.com.services.SegurancaUtils; // Certifique-se de que a importação esteja correta
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ServletEditarPerfil", urlPatterns = {"/ServletEditarPerfil"})
public class ServletEditarPerfil extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Atualize as informações do usuário
        usuario.setNome(nome);
        usuario.setEmail(email);
        if (senha != null && !senha.isEmpty()) {
            String senhaHash = SegurancaUtils.criptografarSenha(senha); // Usando método de hash existente
            usuario.setSenhaHash(senhaHash);
        }

        // Salve as mudanças no banco de dados
        CadastroUsuarioDAO usuarioDAO = new CadastroUsuarioDAO();
        usuarioDAO.alterar(usuario);

        // Atualize o objeto de usuário na sessão
        session.setAttribute("usuario", usuario);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('Usuário alterado com sucesso');");
        out.println("history.back();");
        out.println("window.location.reload();"); // Adiciona o refresh da página após a alteração
        out.println("</script>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletEditarPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletEditarPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
