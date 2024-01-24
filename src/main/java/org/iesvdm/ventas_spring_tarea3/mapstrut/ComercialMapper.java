package org.iesvdm.ventas_spring_tarea3.mapstrut;

import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;
import org.iesvdm.ventas_spring_tarea3.dto.ComercialDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComercialMapper {
    @Mapping(target = "id", source = "comercial.id")
    @Mapping(target = "nombre", source = "comercial.nombre")
    @Mapping(target = "apellido", source = "comercial.apellido")
    @Mapping(target = "apellido2", source = "comercial.apellido2")
    @Mapping(target = "comision", source = "comercial.comision")
    @Mapping(target = "totalPedidosComercial", source = "totalPedidosComercialIn")
    @Mapping(target = "mediaPedidosComercial", source = "mediaPedidosComercialIn")
    @Mapping(target = "pedidoMaximoComercial", source = "pedidoMaximoComercialIn")
    @Mapping(target = "pedidoMinimoComercial", source = "pedidoMinimoComercialIn")
    public ComercialDTO comercialAComercialDTO (Comercial comercial, long totalPedidosComercialIn, double mediaPedidosComercialIn, List<Pedido> pedidoMaximoComercialIn, List<Pedido> pedidoMinimoComercialIn);


    public Comercial comercialDTOAComercial (ComercialDTO comercialDTO);

}
