/*
 * Class Staff là class nằm ở vị trí cao nhất
 * trên inheritance tree.
 */
abstract public class Staff  {
    private int id;
    private String name;
    private int age;
    private double factorSalary;
    private String beginDate;
    private Department dept;
    private int vacationDays;

    // Tạo Constructor có tham số:
    public Staff (int id, String name, int age, double factorSalary, String beginDate,
                  Department dept, int vacationDays) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.factorSalary = factorSalary;
        this.beginDate = beginDate;
        this.dept = dept;
        this.vacationDays = vacationDays;
    }

// Tạo các setter và getter cho mỗi field:
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    //
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    //
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    //
    public void setFactorSalary(double factorSalary) {
        this.factorSalary = factorSalary;
    }
    public double getFactorSalary() {
        return factorSalary;
    }

    //
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }
    public String getBeginDate() {
        return beginDate;
    }

    //
    public void setDept(Department dept) {
        this.dept = dept;
    }
    public Department getDept() {
        return dept;
    }

    //
    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }
    public int getVacationDays() {
        return vacationDays;
    }
// end of setters and getters.

    // [1] khai báo phương thức displayInformation là phương thức abstract:
    abstract void displayInformation();
}
