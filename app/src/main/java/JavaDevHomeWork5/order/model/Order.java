package JavaDevHomeWork5.order.model;

import java.io.Serializable;
import java.util.StringJoiner;

import JavaDevHomeWork5.utill.ModelService;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order implements Serializable, ModelService {

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

    @Override
    public String toString() {
        return new StringJoiner("\n\t", Order.class.getSimpleName() + "[\n\t", "\n]")
                .add("id=" + formatOutputData(id))
                .add("petId=" + formatOutputData(petId))
                .add("quantity=" + formatOutputData(quantity))
                .add("shipDate='" + formatOutputData(shipDate) + "'")
                .add("status=" + formatOutputData(status))
                .add("complete=" + formatOutputData(complete))
                .toString();
    }

    private final static long serialVersionUID = 4966993945821310921L;

}
