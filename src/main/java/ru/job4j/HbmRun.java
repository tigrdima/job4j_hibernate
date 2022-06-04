package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.query.*;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Candidate candidate1 = new Candidate(0, "коля", "10 лет", 20000);
            Candidate candidate2 = new Candidate(1, "Вася", "3 года", 5000);
            Candidate candidate3 = new Candidate(2, "Вова", "1 год", 1000);

            session.save(candidate1);
            session.save(candidate2);
            session.save(candidate3);

            Query queryAll = session.createQuery("from Candidate ");
            for (Object st : queryAll.list()) {
                System.out.println(st);
            }

            Query queryId = session
                    .createQuery("from Candidate c where c.id = :Id")
                    .setParameter("Id", 1);
            System.out.println(queryId.uniqueResult());

            Query queryName = session
                    .createQuery("from Candidate c where c.name = :Name")
                    .setParameter("Name", "Вова");
           for (Object qn : queryName.list()) {
               System.out.println(qn);
           }

             session.createQuery("UPDATE Candidate c set c.salary = :cNewSalary where c.name = :Name")
                    .setParameter("cNewSalary", 130000)
                    .setParameter("Name", "Вася")
                    .executeUpdate();

            session.createQuery("delete from Candidate where id = :Id")
                            .setParameter("Id", 0)
                            .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
