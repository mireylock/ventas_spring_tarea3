package org.iesvdm.ventas_spring_tarea3.controller;

import jakarta.validation.Valid;
import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;
import org.iesvdm.ventas_spring_tarea3.dto.ComercialDTO;
import org.iesvdm.ventas_spring_tarea3.mapstrut.ComercialMapper;
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

        List<Pedido> listaPedidosComercial = comercialService.pedidosComercial(id);

        if (listaPedidosComercial.isEmpty()) {
            ComercialDTO comercialDTO = comercialMapper.comercialAComercialDTO(comercial, 0, 0.0, null, null, null, null);
            model.addAttribute("comercialDTO", comercialDTO);
        } else {
            long totalPedidosComercial = listaPedidosComercial.size();
            double mediaPedidosComercial = comercialService.mediaPedidosComercial(id);
            Pedido pedidoMaximo = comercialService.pedidoMaximoComercial(id);
            Pedido pedidoMinimo = comercialService.pedidoMinimoComercial(id);
            Map<Cliente, Double> totalPorClienteOrdenado = comercialService.totalPorClienteOrdenado(id);

            ComercialDTO comercialDTO = comercialMapper.comercialAComercialDTO(comercial, totalPedidosComercial, mediaPedidosComercial, pedidoMaximo, pedidoMinimo, listaPedidosComercial, totalPorClienteOrdenado);
            model.addAttribute("comercialDTO", comercialDTO);
        }

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
    public String submitCrear (@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("comercial", comercial);
            return "crear-comercial";
        }

        comercialService.createComercial(comercial);
        List<Comercial> listaComerciales = comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);
        //Devuelve al listado con to dos los comerciales tras crear un nuevo comercial
        return "/comerciales";
    }

    @GetMapping("/comerciales/editar/{id}")
    public String editar (Model model, @PathVariable Integer id) {
        Comercial comercial = comercialService.detailComercial(id);
        model.addAttribute("comercial", comercial);

        return "editar-comercial";
    }

    @PostMapping("/comerciales/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("comercial", comercial);
            return "comerciales-editar";
        }
        comercialService.replaceComercial(comercial);
        List<Comercial> listaComerciales = comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);
        //Devuelve al listado con to dos los comerciales tras editar un comercial
        return "/comerciales";
    }

    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {
        comercialService.deleteComercial(id);

        //Devuelve al listado con to dos los comerciales tras editar un comercial
        return new RedirectView("/comerciales");
    }
}
