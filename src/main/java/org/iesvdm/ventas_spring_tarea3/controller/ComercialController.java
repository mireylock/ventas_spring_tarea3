package org.iesvdm.ventas_spring_tarea3.controller;

import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;
import org.iesvdm.ventas_spring_tarea3.dto.ComercialDTO;
import org.iesvdm.ventas_spring_tarea3.mapstrut.ComercialMapper;
import org.iesvdm.ventas_spring_tarea3.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import static java.util.Comparator.comparing;

@Controller

public class ComercialController {
    @Autowired
    private ComercialService comercialService;

    @Autowired
    private ComercialMapper comercialMapper;

    @GetMapping("/comerciales")
    public String listar (Model model) {

        List<Comercial> listAllComerciales = comercialService.listAll();
        model.addAttribute("listaComerciales", listAllComerciales);

        return "comerciales";
    }

    @GetMapping("/comerciales/{id}")
    public String detalle (Model model, @PathVariable Integer id) {
        Comercial comercial = comercialService.detailComercial(id);
        //model.addAttribute("comercial", comercial);

        //Se añaden los pedidos del comercial al detalle del comercial
        List<Pedido> listaPedidosComercial = comercialService.pedidosComercial(id);
        model.addAttribute("listaPedidosComercial", listaPedidosComercial);

        long totalPedidosComercial = listaPedidosComercial.size();
        double mediaPedidosComercial = comercialService.mediaPedidosComercial(id);
        List<Pedido> pedidoMaximo = comercialService.pedidoMaximoComercial(id);
        List<Pedido> pedidoMinimo = comercialService.pedidoMinimoComercial(id);


        ComercialDTO comercialDTO = comercialMapper.comercialAComercialDTO(comercial, totalPedidosComercial, mediaPedidosComercial, pedidoMaximo, pedidoMinimo);
        model.addAttribute("comercialDTO", comercialDTO);

        Map<Cliente, Double> totalPorCliente = comercialService.totalPorCliente(id);
        model.addAttribute("totalPorCliente", totalPorCliente);

        return "detalle-comercial";
    }


    @GetMapping("/comerciales/crear")
    public String crear (Model model) {
        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        //Va al formulario para insertar los datos del nuevo comercial
        return "crear-comercial";
    }

    @PostMapping("/comerciales/crear")
    public RedirectView submitCrear (@ModelAttribute("comercial") Comercial comercial) {
        comercialService.createComercial(comercial);

        //Devuelve al listado con to dos los comerciales tras crear un nuevo comercial
        return new RedirectView("/comerciales");
    }

    @GetMapping("/comerciales/editar/{id}")
    public String editar (Model model, @PathVariable Integer id) {
        Comercial comercial = comercialService.detailComercial(id);
        model.addAttribute("comercial", comercial);

        return "editar-comercial";
    }

    @PostMapping("/comerciales/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("comercial") Comercial comercial) {
        comercialService.replaceComercial(comercial);

        //Devuelve al listado con to dos los comerciales tras editar un comercial
        return new RedirectView("/comerciales");
    }

    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {
        comercialService.deleteComercial(id);

        //Devuelve al listado con to dos los comerciales tras editar un comercial
        return new RedirectView("/comerciales");
    }
}
