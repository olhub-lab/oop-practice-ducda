package constant;

public class VehicleSqlConstant {

  private VehicleSqlConstant() {

  }

  public static final String INSERT_VEHICLE =
      "INSERT INTO" + VehicleEntityConstant.TABLE_NAME + "(" + VehicleEntityConstant.COLUMN_ID + ","
          + VehicleEntityConstant.COLUMN_MODEL + ","
          + VehicleEntityConstant.COLUMN_MANUFACTURER + ","
          + VehicleEntityConstant.COLUMN_YEAR + ","
          + VehicleEntityConstant.COLUMN_BASE_PRICE + ","
          + VehicleEntityConstant.COLUMN_ORIGIN + ", "
          + VehicleEntityConstant.COLUMN_QUANTITY + ","
          + VehicleEntityConstant.COLUMN_TYPE + ","
          + VehicleEntityConstant.COLUMN_TYPE_BICYCLE + ","
          + VehicleEntityConstant.COLUMN_FRAME_MATERIAL + ","
          + VehicleEntityConstant.COLUMN_SEAT + ","
          + VehicleEntityConstant.COLUMN_FUEL + ","
          + VehicleEntityConstant.COLUMN_ENGINE_CAPACITY_CAR + ","
          + VehicleEntityConstant.COLUMN_BODY_TYPE + ","
          + VehicleEntityConstant.COLUMN_ENGINE_CAPACITY_MOTORBIKE + ","
          + VehicleEntityConstant.COLUMN_TYPE_MOTORBIKE
          + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

  public static final String UPDATE_VEHICLE =
      "UPDATE " + VehicleEntityConstant.TABLE_NAME + " SET "
          + VehicleEntityConstant.COLUMN_MODEL + "=?,"
          + VehicleEntityConstant.COLUMN_MANUFACTURER + "=?,"
          + VehicleEntityConstant.COLUMN_YEAR + "=?,"
          + VehicleEntityConstant.COLUMN_BASE_PRICE + "=?,"
          + VehicleEntityConstant.COLUMN_ORIGIN + "=?,"
          + VehicleEntityConstant.COLUMN_QUANTITY + "=?,"
          + VehicleEntityConstant.COLUMN_TYPE + "=?,"
          + " WHERE " + VehicleEntityConstant.COLUMN_ID + "=?";

  public static final String SELECT_ALL_VEHICLE = "SELECT * FROM " + VehicleEntityConstant.TABLE_NAME;
}