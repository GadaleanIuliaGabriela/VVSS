package giir2105MV.salariati.repository.interfaces;

import java.util.List;

import giir2105MV.salariati.enumeration.DidacticFunction;
import giir2105MV.salariati.model.Employee;

public interface EmployeeRepositoryInterface {

    boolean addEmployee(Employee employee);
    void modifyEmployee(String firstName, String lastName, DidacticFunction function);
    List<Employee> getEmployeeList();
    List<Employee> orderBySalary();
    List<Employee> orderByAge();

}

