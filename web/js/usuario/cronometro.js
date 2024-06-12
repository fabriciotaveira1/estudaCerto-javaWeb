let cronometro;
let horas = 0, minutos = 0, segundos = 0;

function iniciarCronometro() {
    if (!cronometro) {
        cronometro = setInterval(() => {
            segundos++;
            if (segundos >= 60) {
                segundos = 0;
                minutos++;
                if (minutos >= 60) {
                    minutos = 0;
                    horas++;
                }
            }
            atualizarCronometro();
        }, 1000);
    }
}

function pausarCronometro() {
    clearInterval(cronometro);
    cronometro = null;
}

function zerarCronometro() {
    clearInterval(cronometro);
    cronometro = null;
    horas = minutos = segundos = 0;
    atualizarCronometro();
}

function atualizarCronometro() {
    let tempoFormatado = `${padZero(horas)}:${padZero(minutos)}:${padZero(segundos)}`;
    document.getElementById('cronometro').textContent = tempoFormatado;
}

function padZero(valor) {
    return valor < 10 ? `0${valor}` : valor;
}

// Event listener para iniciar, pausar e zerar
document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('btnIniciar').addEventListener('click', iniciarCronometro);
    document.getElementById('btnPausar').addEventListener('click', pausarCronometro);
    document.getElementById('btnZerar').addEventListener('click', zerarCronometro);
});

// Salvar notas
document.getElementById('notas').addEventListener('change', function () {
    let notas = this.value;
    console.log('Notas salvas:', notas);
    // Aqui você pode enviar as notas para o backend para salvar no banco de dados
});


function atualizarCronometro() {
    let tempoFormatado = `${padZero(horas)}:${padZero(minutos)}:${padZero(segundos)}`;
    document.getElementById('cronometro').textContent = tempoFormatado;

    // Atualiza o valor do input hidden com o novo tempo gasto
    document.getElementById("tempo_gasto").value = tempoFormatado;
}