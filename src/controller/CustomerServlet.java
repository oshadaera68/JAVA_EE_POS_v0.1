/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package controller;

import dao.custom.CrudDAO;
import dao.custom.DAOFactory;
import entity.Customer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    CrudDAO<Customer, String> customerCrudDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            resp.getWriter().print(customerCrudDAO.getAll());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* resp.setContentType("application/json");*/
        try {
            Customer c = new Customer(req.getParameter("txtCusID"), req.getParameter("txtName"), req.getParameter("txtAddress"), Double.parseDouble(req.getParameter("txtSalary")));
            resp.getWriter().print(customerCrudDAO.add(c));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JsonObject cusJson = Json.createReader(req.getReader()).readObject();
            Customer c = new Customer(cusJson.getString("id"), cusJson.getString("name"), cusJson.getString("address"), Double.parseDouble(cusJson.getString("salary")));
            if (customerCrudDAO.update(c)) {

            } else {

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (customerCrudDAO.delete(req.getParameter("cusId"))) {

            } else {

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
