package com.springboot.productmanagement.utils;

import com.springboot.productmanagement.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductData {
    public static List<Product> populate() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1,"Pencil",20,2000));
        products.add(new Product(2,"Pen",10,3000));
        products.add(new Product(3,"Eraser",15,1000));
        products.add(new Product(4,"Crayon",30,4000));
        products.add(new Product(5,"Ruler",25,5000));
        return products;
    }
}
