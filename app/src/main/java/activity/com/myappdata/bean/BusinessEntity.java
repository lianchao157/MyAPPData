package activity.com.myappdata.bean;

import java.io.Serializable;
import java.util.List;

public class BusinessEntity implements Serializable {

    @Override
    public String toString() {
        return "BusinessEntity{" +
                "BusinessName='" + BusinessName + '\'' +
                ", BusinessImage='" + BusinessImage + '\'' +
                ", products=" + products +
                '}';
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public BusinessEntity(String businessName, String businessImage, List<ProductEntity> products) {
        BusinessName = businessName;
        BusinessImage = businessImage;
        this.products = products;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public String getBusinessImage() {
        return BusinessImage;
    }

    public void setBusinessImage(String businessImage) {
        BusinessImage = businessImage;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    private String BusinessName;
    private String BusinessImage;
    private List<ProductEntity> products;

    public boolean isCollection() {
        return Collection;
    }

    public void setCollection(boolean collection) {
        Collection = collection;
    }

    private  boolean  Collection;//  是否被收藏


    public BusinessEntity() {
    }


}
