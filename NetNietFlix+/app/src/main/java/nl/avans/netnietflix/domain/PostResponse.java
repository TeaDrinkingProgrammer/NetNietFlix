package nl.avans.netnietflix.domain;

import com.google.gson.annotations.SerializedName;

public class PostResponse {
    @SerializedName("success")
    Boolean isSuccessful;
    @SerializedName("status_code")
    String statusCode;
    @SerializedName("status_message")
    String statusMessage;

    public PostResponse(Boolean isSuccessful, String statusCode, String statusMessage) {
        this.isSuccessful = isSuccessful;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public Boolean isSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
