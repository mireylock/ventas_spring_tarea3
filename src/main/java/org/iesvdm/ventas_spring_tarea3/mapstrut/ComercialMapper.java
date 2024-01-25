package org.iesvdm.ventas_spring_tarea3.mapstrut;

import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;
import org.iesvdm.ventas_spring_tarea3.dto.ComercialDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ComercialMapper {
    //hay que pasarle el id de comercial para pasarle el comercial porque pedido tiene también id y no sabe cuál coger
    @Mapping(target = "id", source = "comercial.id")
    @Mapping(target = "totalPedidosComercial", source = "totalPedidosComercialIn")
    @Mapping(target = "mediaPedidosComercial", source = "mediaPedidosComercialIn")
    @Mapping(target = "pedidoMaximoComercial", source = "pedidoMaximoComercialIn")
    @Mapping(target = "pedidoMinimoComercial", source = "pedidoMinimoComercialIn")
    @Mapping(target = "listaPedidosComercial", source = "listaPedidosComercialIn")
    @Mapping(target = "totalPorClienteOrdenado", source = "totalPorClienteOrdenadoIn")
    public ComercialDTO comercialAComercialDTO (Comercial comercial, long totalPedidosComercialIn, double mediaPedidosComercialIn, Pedido pedidoMaximoComercialIn, Pedido pedidoMinimoComercialIn, List<Pedido> listaPedidosComercialIn, Map<Cliente, Double> totalPorClienteOrdenadoIn);

    public Comercial comercialDTOAComercial (ComercialDTO comercialDTO);

}
