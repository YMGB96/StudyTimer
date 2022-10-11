import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudyTimerFrame extends JFrame{
    JFrame frame = new JFrame("Study Timer");
    JButton resume = new JButton("Timer starten");
    JButton pause = new JButton("Timer pausieren");
    JLabel phaseName = new JLabel("Study Timer");
    JLabel timeLeft = new JLabel();
    JLabel inputInfo = new JLabel("Bitte die gewünschten Zeiten in Minuten eintragen:");
    JLabel studyPhase = new JLabel("Lernphasendauer:");
    JLabel breakPhase = new JLabel("Pausendauer:");
    JLabel timerAmountPrompt = new JLabel("Anzahl der Lernphasen:");
    JTextField studyDuration = new JTextField("45");
    JTextField breakDuration = new JTextField("10");
    JTextField timerAmount = new JTextField("3");
    JButton setTimes = new JButton("Zeiten setzen");
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
        studyPhase.setBounds(50,150,120,20);
        studyDuration.setBounds(160,150,30,20);
        breakPhase.setBounds(200,150,120,20);
        breakDuration.setBounds(285,150,30,20);
        timerAmountPrompt.setBounds(50,180,140,20);
        timerAmount.setBounds(190,180,30,20);
        setTimes.setBounds(125,230,150,20);

        frame.add(phaseName);
        frame.add(inputInfo);
        frame.add(studyPhase);
        frame.add(studyDuration);
        frame.add(breakPhase);
        frame.add(breakDuration);
        frame.add(timerAmount);
        frame.add(timerAmountPrompt);
        frame.add(setTimes);

        studyDuration.addKeyListener((new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        }));
        breakDuration.addKeyListener((new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        }));
        timerAmount.addKeyListener((new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        }));
        setTimes.addActionListener(e -> setPhaseTimes());
    }
    public  void setPhaseTimes() {

        String studyDurationInput = studyDuration.getText();
        double studyduration = Double.parseDouble(studyDurationInput)* 60;
        String pauseDurationInput = breakDuration.getText();
        double breakduration = Double.parseDouble(pauseDurationInput)* 60;
        String timerAmountInput = timerAmount.getText();
        int timeramount = Integer.parseInt(timerAmountInput);
        frame.remove(studyPhase);
        frame.remove(studyDuration);
        frame.remove(breakPhase);
        frame.remove(breakDuration);
        frame.remove(timerAmount);
        frame.remove(timerAmountPrompt);
        frame.remove(setTimes);
        frame.remove(inputInfo);
        frame.revalidate();
        frame.validate();
        frame.repaint();
        frame.add(timeLeft);
        frame.add(resume);
        frame.add(pause);
        pause.setEnabled(false);

        StudyController studyController = new StudyController(timeLeft, phaseName, resume, pause, timeramount, studyduration, breakduration);
    }
}
