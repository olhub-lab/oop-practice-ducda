package repository;

import java.util.ArrayList;
import java.util.List;

public interface RepositoryInterface<T> {

  public int insert(T object);

  public int update(T object);

  public int delete(T object);

  public List<T> selectAll();
}
