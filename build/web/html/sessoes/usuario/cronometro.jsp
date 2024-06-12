<%-- 
    Document   : cronometro
    Created on : 11 de jun. de 2024, 18:49:00
    Author     : victo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Recursos</title>
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/index.css">
        <link rel="stylesheet" href="../css/mediaQueries/smarthpone.css">
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
                            <li class="nav-item disabled"><a href="resources.html" class="nav-link text-light ">Recursos</a>
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
            <!-- ---------- HEADER ---------- -->

            <!-- ---------- MAIN ---------- -->
            <main>
                <div class="container-fluid my-2 text-success">
                    <h1 class="text-uppercase text-center text-black fontNature" id="mainH1">RECURSOS</h1>
                </div>

                <section class="container my-2">
                    <hr>

                    <div class="row my-2">
                        <div class="col-md-6">
                            <div class="article rounded">
                                <img src="../assets/background/article1.jpg" alt="Prato com comida saudável">
                                <h3>7 Maneiras para uma Alimentação Sustentável</h3>
                                <p>Descubra como fazer escolhas alimentares mais amigáveis ao meio ambiente e saiba como
                                    reduzir seu impacto através da sua dieta.</p>
                                <a href="https://blog.bb.com.br/dicas-alimentacao-sustentavel/" class="btn btn-success"
                                   target="_blank">Leia Mais</a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="article rounded">
                                <img src="../assets/background/article2.jpg" alt="Compostagem doméstica">
                                <h3>Guia de Compostagem Doméstica Passo a Passo</h3>
                                <p>Aprenda como transformar resíduos orgânicos em composto nutritivo para suas plantas e
                                    reduza a quantidade de lixo que você produz.</p>
                                <a href="https://autossustentavel.com/2021/10/pequeno-guia-para-compostagem-domestica-saiba-o-que-fazer-com-os-residuos-organicos.html"
                                   class="btn btn-success" target="_blank">Leia Mais</a>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="article rounded">
                                <img src="../assets/background/article3.jpg" alt="Torneira">
                                <h3>Economizando Água em Casa: Dicas Práticas</h3>
                                <p>Descubra maneiras simples e eficazes de reduzir o consumo de água em sua casa,
                                    contribuindo para a conservação desse recurso precioso.</p>
                                <a href="https://agergs.rs.gov.br/dica-do-dia-conheca-algumas-maneiras-de-economizar-agua-e-ajudar-o-meio-ambiente"
                                   class="btn btn-success" target="_blank">Leia Mais</a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="article rounded">
                                <img src="../assets/background/article4.jpg" alt="Pápeis brancos em forma de bicicleta em cima de um gramado verde">
                                <h3>Mobilidade Sustentável: Escolhendo o Transporte Certo</h3>
                                <p>Saiba como tomar decisões conscientes sobre transporte, escolhendo opções que reduzam sua
                                    pegada de carbono e promovam um ambiente mais limpo.</p>
                                <a href="https://www.nuvemshop.com.br/blog/transporte-sustentavel/" class="btn btn-success"
                                   target="_blank">Leia Mais</a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="article rounded">
                                <img src="../assets/background/article5.jpg" alt="Comidas sendo diserpdiçadas">
                                <h3>Reduzindo o Desperdício de Alimentos: Um Passo em Direção à Sustentabilidade</h3>
                                <p>Saiba como tomar decisões conscientes sobre transporte, escolhendo opções que reduzam sua
                                    pegada de carbono e promovam um ambiente mais limpo.</p>
                                <a href="https://www.nuvemshop.com.br/blog/transporte-sustentavel/" class="btn btn-success"
                                   target="_blank">Leia Mais</a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="article rounded">
                                <img src="../assets/background/article6.jpg" alt="Mão segurando um planeta">
                                <h3>A Importância da Biodiversidade para um Planeta Saudável</h3>
                                <p> A biodiversidade, a variedade de vida na Terra, é essencial para a saúde de nosso
                                    planeta. Neste artigo, exploramos por que a biodiversidade é crucial e como podemos
                                    protegê-la.</p>
                                <a href="https://razaoconsultoriaambiental.com.br/?p=1538"
                                   class="btn btn-success" target="_blank">Leia Mais</a>
                            </div>
                        </div>
                    </div>

                </section>
            </main>
            <!-- ---------- MAIN ---------- -->
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
    </body>
    <script src="../js/code.jquery.com_jquery-3.7.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"></script>
    <script src="../js/index.js"></script>


</html>
