package price.service;

import price.model.Price;
import product.model.Product;

import java.util.List;

public interface PriceService {

    Price createEntryPrice(Product product, float price);

    List<Price> getAllPrices();

    Price createEntryPriceToProduct(long id, float price);

    List<Price> getAllPricesOfProduct(long id);

}
