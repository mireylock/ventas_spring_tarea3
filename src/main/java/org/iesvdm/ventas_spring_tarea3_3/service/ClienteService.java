package org.iesvdm.ventas_spring_tarea3_3.service;

import org.iesvdm.ventas_spring_tarea3_3.dao.ClienteDAOImpl;
import org.iesvdm.ventas_spring_tarea3_3.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteDAOImpl clienteDAO;

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
        clienteDAO.find(cliente.getId());
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

}
