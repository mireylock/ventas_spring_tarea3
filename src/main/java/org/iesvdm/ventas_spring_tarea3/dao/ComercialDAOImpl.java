package org.iesvdm.ventas_spring_tarea3.dao;

import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class ComercialDAOImpl implements RepositoryBase<Comercial> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create_CON_RECARGA_DE_ID_POR_PS(Comercial comercial) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
                        INSERT INTO comercial
                        (nombre, apellido1, apellido2, comisión)
                        VALUE 
                        (?, ?, ?, ?)
                        """, Statement.RETURN_GENERATED_KEYS);
            int idx = 1;
            ps.setString(idx++, comercial.getNombre());
            ps.setString(idx++, comercial.getApellido());
            ps.setString(idx++, comercial.getApellido2());
            ps.setBigDecimal(idx++, comercial.getComision());
            return ps;
        }, keyHolder);

        comercial.setId(keyHolder.getKey().intValue());
    }

    public void create_CON_RECARGA_DE_ID_POR_SIMPLEJDBCINSERT(Comercial comercial) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                //establecer tabla sobre la que actuar
                .withTableName("comercial")
                //establecer columna de id autogenerado
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nombre", comercial.getNombre())
                .addValue("apellido1", comercial.getApellido())
                .addValue("apellido2", comercial.getApellido2())
                .addValue("comision", comercial.getComision());

        Number number = simpleJdbcInsert.executeAndReturnKey(params);

        comercial.setId(number.intValue());
    }

    @Override
    public List<Comercial> getAll() {
        List<Comercial> listCom = jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getBigDecimal("comisión")
                ));

        return listCom;
    }

    @Override
    public Optional<Comercial> find(int id) {
        Comercial com = jdbcTemplate
                .queryForObject("SELECT * FROM comercial where id = ?"
                        , (rs, rowNum) -> new Comercial(rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido1"),
                                rs.getString("apellido2"),
                                rs.getBigDecimal("comisión"))
                        , id);

        return Optional.of(com);
    }

    @Override
    public void update(Comercial comercial) {
        int rows = jdbcTemplate.update(("UPDATE comercial SET nombre = ?, apellido1 = ?, apellido2 = ?, comisión = ? WHERE id = ?"), comercial.getNombre(), comercial.getApellido(), comercial.getApellido2(), comercial.getComision(), comercial.getId());
        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados");
    }

    @Override
    public void delete(int id) {
        //En la bbdd no está on delete cascade para los comerciales, por lo que para borrar un comercial, hay que borrar primero los pedidos que tiene asociados
        int rowsPedido = jdbcTemplate.update("DELETE FROM pedido WHERE id_comercial= ?", id);
        int rowsComercial = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);
        if (rowsPedido == 0) System.out.println("Delete de comercial con 0 registros actualizados");
        if (rowsComercial == 0) System.out.println("Delete de comercial con 0 registros actualizados");

    }
}
