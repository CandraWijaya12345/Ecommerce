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
                        record.put("Id", rs.getInt("id_order"));

                        array.add(record);
                    }
                    jsonObject.put("Orders Information", array);
                    return jsonObject;
                }
                if(path.length == 3){
                    ResultSet rs = statement.executeQuery("SELECT DISTINCT a.id_order, c.id_product,b.id_user,b.first_name,a.note, c.quantity, c.price,d.star, d.description, e.title FROM orders AS a INNER JOIN users AS b ON a.id_user = b.id_user INNER JOIN order_details AS c ON a.id_order = c.id_order INNER JOIN reviews AS d ON a.id_order = d.id_order INNER JOIN products AS e ON c.id_product = e.id_product where a.id_order =" +  path[2]);
                    while(rs.next()) {
                        JSONObject record = new JSONObject();
                        record.put("idOrder", rs.getInt("id_order"));
                        record.put("idProduct", rs.getInt("id_product"));
                        record.put("idUser", rs.getInt("id_user"));
                        record.put("Name", rs.getString("first_name"));
                        record.put("Note", rs.getInt("note"));
                        record.put("Quantity", rs.getInt("quantity"));
                        record.put("Price", rs.getString("price"));
                        record.put("Star", rs.getInt("star"));
                        record.put("Description", rs.getString("description"));
                        record.put("Title", rs.getString("title"));
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
    public String postData(JSONObject requestBodyJson, String[] path){
        if(path[1].equals("users")){
            String first_name = (String) requestBodyJson.get("first_name");
            String last_name = (String) requestBodyJson.get("last_name");
            String email = (String) requestBodyJson.get("email");
            String phone_number = (String) requestBodyJson.get("phone_number");
            String type = (String) requestBodyJson.get("type");
            PreparedStatement statement = null;
            int rowsAffected = 0;
            String query = "INSERT INTO users(first_name, last_name, email, phone_number, type) VALUES(?,?,?,?,?)";
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:../../../../SQLiteDatabase/ecommerce.db");
                statement = connection.prepareStatement(query);
                statement.setString(1, first_name);
                statement.setString(2, last_name);
                statement.setString(3, email);
                statement.setString(4, phone_number);
                statement.setString(5, type);
                rowsAffected = statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
            System.out.println(rowsAffected + " rows inserted!");
            return rowsAffected + " rows inserted!";
        }
        else if(path[1].equals("orders")){
            int id_buyer = Integer.parseInt(requestBodyJson.get("id_order").toString());
            int id_user = Integer.parseInt(requestBodyJson.get("id_user").toString());
            int note = Integer.parseInt(requestBodyJson.get("note").toString());
            int total = Integer.parseInt(requestBodyJson.get("total").toString());
            int discount = Integer.parseInt(requestBodyJson.get("discount").toString());
            int isPaid = Integer.parseInt(requestBodyJson.get("is_paid").toString());
            PreparedStatement statement = null;
            int rowsAffected = 0;
            String query = "INSERT INTO orders(id_order,id_user,note, total, discount, isPaid) VALUES(?,?,?,?,?,?)";
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:../../../../SQLiteDatabase/ecommerce.db");
                statement = connection.prepareStatement(query);
                statement.setInt(1, id_buyer);
                statement.setInt(2,id_user);
                statement.setInt(3, note);
                statement.setInt(4, total);
                statement.setInt(5, discount);
                statement.setInt(6, isPaid);
                rowsAffected = statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
            System.out.println(rowsAffected + " rows inserted!");
            return rowsAffected + " rows inserted!";
        }
        else if(path[1].equals("products")){
            int idProduct = Integer.parseInt(requestBodyJson.get("id_product").toString());
            int idUser = Integer.parseInt(requestBodyJson.get("id_user").toString());
            String title = requestBodyJson.get("title").toString();
            int description = Integer.parseInt(requestBodyJson.get("description").toString());
            int price = Integer.parseInt(requestBodyJson.get("price").toString());
            int stock = Integer.parseInt(requestBodyJson.get("stock").toString());
            PreparedStatement statement = null;
            int rowsAffected = 0;
            String query = "INSERT INTO products(id_seller, title, description, price, stock) VALUES(?,?,?,?,?)";
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:../../../../SQLiteDatabase/ecommerce.db");
                statement = connection.prepareStatement(query);
                statement.setInt(1, idProduct);
                statement.setInt(2, idUser);
                statement.setString(3, title);
                statement.setInt(4, description);
                statement.setInt(5, price);
                statement.setInt(6, stock);
                rowsAffected = statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
            System.out.println(rowsAffected + " rows inserted!");
            return rowsAffected + " rows inserted!";
        }
        return null;
    }
    public String putData(JSONObject requestBodyJson, String[] path) {
        if (path[1].equals("users")) {
            String first_name = (String) requestBodyJson.get("first_name");
            String last_name = (String) requestBodyJson.get("last_name");
            String email = (String) requestBodyJson.get("email");
            String phone_number = (String) requestBodyJson.get("phone_number");
            String type = (String) requestBodyJson.get("type");
            PreparedStatement statement = null;
            int rowsAffected = 0;
            String query = "INSERT INTO users(first_name, last_name, email, phone_number, type) VALUES(?,?,?,?,?)";
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:../../../../SQLiteDatabase/ecommerce.db");
                statement = connection.prepareStatement(query);
                statement.setString(1, first_name);
                statement.setString(2, last_name);
                statement.setString(3, email);
                statement.setString(4, phone_number);
                statement.setString(5, type);
                rowsAffected = statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(rowsAffected + " rows updated!");
            return rowsAffected + " rows updated!";
        } else if (path[1].equals("orders")) {
            int id_buyer = Integer.parseInt(requestBodyJson.get("id_order").toString());
            int id_user = Integer.parseInt(requestBodyJson.get("id_user").toString());
            int note = Integer.parseInt(requestBodyJson.get("note").toString());
            int total = Integer.parseInt(requestBodyJson.get("total").toString());
            int discount = Integer.parseInt(requestBodyJson.get("discount").toString());
            int isPaid = Integer.parseInt(requestBodyJson.get("is_paid").toString());
            PreparedStatement statement = null;
            int rowsAffected = 0;
            String query = "INSERT INTO orders(id_order,id_user,note, total, discount, isPaid) VALUES(?,?,?,?,?,?)";
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:../../../../SQLiteDatabase/ecommerce.db");
                statement = connection.prepareStatement(query);
                statement.setInt(1, id_buyer);
                statement.setInt(2, id_user);
                statement.setInt(3, note);
                statement.setInt(4, total);
                statement.setInt(5, discount);
                statement.setInt(6, isPaid);
                rowsAffected = statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(rowsAffected + " rows inserted!");
            return rowsAffected + " rows inserted!";
        } else if (path[1].equals("products")) {
            int id_seller = Integer.parseInt(requestBodyJson.get("Id_Seller").toString());
            String title = requestBodyJson.get("Title").toString();
            String description = requestBodyJson.get("Description").toString();
            int price = Integer.parseInt(requestBodyJson.get("Price").toString());
            int stock = Integer.parseInt(requestBodyJson.get("Stock").toString());
            PreparedStatement statement = null;
            int rowsAffected = 0;
            String query = "INSERT INTO products(id_seller, title, description, price, stock) VALUES(?,?,?,?,?)";
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:../../../../SQLiteDatabase/ecommerce.db");
                statement = connection.prepareStatement(query);
                statement.setInt(1, id_seller);
                statement.setString(2, title);
                statement.setString(3, description);
                statement.setInt(4, price);
                statement.setInt(5, stock);
                rowsAffected = statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(rowsAffected + " rows inserted!");
            return rowsAffected + " rows inserted!";
        }return null;
    }
        public String deleteData(String[] path){
            PreparedStatement statement = null;
            int rowsAffected = 0;
            try {
                String query = "DELETE FROM " + path[1] + " WHERE id_user=" + path[2];
                Connection connection = DriverManager.getConnection("jdbc:sqlite:../../../../SQLiteDatabase/ecommerce.db");
                statement = connection.prepareStatement(query);
                rowsAffected = statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
            return rowsAffected + " rows deleted!";
        }

}

