package TmRoom;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class TmRoom {
    private Connection connection;

    private String location;
    private String nameDataBase;

    public TmRoom(String location, String nameDataBase) {
        this.location = location;
        this.nameDataBase = nameDataBase;

        TmRoomManager.regisDb(this);
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

            try (Statement stmt = this.connection.createStatement()) {
                stmt.execute("PRAGMA journal_mode=WAL;");
                stmt.execute("PRAGMA synchronous=NORMAL;");
            }

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
    public synchronized boolean TmRoomCreateTable(String tableName, List<String> columns){
        if (columns == null || columns.isEmpty()) return true;

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
                return true;
            }catch(Exception ex){
                Bukkit.getLogger().log(Level.WARNING, "Error trying to create the database "+ex);
                return false;
            }
        }catch(Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error trying to create the database "+ex);
            return false;
        }
    }

    //insert
    public synchronized boolean TmRoomInsert(String nameTable, List<String> position, List<String> values){
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

            try (PreparedStatement stmt = this.connection.prepareStatement(sqlBuilder.toString())) {
                stmt.executeUpdate();
                return true;

            }catch(Exception ex){
                Bukkit.getLogger().log(Level.WARNING, "Error trying to Insert the database "+ex);
                return false;
            }
        }catch (Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error trying to Insert the database "+ex);
            return false;
        }
    }


    //update
    public synchronized boolean TmRoomUpdate(String nameTable, List<String> setInColumn, String whereId){
        try{
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("UPDATE ").append(nameTable).append(" SET ");

            for (int i = 0; i < setInColumn.size(); i++) {
                if (i > 0) {
                    sqlBuilder.append(", ");
                }
                sqlBuilder.append(setInColumn.get(i));
            }

            sqlBuilder.append(" WHERE ").append(whereId);

            try(PreparedStatement stmt = this.connection.prepareStatement(sqlBuilder.toString())) {
                stmt.executeUpdate();
                return true;
            }catch(Exception ex){
                Bukkit.getLogger().log(Level.WARNING, "Error trying to update the database "+ex);
                return false;
            }
        }catch(Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error trying to update the database "+ex);
            return false;
        }

    }

    //get
    public synchronized Map<String, Object> TmRoomGet(List<String> columnName, String TableName, String whereId){
        try{
            if (whereId == null || whereId.isEmpty()) {
                Bukkit.getLogger().log(Level.WARNING, "Error: whereId cannot be empty!");
                return null;
            }

            Map<String, Object> results = new HashMap<>();

            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT ");
            for (int i = 0; i < columnName.size(); i++) {
                if (i > 0) {
                    sqlBuilder.append(", ");
                }
                sqlBuilder.append(columnName.get(i));
            }

            sqlBuilder.append(" FROM ").append(TableName).append(" WHERE ").append(whereId);



            try(PreparedStatement stmt = this.connection.prepareStatement(sqlBuilder.toString())) {


                try(ResultSet resultset = stmt.executeQuery()){

                    if(resultset.next()){
                        for (String col : columnName) {
                            //Save each column on the map
                            results.put(col, resultset.getObject(col));
                        }
                        return results;
                    }else{
                        Bukkit.getLogger().log(Level.WARNING, "Error, row not found ");
                        return null;
                    }

                }catch(Exception ex){
                    Bukkit.getLogger().log(Level.WARNING, "Error trying to get the database "+ex);
                    return null;
                }
            }catch(Exception ex){
                Bukkit.getLogger().log(Level.WARNING, "Error trying to get the database "+ex);
                return null;
            }

        }catch(Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error trying to get the database "+ex);
            return null;
        }
    }
    //delete
    public synchronized boolean TmRoomDelete(String tableName, String whereId){
        try {
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("DELETE FROM ").append(tableName).append(" WHERE ").append(whereId);

            try(PreparedStatement stmt = this.connection.prepareStatement(sqlBuilder.toString())) {
               if(stmt.executeUpdate() > 0){
                   return true;
               }else{
                   return false;
               }

            }catch(Exception ex){
                Bukkit.getLogger().log(Level.WARNING, "Error trying to delete the database "+ex);
                return false;
            }

        }catch(Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error trying to delete the database "+ex);
            return false;
        }
    }

    public void TmRoomCls() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, "Error "+e);
        }
    }

}

