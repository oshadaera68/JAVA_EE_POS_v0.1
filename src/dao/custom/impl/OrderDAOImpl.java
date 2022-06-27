/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package dao.custom.impl;

import dao.custom.CrudDAO;
import dao.custom.CrudUtil;
import entity.Orders;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements CrudDAO<Orders,String> {
    @Override
    public JsonArray getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM orders");
        JsonArrayBuilder orderArray = Json.createArrayBuilder();
        JsonObjectBuilder orderJSON = Json.createObjectBuilder();
        while (rst.next()) {
            Orders order = new Orders(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4));
            orderJSON.add("orderId", order.getOrderId());
            orderJSON.add("orderDate", order.getOrderDate());
            orderJSON.add("customerId", order.getCustomerId());
            orderJSON.add("cost", order.getCost());
            orderArray.add(orderJSON.build());
        }
        return orderArray.build();
    }

    @Override
    public boolean add(Orders orders) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders orders) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }
}
