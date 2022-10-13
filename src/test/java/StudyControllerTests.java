import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;

import static org.junit.Assert.*;


public class StudyControllerTests {
    private StudyController testStudyController;
    private JButton resumeButton;
    private JButton pauseButton;
    private JLabel timeLeftLabel;
    private JLabel phaseNameLabel;
    private StudyTimer testStudyTimer;

    @Before
    public void setUp() {
        timeLeftLabel = new JLabel();
        phaseNameLabel = new JLabel();
        resumeButton = new JButton();
        pauseButton = new JButton();
        testStudyController = new StudyController(timeLeftLabel, phaseNameLabel, resumeButton, pauseButton,
                3,0,0);
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
    public void test_StudyController_addedAllExpectedActionListeners(){
        assertEquals(4,resumeButton.getActionListeners().length);
        assertEquals(2,pauseButton.getActionListeners().length);

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
    public void test_phaseDone_changePhaseNameLabelToExpectedValue(){
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