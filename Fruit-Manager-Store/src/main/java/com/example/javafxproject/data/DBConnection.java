package com.example.javafxproject.data;

import com.example.javafxproject.data.models.Fruit;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private Connection connection;

    public static final String URL = "jdbc:mysql://localhost/manager-fruit";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public DBConnection(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Fruit> getFruit(){
        ArrayList<Fruit> list = new ArrayList<>();
        String sql = "SELECT * FROM fruit";
        try {
            ResultSet results = connection.prepareStatement(sql).executeQuery();
            while (results.next()){
                Fruit fruit = new Fruit(
                        results.getInt("id"),
                        results.getString("name"),
                        results.getInt("quality"),
                        results.getFloat("price"),
                        results.getString("typefruit"),
                        results.getString("image")
                                        );
                list.add(fruit);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public void Add(Fruit pro){
        String sql = "INSERT INTO fruit (name, image, price, typefruit, quality) VALUE ('"+ pro.name+"','"+ pro.image+"','"+ pro.price+"','"+ pro.typefruit+"',"+ pro.quality+")";
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Update(Fruit pro){
        String sql = "UPDATE fruit SET name = '"+ pro.name +"', image = '"+ pro.image+"', price = '"+ pro.price+"', typefruit = '"+ pro.typefruit+"', quality = '"+ pro.quality+"' WHERE id = "+ pro.id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Delete(int id){
        String sql = "DELETE FROM fruit WHERE id = "+ id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
