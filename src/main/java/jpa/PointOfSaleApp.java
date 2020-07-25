package jpa;

import entity.Invoice;
import entity.Seller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
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
        System.out.println("5. Pobierz i odłącz sprzedawce.");
        System.out.println("6. Wykonaj zapytanie JPQL.");
        System.out.println("7. Dodaj fakturę.");
        System.out.println("8. Wyświetl faktury dla sprzedawcy.");
        System.out.println("9. Zestawienie miesięczne faktur.");
        System.out.println("0. Wyjdź");
        return scanner.nextInt();
    }

    private static void invoicesForSeller(){
        System.out.println("Podaj id sprzedawcy:");
        long id = scanner.nextLong();
        scanner.nextLine();
        EntityManager em = factory.createEntityManager();
        em.createNamedQuery("invoices", Invoice.class).setParameter("seller_id", id)
                .getResultStream().forEach(System.out::println);
    }

    private static void monthSummary(){
        System.out.println("Podaj id sprzedawcy:");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Podaj rok:");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj miesiąc:");
        int month = scanner.nextInt();
        scanner.nextLine();
        EntityManager em = factory.createEntityManager();
        BigDecimal sum = em.createNamedQuery("monthSummary", BigDecimal.class)
                .setParameter("seller_id", id)
                .setParameter("year", year)
                .setParameter("month", month)
                .getSingleResult();
        System.out.println("Suma za podanym miesiąc: " + sum);
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
                case 5: {
                    detachSeller();
                }
                case 6: {
                    runQuery();
                }
                break;
                case 7: {
                    createInvoice();
                }
                break;
                case 8:{
                    invoicesForSeller();
                }
                break;
                case 9:{
                    monthSummary();
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
        sellers.forEach(seller -> {
            System.out.println(seller);
//          Pobranie faktur dla danego sprzedawcy dla związku jednokierunkowego - tylko po stronie Invoice
//            List<Invoice> invoices = em.createQuery("select i from Invoice i where i.seller.id = :id", Invoice.class)
//                    .setParameter("id", seller.getId())
//                    .getResultList();
            //wykorzystujemy dwukierunowy związek jedne-do-wielu
            //może wpływać na znacząco na wydajność, gdy każdy z pracowników ma b. dużo faktur
            for(Invoice item: seller.getInvoices()){
                System.out.println(item);
            }
        });
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

    private static void createInvoice(){
        System.out.println("Podaj id sprzedawcy:");
        long id = scanner.nextLong();
        scanner.nextLine();
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Seller seller = em.find(Seller.class, id);
        System.out.println("Podaj dane klienta:");
        String customer = scanner.nextLine();
        System.out.println("Podaj kwotę faktury:");
        BigDecimal price = scanner.nextBigDecimal();
        Invoice invoice = new Invoice(seller, price, customer);
        em.persist(invoice);
        em.getTransaction().commit();
        em.close();
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
        //zakończenie transakcji powoduje utrwalenie encji, którą zmienialiśmy wewnątrz transakcji
        em.getTransaction().commit();
        em.close();
    }

    private static void detachSeller(){
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        Seller seller = em.find(Seller.class, 1L);
        //odłączamu encję od managera
        em.detach(seller);
        System.out.println(seller);
        System.out.println("Zmieniam pensję na 5000!");
        seller.setSalary(5000);
        System.out.println(seller);
        //pomimo zmian encji, nie zostaną one uwzględnione po została odłączona od managera
        em.getTransaction().commit();

        seller = em.find(Seller.class, 1L);
        System.out.println("Sprzedawca nr 1 pobrany z bazy");
        System.out.println(seller);
    }

    private static void runQuery(){
        System.out.println("Podaj zapytanie:");
        scanner.nextLine();
        String query = scanner.nextLine();
        EntityManager em = factory.createEntityManager();
        em.createQuery(query, Seller.class).getResultStream()
                .forEach(System.out::println);
    }
}
