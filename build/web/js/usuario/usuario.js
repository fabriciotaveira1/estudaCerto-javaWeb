

function mostrarAdicionarDisciplina() {
    document.getElementById("adicionarDisciplina").style.display = "block";
    document.getElementById("editarDisciplina").style.display = "none";
    document.getElementById("excluirDisciplina").style.display = "none";
    document.getElementById("verDisciplina").style.display = "none";
    document.getElementById("verNotas").style.display = "none";
}

function mostrarEditarDisciplina() {
    document.getElementById("adicionarDisciplina").style.display = "none";
    document.getElementById("editarDisciplina").style.display = "block";
    document.getElementById("excluirDisciplina").style.display = "none";
    document.getElementById("verDisciplina").style.display = "none";
    document.getElementById("verNotas").style.display = "none";
}

function mostrarExcluirDisciplina() {
    document.getElementById("adicionarDisciplina").style.display = "none";
    document.getElementById("editarDisciplina").style.display = "none";
    document.getElementById("excluirDisciplina").style.display = "block";
    document.getElementById("verDisciplina").style.display = "none";
    document.getElementById("verNotas").style.display = "none";
}

function mostrarAdicionarNota() {
    document.getElementById("adicionarDisciplina").style.display = "none";
    document.getElementById("editarDisciplina").style.display = "none";
    document.getElementById("excluirDisciplina").style.display = "none";
    document.getElementById("verDisciplina").style.display = "none";
    document.getElementById("verNotas").style.display = "none";
}

function mostrarEditarNota() {
    document.getElementById("adicionarDisciplina").style.display = "none";
    document.getElementById("editarDisciplina").style.display = "none";
    document.getElementById("excluirDisciplina").style.display = "none";
    document.getElementById("verDisciplina").style.display = "none";
    document.getElementById("verNotas").style.display = "none";
}

function mostrarExcluirNota() {
    document.getElementById("adicionarDisciplina").style.display = "none";
    document.getElementById("editarDisciplina").style.display = "none";
    document.getElementById("excluirDisciplina").style.display = "none";
    document.getElementById("verDisciplina").style.display = "none";
    document.getElementById("verNotas").style.display = "none";
}

function mostrarVerNotas() {
    document.getElementById("adicionarDisciplina").style.display = "none";
    document.getElementById("editarDisciplina").style.display = "none";
    document.getElementById("excluirDisciplina").style.display = "none";
    document.getElementById("verDisciplina").style.display = "none";
    document.getElementById("verNotas").style.display = "block";
}

function mostrarVerDisciplinas() {
    document.getElementById("adicionarDisciplina").style.display = "none";
    document.getElementById("editarDisciplina").style.display = "none";
    document.getElementById("excluirDisciplina").style.display = "none";
    document.getElementById("disciplinasConteudo").style.display = "block";
    document.getElementById("verNotas").style.display = "none";
}



// Função para abrir o modal de edição de nota
function abrirModalEditarNota(disciplinaId) {
    // Define o valor do campo oculto com o ID da disciplina
    document.getElementById('editar-nota-disciplina-id').value = disciplinaId;
    // Abre o modal
    $('#editarNotaModal').modal('show');
}

function abrirModalDeletarNota(disciplinaId) {
    // Defina o ID da disciplina no input hidden dentro do formulário do modal
    document.getElementById("deletar-nota-disciplina-id").value = disciplinaId;
    // Abra o modal
    $('#deletarNotaModal').modal('show');
}