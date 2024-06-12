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
        <title>Relatórios e Estatísticas</title>
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mediaQueries/smarthpone.css">
        <style>
            /* Estilos específicos para a página de relatórios */
            .container {
                margin-top: 50px;
            }
            .card {
                margin-bottom: 20px;
            }
        </style>
    </head>
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

        <div class="container">
            <h1 class="text-center">Relatórios e Estatísticas de Estudo</h1>

            <!-- Card para mostrar tempo total de estudo -->
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Tempo Total de Estudo</h5>
                    <p class="card-text">Tempo total registrado de estudo: <span id="tempoTotal">${tempoTotalEstudo}</span></p>
                </div>
            </div>

            <!-- Card para mostrar datas de estudo -->
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Datas que Foram Estudadas</h5>
                    <ul class="list-group" id="datasEstudadas">
                        <!-- Iteração sobre as datas -->
                        <c:forEach var="data" items="${datasEstudadas}">
                            <li class="list-group-item">${fn:escapeXml(dataFormatada(data))}</li>
                            </c:forEach>
                    </ul>
                </div>
            </div>
        </div>

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
        <script>
            function dataFormatada(data) {
                var options = {year: 'numeric', month: '2-digit', day: '2-digit'};
                return new Date(data).toLocaleDateString('pt-BR', options);
            }
        </script>

    </body>
</html>
