package product_hangar.service;

import hangar.service.HangarServiceImpl;
import product.service.ProductServiceImpl;
import product_hangar.dao.Product_HangarDAO;
import product_hangar.model.Product_Hangar;
import hangar.exceptions.HangarException;
import product.exceptions.ProductException;
import product_hangar.exceptions.Product_HangarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Product_HangarServiceImpl implements Product_HangarService {

    @Autowired
    Product_HangarDAO product_hangarDAO;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    HangarServiceImpl hangarService;

    @Override
    public Product_Hangar associateProductToHangar(Product_Hangar product_hangar) {
        if(hangarService.hangarExistById(product_hangar.getHangar()) && productService.existProduct(product_hangar.getProduct()))
            return product_hangarDAO.addProductToHangar(product_hangar);
        throw new Product_HangarException.Product_HangarNotExistException();
    }

    @Override
    public List<Product_Hangar> getAll() {
        return product_hangarDAO.getAll();
    }

    @Override
    public List<Product_Hangar> getProductsOfHangar(long id) {
        if(hangarService.hangarExistById(id)) {
            List<Product_Hangar> result = product_hangarDAO.getProductsOfHangar(id);
            if(result != null)
                return result;
            throw new Product_HangarException.HangarNotAssociatedException(id);
        }
        throw new HangarException.HangarNotFoundException(id);
    }

    @Override
    public List<Product_Hangar> getHangarsOfProduct(long id) {
        if(productService.existProduct(id)) {
            List<Product_Hangar> result = product_hangarDAO.getHangarsOfProduct(id);
            if(result != null)
                return result;
            throw new Product_HangarException.ProductNotAssociatedException(id);
        }
        throw new ProductException.NotFound(id);
    }

    @Override
    public Product_Hangar updateAmount(long product, long hangar, long amount) {
        Product_Hangar update = product_hangarDAO.getRelationship(product, hangar);
        if(update != null) {
            update.setAmount(amount);
            return product_hangarDAO.updateAmount(update);
        }
        throw new Product_HangarException.ProductAndHangarNotAssociatedException();
    }

    @Override
    public Product_Hangar unlinkProductOfHangar(long product, long hangar) {
        Product_Hangar delete = product_hangarDAO.getRelationship(product, hangar);
        if(delete != null) {
            product_hangarDAO.deleteRelationship(delete);
            return delete;
        }
        throw new Product_HangarException.ProductAndHangarNotAssociatedException();
    }
}
