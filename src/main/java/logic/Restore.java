package logic;

import com.smattme.MysqlExportService;
import com.smattme.MysqlImportService;
import connectioninfo.ConnectionCredentials;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Restore {

    public static void backupDB() throws ClassNotFoundException, SQLException, IOException {
        System.out.println("Query is about to happen... backup db");

        //required properties for exporting of db
        Properties properties = new Properties();
        properties.setProperty(MysqlExportService.DB_NAME, "security");
        properties.setProperty(MysqlExportService.DB_USERNAME, ConnectionCredentials.getId());
        properties.setProperty(MysqlExportService.DB_PASSWORD, ConnectionCredentials.getPw());
        properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");

        properties.setProperty(MysqlExportService.JDBC_CONNECTION_STRING, ConnectionCredentials.getURL());

        //set the outputs temp dir
        properties.setProperty(MysqlExportService.TEMP_DIR, new File("backup").getPath());
        MysqlExportService mysqlExportService = new MysqlExportService(properties);

        //Execute Backup
        mysqlExportService.export();

        //Get file. Doesnt save on open. But works fine with psvm.
        File file = mysqlExportService.getGeneratedZipFile();

        String sql = mysqlExportService.getGeneratedSql();

        /*try (PrintWriter out = new PrintWriter("backup/backup.sql")) {
            out.println(sql);
        }*/

    }

    /*public static boolean restoreDB(String sql) throws SQLException, ClassNotFoundException {
        boolean response = MysqlImportService.builder()
                .setDatabase("security")
                .setSqlString(sql)
                .setUsername("root")
                .setPassword("test1234")
                .setDeleteExisting(true)
                .setDropExisting(true)
                .importDatabase();
        return response;
    }*/

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        backupDB();
    }
}
