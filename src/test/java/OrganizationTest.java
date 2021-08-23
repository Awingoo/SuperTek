import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;


public class OrganizationTest {

    private static Organization organization;
    private static ArrayList<Department> departments;
    private static ArrayList<Manager> topLevelManagers;
    private static Manager managerA;
    private static Manager managerB;
    private static Manager managerC;
    private static Manager managerD;
    private static Manager managerE;


    @Before
    public void setup(){

        Employee developerA = new Employee("Developer A", "Developer", 2000);
        Employee developerB = new Employee("Developer B", "Developer", 2000);

        Employee qaTester = new Employee("QA Tester", "QA Tester", 1000);

        managerA = new Manager("Manager A","Manager", 600);
        managerB = new Manager("Manager B","Manager", 600);
        managerC = new Manager("Manager C","Manager", 600);
        managerD = new Manager("Manager D","Manager", 600);
        managerE = new Manager("Manager E","Manager", 600);
        
        ArrayList<Employee> employeesOfManagerA = new ArrayList<>();
        ArrayList<Employee> employeesOfManagerB = new ArrayList<>();
        ArrayList<Employee> employeesOfManagerC = new ArrayList<>();
        ArrayList<Employee> employeesOfManagerE = new ArrayList<>();

        employeesOfManagerA.add(managerB);
        employeesOfManagerB.add(developerA);
        employeesOfManagerB.add(qaTester);
        managerB.setEmployees(employeesOfManagerB);
        managerA.setEmployees(employeesOfManagerA);

        employeesOfManagerC.add(managerD);
        managerC.setEmployees(employeesOfManagerC);

        employeesOfManagerE.add(developerB);
        managerE.setEmployees(employeesOfManagerE);

        topLevelManagers = new ArrayList<>();
        topLevelManagers.add(managerA);
        topLevelManagers.add(managerC);
        topLevelManagers.add(managerE);

        departments = new ArrayList<>();
        Department department = new Department(topLevelManagers);
        departments.add(department);

        organization = new Organization(departments);

    }

    @Test
    public void checkAllocationIsCorrectForManagerA() {
        Assert.assertEquals(managerA.getAllocationExpenses(), 4200);
    }


    @Test
    public void checkAllocationIsCorrectForManagerC() {
        Assert.assertEquals(managerC.getAllocationExpenses(), 1200);
    }

    @Test
    public void checkAllocationIsCorrectForManagerE() {
        Assert.assertEquals(managerE.getAllocationExpenses(), 2600);
    }

    @Test
    public void checkAllocationIsCorrectForDepartment() {
        Assert.assertEquals(departments.get(0).getAllocationExpenses(), 8000);
    }

    @Test
    public void checkAllocationIsCorrectForOrganization() {
        Assert.assertEquals(organization.getAllocationExpenses(), 8000);
    }

    @Test
    public void checkForIdleManagersForManagerA() {
        ArrayList<Manager> idleManagers = new ArrayList<>();
        Assert.assertEquals(managerA.getIdleManagers(), idleManagers);
    }

    @Test
    public void checkForIdleManagersForManagerC() {
        ArrayList<Manager> idleManagers = new ArrayList<>();
        idleManagers.add(managerD);
        Assert.assertEquals(managerC.getIdleManagers(), idleManagers);
    }

    @Test
    public void checkForIdleManagersForManagerE() {
        ArrayList<Manager> idleManagers = new ArrayList<>();
        Assert.assertEquals(managerE.getIdleManagers(), idleManagers);
    }

    @Test
    public void checkForIdleManagersForDepartment() {
        ArrayList<Manager> idleManagers = new ArrayList<>();
        idleManagers.add(managerD);
        Assert.assertEquals(departments.get(0).getIdleManagers(), idleManagers);
    }

    @Test
    public void checkForIdleManagersForOrganization() {
        ArrayList<Manager> idleManagers = new ArrayList<>();
        idleManagers.add(managerD);
        Assert.assertEquals(organization.getIdleManagers(), idleManagers);
    }

}
