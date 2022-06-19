package JavaDevHomeWork5.store.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order implements Serializable
{

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("petId")
    @Expose
    private long petId;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("shipDate")
    @Expose
    private String shipDate;
    @SerializedName("status")
    @Expose
    private OrderStatus status;
    @SerializedName("complete")
    @Expose
    private boolean complete;

    private final static long serialVersionUID = 4966993945821310921L;

}
