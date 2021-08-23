import java.util.ArrayList;

public class Organization {

    private ArrayList<Department> departments;

    public Organization(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "departments=" + departments +
                '}';
    }

    public int getAllocationExpenses() {
        return departments.stream().mapToInt(Department::getAllocationExpenses).sum();
    }

    public ArrayList<Manager> getIdleManagers() {
        ArrayList<Manager> idleManagers = new ArrayList<>();
        departments.forEach(department -> idleManagers.addAll(department.getIdleManagers()));
        return idleManagers;
    }
}
