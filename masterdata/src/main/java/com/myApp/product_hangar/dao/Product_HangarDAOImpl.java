package com.myApp.product_hangar.dao;

import com.myApp.product_hangar.model.Product_Hangar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myApp.product_hangar.repository.Product_HangarRepository;

import java.util.List;

@Component
public class Product_HangarDAOImpl implements Product_HangarDAO {

    @Autowired
    private Product_HangarRepository product_hangarRepository;

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
        return product_hangarRepository.saveAndFlush(update);
    }

    @Override
    public Boolean deleteRelationship(Product_Hangar product_hangar) {
        product_hangarRepository.delete(product_hangar);
        return (product_hangarRepository.findByProductAndHangar(product_hangar.getProduct(), product_hangar.getHangar()) == null);
    };

    @Override
    public Boolean isProductLinkToHangar(long idProduct) {
        List<Product_Hangar> hangarsWithProduct = product_hangarRepository.findAllByProduct(idProduct);
        return !hangarsWithProduct.isEmpty();
    }
}
