package requests;

public enum UserServiceEndpoints {

    REGISTER_NON_EXISTENT_CLIENT("/api/v1/registration/user-profile/new"),
    AUTHENTICATE_USER("/api/v1/login");

    private String url;

    UserServiceEndpoints(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
