<!doctype html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gestão Universitária</title>

    <style>
        label, input {
            display: block;
        }
    </style>
</head>
<body>
    <div>
        <h1>Matricula de aluno</h1>
        <form action="/matricular" method="POST">
            <label for="aluno">Id do aluno:</label>
            <input type="text" id="aluno" name="aluno">
            <br>
            <label for="disciplina">Id da disciplina:</label>
            <input type="text" id="disciplina" name="disciplina">
            <br>
            <label for="semestre">Semestre:</label>
            <input type="text" id="semestre" name="semestre">
            <input type="submit">
        </form>
    </div>
</body>
</html>