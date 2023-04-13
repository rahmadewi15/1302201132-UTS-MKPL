package lib;

public class TaxFunction {

	private static final int MAX_CHILDREN = 3;
	private static final int MAX_MONTHS_WORKED = 12;
	private static final double TAX_RATE = 0.05;
	private static final int MARRIED_DEDUCTIBLE = 58500000;
	private static final int CHILD_DEDUCTIBLE = 1500000;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked, int deductible, boolean isMarried, int numberOfChildren) {

		if (numberOfMonthsWorked > MAX_MONTHS_WORKED) {
			throw new IllegalArgumentException("Number of months worked cannot exceed " + MAX_MONTHS_WORKED);
		}

		if (numberOfChildren > MAX_CHILDREN) {
			numberOfChildren = MAX_CHILDREN;
		}

		int annualSalary = (monthlySalary + otherMonthlyIncome) * numberOfMonthsWorked;
		int deductibleAmount = deductible;

		if (isMarried) {
			deductibleAmount += MARRIED_DEDUCTIBLE;
		}

		deductibleAmount += numberOfChildren * CHILD_DEDUCTIBLE;

		int taxableIncome = annualSalary - deductibleAmount;

		if (taxableIncome < 0) {
			return 0;
		}

		return (int) Math.round(taxableIncome * TAX_RATE);
	}
}
