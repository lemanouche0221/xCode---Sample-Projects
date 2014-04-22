package domain;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.apple.itunes.Dict;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class Track {

	private int trackId;
	public static final String TRACK_ID = "Track ID";
	private String name;
	private static final String NAME = "Name";
	private String artist;
	private static final String ARTIST = "Artist";
	private String album;
	private static final String ALBUM = "Album";
	private String albumArtist;
	private static final String ALBUM_ARTIST = "Album Artist";
	private String composer;
	private static final String COMPOSER = "Composer";
	private String grouping;
	private static final String GROUPING = "Grouping";
	private String genre;
	private static final String GENRE = "Genre";
	private String kind;
	private static final String KIND = "Kind";
	private Integer size;
	private static final String SIZE = "Size";
	private Integer totalTime;
	private static final String TOTAL_TIME = "Total Time";
	private Integer startTime;
	private static final String START_TIME = "Start Time";
	private Integer stopTime;
	private static final String STOP_TIME = "Stop Time";
	private Integer discNumber;
	private static final String DISC_NUMBER = "Disc Number";
	private Integer discCount;
	private static final String DISC_COUNT = "Disc Count";
	private Integer trackNumber;
	private static final String TRACK_NUMBER = "Track Number";
	private Integer trackCount;
	private static final String TRACK_COUNT = "Track Count";
	private Integer year;
	private static final String YEAR = "Year";
	private Integer bpm;
	private static final String BPM = "BPM";
	private Date dateModified;
	private static final String DATE_MODIFIED = "Date Modified";
	private Date dateAdded;
	private static final String DATE_ADDED = "Date Added";
	private Integer bitRate;
	private static final String BIT_RATE = "Bit Rate";
	private Integer sampleRate;
	private static final String SAMPLE_RATE = "Sample Rate";
	private Integer volumeAdjustment;
	private static final String VOLUME_ADJUSTMENT = "Volume Adjustment";
	private Boolean gaplessAlbum;
	private static final String GAPLESS_ALBUM = "Part Of Gapless Album";
	private String comments;
	private static final String COMMENTS = "Comments";
	private Integer playCount;
	private static final String PLAY_COUNT = "Play Count";
	private BigInteger playDate;
	private static final String PLAY_DATE = "Play Date";
	private Date playDateUTC;
	private static final String PLAY_DATE_UTC = "Play Date UTC";
	private Integer skipCount;
	private static final String SKIP_COUNT = "Skip Count";
	private Date skipDate;
	private static final String SKIP_DATE = "Skip Date";
	private Date releaseDate;
	private static final String RELEASE_DATE = "Release Date";
	private Integer normalization;
	private static final String NORMALIZATION = "Normalization";
	private Boolean compilation;
	private static final String COMPILATION = "Compilation";
	private Integer artworkCount;
	private static final String ARTWORK_COUNT = "Artwork Count";
	private String series;
	private static final String SERIES = "Series";
	private Integer season;
	private static final String SEASON = "Season";
	private String episode;
	private static final String EPISODE = "Episode";
	private Integer episodeOrder;
	private static final String EPISODE_ORDER = "Episode Order";
	private String sortAlbum;
	private static final String SORT_ALBUM = "Sort Album";
	private String sortAlbumArtist;
	private static final String SORT_ALBUM_ARTIST = "Sort Album Artist";
	private String sortArtist;
	private static final String SORT_ARTIST = "Sort Artist";
	private String sortComposer;
	private static final String SORT_COMPOSER = "Sort Composer";
	private String sortName;
	private static final String SORT_NAME = "Sort Name";
	private String sortSeries;
	private static final String SORT_SERIES = "Sort Series";
	private String persistentID;
	private static final String PERSISTENT_ID = "Persistent ID";
	private Boolean clean;
	private static final String CLEAN = "Clean";
	private Boolean explicit;
	private static final String EXPLICIT = "Explicit";
	private String trackType;
	private static final String TRACK_TYPE = "Track Type";
	private Boolean isProtected;
	private static final String PROTECTED = "Protected";
	private Boolean purchased;
	private static final String PURCHASED = "Purchased";
	private Boolean podcast;
	private static final String PODCAST = "Podcast";
	private Boolean hasVideo;
	private static final String HAS_VIDEO = "Has Video";
	private Boolean hd;
	private static final String HD = "HD";
	private Integer videoWidth;
	private static final String VIDEO_WIDTH = "Video Width";
	private Integer videoHeight;
	private static final String VIDEO_HEIGHT = "Video Height";
	private Boolean movie;
	private static final String MOVIE = "Movie";
	private Boolean musicVideo;
	private static final String MUSIC_VIDEO = "Music Video";
	private Boolean tvShow;
	private static final String TV_SHOW = "TV Show";
	private Boolean unplayed;
	private static final String UNPLAYED = "Unplayed";
	private String location;
	private static final String LOCATION = "Location";
	private Integer fileFolderCount;
	private static final String FILE_FOLDER_COUNT = "File Folder Count";
	private Integer libraryFolderCount;
	private static final String LIBRARY_FOLDER_COUNT = "Library Folder Count";
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	public Track(int trackId, String name, String artist, String album, 
			String kind, Integer totalTime, Date dateAdded, Integer playCount, 
			Date playDateUTC, String persistentID, String location) {
		this.trackId = trackId;
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.kind = kind;
		this.totalTime = totalTime;
		this.dateAdded = dateAdded;
		this.playCount = playCount;
		this.playDateUTC = playDateUTC;
		this.persistentID = persistentID;
		this.location = location;
	}
	
	public Track(int trackId, String name, String artist, String albumArtist, String composer,
			String album, String grouping, String genre, String kind, Integer size, Integer totalTime, 
			Integer startTime, Integer stopTime, Integer discNumber, Integer discCount, 
			Integer trackNumber, Integer trackCount, Integer year, Integer bpm, Date dateModified, 
			Date dateAdded, Integer bitRate, Integer sampleRate, Integer volumeAdjustment, 
			Boolean gaplessAlbum, String comments, Integer playCount, BigInteger playDate, 
			Date playDateUTC, Integer skipCount, Date skipDate, Date releaseDate, Integer normalization, Boolean compilation,
			Integer artworkCount, String series, Integer season, String episode, Integer episodeOrder,
			String sortAlbum, String sortAlbumArtist, String sortArtist, String sortComposer,
			String sortName, String sortSeries, String persistentID, Boolean clean, Boolean explicit, 
			String trackType, Boolean isProtected, Boolean purchased, Boolean hasVideo, Boolean hd, Boolean podcast, 
			Integer videoWidth, Integer videoHeight, Boolean movie, Boolean musicVideo, Boolean tvShow,
			Boolean unplayed, String location, Integer fileFolderCount, Integer libraryFolderCount) {
		this.trackId = trackId;
		this.name = name;
		this.artist = artist;
		this.albumArtist = albumArtist;
		this.composer = composer;
		this.album = album;
		this.grouping = grouping;
		this.genre = genre;
		this.kind = kind;
		this.size = size;
		this.totalTime = totalTime;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.discNumber = discNumber;
		this.discCount = discCount;
		this.trackNumber = trackNumber;
		this.trackCount = trackCount;
		this.year = year;
		this.bpm = bpm;
		this.dateModified = dateModified;
		this.dateAdded = dateAdded;
		this.bitRate = bitRate;
		this.sampleRate = sampleRate;
		this.volumeAdjustment = volumeAdjustment;
		this.gaplessAlbum = gaplessAlbum;
		this.comments = comments;
		this.playCount = playCount;
		this.playDate = playDate;
		this.playDateUTC = playDateUTC;
		this.skipCount = skipCount;
		this.skipDate = skipDate;
		this.releaseDate = releaseDate;
		this.normalization = normalization;
		this.compilation = compilation;
		this.artworkCount = artworkCount;
		this.series = series;
		this.season = season;
		this.episode = episode;
		this.episodeOrder = episodeOrder;
		this.sortAlbum = sortAlbum;
		this.sortAlbumArtist = sortAlbumArtist;
		this.sortArtist = sortArtist;
		this.sortComposer = sortComposer;
		this.sortName = sortName;
		this.sortSeries = sortSeries;
		this.persistentID = persistentID;
		this.clean = clean;
		this.explicit = explicit;
		this.trackType = trackType;
		this.isProtected = isProtected;
		this.purchased = purchased;
		this.hasVideo = hasVideo;
		this.hd = hd;
		this.podcast = podcast;
		this.videoWidth = videoWidth;
		this.videoHeight = videoHeight;
		this.movie = movie;
		this.musicVideo = musicVideo;
		this.tvShow = tvShow;
		this.unplayed = unplayed;
		this.location = location;
		this.fileFolderCount = fileFolderCount;
		this.libraryFolderCount = libraryFolderCount;
	}
	
	public Track(Map<String, String> trackMap) throws NumberFormatException, ParseException {
		this(Integer.parseInt(trackMap.get(TRACK_ID)), trackMap.get(NAME), trackMap.get(ARTIST), 
				trackMap.get(ALBUM_ARTIST), trackMap.get(COMPOSER), trackMap.get(ALBUM), 
				trackMap.get(GROUPING), trackMap.get(GENRE), trackMap.get(KIND), getInteger(trackMap.get(SIZE)), 
				getInteger(trackMap.get(TOTAL_TIME)), getInteger(trackMap.get(START_TIME)),
				getInteger(trackMap.get(STOP_TIME)), getInteger(trackMap.get(DISC_NUMBER)), 
				getInteger(trackMap.get(DISC_COUNT)), getInteger(trackMap.get(TRACK_NUMBER)), 
				getInteger(trackMap.get(TRACK_COUNT)), getInteger(trackMap.get(YEAR)), 
				getInteger(trackMap.get(BPM)), getDate(trackMap.get(DATE_MODIFIED)), 
				dateFormat.parse(trackMap.get(DATE_ADDED)), getInteger(trackMap.get(BIT_RATE)), 
				getInteger(trackMap.get(SAMPLE_RATE)), getInteger(trackMap.get(VOLUME_ADJUSTMENT)),
				getBoolean(trackMap.get(GAPLESS_ALBUM)), trackMap.get(COMMENTS),
				getInteger(trackMap.get(PLAY_COUNT)), getBigInteger(trackMap.get(PLAY_DATE)), 
				getDate(trackMap.get(PLAY_DATE_UTC)), getInteger(trackMap.get(SKIP_COUNT)), 
				getDate(trackMap.get(SKIP_DATE)), getDate(trackMap.get(RELEASE_DATE)), 
				getInteger(trackMap.get(NORMALIZATION)), getBoolean(trackMap.get(COMPILATION)), 
				getInteger(trackMap.get(ARTWORK_COUNT)), trackMap.get(SERIES), getInteger(trackMap.get(SEASON)), 
				trackMap.get(EPISODE), getInteger(trackMap.get(EPISODE_ORDER)), trackMap.get(SORT_ALBUM), 
				trackMap.get(SORT_ALBUM_ARTIST), trackMap.get(SORT_ARTIST), trackMap.get(SORT_COMPOSER), 
				trackMap.get(SORT_NAME), trackMap.get(SORT_SERIES), trackMap.get(PERSISTENT_ID), 
				getBoolean(trackMap.get(CLEAN)), getBoolean(trackMap.get(EXPLICIT)), trackMap.get(TRACK_TYPE), 
				getBoolean(trackMap.get(PROTECTED)), getBoolean(trackMap.get(PURCHASED)),
				getBoolean(trackMap.get(HAS_VIDEO)), getBoolean(trackMap.get(HD)), getBoolean(trackMap.get(PODCAST)), 
				getInteger(trackMap.get(VIDEO_WIDTH)), getInteger(trackMap.get(VIDEO_HEIGHT)), 
				getBoolean(trackMap.get(MOVIE)), getBoolean(trackMap.get(MUSIC_VIDEO)), 
				getBoolean(trackMap.get(TV_SHOW)), getBoolean(trackMap.get(UNPLAYED)), trackMap.get(LOCATION), 
				getInteger(trackMap.get(FILE_FOLDER_COUNT)), getInteger(trackMap.get(LIBRARY_FOLDER_COUNT)));
	}

	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbumArtist() {
		return albumArtist;
	}

	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(Integer trackNumber) {
		this.trackNumber = trackNumber;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Integer getBitRate() {
		return bitRate;
	}

	public void setBitRate(Integer bitRate) {
		this.bitRate = bitRate;
	}

	public Integer getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(Integer sampleRate) {
		this.sampleRate = sampleRate;
	}

	public Integer getPlayCount() {
		return playCount == null ? 0 : playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public BigInteger getPlayDate() {
		return playDate;
	}

	public void setPlayDate(BigInteger playDate) {
		this.playDate = playDate;
	}
	
	public Date getPlayDateUTC() {
		return playDateUTC;
	}
	
	public void setPlayDateUTC(Date playDateUTC) {
		this.playDateUTC = playDateUTC;
	}

	public Integer getSkipCount() {
		return skipCount;
	}

	public void setSkipCount(Integer skipCount) {
		this.skipCount = skipCount;
	}

	public Date getSkipDate() {
		return skipDate;
	}

	public void setSkipDate(Date skipDate) {
		this.skipDate = skipDate;
	}

	public int getArtworkCount() {
		return artworkCount;
	}

	public void setArtworkCount(int artworkCount) {
		this.artworkCount = artworkCount;
	}

	public String getPersistentID() {
		return persistentID;
	}

	public void setPersistentID(String persistentID) {
		this.persistentID = persistentID;
	}

	public String getTrackType() {
		return trackType;
	}

	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getFileFolderCount() {
		return fileFolderCount;
	}

	public void setFileFolderCount(Integer fileFolderCount) {
		this.fileFolderCount = fileFolderCount;
	}

	public Integer getLibraryFolderCount() {
		return libraryFolderCount;
	}

	public void setLibraryFolderCount(Integer libraryFolderCount) {
		this.libraryFolderCount = libraryFolderCount;
	}

	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", name=" + name + ", artist="
				+ artist + ", album=" + album + ", albumArtist=" + albumArtist
				+ ", genre=" + genre + ", kind=" + kind + ", size=" + size
				+ ", totalTime=" + totalTime + ", trackNumber=" + trackNumber
				+ ", year=" + year + ", dateModified=" + dateModified
				+ ", dateAdded=" + dateAdded + ", bitRate=" + bitRate
				+ ", sampleRate=" + sampleRate + ", playCount=" + playCount
				+ ", playDate=" + playDate + ", skipCount=" + skipCount
				+ ", skipDate=" + skipDate + ", artworkCount=" + artworkCount
				+ ", persistentID=" + persistentID + ", trackType=" + trackType
				+ ", Location=" + location + ", fileFolderCount="
				+ fileFolderCount + ", libraryFolderCount="
				+ libraryFolderCount + "]";
	}
	
	private static Boolean getBoolean(String strValue) {
		return strValue != null ? Boolean.parseBoolean(strValue) : null;
	}

	private static Integer getInteger(String strValue) {
		return strValue != null ? Integer.parseInt(strValue) : null;
	}
	
	private static BigInteger getBigInteger(String strValue) {
		return strValue != null ? BigInteger.valueOf(Long.parseLong(strValue)) : null;
	}
	
	private static Date getDate(String strValue) throws ParseException {
		return strValue != null ? dateFormat.parse(strValue) : null;
	}
	
	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public Integer getStopTime() {
		return stopTime;
	}

	public void setStopTime(Integer stopTime) {
		this.stopTime = stopTime;
	}

	public Integer getDiscNumber() {
		return discNumber;
	}

	public void setDiscNumber(Integer discNumber) {
		this.discNumber = discNumber;
	}

	public Integer getDiscCount() {
		return discCount;
	}

	public void setDiscCount(Integer discCount) {
		this.discCount = discCount;
	}

	public Integer getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(Integer trackCount) {
		this.trackCount = trackCount;
	}

	public Integer getVolumeAdjustment() {
		return volumeAdjustment;
	}

	public void setVolumeAdjustment(Integer volumeAdjustment) {
		this.volumeAdjustment = volumeAdjustment;
	}

	public Boolean getGaplessAlbum() {
		return gaplessAlbum;
	}

	public void setGaplessAlbum(Boolean gaplessAlbum) {
		this.gaplessAlbum = gaplessAlbum;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getNormalization() {
		return normalization;
	}

	public void setNormalization(Integer normalization) {
		this.normalization = normalization;
	}

	public Boolean getCompilation() {
		return compilation;
	}

	public void setCompilation(Boolean compilation) {
		this.compilation = compilation;
	}

	public String getSortAlbum() {
		return sortAlbum;
	}

	public void setSortAlbum(String sortAlbum) {
		this.sortAlbum = sortAlbum;
	}

	public String getSortAlbumArtist() {
		return sortAlbumArtist;
	}

	public void setSortAlbumArtist(String sortAlbumArtist) {
		this.sortAlbumArtist = sortAlbumArtist;
	}

	public String getSortArtist() {
		return sortArtist;
	}

	public void setSortArtist(String sortArtist) {
		this.sortArtist = sortArtist;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public Boolean getPodcast() {
		return podcast;
	}

	public void setPodcast(Boolean podcast) {
		this.podcast = podcast;
	}

	public Boolean getUnplayed() {
		return unplayed;
	}

	public void setUnplayed(Boolean unplayed) {
		this.unplayed = unplayed;
	}

	public void setArtworkCount(Integer artworkCount) {
		this.artworkCount = artworkCount;
	}

	public String getGrouping() {
		return grouping;
	}

	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getBpm() {
		return bpm;
	}

	public void setBpm(Integer bpm) {
		this.bpm = bpm;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public String getEpisode() {
		return episode;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
	}

	public Integer getEpisodeOrder() {
		return episodeOrder;
	}

	public void setEpisodeOrder(Integer episodeOrder) {
		this.episodeOrder = episodeOrder;
	}

	public String getSortComposer() {
		return sortComposer;
	}

	public void setSortComposer(String sortComposer) {
		this.sortComposer = sortComposer;
	}

	public String getSortSeries() {
		return sortSeries;
	}

	public void setSortSeries(String sortSeries) {
		this.sortSeries = sortSeries;
	}

	public Boolean getClean() {
		return clean;
	}

	public void setClean(Boolean clean) {
		this.clean = clean;
	}

	public Boolean getExplicit() {
		return explicit;
	}

	public void setExplicit(Boolean explicit) {
		this.explicit = explicit;
	}

	public Boolean isProtected() {
		return isProtected;
	}

	public void setProtected(Boolean isProtected) {
		this.isProtected = isProtected;
	}

	public Boolean getPurchased() {
		return purchased;
	}

	public void setPurchased(Boolean purchased) {
		this.purchased = purchased;
	}

	public Boolean getHasVideo() {
		return hasVideo;
	}

	public void setHasVideo(Boolean hasVideo) {
		this.hasVideo = hasVideo;
	}

	public Boolean getHd() {
		return hd;
	}

	public void setHd(Boolean hd) {
		this.hd = hd;
	}

	public Integer getVideoWidth() {
		return videoWidth;
	}

	public void setVideoWidth(Integer videoWidth) {
		this.videoWidth = videoWidth;
	}

	public Integer getVideoHeight() {
		return videoHeight;
	}

	public void setVideoHeight(Integer videoHeight) {
		this.videoHeight = videoHeight;
	}

	public Boolean getMovie() {
		return movie;
	}

	public void setMovie(Boolean movie) {
		this.movie = movie;
	}

	public Boolean getMusicVideo() {
		return musicVideo;
	}

	public void setMusicVideo(Boolean musicVideo) {
		this.musicVideo = musicVideo;
	}

	public Boolean getTvShow() {
		return tvShow;
	}

	public void setTvShow(Boolean tvShow) {
		this.tvShow = tvShow;
	}

	public Double getPlaysPerDay() {
		Calendar calendarDate = Calendar.getInstance();
		return getPlaysPerDay(calendarDate);
	}
	
	private Double getPlaysPerDay(Calendar calendarDate) {
		Double plays =  new Double((double)this.getPlayCount()/ ( (calendarDate.getTimeInMillis() - this.getDateAdded().getTime()) / (1000*60*60*24)) );
		plays = plays.equals(Double.NaN) ? Double.MIN_VALUE : plays;
		return plays;
	}
	
    public static class MostOftenPlayedComparator implements Comparator<Track> {
    	private static Calendar calendarDate = Calendar.getInstance();
    	public int compare(Track t1, Track t2) {
			int days1 = (int) ((calendarDate.getTimeInMillis() - t1.getDateAdded().getTime()) / (1000*60*60*24));
			Double playCount1 = t1.getPlaysPerDay(calendarDate) / (days1 < 30 ? 2 * (4 - (days1/10)) : 1);
			int days2 = (int) ((calendarDate.getTimeInMillis() - t2.getDateAdded().getTime()) / (1000*60*60*24));
			Double playCount2 = t2.getPlaysPerDay(calendarDate) / (days2 < 30 ? 2 * (4 - (days2/10)) : 1);
			return - playCount1.compareTo(playCount2);
    	}
    }
    
    public static Predicate getMusicPredicate() {
        Predicate predicate = new Predicate() {
            public boolean evaluate(Object object) {
            	if (object.getClass() != Track.class) {
            		return false;
            	}
            	Track track = (Track) object;
            	if (track.getPodcast() != null && track.getPodcast()) {
            		return false;
            	}
            	if (track.getTvShow() != null && track.getTvShow()) {
            		return false;
            	}
            	if (track.getMovie() != null && track.getMovie()) {
            		return false;
            	}
                return true;
            }
        };
        return predicate;
    }

	public static List<Track> getSortedTracksByCount(Collection<Track> tracks, Comparator<Track> comparator, Integer limit) {
		List<Track> trackList = new ArrayList<Track>(tracks);
		CollectionUtils.filter(trackList, getMusicPredicate());
		Collections.sort(trackList, comparator);
		try {
			return trackList.subList(0, limit);
		}
		catch(NullPointerException ex) {
			return trackList;
		}
		catch(IndexOutOfBoundsException ex) {
			return trackList;
		}
		catch(IllegalArgumentException ex) {
			return new ArrayList<Track>();
		}
	}
	
	public static List<Track> getSortedTracksByTime(Collection<Track> tracks, Comparator<Track> comparator, Integer minutes) {
		return getSortedTracksByCountAndTime(tracks, comparator, null, minutes);
	}

	public static List<Track> getSortedTracksByCountAndTime(Collection<Track> tracks, Comparator<Track> comparator, Integer count, Integer minutes) {
		if (minutes == null)
			return getSortedTracksByCount(tracks, comparator, count);
		List<Track> trackList = new ArrayList<Track>(tracks);
		CollectionUtils.filter(trackList, getMusicPredicate());
		Collections.sort(trackList, comparator);
		try {
			int limit = 0;
			BigInteger maxMinutes = BigInteger.valueOf(minutes).multiply(BigInteger.valueOf(60 * 999));
			BigInteger totalTime = BigInteger.ZERO;
			
			for (Track track : trackList) {
				int trackTime = track.totalTime == null ? 0 : track.totalTime;
				totalTime = totalTime.add(BigInteger.valueOf(trackTime));
				if (totalTime.compareTo(maxMinutes) > -1)
					break;
				limit++;
			}
			
			if (count != null && count.compareTo(Integer.valueOf(limit)) < 0)
				limit = count;
			
			return trackList.subList(0, limit);
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
			return trackList;
		}
		catch(IllegalArgumentException ex) {
			ex.printStackTrace();
			return trackList;
		}
	}
	
	public Dict getDict() {
		Dict dict = new Dict();
		dict.addKeyAndValue(TRACK_ID, trackId);
		dict.addKeyAndValue(NAME, name);
		dict.addKeyAndValue(ARTIST, artist);
		dict.addKeyAndValue(ALBUM_ARTIST, albumArtist);
		dict.addKeyAndValue(COMPOSER, composer);
		dict.addKeyAndValue(ALBUM, album);
		dict.addKeyAndValue(GROUPING, grouping);
		dict.addKeyAndValue(GENRE, genre);
		dict.addKeyAndValue(KIND, kind);
		dict.addKeyAndValue(SIZE, size);
		dict.addKeyAndValue(TOTAL_TIME, totalTime);
		dict.addKeyAndValue(START_TIME, startTime);
		dict.addKeyAndValue(STOP_TIME, stopTime);
		dict.addKeyAndValue(DISC_NUMBER, discNumber);
		dict.addKeyAndValue(DISC_COUNT, discCount);
		dict.addKeyAndValue(TRACK_NUMBER, trackNumber);
		dict.addKeyAndValue(TRACK_COUNT, trackCount);
		dict.addKeyAndValue(YEAR, year);
		dict.addKeyAndValue(BPM, bpm);
		dict.addKeyAndValue(DATE_MODIFIED, dateModified, dateFormat);
		dict.addKeyAndValue(DATE_ADDED, dateAdded, dateFormat);
		dict.addKeyAndValue(BIT_RATE, bitRate);
		dict.addKeyAndValue(SAMPLE_RATE, sampleRate);
		dict.addKeyAndValue(VOLUME_ADJUSTMENT, volumeAdjustment);
		dict.addKeyAndValue(GAPLESS_ALBUM, gaplessAlbum);
		dict.addKeyAndValue(COMMENTS, comments);
		dict.addKeyAndValue(PLAY_COUNT, playCount);
		dict.addKeyAndValue(PLAY_DATE, playDate);
		dict.addKeyAndValue(PLAY_DATE_UTC, playDateUTC, dateFormat);
		dict.addKeyAndValue(SKIP_COUNT, skipCount);
		dict.addKeyAndValue(SKIP_DATE, skipDate, dateFormat);
		dict.addKeyAndValue(RELEASE_DATE, releaseDate, dateFormat);
		dict.addKeyAndValue(NORMALIZATION, normalization);
		dict.addKeyAndValue(COMPILATION, compilation);
		dict.addKeyAndValue(ARTWORK_COUNT, artworkCount);
		dict.addKeyAndValue(SERIES, series);
		dict.addKeyAndValue(SEASON, season);
		dict.addKeyAndValue(EPISODE, episode);
		dict.addKeyAndValue(EPISODE_ORDER, episodeOrder);
		dict.addKeyAndValue(SORT_ALBUM, sortAlbum);
		dict.addKeyAndValue(SORT_ALBUM_ARTIST, sortAlbumArtist);
		dict.addKeyAndValue(SORT_ARTIST, sortArtist);
		dict.addKeyAndValue(SORT_COMPOSER, sortComposer);
		dict.addKeyAndValue(SORT_NAME, sortName);
		dict.addKeyAndValue(SORT_SERIES, sortSeries);
		dict.addKeyAndValue(PERSISTENT_ID, persistentID);
		dict.addKeyAndValue(CLEAN, clean);
		dict.addKeyAndValue(EXPLICIT, explicit);
		dict.addKeyAndValue(TRACK_TYPE, trackType);
		dict.addKeyAndValue(PROTECTED, isProtected);
		dict.addKeyAndValue(PURCHASED, purchased);
		dict.addKeyAndValue(HAS_VIDEO, hasVideo);
		dict.addKeyAndValue(HD, hd);
		dict.addKeyAndValue(VIDEO_WIDTH, videoWidth);
		dict.addKeyAndValue(VIDEO_HEIGHT, videoHeight);
		dict.addKeyAndValue(MOVIE, movie);
		dict.addKeyAndValue(MUSIC_VIDEO, musicVideo);
		dict.addKeyAndValue(TV_SHOW, tvShow);
		dict.addKeyAndValue(PODCAST, podcast);
		dict.addKeyAndValue(UNPLAYED, unplayed);
		dict.addKeyAndValue(LOCATION, location);
		dict.addKeyAndValue(FILE_FOLDER_COUNT, fileFolderCount);
		dict.addKeyAndValue(LIBRARY_FOLDER_COUNT, libraryFolderCount);
		return dict;
	}
}
