/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function mostrarAdicionarMaterial() {
    document.getElementById("adicionarMaterial").style.display = "block";
    document.getElementById("excluirMaterial").style.display = "none";
    document.getElementById("verMateriais").style.display = "none";
    document.getElementById("verNotas").style.display = "none";
}

function mostrarExcluirMaterial() {
    document.getElementById("adicionarMaterial").style.display = "none";
    document.getElementById("excluirMaterial").style.display = "block";
    document.getElementById("verMateriais").style.display = "none";
    document.getElementById("verNotas").style.display = "none";
}

function mostrarVerNotas() {
    document.getElementById("adicionarMaterial").style.display = "none";
    document.getElementById("excluirMaterial").style.display = "none";
    document.getElementById("verMateriais").style.display = "none";
    document.getElementById("verNotas").style.display = "block";
}

function mostrarVerMateriais() {
    document.getElementById("adicionarMaterial").style.display = "none";
    document.getElementById("excluirMaterial").style.display = "none";
    document.getElementById("verMateriais").style.display = "block";
    document.getElementById("verNotas").style.display = "none";
}

function abrirModalEditar(materialId) {
    // Aqui você pode usar AJAX para buscar os detalhes do material com o ID fornecido
    // Por enquanto, vamos supor que você já tenha os detalhes do material e os campos do formulário preenchidos

    // Suponha que você tenha os detalhes do material em um objeto chamado material
    var material = {
        id: materialId,
        titulo: "Título do Material",
        descricao: "Descrição do Material"
    };

    // Preencha os campos do formulário no modal com os detalhes do material
    document.getElementById("materialId").value = material.id;
    document.getElementById("tituloEditar").value = material.titulo;
    document.getElementById("descricaoEditar").value = material.descricao;

    // Abra o modal de edição de material
    var modal = new bootstrap.Modal(document.getElementById('modalEditarMaterial'));
    modal.show();
}



function abrirModalExcluir(id) {
    document.getElementById('idExcluir').value = id;
    var modalExcluir = new bootstrap.Modal(document.getElementById('modalExcluirMaterial'));
    modalExcluir.show();
}