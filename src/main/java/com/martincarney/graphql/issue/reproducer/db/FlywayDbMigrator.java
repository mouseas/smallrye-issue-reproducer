package com.martincarney.graphql.issue.reproducer.db;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;

/**
 * Runs database migration tasks during application startup
 * @author mouseasw@gmail.com
 */
@Singleton // instanciated once per application
@Startup // instanciated during startup
@TransactionManagement(TransactionManagementType.BEAN)
public class FlywayDbMigrator {
    private static final Logger log = Logger.getLogger(FlywayDbMigrator.class.getName());
    
    @Resource(lookup = "java:/comp/env/jdbc/smallryeDs")
    private DataSource ds;
    
    /**
     * <p>Run database migration tasks defined in <code>src/main/resources/db/migration</code>.</p>
     * 
     * <p>Per Flyway default, each task should be a sql file named according to the format:</p>
     * 
     * <ol>
     * <li>Start with an upper-case "V"</li>
     * <li>A number 1 greater than the previous migration task.</li>
     * <li>Two underscore characters, "__"</li>
     * <li>A description of the migration task in lower case, with underscores instead of spaces. E.g. "add_auditing_framework"</li>
     * <li>File extension, typically ".sql"</li>
     * </ol>
     * 
     * <p>The examples above would result in filename: "V0003__add_auditing_framework.sql"</p>
     */
    @PostConstruct // makes this method run during startup
    protected void onStartup() {
        log.info("------ Flyway DB Migration start ------");
        
        Flyway flyway = Flyway.configure().dataSource(ds).load();
        
        for (MigrationInfo i : flyway.info().all()) {
            log.log(Level.INFO, "migrate task: {0} : {1} from file: {2} (state: {3})", 
                    new Object[]{i.getVersion(), i.getDescription(), i.getScript(), i.getState().toString()});
        }
        
        flyway.migrate();
        
        log.info("------ Flyway DB Migration end ------");
    }
}
