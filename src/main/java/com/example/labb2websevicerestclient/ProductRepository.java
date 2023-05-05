package com.example.labb2websevicerestclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Repository
public class ProductRepository {

    WebClient webClient = WebClient.create("http://localhost:8080/rest/");
    Scanner scanner = new Scanner(System.in);
    public void menu() {
        int choice;
        while (true) {
            System.out.println("Välj 1 för att visa alla produkter");
            System.out.println("Välj 2 för att lägga till produkt");
            System.out.println("Välj 3 för att uppdatera pris");
            System.out.println("Välj 4 för att radera produkt");
            System.out.println("Välj 5 för att avsluta");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    List<Product> products = getAllProducts();
                    System.out.println(products);
                }
                case 2 -> readingProductFromUser();
                case 3 -> readingUpdatedPriceFromUser();
                case 4 -> readingProductToRemoveFromUser();
                case 5 -> {
                    System.out.println("Avslutar!");
                    System.exit(0);
                }
                default -> System.out.println("Det är inkorrekt val! Välj igen!");
            }
        }


    }

    private List<Product> getAllProducts() {
        Flux<Product> f = webClient
                .get()
                .uri("allproducts")
                .retrieve()
                .bodyToFlux(Product.class);
        return f.collectList().block();
    }

    private boolean addProduct(Product p) {
        try {
            Mono<String> m = webClient
                    .post()
                    .uri("addproduct")
                    .bodyValue(p)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class);
            m.block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void readingProductFromUser() {
        Category category = null;
       while (true) {
           try {
               System.out.println("Skriv in kategori");
               category = Category.valueOf(scanner.nextLine().toUpperCase());
               break;
           } catch (IllegalArgumentException e) {
               System.out.println("Fel val av kategori");
           }
       }
        System.out.println("Skriv in ett namn");
        String name = scanner.nextLine();
        System.out.println("Skriv in ett pris");
        double price = scanner.nextDouble();
        scanner.nextLine();
        if (addProduct(new Product(category, name, price))) {
            System.out.println("Det gick bra");
        } else {
            System.out.println("Det gick dåligt");
        }
    }

    private boolean updatePrice(long id, double price) {
        try {
            Mono<String> m = webClient
                    .put()
                    .uri("updateprice/{id}/{price}", id, price)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class);
            m.block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void readingUpdatedPriceFromUser() {
        System.out.println("Välj id på produkten du vill uppdatera priset på");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Skriv in det nya priset");
        double price = scanner.nextDouble();
        scanner.nextLine();
        if (updatePrice(id, price)){
            System.out.println("Det gick bra!");
        }else {
            System.out.println("Det finns ingen produkt med detta id!");
        }

    }

    private boolean removeProduct(long id){
        try {
            Mono<String> m = webClient
                    .delete()
                    .uri("deleteproduct/{id}",id)
                    .retrieve()
                    .bodyToMono(String.class);
            m.block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void readingProductToRemoveFromUser() {
        System.out.println("Skriv in id för product du vill radera");
        long id = scanner.nextLong();
        scanner.nextLine();
        if(removeProduct(id)){
            System.out.println("Produkten är borttagen");
        }else {
            System.out.println("Det gick inte att ta bort produkt!");
        }
    }



}
