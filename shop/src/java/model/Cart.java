/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author QuangDuy
 */
public class Cart {
    private HashMap<Integer, Item> cartItems;

    public Cart() {
        cartItems = new HashMap<>();
    }

    public Cart(HashMap<Integer, Item> cartItems) {
        this.cartItems = cartItems;
    }

    public HashMap<Integer, Item> getCartItems() {
        return cartItems;
    }

    public void setCartItems(HashMap<Integer, Item> cartItems) {
        this.cartItems = cartItems;
    }
    
    //plus item(product) to cart;
    public void plusToCart(Integer key, Item item) {
        boolean check = cartItems.containsKey(key);
        if (check) {
            int quantity_old = item.getQuantity();
            item.setQuantity(quantity_old + 1);
            cartItems.put(key, item);
        } else {
            cartItems.put(key, item);
        }
    }
    
    //sub item(product) to cart;
    public void subToCart(Integer key, Item item) {
        boolean check = cartItems.containsKey(key);
        if (check) {
            int quantity_old = item.getQuantity();
            if (quantity_old <= 1) {
                cartItems.remove(key);
            }
            else {
                item.setQuantity(quantity_old - 1);
                cartItems.put(key, item);
            }
        } else {
            cartItems.put(key, item);
        }
    }
    
    //remove item(product) to cart;
    public void removeToCart(Integer key) {
        boolean check = cartItems.containsKey(key);
        if (check) {
            cartItems.remove(key);
        }
    }
    
    //count item(product);
    public int countItem() {
        return cartItems.size();
    }
    
    //sum total
    public double totalCart() {
        double count = 0;
        for(Map.Entry<Integer, Item> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getProductSalePrice()* 
                    list.getValue().getQuantity();
        }
        return count;
    }
}
