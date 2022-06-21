package JavaDevHomeWork5.user.model;


import JavaDevHomeWork5.utill.ModelService;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.StringJoiner;

@Data
@Builder
public class User implements Serializable, ModelService {
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

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + formatOutputData(id))
                .add("username='" + formatOutputData(username) + "'")
                .add("firstName='" + formatOutputData(firstName) + "'")
                .add("lastName='" + formatOutputData(lastName) + "'")
                .add("email='" + formatOutputData(email) + "'")
                .add("password='" + formatOutputData(password) + "'")
                .add("phone='" + formatOutputData(phone) + "'")
                .add("userStatus=" + formatOutputData(userStatus))
                .toString();
    }

    private static final long serialVersionUID = 1231234444121312134L;
}
