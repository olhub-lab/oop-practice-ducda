package constant;

public class CustomerSqlConstant {

  private CustomerSqlConstant() {
  }

  public static final String INSERT_CUSTOMER =
      "INSERT INTO " + CustomerEntityConstant.TABLE_NAME + "(" + CustomerEntityConstant.COLUMN_ID
          + ","
          + CustomerEntityConstant.COLUMN_NAME + ","
          + CustomerEntityConstant.COLUMN_ADDRESS + ","
          + CustomerEntityConstant.COLUMN_PHONE_NUMBER + ","
          + CustomerEntityConstant.COLUMN_BALANCE + ") VALUES(?,?,?,?,?)";

  public static final String UPDATE_CUSTOMER =
      "UPDATE " + CustomerEntityConstant.TABLE_NAME + " SET "
          + CustomerEntityConstant.COLUMN_NAME + "=?,"
          + CustomerEntityConstant.COLUMN_ADDRESS + "=?,"
          + CustomerEntityConstant.COLUMN_PHONE_NUMBER + "=?,"
          + CustomerEntityConstant.COLUMN_BALANCE + "=? "
          + " WHERE " + CustomerEntityConstant.COLUMN_ID + "=?";

  public static final String SELECT_ALL_CUSTOMER =
      "SELECT * FROM " + CustomerEntityConstant.TABLE_NAME;

}
