package dao;

import java.util.List;

public interface IDao<T, ID> {
    List<T> findAll();
    T findById(ID identifiant);
    T save(T newElement);
    List<T> saveAll(T... elements);
    boolean update(T newValuesElement);
    boolean delete(T element);
    boolean deleteById(ID identifiant);
}
