package com.martincarney.graphql.issue.reproducer.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author mouseasw@gmail.com
 */
@Entity
@Table(schema = "public", name = "bar")
public class Bar implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bar_id")
    private int id;
    
    @Column(name = "description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
