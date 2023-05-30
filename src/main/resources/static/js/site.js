window.onload = function() {
    let path = window.location.pathname;

    let nome = path.toLowerCase();
    switch (nome) {
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
    }

    function alteraBotao(nome){
        var button = document.getElementById(nome+'-link');
        button.classList.add('active');
        button.classList.remove('text-white');
    }
}
