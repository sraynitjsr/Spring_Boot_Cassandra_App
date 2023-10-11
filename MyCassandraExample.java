import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class MyCassandraExample {

    public static void main(String[] args) {
        Cluster cluster;
        Session session;
      
        cluster = Cluster.builder()
                .addContactPoints("127.0.0.1")
                .build();
      
        session = cluster.connect();

        String keyspaceName = "my_keyspace";
        String createKeyspaceQuery = "CREATE KEYSPACE IF NOT EXISTS " + keyspaceName
                + " WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}";
        session.execute(createKeyspaceQuery);

        session.execute("USE " + keyspaceName);

        String createTableQuery = "CREATE TABLE IF NOT EXISTS my_table (" +
                "id UUID PRIMARY KEY," +
                "name TEXT," +
                "age INT" +
                ")";
        session.execute(createTableQuery);

        String insertDataQuery = "INSERT INTO my_table (id, name, age) VALUES (?, ?, ?)";
        session.execute(insertDataQuery,
                java.util.UUID.randomUUID(),
                "Subhradeep Ray",
                30);

        session.close();
        cluster.close();
    }
}
