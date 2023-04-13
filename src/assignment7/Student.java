package assignment7;

public class Student {
	private String firstName;
	private String lastName;
	private int idNumber;
	private int attemptedCredits;
	private int passingCredits;
	private double totalGradeQualityPoints;
	private double bearBucksBalance;

	// Constructor
	public Student(String firstName, String lastName, int idNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
	}

	// Accessor methods
	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public int getId() {
		return this.idNumber;
	}

	public int getTotalAttemptedCredits() {
		return this.attemptedCredits;
	}

	public int getTotalPassingCredits() {
		return this.passingCredits;
	}

	public double getTotalGradeQualityPoints() {
		return this.totalGradeQualityPoints;
	}

	public double getBearBucksBalance() {
		return this.bearBucksBalance;
	}

	// Mutator methods
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public void setAttemptedCredits(int attemptedCredits) {
		this.attemptedCredits = attemptedCredits;
	}

	public void setPassingCredits(int passingCredits) {
		this.passingCredits = passingCredits;
	}

	public void setTotalGradeQualityPoints(double totalGradeQualityPoints) {
		this.totalGradeQualityPoints = totalGradeQualityPoints;
	}

	public void setBearBucksBalance(double bearBucksBalance) {
		this.bearBucksBalance = bearBucksBalance;
	}

	// Method to get full name
	public String getFullName() {
		return firstName + " " + lastName;
	}

	// Method to submit a grade
	public void submitGrade(double grade, int credits) {
		attemptedCredits += credits;
		totalGradeQualityPoints += grade * credits;

		if (grade >= 1.7) {
			passingCredits += credits;
		}
	}

	// Method to calculate GPA
	public double calculateGradePointAverage() {
		return totalGradeQualityPoints / attemptedCredits;
	}

	// Method to get class standing
	public String getClassStanding() {
		if (passingCredits < 30) {
			return "First Year";
		} else if (passingCredits < 60) {
			return "Sophomore";
		} else if (passingCredits < 90) {
			return "Junior";
		} else {
			return "Senior";
		}
	}

	// Method to check if eligible for Phi Beta Kappa
	public boolean isEligibleForPhiBetaKappa() {
		return (attemptedCredits >= 98 && calculateGradePointAverage() >= 3.60)
				|| (attemptedCredits >= 75 && calculateGradePointAverage() >= 3.80);
	}

	// Method to deposit Bear Bucks
	public void depositBearBucks(double amount) {
		bearBucksBalance += amount;
	}

	// Method to deduct Bear Bucks
	public void deductBearBucks(double amount) {
		bearBucksBalance -= amount;
	}

	// Method to cash out Bear Bucks
	public double cashOutBearBucks() {
		double refund;
		if (bearBucksBalance <= 10) {
			refund = 0;
		} else {
			refund = bearBucksBalance - 10;
		}
		bearBucksBalance = 0;
		return refund;
	}

	// Method to create a legacy student
	public Student createLegacy(String firstName, Student otherParent, boolean isHyphenated, int id) {
		// Determine the last name for the legacy student
		String lastName;
		if (isHyphenated) {
			lastName = this.lastName + "-" + otherParent.getLastName();
		} else {
			lastName = this.lastName;
		}

		// Create the legacy student
		Student legacy = new Student(firstName, lastName, id);

		// Transfer Bear Bucks from both parents to the legacy student
		double transferAmount = this.cashOutBearBucks() + otherParent.cashOutBearBucks();
		legacy.depositBearBucks(transferAmount);

		return legacy;
	}

	// Method to return a string representation of the student
	public String toString() {
		return getFullName() + " " + idNumber;
	}
}
