package com.martincarney.graphql.issue.reproducer.db;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Defines an implementation for EntityManager
 * @author mouseasw@gmail.com
 */
public class CDIInjectionImpl {

    @PersistenceContext
    private EntityManager em;
    
    @Produces
    @Default
    public EntityManager getEntityManager() {
        return em;
    }
}