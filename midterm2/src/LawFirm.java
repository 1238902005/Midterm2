import java.io.*;
import java.util.*;

public class LawFirm {
    private String name;
    private List<Lawyer> lawyers;


    public LawFirm(String name) {
        this.name = name;
        this.lawyers = new ArrayList<>();
    }

    public void addLawyer(Lawyer lawyer) {
        lawyers.add(lawyer);
    }


    public void removeLawyer(Lawyer lawyer) {
        lawyers.remove(lawyer);
    }


    public void saveState() {
        try (PrintWriter writer = new PrintWriter(new File("state.csv"))) {
            for (Lawyer lawyer : lawyers) {
                writer.println(lawyer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void restoreState() {
        lawyers.clear();
        try (Scanner scanner = new Scanner(new File("state.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    lawyers.add(new Lawyer(parts[0], parts[1]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to display options
    public void displayOptions() {
        System.out.println("1. View Lawyers");
        System.out.println("2. Add Lawyer");
        System.out.println("3. Remove Lawyer");
        System.out.println("4. Save State");
        System.out.println("5. Restore State");
        System.out.println("6. Exit");
    }


    public void displayLawyers() {
        System.out.println("Lawyers:");
        for (Lawyer lawyer : lawyers) {
            System.out.println("- " + lawyer);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LawFirm myFirm = new LawFirm("Doe & Associates");

        int choice;
        do {
            myFirm.displayOptions();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    myFirm.displayLawyers();
                    break;
                case 2:
                    System.out.print("Enter lawyer's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter lawyer's specialization: ");
                    String specialization = scanner.nextLine();
                    myFirm.addLawyer(new Lawyer(name, specialization));
                    System.out.println("Lawyer added successfully.");
                    break;
                case 3:
                    System.out.print("Enter lawyer's name to remove: ");
                    String removeName = scanner.nextLine();
                    Lawyer removeLawyer = null;
                    for (Lawyer lawyer : myFirm.lawyers) {
                        if (lawyer.getName().equals(removeName)) {
                            removeLawyer = lawyer;
                            break;
                        }
                    }
                    if (removeLawyer != null) {
                        myFirm.removeLawyer(removeLawyer);
                        System.out.println("Lawyer removed successfully.");
                    } else {
                        System.out.println("Lawyer not found.");
                    }
                    break;
                case 4:
                    myFirm.saveState();
                    System.out.println("State saved successfully.");
                    break;
                case 5:
                    myFirm.restoreState();
                    System.out.println("State restored successfully.");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}


