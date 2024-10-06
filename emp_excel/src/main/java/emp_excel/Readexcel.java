package emp_excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Employee {
    private int empId;
    private String empName;
    private double empSalary;
    private String dateOfJoining;

    // Constructor, getters, and setters
    public Employee(int empId, String empName, double empSalary, String dateOfJoining) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.dateOfJoining = dateOfJoining;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empSalary=" + empSalary +
                ", dateOfJoining='" + dateOfJoining + '\'' +
                '}';
    }
}

public class Readexcel {
    public static void main(String[] args) {
        String excelFilePath = "employee_data.xls";

        List<Employee> employeeList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new HSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                int empId = (int) row.getCell(0).getNumericCellValue();
                String empName = row.getCell(1).getStringCellValue();
                double empSalary = row.getCell(2).getNumericCellValue();
                String dateOfJoining = row.getCell(3).getStringCellValue();

                Employee employee = new Employee(empId, empName, empSalary, dateOfJoining);
                employeeList.add(employee);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display the employees
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }
}
