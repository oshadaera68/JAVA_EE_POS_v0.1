/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package dao.custom.impl;

import entity.Customer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CrudDAO<Customer, String> {
    @Override
    public JsonArray getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer");
        Customer c = null;
        JsonArrayBuilder customerArrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder customerObjectBuilder = Json.createObjectBuilder();
        while (rst.next()) {
            c = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            );
            customerObjectBuilder.add("id", c.getId());
            customerObjectBuilder.add("name", c.getName());
            customerObjectBuilder.add("address", c.getAddress());
            customerObjectBuilder.add("salary", c.getSalary());
            customerArrayBuilder.add(customerObjectBuilder.build());
        }
        return customerArrayBuilder.build();
    }

    @Override
    public boolean add(Customer c) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?)", c.getId(), c.getName(), c.getAddress(), c.getSalary());
    }

    @Override
    public boolean update(Customer c) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customer SET name=?, address=?, salary=? WHERE id=?", c.getName(), c.getAddress(), c.getSalary(), c.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE id=?", id);
    }

}
