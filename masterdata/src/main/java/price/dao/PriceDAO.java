package price.dao;

import price.model.Price;
import product.model.Product;

import java.util.List;

public interface PriceDAO {

    Price createEntryPrice(Price price);

    List<Price> getAllPrices();

    Price getLastPriceOfProduct(long id);

    List<Price> getAllPricesOfProduct(Product product);
}
