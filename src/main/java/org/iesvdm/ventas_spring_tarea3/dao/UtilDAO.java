package org.iesvdm.ventas_spring_tarea3.dao;

import org.iesvdm.ventas_spring_tarea3.domain.ClienteHasComercial;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;
import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class UtilDAO {
    public static Pedido newPedido(ResultSet rs) throws SQLException {
        return new org.iesvdm.ventas_spring_tarea3.domain.Pedido(rs.getInt("id"),
                rs.getDouble("total"),
                rs.getDate("fecha").toLocalDate(),
                new Cliente(rs.getInt("C.id"),
                        rs.getString("C.nombre"),
                        rs.getString("C.apellido1"),
                        rs.getString("C.apellido2"),
                        rs.getString("C.email"),
                        rs.getString("C.ciudad"),
                        rs.getInt("C.categoría")
                ),
                new Comercial(rs.getInt("CO.id"),
                        rs.getString("CO.nombre"),
                        rs.getString("CO.apellido1"),
                        rs.getString("CO.apellido2"),
                        rs.getBigDecimal("CO.comisión")
                )
        );
    }

    public static Cliente newCliente(ResultSet rs) throws SQLException {
        return new Cliente (rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("apellido1"),
            rs.getString("apellido2"),
            rs.getString("email"),
            rs.getString("ciudad"),
            rs.getInt("categoría")
        );
    }

    public static Comercial newComercial(ResultSet rs)  throws  SQLException {
        return new Comercial (rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido1"),
                rs.getString("apellido2"),
                rs.getBigDecimal("comisión")
        );
    }

    public static ClienteHasComercial newClienteHasComercial(ResultSet rs) throws SQLException {
        return new org.iesvdm.ventas_spring_tarea3.domain.ClienteHasComercial(
                new Cliente(rs.getInt("C.id"),
                        rs.getString("C.nombre"),
                        rs.getString("C.apellido1"),
                        rs.getString("C.apellido2"),
                        rs.getString("C.email"),
                        rs.getString("C.ciudad"),
                        rs.getInt("C.categoría")
                ), new Comercial(rs.getInt("CO.id"),
                rs.getString("CO.nombre"),
                rs.getString("CO.apellido1"),
                rs.getString("CO.apellido2"),
                rs.getBigDecimal("CO.comisión")),
                rs.getDate("fecha_asociacion").toLocalDate(), rs.getInt("prioridad")
        );
    }
}
