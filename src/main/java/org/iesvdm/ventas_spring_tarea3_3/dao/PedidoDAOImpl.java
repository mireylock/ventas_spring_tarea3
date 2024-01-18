package org.iesvdm.ventas_spring_tarea3_3.dao;


import org.iesvdm.ventas_spring_tarea3_3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3_3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3_3.domain.Pedido;

import java.util.List;
import java.util.Optional;

public class PedidoDAOImpl implements PedidoDAO {
    @Override
    public void create(Pedido pedido) {

    }

    @Override
    public List<Pedido> getAll() {
        return null;
    }

    @Override
    public Optional<Pedido> find(int id) {
        return Optional.empty();
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
