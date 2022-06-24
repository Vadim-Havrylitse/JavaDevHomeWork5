package JavaDevHomeWork5.order.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    @SerializedName("placed")
    PLACED,
    @SerializedName("approved")
    APPROVED,
    @SerializedName("delivered")
    DELIVERED;

    public static OrderStatus getByIndex(int index){
        return OrderStatus.values()[index-1];
    }
}
