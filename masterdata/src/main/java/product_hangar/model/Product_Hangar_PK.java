package product_hangar.model;

import java.io.Serializable;
import java.util.Objects;


public class Product_Hangar_PK implements Serializable {

    private static final long serialVersionUID = -3064410667944568906L;

    private long hangar;

    private long product;

    public Product_Hangar_PK(long hangar_pk, long product_pk) {
        this.hangar = hangar_pk;
        this.product = product_pk;
    }

    public Product_Hangar_PK() { }

    public long getHangar() {
        return hangar;
    }

    public void setHangar(long hangar) {
        this.hangar = hangar;
    }

    public long getProduct() {
        return product;
    }

    public void setProduct(long product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product_Hangar_PK that = (Product_Hangar_PK) o;
        return hangar == that.hangar &&
                product == that.product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hangar, product);
    }
}
