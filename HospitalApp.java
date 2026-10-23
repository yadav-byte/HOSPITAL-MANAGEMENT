package main;

import dao.PatientDAO;
import model.Patient;

import java.util.List;
import java.util.Scanner;

public class HospitalApp {
    public static void main(String[] args) {
        PatientDAO dao = new PatientDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Hospital Management ===");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Delete Patient");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice;
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number:");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Enter valid age:");
                        sc.next();
                    }
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Disease: ");
                    String disease = sc.nextLine();

                    dao.addPatient(new Patient(0, name, age, disease));
                    break;

                case 2:
                    List<Patient> list = dao.getAllPatients();
                    list.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter Patient ID to Delete: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Enter a valid ID:");
                        sc.next();
                    }
                    int id = sc.nextInt();
                    sc.nextLine();
                    dao.deletePatient(id);
                    break;

                case 4:
                    System.out.println("Thank you for using the Hospital Management System!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Option!");
            }
        }
    }
}

