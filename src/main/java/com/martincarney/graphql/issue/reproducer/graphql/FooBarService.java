package com.martincarney.graphql.issue.reproducer.graphql;

import com.martincarney.graphql.issue.reproducer.db.FooBarEJB;
import com.martincarney.graphql.issue.reproducer.model.*;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

/**
 *
 * @author mouseasw@gmail.com
 */
@GraphQLApi
@ApplicationScoped
public class FooBarService {
    
    @Inject
    private FooBarEJB fbejb;
    
    @Query("foo")
    public Foo getFoo(String description) {
        return fbejb.getFoo(description);
    }
    
    @Query("foos")
    public List<Foo> getAllFoo() {
        return fbejb.getAllFoos();
    }
}
