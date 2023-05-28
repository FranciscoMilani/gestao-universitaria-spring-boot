package br.ucs.ffmilani.GestaoUni.dao;

import br.ucs.ffmilani.GestaoUni.model.Aluno;
import br.ucs.ffmilani.GestaoUni.model.Matricula;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    //private final RowMapper<Matricula> rowMapper = BeanPropertyRowMapper.newInstance(Matricula.class);

    private final RowMapper<Matricula> rowMapper = (rs, rowNum) -> {
        Matricula matricula = new Matricula(null, null, null, null);

        matricula.setId(rs.getInt("id"));
//        matricula.setAluno(rs.getString("aluno"));
//        matricula.setEmail(rs.getString("email"));
//        matricula.setPassword(rs.getString("password"));
        return matricula;
    };


    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public MatriculaJdbcDAO(@Qualifier("datasource1") DataSource dataSource){
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Matricula> listar() {
        String sql = "SELECT id, aluno, disciplina, semestre FROM matricula";
        return namedJdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void criar(Matricula matricula) {
        String sql =  "INSERT INTO matricula (id, aluno, disciplina, semestre) VALUES (DEFAULT, :aluno, :disciplina, :semestre)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("aluno", matricula.getAluno().getId())
                .addValue("disciplina", matricula.getDisciplina().getId())
                .addValue("semestre", matricula.getSemestre());

        namedJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Matricula> encontrar(int id) {

        Matricula matricula = null;

        String sql = "SELECT id, aluno, disciplina, semestre FROM matricula WHERE id = :id";
        SqlParameterSource namedParam = new MapSqlParameterSource("id", id);

        try {
            matricula = namedJdbcTemplate.queryForObject(sql, namedParam, rowMapper);
        } catch (DataAccessException ex) {
            System.out.println("Matricula " + id + " não encontrada.");
        }

        return Optional.ofNullable(matricula);
    }
}
