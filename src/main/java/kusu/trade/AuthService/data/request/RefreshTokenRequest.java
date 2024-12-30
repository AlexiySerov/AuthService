package kusu.trade.AuthService.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@RequiredArgsConstructor
@Data
@Jacksonized
public class RefreshTokenRequest {

    @JsonProperty("refresh_token")
    private String refreshToken;
}
