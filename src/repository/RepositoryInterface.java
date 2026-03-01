package repository;

import java.util.ArrayList;

public interface RepositoryInterface<T> {

  public int insert(T object);

  public int update(T object);

  public int delete(T object);

  public ArrayList<T> selectAll();
}
