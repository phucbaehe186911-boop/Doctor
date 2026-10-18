package ui;


import controller.DoctorController;
import validate.Validation;

public class Main {

    public static void main(String[] args) {
        DoctorController controller = new DoctorController();
        Validation validation = new Validation();

        // Chạy chương trình
        while (true) {
            controller.showMenu();
            int choice = validation.checkIntLimit("Enter choice: ", 1, 5);

            switch (choice) {
                case 1 ->
                    controller.addDoctor();
                case 2 ->
                    controller.updateDoctor();
                case 3 ->
                    controller.deleteDoctor();
                case 4 ->
                    controller.searchDoctor();
                case 5 -> {
                    System.out.println("👋 Exiting program...");
                    return;
                }
                default ->
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }
}
