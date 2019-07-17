package product.dao;

import java.util.List;

import product.model.Product;

public interface ProductDAO {
	
	List<Product> getAllProducts();
	
	Product getProduct(Long id);
	
	Product createProduct(Product product);
	
	Product deleteProduct(long id);

	boolean existProduct(long id);

	Product updateProduct(Product product);

	boolean existProductByName(Product product);

}
