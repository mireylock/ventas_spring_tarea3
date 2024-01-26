package org.iesvdm.ventas_spring_tarea3.mapstrut;

import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;
import org.iesvdm.ventas_spring_tarea3.dto.ClienteDTO;
import org.iesvdm.ventas_spring_tarea3.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "comercialesConteoPedidos", source = "comercialesConteoPedidosIn")
    @Mapping(target = "pedidosTrimestre", source = "pedidosTrimestreIn")
    @Mapping(target = "pedidosSemestre", source = "pedidosSemestreIn")
    @Mapping(target = "pedidosAnio", source = "pedidosAnioIn")
    @Mapping(target = "pedidosLustro", source = "pedidosLustroIn")

    public ClienteDTO clienteAClienteDTO (Cliente cliente, Map<Comercial, Long> comercialesConteoPedidosIn, Long pedidosTrimestreIn, Long pedidosSemestreIn, Long pedidosAnioIn, Long pedidosLustroIn);

    public Cliente clienteDTOACliente (ClienteDTO clienteDTO);
}
