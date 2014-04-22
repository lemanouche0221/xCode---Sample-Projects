package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.apple.itunes.Array;
import com.apple.itunes.Dict;

public class Playlist {
	private Integer playlistId;
	private static String PLAYLIST_ID = "Playlist ID";
	private String name;
	public static String NAME = "Name";
	private String persistentID;
	private static String PERSISTENT_ID = "Playlist Persistent ID";
	private Integer distinguishedKind;
	private static String DISTINGUISHED_KIND = "Distinguished Kind";
	private Boolean master;
	private static String MASTER = "Master";
	private Boolean visible;
	private static String VISIBLE = "Visible";
	private Boolean purchasedMusic;
	private static String PURCHASED_MUSIC = "Purchased Music";
	private Boolean audiobooks;
	private static String AUDIOBOOKS = "Audiobooks";
	private Boolean books;
	private static String BOOKS = "Books";
	private Boolean movies;
	private static String MOVIES = "Movies";
	private Boolean music;
	private static String MUSIC = "Music";
	private Boolean podcasts;
	private static String PODCASTS = "Podcasts";
	private Boolean tvShows;
	private static String TV_SHOWS = "TV Shows";
	private boolean allItems;
	public static String ALL_ITEMS = "All Items";
	private byte[] smartInfo;
	public static String SMART_INFO = "Smart Info";
	private byte[] smartCriteria;
	public static String SMART_CRITERIA = "Smart Criteria";
	private List<Integer> playlistItems;
	public static String PLAYLIST_ITEMS = "Playlist Items";

	public Playlist(Integer playlistId, String name, String persistentID, Integer distinguishedKind, 
			Boolean master, Boolean visible, Boolean purchasedMusic, Boolean audiobooks, 
			Boolean books, Boolean music, Boolean movies, Boolean podcasts, Boolean tvShows, 
			boolean allItems, byte[] smartInfo, byte[] smartCriteria, 
			List<Integer> playlistItems) {
		this.playlistId = playlistId;
		this.name = name;
		this.persistentID = persistentID;
		this.distinguishedKind = distinguishedKind;
		this.master = master;
		this.visible = visible;
		this.purchasedMusic = purchasedMusic;
		this.audiobooks = audiobooks;
		this.books = books;
		this.music = music;
		this.movies = movies;
		this.podcasts = podcasts;
		this.tvShows = tvShows;
		this.allItems = allItems;
		this.smartInfo = smartInfo;
		this.smartCriteria = smartCriteria;
		this.playlistItems = playlistItems;
	}
	
	public Playlist(Map<String, String> playlistMap) {
		this(getIntegerValue(playlistMap.get(PLAYLIST_ID)), playlistMap.get(NAME), 
				playlistMap.get(PERSISTENT_ID), getIntegerValue(playlistMap.get(DISTINGUISHED_KIND)), 
				getBooleanValue(playlistMap.get(MASTER)), getBooleanValue(playlistMap.get(VISIBLE)), 
				getBooleanValue(playlistMap.get(PURCHASED_MUSIC)), getBooleanValue(playlistMap.get(AUDIOBOOKS)), 
				getBooleanValue(playlistMap.get(BOOKS)), getBooleanValue(playlistMap.get(MUSIC)), 
				getBooleanValue(playlistMap.get(MOVIES)), getBooleanValue(playlistMap.get(PODCASTS)), 
				getBooleanValue(playlistMap.get(TV_SHOWS)), getBooleanValue(playlistMap.get(ALL_ITEMS)), null, null, null);
	}

	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersistentID() {
		return persistentID;
	}

	public void setPersistentID(String persistentID) {
		this.persistentID = persistentID;
	}

	public Integer getDistinguishedKind() {
		return distinguishedKind;
	}

	public void setDistinguishedKind(Integer distinguishedKind) {
		this.distinguishedKind = distinguishedKind;
	}

	public Boolean getMaster() {
		return master;
	}

	public void setMaster(Boolean master) {
		this.master = master;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getMusic() {
		return music;
	}

	public void setMusic(Boolean music) {
		this.music = music;
	}

	public Boolean getMovies() {
		return movies;
	}

	public void setMovies(Boolean movies) {
		this.movies = movies;
	}

	public Boolean getTvShows() {
		return tvShows;
	}

	public void setTvShows(Boolean tvShows) {
		this.tvShows = tvShows;
	}

	public boolean isAllItems() {
		return allItems;
	}

	public void setAllItems(boolean allItems) {
		this.allItems = allItems;
	}

	public byte[] getSmartInfo() {
		return smartInfo;
	}

	public void setSmartInfo(byte[] smartInfo) {
		this.smartInfo = smartInfo;
	}

	public byte[] getSmartCriteria() {
		return smartCriteria;
	}

	public void setSmartCriteria(byte[] smartCriteria) {
		this.smartCriteria = smartCriteria;
	}

	public List<Integer> getPlaylistItems() {
		if (playlistItems == null)
			playlistItems = new ArrayList<Integer>();
		return playlistItems;
	}
	
	public Boolean getPurchasedMusic() {
		return purchasedMusic;
	}

	public void setPurchasedMusic(Boolean purchasedMusic) {
		this.purchasedMusic = purchasedMusic;
	}

	public Boolean getAudiobooks() {
		return audiobooks;
	}

	public void setAudiobooks(Boolean audiobooks) {
		this.audiobooks = audiobooks;
	}

	public Boolean getBooks() {
		return books;
	}

	public void setBooks(Boolean books) {
		this.books = books;
	}

	public Boolean getPodcasts() {
		return podcasts;
	}

	public void setPodcasts(Boolean podcasts) {
		this.podcasts = podcasts;
	}

	@Override
	public String toString() {
		return "Playlist [playlistId=" + playlistId + ", name=" + name
				+ ", persistentID=" + persistentID + ", distinguishedKind="
				+ distinguishedKind + ", master=" + master + ", music=" + music
				+ ", movies=" + movies + ", tvShows=" + tvShows + ", allItems=" + allItems
				+ ", smartInfo=" + (smartInfo != null) + ", smartCriteria=" + (smartCriteria != null)
				+ ", playlistItems=" + (playlistItems != null ? playlistItems.size() : 0) + "]";
	}
	
	public Dict getDict() {
		Dict dict = new Dict();
		dict.addKeyAndValue(NAME, name);
		dict.addKeyAndValue(MASTER, master);
		dict.addKeyAndValue(PLAYLIST_ID, playlistId);
		dict.addKeyAndValue(PERSISTENT_ID, persistentID);
		dict.addKeyAndValue(DISTINGUISHED_KIND, distinguishedKind);
		dict.addKeyAndValue(VISIBLE, visible);
		dict.addKeyAndValue(PURCHASED_MUSIC, purchasedMusic);
		dict.addKeyAndValue(AUDIOBOOKS, audiobooks);
		dict.addKeyAndValue(BOOKS, books);
		dict.addKeyAndValue(MUSIC, music);
		dict.addKeyAndValue(MOVIES, movies);
		dict.addKeyAndValue(PODCASTS, podcasts);
		dict.addKeyAndValue(TV_SHOWS, tvShows);
		dict.addKeyAndValue(ALL_ITEMS, allItems);
		if (smartCriteria != null && smartInfo != null) {
			dict.addKeyAndValue(SMART_INFO, smartInfo);
			dict.addKeyAndValue(SMART_CRITERIA, smartCriteria);
		}
		if (playlistItems != null && playlistItems.size() > 0) {
			Array itemsArray = new Array();
			for (Integer i : playlistItems) {
				if (i != null) {
					Dict itemDict = new Dict();
					itemDict.addKeyAndValue(Track.TRACK_ID, i);
					itemsArray.getDict().add(itemDict);
				}
			}
			dict.addKeyAndValue(PLAYLIST_ITEMS, itemsArray);
		}
		return dict;
	}
	
	private static Boolean getBooleanValue(String value) {
		if (value == null)
			return null;
		return Boolean.valueOf(value);
	}
	
	private static Integer getIntegerValue(String value) {
		if (value == null)
			return null;
		return Integer.parseInt(value);
	}
}
