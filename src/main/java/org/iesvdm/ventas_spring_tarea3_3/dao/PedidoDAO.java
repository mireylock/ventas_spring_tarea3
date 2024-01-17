package org.iesvdm.ventas_spring_tarea3_3.dao;

import lombok.Data;
import org.iesvdm.ventas_spring_tarea3_3.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO {
    public void create(Pedido pedido);
    public List<Pedido> getAll();
    public Optional<Pedido> find(int id);
    public void update(Pedido pedido);
    public void delete (int id);

}
