package org.iesvdm.ventas_spring_tarea3_3.dao;


import org.iesvdm.ventas_spring_tarea3_3.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class PedidoDAOImpl implements PedidoDAO<Pedido> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create_CON_RECARGA_DE_ID_POR_PS(Pedido pedido) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
                        INSERT INTO pedido
                        (total, fecha, id_cliente, id_comercial)
                        VALUE
                        (?, ?, ?, ?)
                        """, Statement.RETURN_GENERATED_KEYS);
            int idx = 1;
            ps.setDouble(idx++, pedido.getTotal());
            ps.setDate(idx++, new java.sql.Date(pedido.getFecha().getTime()));
            ps.setInt(idx++, pedido.getCliente().getId());
            ps.setInt(idx++, pedido.getComercial().getId());
            return ps;
        }, keyHolder);

        pedido.setId(keyHolder.getKey().intValue());

    }

    @Override
    public List<Pedido> getAll() {
        List<Pedido> listPedido = this.jdbcTemplate.query("""
                SELECT * FROM  pedido P left join cliente C on  P.id_cliente = C.id
                                        left join comercial CO on P.id_comercial = CO.id
                """, (rs, rowNum) -> UtilDAO.newPedido(rs));


        return listPedido;
    }

    @Override
    public Optional<Pedido> find(int id) {
        Pedido pedido = this.jdbcTemplate.queryForObject("""
                   SELECT * FROM  pedido P left join cliente C on  P.id_cliente = C.id
                                        left join comercial CO on P.id_comercial = CO.id
                                        WHERE P.id = ?
                """, (rs, rowNum) -> UtilDAO.newPedido(rs), id);

        if (pedido !=null) {
            return Optional.of(pedido);
        } else {
            //log.debug("No encontrado pedido con id {} devolviendo Optional.empty()", id);
            return Optional.empty();
        }
    }

    @Override
    public void update(Pedido pedido) {
        this.jdbcTemplate.update("""
            UPDATE pedido SET total = ?, fecha = ?, id_cliente = ?, id_comercial = ? WHERE id = ?
            """, pedido.getTotal(), pedido.getFecha(), pedido.getCliente().getId(), pedido.getComercial().getId(), pedido.getId());
    }

    @Override
    public void delete(int id) {
        this.jdbcTemplate.update("""
            DELETE from pedido WHERE id = ?
        """, id);
    }

    @Override
    public List<Pedido> findByIdComercial(int idComercial) {
        List<Pedido> listaPedidosComercial = this.jdbcTemplate.query("""
                   SELECT * FROM  pedido P join comercial CO on P.id_comercial = CO.id 
                   join cliente C on P.id_cliente = C.id
                   WHERE CO.id = ?
                """, (rs, rowNum) -> UtilDAO.newPedido(rs), idComercial);

       return listaPedidosComercial;
    }
}
