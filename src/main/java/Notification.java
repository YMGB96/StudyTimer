import java.awt.*;
import java.awt.TrayIcon.MessageType;

public interface Notification {
    static void displayNotification() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Pause vorbei", "Die Pausenzeit ist abgelaufen", MessageType.INFO);
    }
}