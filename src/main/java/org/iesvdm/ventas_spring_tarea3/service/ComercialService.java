package org.iesvdm.ventas_spring_tarea3.service;

import org.iesvdm.ventas_spring_tarea3.dao.ComercialDAOImpl;
import org.iesvdm.ventas_spring_tarea3.dao.PedidoDAOImpl;
import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class ComercialService {
    @Autowired
    private ComercialDAOImpl comercialDAO;

    @Autowired
    private PedidoDAOImpl pedidoDAO;

    /**
     * Crea un comercial
     * @param comercial
     */
    public void createComercial (Comercial comercial) {
        comercialDAO.create_CON_RECARGA_DE_ID_POR_PS(comercial);
    }

    /**
     * Read (lista) los comerciales
     * @return
     */
    public List<Comercial> listAll () {
        return comercialDAO.getAll();
    }

    /**
     * Update (reemplaza) un comercial
     * @param comercial
     */
    public void replaceComercial (Comercial comercial) {
        comercialDAO.update(comercial);
    }

    /**
     * Delete (borra) un comercial
     * @param id
     */
    public void deleteComercial(int id) {
        comercialDAO.delete(id);
    }

    /**
     * Da el detalle del comercial de un id determinado
     * @param id
     * @return
     */
    public Comercial detailComercial(Integer id) {
        Optional<Comercial> opComercial = comercialDAO.find(id);

        if(opComercial.isPresent()) {
            return opComercial.get();
        } else {
            return null;
        }
    }

    public List<Pedido> pedidosComercial (Integer id) {
        List<Pedido> listaPedidosComercial = pedidoDAO.findByIdComercial(id);

        return listaPedidosComercial;
    }

    public long getTotalPedidosComercial (Integer id) {
        List<Pedido> listaPedidosComercial = pedidosComercial(id);
        return listaPedidosComercial.size();
    }

    public double mediaPedidosComercial(Integer id) {
        List<Pedido> listaPedidosComercial = pedidosComercial(id);
        OptionalDouble optMediaPedidosComercial = listaPedidosComercial.stream()
                .mapToDouble(p -> p.getTotal())
                .average();

        return optMediaPedidosComercial.orElse(0.0);
    }


    public Pedido pedidoMaximoComercial (Integer id) {
        List<Pedido> listaPedidosComercial = pedidosComercial(id);


        Pedido pedidoMaximo = listaPedidosComercial.stream()
                .sorted(comparing(Pedido::getTotal).reversed())
                .limit(1)
                .toList().get(0);

        return pedidoMaximo;
    }

    public Pedido pedidoMinimoComercial (Integer id) {
        List<Pedido> listaPedidosComercial = pedidosComercial(id);

        Pedido pedidoMinimo = listaPedidosComercial.stream()
                .sorted(comparing(Pedido::getTotal))
                .limit(1)
                .toList().get(0);

        return pedidoMinimo;
    }

    public  Map<Cliente, Double> totalPorClienteOrdenado (Integer id) {
        List<Pedido> listaPedidosComercial = pedidosComercial(id);

        Map<Cliente, Double> totalPorClienteOrdenado = listaPedidosComercial.stream()
                //Mete en el map el cliente y el total de la suma de sus pedidos con summingDouble
                .collect(Collectors.groupingBy(Pedido::getCliente, Collectors.summingDouble(Pedido::getTotal)))
                //Ordena según los valores con comparingByValue (de mayor a menor porque es reversed())
                .entrySet().stream()
                .sorted(Map.Entry.<Cliente, Double>comparingByValue().reversed())
                //Usa el orden de los values para guardar el mapa con LinkedHasMap::new (nuevo mapa que siga ese orden)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        return totalPorClienteOrdenado;
    }




}
