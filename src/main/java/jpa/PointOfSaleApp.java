package jpa;

import entity.Seller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class PointOfSaleApp {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("pos");
    static Scanner scanner = new Scanner(System.in);
    static int menu(){
        System.out.println("1. Wyświetl sprzedawców.");
        System.out.println("2. Dodaj sprzedawcę.");
        System.out.println("3. Usuń sprzedawcę.");
        System.out.println("0. Wyjdź");
        return scanner.nextInt();
    }
    public static void main(String[] args) {
        while(true){
            switch(menu()){
                case 1: {
                        allSellers();
                }
                break;
                case 0: {
                    System.exit(0);
                }
            }
        }
    }

    private static void allSellers() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        List<Seller> sellers = em.createQuery("select s from Seller s",Seller.class).getResultList();
        sellers.forEach(seller -> System.out.println(seller));
        em.getTransaction().commit();
        em.close();
    }
}
