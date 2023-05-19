package br.ucs.ffmilani.GestaoUni.dao;

import br.ucs.ffmilani.GestaoUni.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@Repository
public class AlunoJdbcDAO implements DAO<Aluno> {

    private NamedParameterJdbcTemplate namedJdbcTemplate;
    private final RowMapper<Aluno> rowMapper = (rs, rowNum) -> {
        Aluno aluno = new Aluno(null, null, null, null);
        aluno.setId(rs.getLong("id"));
        aluno.setNome(rs.getString("nome"));
        aluno.setEmail(rs.getString("email"));
        aluno.setPassword(rs.getString("password"));
        return aluno;
    };

    public AlunoJdbcDAO(@Qualifier("datasource1") DataSource dataSource){
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Aluno> listar() {
        String sql = "SELECT id, nome, email, password from aluno";
        return namedJdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void criar(Aluno aluno) {
        String sql =  "INSERT INTO aluno (id, nome, email, password) VALUES (DEFAULT, :nome, :email, :password)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nome", aluno.getNome())
                .addValue("email", aluno.getEmail())
                .addValue("password", aluno.getPassword());

        namedJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Aluno> encontrar(int id) {

        Aluno aluno = null;

        String sql = "SELECT id, nome, email, password from aluno where id = :id";
        SqlParameterSource namedParam = new MapSqlParameterSource("id", id);

        try {
            aluno = namedJdbcTemplate.queryForObject(sql, namedParam, rowMapper);
        } catch (DataAccessException ex) {
            System.out.println("Aluno " + id + " não encontrado.");
        }

        return Optional.ofNullable(aluno);
    }
}
