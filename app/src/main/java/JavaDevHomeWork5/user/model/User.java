package JavaDevHomeWork5.user.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("userStatus")
    @Expose
    private int userStatus;

    private static final long serialVersionUID = 1231234444121312134L;
}
