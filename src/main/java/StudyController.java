import javax.swing.*;
import java.awt.*;

class StudyController implements StudyControllerMethods {

    private JLabel timeLeft;
    private JLabel phaseName;
    private JButton resume;
    private JButton pause;
    private StudyTimer[] studyTimers;
    private int studySessionCounter = 0;


    public StudyController(JLabel timeLeft, JLabel phaseName, JButton resume, JButton pause,
                           int timerCount, double studyDuration, double breakDuration) {
        this.timeLeft = timeLeft;
        this.phaseName = phaseName;
        this.resume = resume;
        this.pause = pause;
        this.studyTimers = new StudyTimer[timerCount];
        for (int index = 0; index < timerCount; index++) {
            studyTimers[index] = new StudyTimer(studyDuration, breakDuration, this);
        }
        resume.addActionListener(e -> studyTimers[studySessionCounter].resumeTimer());
        this.resume.addActionListener(e -> this.phaseName.setText("Lernphase"));
        this.resume.addActionListener(e -> resume.setEnabled(false));
        this.resume.addActionListener(e -> pause.setEnabled(true));
        pause.addActionListener(e -> studyTimers[studySessionCounter].pauseTimer());
        pause.addActionListener(e -> resume.setEnabled(true));
    }


    public void phaseDone(StudyTimer studyTimer, boolean isStudyPhase) {
        if (isStudyPhase == false) {
            this.phaseName.setText("Pause");
            phaseEndAlert(isStudyPhase);
            studyTimers[(studySessionCounter)].resumeTimer();
        } else {
            this.timeLeft.setText("Pause beendet");
            phaseEndAlert(isStudyPhase);
            resume.setEnabled(true);
            pause.setEnabled(false);
            if (studySessionCounter == studyTimers.length -1) {
                this.phaseName.setText("Lernsession beendet");
                this.timeLeft.setText("Glückwunsch!");
                this.pause.setEnabled(false);
                phaseEndAlert(isStudyPhase);
                this.resume.setEnabled(false);
            } else {
                studySessionCounter++;
            }
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

    public void phaseEndAlert(boolean isStudyPhase){
        try {
            Toolkit.getDefaultToolkit().beep();
            if (isStudyPhase == true) {
                Notification.displayNotification();
            }
        }
        catch (AWTException e) {
            System.out.println("Notification failed");
        }
    }
}
