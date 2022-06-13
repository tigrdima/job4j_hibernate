package ru.job4j.vacancies;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HdbVacancyRun {
    public static void main(String[] args) {
        Candidates rsl = null;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            rsl = session.createQuery("select distinct c from Candidates c "
                    + "join fetch c.dbVacancy d join fetch d.vacancies v where c.id = :cId", Candidates.class)
                    .setParameter("cId", 1).uniqueResult();

            session.getTransaction().commit();
            session.close();

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

      System.out.println(rsl);
    }
}
