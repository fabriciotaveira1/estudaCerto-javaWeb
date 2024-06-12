package br.com.bean;

import br.com.entidade.CadastroCronometroDAO;
import br.com.model.CronometroDeEstudos;
import br.com.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ServletCronometro", urlPatterns = {"/ServletCronometro"})
public class ServletCronometro extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");

        if ("salvarTempoGasto".equals(action)) {
            salvarTempoGasto(request, response);
        } else {
            enviarMensagem(response, "Ação desconhecida!");
        }
    }

    private void salvarTempoGasto(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        try {
            String atividade = request.getParameter("atividade");
            String tempoGastoStr = request.getParameter("tempo_gasto");
            int idUsuario = Integer.parseInt(request.getParameter("id"));

            Time tempoGasto = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date date = sdf.parse(tempoGastoStr);
                tempoGasto = new Time(date.getTime());
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException("Erro ao converter tempo gasto.", e);
            }

            Date dataAtual = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String data = dateFormat.format(dataAtual);

            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario == null) {
                response.sendRedirect("index.html");
            } else {
                // Cria um objeto CronometroDeEstudos com os dados
                CronometroDeEstudos cronometro = new CronometroDeEstudos();
                cronometro.setUsuarioId(idUsuario);
                cronometro.setAtividade(atividade);
                cronometro.setTempoGasto(tempoGasto);
                cronometro.setData(new java.sql.Date(dataAtual.getTime()));

                // Utiliza o DAO para inserir no banco de dados
                CadastroCronometroDAO dao = new CadastroCronometroDAO();
                dao.inserir(cronometro);

                // Mensagem de sucesso
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('Tempo gasto registrado com sucesso');");
                out.println("window.history.back();");
                out.println("</script>");
            }

        } catch (IOException | ServletException e) {
            enviarMensagem(response, "Erro ao salvar tempo gasto: " + e.getMessage());
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletCronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletCronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para registrar tempo gasto no cronômetro de estudos.";
    }
}
