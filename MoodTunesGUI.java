import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MoodTunesGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private HashMap<String, MoodPanel> moodPanels;

    public MoodTunesGUI() {
        setTitle("MoodTunes 🎵");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        moodPanels = new HashMap<>();

        mainPanel.add(createMoodSelectionScreen(), "MoodSelection");
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createMoodSelectionScreen() {
        JPanel moodPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        moodPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        moodPanel.setBackground(Color.WHITE);

        String[] moods = {"Happy", "Sad", "Calm", "Energetic"};
        Color[] colors = {Color.YELLOW, Color.CYAN, new Color(173, 216, 230), Color.PINK};

        for (int i = 0; i < moods.length; i++) {
            String mood = moods[i];
            JButton moodButton = new JButton(mood);
            moodButton.setFont(new Font("SansSerif", Font.BOLD, 18));
            moodButton.setBackground(colors[i]);
            moodButton.setOpaque(true);
            moodButton.setPreferredSize(new Dimension(100, 100));
            moodButton.addActionListener(e -> openMoodPanel(mood));
            moodPanel.add(moodButton);
        }

        return moodPanel;
    }

    private void openMoodPanel(String mood) {
        if (!moodPanels.containsKey(mood)) {
            MoodPanel panel = new MoodPanel(mood, this);
            moodPanels.put(mood, panel);
            mainPanel.add(panel, mood);
        }
        cardLayout.show(mainPanel, mood);
    }

    public void returnToMoodSelection() {
        cardLayout.show(mainPanel, "MoodSelection");
    }
}
