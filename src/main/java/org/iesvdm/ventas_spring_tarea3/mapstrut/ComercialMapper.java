package org.iesvdm.ventas_spring_tarea3.mapstrut;

import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;
import org.iesvdm.ventas_spring_tarea3.dto.ComercialDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComercialMapper {
//    @Mapping(target = "totalPedidosComercial", source = "totalPedidosComercialIn")
//    @Mapping(target = "mediaPedidosComercial", source = "mediaPedidosComercialIn")
    @Mapping(target = "pedidoMaximoComercial", source = "pedidoMaximoComercialIn")
//    @Mapping(target = "pedidoMinimoComercial", source = "pedidoMinimoComercialIn")
    public ComercialDTO comercialAComercialDTO (Comercial comercial, List<Pedido> pedidoMaximoComercialIn);
    public Comercial comercialDTOAComercial (ComercialDTO comercial);

}
