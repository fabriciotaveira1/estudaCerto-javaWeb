<%@ page import="br.com.model.Usuario" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="br.com.bean.ServletLogin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cronômetro de Estudos</title>
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mediaQueries/smarthpone.css">
        <style>
            /* Estilos específicos para o cronômetro */
            .cronometro-container {
                text-align: center;
                margin-top: 50px;
            }

            .cronometro {
                font-size: 3em;
                margin-bottom: 20px;
            }

            .acoes-cronometro {
                margin-top: 20px;
            }

            .notas-container {
                margin-top: 30px;
                padding: 20px;
                background-color: #f9f9f9;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .notas-container textarea {
                width: 100%;
                height: 100px;
                padding: 10px;
                margin-top: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                resize: vertical;
            }
        </style>
    </head>
    <%
        // Recupere o objeto usuario da sessão
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            // Redireciona para a página de índice se o usuário não estiver na sessão
            response.sendRedirect(request.getContextPath() + "/index.html");
            return;
        }
    %>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg bg-primary">
                <div class="container-fluid p-2 fontNature">
                    <a href="#" class="navbar-brand text-light">Estude Certo</a>
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0" id="ulRight">
                        <button class="btn" type="button" id="btnPage"><img src="../assets/background/icon1.svg" alt=""></button>
                        <li class="nav-item disabled"><a href="${pageContext.request.contextPath}/index.html" class="nav-link text-light">Página
                                Inicial</a></li>
                        <li class="nav-item disabled"><a href="${pageContext.request.contextPath}/ServletListarDisciplina"
                                                         class="nav-link text-light ">Alterar Disciplina</a></li>
                        <li class="nav-item disabled"><a href="${pageContext.request.contextPath}/ServletCronometroPage" class="nav-link text-light ">Cronômetro de Estudos</a>
                        </li>
                        <li class="nav-item disabled"><a href="${pageContext.request.contextPath}/ServletListarMaterial"
                                                         class="nav-link text-light ">Material de Aprendizagem</a></li>
                        <li class="nav-item"><a href="${pageContext.request.contextPath}/ServletRelatorios" class="nav-link text-light active">Relatórios e Estatísticas</a></li>
                    </ul>
                    <ul class="navbar-nav mb-2 mb-lg-0 disabled">
                        <li class="nav-item"><a href="${pageContext.request.contextPath}/html/sessoes/usuario/profile.jsp" class="nav-link text-light">Meu Perfil</a></li>
                        <li class="nav-item"><a href="${pageContext.request.contextPath}/index.html" class="nav-link text-light">Sair</a></li>
                    </ul>
                </div>
            </nav>
        </header>

        <div class="cronometro-container">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>Atenção!</strong> Para salvar o tempo gasto, certifique-se de pausar o cronômetro antes de clicar em "Salvar Tempo Gasto".
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <h1 class="text-center">Cronômetro de Estudos</h1>
            <div class="cronometro" id="cronometro">00:00:00</div>
            <div class="acoes-cronometro">
                <!-- Botões para iniciar, pausar e zerar o cronômetro (implementados via JavaScript) -->
                <button id="btnIniciar" class="btn btn-primary">Iniciar</button>
                <button id="btnPausar" class="btn btn-warning">Pausar</button>
                <button id="btnZerar" class="btn btn-danger">Zerar</button>
            </div>
        </div>
        <div class="notas-container">
            <h2 class="text-center">Notas sobre o Estudo</h2>
            <textarea id="notas" class="form-control" rows="4" placeholder="Adicione suas notas aqui..."></textarea>
        </div>
        <!-- Formulário para enviar tempo gasto e atividade -->
        <form id="formCronometro" action="${pageContext.request.contextPath}/ServletCronometro" method="post">
            <input type="hidden" name="action" value="salvarTempoGasto">
            <input type="hidden" id="id" name="id" value="${usuario.id}">
            <input type="hidden" id="tempo_gasto" name="tempo_gasto" value="00:00:00"> <!-- Valor do tempo gasto atualizado via JavaScript -->

            <input type="text" class="form-control" id="atividade" name="atividade" placeholder="Atividade realizada" required>
            <br>
            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-success">Salvar Tempo Gasto</button>
            </div>

        </form>
    </main>

    <!-- ---------- FOOTER ---------- -->
    <footer class="bg-primary text-white mt-4">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h5>Sobre Nós</h5>
                    <p>Informações sobre a empresa...</p>
                </div>
                <div class="col-md-6">
                    <h5>Contato</h5>
                    <ul class="list-unstyled">
                        <li><i class="fas fa-phone"></i> Telefone: (11) 1234-5678</li>
                        <li><i class="fas fa-envelope"></i> Email: contato@estudacerto.com</li>
                    </ul>
                </div>
            </div>
            <div class="text-center mt-4">
                &copy; 2024 EstudaCerto. Todos os direitos reservados.
            </div>
        </div>
    </footer>
    <!-- ---------- FOOTER ---------- -->

    <!-- Scripts JavaScript -->
    <script src="${pageContext.request.contextPath}/js/code.jquery.com_jquery-3.7.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <script src="${pageContext.request.contextPath}/js/usuario/cronometro.js"></script>
    <script>
        function mostrarRelatorios() {
            document.getElementById('relatorios').style.display = 'block';
        }
    </script>

</body>

</html>
