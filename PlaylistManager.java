import java.util.ArrayList;

public class PlaylistManager {
    private ArrayList<String> songList = new ArrayList<>();

    public void addSong(String songPath) {
        songList.add(songPath);
    }

    public void removeSong(String songPath) {
        songList.remove(songPath);
    }

    public ArrayList<String> getSongs() {
        return songList;
    }
}
