package com.myApp.product_hangar.service;

import com.myApp.hangar.exceptions.HangarException;
import com.myApp.hangar.service.HangarServiceImpl;
import com.myApp.product.exceptions.ProductException;
import com.myApp.model.Product;
import com.myApp.product.service.ProductServiceImpl;
import com.myApp.product_hangar.builder.ProductName_HangarDtoBuilder;
import com.myApp.product_hangar.dao.Product_HangarDAO;
import com.myApp.product_hangar.dto.ProductName_HangarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myApp.product_hangar.exceptions.Product_HangarException;
import com.myApp.product_hangar.model.Product_Hangar;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class Product_HangarServiceImpl implements Product_HangarService {

    @Autowired
    private Product_HangarDAO product_hangarDAO;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private HangarServiceImpl hangarService;

    //TODO Las excepciones que lanzo desde aquí de hangares o productos moverlas a generic exceptions

    @Override
    public Product_Hangar associateProductToHangar(Product_Hangar product_hangar) {
        if(hangarService.hangarExistById(product_hangar.getHangar()) && productService.existProduct(product_hangar.getProduct()))
            return product_hangarDAO.addProductToHangar(product_hangar);
        throw new Product_HangarException.Product_HangarNotExistException();
    }

    @Override
    public List<Product_Hangar> getAll() {
        List<Product_Hangar> productsOfHangars = product_hangarDAO.getAll();
        if (!productsOfHangars.isEmpty())
            return productsOfHangars;
        throw new Product_HangarException.Product_HangarNotExistException();
    }

    @Override
    public List<Product_Hangar> getProductsOfHangar(long id) {
        if(hangarService.hangarExistById(id)) {
            List<Product_Hangar> productsOfHangar = product_hangarDAO.getProductsOfHangar(id);
            if(!productsOfHangar.isEmpty())
                return productsOfHangar;
            throw new Product_HangarException.HangarNotAssociatedException(id);
        }
        throw new HangarException.HangarNotFoundException(id);
    }

    @Override
    public List<ProductName_HangarDto> getNameOfProductsOfHangar(long id) {
        if(hangarService.hangarExistById(id)) {
            List<Product_Hangar> productsOfHangar = product_hangarDAO.getProductsOfHangar(id);
            if (!productsOfHangar.isEmpty()) {
                List<String> nameOfProducts = getNameOfProducts(productsOfHangar);
                return buildProductsWithNameOfHangar(productsOfHangar, nameOfProducts);
            }
            throw new Product_HangarException.HangarNotAssociatedException(id);
        }
        throw new HangarException.HangarNotFoundException(id);
    }

    private List<String> getNameOfProducts(List<Product_Hangar> products_hangar) {
        return products_hangar.stream()
                .map(product_hangar -> productService.getNameOfProductById(product_hangar.getProduct()))
                .collect(Collectors.toList());
    }

    private List<ProductName_HangarDto> buildProductsWithNameOfHangar(List<Product_Hangar> productsOfHangar, List<String> nameOfProducts) {
        AtomicInteger i = new AtomicInteger();
        return productsOfHangar.stream()
                .map((item) -> {
                    String name = nameOfProducts.get(i.get());
                    i.getAndIncrement();
                    return new ProductName_HangarDtoBuilder(item, name).getProductName_hangarDto_hangarDto();
                }).collect(Collectors.toList());
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
    public Boolean unlinkProductOfHangar(long product, long hangar) {
        Product_Hangar delete = product_hangarDAO.getRelationship(product, hangar);
        if(delete != null) {
            return product_hangarDAO.deleteRelationship(delete);
        }
        throw new Product_HangarException.ProductAndHangarNotAssociatedException();
    }

    @Override
    public Boolean isProductLinkToHangar(long idProduct) {
        if(productService.existProduct(idProduct)) {
            if(!product_hangarDAO.isProductLinkToHangar(idProduct)) {
                deleteProductIfIsNotLinkToHangar(idProduct);
                return product_hangarDAO.isProductLinkToHangar(idProduct);
            }
            return product_hangarDAO.isProductLinkToHangar(idProduct);
        }
        throw new ProductException.ProductExistException();
    }

    // Logic delete
    private void deleteProductIfIsNotLinkToHangar(long idProduct) {
        productService.updateState(idProduct);
    }

    @Override //TODO Repensar este código
    public List<Product> getProductsUnlinkOfHangar(long idHangar) {
        List<Product> products = productService.getAllActiveProducts();
        try {
            List<Product_Hangar> productsOfHangar = getProductsOfHangar(idHangar);
            if (!productsOfHangar.isEmpty()) {
                List<Product> prod = productsOfHangar.stream().map(productOfHangar -> productService.getProduct(productOfHangar.getProduct()))
                        .collect(Collectors.toList());
                return products.stream().filter(
                        product -> !prod.contains(product)
                ).collect(Collectors.toList());
            }
        } catch (Exception e) {
            return products;
        }
        return products;
    }

    @Override
    public Product_Hangar updateAmountAfterOrder(long product, long hangar, long amount) {
        Product_Hangar p_h = product_hangarDAO.getRelationship(product, hangar);
        long _amount = p_h.getAmount() - amount;
        if (p_h.getAmount() >= amount && _amount >= 0) {
            p_h.setAmount(_amount);
            return product_hangarDAO.updateAmount(p_h);
        } throw new Product_HangarException.StockException();
    }
}