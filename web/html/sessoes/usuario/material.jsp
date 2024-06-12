<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="br.com.bean.ServletLogin" %>
<%@ page import="br.com.entidade.CadastroMaterialDAO" %>
<%@ page import="br.com.model.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Material de Aprendizagem</title>
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mediaQueries/smarthpone.css">
    </head>

    <body>
        <!-- ---------- PAGE ---------- -->
        <div id="page" class="flex-column ">
            <header>
                <nav class="navbar navbar-expand-lg bg-primary">
                    <div class="container-fluid p-2 fontNature">
                        <a href="#" class="navbar-brand text-light">Estude Certo</a>
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0" id="ulRight">
                            <button class="btn" type="button" id="btnPage"><img src="../assets/background/icon1.svg" alt=""></button>
                            <li class="nav-item disabled"><a href="${pageContext.request.contextPath}/index.html" class="nav-link text-light">Pagina
                                    Inicial</a></li>
                            <li class="nav-item disabled"><a href="sessaoUsuarioDisciplina.jsp"
                                                             class="nav-link text-light ">Alterar Disciplina</a></li>
                            <li class="nav-item disabled"><a href="cronometro.jsp" class="nav-link text-light ">Cronometro de Estudos</a>
                            </li>
                            <li class="nav-item disabled"><a href="material.jsp"
                                                             class="nav-link text-light ">Material</a></li>
                        </ul>
                        <ul class="navbar-nav mb-2 mb-lg-0 disabled">
                            <li class="nav-item"><a href="${pageContext.request.contextPath}/html/sessoes/usuario/profile.jsp" class="nav-link text-light">Meu Perfil</a></li>
                            <li class="nav-item"><a href="${pageContext.request.contextPath}/index.html" class="nav-link text-light">Sair</a></li>
                        </ul>
                    </div>
                </nav>
            </header>

            <%
            // Recupere o objeto usuario da sessão
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (usuario == null) {
                // Redireciona para a página de índice se o usuário não estiver na sessão
                response.sendRedirect(request.getContextPath() + "/index.html");
                return;
            }
        
            CadastroMaterialDAO cadastroDao = new CadastroMaterialDAO(); 
            %>

            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                <div class="container">
                    <a class="navbar-brand" href="#">Gerenciamento de Materiais de Aprendizagem</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="#" onclick="mostrarAdicionarMaterial()">Adicionar Material</a>
                            </li>
                        
                            <li class="nav-item">
                                <a class="nav-link" href="#" onclick="mostrarExcluirMaterial()">Excluir Material</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="#" onclick="mostrarVerMateriais()">Ver Materiais</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- ---------- HEADER ---------- -->

            <!-- ---------- MAIN ---------- -->
            <main>
                <div class="container mt-4" id="adicionarMaterial">
                    <h2>Adicionar Material de Aprendizagem</h2>
                    <form action="${pageContext.request.contextPath}/ServletCadastrarMaterial" method="post" onsubmit="return enviarFormulario(this)">
                        <input type="hidden" name="action" value="adicionar">
                        <div class="mb-3">
                            <label for="usuario_id" class="form-label"></label>
                            <span type="number" id="usuario_id" name="usuario_id" style="display:none;"><%= usuario.getId() %></span>
                        </div>
                        <div class="mb-3">
                            <label for="titulo" class="form-label">Título</label>
                            <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Digite o título do material">
                        </div>
                        <div class="mb-3">
                            <label for="descricao" class="form-label">Descrição</label>
                            <textarea class="form-control" id="descricao" name="descricao" rows="3" placeholder="Digite uma descrição do material"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Adicionar Material</button>
                    </form>
                </div>

             

                <div class="container mt-4" id="excluirMaterial" style="display: none;">
                    <h2>Excluir Material de Aprendizagem</h2>
                    <form action="${pageContext.request.contextPath}/ServletCadastrarMaterial" method="post" onsubmit="return enviarFormulario(this)">
                        <input type="hidden" name="action" value="excluir">
                        <div class="mb-3">
                            <label for="idExcluir" class="form-label">ID do Material</label>
                            <input type="number" class="form-control" id="idExcluir" name="id" placeholder="Digite o ID do material">
                        </div>
                        <button type="submit" class="btn btn-danger">Excluir Material</button>
                    </form>
                </div>

                <div class="container mt-4" id="verMateriais">
                    <h2>Materiais de Aprendizagem</h2>
                    <!-- Aqui você adicionará o conteúdo dinâmico para exibir os materiais -->
                    <div id="materiaisConteudo">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Título</th>
                                    <th>Descrição</th>
                                    <th>Data de Adição</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Conteúdo dos materiais será carregado aqui -->
                                <c:forEach var="material" items="${materiais}">
                                    <tr>
                                        <td>${material.id}</td>
                                        <td>${material.titulo}</td>
                                        <td>${material.descricao}</td>
                                        <td>${material.dataAdicao}</td>
                                        <td>
                                            <button type="button" class="btn btn-primary" onclick="abrirModalEditar(${material.id})">Editar</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </main>
            <!-- ---------- MAIN ---------- -->


            <!-- Modal para editar material -->
            <div class="modal fade" id="modalEditarMaterial" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Editar Material</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Formulário para editar material -->
                            <form id="formEditarMaterial" action="${pageContext.request.contextPath}/ServletCadastrarMaterial" method="post">
                                <input type="hidden" name="action" value="editar"> <!-- Valor da ação é "editar" -->

                                <div class="mb-3">
                                    <label for="materialId" class="form-label">Id Material</label>
                                    <input type="text" class="form-control" id="materialId" name="materialId" value="${material.id}">${material.id}
                                </div>


                                <div class="mb-3">
                                    <label for="tituloEditar" class="form-label">Título</label>
                                    <input type="text" class="form-control" id="tituloEditar" name="tituloEditar" placeholder="Digite o título do material">
                                </div>
                                <div class="mb-3">
                                    <label for="descricaoEditar" class="form-label">Descrição</label>
                                    <textarea class="form-control" id="descricaoEditar" name="descricaoEditar" rows="3" placeholder="Digite uma descrição do material"></textarea>
                                </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary">Salvar</button>
                        </div>
                        </form>
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
        </div>
        <!-- ---------- PAGE ---------- -->

        <script src="${pageContext.request.contextPath}/js/code.jquery.com_jquery-3.7.1.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/js/index.js"></script>
        <script src="${pageContext.request.contextPath}/js/usuario/material.js"></script>
        <script>
                                // Função para abrir o modal de exclusão com o ID correto
                                function abrirModalExcluir(id) {
                                    document.getElementById('idExcluir').value = id;
                                    var modalExcluir = new bootstrap.Modal(document.getElementById('modalExcluirMaterial'));
                                    modalExcluir.show();
                                }
        </script>
    </body>

</html>
