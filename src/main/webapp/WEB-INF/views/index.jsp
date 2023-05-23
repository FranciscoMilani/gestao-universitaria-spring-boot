<!doctype html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gestão Universitária</title>

    <style>
        body {
            font-family: "Roboto Light", "Segoe UI", sans-serif;
            background-color: #393E46;
            color: #EEEEEE;
        }

        .formulario {
            margin-top: 200px;
        }

        label, input {
            display: block;
        }

        form {
            display: flex;
            flex-direction: column;
            justify-content: center;
            width: 250px;
            margin: auto;
            padding: 50px;
            border-radius: 5px;
            height: 100%;
            background-color: #222831;
        }

        form label {
            text-align: left;
        }

        form input {
            border: none;
            background-color: #EEEEEE;
            border-radius: 3px;
            padding: 5px;
        }

        form input[type="submit"] {
            margin: 12px auto;
            width: 50%;
        }

        h1 {
            text-align:center;
        }
    </style>
</head>
<body>
    <main>
        <div class="formulario">
            <h1>Matricula de aluno</h1>
            <form action="/matricular" method="POST">
                <label for="aluno">Id do aluno</label>
                <input type="text" id="aluno" name="aluno">
                <br>
                <label for="disciplina">Id da disciplina</label>
                <input type="text" id="disciplina" name="disciplina">
                <br>
                <label for="semestre">Semestre</label>
                <input type="text" id="semestre" name="semestre">
                <input type="submit">
            </form>
        </div>
    </main>
</body>
</html>