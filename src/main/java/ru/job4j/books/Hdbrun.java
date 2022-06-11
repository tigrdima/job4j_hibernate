package ru.job4j.books;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Hdbrun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book one = new Book("Book1");
            Book two = new Book("Book2");
            Book three = new Book("Book3");
            Book four = new Book("Book4");

            Author first = new Author("Author1");
            Author second = new Author("Author2");
            Author third = new Author("Author3");
            Author fourth = new Author("Author4");

            first.getBooks().add(one);
            first.getBooks().add(two);
            second.getBooks().add(two);
            third.getBooks().add(three);
            fourth.getBooks().add(three);
            fourth.getBooks().add(four);

            session.persist(first);
            session.persist(second);
            session.persist(third);
            session.persist(fourth);

            session.getTransaction().commit();
            session.close();

            Session session1 = sf.openSession();
            session1.beginTransaction();
            session1.remove(session1.get(Author.class, 3));
            session1.getTransaction().commit();
            session1.close();

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
