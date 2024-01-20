package org.iesvdm.ventas_spring_tarea3.dao;


import java.util.List;

public interface PedidoDAO<Pedido> extends RepositoryBase<Pedido> {
    List<Pedido> findByIdComercial(int idComercial);
}
