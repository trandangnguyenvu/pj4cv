import java.io.PrintStream;

/* Class Employee chứa thông tin chung về nhân viên thông thường.
 * Class Employee triển khai (extends) từ class Staff
 * và implements từ class ICalculator:
 */
public class Employee extends Staff implements ICalculator {
    private int addTimeNum;

    // tạo constructor có tham số:
    public Employee(int id, String name, int age, double factorSalary, String beginDate,
                    Department dept, int vacationDays, int addTimeNum) {
        super(id, name, age, factorSalary, beginDate, dept, vacationDays);
        this.addTimeNum = addTimeNum;
    }


    // tạo getter và setter cho field "addTimeNum":
    public void setAddTimeNum(int addTimeNum) {
        this.addTimeNum = addTimeNum;
    }
    public int getAddTimeNum() {
        return addTimeNum;
    }


    // [1] Tạo phương thức trả về lương nhân viên:
    @Override
    public double calculateSalary() {
        return super.getFactorSalary() * 3000000 + addTimeNum * 200000;
    }

    // [2] Tạo phương thức hiển thị thông tin nhân viên:
    @Override
    public void displayInformation() {
        System.out.printf("%-15d %-35s %-5d %-15.2f %-16s %-30s %-15d %-16d %-30s %30.2f \n",
                super.getId(),super.getName(),super.getAge(),super.getFactorSalary(),super.getBeginDate(),
                super.getDept().getNameOfDept(),super.getVacationDays(),addTimeNum,"",calculateSalary());
    }
}
