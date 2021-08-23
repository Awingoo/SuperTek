import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Manager extends Employee {

    private ArrayList<Employee> employees;

    public Manager(String name, String title, int cost) {
        super(name, title, cost);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "employees=" + employees +
                '}';
    }

    public int getAllocationExpenses() {
        return this.getCost() + recursiveAllocationCalculation(employees);
    }

    public static int recursiveAllocationCalculation(ArrayList<Employee> allEmployees) {
        AtomicInteger allocationAmount = new AtomicInteger();
        allEmployees.forEach(employee -> {
            allocationAmount.addAndGet(employee.getCost());
            if (employee instanceof Manager) {
                if (((Manager) employee).getEmployees() != null && !((Manager) employee).getEmployees().isEmpty()) {
                    ArrayList<Employee> subEmployees = ((Manager) employee).getEmployees();
                    allocationAmount.addAndGet(subEmployees.stream().mapToInt(Employee::getCost).sum());
                    recursiveAllocationCalculation(subEmployees);
                }
            }
        });
        return allocationAmount.intValue();
    }

    public ArrayList<Manager> getIdleManagers() {
        return recursiveIdleManagers(employees);
    }

    public static ArrayList<Manager> recursiveIdleManagers(ArrayList<Employee> allEmployees) {
        ArrayList<Manager> idleManagers = new ArrayList<>();
        allEmployees.forEach(employee -> {
            if (employee instanceof Manager) {
                if (((Manager) employee).getEmployees() == null || ((Manager) employee).getEmployees().isEmpty()) {
                    idleManagers.add((Manager) employee);
                } else {
                    recursiveIdleManagers(((Manager) employee).getEmployees());
                }
            }
        });
        return idleManagers;
    }
}
