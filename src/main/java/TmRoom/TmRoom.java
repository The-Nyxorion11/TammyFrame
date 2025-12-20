package TmRoom;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;

public class TmRoom {
    private Connection connection;

    private String location;
    private String nameDataBase;
    private Plugin plugin;

    public TmRoom(String location, String nameDataBase,  Plugin plugin) {
        this.location = location;
        this.nameDataBase = nameDataBase;
        this.plugin = plugin;
    }
    //create file
    public void TmRoomCreate(){
        try {

            // Forzar el Driver
            Class.forName("org.sqlite.JDBC");

            File folder = new File(this.location);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            //Create the folder where the database will be located.
            File dbFolder = new File(folder, this.nameDataBase + ".db");

            String url = "jdbc:sqlite:" + dbFolder.getAbsolutePath();
            this.connection = DriverManager.getConnection(url);

            //send Message
            Bukkit.getLogger().log(Level.INFO, "Database successfully linked to: " + dbFolder.getName());


        }catch (Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error saving the database "+ex);
        }
    }
    //returns the location
    public Connection TmRoomConnect(){
        return this.connection;
    }


    //create
    public void TmRoomCreateTable(String tableName, List<String> columns){
        if (columns == null || columns.isEmpty()) return;

        try{
            String primaryKey = columns.get(0);

            //build the list of columns
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
            sqlBuilder.append(primaryKey).append(" TEXT PRIMARY KEY"); // Forzamos TEXT para la Key

            //Add the remaining columns separated by commas
            for (int i = 1; i < columns.size(); i++) {
                sqlBuilder.append(", ").append(columns.get(i));
            }

            sqlBuilder.append(");");

            //Run only once
            try (Statement stmt = this.connection.createStatement()) {
                stmt.execute(sqlBuilder.toString());
            }catch(Exception ex){
                Bukkit.getLogger().log(Level.WARNING, "Error trying to create the database "+ex);
            }
        }catch(Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error trying to create the database "+ex);
        }
    }

    //insert
    public void TmRoomInsert(String nameTable, List<String> position, List<String> values){
        try{
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("INSERT INTO ").append(nameTable).append(" (");

            for (int i = 0; i < position.size(); i++) {
                if (i > 0) {
                    sqlBuilder.append(", ");
                }
                sqlBuilder.append(position.get(i));
            }

            sqlBuilder.append(") VALUES (");

            for (int i = 0; i < values.size(); i++) {
                if (i > 0) {
                    sqlBuilder.append(", ");
                }
                sqlBuilder.append(values.get(i));
            }

            sqlBuilder.append(" )");

            try (Statement stmt = this.connection.prepareStatement(sqlBuilder.toString())) {
                stmt.executeUpdate(sqlBuilder.toString());

            }catch(Exception ex){
                Bukkit.getLogger().log(Level.WARNING, "Error trying to Insert the database "+ex);
            }
        }catch (Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error trying to Insert the database "+ex);
        }
    }


    //update
    public void TmRoomUpdate(String nameTable, List<String> setInColumn, String whereId){
        try{
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("UPDATE ").append(nameTable).append(" SET ");

            for (int i = 0; i < setInColumn.size(); i++) {
                if (i > 0) {
                    sqlBuilder.append(", ");
                }
                sqlBuilder.append(setInColumn.get(i));
            }

            sqlBuilder.append("WHERE ").append(whereId);

            try(Statement stmt = this.connection.prepareStatement(sqlBuilder.toString())) {
                stmt.executeUpdate(sqlBuilder.toString());

            }catch(Exception ex){
                Bukkit.getLogger().log(Level.WARNING, "Error trying to update the database "+ex);
            }
        }catch(Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error trying to update the database "+ex);
        }

    }

}

