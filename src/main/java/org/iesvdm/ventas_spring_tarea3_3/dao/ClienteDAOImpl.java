package org.iesvdm.ventas_spring_tarea3_3.dao;

import org.iesvdm.ventas_spring_tarea3_3.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Cliente cliente) {
        //RECARGA DE ID AUTOINCREMENT CON JDBCTEMPLATE
        //se puede hacer de dos formas:
        //connection/preparedStatement/KeyHolder
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
                        INSERT INTO cliente
                        (nombre, apellido1, apellido2, ciudad, categoría)
                        VALUE 
                        (?, ?, ?, ?, ?)
                        """, Statement.RETURN_GENERATED_KEYS);
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getApellido());
            ps.setString(idx++, cliente.getApellido2());
            ps.setString(idx++, cliente.getCiudad());
            ps.setInt(idx++, cliente.getCategoria());
            return ps;
        }, keyHolder);

        cliente.setId(keyHolder.getKey().intValue());


        //simpleJdbcInsert
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                //establecer tabla sobre la que actuar
                .withTableName("cliente")
                //establecer columna de id autogenerado
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nombre", cliente.getNombre())
                .addValue("apellido", cliente.getApellido())
                .addValue("apellido2", cliente.getApellido2())
                .addValue("ciudad", cliente.getCiudad())
                .addValue("categoría", cliente.getCategoria());

        Number number = simpleJdbcInsert.executeAndReturnKey(params);

        cliente.setId(number.intValue());
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> listCli = jdbcTemplate.query(
                "SELECT * FROM cliente",
                (rs, rowNum) -> new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getInt("categoría")
                        ));

        return listCli;
    }

    @Override
    public Optional<Cliente> find(int id) {
        Cliente cli = jdbcTemplate
                .queryForObject("SELECT * FROM cliente where id = ?"
                        , (rs, rowNum) -> new Cliente(rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("apellido2"),
                                rs.getString("ciudad"),
                                rs.getInt("categoría"))
                        , id);

        return Optional.of(cli);
    }

    @Override
    public void update(Cliente Cliente) {



    }

    @Override
    public void delete(int id) {

    }
}
