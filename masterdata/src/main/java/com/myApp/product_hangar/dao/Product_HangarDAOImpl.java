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
    public List<Product_Hangar> getAllNotContentInHangar(long hangar) {
        return product_hangarRepository.findProduct_HangarsByHangarIsNotLike(hangar);
    }

    @Override
    public Product_Hangar addProductToHangar(Product_Hangar product_hangar) {
        return product_hangarRepository.save(product_hangar);
    }

    @Override
    public Product_Hangar getRelationship(long product, long hangar) {
        return product_hangarRepository.findByProductAndHangar(product, hangar);
    }

    @Override
    public Product_Hangar updateAmount(Product_Hangar update) {
        return product_hangarRepository.save(update);
    }

    @Override
    public void deleteRelationship(Product_Hangar product_hangar) {
        product_hangarRepository.delete(product_hangar);
    };

    @Override
    public boolean isProductLinkToAnyHangar(long id_product) {
        return product_hangarRepository.existsProduct_HangarByProduct(id_product);
    }

    @Override
    public boolean isProductLinkToHangar(long product, long hangar) {
        return product_hangarRepository.existsProduct_HangarByHangarAndProduct(product, hangar);
    }
}
