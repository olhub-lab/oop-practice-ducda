package repository;

import java.util.ArrayList;
import model.Customer;

public class CustomerRepository implements RepositoryInterface<Customer>{


  @Override
  public int insert(Customer object) {
    return 0;
  }

  @Override
  public int update(Customer object) {
    return 0;
  }

  @Override
  public int delete(Customer object) {
    return 0;
  }

  @Override
  public ArrayList<Customer> selectAll() {
    return null;
  }
}
