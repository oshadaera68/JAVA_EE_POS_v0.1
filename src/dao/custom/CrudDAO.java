/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package dao.custom;

import javax.json.JsonArray;
import java.sql.SQLException;

public interface CrudDAO<Y, ID> {
    JsonArray getAll() throws SQLException, ClassNotFoundException;

    boolean add(Y y) throws SQLException, ClassNotFoundException;

    boolean update(Y y) throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;
}
