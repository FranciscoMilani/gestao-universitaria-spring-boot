$(document).ready(function(){
    let path = window.location.pathname;
    let nome = path.toLowerCase();
    switch (nome) {
        case '/universidades':
            alteraBotao('universidades');
            break;
        case '/matricular':
            alteraBotao('matricular');
            break;
        case '/matriculas':
            alteraBotao('matriculas');
            break;
        case '/cursos':
            alteraBotao('cursos');
            break;
        case '/alunos':
            alteraBotao('alunos');
            break;
        case '/disciplinas':
            alteraBotao('disciplinas');
            break;
        case '/cadastro/aluno':
            alteraBotao('cadastro-aluno');
            break;
        case '/cadastro/curso':
            alteraBotao('cadastro-curso');
            break;
    }

    function alteraBotao(nome){
        var button = document.getElementById(nome+'-link');
        button.classList.add('active');
        button.classList.remove('text-white');
    }
})
