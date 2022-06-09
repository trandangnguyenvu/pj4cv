// Tạo class chứa thông tin chung về mỗi bộ phận trong công ty:
public class Department {
    private String idOfDept;
    private String nameOfDept;
    private int staffsNum;

    // Tạo constructor không tham số:
    public Department() {

    }

    // Tạo constructor có tham số:
    public Department(String idOfDept, String nameOfDept, int staffsNum) {
        this.idOfDept = idOfDept;
        this.nameOfDept = nameOfDept;
        this.staffsNum = staffsNum;
    }

    // Overloading1 cho constructor:
    public Department(String idOfDept, String nameOfDept) {
        this.idOfDept = idOfDept;
        this.nameOfDept = nameOfDept;
    }

    // Overloading2 cho constructor:
    public Department(String idOfDept, int staffsNum) {
        this.idOfDept = idOfDept;
        this.staffsNum = staffsNum;
    }

// Tạo setter và getter cho mỗi field:
    public void setIdOfDept(String idOfDept) {
        this.idOfDept = idOfDept;
    }
    public String getIdOfDept() {
        return idOfDept;
    }

    //
    public void setNameOfDept(String nameOfDept) {
        this.nameOfDept = nameOfDept;
    }
    public String getNameOfDept() {
        return nameOfDept;
    }

    //
    public void setStaffsNum(int staffsNum) {
        this.staffsNum = staffsNum;
    }
    public int getStaffsNum() {
        return staffsNum;
    }

    // [1]
    @Override
    public String toString() {
        String deptString = String.format("%-15s %-35s %-15d",
                idOfDept,nameOfDept,staffsNum);
        return deptString;
    }
}
