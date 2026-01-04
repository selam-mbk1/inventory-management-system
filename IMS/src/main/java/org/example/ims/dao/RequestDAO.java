package org.example.ims.dao;

import org.example.ims.config.DBConnection;
import org.example.ims.model.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {

    // 1️⃣ Create a new request
    public void createRequest(Request r) {
        String sql = "INSERT INTO requests(requester_id,item_id,quantity,reason,status) VALUES(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getRequesterId());
            ps.setInt(2, r.getItemId());
            ps.setInt(3, r.getQuantity());
            ps.setString(4, r.getReason());
            ps.setString(5, "PENDING_DH");
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2️⃣ Get requests by status
    public List<Request> getRequestsByStatus(String status) {
        List<Request> list = new ArrayList<>();
        String sql = "SELECT * FROM requests WHERE status=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Request r = new Request();
                r.setRequestId(rs.getInt("request_id"));
                r.setRequesterId(rs.getInt("requester_id"));
                r.setItemId(rs.getInt("item_id"));
                r.setQuantity(rs.getInt("quantity"));
                r.setReason(rs.getString("reason"));
                r.setStatus(rs.getString("status"));
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3️⃣ Update request status
    public void updateStatus(int requestId, String status) {
        String sql = "UPDATE requests SET status=? WHERE request_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, requestId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4️⃣ Get issued requests (new method for Storekeeper)
    public List<Request> getIssuedRequests() {
        List<Request> list = new ArrayList<>();
        String sql = "SELECT r.*, u.full_name, i.item_name FROM requests r " +
                "JOIN users u ON r.requester_id = u.user_id " +
                "JOIN inventory_items i ON r.item_id = i.item_id " +
                "WHERE r.status='ISSUED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Request r = new Request();
                r.setRequestId(rs.getInt("request_id"));
                r.setRequesterId(rs.getInt("requester_id"));
                r.setItemId(rs.getInt("item_id"));
                r.setQuantity(rs.getInt("quantity"));
                r.setReason(rs.getString("reason"));
                r.setStatus(rs.getString("status"));
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
