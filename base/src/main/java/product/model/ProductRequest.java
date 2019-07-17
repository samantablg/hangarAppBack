package product.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private float price;

    public ProductRequest() { }

    public ProductRequest(String name) {
        this.name = name;
    }

    public ProductRequest(@NotEmpty String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductRequest(@NotEmpty String name, String description, @NotNull float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}