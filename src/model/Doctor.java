
package model;

import java.util.Scanner;
import validate.Validation; // Import lớp Validation của bạn

public class Doctor {

    private String code;
    private String name;
    private String specialization;
    private int availability;


    public Doctor() {
    }

    public Doctor(String code, String name, String specialization, int availability) {
        setCode(code);
        setName(name);
        setSpecialization(specialization);
        setAvailability(availability);
    }

//    public String getCode() { return code; }
//    public void setCode(String code) { this.code = code; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getSpecialization() { return specialization; }
//    public void setSpecialization(String specialization) { this.specialization = specialization; }
//
//    public int getAvailability() { return availability; }
//    public void setAvailability(int availability) { this.availability = availability; }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Doctor code cannot be empty!");
        }
        this.code = code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Doctor name cannot be empty!");
        }
        this.name = name.trim();
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty!");
        }
        this.specialization = specialization.trim();
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        if (availability < 0) {
            throw new IllegalArgumentException("Availability cannot be negative!");
        }
        this.availability = availability;
    }
    
    

   
    public void inputDoctorDetails() {
          Validation validation = new Validation(); // tạo mới đối tượng Validation để nhập dữ liệu

        
        String code = validation.checkEmpty("Enter doctor code: ");
        String name = validation.checkEmpty("Enter doctor name: ");
        String specialization = validation.checkEmpty("Enter specialization: ");
        int availability = validation.checkPositiveNumber("Enter availability (hours/week): ");
        
    }
    
//   public static Doctor inputDoctorDetails() {
//    Validation validation = new Validation(); // tạo mới đối tượng Validation để nhập dữ liệu
//
//    String code = validation.checkEmpty("Enter doctor code: ");
//    String name = validation.checkEmpty("Enter doctor name: ");
//    String specialization = validation.checkEmpty("Enter specialization: ");
//    int availability = validation.checkPositiveNumber("Enter availability (hours/week): ");
//
//    // Sau khi nhập xong, tạo đối tượng Doctor mới từ dữ liệu người dùng
//    Doctor doctor = new Doctor();
//    doctor.setCode(code);
//    doctor.setName(name);
//    doctor.setSpecialization(specialization);
//    doctor.setAvailability(availability);
//
//    return doctor;
//}

    @Override
    public String toString() {
        return String.format("Code: %s | Name: %s | Specialization: %s | Availability: %d hours/week",
                code, name, specialization, availability);
    }
}
