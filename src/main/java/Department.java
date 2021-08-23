import java.util.ArrayList;

public class Department {

    private ArrayList<Manager> managers;

    public Department(ArrayList<Manager> managers) {
        this.managers = managers;
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }

    public void setManagers(ArrayList<Manager> managers) {
        this.managers = managers;
    }

    @Override
    public String toString() {
        return "Department{" +
                "managers=" + managers +
                '}';
    }

    public int getAllocationExpenses() {
        return managers.stream().mapToInt(Manager::getAllocationExpenses).sum();
    }

    public ArrayList<Manager> getIdleManagers() {
        ArrayList<Manager> idleManagers = new ArrayList<>();
        managers.forEach(manager -> idleManagers.addAll(manager.getIdleManagers()));
        return idleManagers;
    }
}
