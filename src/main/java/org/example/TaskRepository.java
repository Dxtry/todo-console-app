package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    /*Created a task list to store received tasks.*/
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();


        /*
        * Connection conn - connecting to a database
        * Statement stmt - SQL command
        * ResultSet rs - SQL query result*/
        try (Connection conn = DataBase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tasks")){

            while(rs.next()){
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getBoolean("is_done")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
        //In case of an error, we print it and return the list of tasks at the end

    //Creating a SQL query to add
    public void addTask(String title){
        String sql = "INSERT INTO tasks (title) VALUES (?)";

        try (Connection conn = DataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){ //Safe way to insert data into a table
            stmt.setString(1, title);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Creating a SQL query to delete
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = DataBase.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Updates the is_done field for a specific task
    public void is_doneTask(int id){
        String sql = "UPDATE tasks SET is_done = TRUE WHERE id = ?";

        try (Connection conn = DataBase.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
