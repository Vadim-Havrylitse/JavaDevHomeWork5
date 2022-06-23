package JavaDevHomeWork5.order.model;

public enum OrderStatus {
    PLACED,
    APPROVED,
    DELIVERED;

    public static String getNameInLowerCase(int index){
        return OrderStatus.values()[index-1].name().toLowerCase();
    }
}
