package dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface DAOInterface<T> {

  public int insert(T object);

  public int update(T object);

  public int delete(T object);

  public ArrayList<T> selectAll();
}
