<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="br.com.bean.ServletLogin" %>
<%@ page import="br.com.model.Usuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gerenciamento de Disciplinas</title>
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mediaQueries/smarthpone.css">

    </head>
    <div id="page" class="flex-column ">
        <header>
            <nav class="navbar navbar-expand-lg bg-primary">
                <div class="container-fluid p-2 fontNature">
                    <a href="#" class="navbar-brand text-light">Estude Certo</a>
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0" id="ulRight">
                        <button class="btn" type="button" id="btnPage"><img src="../assets/background/icon1.svg" alt=""></button>
                        <li class="nav-item disabled"><a href="${pageContext.request.contextPath}/index.html" class="nav-link text-light">Pagina
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
            <!-- Navegação e cabeçalho... -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                <div class="container">
                    <a class="navbar-brand" href="#">Gerenciamento de Disciplinas</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="#" onclick="mostrarAdicionarDisciplina()">Adicionar Disciplina</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" onclick="mostrarEditarDisciplina()">Editar Disciplina</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" onclick="mostrarExcluirDisciplina()">Excluir Disciplina</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="#" onclick="mostrarVerDisciplinas()">Ver Disciplinas</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#" onclick="mostrarVerNotas()">Ver Notas</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <main>
                <!-- Conteúdo da página -->
                <div class="container mt-4" id="adicionarDisciplina">
                    <h2 class="text-center">Adicionar Disciplina</h2>
                    <form id="addDisciplineForm" action="${pageContext.request.contextPath}/ServletGerenciarDisciplinas" method="post" onsubmit="return enviarFormulario(this)">
                        <input type="hidden" name="action" value="adicionar">
                        <div class="mb-3">
                            <label for="usuario_id" class="form-label"></label>
                            <span type="number" id="usuario_id" name="usuario_id" style="display:none;"><%= usuario.getId()%></span>
                        </div>
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome da Disciplina</label>
                            <input type="text" class="form-control" id="nome" name="nome" placeholder="Digite o nome da disciplina">
                        </div>
                        <div class="mb-3">
                            <label for="cargaHoraria" class="form-label">Carga Horária</label>
                            <input type="number" class="form-control" id="cargaHoraria" name="cargaHoraria" placeholder="Digite a carga horária da disciplina">
                        </div>
                        <div class="mb-3">
                            <label for="professor" class="form-label">Professor Responsável</label>
                            <input type="text" class="form-control" id="professor" name="professor" placeholder="Digite o nome do professor responsável">
                        </div>
                        <div class="mb-3">
                            <label for="descricao" class="form-label">Descrição Breve</label>
                            <textarea class="form-control" id="descricao" name="descricao" rows="3" placeholder="Digite uma descrição breve da disciplina"></textarea>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary">Adicionar Disciplina</button>
                        </div>
                    </form>
                </div>

                <div class="container mt-4" id="editarDisciplina" style="display: none;">
                    <h2>Editar Disciplina</h2>
                    <form id="editDisciplineForm" action="${pageContext.request.contextPath}/ServletGerenciarDisciplinas" method="post" onsubmit="return enviarFormulario(this)">
                        <input type="hidden" name="action" value="editar">
                        <div class="mb-3">
                            <label for="id" class="form-label">ID da Disciplina</label>
                            <input type="number" class="form-control" id="id" name="id" placeholder="Digite o ID da disciplina">
                        </div>
                        <div class="mb-3">
                            <label for="usuario_id" class="form-label"></label>
                            <span type="number" id="usuario_id" name="usuario_id" style="display:none;"><%= usuario.getId()%></span>
                        </div>
                        <div class="mb-3">
                            <label for="nomeEditar" class="form-label">Nome da Disciplina</label>
                            <input type="text" class="form-control" id="nomeEditar" name="nome" placeholder="Digite o nome da disciplina">
                        </div>
                        <div class="mb-3">
                            <label for="cargaHorariaEditar" class="form-label">Carga Horária</label>
                            <input type="number" class="form-control" id="cargaHorariaEditar" name="cargaHoraria" placeholder="Digite a carga horária da disciplina">
                        </div>
                        <div class="mb-3">
                            <label for="professorEditar" class="form-label">Professor Responsável</label>
                            <input type="text" class="form-control" id="professorEditar" name="professor" placeholder="Digite o nome do professor responsável">
                        </div>
                        <div class="mb-3">
                            <label for="descricaoEditar" class="form-label">Descrição Breve</label>
                            <textarea class="form-control" id="descricaoEditar" name="descricao" rows="3" placeholder="Digite uma descrição breve da disciplina"></textarea>
                        </div>

                        <div class="mb-3">
                            <label for="notaEditar" class="form-label">Nota</label>
                            <textarea class="form-control" id="notaEditar" name="nota" rows="3" placeholder="Digite uma descrição breve da disciplina"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Editar Disciplina</button>
                    </form>
                </div>

                <div class="container mt-4" id="excluirDisciplina" style="display: none;">
                    <h2>Excluir Disciplina</h2>
                    <form id="DeleteDisciplineForm" action="${pageContext.request.contextPath}/ServletGerenciarDisciplinas" method="post" onsubmit="return enviarFormulario(this)">
                        <input type="hidden" name="action" value="excluir">
                        <div class="mb-3">
                            <label for="idExcluir" class="form-label">ID da Disciplina</label>
                            <input type="number" class="form-control" id="idExcluir" name="id" placeholder="Digite o ID da disciplina">
                        </div>
                        <button type="submit" class="btn btn-danger">Excluir Disciplina</button>
                    </form>
                </div>

                <div class="container mt-4" id="verDisciplinas">
                    <h2 class="text-center">Disciplinas</h2>
                    <br>
                    <div class="d-flex justify-content-center">
                        <button onclick="window.location.reload();" type="submit" class="btn btn-primary">Atualizar Disciplinas</button>
                    </div>
                    <br>
                    <!-- Aqui você adicionará o conteúdo dinâmico para exibir as disciplinas -->
                    <div id="disciplinasConteudo">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nome da Disciplina</th>
                                    <th>Carga Horária</th>
                                    <th>Nota</th>
                                    <th>Ações</th>

                                </tr>
                            </thead>
                            <tbody>
                                <!-- Conteúdo das disciplinas será carregado aqui -->
                                <c:forEach var="disciplina" items="${disciplinas}">
                                    <tr>
                                        <td>${disciplina.id}</td>
                                        <td>${disciplina.nome}</td>
                                        <td>${disciplina.cargaHoraria}</td>
                                        <td>${disciplina.nota}</td>
                                        <td>                                            
                                            <button type="button" class="btn btn-primary" onclick="abrirModalEditarNota(${disciplina.id})">Editar Nota</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>

            <!-- Modal para Editar Nota -->
            <div class="modal fade" id="editarNotaModal" tabindex="-1" aria-labelledby="editarNotaLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editarNotaLabel">Editar Nota</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form id="editarNotaForm" method="post" action="${pageContext.request.contextPath}/ServletGerenciarDisciplinas">
                            <input type="hidden" name="action" value="editarNota"> <!-- Adicionando ação para editar nota -->
                            <div class="modal-body">
                                <input type="hidden" id="editar-nota-disciplina-id" name="id" value="${disciplina.id}">
                                <div class="mb-3">
                                    <label for="novaNota" class="form-label">Nova Nota</label>
                                    <input type="number" step="0.01" class="form-control" id="novaNota" name="novaNota" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                                <button type="submit" class="btn btn-primary">Salvar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>


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
    </div>



    <!-- JavaScript Bundle with Popper -->
    <script src="${pageContext.request.contextPath}/js/usuario/usuario.js"></script>
    <script src="${pageContext.request.contextPath}/js/code.jquery.com_jquery-3.7.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>

</html>
