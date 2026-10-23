package dao;

import model.Patient;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void addPatient(Patient patient) {
        String query = "INSERT INTO patients (name, age, disease) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getDisease());
            stmt.executeUpdate();

            System.out.println("Patient Added Successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> getAllPatients() {
        String query = "SELECT * FROM patients";
        List<Patient> patients = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Patient p = new Patient(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("disease")
                );
                patients.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public void deletePatient(int id) {
        String query = "DELETE FROM patients WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Patient Deleted Successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

