# graphql-issue-reproducer

Small project which reproduces [issue 1171](https://github.com/smallrye/smallrye-graphql/issues/1171) of [smallrye/smallrye-graphql](https://github.com/smallrye/smallrye-graphql)

## Setup

You'll need a Wildfly server. I'm using v25.0.0.Final. Available at https://www.wildfly.org/downloads/

You'll also need an empty PostgreSQL database.

Wildfly needs to have modules installed and added:

- org.wildfly.extension.microprofile.graphql-smallrye
- org.eclipse.microprofile.graphql
- org.eclipse.microprofile.context-propogation.api
- org.wildfly.context-propagation
- org.wildfly.extension.microprofile.context-propagation
- io.smallrye.graphql
- io.smallrye.graphql.api
- io.smallrye.graphql.client
- io.smallrye.graphql.client.api
- io.smallrye.context-propagation
- io.smallrye.reactive.mutiny.context-propagation
- org.postgresql

I may have missed some modules, apologies.

Wildfly's standalone configuration also needs a datasource defined with `jndi-name="java:/comp/env/jdbc/smallryeDs" pool-name="smallryeDS"` pointing to the empty PostgreSQL database + user/password you created for this.

Once that's set up and the server started, build the ROOT.war file and put it in Wildfly's standalone/deployments to deploy. Then go to http://localhost:8080/graphql-ui and try some different queries:

Exception triggered:
```
query {
  foo (description:"The Mandalorian") {
    description, id,
    bars {
      description, id
    }
  }
}
```

No exception:
```
query {
  foo (description:"The Mandalorian") {
    description, id
  }
}
```
