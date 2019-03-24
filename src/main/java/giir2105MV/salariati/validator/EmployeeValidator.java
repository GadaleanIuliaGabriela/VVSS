package giir2105MV.salariati.validator;

import giir2105MV.salariati.enumeration.DidacticFunction;
import giir2105MV.salariati.model.Employee;

public class EmployeeValidator {

    public EmployeeValidator() {
    }

    public boolean isValid(Employee employee) {
        boolean isFirstNameValid = employee.getFirstName().matches("[a-zA-Z]+") && (employee.getFirstName().length() > 2);
        boolean isLastNameValid = employee.getLastName().matches("[a-zA-Z]+") && (employee.getLastName().length() > 2);
        boolean isCNPValid = employee.getCnp().matches("[0-9]+") && (employee.getCnp().length() == 13);
        boolean isFunctionValid = employee.getFunction().equals(DidacticFunction.ASISTENT) ||
                employee.getFunction().equals(DidacticFunction.LECTURER) ||
                employee.getFunction().equals(DidacticFunction.TEACHER);
        boolean isSalaryValid = employee.getSalary().matches("[0-9]+") && (employee.getSalary().length() > 1) && (Integer.parseInt(employee.getSalary()) > 0);

        return isFirstNameValid && isLastNameValid && isCNPValid && isFunctionValid && isSalaryValid;
    }


}

