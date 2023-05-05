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

        @Override
        public String toString() {
            return "Id: " + id +
                    ", produkt: " + name +
                    ", pris: " + price;
        }

    }


