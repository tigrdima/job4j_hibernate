package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class CarRun {
    public static void main(String[] args) {
        List<CarMark> carMarkList = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            Session session = sf.openSession();
            session.beginTransaction();

            carMarkList = session.createQuery("select distinct c from CarMark c join fetch c.carModels").list();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        for (CarModel carModel : carMarkList.get(0).getCarModels()) {
            System.out.println(carModel);
        }
    }
}
