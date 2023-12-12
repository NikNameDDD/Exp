package constants;

public class DatabaseRequests {

    public static final String UPDATE_CLIENT_STATUS_TO_NOT_REGISTERED = "UPDATE \"user-service\".client " +
            "SET client_status='NOT_REGISTERED' WHERE mobile_phone= '%s'";
    public static final String UPDATE_CLIENT_STATUS_TO_NOT_ACTIVE = "UPDATE \"user-service\".client " +
            "SET client_status='NOT_ACTIVE' WHERE mobile_phone= '%s'";
    public static final String DELETE_FROM_VERIFICATION = "DELETE FROM \"user-service\".verification WHERE mobile_phone= '%s'";
    public static final String DELETE_FROM_SMS_BLOCK_SENDING = "DELETE FROM \"user-service\".sms_block_sending WHERE = '%s'";
    public static final String  DELETE_FROM_CLIENT = "DELETE FROM \"user-service\".client WHERE id = '%s'";
    public static final String  DELETE_FROM_USER_PROFILE = "DELETE FROM \"user-service\".user_profile WHERE client_id = '%s'";
    public static final String  DELETE_FROM_FINGERPRINT = "DELETE FROM \"user-service\".fingerprint WHERE client_id = '%s'";
}
