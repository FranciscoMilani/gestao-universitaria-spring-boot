package br.ucs.ffmilani.GestaoUni.dao;

import br.ucs.ffmilani.GestaoUni.model.Aluno;
import br.ucs.ffmilani.GestaoUni.model.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class MatriculaJdbcDAO implements DAO<Matricula> {
    @Override
    public List<Matricula> listar() {
        return null;
    }

    @Override
    public void criar(Matricula matricula) {

    }

    @Override
    public Optional<Matricula> encontrar(int id) {
        return Optional.empty();
    }
//
//    private NamedParameterJdbcTemplate namedJdbcTemplate;
//    private final RowMapper<Matricula> rowMapper = (rs, rowNum) -> {
//        Matricula matricula = new Matricula(null, null, null, null);
//        matricula.setId(rs.getInt("id"));
//        matricula.setAluno(rs.getInt("aluno"));
//        matricula.setDisciplina(rs.getString("disciplina"));
//        matricula.setSemestre(rs.getString("semestre"));
//        return aluno;
//    };
//
//    @Autowired
//    public void init(DataSource dataSource) {
//        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//    }
//
//    public MatriculaJdbcDAO(NamedParameterJdbcTemplate namedJdbcTemplate){
//        this.namedJdbcTemplate = namedJdbcTemplate;
//    }
//
//    @Override
//    public List<Matricula> listar() {
//        String sql = "SELECT id, nome, email, password from matricula";
//        return namedJdbcTemplate.query(sql, rowMapper);
//    }
//
//    @Override
//    public void criar(Matricula matricula) {
//        String sql =  "INSERT INTO matricula (id, nome, email, password) VALUES (DEFAULT, :nome, :email, :password)";
//        SqlParameterSource params = new MapSqlParameterSource()
//                .addValue("nome", aluno.getNome())
//                .addValue("email", aluno.getEmail())
//                .addValue("password", aluno.getPassword());
//
//        namedJdbcTemplate.update(sql, params);
//    }
//
//    @Override
//    public Optional<Matricula> encontrar(int id) {
//
//        Matricula aluno = null;
//
//        String sql = "SELECT id, nome, email, password from matricula WHERE id = :id";
//        SqlParameterSource namedParam = new MapSqlParameterSource("id", id);
//
//        try {
//            matricula = namedJdbcTemplate.queryForObject(sql, namedParam, rowMapper);
//        } catch (DataAccessException ex) {
//            System.out.println("Aluno " + id + " não encontrado.");
//        }
//
//        return Optional.ofNullable(aluno);
//    }
}
