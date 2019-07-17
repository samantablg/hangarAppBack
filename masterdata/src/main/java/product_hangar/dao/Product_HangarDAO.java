package product_hangar.dao;

import product_hangar.model.Product_Hangar;

import java.util.List;

public interface Product_HangarDAO {

    Product_Hangar addProductToHangar(Product_Hangar product_hangar);

    Product_Hangar getRelationship(long product, long hangar);

    List<Product_Hangar> getAll();

    List<Product_Hangar> getProductsOfHangar(long hangar);

    List<Product_Hangar> getHangarsOfProduct(long product);

    Product_Hangar updateAmount(Product_Hangar update);

    //boolean existRelationship(long product, long hangar);

    void deleteRelationship(Product_Hangar product_hangar);

}
