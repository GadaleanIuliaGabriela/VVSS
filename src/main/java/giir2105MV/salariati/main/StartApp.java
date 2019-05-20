package giir2105MV.salariati.main;

//functionalitati
//F01.	 adaugarea unui nou angajat (nume, prenume, CNP, functia didactica, salariul de incadrare);
//F02.	 modificarea functiei didactice (asistent/lector/conferentiar/profesor) a unui angajat;
//F03.	 afisarea salariatilor ordonati descrescator dupa salariu si crescator dupa varsta (CNP).

import giir2105MV.salariati.controller.EmployeeController;
import giir2105MV.salariati.enumeration.DidacticFunction;
import giir2105MV.salariati.exception.EmployeeException;
import giir2105MV.salariati.model.Employee;
import giir2105MV.salariati.repository.implementations.EmployeeRepository;
import giir2105MV.salariati.repository.interfaces.EmployeeRepositoryInterface;

import java.util.List;
import java.util.Scanner;

public class StartApp {

    static private EmployeeRepositoryInterface employeesRepository = new EmployeeRepository();
    static private EmployeeController employeeController = new EmployeeController(employeesRepository);

    public static void main(String[] args) {
        menu();
    }

    private static void printMenu() {
        System.out.println("-----------------------------------------");
        System.out.println("1. Add a new employee.");
        System.out.println("2. Modify a employee position.");
        System.out.println("3. Order Employees by salary (descending) ");
        System.out.println("4. Order Employees by age (ascendinf) ");
        System.out.println("5. Show employees.");
        System.out.println("6. Order Employees by age and salary");
        System.out.println("7. Exit.");
        System.out.println("Enter command: ");
    }

    private static void menu(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            switch (scanner.nextInt()) {
                case 1: {
                    try {
                        addEmployee();
                    } catch (EmployeeException employeeExceptions) {
                        employeeExceptions.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    try {
                        modifyEmployee();
                    }
                    catch(Exception e){
                        e.getMessage();
                    }
                    break;
                }
                case 3: {
                    orderBySalary();
                    break;
                }
                case 4: {
                    try {
                        orderByAge();
                    } catch (EmployeeException employeeExceptions) {
                        employeeExceptions.printStackTrace();
                    }
                    break;
                }
                case 5: {
                    getAllEmployees();
                    break;
                }
                case 6:
                    return;
                default: {
                    System.out.println("Invalid command!");
                    printMenu();
                }
            }
        }
    }

    private static void addEmployee() throws EmployeeException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("First name: ");
        String firstName = scanner.next();
        System.out.println("Last name: ");
        String lastName = scanner.next();
        System.out.println("CNP: ");
        String cnp = scanner.next();
        System.out.println("Position: ");
        String function = scanner.next();
        System.out.println("Salary: ");
        String salary = scanner.next();


        Employee employee = new Employee(firstName, lastName, cnp, null, salary);

        DidacticFunction didacticFunction = checkFunction(function);
        if (didacticFunction == null) {
            System.out.println("Invalid input!!");
            return;
        }
        employee.setFunction(didacticFunction);
        employeeController.addEmployee(employee);
        System.out.println("Success!");
    }

    private static DidacticFunction checkFunction(String function) {
        if (function.equals("ASISTENT"))
            return DidacticFunction.ASISTENT;
        if (function.equals("LECTURER"))
            return DidacticFunction.LECTURER;
        if (function.equals("TEACHER"))
            return DidacticFunction.TEACHER;
        return null;
    }

    private static void modifyEmployee() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("First Name: ");
        String firstName = scanner.next();
        System.out.println("Last Name: ");
        String lastName = scanner.next();
        System.out.println("New function: ");
        String function = scanner.next();
        DidacticFunction didacticFunction = checkFunction(function);

        if (didacticFunction == null) {
            System.out.println("Invalid input!!");
            return;
        }
        employeeController.modifyEmployee(firstName, lastName, didacticFunction);
        System.out.println("Success!");
    }

    private static void getAllEmployees() {
        List<Employee> employees = employeeController.getEmployeesList();
        for (Employee e : employees) {
            System.out.println(e.toString());
        }
    }

    private static void orderBySalary() {
        List<Employee> employees = employeeController.orderBySalary();
        for (Employee e : employees) {
            System.out.println(e.toString());
        }

    }

    private static void orderByAge() throws EmployeeException{
        List<Employee> employees = employeeController.orderByAge();
        for (Employee e : employees) {
            System.out.println(e.toString());
        }

    }
}

