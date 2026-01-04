package org.example.ims.dao;

import org.example.ims.config.DBConnection;
import org.example.ims.model.InventoryItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    public List<InventoryItem> getAllItems() {
        List<InventoryItem> list = new ArrayList<>();
        String sql = "SELECT * FROM inventory_items";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                InventoryItem item = new InventoryItem();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setCategory(rs.getString("category"));
                item.setQuantity(rs.getInt("quantity"));
                item.setLocation(rs.getString("location"));
                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
