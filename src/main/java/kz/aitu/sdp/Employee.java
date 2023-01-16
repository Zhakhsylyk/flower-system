package kz.aitu.sdp;

public class Employee {
    private int employee_id;
    private String employee_fname;
    private String employee_lname;
    private String employee_position;
    private String employee_phone;

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_fname() {
        return employee_fname;
    }

    public void setEmployee_fname(String employee_fname) {
        this.employee_fname = employee_fname;
    }

    public String getEmployee_lname() {
        return employee_lname;
    }

    public void setEmployee_lname(String employee_lname) {
        this.employee_lname = employee_lname;
    }

    public String getEmployee_position() {
        return employee_position;
    }

    public void setEmployee_position(String employee_position) {
        this.employee_position = employee_position;
    }

    public String getEmployee_phone() {
        return employee_phone;
    }

    public void setEmployee_phone(String employee_phone) {
        this.employee_phone = employee_phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", employee_fname='" + employee_fname + '\'' +
                ", employee_lname='" + employee_lname + '\'' +
                ", employee_position='" + employee_position + '\'' +
                ", employee_phone='" + employee_phone + '\'' +
                '}';
    }
}
