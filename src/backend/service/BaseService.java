package backend.service;

import java.util.HashSet;
import java.util.List;

public interface BaseService<M> {
    boolean create(M m);
    boolean add(M m);
    void delete(String id);
    void update(M newM, int index);
    List<M> get();
}
