package com.myApp.product_hangar.service;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
import com.myApp.exception.GeneralException;
import com.myApp.hangar.service.HangarServiceImpl;
import com.myApp.model.Product;
import com.myApp.product.service.ProductServiceImpl;
import com.myApp.product_hangar.builder.ProductName_HangarDtoBuilder;
import com.myApp.product_hangar.dao.Product_HangarDAO;
import com.myApp.product_hangar.dto.ProductName_HangarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    @Override
    public Product_Hangar associateProductToHangar(Product_Hangar product_hangar) {
        if (hangarService.hangarExistById(product_hangar.getHangar()) && productService.existProduct(product_hangar.getProduct()))
            return product_hangarDAO.addProductToHangar(product_hangar);
        throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    @Override
    public List<Product_Hangar> getAll() {
        List<Product_Hangar> products_hangars = product_hangarDAO.getAll();
        if (!products_hangars.isEmpty())
            return products_hangars;
        throw new ApplicationException(ApplicationExceptionCause.PROD_HANG_UNLINK);
    }

    @Override
    public List<Product_Hangar> getProductsOfHangar(long id_hangar) {
        if (hangarService.hangarExistById(id_hangar)) {
            List<Product_Hangar> products_hangar = product_hangarDAO.getProductsOfHangar(id_hangar);
            if (!products_hangar.isEmpty())
                return products_hangar;
            throw new ApplicationException(ApplicationExceptionCause.PROD_HANG_UNLINK);
        } throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    @Override
    public List<ProductName_HangarDto> getNameOfProductsOfHangar(long id_hangar) {
        if (hangarService.hangarExistById(id_hangar)) {
            List<Product_Hangar> products_hangar = product_hangarDAO.getProductsOfHangar(id_hangar);
            if (!products_hangar.isEmpty()) {
                List<String> name_products = getNameOfProducts(products_hangar);
                return buildProductsWithNameOfHangar(products_hangar, name_products);
            }
            throw new ApplicationException(ApplicationExceptionCause.HANG_UNLINK);
        } throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    @Override
    public List<Product_Hangar> getHangarsOfProduct(long id_product) {
        if (productService.existProduct(id_product)) {
            List<Product_Hangar> product_hangars = product_hangarDAO.getHangarsOfProduct(id_product);
            if (!product_hangars.isEmpty())
                return product_hangars;
            throw new ApplicationException(ApplicationExceptionCause.PROD_UNLINK);
        } throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    @Override
    public Product_Hangar updateAmount(long product, long hangar, long amount) {
        if (product_hangarDAO.isProductLinkToHangar(product, hangar)) {
            Product_Hangar product_hangar = product_hangarDAO.getRelationship(product, hangar);
            product_hangar.setAmount(amount);
            return product_hangarDAO.updateAmount(product_hangar);
        } throw new ApplicationException(ApplicationExceptionCause.PROD_HANG_UNLINK);
    }

    @Override
    public void unlinkProductOfHangar(long product, long hangar) {
        if (product_hangarDAO.isProductLinkToHangar(product, hangar)) {
            Product_Hangar product_hangar = product_hangarDAO.getRelationship(product, hangar);
            product_hangarDAO.deleteRelationship(product_hangar);
        } throw new ApplicationException(ApplicationExceptionCause.PROD_HANG_UNLINK);
    }

    @Override
    public boolean isProductLinkToHangar(long id_product) { //TODO
        if (productService.existProduct(id_product))
            return product_hangarDAO.isProductLinkToAnyHangar(id_product);
        throw new ApplicationException(ApplicationExceptionCause.PROD_HANG_UNLINK);
    }

    @Override
    public List<Product> getProductsUnlinkOfHangar(long id_hangar) { // usando la query sólo obtengo los productos no asociados a este hangar pero si asociados a otro hangar, por eso lo hago de este modo

        List<Product> products = productService.getAllActiveProducts();
        List<Product_Hangar> products_hangar = getProductsOfHangar(id_hangar);

        if (!products_hangar.isEmpty()) {
            List<Product> _products = this.getProductsLinksToHangar(products_hangar);
            return this.filterProductsNotContentInBothArrays(products, _products);
        } throw new ApplicationException(ApplicationExceptionCause.HANG_UNLINK);
    }

    @Override
    public Product_Hangar updateAmountAfterOrder(long product, long hangar, long amount) {
        Product_Hangar product_hangar = product_hangarDAO.getRelationship(product, hangar);
        long _amount = product_hangar.getAmount() - amount;
        if (_amount >= 0) {
            product_hangar.setAmount(_amount);
            return product_hangarDAO.updateAmount(product_hangar);
        } throw new ApplicationException(ApplicationExceptionCause.NOT_STOCK);
    }

    private List<String> getNameOfProducts(List<Product_Hangar> products_hangar) {
        return products_hangar.stream()
                .map(product_hangar ->
                        productService.getNameOfProductById(product_hangar.getProduct())
                ).collect(Collectors.toList());
    }

    private List<ProductName_HangarDto> buildProductsWithNameOfHangar(List<Product_Hangar> productsOfHangar, List<String> name_products) { // pensar otra vez la lógica de esta función
        AtomicInteger i = new AtomicInteger();
        return productsOfHangar.stream()
                .map( product_hangar -> {
                    String name = name_products.get(i.get());
                    i.getAndIncrement();
                    return new ProductName_HangarDtoBuilder(product_hangar, name).getProductName_hangarDto_hangarDto();
                }).collect(Collectors.toList());
    }

    private void deleteProductIfIsNotLinkToHangar(long id_product) { //Logic delete -> Mover de aquí
        productService.updateState(id_product);
    }

    private List<Product> getProductsLinksToHangar(List<Product_Hangar> products_hangar) {
        return products_hangar.stream()
                .map( product_hangar ->
                        productService.getProduct(product_hangar.getProduct())
                ).collect(Collectors.toList());
    }

    private List<Product> filterProductsNotContentInBothArrays(List<Product> products, List<Product> _products) {
        return products.stream()
                .filter( product -> !_products.contains(product))
                .collect(Collectors.toList());
    }
}