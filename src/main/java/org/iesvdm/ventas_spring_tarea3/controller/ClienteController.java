package org.iesvdm.ventas_spring_tarea3.controller;

import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3.dto.ClienteDTO;
import org.iesvdm.ventas_spring_tarea3.mapstrut.ClienteMapper;
import org.iesvdm.ventas_spring_tarea3.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@Controller
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @GetMapping("/clientes")
    public String listar (Model model) {

        List<Cliente> listAllClientes = clienteService.listAll();
        model.addAttribute("listaClientes", listAllClientes);

        return "clientes";
    }

    @GetMapping("/clientes/{id}")
    public String detalle (Model model, @PathVariable Integer id) {
        Cliente cliente = clienteService.detailCliente(id);

        Map<Comercial, Long> comercialesConteoPedidos = clienteService.comercialesConteoPedidos(id);
        Long pedidosTrimestre = clienteService.pedidosTrimestre(id);
        Long pedidosSemestre = clienteService.pedidosSemestre(id);
        Long pedidosAnio = clienteService.pedidosAnio(id);
        Long pedidosLustro = clienteService.pedidosLustro(id);

        ClienteDTO clienteDTO = clienteMapper.clienteAClienteDTO(cliente, comercialesConteoPedidos, pedidosTrimestre, pedidosSemestre, pedidosAnio, pedidosLustro);

        model.addAttribute("clienteDTO", clienteDTO);

        return "detalle-cliente";
    }

    @GetMapping("/clientes/crear")
    public String crear (Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        //Va al formulario para insertar los datos del nuevo cliente
        return "crear-cliente";
    }

    @PostMapping("/clientes/crear")
    public RedirectView submitCrear (@ModelAttribute("cliente") Cliente cliente) {
        clienteService.createCliente(cliente);

        //Devuelve al listado con to dos los clientes tras crear un nuevo cliente
        return new RedirectView("/clientes");
    }

    @GetMapping("/clientes/editar/{id}")
    public String editar (Model model, @PathVariable Integer id) {
        Cliente cliente = clienteService.detailCliente(id);
        model.addAttribute("cliente", cliente);

        return "editar-cliente";
    }

    @PostMapping("/clientes/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.replaceCliente(cliente);

        //Devuelve al listado con to dos los clientes tras editar un cliente
        return new RedirectView("/clientes");
    }

    @PostMapping("/clientes/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {
        clienteService.deleteCliente(id);

        //Devuelve al listado con to dos los clientes tras editar un cliente
        return new RedirectView("/clientes");
    }

}
