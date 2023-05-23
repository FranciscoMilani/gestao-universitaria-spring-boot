<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.ucs.ffmilani.GestaoUni.model.Matricula" %>
<!DOCTYPE html>
<html>
<head>
    <title>Matriculas</title>
</head>
<body>
    <h1>Lista de matrículas</h1>
    <table>
        <tr>
            <th>Aluno</th>
            <th>Disciplina</th>
            <th>Semestre</th>
        </tr>
        <c:forEach items="${matriculas}" var="matricula">
            <tr>
                <td>${matricula.getSemestre()}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
