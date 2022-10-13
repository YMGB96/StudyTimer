import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;


public class StudyControllerTests {
    private StudyController testStudyController;
    private JButton resumeButton;
    private JButton pauseButton;
    private JLabel timeLeftLabel;
    private JLabel phaseNameLabel;
    private StudyTimer testStudyTimer;

    private StudyControllerTestHelpers testHelpers;

    @Before
    public void setUp() {
        timeLeftLabel = new JLabel();
        phaseNameLabel = new JLabel();
        resumeButton = new JButton();
        pauseButton = new JButton();

        testHelpers = new StudyControllerTestHelpers();
        // sanity check
        assertEquals(testHelpers.fixedTimers.size(), 0);

        testStudyController = new StudyController(timeLeftLabel, phaseNameLabel, resumeButton, pauseButton,
                testHelpers.timerCount,10.0,5.0, testHelpers::buildFake);
    }
    @After
    public void teardown(){
        timeLeftLabel = null;
        phaseNameLabel = null;
        resumeButton = null;
        pauseButton = null;
        testStudyController = null;
    }
    @Test
    public void test_StudyController_addedAllExpectedActionListeners() throws InterruptedException {
        // the constructor was already called in setUp()
        assertEquals(4,resumeButton.getActionListeners().length);
        assertEquals(2,pauseButton.getActionListeners().length);

        assertEquals(testHelpers.fixedTimers.size(), 3);
        StudyControllerTestHelpers.StudyTimerSpy firstTimer = testHelpers.fixedTimers.get(0);

        assertFalse(testHelpers.fixedTimers.get(0).resumeWasCalled);
        resumeButton.doClick();
        assertTrue(testHelpers.fixedTimers.get(0).resumeWasCalled);

        assertEquals(phaseNameLabel.getText(), "Lernphase");
        testHelpers.fixedTimers.get(0).resumeWasCalled = false;
        testHelpers.fixedTimers.get(0).fakePhaseDoneCall(testHelpers.fixedTimers.get(0), false);
        assertEquals(phaseNameLabel.getText(), "Pause");
        assertTrue(testHelpers.fixedTimers.get(0).resumeWasCalled);
    }
    @Test
    public void test_updatedRemainingTime_displaysExpectedOutput() {
        testStudyController.updatedRemainingTime(60);
        assertEquals("Noch 1 Minute",timeLeftLabel.getText());
        testStudyController.updatedRemainingTime(30);
        assertEquals("Noch 0 Minuten",timeLeftLabel.getText());
        testStudyController.updatedRemainingTime(120);
        assertEquals("Noch 2 Minuten",timeLeftLabel.getText());
        testStudyController.updatedRemainingTime(0);
        assertEquals("Noch 0 Minuten",timeLeftLabel.getText());
    }
    @Test
    public void test_phaseDone_changePhaseNameLabelToExpectedValue() {
        testStudyController.phaseDone(testStudyTimer, false);
        assertEquals("Pause",phaseNameLabel.getText());
        testStudyController.phaseDone(testStudyTimer, true);
        assertEquals("Pause beendet",phaseNameLabel.getText());
        testStudyController.phaseDone(testStudyTimer, false);
        assertEquals("Pause",phaseNameLabel.getText());
        testStudyController.phaseDone(testStudyTimer, true);
        assertEquals("Lernsession beendet",phaseNameLabel.getText());
        assertEquals("Glückwunsch!",timeLeftLabel.getText());
    }
}