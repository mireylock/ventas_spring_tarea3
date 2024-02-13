package org.iesvdm.ventas_spring_tarea3.dao;

import org.iesvdm.ventas_spring_tarea3.domain.ClienteHasComercial;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;
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
public class ClienteHasComercialImpl implements RepositoryBase<ClienteHasComercial> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create_CON_RECARGA_DE_ID_POR_PS(ClienteHasComercial clienteHasComercial) {

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
                        INSERT INTO clienteHasComercial
                        (cliente_id, comercial_id, fecha_asociacion, prioridad)
                        VALUE
                        (?, ?, ?, ?)
                        """, Statement.RETURN_GENERATED_KEYS);
            int idx = 1;
            ps.setInt(idx++, clienteHasComercial.getCliente().getId());
            ps.setInt(idx++, clienteHasComercial.getComercial().getId();
            ps.setDate(idx++,  Date.valueOf(clienteHasComercial.getFecha_asociacion()));
            ps.setInt(idx++, clienteHasComercial.getPrioridad());
            return ps;
        });
    }

    @Override
    public List<ClienteHasComercial> getAll() {
        List<ClienteHasComercial> listClienteHasComercial = this.jdbcTemplate.query("""
                SELECT * FROM  clienteHasComercial CH left join cliente C on  CH.cliente_id = C.id
                                        left join comercial CO on CH.comercial_id = CO.id
                """, (rs, rowNum) -> UtilDAO.newClienteHasComercial(rs));
        return listClienteHasComercial;
    }

    @Override
    public Optional<ClienteHasComercial> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(ClienteHasComercial clienteHasComercial) {

    }

    @Override
    public void delete(int id) {

    }
}
