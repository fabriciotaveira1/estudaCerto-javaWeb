<%@ page import="br.com.model.Usuario" %>
<%@ page import="br.com.bean.ServletLogin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
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
                    <li class="nav-item disabled"><a href="sessaoUsuarioDisciplina.jsp"
                                                     class="nav-link text-light ">Alterar Disciplina</a></li>
                    <li class="nav-item disabled"><a href="cronometro.jsp" class="nav-link text-light ">Cronometro de Estudos</a>
                    </li>
                    <li class="nav-item disabled"><a href="comunity.html"
                                                     class="nav-link text-light ">Comunidade</a></li>
                </ul>
                <ul class="navbar-nav mb-2 mb-lg-0 disabled">
                    <li class="nav-item"><a href="${pageContext.request.contextPath}/html/sessoes/usuario/profile.jsp" class="nav-link text-light">Meu Perfil</a></li>
                    <li class="nav-item"><a href="${pageContext.request.contextPath}/index.html" class="nav-link text-light">Sair</a></li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- ---------- MAIN ---------- -->
    <main>
        <div class="container-fluid my-2 text-success">
            <h1 class="text-uppercase text-center text-black fontNature" id="mainH1">Meu Perfil</h1>
        </div>
        <section class="container-fluid my-3">
            <div class="container-fluid my-3" id="profile">
                <div class="container m-3 perfil">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="perfil-header">
                                <img src="${pageContext.request.contextPath}/assets/background/profile.jpg" alt="Foto de Perfil">
                                <h2 class="text-center">${usuario.nome}</h2>
                            </div>

                        </div>

                        <div class="col-md-6">
                            <div class="perfil-info ">
                                <h3>Informações do Perfil</h3>
                                <hr>
                                <p><strong>Matrícula:</strong> ${usuario.id}</p>
                                <p><strong>Email:</strong> ${usuario.email}</p>


                                <!-- Adicione mais informações do perfil aqui -->
                            </div>
                            <div class="containerd-flex">
                                <button class="btn btn-success" id="editProfileBtn">Editar Perfil</button>

                                <button class="btn btn-danger"> <a href="${pageContext.request.contextPath}" class="nav-link text-light">Sair</a></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <!-- Edit Profile Modal -->
    <div class="modal fade" id="editProfileModal" tabindex="-1" aria-labelledby="editProfileModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editProfileModalLabel">Editar Perfil</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="editProfileForm" action="${pageContext.request.contextPath}/ServletEditarPerfil" method="post">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" class="form-control" id="nome" name="nome" value="${usuario.nome}">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="${usuario.email}">
                        </div>
                        <div class="mb-3">
                            <label for="senha" class="form-label">Nova Senha</label>
                            <input type="password" class="form-control" id="senha" name="senha">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                            <button type="submit" class="btn btn-primary">Salvar mudanças</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Edit Profile Modal -->
    <!-- ---------- MAIN ---------- -->

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
<script src="${pageContext.request.contextPath}/js/code.jquery.com_jquery-3.7.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
<script>
    document.getElementById('editProfileBtn').addEventListener('click', function () {
        var editProfileModal = new bootstrap.Modal(document.getElementById('editProfileModal'));
        editProfileModal.show();
    });
</script>

</body>
</html>
