package giir2105MV.salariati.Model;

import giir2105MV.salariati.controller.EmployeeController;
import giir2105MV.salariati.enumeration.DidacticFunction;
import giir2105MV.salariati.exception.EmployeeException;
import giir2105MV.salariati.model.Employee;
import giir2105MV.salariati.repository.implementations.EmployeeRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class EmployeeTest {
    private EmployeeController ctrl;
    private EmployeeRepository repo;

    @Before
    public void init() {

        repo = new EmployeeRepository();
        ctrl = new EmployeeController(repo);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Test
    public void testECP1() throws EmployeeException {
        Employee employee = new Employee("iulia","gadalean", "2970327123456", DidacticFunction.TEACHER, "4000");
        ctrl.addEmployee(employee);
    }

//    @Test
//    public void testECP2() throws EmployeeException {
//        Employee employee = new Employee(123,"gadalean", "2970327123456", DidacticFunction.TEACHER, "4000");
//        ctrl.addEmployee(employee);
//    }


//    @Test
//    public void testECP3() throws EmployeeException {
//        Employee employee = new Employee("iulia","gadalean",123, DidacticFunction.TEACHER, "100");
//        ctrl.addEmployee(employee);
//    }

    @Test
    public void testBVA1() throws EmployeeException {
        int nrEmployees = ctrl.getEmployeesList().size();
        Employee employee = new Employee("iulia","gadalean", "2970327123456", DidacticFunction.TEACHER, "4000");
        ctrl.addEmployee(employee);
        assert (ctrl.getEmployeesList().size() == nrEmployees+1);
    }

    @Test(expected=EmployeeException.class)
    public void testBVA2() throws EmployeeException {
        Employee employee = new Employee("iulia","gadalean", "29703271234569", DidacticFunction.TEACHER, "4000");
        ctrl.addEmployee(employee);
    }

    @Test(expected=EmployeeException.class)
    public void testBVA3() throws EmployeeException {
        Employee employee = new Employee("iulia","gadalean", "2970327123456", DidacticFunction.TEACHER, "0");
        ctrl.addEmployee(employee);
    }

    @Test
    public void testBVA4() throws EmployeeException {
        int nrEmployees = ctrl.getEmployeesList().size();
        Employee employee = new Employee("iulia", "gadalean","2970327123456", DidacticFunction.TEACHER, "10");
        ctrl.addEmployee(employee);
        assert (ctrl.getEmployeesList().size() == nrEmployees+1);
    }
}