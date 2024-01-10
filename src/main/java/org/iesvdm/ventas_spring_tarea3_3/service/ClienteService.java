package org.iesvdm.ventas_spring_tarea3_3.service;

import org.iesvdm.ventas_spring_tarea3_3.dao.ClienteDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteDAOImpl clienteDAO;


}
