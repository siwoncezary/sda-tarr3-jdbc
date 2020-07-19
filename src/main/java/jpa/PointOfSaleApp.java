package jpa;

import entity.Seller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PointOfSaleApp {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("pos");
    static Scanner scanner = new Scanner(System.in);

    static int menu() {
        System.out.println("1. Wyświetl sprzedawców.");
        System.out.println("2. Dodaj sprzedawcę.");
        System.out.println("3. Usuń sprzedawcę.");
        System.out.println("4. Zmiana danych sprzedawcy.");
        System.out.println("0. Wyjdź");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        while (true) {
            switch (menu()) {
                case 1: {
                    allSellers();
                }
                break;
                case 2: {
                    createSeller();
                }
                break;
                case 3: {
                    deleteSeller();
                }
                break;
                case 4: {
                    updateSeller();
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
        List<Seller> sellers = em.createQuery("select s from Seller s", Seller.class).getResultList();
        sellers.forEach(seller -> System.out.println(seller));
        em.getTransaction().commit();
        em.close();
    }

    private static void createSeller() {
        System.out.println("Podaj imie:");
        String firstName = scanner.next();
        System.out.println("Podaj nazwisko:");
        String lastName = scanner.next();
        System.out.println("Podaj datę urodzin w formacie YYYY-MM-DD:");
        String dateStr = scanner.next();
        System.out.println("Podaj pensję:");
        int salary = scanner.nextInt();
        Seller seller = new Seller(firstName, lastName, LocalDate.parse(dateStr), salary);

        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(seller);
        em.getTransaction().commit();
        em.close();
        scanner.nextLine(); //pusty odczyt znaku końca linii (Enter)
    }

    private static void deleteSeller(){
        System.out.println("Podaj id sprzedawcy:");
        long id = scanner.nextLong();
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Seller seller = em.find(Seller.class, id);
        em.remove(seller);
        em.getTransaction().commit();
        em.close();
    }
    private static void updateSeller(){
        System.out.println("Podaj id sprzedawcy");
        long id = scanner.nextLong();
        scanner.nextLine();
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        //odczytana encja z bazy jest zarządzana przez managera
        Seller seller = em.find(Seller.class, id);

        System.out.println("Podaj nowe imię:");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) {
            seller.setFirstName(firstName);
        }

        System.out.println("Podaj nazwisko:");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()){
            seller.setLastName(lastName);
        }

        System.out.println("Podaj datę urodzin:");
        String dateStr = scanner.nextLine();
        if (!dateStr.isEmpty()){
            seller.setBirthDate(LocalDate.parse(dateStr));
        }
        System.out.println("Podaj pensję");
        String intStr = scanner.nextLine();
        if (!intStr.isEmpty()){
            seller.setSalary(Integer.parseInt(intStr));
        }
        em.getTransaction().commit();
        em.close();

    }
}
