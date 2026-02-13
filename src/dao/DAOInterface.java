package dao;

import java.util.ArrayList;

public interface DAOInterface <Object> {
  public int insert(Object object);

  public int update(Object object);

  public int delete(Object object);

  public ArrayList<Object> selectAll();
}
