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

public class EmployeeTest {
    private EmployeeController ctrl;
    private EmployeeRepository repo;

    @Before
    public void init() throws EmployeeException {

        repo = new EmployeeRepository();
        ctrl = new EmployeeController(repo);
        Employee firstEmployee = new Employee("Raluca","Vlad", "2960312125678", DidacticFunction.LECTURER, "10000");
        Employee secondEmployee = new Employee("Larisa","Ionel", "2930517125678", DidacticFunction.TEACHER, "10000");
        Employee thirdEmployee = new Employee("Marius","Manusa", "1960311125678", DidacticFunction.LECTURER, "10000");
        ctrl.addEmployee(firstEmployee);
        ctrl.addEmployee(secondEmployee);
        ctrl.addEmployee(thirdEmployee);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @After
    public void after(){
        ctrl.deleteAll();
    }

    @Test
    public void testECP1() throws EmployeeException {
        Employee employee = new Employee("iulia","gadalean", "2970327123456", DidacticFunction.TEACHER, "4000");
        ctrl.addEmployee(employee);
    }

    @Test(expected = EmployeeException.class)
    public void testECP2() throws EmployeeException {
        Employee employee = new Employee("iulia","gadalean", "123", DidacticFunction.TEACHER, "4000");
        ctrl.addEmployee(employee);
    }


//    @Test
//    public void testECP3() throws EmployeeException {
//        Employee employee = new Employee("iulia","gadalean",123, DidacticFunction.TEACHER, "100");
//        ctrl.addEmployee(employee);
//    }

    @Test
    public void testBVA1() throws Exception {
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

    @Test(expected=Exception.class)
    public void testBVA3() throws Exception {
        Employee employee = new Employee("iulia","gadalean", "2970327123456", DidacticFunction.TEACHER, "0");
        ctrl.addEmployee(employee);
    }

    @Test
    public void testBVA4() throws Exception {
        int nrEmployees = ctrl.getEmployeesList().size();
        Employee employee = new Employee("iulia", "gadalean","2970327123456", DidacticFunction.TEACHER, "10");
        ctrl.addEmployee(employee);
        assert (ctrl.getEmployeesList().size() == nrEmployees+1);
    }

    //lab3

    @Test(expected = Exception.class)
    public void testModifyEmployee1() throws Exception {
        List<Employee> firstList = ctrl.getEmployeesList();
        ctrl.modifyEmployee(null,"gadalean", DidacticFunction.TEACHER);
        List<Employee> secondList = ctrl.getEmployeesList();

        for (int i = 0; i < firstList.size(); i++){
            assert (firstList.get(i).getFunction() == secondList.get(i).getFunction());
        }
    }


    @Test(expected = Exception.class)
    public void testModifyEmployee2() throws Exception{
        List<Employee> firstList = ctrl.getEmployeesList();
        ctrl.modifyEmployee("a","ana", DidacticFunction.TEACHER);
        List<Employee> secondList = ctrl.getEmployeesList();

        for (int i = 0; i < firstList.size(); i++){
            assert (firstList.get(i).getFunction() == secondList.get(i).getFunction());
        }
    }

    @Test(expected = Exception.class)
    public void testModifyEmployee3() throws Exception{
        List<Employee> firstList = ctrl.getEmployeesList();
        ctrl.modifyEmployee("ana",null, DidacticFunction.TEACHER);
        List<Employee> secondList = ctrl.getEmployeesList();

        for (int i = 0; i < firstList.size(); i++){
            assert (firstList.get(i).getFunction() == secondList.get(i).getFunction());
        }
    }

    @Test(expected = Exception.class)
    public void testModifyEmployee4() throws Exception{
        List<Employee> firstList = ctrl.getEmployeesList();
        ctrl.modifyEmployee("ana","a", DidacticFunction.TEACHER);
        List<Employee> secondList = ctrl.getEmployeesList();

        for (int i = 0; i < firstList.size(); i++){
            assert (firstList.get(i).getFunction() == secondList.get(i).getFunction());
        }
    }

    @Test
    public void testModifyEmployee5() throws Exception{
        List<Employee> firstList = ctrl.getEmployeesList();
        ctrl.deleteAll();
        assert(ctrl.getEmployeesList().size() == 0);
        ctrl.modifyEmployee("iulia","gadalean", DidacticFunction.ASISTENT);
        assert(ctrl.getEmployeesList().size() == 0);

        for(Employee e : firstList){
            try{
                ctrl.addEmployee(e);
            }
            catch(EmployeeException ex){
                System.out.println(ex);
            }
        }
        assert(ctrl.getEmployeesList().size() == 3);
    }

    @Test
    public void testModifyEmployee6() throws Exception{
        List<Employee> firstList = ctrl.getEmployeesList();
        ctrl.modifyEmployee("iulia","gadalean", DidacticFunction.TEACHER);
        List<Employee> secondList = ctrl.getEmployeesList();

        for (int i = 0; i < firstList.size(); i++){
            assert (firstList.get(i).getFunction() == secondList.get(i).getFunction());
        }
    }

    @Test
    public void testModifyEmployee7() throws Exception{
        List<Employee> firstList = ctrl.getEmployeesList();
        ctrl.modifyEmployee("alina","alina", DidacticFunction.TEACHER);
        List<Employee> secondList = ctrl.getEmployeesList();

        for (int i = 0; i < firstList.size(); i++){
            if(secondList.get(i).getLastName().equals("alina"))
                assert (firstList.get(i).getFunction() != secondList.get(i).getFunction());
        }

        ctrl.modifyEmployee("alina","alina", DidacticFunction.ASISTENT);
    }

//    //lab 4
@Test
public void testOrderByAge()throws EmployeeException {
    List<Employee> orderedEmployees = ctrl.orderByAge();
    for (int i = 0; i < orderedEmployees.size()-1; i++){
        assert (ctrl.compareCNPs(orderedEmployees.get(i),orderedEmployees.get(i+1)) == orderedEmployees.get(i).getCnp());
    }
}

    @Test(expected = EmployeeException.class)
    public void testOrderByAge2() throws EmployeeException{
        ctrl.deleteAll();
        List<Employee> result = ctrl.orderByAge();
    }
}
