import java.awt.*;

public interface StudyControllerInterface {
    void phaseDone(StudyTimer studyTimer, boolean isStudyPhase) throws AWTException;
    void updatedRemainingTime(double remainingTime);
}