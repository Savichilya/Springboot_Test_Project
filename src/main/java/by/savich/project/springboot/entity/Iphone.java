package by.savich.project.springboot.entity;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Iphone {

    private int id;
    private String model;
    private boolean ref;

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

}
