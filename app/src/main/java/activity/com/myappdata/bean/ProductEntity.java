package activity.com.myappdata.bean;

import java.io.Serializable;

public class ProductEntity implements Serializable {
//     productEntity.setProductName("我是一个商品哈哈哈哈或或或" + i);
//        productEntity.setProductImage("https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=71cd4229be014a909e3e41bd99763971/472309f7905298221dd4c458d0ca7bcb0b46d442.jpg");
//        productEntity.setProductPrice("99." + i);


    public ProductEntity() {
    }

    public ProductEntity(String productName, String productImage, String productPrice) {
        ProductName = productName;
        ProductImage = productImage;
        ProductPrice = productPrice;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "ProductName='" + ProductName + '\'' +
                ", ProductImage='" + ProductImage + '\'' +
                ", ProductPrice='" + ProductPrice + '\'' +
                '}';
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    private String ProductName;
    private String ProductImage;
    private String ProductPrice;
}
