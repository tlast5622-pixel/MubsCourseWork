// this is a source code  for a marks entry management system in java with eclipse compiled by ..... 
// 
//Babirye Catherine 2500714971
//Mabberi mollivia 2500715211
//Namuyomba Dorcus 2500715499
//Nabukenya Annet 2500715353




import java.util.Scanner;

public class StudentGradeManager {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Student Grade Manager ===");

        // ask how many students we're entering
        System.out.print("Enter number of students: ");
        while (!input.hasNextInt()) {
            System.out.print("That's not a number, try again: ");
            input.next();
        }
        int studentCount = input.nextInt();

        // WHILE LOOP - make sure they didn't enter 0 or a negative number
        while (studentCount <= 0) {
            System.out.print("Has to be more than 0: ");
            while (!input.hasNextInt()) {
                System.out.print("That's not a number, try again: ");
                input.next();
            }
            studentCount = input.nextInt();
        }
        input.nextLine(); // gets rid of the leftover enter from nextInt

        String[] names = new String[studentCount];
        int[] scores = new int[studentCount];
        String[] grades = new String[studentCount];
        boolean[] passed = new boolean[studentCount];

        // FOR LOOP - go through and get each student's info
        for (int i = 0; i < studentCount; i++) {
            System.out.println("\nStudent " + (i + 1));
            System.out.print("Name: ");
            names[i] = input.nextLine();

            System.out.print("Score (0-100): ");
            while (!input.hasNextInt()) {
                System.out.print("Needs to be a number 0-100: ");
                input.next();
            }
            int score = input.nextInt();

            // WHILE LOOP - keep asking until score is actually in range
            while (score < 0 || score > 100) {
                System.out.print("Needs to be between 0 and 100: ");
                while (!input.hasNextInt()) {
                    System.out.print("Needs to be a number 0-100: ");
                    input.next();
                }
                score = input.nextInt();
            }
            input.nextLine(); // clear buffer again so nextLine works for the next name

            scores[i] = score;

            // TERNARY - pass if 50 or above
            passed[i] = score >= 50 ? true : false;

            // TERNARY - figuring out the letter grade
            grades[i] = score >= 80 ? "A" :
                        score >= 70 ? "B" :
                        score >= 60 ? "C" :
                        score >= 50 ? "D" : "F";
        }

        char again;
        // DO-WHILE LOOP - show the report, ask if they want to see it again
        do {
            int total = 0;
            int passedCount = 0;

            System.out.println("\n=== REPORT ===");
            for (int i = 0; i < studentCount; i++) {
                String status = passed[i] ? "PASS" : "FAIL";
                System.out.println("Name: " + names[i]);
                System.out.println("Score: " + scores[i]);
                System.out.println("Grade: " + grades[i]);
                System.out.println("Status: " + status);
                System.out.println();

                total = total + scores[i];
                if (passed[i]) {
                    passedCount++;
                }
            }

            int failedCount = studentCount - passedCount;
            double average = (double) total / studentCount;

            // TERNARY - just a rough label for how the class did overall
            String performance = average >= 75 ? "Excellent" :
                                  average >= 60 ? "Good" :
                                  average >= 50 ? "Fair" : "Needs Improvement";

            System.out.println("Total score: " + total);
            System.out.println("Passed students: " + passedCount);
            System.out.println("Failed students: " + failedCount);
            System.out.printf("Average score: %.2f%n", average);
            System.out.println("Class performance: " + performance);

            System.out.print("Show report again? (y/n): ");
            again = input.next().charAt(0);
        } while (again == 'y' || again == 'Y');

        input.close();
    }
}
