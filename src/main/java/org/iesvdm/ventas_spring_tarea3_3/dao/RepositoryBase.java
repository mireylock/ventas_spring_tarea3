package org.iesvdm.ventas_spring_tarea3_3.dao;

import java.util.List;
import java.util.Optional;

public interface RepositoryBase<T> {

    public void create_CON_RECARGA_DE_ID_POR_PS(T t);
    public List<T> getAll();
    public Optional<T> find(int id);
    public void update(T t);
    public void delete (int id);
}
