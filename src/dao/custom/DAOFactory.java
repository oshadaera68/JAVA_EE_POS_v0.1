/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package dao.custom;

import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public CrudDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER
    }
}
