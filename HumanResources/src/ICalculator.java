/*  Tạo interface ICalculator chứa hàm tính lương cho
 *  nhân viên thông  và nhân viên cấp quản lý:
 */
public interface ICalculator {
    int getId();
    String getName();
    // Khai báo phương thức trả về lương nhân viên:
    double calculateSalary();
}
