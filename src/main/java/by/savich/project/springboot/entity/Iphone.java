package by.savich.project.springboot.entity;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Iphone {

    private int id;
    private String model;
    private boolean ref;
    private int releaseDate;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isRef() {
        return ref;
    }

    public void setRef(boolean ref) {
        this.ref = ref;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Iphone)) return false;
        Iphone iphone = (Iphone) o;
        return id == iphone.id && ref == iphone.ref && releaseDate == iphone.releaseDate && Objects.equals(model, iphone.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, ref, releaseDate);
    }
}
