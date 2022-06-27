/**
 * @Owner - Oshada Eranga
 * @Version - v0.1.0
 **/

package bo;

import bo.custom.PlaceOrderBo;
import bo.custom.impl.PlaceOrderBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    public BoFactory() {
    }

    public static BoFactory getBoFactory() {
        return boFactory == null ? boFactory = new BoFactory() : boFactory;
    }

    public PlaceOrderBo getBo(BoTypes boTypes) {
        switch (boTypes) {
            case ORDER:
                return new PlaceOrderBoImpl();
            default:
                return null;
        }
    }


    public enum BoTypes {
        ORDER
    }
}
