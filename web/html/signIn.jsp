<%-- 
    Document   : signIn
    Created on : 10 de jun. de 2024, 23:53:20
    Author     : victo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Comunidade</title>
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mediaQueries/smarthpone.css">
    </head>

    <body>
        <!-- ---------- PAGE ---------- -->
        <div id="page" class="flex-column ">
            <!-- ---------- HEADER ---------- -->
            <header>
                <nav class="navbar navbar-expand-lg bg-primary">
                    <div class="container-fluid p-2 fontNature">
                        <a href="#" class="navbar-brand text-light">Estude Certo</a>
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0" id="ulRight">
                            <li class="nav-item disabled"><a href="../index.html" class="nav-link text-light">Pagina
                                    Inicial</a></li>
                            <li class="nav-item disabled"><a href="tecs.html"
                                                             class="nav-link text-light ">Técnicas de Estudo</a></li>

                        </ul>
                        <ul class="navbar-nav mb-2 mb-lg-0 disabled">
                            <li class="nav-item"><a href="${pageContext.request.contextPath}/html/signIn.jsp" class="nav-link text-light">Entrar/Cadastrar</a></li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- ---------- HEADER ---------- -->

            <!-- ---------- MAIN ---------- -->

            <main class="my-2">
                <div class="container-fluid my-2 text-primary">
                    <h1 class="text-uppercase text-center text-black fontNature" id="mainH1">Entrar/Cadastrar</h1>
                </div>
                <section class="container-fluid" >
                    <div class="row signIn d-flex align-items-center justify-content-center">
                        <div class="col-md-6">
                            <div class="container">
                                <h3 class="text-center">Faça o seu login</h3>
                                <form action="${pageContext.request.contextPath}/ServletLogin" class="form" method="post">
                                    <label class="form-label" for="email">Email</label>
                                    <input class="form-control" type="email" name="email" id="email" placeholder="Digite aqui o seu Email" required>
                                    <label class="form-label" for="senha">Senha</label>
                                    <input class="form-control" type="password" name="senha" id="senha" placeholder="Digite sua senha">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <button type="submit" class="btn btn-primary my-3">Entrar</button>
                                        <button type="button" class="btn btn-warning my-3" data-bs-toggle="modal" data-bs-target="#forgetPassword">
                                            Recuperar Senha
                                        </button>
                                    </div>
                                </form>
                                <p>${mensagemErro}</p>
                            </div>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade" id="forgetPassword" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Recuperar Senha</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${pageContext.request.contextPath}/ServletRecuperarSenha" method="post">
                                            <div class="mb-3">
                                                <label for="email" class="form-label">Email</label>
                                                <input type="email" class="form-control" id="email" name="email" placeholder="Digite aqui o seu Email" required>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Enviar</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="col-md-6">
                            <div class="container">
                                <h3 class="text-center">Cadastre-se</h3>
                                <form action="${pageContext.request.contextPath}/ServletCadastrarUsuario" class="form" method="post">
                                    <label class="form-label" for="email">Email</label>
                                    <input class="form-control" type="email" name="email" id="email" placeholder="Digite o seu Email" required>

                                    <label for="name" class="form-label">Nome</label>
                                    <input type="text" class="form-control" name="name" id="name" placeholder="Digite o seu nome completo" required>

                                    <label class="form-label" for="password">Senha</label>
                                    <input class="form-control" type="password" name="password" id="password" placeholder="Digite sua senha" required>
                                    <small id="passwordHelp" class="form-text text-muted">A senha deve conter pelo menos 8 caracteres, incluindo pelo menos uma letra maiúscula, uma letra minúscula e um número.</small>
                                    <br>

                                    <label class="form-label" for="tipo_usuario">Tipo de Usuário</label>
                                    <select class="form-control" name="tipo_usuario" id="tipo_usuario" required>
                                        <option value="" disabled selected>Selecione o tipo de usuário</option>
                                        <option value="estudante">Estudante</option>
                                        <option value="professor">Professor</option>
                                        <option value="administrador">Administrador</option>
                                    </select>
                                    <div class="d-flex justify-content-center align-items-center">
                                        <button type="submit" class="btn btn-primary my-3">Cadastrar-se</button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </section>
            </main>
            <!-- ---------- MAIN ---------- -->
            <!-- ---------- FOOTER ---------- -->
            <!-- Footer -->
            <footer class="bg-primary text-light py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4">
                            <h4>Sobre Nós</h4>
                            <p>O EstudaCerto é uma plataforma dedicada a inspirar e guiar estudantes em suas jornadas
                                para um aprendizado eficiente e prazeroso. Nosso objetivo é oferecer recursos, desafios e uma
                                comunidade vibrante para apoiar a adoção de práticas de estudo mais eficazes.</p>
                        </div>
                        <div class="col-md-4">
                            <h4>Links Úteis</h4>
                            <ul class="list-unstyled">
                                <li class="nav-item"><a class="nav-link text-white" href="index.html">Página Inicial</a></li>
                                <li class="nav-item"><a class="nav-link text-white" href="html/desafios.html">Desafios</a></li>
                                <li class="nav-item"><a class="nav-link text-white" href="html/recursos.html">Recursos</a></li>
                                <li class="nav-item"><a class="nav-link text-white" href="html/comunidade.html">Comunidade</a></li>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <h4>Contato</h4>
                            <p>Email: contato@estudacerto.com</p>
                            <p>Telefone: (123) 456-7890</p>
                            <div class="social-icons mt-3">
                                <a href="#" class="text-light me-3"><i class="fab fa-facebook"></i></a>
                                <a href="#" class="text-light me-3"><i class="fab fa-twitter"></i></a>
                                <a href="#" class="text-light me-3"><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>

            <!-- JavaScript Bundle with Popper -->
            <script src="${pageContext.request.contextPath}/js/code.jquery.com_jquery-3.7.1.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"></script>
            <script src="${pageContext.request.contextPath}/js/index.js"></script>

    </body>