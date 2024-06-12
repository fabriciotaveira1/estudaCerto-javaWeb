package br.com.bean;

import br.com.entidade.CadastroDisciplinaDAO;
import br.com.entidade.CadastroMaterialDAO;
import br.com.entidade.CadastroNotaDAO;
import br.com.entidade.CadastroUsuarioDAO;
import br.com.model.Disciplina;
import br.com.model.Material;
import br.com.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            CadastroUsuarioDAO dao = new CadastroUsuarioDAO();

            try {
                Usuario usuario = dao.autenticar(email, senha);
                request.getSession().setAttribute("usuario", usuario);
                if (usuario != null) {
                    if (usuario.getTentativasLoginIncorretas() < 2) {
                        // Autenticação bem-sucedida, armazena o objeto usuario na sessão
                        CadastroDisciplinaDAO daoDisciplina = new CadastroDisciplinaDAO();
                        CadastroMaterialDAO daoMaterial = new CadastroMaterialDAO();          
                        
                        ArrayList<Material> materiais = daoMaterial.pesquisarTudo();
                        ArrayList<Disciplina> disciplinas = daoDisciplina.pesquisarTudo();
                        
                        // Envia as disciplinas como parte do request
                        request.setAttribute("disciplinas", disciplinas);
                        
                        // Envia as materiais como parte do request
                        request.setAttribute("materiais", materiais);
                      
                        // Define o atributo "usuario" na sessão
                        

                        // Redireciona para a página de sessão do usuário
                        request.getRequestDispatcher("html/sessoes/usuario/sessaoUsuarioDisciplina.jsp").forward(request, response);
                    } else {
                        // Suspender temporariamente a conta do usuário
                        dao.suspenderConta(usuario.getId());
                        request.setAttribute("mensagemErro", "Sua conta foi temporariamente suspensa devido a múltiplas tentativas de login com senha incorreta.");
                        request.getRequestDispatcher("html/signIn.jsp").forward(request, response);
                    }
                } else {
                    // Credenciais inválidas, aumentar o número de tentativas de login incorretas
                    dao.incrementarTentativasLoginIncorretas(email);
                    request.setAttribute("mensagemErro", "CPF ou senha inválidos");
                    request.getRequestDispatcher("html/signIn.jsp").forward(request, response);
                }
            } catch (Exception e) {
                // Se ocorrer uma exceção durante a autenticação, imprime o erro e redireciona para uma página de erro
                e.printStackTrace();
                request.setAttribute("mensagemErro", "Erro durante a autenticação");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
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
        return "Short description";
    }

}
