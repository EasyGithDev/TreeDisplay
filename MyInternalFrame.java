import javax.swing.JInternalFrame;

public class MyInternalFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public MyInternalFrame() {
        this(300, 300, "");
    }

    public MyInternalFrame(int width, int height, String title) {
        super("Document #" + (++openFrameCount),
                true, // resizable
                true, // closable
                true, // maximizable
                true);// iconifiable

        // ...Create the GUI and put it in the window...
        setTitle(title);

        // ...Then set the window size or call pack...
        setSize(width, height);

        // Set the window's location.
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
    }

}