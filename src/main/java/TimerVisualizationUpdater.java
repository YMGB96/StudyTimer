public interface TimerVisualizationUpdater {
    void phaseDone(StudyTimer studyTimer, boolean isStudyPhase);
    void updatedRemainingTime(double remainingTime);
}