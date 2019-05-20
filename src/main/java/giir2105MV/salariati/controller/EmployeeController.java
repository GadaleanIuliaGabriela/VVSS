package giir2105MV.salariati.controller;

import giir2105MV.salariati.enumeration.DidacticFunction;
import giir2105MV.salariati.exception.EmployeeException;
import giir2105MV.salariati.model.Employee;
import giir2105MV.salariati.repository.interfaces.EmployeeRepositoryInterface;

import java.util.List;

public class EmployeeController {

    private EmployeeRepositoryInterface employeeRepository;

    public EmployeeController(EmployeeRepositoryInterface employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(Employee employee) throws EmployeeException {
        employeeRepository.addEmployee(employee);
    }

    public List<Employee> getEmployeesList() {
        return employeeRepository.getEmployeeList();
    }

    public void modifyEmployee(String firstName, String lastName, DidacticFunction function) throws Exception {
        employeeRepository.modifyEmployee(firstName, lastName, function);
    }

//    public void deleteEmployee(Employee employee) {
//        employeeRepository.deleteEmployee(employee);
//    }

    public List<Employee> orderBySalary() {
        return employeeRepository.orderBySalary();
    }

    public List<Employee> orderByAge() throws EmployeeException{
        return employeeRepository.orderByAge();
    }

    public void deleteAll() {
        employeeRepository.deleteAllFromFile();
    }

    public String compareCNPs(Employee e1,Employee e2){
        return employeeRepository.compareCNPs(e1.getCnp(),e2.getCnp());
    }

}

