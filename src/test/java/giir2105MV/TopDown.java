package giir2105MV;

import giir2105MV.salariati.controller.EmployeeController;
import giir2105MV.salariati.enumeration.DidacticFunction;
import giir2105MV.salariati.exception.EmployeeException;
import giir2105MV.salariati.model.Employee;
import giir2105MV.salariati.repository.implementations.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

public class TopDown {

    private EmployeeController ctrl;
    private EmployeeRepository repo;

    @Before
    public void init() throws EmployeeException {
        Employee firstEmployee = new Employee("Raluca","Vlad", "2960312125678", DidacticFunction.LECTURER, "10000");
        Employee secondEmployee = new Employee("Larisa","Ionel", "2930517125678", DidacticFunction.TEACHER, "10000");
        Employee thirdEmployee = new Employee("Marius","Manusa", "1960311125678", DidacticFunction.LECTURER, "10000");
        repo = new EmployeeRepository();
        ctrl = new EmployeeController(repo);
        ctrl.addEmployee(firstEmployee);
        ctrl.addEmployee(secondEmployee);
        ctrl.addEmployee(thirdEmployee);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @After
    public void after() {
        ctrl.deleteAll();
    }

    //A
    @Test
    public void testECP1() throws EmployeeException {
        Employee employee = new Employee("Iulia","Gadalean", "2970327123456", DidacticFunction.TEACHER, "4000");
        ctrl.addEmployee(employee);
    }

    //B
    @Test(expected = Exception.class)
    public void testModifyEmployee1() throws Exception {
        List<Employee> firstList = ctrl.getEmployeesList();
        ctrl.modifyEmployee(null,"Gadalean", DidacticFunction.TEACHER);
        List<Employee> secondList = ctrl.getEmployeesList();

        for (int i = 0; i < firstList.size(); i++){
            assert (firstList.get(i).getFunction() == secondList.get(i).getFunction());
        }
    }

    //C
    @Test
    public void testOrderByAge()throws EmployeeException {
        List<Employee> orderedEmployees = ctrl.orderByAge();
        for (int i = 0; i < orderedEmployees.size()-1; i++){
            assert (ctrl.compareCNPs(orderedEmployees.get(i),orderedEmployees.get(i+1)) == orderedEmployees.get(i).getCnp());
        }
    }

    //A-B
    @Test
    public void testTopDownAB() throws Exception{
        Employee employee = new Employee("Alin","Pop","1880217123456",DidacticFunction.TEACHER,"10000");
        ctrl.addEmployee(employee);

        ctrl.modifyEmployee("Alin","Pop",DidacticFunction.LECTURER);

        List<Employee> result = ctrl.getEmployeesList();

        for (int i = 0; i < result.size() - 1; i++) {
            if(result.get(i).getFirstName().equals("Alin")){
                assert (result.get(i).getFunction().equals(DidacticFunction.LECTURER));
            }
        }
    }

    //S-A-B-C
    @Test
    public void testTopDown() throws Exception {
        Employee employee = new Employee("Luminita","Baicu","2880217123456",DidacticFunction.TEACHER,"10000");
        ctrl.addEmployee(employee);

        ctrl.modifyEmployee("Violeta","Baicu",DidacticFunction.ASISTENT);

        List<Employee> result = ctrl.orderByAge();

        for (int i = 0; i < result.size() - 1; i++) {
            assert (ctrl.compareCNPs(result.get(i), result.get(i + 1)) == result.get(i).getCnp());
            if(result.get(i).getFirstName().equals("Violeta")){
                assert (result.get(i).getFunction().equals(DidacticFunction.ASISTENT));
            }
        }

    }

}