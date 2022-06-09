import java.util.Locale;

/* Class Manager chứa thông tin chung về nhân viên cấp quản lý.
 * Class Manager triển khai (extends) từ class Staff
 * và implements từ class ICalculator:
 */
public class Manager extends Staff implements ICalculator {
    private String title;

    // Tạo constructor có tham số:
    public Manager(int id, String name, int age, double factorSalary, String beginDate,
                   Department dept, int vacationDays ,String title) {
        super(id, name, age, factorSalary, beginDate, dept, vacationDays);
        this.title = title;
    }



    // Tạo setter và getter cho field "title":
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }



    // [1] Tạo phương thức trả về lương của nhân viên cấp quản lý:
    @Override
    public double calculateSalary() {
        double responsibleWage = 0; // Lương trách nhiệm.

        if (title.toLowerCase().equals("business leader")) {
            responsibleWage = 8000000;
        } else if (title.toLowerCase().equals("project leader ")) {
            responsibleWage = 5000000;
        } else if (title.toLowerCase().equals("technical leader")) {
            responsibleWage = 6000000;
        }
        return super.getFactorSalary() * 5000000 + responsibleWage;
    }

    // [2] Tạo phương thức hiển thị thông tin vể nhân viên cấp quản lý:
    @Override
    public void displayInformation() {
        System.out.printf("%-15d %-35s %-5d %-15.2f %-16s %-30s %-15d %-16s %-30s %30.2f \n",
                super.getId(),super.getName(),super.getAge(),super.getFactorSalary(),super.getBeginDate(),
                super.getDept().getNameOfDept(),super.getVacationDays(),"",title,calculateSalary());

    }
}
