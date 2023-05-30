package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

public class Data {
    public JSONObject selectDatabase(String[] path, String query){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:../../../../SQLiteDatabase/ecommerce.db");
            Statement statement = connection.createStatement();
            System.out.println("connection berhasil");
            if(path[1].equals("users")){
                JSONObject jsonObject = new JSONObject();
                JSONArray array = new JSONArray();
                if(path.length == 2){
                    ResultSet rs = statement.executeQuery("select * from " + path[1]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("id_user", rs.getInt("id_user"));
                        record.put("First_Name", rs.getString("first_name"));
                        record.put("Last_Name", rs.getString("last_name"));
                        record.put("Email", rs.getString("email"));
                        record.put("Phone Number", rs.getString("phone_number"));
                        record.put("Type", rs.getString("type"));
                        array.add(record);
                    }
                    jsonObject.put("User Information", array);
                    return jsonObject;
                }
                if (path.length == 3) {
                    ResultSet rs = statement.executeQuery("SELECT u.*, a.city, a.province, a.postcode " +"FROM users u " +"JOIN addresses a ON u.id_user = a.id_user " +"WHERE u.id_user = " + path[2]);
                    while (rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("Id", rs.getInt("id_user"));
                        record.put("First_Name", rs.getString("first_name"));
                        record.put("Last_Name", rs.getString("last_name"));
                        record.put("Email", rs.getString("email"));
                        record.put("Phone Number", rs.getString("phone_number"));
                        record.put("Type", rs.getString("type"));
                        record.put("City", rs.getString("city"));
                        record.put("Province", rs.getString("province"));
                        record.put("Postcode", rs.getString("postcode"));
                        array.add(record);
                    }
                    jsonObject.put("User Information", array);
                    return jsonObject;
                }
                if(path.length == 4) {
                    if (path[3].equals("reviews")) {
                        ResultSet rs = statement.executeQuery("SELECT a.id_order, a.star, a.description,b.id_user,b.first_name, b.last_name FROM reviews AS a INNER JOIN orders AS c ON c.id_order = a.id_order INNER JOIN users AS b ON b.id_user = c.id_user where b.id_user = " + path[2]);
                        while (rs.next()) {
                            JSONObject record = new JSONObject();
                            record.put("order", rs.getInt("id_order"));
                            record.put("star", rs.getInt("star"));
                            record.put("User", rs.getInt("id_user"));
                            record.put("Deskripsi", rs.getString("description"));
                            record.put("First Name", rs.getString("first_name"));
                            record.put("Last Name", rs.getString("last_name"));
                            array.add(record);
                        }
                        jsonObject.put("Reviews Information", array);
                        return jsonObject;
                    }
                    if (path[3].equals("orders")) {
                        ResultSet rs = statement.executeQuery("select a.id_order,a.total,a.discount,b.id_user,b.first_name from orders as a inner join users as b on a.id_user=b.id_user where b.id_user =" + path[2]);
                        while (rs.next()) {
                            JSONObject record = new JSONObject();
                            record.put("Order", rs.getInt("id_order"));
                            record.put("Total", rs.getInt("total"));
                            record.put("Diskon", rs.getInt("discount"));
                            record.put("id user", rs.getInt("id_user"));
                            record.put("Nama", rs.getString("first_name"));
                            array.add(record);
                        }
                        jsonObject.put("Order Information", array);
                        return jsonObject;
                    }  if (path[3].equals("products")) {
                        ResultSet rs = statement.executeQuery("select a.id_product,a.title,a.description,a.price,a.stock, b.id_user, b.first_name from products as a inner join users as b on a.id_user=b.id_user where b.id_user =" + path[2]);
                        while (rs.next()) {
                            JSONObject record = new JSONObject();
                            record.put("ID Barang", rs.getInt("id_product"));
                            record.put("Nama Produk", rs.getString("title"));
                            record.put("Deskripsi", rs.getString("description"));
                            record.put("Harga", rs.getInt("price"));
                            record.put("Stock", rs.getInt("stock"));
                            record.put("User ID", rs.getInt("id_user"));
                            record.put("Nama", rs.getString("first_name"));
                            array.add(record);
                        }
                        jsonObject.put("Products Information", array);
                        return jsonObject;
                    }
                }
            }
            else if(path[1].equals("products")){
                JSONObject jsonObject = new JSONObject();
                JSONArray array = new JSONArray();
                System.out.println(query);
                if(path.length == 2){
                    ResultSet rs = statement.executeQuery("select * from " + path[1]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("Stock", rs.getInt("stock"));
                        record.put("Price", rs.getString("price"));
                        record.put("Description", rs.getString("description"));
                        record.put("Title", rs.getString("title"));
                        record.put("Id", rs.getInt("id_product"));
                        record.put("User",rs.getInt("id_user"));
                        array.add(record);
                    }
                    jsonObject.put("Product Information", array);
                    return jsonObject;
                }
                if(path.length == 3){
                    ResultSet rs = statement.executeQuery("select * from products inner join users on products.id_user = users.id_user where users.id_user =" + path[2]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("Stock", rs.getInt("stock"));
                        record.put("Price", rs.getString("price"));
                        record.put("Description", rs.getString("description"));
                        record.put("Title", rs.getString("title"));
                        record.put("Seller", rs.getInt("id_user"));
                        record.put("Id", rs.getInt("id_product"));
                        record.put("id_user", rs.getInt("id_user"));
                        record.put("First_Name", rs.getString("first_name"));
                        record.put("Last_Name", rs.getString("last_name"));
                        record.put("Email", rs.getString("email"));
                        record.put("Phone Number", rs.getString("phone_number"));
                        record.put("Type", rs.getString("type"));
                        array.add(record);
                    }
                    jsonObject.put("Product Information", array);
                    return jsonObject;
                }
            }
            else if(path[1].equals("orders")){
                JSONObject jsonObject = new JSONObject();
                JSONArray array = new JSONArray();
                if(path.length == 2){
                    ResultSet rs = statement.executeQuery("select * from " + path[1]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("isPaid", rs.getInt("is_paid"));
                        record.put("Discount", rs.getInt("discount"));
                        record.put("Total", rs.getInt("total"));
                        record.put("Note", rs.getInt("note"));
                        record.put("Id", rs.getInt("id"));

                        array.add(record);
                    }
                    jsonObject.put("Orders Information", array);
                    return jsonObject;
                }
                if(path.length == 3){
                    ResultSet rs = statement.executeQuery("select * from" + path[1] + "inner join users on products.id_user = users.id_user where users.id_users=" + path[2]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("isPaid", rs.getInt("is_paid"));
                        record.put("Discount", rs.getInt("discount"));
                        record.put("Total", rs.getInt("total"));
                        record.put("Note", rs.getInt("note"));
                        record.put("id_user", rs.getInt("id_user"));
                        record.put("First_Name", rs.getString("first_name"));
                        record.put("Last_Name", rs.getString("last_name"));
                        record.put("Email", rs.getString("email"));
                        record.put("Phone Number", rs.getString("phone_number"));
                        record.put("Type", rs.getString("type"));
                        array.add(record);
                    }
                    jsonObject.put("Orders Information", array);
                    return jsonObject;
                }
            }

        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    // private JSONObject getData(JSONObject object, JSONArray array, ResultSet rs){

    // }
}