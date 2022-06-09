import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jdk.dynalink.beans.StaticClass;
import java.rmi.server.LogStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.*;

// Class chứa thông tin chung về nhân viên và tạo luồng chính của chương trình:
public class HumanResources {
    static Scanner sc = new Scanner(System.in);
    // [0]
    /* Tạo ArrayList với type là class Staff sẽ lưu trữ thông tin
     * của toàn bộ nhân viên trong công ty:
     */
    static ArrayList<Staff> staffs = new ArrayList<Staff>();

    /* Tạo ArrayList với type là class Department để:
     * nhập một lần và lưu trữ thông tin cho từng Department của công ty,
     * thông tin của mỗi Department bao gồm ID và Name:
     */
    static ArrayList<Department> depts = new ArrayList<Department>();




    // Phương thức main: tạo luồng cho chương trình:
    public static void main(String[] args) {
        // Thiết lập dữ liệu cho mảng depts là mảng lưu trữ thông tin cho từng bộ phận trong cty:
        deptsData();

        /* thêm 5 đối tượng
         * vào mảng staffs để kiểm tra các phương thức trong luồng chính.
         * mảng staffs là mảng lưu trữ thông tin của toàn bộ nhân viên trong công ty.
         */
        staffs.add(new Employee(1, "Minh", 20, 2.46, "01/01/2015", new Department("co1", "business"), 12, 3));
        staffs.add(new Manager(2, "Vũ", 30, 3.1, "01/01/2010", new Department("co2", "project"), 14, "Business leader"));
        staffs.add(new Manager(3, "Huy", 32, 3.4, "01/01/2011", new Department("co3", "Technical"), 14, "technical leader"));
        staffs.add(new Manager(4, "Hùng", 34, 3.4, "01/01/2000", new Department("co3", "Technical"), 15, "technical manager"));
        staffs.add(new Employee(5, "Xuân", 20, 2.46, "01/01/2018", new Department("co4", "reception"), 12, 4));

        // Menu của chương trình:
        System.out.println("\nWelcome!\n\nThis is the staffs infomation managing program.\n");
        System.out.println("The following is the menu of the program:");

        /*
         * Tạo vòng lặp xuất hiện ô nhập số để người dùng chọn tác vụ mình muốn thực hiện.
         * Chỉ khi người dùng chọn thoát khỏi chương trình thì vòng lặp này mới kết thúc.
         */
        boolean isLoopExit = false;
        do {
            System.out.printf("\n[1] Staffs List\n[2] Department List\n[3] Staffs List (belong to the Department)" +
                    "\n[4] Add a new staff\n[5] Staff Searching (by ID or by Name)\n[6] Salary Table (sorting up)" +
                    "\n[7] Salary Table (sorting down)\n[8] Exit\n");
            System.out.print("\nPlease write one of 1-8 numbers for the task that you want to perform!");
            int input = sc.nextInt();
            if (input == 1) {
                // [1]
                // Phương thức hiển thị danh sách nhân viên hiện có trong công ty:
                showInformationOfArray();
            } else if (input == 2) {
                // [2]
                // Phương thức hiển thị các bộ phận trong công ty:
                showDepartment();
            } else if (input == 3) {
                // [3]
                // Phương thức hiển thị các nhân viên theo từng bộ phận:
                showStaffsBelongToDept();
            } else if (input == 4) {
                // [4]
                // Thêm nhân viên mới vào công ty:
                addLoop();
                yesOrNoLoop();
            } else if (input == 5) {
                // [5]
                // Phương thức tìm kiếm nhân viên theo tên hoặc mã nhân viên
                /* Người dùng gõ vào ID hoặc Name của nhân viên muốn tìm kiếm.
                 * Nếu tìm thấy, chương trình sẽ hiển thị thông tin,
                 * Nếu không tìm thấy, chương trình sẽ báo "ID or Name is not found."
                 * Sau đó Chương trình sẽ hỏi người dùng có muốn tiếp tục tìm kiếm hay không,
                 * Nếu đồng ý thì chương trình sẽ chạy lại phương thức tìm kiếm.
                 */
                staffSearching();
                yesOrNoSearchingLoop();
            } else if (input == 6) {
                // [6]
                /* Phương thức hiển thị bảng lương của nhân viên toàn công ty, sắp xếp theo thứ
                 * tăng dần của lương:
                 */
                showSalaryTableSort();
            } else if (input == 7) {
                // [7]
                /* Phương thức hiển thị bảng lương của nhân viên toàn công ty, sắp xếp theo thứ
                 * giảm dần của lương:
                 */
                showSalaryTableDownSort();
            } else if (input == 8) {
                isLoopExit = true;
            }
        } while (!isLoopExit);

    } // END OF MAIN METHOD




    // [0]
    // Phương thức đưa dữ liệu cho mảng depts, dữ liệu là thông tin của từng bộ phận (department) trong cty.
    public static void deptsData() {
        /*
         * Nhập thông tin bao gồm ID và name của mỗi bộ phận (department) trong công ty vào
         * mảng depts.Bằng cách:
         * thêm phần tử mới vào mảng, mỗi phần tử là một object (1 thể hiện) của class Department,
         * mỗi object chứa 2 giá trị của 2 field: idOfDept và nameOfDept:
         */
        depts.add(new Department("co1","Business"));
        depts.add(new Department("co2","Project"));
        depts.add(new Department("co3","Technical"));
        depts.add(new Department("co4","Reception"));
        depts.add(new Department("co5","Accounting"));
    }

    // [1]
    // Hiển thị danh sách nhân viên hiện có trong công ty:
    /* bằng cách duyệt qua mảng staffs, và với mỗi phần tử của mảng thì sẽ
     * dùng phương thức displayInformation() để hiển thị thông tin.
     */
    public static void showInformationOfArray() {
        System.out.println("\nINFORMATION OF STAFFS:\n");
        System.out.printf("%-15s %-35s %-5s %-15s %-16s %-30s %-15s %-16s %-30s %30s \n\n",
                "ID", "Name", "Age", "Factor salary", "The begin date", "Department", "Vacation days", "Overtime hours", "Title", "Salary");
        for (int i = 0; i < staffs.size(); i++) {
            staffs.get(i).displayInformation();
        }
    }


    // [2]
    // Tạo phương thức hiển thị các bộ phận trong công ty:
    /*
     * Duyệt qua mảng depts (mảng của class Department) để hiển thị thông tin
     * của mỗi bộ phận trong công ty.
     */
    public static void showDepartment() {
        /*
         * Duyệt qua mảng depts của class Department
         * và hiển thị thông tin của mỗi bộ phận trong công ty:
         */
        System.out.println("\nINFORMATION OF DEPARTMENTS:\n");
        System.out.printf("%-15s %-35s %-15s","ID","Name","Total of staffs\n\n");
        for (int i = 0; i < depts.size(); i++) {
            int countStaffs = 0;
            for (int j = 0; j < staffs.size(); j++) {
                if (depts.get(i).getIdOfDept().toLowerCase().equals(
                        staffs.get(j).getDept().getIdOfDept().toLowerCase()
                )) {
                    countStaffs +=1;
                }
            }
            depts.get(i).setStaffsNum(countStaffs);
            /*
             * Hiển thị nội dung mỗi phần tử của mảng depts (mỗi phần tử của mảng là
             * một object của class Department)
             * thông qua phương thức toString.
             * (class Deppartment có phương thức toString ghi đè lên phương thức toString của
             * class Object).
             */
            System.out.println(depts.get(i));
        }
    }


    // [3]
    // Hiển thị các nhân viên theo từng bộ phận.
    /* Duyệt qua mảng depts của class Department, in ra tên của Department.
     * Mỗi một lần duyệt qua một phần tử của mảng depts sẽ thực hiện duyệt qua mảng staffs.
     * khi duyệt qua mảng staffs của class Staff: tại mỗi phần tử, nếu ID (ID of Department)
     * của object dept (instance của class Department) của phần tử đó trùng với ID
     * của mảng depts (mảng của class Department) thì:
     * hiển thị thông tin của phần tử đó, cũng tức là thông tin của
     * nhân viên thuộc Department đang được duyệt trong mảng depts.
     * thông tin được hiển thị bao gồm ID và tên của nhân viên.
     */
    public static void showStaffsBelongToDept() {
        System.out.println("\nSTAFFS OF EACH DEPARTMENT:\n");
        System.out.printf("%-15s %-35s %-5s %-15s %-16s %-30s %-15s %-16s %-30s %30s \n\n",
                "ID", "Name", "Age", "Factor salary", "The begin date", "Department", "Vacation days", "Overtime hours", "Title", "Salary");
        for (Department department:depts) {
            System.out.println("Department: " + department.getNameOfDept());
            System.out.println();
            for (Staff staffsBelongToDept:staffs) {
                if (staffsBelongToDept.getDept().getIdOfDept().equals(department.getIdOfDept())) {
                    staffsBelongToDept.displayInformation();
                }
            }
            System.out.println();
        }
    }




    // [4]
    // Thêm nhân viên mới vào công ty:
    /* Tạo vòng lặp vô tận để yêu cầu người dùng xác nhận
     * mình là employee hoặc là manager.
     * Vòng lặp này gọi là vòng lặp của phương thức "thêm thông tin nhân viên mới" (object) vào mảng.
     * Tùy theo câu trả lời của người dùng là "1" (tương ứng employee)
     * hoặc "2" (tương ứng manager) mà lần lặp đó sẽ khởi tạo một object
     * là một thể hiện của class Employee hoặc là class Manager.
     * Hai class Employee và Manager kế thừa abstract class Staff.
     * object đó sẽ được thêm vào ArrayList staffs như là một phần tử của ArrayList staffs.
     * Sau đó sẽ thoát khỏi vòng lặp "thêm thông tin".
     * Nếu câu trả lời của người dùng không phải là một trong hai kết quả hoặc
     * "1" hoặc "2" thì chạy lại vòng lặp "thêm thông tin nhân viên mới".

     * Sau đó sẽ tạo vòng lặp vô tận hỏi người dùng
     * có muốn nhập thêm thông tin về nhân viên mới khác (thêm object vào mảng) không,
     * Vòng lặp này gọi là vòng lặp "muốn tiếp tục hay không".
     * Nếu không thì kết thúc vòng lặp "thêm thông tin nhân viên mới",
     * Nếu có thì tiếp tục vòng lặp "thêm thông tin nhân viên mới".
     */


    // [4/a]
    // Tạo vòng lặp addLoop() "thêm thông tin nhân viên mới":
    /* Trước hết là tạo các phương thức con sẽ được gọi đến trong phương thức addLoop:
     * Các phương thức con này là các phương thức nhập dữ liệu và sau đó kiểm tra tính hợp lệ
     * của dữ liệu vừa nhập.
     * Các phương thức nhập liệu này sẽ được dùng chung bởi hai subclass Employee và Manager.
     */


    // [4/a/1]
    /*
     * Phương thức nhập ID và kiểm tra tính hợp lệ của ID (có phải số nguyên hay không và có bị
     * trùng với những ID đã tồn tại hay không):
     * Phương thức này tạo vòng lặp nhập ID của nhân viên, mỗi lần lặp sẽ hiện ô nhập ID. sau khi
     * người dùng nhập ID và enter, chuỗi vừa nhập nếu convert được sang số nguyên thì
     * sẽ được gán cho biến inputID và phương thức
     * sẽ so sánh giá trị ID đó có trùng với những ID đã có trong mảng staffs hay không.
     * Nếu không thì dừng vòng lặp. Giá trị ID này sẽ được phương thức addLoop sử dụng sau đó.
     * Nếu trùng thì sẽ yêu cầu người dùng nhập lại ID.
     * (Nếu ID không phải là số nguyên thì sẽ xử lý như một ngoại lệ).
     * Phương thức này trả về giá trị ID.
     */
    public static int inputIdLoop() {
        int inputId = 0;
        int count = 0;
        do {
            try {
                System.out.print("ID (number only): ");
                inputId = Integer.parseInt(sc.next());
                for (Staff staff: staffs) {
                    if (staff.getId() == inputId) {
                        count = 1;
                        System.out.println("Your ID has already exist. Please write again!");
                        break;
                    } else {
                        count = 0;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Your number is not valid. Please write again!");
                count = 1;
            }
        } while (count != 0);
        return inputId;
    }

    // [4/a/2]
    // Phương thức nhập tuổi và kiểm tra tính hợp lệ của dữ liệu (có phải số nguyên hay không):
    /*
     * Để kiểm tra tính hợp lệ của dữ liệu (kiểu dữ liệu là số nguyên) thì dữ liệu nhập vào sẽ
     * là kiểu String, sau đó được chuyển sang kiểu int. Nếu chuyển sang kiểu int không được thì
     * sẽ xử lý như một ngoại lệ và yêu cầu người dùng nhập lại tuổi (sử dụng vòng lặp do while).
     * Sử dụng biến boolean để thiết lập điều kiện chạy lại vòng lặp cho while.
     * Phương thức này trả về giá trị là tuổi (kiểu int).
     */
    public static int inputAgeLoop() {
        int inputAge = 0;
        boolean isCorrectFormat = false;
        do {
            try {
                System.out.print("Age: ");
                inputAge = Integer.parseInt(sc.next());
                isCorrectFormat = true;
            } catch (NumberFormatException e) {
                System.out.println("Your number is not valid. Please write again!");
            }
        } while (!isCorrectFormat);
        return inputAge;
    }


    // [4/a/3]
    /* Phương thức nhập hệ số lương và kiểm tra tính hợp lệ của dữ liệu
     * (có phải số thực hay không):
     */
    public static double inputFactorSalaryLoop() {
        double inputFactorSalary = 0;
        boolean isCorrectFormat = false;
        do {
            try {
                System.out.print("Factor salary: ");
                inputFactorSalary = Double.parseDouble(sc.next());
                isCorrectFormat = true;
            } catch (NumberFormatException e) {
                System.out.println("Your number is not valid. Please write again!");
            }
        } while (!isCorrectFormat);
        return inputFactorSalary;
    }


    // [4/a/4]
    /*
     * Phương thức tạo vòng lặp vô tận buộc người dùng nhập đúng Department,
     * khi đó mới dừng vòng lặp và gán giá trị cho hai biến inputIdOfDept, inputNameOfDept.
     * Phương thức này trả về giá trị là một đối tượng của class Department gồm hai field là
     * id và name của Department.
     */
    public static Department inputDeptLoop() {
        int inputDept;
        Department department = new Department();
        for (int i = 0; ;i++) {
            System.out.println("Department: \n1 for Business\n2 for Project\n" +
                    "3 for Technical\n4 for Reception\n5 for Accounting" );
            inputDept = sc.nextInt();
            if (inputDept == 1) {
                department.setIdOfDept("co1");
                department.setNameOfDept("Business");
                break;
            } else if (inputDept == 2) {
                department.setIdOfDept("co2");
                department.setNameOfDept("Project");
                break;
            } else if (inputDept == 3) {
                department.setIdOfDept("co3");
                department.setNameOfDept("Technical");
                break;
            } else if (inputDept == 4) {
                department.setIdOfDept("co4");
                department.setNameOfDept("Reception");
                break;
            } else if (inputDept == 5) {
                department.setIdOfDept("co5");
                department.setNameOfDept("Accounting");
                break;
            }
        }
        return department;
    }

    // [4/a/5]
    /* Phương thức YÊU CẦU NGƯỜI DÙNG NHẬP NGÀY THÁNG ĐÚNG ĐỊNH DẠNG:
     * Nếu dữ liệu nhập vào không convert được sang định dạng ngày tháng năm được thì sẽ xử lý như một
     * ngoại lệ và yêu cầu người dùng nhập lại.
     * Nếu ô nhập không để trống và dữ liệu nhập vào hợp lệ thì sẽ được ghi lại và sẽ là giá trị
     * trả về của phương thức.
     */
    public static String dateEntry() {
        String inputBeginDate = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        boolean isCorrectFormat = false;
        Date date = null;
        do {
            try {
                System.out.print("Begin date (dd/MM/yyyy): ");
                String inputDate = sc.nextLine();
                date = formatter.parse(inputDate);
                isCorrectFormat = true;
            } catch (ParseException e) {
                System.out.println("The format of date is not correct. Please write again!");
            }
        } while(!isCorrectFormat);
        if (date != null) {
            inputBeginDate = formatter.format(date);
        }
        return inputBeginDate;
    }

    // [4/a/1.6]
    /* Phương thức nhập số ngày nghỉ phép và kiểm tra tính hợp lệ của dữ liệu
     * (có phải số nguyên hay không):
     */
    public static int inputVacationDaysLoop() {
        int inputVacationDays = 0;
        boolean isCorrectFormat = false;
        do {
            try {
                System.out.print("Vacation days: ");
                inputVacationDays = Integer.parseInt(sc.next());
                isCorrectFormat = true;
            } catch (NumberFormatException e) {
                System.out.println("Your number is not valid. Please write again!");
            }
        } while (!isCorrectFormat);
        return inputVacationDays;
    }


    // addLoop: phương thức tạo vòng lặp "thêm thông tin nhân viên mới":
    public static void addLoop() {
        System.out.println("\nADD A NEW STAFF:\n");
        /*
         * Phương thức buộc người dùng gõ ID khác với những ID đã tồn tại trong danh sách
         * các nhân viên của công ty:
         */
        int inputId = inputIdLoop();

        // Mục nhập tên nhân viên:
        sc.nextLine();
        System.out.print("Name: ");
        String inputName = sc.nextLine();

        // Mục nhập tuổi:
        int inputAge = inputAgeLoop();

        // Mục nhập hệ số lương:
        double inputFactorSalary = inputFactorSalaryLoop();

        // Mục nhập id và name của Department:
        Department department = inputDeptLoop();

        // Mục nhập số ngày nghỉ phép:
        int inputVacationDays = inputVacationDaysLoop();

        // Mục nhập ngày bắt đầu vào làm trong công ty:
        sc.nextLine();
        String inputBeginDate = dateEntry();

        /*
         * Tạo vòng lặp để người dùng chọn 1 trong 2: hoặc employee hoặc manager:
         */
        for (int i = 0; ; i++) {
            System.out.print("Are you a employee or a manager? (1 for employee, 2 for manager)");
            int input = sc.nextInt();
            if (input == 1) {
                System.out.print("Overtime hours: ");
                int inputAddTimeNum = sc.nextInt();
                System.out.println();

                /* Khởi tạo một đối tượng của class Employee và
                 * thêm vào cuối ArrayList staffs.
                 * các mục thông tin người dùng nhập vào theo đúng thứ tự
                 * đã được hướng dẫn sẽ được truyền vào cho tham số
                 * của hàm khởi tạo (constructor) đối tượng.
                 */
                staffs.add(new Employee(
                        inputId,
                        inputName,
                        inputAge,
                        inputFactorSalary,
                        inputBeginDate,
                        department,
                        inputVacationDays,
                        inputAddTimeNum
                ));
                break;
            } else if (input == 2) {
                sc.nextLine();
                System.out.print("Title: ");
                String inputTitle = sc.nextLine();
                System.out.println();

                /* Khởi tạo một đối tượng của class Manager và
                 * thêm vào cuối ArrayList staffs.
                 * các mục thông tin người dùng nhập vào theo đúng thứ tự
                 * đã được hướng dẫn sẽ được truyền vào cho tham số
                 * của hàm khởi tạo (constructor) đối tượng.
                 */
                staffs.add(new Manager(
                        inputId,
                        inputName,
                        inputAge,
                        inputFactorSalary,
                        inputBeginDate,
                        department,
                        inputVacationDays,
                        inputTitle
                ));
                break;
            }
        }
    }

    // [4/b]
    /* Phương thức tạo vòng lặp "muốn tiếp tục hay không" để hỏi người dùng
     * có muốn nhập thêm nhân viên mới hay không (chạy lại vòng lặp "thêm thông tin
     * nhân viên mới").
     */
    public static void yesOrNoLoop() {
        for (int i = 0; ; i++) {
            System.out.print("Do you want to continue? (y = yes, n = no)");
            String input = sc.next();
            if (input.toLowerCase().equals("y")) {
                addLoop();
            } else if (input.toLowerCase().equals("n")) {
                break;
            }
        }
    }

    // [5]
    // Tìm kiếm nhân viên theo tên hoặc mã nhân viên
    /* Duyệt qua mảng staffs để tìm phần tử có giá trị của biến id và name chứa một phần
     * hoặc toàn bộ chuỗi trùng với chuỗi ký tự mà người dùng nhập vào, nếu trùng khớp thì
     * hiển thị thông tin của nhân viên đó, nếu không thì hiển thị thông báo
     * "ID or Name is not found."
     * Sau đó chạy vòng lặp có chức năng hỏi người dùng có muốn tiếp tục tìm kiếm không,
     * nếu muốn thì sẽ chạy lại phương thức tìm kiếm, nếu không sẽ dừng vòng lặp.
     */
    // [5/a]
    public static void staffSearching() {
        System.out.println("\nSTAFF SEARCHING.\n");
        System.out.println("Write your staff ID or staff Name for staff searching:");
        sc.nextLine();
        System.out.println("ID / Name:");
        String inputIdOrName = sc.nextLine();
        int countStringsBeNotFound = 0;
        int countResult = 0;
        for (int i = 0; i < staffs.size(); i++) {
            if (String.valueOf(staffs.get(i).getId()).contains(inputIdOrName) ||
            staffs.get(i).getName().toLowerCase().contains(inputIdOrName.toLowerCase())) {
                countResult += 1;
                System.out.println("Result " + countResult +":");
                System.out.printf("%-15s %-35s %-5s %-15s %-16s %-30s %-15s %-16s %-30s %30s \n\n",
                        "ID", "Name", "Age", "Factor salary", "The begin date", "Department", "Vacation days", "Overtime hours", "Title", "Salary");

                staffs.get(i).displayInformation();
                System.out.println();
            } else {
                countStringsBeNotFound += 1;
            }
        }
        if (countStringsBeNotFound == staffs.size()) {
            System.out.println("ID or Name is not found.");
        }
    }
    // [5/b]
    public static void yesOrNoSearchingLoop() {
        for (int i = 0; ; i++) {
            System.out.print("Do you want to continue? (y = yes, n = no)");
            String input = sc.next();
            if (input.toLowerCase().equals("y")) {
                staffSearching();
            } else if (input.toLowerCase().equals("n")) {
                break;
            }
        }
    }


    // [6]
    /* Hiển thị bảng lương của nhân viên toàn công ty theo thứ tự của từ thấp đến cao
     * của lương.
     * Sử dụng phương thức sort của Collections để sort theo giá trị của lương.
     * Khi định nghĩa lại hàm compare: gọi đến hàm tính lương calculateSalary của interface
     * ICalculator bằng cách casting lại đối tượng o1, o1 từ kiểu của abstract Staff về kiểu
     * của interface ICalculator. vì Employee và Manager (đều extends Staff) đều implements ICalculator.
     */
    public static void showSalaryTableSort() {
        // Sort lại mảng staffs theo thứ tự từ thấp đến cao của lương:
        Collections.sort(staffs, new Comparator<Staff>() {
            @Override
            public int compare(Staff o1, Staff o2) {
                return ((ICalculator)o1).calculateSalary() >
                        ((ICalculator)o2).calculateSalary() ? 1 : -1;
            }
        });

        // Hiển thị bảng lương từ mảng staffs vừa được sort:
        System.out.println("\nSALARY TABLE (SORTING UP):\n");
        System.out.printf("%-15s %-35s %-5s %-15s %-16s %-30s %-15s %-16s %-30s %30s \n\n",
                "ID", "Name", "Age", "Factor salary", "The begin date", "Department", "Vacation days", "Overtime hours", "Title", "Salary");

        for (int i = 0; i < staffs.size(); i++) {
            staffs.get(i).displayInformation();
        }
    }


    // [7]
    /* Hiển thị bảng lương của nhân viên toàn công ty theo thứ tự từ cao xuống thấp
     * của lương.
     */
    public static  void showSalaryTableDownSort() {
        // Sort lại mảng staffs theo thứ tự từ cao xuống thấp của lương:
        Collections.sort(staffs, new Comparator<Staff>() {
            @Override
            public int compare(Staff o1, Staff o2) {
                return ((ICalculator)o1).calculateSalary() >
                        ((ICalculator)o2).calculateSalary() ? -1 : 1;
            }
        });

        // Hiển thị bảng lương từ mảng staffs vừa được sort:
        System.out.println("\nSALARY TABLE (SORTING DOWN):\n");
        System.out.printf("%-15s %-35s %-5s %-15s %-16s %-30s %-15s %-16s %-30s %30s \n\n",
                "ID", "Name", "Age", "Factor salary", "The begin date", "Department", "Vacation days", "Overtime hours", "Title", "Salary");

        for (Staff salaryDown:staffs) {
            salaryDown.displayInformation();
        }
    }
}
