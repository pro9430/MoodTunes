import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MoodPanel extends JPanel {
    private DefaultListModel<String> songListModel;
    private JList<String> songList;
    private PlaylistManager playlistManager;
    private String mood;
    private MoodTunesGUI parent;

    public MoodPanel(String mood, MoodTunesGUI parent) {
        this.mood = mood;
        this.parent = parent;
        this.playlistManager = new PlaylistManager();

        setLayout(new BorderLayout());
        setBackground(getMoodColor(mood));

        JLabel titleLabel = new JLabel("🎵 " + mood + " Playlist 🎵", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        songListModel = new DefaultListModel<>();
        songList = new JList<>(songListModel);
        JScrollPane scrollPane = new JScrollPane(songList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel controls = new JPanel();
        controls.setBackground(getMoodColor(mood));

        JButton addBtn = new JButton("Add Song");
        JButton removeBtn = new JButton("Remove Song");
        JButton playBtn = new JButton("Play Song");
        JButton backBtn = new JButton("Back");

        addBtn.addActionListener(e -> addSong());
        removeBtn.addActionListener(e -> removeSong());
        playBtn.addActionListener(e -> playSong());
        backBtn.addActionListener(e -> parent.returnToMoodSelection());

        controls.add(addBtn);
        controls.add(removeBtn);
        controls.add(playBtn);
        controls.add(backBtn);

        add(controls, BorderLayout.SOUTH);
    }

    private void addSong() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            songListModel.addElement(path);
            playlistManager.addSong(path);
        }
    }

    private void removeSong() {
        int selected = songList.getSelectedIndex();
        if (selected != -1) {
            String song = songListModel.getElementAt(selected);
            songListModel.remove(selected);
            playlistManager.removeSong(song);
        }
    }

    private void playSong() {
        int selected = songList.getSelectedIndex();
        if (selected != -1) {
            MusicPlayer.play(songListModel.getElementAt(selected));
        }
    }

    private Color getMoodColor(String mood) {
        return switch (mood) {
            case "Happy" -> Color.YELLOW;
            case "Sad" -> Color.CYAN;
            case "Calm" -> new Color(173, 216, 230);
            case "Energetic" -> Color.PINK;
            default -> Color.LIGHT_GRAY;
        };
    }
}
