package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;
	
	private LocalDate dateJoined;

    private boolean isForeigner;
    private boolean isMale;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

	private List<Child> children;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate dateJoined, boolean isForeigner, boolean isMale) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.dateJoined = dateJoined;
        this.isForeigner = isForeigner;
        this.isMale = isMale;

        children = new ArrayList<>();
    }

	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {
        switch (grade) {
            case 1:
                monthlySalary = 3000000;
                break;
            case 2:
                monthlySalary = 5000000;
                break;
            case 3:
                monthlySalary = 7000000;
                break;
            default:
                throw new IllegalArgumentException("Invalid grade");
        }

        if (isForeigner) {
            monthlySalary = (int) (monthlySalary * 1.5);
        }
    }
	
	 public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        children.add(new Child(childName, childIdNumber));
    }

    public int getAnnualIncomeTax() {
        int monthsWorkedInYear = calculateMonthsWorkedInYear();

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorkedInYear, annualDeductible, spouseIdNumber == null, children.size());
    }

    private int calculateMonthsWorkedInYear() {
        LocalDate currentDate = LocalDate.now();
        int yearJoined = dateJoined.getYear();
        int monthJoined = dateJoined.getMonthValue();

        if (currentDate.getYear() == yearJoined) {
            return currentDate.getMonthValue() - monthJoined + 1;
        } else {
            return 12;
        }
    }

    private static class Child {
        private String name;
        private String idNumber;

        public Child(String name, String idNumber) {
            this.name = name;
            this.idNumber = idNumber;
        }
    }
}