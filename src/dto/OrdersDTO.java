/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package dto;

import javax.json.JsonArray;

public class OrdersDTO {
    private String orderId;
    private String orderDate;
    private String customerId;
    private double cost;
    private JsonArray orderItems;

    public OrdersDTO() {
    }

    public OrdersDTO(String orderId, String orderDate, String customerId, double cost, JsonArray orderItems) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.cost = cost;
        this.orderItems = orderItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public JsonArray getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(JsonArray orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerId='" + customerId + '\'' +
                ", cost=" + cost +
                ", orderItems=" + orderItems +
                '}';
    }
}