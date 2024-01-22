package org.iesvdm.ventas_spring_tarea3.service;

import org.iesvdm.ventas_spring_tarea3.dao.ComercialDAOImpl;
import org.iesvdm.ventas_spring_tarea3.dao.PedidoDAOImpl;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

}