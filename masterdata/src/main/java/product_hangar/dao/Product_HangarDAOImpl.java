package product_hangar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product_hangar.model.Product_Hangar;
import product_hangar.repository.Product_HangarRepository;

import java.util.List;

@Component
public class Product_HangarDAOImpl implements Product_HangarDAO {

    @Autowired
    Product_HangarRepository product_hangarRepository;

    @Override
    public Product_Hangar addProductToHangar(Product_Hangar product_hangar) {
        return product_hangarRepository.save(product_hangar);
    }

    @Override
    public Product_Hangar getRelationship(long product, long hangar) {
        return product_hangarRepository.findByProductAndHangar(product, hangar);
    }

    @Override
    public List<Product_Hangar> getAll() {
        return product_hangarRepository.findAll();
    }

    @Override
    public List<Product_Hangar> getProductsOfHangar(long hangar) {
        return product_hangarRepository.findAllByHangar(hangar);
    }

    @Override
    public List<Product_Hangar> getHangarsOfProduct(long product) {
        return product_hangarRepository.findAllByProduct(product);
    }

    @Override
    public Product_Hangar updateAmount(Product_Hangar update) {
        return product_hangarRepository.save(update);
    }

    /*@Override
    public boolean existRelationship(long product, long hangar) {
        return product_hangarRepository.existsByProductAndHangar(product, hangar);
    }*/

    @Override
    public void deleteRelationship(Product_Hangar product_hangar) {
        product_hangarRepository.delete(product_hangar);
    }
}
