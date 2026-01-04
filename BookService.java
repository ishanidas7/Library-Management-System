package service;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import util.InputValidator;

public class BookService {

    public void viewBooks() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM books";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Books Available ---");
            System.out.println("ID\tTitle\t\tAuthor\t\tQuantity");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("book_id") + "\t" +
                    rs.getString("title") + "\t\t" +
                    rs.getString("author") + "\t\t" +
                    rs.getInt("quantity")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addBook() {
    try {
        Scanner sc = new Scanner(System.in);
        Connection con = DBConnection.getConnection();

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        
        if (!InputValidator.isValidId(id)) {
    System.out.println("❌ Invalid Book ID.");
    return;
}
if (!InputValidator.isValidText(title) || !InputValidator.isValidText(author)) {
    System.out.println("❌ Title/Author cannot be empty.");
    return;
}
if (!InputValidator.isValidQuantity(qty)) {
    System.out.println("❌ Quantity cannot be negative.");
    return;
}
        String sql = "INSERT INTO books VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, title);
        ps.setString(3, author);
        ps.setInt(4, qty);

        ps.executeUpdate();
        System.out.println("✅ Book added successfully!");

    } catch (Exception e) {
        System.out.println("❌ Error adding book.");
        e.printStackTrace();
    }
}

    public void removeBook() {
    try {
        Scanner sc = new Scanner(System.in);
        Connection con = DBConnection.getConnection();

        System.out.print("Enter Book ID to remove: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM books WHERE book_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Book removed successfully!");
        } else {
            System.out.println("❌ Book ID not found.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void updateBook() {
    try {
        Scanner sc = new Scanner(System.in);
        Connection con = DBConnection.getConnection();

        System.out.print("Enter Book ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new Title: ");
        String title = sc.nextLine();

        System.out.print("Enter new Author: ");
        String author = sc.nextLine();

        System.out.print("Enter new Quantity: ");
        int qty = sc.nextInt();

        if (qty < 0) {
            System.out.println("❌ Quantity cannot be negative.");
            return;
        }

        String sql = "UPDATE books SET title=?, author=?, quantity=? WHERE book_id=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, title);
        ps.setString(2, author);
        ps.setInt(3, qty);
        ps.setInt(4, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Book updated successfully!");
        } else {
            System.out.println("❌ Book ID not found.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

