import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudyTimerFrame extends JFrame{
    JFrame frame = new JFrame("Study Timer");
    JButton resumeCurrentTimer = new JButton("Timer starten");
    JButton pauseCurrentTimer = new JButton("Timer pausieren");
    JLabel currentPhaseName = new JLabel("Study Timer");
    JLabel currentPhaseTimeLeft = new JLabel();
    JLabel inputInformation = new JLabel("Bitte die gewünschten Zeiten in Minuten eintragen:");
    JLabel studyDurationPrompt = new JLabel("Lernphasendauer:");
    JLabel breakDurationPrompt = new JLabel("Pausendauer:");
    JLabel timerAmountPrompt = new JLabel("Anzahl der Lernphasen:");
    JTextField studyDuration = new JTextField("45");
    JTextField breakDuration = new JTextField("10");
    JTextField timerAmount = new JTextField("3");
    JButton createTimerArray = new JButton("Zeiten setzen");
    public StudyTimerFrame() {
        frame.setSize(400, 350);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        currentPhaseName.setBounds(0,30,400,60);
        currentPhaseName.setFont(new Font("Serif", Font.PLAIN, 25));
        currentPhaseName.setHorizontalAlignment(SwingConstants.CENTER);
        resumeCurrentTimer.setBounds(125,150,150,20);
        pauseCurrentTimer.setBounds(125,175,150,20);
        currentPhaseTimeLeft.setBounds(0,75,400,60);
        currentPhaseTimeLeft.setFont(new Font("Serif", Font.PLAIN, 25));
        currentPhaseTimeLeft.setHorizontalAlignment(SwingConstants.CENTER);
        inputInformation.setBounds(0,75,400,60);
        inputInformation.setHorizontalAlignment(SwingConstants.CENTER);
        studyDurationPrompt.setBounds(50,150,120,20);
        studyDuration.setBounds(160,150,30,20);
        breakDurationPrompt.setBounds(200,150,120,20);
        breakDuration.setBounds(285,150,30,20);
        timerAmountPrompt.setBounds(50,180,140,20);
        timerAmount.setBounds(210,180,30,20);
        createTimerArray.setBounds(125,230,150,20);

        frame.add(currentPhaseName);
        frame.add(inputInformation);
        frame.add(studyDurationPrompt);
        frame.add(studyDuration);
        frame.add(breakDurationPrompt);
        frame.add(breakDuration);
        frame.add(timerAmount);
        frame.add(timerAmountPrompt);
        frame.add(createTimerArray);

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
        createTimerArray.addActionListener(e -> setPhaseTimes());
    }
    public void setPhaseTimes() {

        String studyDurationInput = studyDuration.getText();
        double studyduration = Double.parseDouble(studyDurationInput);//* 60;
        String pauseDurationInput = breakDuration.getText();
        double breakduration = Double.parseDouble(pauseDurationInput);//* 60;
        String timerAmountInput = timerAmount.getText();
        int timeramount = Integer.parseInt(timerAmountInput);
        frame.remove(studyDurationPrompt);
        frame.remove(studyDuration);
        frame.remove(breakDurationPrompt);
        frame.remove(breakDuration);
        frame.remove(timerAmount);
        frame.remove(timerAmountPrompt);
        frame.remove(createTimerArray);
        frame.remove(inputInformation);
        frame.repaint();
        frame.add(currentPhaseTimeLeft);
        frame.add(resumeCurrentTimer);
        frame.add(pauseCurrentTimer);
        pauseCurrentTimer.setEnabled(false);
        StudyController studyController = new StudyController(currentPhaseTimeLeft, currentPhaseName,
                resumeCurrentTimer, pauseCurrentTimer, timeramount, studyduration, breakduration);
    }
}
