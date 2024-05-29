import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator {
    private JFrame frame;
    private JTextField[] subjectFields;
    private JButton calculateButton;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public StudentGradeCalculator() {
        // Set up the frame
        frame = new JFrame("Student Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        // Initialize subject fields
        subjectFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            JLabel subjectLabel = new JLabel("Subject " + (i + 1) + ":");
            subjectLabel.setBounds(50, 50 + (i * 40), 100, 25);
            frame.add(subjectLabel);

            subjectFields[i] = new JTextField();
            subjectFields[i].setBounds(150, 50 + (i * 40), 100, 25);
            frame.add(subjectFields[i]);
        }

        // Set up the calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 250, 100, 25);
        frame.add(calculateButton);

        // Set up the labels for displaying results
        totalMarksLabel = new JLabel("Total Marks: ");
        totalMarksLabel.setBounds(50, 290, 300, 25);
        frame.add(totalMarksLabel);

        averagePercentageLabel = new JLabel("Average Percentage: ");
        averagePercentageLabel.setBounds(50, 320, 300, 25);
        frame.add(averagePercentageLabel);

        gradeLabel = new JLabel("Grade: ");
        gradeLabel.setBounds(50, 350, 300, 25);
        frame.add(gradeLabel);

        // Add action listener to the button
        calculateButton.addActionListener(new CalculateButtonListener());

        // Make the frame visible
        frame.setVisible(true);
    }

    // Action listener for the calculate button
    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int totalMarks = 0;
                for (JTextField field : subjectFields) {
                    int marks = Integer.parseInt(field.getText());
                    if (marks < 0 || marks > 100) {
                        JOptionPane.showMessageDialog(frame, "Please enter marks between 0 and 100.");
                        return;
                    }
                    totalMarks += marks;
                }
                int numSubjects = subjectFields.length;
                double averagePercentage = (double) totalMarks / numSubjects;

                String grade;
                if (averagePercentage >= 90) {
                    grade = "A";
                } else if (averagePercentage >= 80) {
                    grade = "B";
                } else if (averagePercentage >= 70) {
                    grade = "C";
                } else if (averagePercentage >= 60) {
                    grade = "D";
                } else {
                    grade = "F";
                }

                totalMarksLabel.setText("Total Marks: " + totalMarks);
                averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
                gradeLabel.setText("Grade: " + grade);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for all subjects.");
            }
        }
    }

    public static void main(String[] args) {
        new StudentGradeCalculator();
    }
}
