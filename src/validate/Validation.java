
package validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Validation {

    // biến cục bộ
    public Scanner scanner = new Scanner(System.in);
    //nhập chuỗi bắt buộc
    public String checkEmpty(String mess) {
        System.out.println(mess);
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Not empty!");
            } else {
                return input;
            }
        }
    }

//    public String stringInput(String mess) {
//        System.out.println(mess);
//        String input = scanner.nextLine();
//        return input;
//    }
    //nhập số nguyên dương
    public int checkPositiveNumber(String mess) {
        while (true) {
            try {
                int input = Integer.parseInt(checkEmpty(mess));
                if (input > 0) {
                    return input;
                } else {
                    System.out.println("Input must be than 0!");
                }
            } catch (Exception e) {
                System.out.println("Must be integer!");
            }
        }
    }

//    public double checkSalaryPositiveNumber(String mess) {
//        while (true) {
//            try {
//                double input = Double.parseDouble(checkEmpty(mess));
//                if (input > 0) {
//                    return input;
//                } else {
//                    System.out.println("Input must be than 0!");
//                }
//            } catch (Exception e) {
//                System.out.println("Must be Double!");
//            }
//        }
//    }
    
    //nhập lựa chọn menu
    public int checkIntLimit(String mess, int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(checkEmpty(mess));
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.err.println("Input must in range: " + min + "->" + max);
                }
            } catch (Exception e) {
                System.err.println("Must be integer!");
            }
        }
    }
    
        // Cho phép người dùng bỏ trống nếu không muốn thay đổi giá trị
    public String optionalInput(String mess) {
        System.out.println(mess);
        return scanner.nextLine().trim();
    }

    // Cho phép bỏ trống để giữ giá trị cũ
    public int checkPositiveNumberAllowEmpty(String mess, int oldValue) {
        while (true) {
            System.out.println(mess);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return oldValue;
            }
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Input must be greater than 0!");
                }
            } catch (Exception e) {
                System.out.println("Must be integer!");
            }
        }
    }


//    public String getCurrentDay() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        LocalDate currentDate = LocalDate.now();
//        return currentDate.format(formatter);
//    }

    // nhấn shift + f6 để run main ở class Validation
//    public static void main(String[] args) {
//        Validation validation = new Validation();
//        System.out.println("Day: " + validation.getCurrentDay());
//    }
}
