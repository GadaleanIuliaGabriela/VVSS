package giir2105MV.salariati.controller;

import giir2105MV.salariati.enumeration.DidacticFunction;
import giir2105MV.salariati.model.Employee;
import giir2105MV.salariati.repository.interfaces.EmployeeRepositoryInterface;

import java.util.List;

public class EmployeeController {

    private EmployeeRepositoryInterface employeeRepository;

    public EmployeeController(EmployeeRepositoryInterface employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    public List<Employee> getEmployeesList() {
        return employeeRepository.getEmployeeList();
    }

    public void modifyEmployee(String firstName, String lastName, DidacticFunction function) {
        employeeRepository.modifyEmployee(firstName, lastName, function);
    }

//    public void deleteEmployee(Employee employee) {
//        employeeRepository.deleteEmployee(employee);
//    }

    public List<Employee> orderBySalary() {
        return employeeRepository.orderBySalary();
    }

    public List<Employee> orderByAge() {
        return employeeRepository.orderByAge();
    }

}

