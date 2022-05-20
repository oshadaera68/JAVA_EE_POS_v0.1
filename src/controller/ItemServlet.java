/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package controller;

import dao.custom.impl.CrudDAO;
import dao.custom.impl.DAOFactory;
import entity.Item;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    CrudDAO<Item, String> itemCrudDao = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            resp.getWriter().print(itemCrudDao.getAll());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* resp.setContentType("application/json");*/
        try {
            Item i = new Item(req.getParameter("txtItemCode"), req.getParameter("txtItemName"), Double.parseDouble(req.getParameter("txtUnitPrice")), Integer.parseInt(req.getParameter("txtQtyOnHand")));
            resp.getWriter().print(itemCrudDao.add(i));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JsonObject cusJson = Json.createReader(req.getReader()).readObject();
            Item c = new Item(cusJson.getString("code"), cusJson.getString("name"), Double.parseDouble(cusJson.getString("unitPrice")), Integer.parseInt(cusJson.getString("salary")));
            if (itemCrudDao.update(c)) {

            } else {

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (itemCrudDao.delete(req.getParameter("itemCode"))) {

            } else {

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
