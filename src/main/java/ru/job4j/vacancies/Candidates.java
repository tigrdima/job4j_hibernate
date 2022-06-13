package ru.job4j.vacancies;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidates")
public class Candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private DbVacancy dbVacancy;

    public Candidates() {
    }

    public Candidates(String name, DbVacancy dbVacancy) {
        this.name = name;
        this.dbVacancy = dbVacancy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DbVacancy getDbVacancy() {
        return dbVacancy;
    }

    public void setDbVacancy(DbVacancy dbVacancy) {
        this.dbVacancy = dbVacancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidates candidate = (Candidates) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Candidates{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", dbVacancy=" + dbVacancy
                + '}';
    }
}



