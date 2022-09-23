import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class StudyTimer {


    public double getStudyDuration() {
        return studyDuration;
    }

    private double studyDuration;
    private double breakDuration;
    private double remainingTimerDuration;
    private String studyDoneAlarm;
    private String breakDoneAlarm;
    private StudyControllerInterface studyController;
    private Timer countdownTimer = new Timer();
    private boolean isStudyPhase = true;
    public double getBreakDuration() {
        return breakDuration;
    }
    public void setStudyDuration(double studyDuration) {
        this.studyDuration = studyDuration;
    }

    public StudyTimer(double studyDuration, double breakDuration, StudyControllerInterface studyController) {
        this.studyDuration = studyDuration;
        this.breakDuration = breakDuration;
        this.studyController = studyController;
    }
    public void setBreakDuration(double breakDuration) {
        this.breakDuration = breakDuration;
    }

    public void resumeTimer() {
        if (isStudyPhase == true){
            this.remainingTimerDuration = this.studyDuration;
        }
        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (remainingTimerDuration % 60 == 0) {
                    studyController.updatedRemainingTime(remainingTimerDuration);
                }
                remainingTimerDuration -= 1.0;

                if (isStudyPhase == true) {

                    if (remainingTimerDuration < 0) {
                        cancel();
                        remainingTimerDuration = breakDuration;
                        isStudyPhase = false;
                        try {
                            timerIsDone();
                        } catch (AWTException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {

                    if (remainingTimerDuration < 0) {
                        cancel();
                        remainingTimerDuration = studyDuration;
                        isStudyPhase = true;
                        try {
                            timerIsDone();
                        } catch (AWTException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };
        countdownTimer.scheduleAtFixedRate(myTimerTask, 0, 1000);
    }

    private void timerIsDone() throws AWTException {
        if (isStudyPhase == false) {
            studyController.phaseDone(this, false);
        } else {
            studyController.phaseDone(this, true);
        }
    }
    public void pauseTimer() {
        countdownTimer.cancel();
        countdownTimer = new Timer();
    }
}
