package com.martincarney.graphql.issue.reproducer.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author mouseasw@gmail.com
 */
@Entity
@Table(schema = "public", name = "foo")
public class Foo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foo_id")
    private int id;
    
    @Column(name = "description")
    private String description;
    
    @ManyToMany
    @JoinTable(
            name = "foo_bar",
            joinColumns = {
                @JoinColumn(name = "foo_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "bar_id", referencedColumnName = "id")})
    private Set<Bar> bars;

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

    public Set<Bar> getBars() {
        return bars;
    }

    public void setBars(Set<Bar> bars) {
        this.bars = bars;
    }
}
