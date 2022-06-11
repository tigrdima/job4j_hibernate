package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class CarRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            CarModel one = new CarModel("M1");
            CarModel two = new CarModel("M2");
            CarModel three = new CarModel("M3");
            CarModel four = new CarModel("M4");
            CarModel five = new CarModel("M5");
            session.save(one);
            session.save(two);
            session.save(three);
            session.save(four);
            session.save(five);

            CarMark carMark = new CarMark("AUDI");

            carMark.addCarModel(session.load(CarModel.class, 1));
            carMark.addCarModel(session.load(CarModel.class, 2));
            carMark.addCarModel(session.load(CarModel.class, 3));
            carMark.addCarModel(session.load(CarModel.class, 4));
            carMark.addCarModel(session.load(CarModel.class, 5));

            session.save(carMark);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
