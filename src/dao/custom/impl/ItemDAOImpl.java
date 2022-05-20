/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package dao.custom.impl;

import entity.Item;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAOImpl implements CrudDAO<Item, String> {
    @Override
    public JsonArray getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item");
        Item i = null;
        JsonArrayBuilder itemArrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder itemObjectBuilder = Json.createObjectBuilder();
        while (rst.next()) {
            i = new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4)
            );
            itemObjectBuilder.add("id", i.getCode());
            itemObjectBuilder.add("name", i.getName());
            itemObjectBuilder.add("address", i.getUnitPrice());
            itemObjectBuilder.add("salary", i.getQtyOnHand());
            itemArrayBuilder.add(itemObjectBuilder.build());
        }
        return itemArrayBuilder.build();
    }

    @Override
    public boolean add(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO item VALUES (?,?,?,?)", i.getCode(), i.getName(), i.getUnitPrice(), i.getQtyOnHand());
    }

    @Override
    public boolean update(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE item SET name=?, unitPrice=?,qtyOnHand=?,code=?", i.getName(), i.getUnitPrice(), i.getQtyOnHand(), i.getCode());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM item WHERE code=?", code);
    }
}
