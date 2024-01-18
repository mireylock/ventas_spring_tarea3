package org.iesvdm.ventas_spring_tarea3_3.dao;


import org.iesvdm.ventas_spring_tarea3_3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3_3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3_3.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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
                """, (rs, rowNum) -> UtilDAO.newPedido(rs)
        );

        return listPedido;
    }

    @Override
    public Optional<Pedido> find(int id) {
        return null;

    }

    @Override
    public void update(Pedido pedido) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<Cliente> findClienteBy(int pedidoId) {
        return Optional.empty();
    }

    @Override
    public Optional<Comercial> findComercialBy(int pedidoId) {
        return Optional.empty();
    }
}
