/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package bo.custom.impl;

import bo.custom.PlaceOrderBo;
import db.DbConnection;
import dto.ItemDTO;
import dto.OrderDetailsDTO;
import dto.OrdersDTO;

import javax.json.JsonValue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOrderBoImpl implements PlaceOrderBo{
    Connection con = null;
    PreparedStatement stm = null;

    public boolean addOrder(OrdersDTO dto) throws SQLException {
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            stm = con.prepareStatement("INSERT INTO orders VALUES (?,?,?,?)");
            stm.setString(1, dto.getOrderId());
            stm.setString(2, dto.getOrderDate());
            stm.setString(3, dto.getCustomerId());
            stm.setDouble(4, dto.getCost());
            if (stm.executeUpdate() > 0) {
                if (addOrderDetail(dto)) {
                    con.commit();
                    con.setAutoCommit(true);
                    return true;
                } else {
                    con.rollback();
                    con.setAutoCommit(true);
                    return false;
                }
            } else {
                con.rollback();
                con.setAutoCommit(true);
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            con.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public boolean addOrderDetail(OrdersDTO dto) {
        for (JsonValue temp : dto.getOrderItems()) {
            OrderDetailsDTO orderDetail = new OrderDetailsDTO(dto.getOrderId(), temp.asJsonObject().getString("itemCode"), temp.asJsonObject().getString("itemName"), Double.parseDouble("unitPrice"), Integer.parseInt("qty"), Double.parseDouble("total"));
            try {
                stm = con.prepareStatement("INSERT INTO orderdetail VALUES (?,?,?,?,?,?)");
                stm.setString(1, orderDetail.getOrderId());
                stm.setString(2, orderDetail.getItemCode());
                stm.setString(3, orderDetail.getItemName());
                stm.setDouble(4, orderDetail.getUnitPrice());
                stm.setInt(5, orderDetail.getQty());
                stm.setDouble(6, orderDetail.getTotal());

                if (stm.executeUpdate() > 0) {
                    if (updateItemQty(orderDetail.getItemCode(), orderDetail.getQty())) {

                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean updateItemQty(String itemId, int orderQty) {
        try {
            stm = con.prepareStatement("SELECT * FROM item WHERE code=?");
            stm.setString(1, itemId);
            ResultSet rst = stm.executeQuery();
            ItemDTO item = null;
            if (rst.next()) {
                item = new ItemDTO(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4));
            }

            stm = con.prepareStatement("UPDATE item SET qtyOnHand =? WHERE code=?");
            int stock = item.getQtyOnHand() - orderQty;
            stm.setInt(1, stock);
            stm.setString(2, itemId);

            return stm.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
