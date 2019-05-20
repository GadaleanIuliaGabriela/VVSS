package giir2105MV.salariati.repository.interfaces;

import java.util.List;

import giir2105MV.salariati.enumeration.DidacticFunction;
import giir2105MV.salariati.exception.EmployeeException;
import giir2105MV.salariati.model.Employee;

public interface EmployeeRepositoryInterface {

    boolean addEmployee(Employee employee)throws EmployeeException;
    void modifyEmployee(String firstName, String lastName, DidacticFunction function) throws Exception;
    List<Employee> getEmployeeList();
    List<Employee> orderBySalary();
    List<Employee> orderByAge() throws EmployeeException;
    void deleteAllFromFile();
    String compareCNPs(String cnp1, String cnp2);
}

