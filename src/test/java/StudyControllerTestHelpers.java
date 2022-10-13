import java.util.ArrayList;

public class StudyControllerTestHelpers {

    public ArrayList<StudyTimerSpy> fixedTimers = new ArrayList<>();

    public final int timerCount = 3;

    public StudyTimerSpy buildFake(double studyDur, double breakDur, TimerVisualizationUpdater updater) {
        StudyTimerSpy newEntry = new StudyTimerSpy(studyDur, breakDur, updater);
        fixedTimers.add(newEntry);
        if (fixedTimers.size() > timerCount) {
            fixedTimers.remove(0);
        }
        return newEntry;
    }

    public static class StudyTimerSpy extends StudyTimer {

        public TimerVisualizationUpdater myParent;
        public double studyDuration;
        public double breakDuration;

        public StudyTimerSpy(double studyDuration, double breakDuration, TimerVisualizationUpdater studyController) {
            super(studyDuration, breakDuration, studyController);
            myParent = studyController;
            this.studyDuration = studyDuration;
            this.breakDuration = breakDuration;
        }

//        Runnable resumeCallback;
        public boolean resumeWasCalled = false;
        @Override
        public void resumeTimer() {
            resumeWasCalled = true;
//            if (resumeCallback != null) {
//                resumeCallback.run();
//            }
        }

//        Runnable pauseCallback;
        public boolean pauseWasCalled = false;
        @Override
        public void pauseTimer() {
            pauseWasCalled = true;
//            if (pauseCallback != null) {
//                pauseCallback.run();
//            }
        }

        public void fakePhaseDoneCall(StudyTimerSpy fakeTimer, boolean fakedPhase) {
            myParent.phaseDone(fakeTimer, fakedPhase);
        }
    }
}
