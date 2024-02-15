package org.iesvdm.ventas_spring_tarea3.service;

import org.iesvdm.ventas_spring_tarea3.dao.ClienteHasComercialDAOImpl;
import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.ClienteHasComercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteHasComercialService {
    @Autowired
    private ClienteHasComercialDAOImpl clienteHasComercialDAO;

    public void createClienteHasComercial (ClienteHasComercial clienteHasComercial) {
        clienteHasComercialDAO.create_CON_RECARGA_DE_ID_POR_PS(clienteHasComercial);
    }

    public List<ClienteHasComercial> listAll () {
        return clienteHasComercialDAO.getAll();
    }


}
