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
        String code;

        // Kiểm tra mã bác sĩ ngay khi nhập
        while (true) {
            code = validation.checkEmpty("Enter doctor code: ").trim();

            if (doctorDatabase.containsKey(code)) {
                doctorView.displayMessage("❌ Doctor code already exists!");
                System.out.println("Please enter another doctor code.");
            } else {
                break;
            }
        }

        // Chỉ nhập thông tin khác khi mã bác sĩ hợp lệ
        
        String name = validation.checkLetters("Enter doctor name: ");
        String specialization = validation.checkLetters("Enter specialization: ");

        int availability = validation.checkPositiveNumber("Enter availability (hours/week): ");

        Doctor doctor = new Doctor(code, name, specialization, availability);
        doctorDatabase.put(code, doctor);

        doctorView.displayMessage("✅ Doctor added successfully!");
    }


    // Cập nhật thông tin bác sĩ
    public void updateDoctor() {
        String code = validation.checkEmpty("Enter doctor code to update: ");
        Doctor existing = doctorDatabase.get(code);
        //null neu kh thay doi doctor
        if (existing != null) {
            System.out.println("Enter new details for doctor (leave blank to keep old value):");

            // Cho phép nhập lại từng trường, giữ nguyên nếu để trống
            
            String newName = validation.optionalLetters("Enter new name (" + existing.getName() + "): ");
            String newSpecialization = validation.optionalLetters("Enter new specialization (" + existing.getSpecialization() + "): ");

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
