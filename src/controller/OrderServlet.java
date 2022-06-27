/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package controller;

import bo.BoFactory;
import bo.custom.PlaceOrderBo;
import dao.custom.CrudDAO;
import dao.custom.DAOFactory;
import dto.OrdersDTO;
import entity.Orders;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    CrudDAO<Orders,String> placeOrderDAO =  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    PlaceOrderBo placeOrderController = BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ORDER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            placeOrderDAO.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject orderJSON = Json.createReader(req.getReader()).readObject();
        OrdersDTO order = new OrdersDTO(orderJSON.getString("orderId"),orderJSON.getString("orderDate"),orderJSON.getString("customerId"),Double.parseDouble(orderJSON.getString("cost")),orderJSON.getJsonArray("orderItems"));

        try {
            if (placeOrderController.addOrder(order)) {

            } else {

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}