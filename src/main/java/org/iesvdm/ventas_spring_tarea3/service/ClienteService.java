package org.iesvdm.ventas_spring_tarea3.service;

import org.iesvdm.ventas_spring_tarea3.dao.ClienteDAOImpl;
import org.iesvdm.ventas_spring_tarea3.dao.ClienteHasComercialDAOImpl;
import org.iesvdm.ventas_spring_tarea3.dao.PedidoDAOImpl;
import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.ClienteHasComercial;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteDAOImpl clienteDAO;

    @Autowired
    private PedidoDAOImpl pedidoDAO;

    /**
     * Crea un cliente
     * @param cliente
     */
    public void createCliente (Cliente cliente) {
        clienteDAO.create_CON_RECARGA_DE_ID_POR_PS(cliente);
    }

    /**
     * Read (lista) los clientes
     * @return
     */
    public List<Cliente> listAll () {
        return clienteDAO.getAll();
    }

    /**
     * Update (reemplaza) un cliente
     * @param cliente
     */
    public void replaceCliente (Cliente cliente) {
        clienteDAO.update(cliente);
    }

    /**
     * Delete (borra) un cliente
     * @param id
     */
    public void deleteCliente(int id) {
        clienteDAO.delete(id);
    }

    /**
     * Da el detalle del cliente de un id determinado
     * @param id
     * @return
     */
    public Cliente detailCliente(Integer id) {
        Optional<Cliente> opCliente = clienteDAO.find(id);

        if(opCliente.isPresent()) {
            return opCliente.get();
        } else {
            return null;
        }
    }

    public Map<Comercial, Long> comercialesConteoPedidos (Integer id) {

        List<Pedido> listaPedidosCliente = pedidoDAO.findByIdCliente(id);

        Map<Comercial, Long> comercialesConteoPedidos = listaPedidosCliente.stream()
                //Mete en el map el cliente y el total de la suma de sus pedidos con summingDouble
                .collect(Collectors.groupingBy(Pedido::getComercial, Collectors.counting()));

        return comercialesConteoPedidos;
    }
    public Long pedidosTrimestre (Integer id) {
        List<Pedido> listaPedidosCliente = pedidoDAO.findByIdCliente(id);

        Long pedidosTrimestre = listaPedidosCliente.stream()
                .filter(pedido -> pedido.getFecha().isAfter(LocalDate.now().minusMonths(3)))
                .count();

        return pedidosTrimestre;
    }
    public Long pedidosSemestre (Integer id) {
        List<Pedido> listaPedidosCliente = pedidoDAO.findByIdCliente(id);

        Long pedidosSemestre = listaPedidosCliente.stream()
                .filter(pedido -> pedido.getFecha().isAfter(LocalDate.now().minusMonths(6)))
                .count();

        return pedidosSemestre;
    }

    public Long pedidosAnio(Integer id) {
        List<Pedido> listaPedidosCliente = pedidoDAO.findByIdCliente(id);

        Long pedidosAnio = listaPedidosCliente.stream()
                .filter(pedido -> pedido.getFecha().isAfter(LocalDate.now().minusMonths(12)))
                .count();

        return pedidosAnio;
    }

    public Long pedidosLustro(Integer id) {
        List<Pedido> listaPedidosCliente = pedidoDAO.findByIdCliente(id);

        Long pedidosLustro = listaPedidosCliente.stream()
                .filter(pedido -> pedido.getFecha().isAfter(LocalDate.now().minusYears(5)))
                .count();

        return pedidosLustro;
    }



}
