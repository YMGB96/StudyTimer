import javax.swing.*;
import java.awt.*;

class StudyController implements StudyControllerInterface {

    private JLabel timeLeft;
    private JLabel phaseName;
    private JButton resume;
    private JButton pause;
    private StudyTimer[] studyTimers;
    private int studySessionCounter = 0;


    public StudyController(JLabel timeLeft, JLabel phaseName, JButton resume, JButton pause,
                           int timerCount, double studyDuration1, double breakDuration1,
                           double studyDuration2, double breakDuration2, double studyDuration3) {
        this.timeLeft = timeLeft;
        this.phaseName = phaseName;
        this.resume = resume;
        this.pause = pause;
        this.studyTimers = new StudyTimer[timerCount];
        for (int index = 0; index < timerCount; index++) {
            studyTimers[index] = new StudyTimer(6.0, 4.0, this);
        }
        studyTimers[0].setStudyDuration(studyDuration1);
        studyTimers[0].setBreakDuration(breakDuration1);
        studyTimers[1].setStudyDuration(studyDuration2);
        studyTimers[1].setBreakDuration(breakDuration2);
        studyTimers[2].setStudyDuration(studyDuration3);
        studyTimers[timerCount -1].setBreakDuration(0.0);
        resume.addActionListener(e -> studyTimers[studySessionCounter].resumeTimer());
        this.resume.addActionListener(e -> this.phaseName.setText("Lernphase"));
        this.resume.addActionListener(e -> resume.setEnabled(false));
        this.resume.addActionListener(e -> pause.setEnabled(true));
        pause.addActionListener(e -> studyTimers[studySessionCounter].pauseTimer());
        pause.addActionListener(e -> resume.setEnabled(true));
    }


    public void phaseDone(StudyTimer studyTimer, boolean isStudyPhase) throws AWTException {
        if (isStudyPhase == false) {
            if (studyTimers[studySessionCounter].getBreakDuration() == 0.0) {
                this.phaseName.setText("Lernsession beendet");
                this.timeLeft.setText("Glückwunsch!");
                this.pause.setEnabled(false);
                Toolkit.getDefaultToolkit().beep();
                this.resume.setEnabled(false);
            } else {
                this.phaseName.setText("Pause");
                Toolkit.getDefaultToolkit().beep();
                studyTimers[(studySessionCounter)].resumeTimer();
            }
        } else {
            this.timeLeft.setText("Pause beendet");
            Toolkit.getDefaultToolkit().beep();
            Notification breakEndNotification = new Notification();
            breakEndNotification.displayNotification();
            resume.setEnabled(true);
            pause.setEnabled(false);
            studySessionCounter++;
        }
    }

    public void updatedRemainingTime(double remainingTime) {
        int shownTime = (int) remainingTime;
            shownTime /= 60;
        if (shownTime == 1) {
            this.timeLeft.setText("Noch " + shownTime + " Minute");
        } else
            this.timeLeft.setText("Noch " + shownTime + " Minuten");
    }
}