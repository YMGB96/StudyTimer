import javax.swing.*;
import java.awt.*;

public class StudyTimerFrame extends JFrame{
    JFrame frame = new JFrame("Study Timer");
    JButton resume = new JButton("Timer starten");
    JButton pause = new JButton("Timer pausieren");
    JLabel phaseName = new JLabel("Study Timer");
    JLabel timeLeft = new JLabel();
    JLabel inputInfo = new JLabel("Bitte die gewünschten Zeiten in Minuten eintragen:");
    JButton createTimers = new JButton("Create Timers");
    JLabel studyPhase1 = new JLabel("1. Lernphase");
    JLabel breakPhase1 = new JLabel("1. Pause");
    JTextField studyDuration1 = new JTextField("45");
    JTextField breakDuration1 = new JTextField("10");
    JLabel studyPhase2 = new JLabel("2. Lernphase");
    JLabel breakPhase2 = new JLabel("2. Pause");
    JTextField studyDuration2 = new JTextField("45");
    JTextField breakDuration2 = new JTextField("20");
    JLabel studyPhase3 = new JLabel("3. Lernphase");
    JButton setTimes = new JButton("Zeiten setzen");
    JTextField studyDuration3 = new JTextField("45");
    public StudyTimerFrame() {


        frame.setSize(400, 350);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        phaseName.setBounds(0,30,400,60);
        phaseName.setFont(new Font("Serif", Font.PLAIN, 25));
        phaseName.setHorizontalAlignment(SwingConstants.CENTER);
        resume.setBounds(125,150,150,20);
        pause.setBounds(125,175,150,20);
        timeLeft.setBounds(0,75,400,60);
        timeLeft.setFont(new Font("Serif", Font.PLAIN, 25));
        timeLeft.setHorizontalAlignment(SwingConstants.CENTER);
        inputInfo.setBounds(0,75,400,60);
        inputInfo.setHorizontalAlignment(SwingConstants.CENTER);
        studyPhase1.setBounds(50,150,75,20);
        studyDuration1.setBounds(125,150,30,20);
        breakPhase1.setBounds(160,150,50,20);
        breakDuration1.setBounds(210,150,30,20);
        studyPhase2.setBounds(50,175,75,20);
        studyDuration2.setBounds(125,175,30,20);
        breakPhase2.setBounds(160,175,50,20);
        breakDuration2.setBounds(210,175,30,20);
        studyPhase3.setBounds(50,200,75,20);
        studyDuration3.setBounds(125,200,30,20);
        setTimes.setBounds(125,230,150,20);

        frame.add(phaseName);
        frame.add(inputInfo);
        frame.add(createTimers);
        frame.add(studyPhase1);
        frame.add(studyDuration1);
        frame.add(breakPhase1);
        frame.add(breakDuration1);
        frame.add(studyPhase2);
        frame.add(studyDuration2);
        frame.add(breakPhase2);
        frame.add(breakDuration2);
        frame.add(studyPhase3);
        frame.add(studyDuration3);
        frame.add(setTimes);

        setTimes.addActionListener(e -> setPhaseTimes());
    }
    public  void setPhaseTimes() {
        try {
            String studyDurationInput1 = studyDuration1.getText();
        double sd1 = Double.parseDouble(studyDurationInput1)* 60;
        String pauseDurationInput1 = breakDuration1.getText();
        double pd1 = Double.parseDouble(pauseDurationInput1)* 60;
        String studyDurationInput2 = studyDuration2.getText();
        double sd2 = Double.parseDouble(studyDurationInput2) * 60;
        String pauseDurationInput2 = breakDuration2.getText();
        double pd2 = Double.parseDouble(pauseDurationInput2) * 60;
        String studyDurationInput3 = studyDuration3.getText();
        double sd3 = Double.parseDouble(studyDurationInput3) * 60;
        frame.remove(studyPhase1);
        frame.remove(studyDuration1);
        frame.remove(breakPhase1);
        frame.remove(breakDuration1);
        frame.remove(studyPhase2);
        frame.remove(studyDuration2);
        frame.remove(breakPhase2);
        frame.remove(breakDuration2);
        frame.remove(studyPhase3);
        frame.remove(studyDuration3);
        frame.remove(setTimes);
        frame.remove(inputInfo);
        frame.revalidate();
        frame.validate();
        frame.repaint();
        frame.add(timeLeft);
        frame.add(resume);
        frame.add(pause);
        pause.setEnabled(false);
        StudyController studyController = new StudyController(timeLeft, phaseName, resume, pause,3,
                                                                sd1, pd1, sd2, pd2, sd3);
        } catch (Exception e){
            inputInfo.setText("Bitte eine ganze Zahl eingeben!");
        }
    }
}
