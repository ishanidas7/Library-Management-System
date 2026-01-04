package service;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import util.InputValidator;


public class StaffService {

    public void viewStaff() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM staff";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Staff Details ---");
            System.out.println("ID\tName\t\tRole\t\tContact");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("staff_id") + "\t" +
                    rs.getString("name") + "\t\t" +
                    rs.getString("role") + "\t\t" +
                    rs.getString("contact")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addStaff() {
    try {
        Scanner sc = new Scanner(System.in);
        Connection con = DBConnection.getConnection();

        System.out.print("Enter Staff ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Staff Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Role: ");
        String role = sc.nextLine();

        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();

        if (!InputValidator.isValidId(id)) {
    System.out.println("❌ Invalid Staff ID.");
    return;
}

if (!InputValidator.isValidText(name) || !InputValidator.isValidText(role)) {
    System.out.println("❌ Name/Role cannot be empty.");
    return;
}

if (!InputValidator.isValidContact(contact)) {
    System.out.println("❌ Contact must be 10 digits.");
    return;
}


        String sql = "INSERT INTO staff VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, role);
        ps.setString(4, contact);

        ps.executeUpdate();
        System.out.println("✅ Staff added successfully!");

    } catch (Exception e) {
        System.out.println("❌ Error adding staff.");
        e.printStackTrace();
    }
} 
    public void removeStaff() {
    try {
        Scanner sc = new Scanner(System.in);
        Connection con = DBConnection.getConnection();

        System.out.print("Enter Staff ID to remove: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM staff WHERE staff_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Staff removed successfully!");
        } else {
            System.out.println("❌ Staff ID not found.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void updateStaff() {
    try {
        Scanner sc = new Scanner(System.in);
        Connection con = DBConnection.getConnection();

        System.out.print("Enter Staff ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new Name: ");
        String name = sc.nextLine();

        System.out.print("Enter new Role: ");
        String role = sc.nextLine();

        System.out.print("Enter new Contact: ");
        String contact = sc.nextLine();

        if (contact.length() < 10) {
            System.out.println("❌ Invalid contact number.");
            return;
        }

        String sql = "UPDATE staff SET name=?, role=?, contact=? WHERE staff_id=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, role);
        ps.setString(3, contact);
        ps.setInt(4, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Staff updated successfully!");
        } else {
            System.out.println("❌ Staff ID not found.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}


}

