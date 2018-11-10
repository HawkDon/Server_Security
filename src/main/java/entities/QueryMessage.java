package entities;

public class QueryMessage {

    private String query;
    private String table;

    public QueryMessage(String query, String table) {
        this.query = query;
        this.table = table;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
