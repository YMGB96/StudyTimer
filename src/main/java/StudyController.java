import javax.swing.*;
import java.awt.*;

class StudyController implements TimerVisualizationUpdater {
    private final JLabel currentPhaseTimeLeft;
    private final JLabel currentPhaseName;
    private final JButton resumeCurrentTimer;
    private final JButton pauseCurrentTimer;
    private final StudyTimer[] studyTimers;
    private int studyTimerCount = 0;
    public StudyController(JLabel currentPhaseTimeLeft, JLabel currentPhaseName, JButton resumeCurrentTimer, JButton pauseCurrentTimer,
                           int timerCount, double studyDuration, double breakDuration) {
        this.currentPhaseTimeLeft = currentPhaseTimeLeft;
        this.currentPhaseName = currentPhaseName;
        this.resumeCurrentTimer = resumeCurrentTimer;
        this.pauseCurrentTimer = pauseCurrentTimer;
        this.studyTimers = new StudyTimer[timerCount];
        for (int index = 0; index < timerCount; index++) {
            studyTimers[index] = new StudyTimer(studyDuration, breakDuration, this);
        }
        resumeCurrentTimer.addActionListener(e -> studyTimers[studyTimerCount].resumeTimer(this.currentPhaseName));
        resumeCurrentTimer.addActionListener(e -> resumeCurrentTimer.setEnabled(false));
        resumeCurrentTimer.addActionListener(e -> pauseCurrentTimer.setEnabled(true));
        pauseCurrentTimer.addActionListener(e -> studyTimers[studyTimerCount].pauseTimer());
        pauseCurrentTimer.addActionListener(e -> resumeCurrentTimer.setEnabled(true));
    }
    public void phaseDone(StudyTimer studyTimer, boolean isStudyPhase) {
        if (!isStudyPhase) {
            currentPhaseName.setText("Pause");
            phaseEndAlert(false);
            studyTimers[(studyTimerCount)].resumeTimer(this.currentPhaseName);
        } else {
            currentPhaseName.setText("Pause beendet");
            phaseEndAlert(true);
            resumeCurrentTimer.setEnabled(true);
            pauseCurrentTimer.setEnabled(false);
            if (studyTimerCount == studyTimers.length -1) {
                currentPhaseName.setText("Lernsession beendet");
                currentPhaseTimeLeft.setText("Glückwunsch!");
                pauseCurrentTimer.setEnabled(false);
                phaseEndAlert(true);
                resumeCurrentTimer.setEnabled(false);
            } else {
                studyTimerCount++;
            }
        }
    }
    public void updatedRemainingTime(double remainingTime) {
        int shownTime = (int) remainingTime;
            shownTime /= 60;
        if (shownTime == 1) {
            currentPhaseTimeLeft.setText("Noch " + shownTime + " Minute");
        } else
            currentPhaseTimeLeft.setText("Noch " + shownTime + " Minuten");
    }
    private void phaseEndAlert(boolean isStudyPhase){
        try {
            Toolkit.getDefaultToolkit().beep();
            if (isStudyPhase) {
                Notification.displayNotification();
            }
        }
        catch (AWTException e) {
            System.out.println("Notification failed");
        }
    }
}