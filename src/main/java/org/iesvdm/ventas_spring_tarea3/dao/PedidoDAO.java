package org.iesvdm.ventas_spring_tarea3.dao;


import org.iesvdm.ventas_spring_tarea3.domain.Pedido;

import java.util.List;

public interface PedidoDAO<Pedido> extends RepositoryBase<Pedido> {
    List<Pedido> findByIdComercial(int idComercial);

    List<Pedido> findByIdCliente(int idCliente);
}
