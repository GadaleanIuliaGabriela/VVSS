package giir2105MV.salariati.repository.implementations;

import giir2105MV.salariati.enumeration.DidacticFunction;
import giir2105MV.salariati.exception.EmployeeException;
import giir2105MV.salariati.model.Employee;
import giir2105MV.salariati.repository.interfaces.EmployeeRepositoryInterface;
import giir2105MV.salariati.validator.EmployeeValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class EmployeeRepository implements EmployeeRepositoryInterface {

    private final String employeeFile = "D:\\VVSS\\Labs\\Lab01\\src\\main\\java\\giir2105MV\\salariati\\utils\\employees.txt";
    private EmployeeValidator employeeValidator = new EmployeeValidator();

    @Override
    public boolean addEmployee(Employee employee) throws EmployeeException {
        if( employeeValidator.isValid(employee) ) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(employeeFile, true));
                bw.write(employee.toString());
                bw.newLine();
                bw.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else throw new EmployeeException("Invalid");
        return false;
    }

//    @Override
//    public void deleteEmployee(Employee employee) {
//        // TODO Auto-generated method stub
//
//    }

    @Override
    public void modifyEmployee(String firstName, String lastName, DidacticFunction function) throws Exception {
        List<Employee> employees = getEmployeeList(); //1

        if(firstName == null) //2
            throw new Exception("FirstName can't be null!"); //3
        if(firstName.length() < 2){ //4
            throw new Exception("FirstName can't have only one character!"); //5
        }

        if(lastName == null) //6
            throw new Exception("LastName can't be null!"); //7
        if(lastName.length() < 2){ //8
            throw new Exception("LastName can't have only one character!"); //9
        }

        int i=0; //10
        while(i<employees.size()){ //11
            String name = employees.get(i).getLastName(); //12
            if(lastName.equals(name)){ //13
                employees.get(i).setFunction(function); //14
                break; //15
            }
            i++; //16
        }
        deleteAllFromFile(); //17
        writeEmployeeList(employees); //18
    }

    public void writeEmployeeList(List<Employee> employees) {


        BufferedWriter bw = null;
        String employee = "";
        try {
            bw = new BufferedWriter(new FileWriter(employeeFile, true));
            for (Employee e : employees) {
                bw.write(e.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error while reading: " + e);
        } finally {
            if (bw != null)
                try {
                    bw.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the file: " + e);
                }
        }

    }

    public void deleteAllFromFile() {
        try {
            PrintWriter writer = new PrintWriter(employeeFile);
            writer.print("");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<Employee>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(employeeFile));
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                Employee employee = new Employee();
                try {
                    employee = (Employee) Employee.getEmployeeFromString(line, counter);
                    employeeList.add(employee);
                } catch (EmployeeException ex) {
                    System.err.println("Error while reading: " + ex.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error while reading: " + e);
        } catch (IOException e) {
            System.err.println("Error while reading: " + e);
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the file: " + e);
                }
        }

        return employeeList;
    }


//    @Override
//    public List<Employee> getEmployeeByCriteria(String criteria) {
//        List<Employee> employeeList = new ArrayList<Employee>();
//
//        return employeeList;
//    }

    @Override
    public List<Employee> orderBySalary() {
        List<Employee> employees = getEmployeeList();
        for (int i = 0; i < employees.size() - 1; i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                if (Integer.parseInt(employees.get(i).getSalary()) < Integer.parseInt(employees.get(j).getSalary())) {
                    Employee aux = employees.get(i);
                    employees.set(i, employees.get(j));
                    employees.set(j, aux);
                }
            }
        }
        return employees;
    }

    public String compareCNPs(String cnp1, String cnp2) {
        String cnp1Year = cnp1.substring(1, 3);
        String cnp2Year = cnp2.substring(1, 3);

        String cnp1Month = cnp1.substring(3, 5);
        String cnp2Month = cnp2.substring(3, 5);

        String cnp1Day = cnp1.substring(5, 7);
        String cnp2Day = cnp2.substring(5, 7);

        //YEAR
        if (Integer.parseInt(cnp1Year) > Integer.parseInt(cnp2Year)) return cnp1;
        if (Integer.parseInt(cnp1Year) < Integer.parseInt(cnp2Year)) return cnp2;

        //Month
        if (cnp1Month.charAt(0) == '0') cnp1Month = cnp1Month.substring(1, 2);
        if (cnp2Month.charAt(0) == '0') cnp2Month = cnp2Month.substring(1, 2);

        if (Integer.parseInt(cnp1Month) > Integer.parseInt(cnp2Month)) return cnp1;
        if (Integer.parseInt(cnp1Month) < Integer.parseInt(cnp2Month)) return cnp2;

        //DAY
        if (cnp1Day.charAt(0) == '0') cnp1Day = cnp1Day.substring(1, 2);
        if (cnp2Day.charAt(0) == '0') cnp2Day = cnp2Day.substring(1, 2);

        if (Integer.parseInt(cnp1Day) > Integer.parseInt(cnp2Day)) return cnp1;
        if (Integer.parseInt(cnp1Day) < Integer.parseInt(cnp2Day)) return cnp2;

        return null;
    }


    @Override
    public List<Employee> orderByAge() throws EmployeeException{
        List<Employee> employees = getEmployeeList();

        if(employees.size() == 0) throw new EmployeeException("Can`t order an empty list!!");

        for (int i = 0; i < employees.size() - 1; i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                String cnp1 = employees.get(i).getCnp();
                String cnp2 = employees.get(j).getCnp();
                if (Objects.equals(compareCNPs(cnp1, cnp2), cnp2)) {
                    Employee aux = employees.get(i);
                    employees.set(i, employees.get(j));
                    employees.set(j, aux);
                }
            }
        }
        return employees;
    }

}

