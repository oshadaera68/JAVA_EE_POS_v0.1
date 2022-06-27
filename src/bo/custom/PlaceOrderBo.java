/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package bo.custom;

import dto.OrdersDTO;

import java.sql.SQLException;

public interface PlaceOrderBo {
    boolean addOrder(OrdersDTO dto) throws SQLException;

    boolean addOrderDetail(OrdersDTO dto);

    boolean updateItemQty(String itemId, int orderQty);

}
