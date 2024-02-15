package org.iesvdm.ventas_spring_tarea3.controller;

import jakarta.validation.Valid;
import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.ClienteHasComercial;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3.dto.ClienteDTO;
import org.iesvdm.ventas_spring_tarea3.mapstrut.ClienteMapper;
import org.iesvdm.ventas_spring_tarea3.service.ClienteHasComercialService;
import org.iesvdm.ventas_spring_tarea3.service.ClienteService;
import org.iesvdm.ventas_spring_tarea3.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @Autowired
    private ComercialService comercialService;

    @Autowired
    private ClienteHasComercialService clienteHasComercialService;


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
    public String submitCrear (@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cliente", cliente);
            return "crear-cliente";
        }
        clienteService.createCliente(cliente);
        List<Cliente> listaClientes = clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);

        //Devuelve al listado con to dos los clientes tras crear un nuevo cliente
        return "/clientes";
    }

    @GetMapping("/clientes/editar/{id}")
    public String editar (Model model, @PathVariable Integer id) {
        Cliente cliente = clienteService.detailCliente(id);
        model.addAttribute("cliente", cliente);

        List<Comercial> listaComerciales = comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);

        ClienteHasComercial clienteHasComercial = new ClienteHasComercial();
        model.addAttribute("clienteHasComercial", clienteHasComercial);

        List<ClienteHasComercial> listaComercialesAsociados = clienteHasComercialService.listAll();
        model.addAttribute("listaComercialesAsociados", listaComercialesAsociados);

        return "editar-cliente";
    }

//    @PostMapping("/clientes/editar/{id}")
//    public String submitEditar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("cliente", cliente);
//            return "editar-cliente";
//        }
//        clienteService.replaceCliente(cliente);
//        List<Cliente> listaClientes = clienteService.listAll();
//        model.addAttribute("listaClientes", listaClientes);
//        //Devuelve al listado con to dos los clientes tras editar un cliente
//        return "/clientes";
//    }

    @PostMapping("/clientes/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute("cliente") Cliente cliente, @Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cliente", cliente);
            model.addAttribute("comercial", comercial);
            return "editar-cliente";
        }
        clienteService.replaceCliente(cliente);
        List<Cliente> listaClientes = clienteService.listAll();
        model.addAttribute("listaClientes", listaClientes);

        //Devuelve al listado con to dos los clientes tras editar un cliente
        return "/clientes";
    }


    @PostMapping("/clientes/aniadir-comercial/{id}")
    public String submitAniadirComercial (@Valid @ModelAttribute("cliente") Cliente cliente, @Valid @ModelAttribute("comercial") Comercial comercial, @Valid @ModelAttribute("cliente_has_comercial") ClienteHasComercial clienteHasComercial, BindingResult bindingResult, Model model) {
        clienteHasComercialService.createClienteHasComercial(clienteHasComercial);
        List<ClienteHasComercial> listaComercialesAsociados = clienteHasComercialService.listAll();
        model.addAttribute("listaComercialesAsociados", listaComercialesAsociados);

        //devuelve al formulario de editar con los comerciales añadidos
        return "editar-cliente";
    }

    @PostMapping("/clientes/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {
        clienteService.deleteCliente(id);

        //Devuelve al listado con to dos los clientes tras editar un cliente
        return new RedirectView("/clientes");
    }

}
