package price.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import price.model.Price;
import price.repository.PriceRepository;
import product.model.Product;

import java.util.List;

@Component
public class PriceDAOImpl implements PriceDAO {

    @Autowired
    PriceRepository priceRepository;

    @Override
    public Price createEntryPrice(Price price) {

        /*TODO crear una query para filtrar por producto, precio y fecha
          -> no quiero crear una entrada de precio con el mismo precio que hay actualmente pero si puede volver a tener un precio anterior
          -> se puede actualizar una vez cada día ¿?
        */
        List<Price> prices = priceRepository.findByProductOrderByDateDesc(price.getProduct());
        Price lastPrice = prices.get(prices.size()-1);
        if(lastPrice.getProduct() != price.getProduct() &&  lastPrice.getPrice() != price.getPrice()) {
            return priceRepository.save(price);
        }
        return null;
    }

    @Override
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Price getLastPriceOfProduct(long id) {
        return null;
    }

    @Override
    public List<Price> getAllPricesOfProduct(Product product) {
        return priceRepository.findPricesByProduct(product);
    }
}
