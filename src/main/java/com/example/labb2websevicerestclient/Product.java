package com.example.labb2websevicerestclient;

public class Product {

        private Long id;
        private String name;

        private double price;
        private Category category;

        public Product() {
        }

        public Product(Category category, String name, double price) {
            this.category = category;
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
        public String toString() {
            return "Id: " + id +
                    ", produkt: " + name +
                    ", pris: " + price;
        }

    }


