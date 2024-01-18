package org.iesvdm.ventas_spring_tarea3_3.dao;

import org.iesvdm.ventas_spring_tarea3_3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3_3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3_3.domain.Pedido;

import java.util.Optional;


public interface PedidoDAO<Pedido> extends RepositoryBase<Pedido> {
    public Optional<Cliente> findClienteBy(int pedidoId);

    public Optional<Comercial> findComercialBy(int pedidoId);



}
