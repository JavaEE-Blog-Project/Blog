package cn.myblog.security.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthToken implements Serializable {

    /**
     * Access token
     */
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * Expired in seconds
     */
    @JsonProperty("expired_in")
    private int expiredIn;

    /**
     * Refresh token
     */
    @JsonProperty("refresh_token")
    private String refreshToken;
}
