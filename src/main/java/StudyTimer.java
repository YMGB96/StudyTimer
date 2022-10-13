import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class StudyTimer {
    private final double studyDuration;
    private final double breakDuration;
    private double remainingTimerDuration;
    private final TimerVisualizationUpdater studyController;
    private Timer countdownTimer = new Timer();
    private boolean isStudyPhase = true;

    public StudyTimer(double studyDuration, double breakDuration, TimerVisualizationUpdater studyController) {
        this.studyDuration = studyDuration;
        this.breakDuration = breakDuration;
        this.studyController = studyController;
    }
    public void resumeTimer(JLabel currentPhaseName) {
        if (isStudyPhase){
            currentPhaseName.setText("Lernphase");
            this.remainingTimerDuration = this.studyDuration;
        }
        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (remainingTimerDuration % 60 == 0) {
                    studyController.updatedRemainingTime(remainingTimerDuration);
                }
                remainingTimerDuration -= 1.0;

                if (isStudyPhase) {

                    if (remainingTimerDuration < 0) {
                        cancel();
                        remainingTimerDuration = breakDuration;
                        isStudyPhase = false;
                        timerIsDone();
                    }
                } else {

                    if (remainingTimerDuration < 0) {
                        cancel();
                        remainingTimerDuration = studyDuration;
                        isStudyPhase = true;
                        timerIsDone();
                    }
                }
            }
        };
        countdownTimer.scheduleAtFixedRate(myTimerTask, 0, 1000);
    }

    private void timerIsDone(){
        studyController.phaseDone(this, isStudyPhase);
    }
    public void pauseTimer() {
        countdownTimer.cancel();
        countdownTimer = new Timer();
    }
}
