package org.iesvdm.ventas_spring_tarea3_3.dao;

import org.iesvdm.ventas_spring_tarea3_3.domain.Pedido;
import org.iesvdm.ventas_spring_tarea3_3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3_3.domain.Comercial;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilDAO {
    public static Pedido newPedido(ResultSet rs) throws SQLException {
        return new org.iesvdm.ventas_spring_tarea3_3.domain.Pedido(rs.getInt("id"),
                rs.getDouble("total"),
                rs.getDate("fecha"),
                new Cliente(rs.getInt("C.id"),
                        rs.getString("C.nombre"),
                        rs.getString("C.apellido1"),
                        rs.getString("C.apellido2"),
                        rs.getString("C.ciudad"),
                        rs.getInt("C.categoría")
                ),
                new Comercial(rs.getInt("CO.id"),
                        rs.getString("CO.nombre"),
                        rs.getString("CO.apellido1"),
                        rs.getString("CO.apellido2"),
                        rs.getFloat("CO.comisión")
                )
        );
    }

    public static Cliente newCliente(ResultSet rs) throws SQLException {
        return new Cliente (rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("apellido1"),
            rs.getString("apellido2"),
            rs.getString("ciudad"),
            rs.getInt("categoría")
        );
    }

    public static Comercial newComercial(ResultSet rs)  throws  SQLException {
        return new Comercial (rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido1"),
                rs.getString("apellido2"),
                rs.getFloat("comisión")
        );
    }
}
