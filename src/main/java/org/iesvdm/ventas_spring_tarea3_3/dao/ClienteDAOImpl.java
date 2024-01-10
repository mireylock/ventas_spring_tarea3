package org.iesvdm.ventas_spring_tarea3_3.dao;

import org.iesvdm.ventas_spring_tarea3_3.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Cliente Cliente) {


    }

    @Override
    public List<Cliente> getAll() {
        return null;
    }

    @Override
    public Optional<Cliente> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Cliente Cliente) {

    }

    @Override
    public void delete(int id) {

    }
}
