package controller;

import java.util.HashMap;
import java.util.Map;
import model.Doctor;
import validate.Validation; // Đổi sang đúng package của bạn


public class DoctorController {

    private Map<String, Doctor> doctorDatabase = new HashMap<>();
    DoctorView doctorView = new DoctorView();
    Validation validation = new Validation();

    // Thêm bác sĩ
    public void addDoctor() {
        Doctor tempDoctor = new Doctor();
//        Doctor doctor = tempDoctor.inputDoctorDetails(); // Gọi trực tiếp từ Doctor
        Doctor doctor = new Doctor();
        doctor.inputDoctorDetails();

        if (doctorDatabase.containsKey(doctor.getCode())) {
            doctorView.displayMessage("❌ Doctor code already exists!");
        } else {
            doctorDatabase.put(doctor.getCode(), doctor);
            doctorView.displayMessage("✅ Doctor added successfully!");
        }
    }

    // Cập nhật thông tin bác sĩ
    public void updateDoctor() {
        String code = validation.checkEmpty("Enter doctor code to update: ");
        Doctor existing = doctorDatabase.get(code);

        if (existing != null) {
            System.out.println("Enter new details for doctor (leave blank to keep old value):");

            // Cho phép nhập lại từng trường, giữ nguyên nếu để trống
            String newName = validation.optionalInput("Enter new name (" + existing.getName() + "): ");
            String newSpecialization = validation.optionalInput("Enter new specialization (" + existing.getSpecialization() + "): ");
            int newAvailability = validation.checkPositiveNumberAllowEmpty("Enter new availability (" + existing.getAvailability() + "): ", existing.getAvailability());

            // Cập nhật
            if (!newName.isEmpty()) {
                existing.setName(newName);
            }
            if (!newSpecialization.isEmpty()) {
                existing.setSpecialization(newSpecialization);
            }
            existing.setAvailability(newAvailability);

            doctorView.displayMessage("✅ Doctor updated successfully!");
        } else {
            doctorView.displayMessage("❌ Doctor code does not exist.");
        }
    }

    // Xóa bác sĩ
    public void deleteDoctor() {
        String code = validation.checkEmpty("Enter doctor code to delete: ");
        if (doctorDatabase.containsKey(code)) {
            doctorDatabase.remove(code);
            doctorView.displayMessage("🗑️ Doctor deleted successfully!");
        } else {
            doctorView.displayMessage("❌ Doctor code does not exist.");
        }
    }

    // Tìm hoặc liệt kê bác sĩ
    public void searchDoctor() {
        String code = validation.optionalInput("Enter doctor code to search (leave empty to list all): ");

        if (code.isEmpty()) {
            if (doctorDatabase.isEmpty()) {
                doctorView.displayMessage("⚠️ No doctors available in the system.");
            } else {
                doctorView.displayDoctorList(doctorDatabase);
            }
        } else {
            if (doctorDatabase.containsKey(code)) {
                Doctor doctor = doctorDatabase.get(code);
                doctorView.displayDoctor(doctor);
            } else {
                doctorView.displayMessage("❌ Doctor code does not exist.");
            }
        }
    }

    // Menu hiển thị lựa chọn
    public void showMenu() {
        System.out.println("\n====== DOCTOR MANAGEMENT ======");
        System.out.println("1. Add Doctor");
        System.out.println("2. Update Doctor");
        System.out.println("3. Delete Doctor");
        System.out.println("4. Search Doctor");
        System.out.println("5. Exit");
    }

    // Chạy chương trình
    public void start() {
        while (true) {
            showMenu();
            int choice = validation.checkIntLimit("Enter choice: ", 1, 5);

            switch (choice) {
                case 1 ->
                    addDoctor();
                case 2 ->
                    updateDoctor();
                case 3 ->
                    deleteDoctor();
                case 4 ->
                    searchDoctor();
                case 5 -> {
                    System.out.println("👋 Exiting program...");
                    return;
                }
                default ->
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }
    class DoctorView {

    private Validation validation = new Validation();
    
//    public Doctor inputDoctorDetails() {
//        String code = validation.checkEmpty("Enter doctor code: ");
//        String name = validation.checkEmpty("Enter doctor name: ");
//        String specialization = validation.checkEmpty("Enter specialization: ");
//        int availability = validation.checkPositiveNumber("Enter availability (hours/week): ");
//        
//        return new Doctor(code, name, specialization, availability);
//    }

    public void displayDoctor(Doctor doctor) {
        System.out.printf("%-10s %-15s %-15s %-10d\n", 
                          doctor.getCode(), 
                          doctor.getName(), 
                          doctor.getSpecialization(), 
                          doctor.getAvailability());
    }

    public void displayDoctorList(Map<String, Doctor> doctors) {
        System.out.println("--------- Result ------------");
        System.out.printf("%-10s %-15s %-15s %-10s\n", 
                          "Code", "Name", "Specialization", "Availability");
        for (Doctor doctor : doctors.values()) {
            displayDoctor(doctor);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
}
