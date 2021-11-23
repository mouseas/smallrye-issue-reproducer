/*
 * Copyright (C) 2021 Utah Department of Technology Services and Utah Department of Health
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses
 *
 * In addition, this program is also subject to certain additional terms. You should 
 * have received a copy of these additional terms immediately following the terms and 
 * conditions of the GNU Affero General Public License which accompanied the program. 
 * If not, please request a copy in writing from the Utah Department of Health at 
 * the address below. 
 *
 * If you have questions concerning this license or the applicable additional terms, 
 * you may contact us in writing at:
 * Utah Department of Health, P.O. Box 141010, Salt Lake City, UT 84114-1010 USA.
 */
package com.martincarney.graphql.issue.reproducer.db;

import com.martincarney.graphql.issue.reproducer.model.*;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author mouseasw@gmail.com
 */
public class FooBarEJB {
    
    @Inject
    EntityManager em;
    
    public Foo getFoo(String description) {
        Foo result = null;
        Query query = em.createQuery("SELECT f FROM Foo f WHERE f.description = :desc", Foo.class);
        query.setParameter("desc", description);
        List<Foo> results = query.getResultList();
        if (!results.isEmpty()) {
            result = results.get(0);
        }
        return result;
    }
    
    public List<Foo> getAllFoos() {
        Query query = em.createQuery("SELECT f FROM Foo f", Foo.class);
        return query.getResultList();
    }
    
}
