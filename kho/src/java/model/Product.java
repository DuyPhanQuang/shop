/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author QuangDuy
 */
public class Product {
    private int productID;
    private int brandID;
    private String productName;
    private String productImage;
    private double productImportPrice;
    private double productSalePrice;
    private String productDescription;
    private int productQuantity;
    
    public Product() {
        
    }

    public Product(int productID, int brandID, String productName, String productImage, double productImportPrice, double productSalePrice, String productDescription, int productQuantity) {
        this.productID = productID;
        this.brandID = brandID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.productImportPrice = productImportPrice;
        this.productSalePrice = productSalePrice;
        this.productQuantity = productQuantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public double getProductImportPrice() {
        return productImportPrice;
    }

    public void setProductImportPrice(double productImportPrice) {
        this.productImportPrice = productImportPrice;
    }

    public double getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(double productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    
}
