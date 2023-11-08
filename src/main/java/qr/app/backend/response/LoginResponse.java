package qr.app.backend.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String email;
    private boolean success;
    private String token;
    private String message;
    public LoginResponse(String email, String message, boolean success){
        this.email = email;
        this.message = message;
        this.success = success;
        this.token = null;

    }

    public LoginResponse(String email, boolean success, String token, String message) {
        this.email = email;
        this.success = success;
        this.token = token;
        this.message = message;
    }

    public LoginResponse(String message, boolean b) {
        this.message = message;
        this.success = b;
    }

    public static LoginResponse fail(String message) {
        return new LoginResponse(message, false);
    }
}
