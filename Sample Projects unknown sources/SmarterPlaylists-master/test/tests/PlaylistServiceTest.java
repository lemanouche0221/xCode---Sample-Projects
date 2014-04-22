package tests;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.xml.sax.SAXException;

import services.PlaylistService;

import com.apple.itunes.Plist;

import domain.Library;
import domain.Playlist;
import domain.PlaylistLimit;
import domain.Track;
import enums.TrackFilterType;

public class PlaylistServiceTest {

	private List<Track> getTrackList() {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.clear();

		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.MONTH, -36);
        Date dateAdded = calendarDate.getTime();
        calendarDate.set(2013, 7, 8, 21, 5, 7);
        Date playDate = calendarDate.getTime();
		List<Track> trackList = new ArrayList<Track>();
		trackList.add(new Track(11716, "My Friends", "Red Hot Chili Peppers", "One Hot Minute", 
			"MPEG audio file", 242886, dateAdded, 36, playDate, "58129546BE036721", 
			"file://localhost/C:/Music/Red%20Hot%20Chili%20Peppers%20-%20My%20Friends.mp3"));
		
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.MONTH, -34);
	    dateAdded = calendarDate.getTime();
	    calendarDate.set(2013, 10, 19, 4, 43, 39);
	    playDate = calendarDate.getTime();
		trackList.add(new Track(8844, "Come Alive", "Foo Fighters", "Echoes, Silence, Patience & Grace", 
			"MPEG audio file", 310674, dateAdded, 153, playDate, "FEBA0A5A4AD727B0",
			"file://localhost/C:/Music/Foo%20Fighters%20-%20Come%20Alive.mp3"));
	
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.DATE, -1);
	    dateAdded = calendarDate.getTime();
		trackList.add(new Track(54321, "Losing My Edge", "LCD Soundsystem", "LCD Soundsystem", 
			"MPEG audio file", 268225, dateAdded, null, null, "HDF9EJDU7HEKDNEU",
			"file://localhost/C:/Music/LCD%20Soundsystem%20-%20Losing%20My%20Edge.mp3"));
		
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.MONTH, -42);
	    dateAdded = calendarDate.getTime();
	    calendarDate.set(2013, 8, 18, 5, 11, 31);
	    playDate = calendarDate.getTime();
		trackList.add(new Track(12326, "4th Of July", "Soundgarden", "Superunknown", 
			"MPEG audio file", 308349, dateAdded, 106, playDate, "64CEC1404A7F5071",
			"file://localhost/C:/Music/Soundgarden%20-%204th%20Of%20July.mp3"));
		
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.MONTH, -12);
		dateAdded = calendarDate.getTime();
		calendarDate.set(2013, 8, 7, 17, 36, 52);
		playDate = calendarDate.getTime();
		trackList.add(new Track(15322, "Pyramids", "Frank Ocean", "channel ORANGE", 
				"MPEG audio file", 593632, dateAdded, 36, playDate, "7F4DCB36553A2885",
				"file://localhost/C:/Music/Frank%20Ocean%20-%20Pyramids.mp3"));
		
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.DATE, -28);
		dateAdded = calendarDate.getTime();
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.MINUTE, -15);
		playDate = calendarDate.getTime();
		trackList.add(new Track(19938, "Instant Crush", "Daft Punk", "Random Access Memories", 
				"MPEG audio file", 337632, dateAdded, 12, playDate, "GHYD78GFRYH498FH",
				"file://localhost/Users/blevy/Music/iTunes/iTunes%20Media/Music/Daft%20Punk/Random%20Access%20Memories/Instant%20Crush.mp3"));
		
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.DATE, -7);
		dateAdded = calendarDate.getTime();
		playDate = null;
		Track podcast = new Track(26760, "RT Podcast #259", "Rooster Teeth Podcast", "Rooster Teeth Podcast", 
				"AAC audio file", null, dateAdded, null, playDate, "A32F51E87EAF2A84",
				"file://localhost/Users/blevy/Music/iTunes/Podcasts/Rooster%20Teeth%20Podcast/RT%20Podcast%20%23259.m4a");
		podcast.setPodcast(true);
		trackList.add(podcast);
		
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.DATE, -14);
		dateAdded = calendarDate.getTime();
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.MINUTE, -30);
		playDate = calendarDate.getTime();
		trackList.add(new Track(214121, "Run The Jewels", "Run The Jewels", "Run The Jewels", 
				"MPEG audio file", 210703, dateAdded, 5, playDate, "78YHFR4YU8H87FHQ",
				"file://localhost/C:/Music/Run%20The%20Jewels%20-%20Run%20The%20Jewels.mp3"));
		
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.MONTH, -20);
		dateAdded = calendarDate.getTime();
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.MONTH, -3);
		playDate = calendarDate.getTime();
		Track tvShow = new Track(10641, "The Clipshow Wherein Dante and Randal are Locked in the Freezer and Remember Some of the Great Moments in Their Lives", "Clerks", null, 
				"MPEG-4 video file", 1267680, dateAdded, 2, playDate, "264895B685E6A512",
				"file://localhost/Users/blevy/Music/iTunes/iTunes%20Media/Music/Clerks/Clerks%20-%20Episode%202.mp4");
		tvShow.setHasVideo(true);
		tvShow.setHd(false);
		tvShow.setTvShow(true);
		trackList.add(tvShow);
		
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.YEAR, -3);
		dateAdded = calendarDate.getTime();
		calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.MONTH, -26);
		playDate = calendarDate.getTime();
		Track movie = new Track(10641, "Conan checks in on his statue", null, null, 
				"MPEG-4 video file", 363240, dateAdded, 1, playDate, "4AC670F7D30097FF",
				"file://localhost/Users/blevy/Music/iTunes/iTunes%20Media/Videos/Conan%20checks%20in%20on%20his%20statue.mp4");
		movie.setMovie(true);
		trackList.add(movie);
		
		return trackList;
	}
	
	private int nonMusicTracks = 3;
	
	@SuppressWarnings("unused")
	private void printPlist(Plist plist) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Plist.class);
        Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal( plist, System.out );
	}

	@Test
	public void checkLibraryParsing() throws NumberFormatException, JAXBException, ParseException, SAXException, IOException {
		Library returnedLibrary = PlaylistService.getLibrary(new File("test/assets/Well_Formed.xml"));
		assertThat(returnedLibrary.getMajorVersion()).isEqualTo(1);
		assertThat(returnedLibrary.getMinorVersion()).isEqualTo(2);
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.clear();
        calendarDate.set(2013, 10, 28, 2, 17, 11);
		assertThat(returnedLibrary.getDate()).isEqualTo(calendarDate.getTime());
		assertThat(returnedLibrary.getApplicationVersion()).isEqualTo("11.1.3");
		assertThat(returnedLibrary.getFeatures()).isEqualTo(5);
		assertThat(returnedLibrary.isShowContentRatings()).isTrue();
		assertThat(returnedLibrary.getMusicFolder()).isEqualTo(("file://localhost/C:/Music/"));
		assertThat(returnedLibrary.getLibraryPersistentId()).isEqualTo("437026SJ3UJ3Y3T7");
	}	

	@Test
	public void checkTrackParsing() throws NumberFormatException, JAXBException, ParseException, SAXException, IOException {
		Library returnedLibrary = PlaylistService.getLibrary(new File("test/assets/Well_Formed.xml"));
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.clear();
		assertThat(returnedLibrary.getTracks().size()).isEqualTo(25);
		Track track = returnedLibrary.getTracks().get(10892);
		assertThat(track).isNotNull();
		assertThat(track.getName()).isEqualTo("World (Demo)");
		assertThat(track.getArtist()).isEqualTo("Foo Fighters");
		assertThat(track.getAlbum()).isEqualTo("Five Songs & A Cover");
		assertThat(track.getKind()).isEqualTo("MPEG audio file");
		assertThat(track.getTrackNumber()).isEqualTo(4);
		assertThat(track.getYear()).isEqualTo(2005);
		calendarDate.set(2010, 2, 21, 4, 50, 12);
		assertThat(track.getDateAdded()).isEqualTo(calendarDate.getTime());
		assertThat(track.getPlayCount()).isEqualTo(117);
		calendarDate.set(2013, 9, 16, 16, 8, 59);
		assertThat(track.getPlayDateUTC()).isEqualTo(calendarDate.getTime());
		assertThat(track.getSkipCount()).isEqualTo(3);
		assertThat(track.getPersistentID()).isEqualTo("023DDE089E93FEF0");
		assertThat(track.getTrackType()).isEqualTo("File");
		
		returnedLibrary = PlaylistService.getLibrary(new File("test/assets/Empty.xml"));
		assertThat(returnedLibrary.getTracks().size()).isEqualTo(0);
	}
	
	@Test
	public void checkPlaylistParsing() throws NumberFormatException, JAXBException, ParseException, SAXException, IOException {
		Library returnedLibrary = PlaylistService.getLibrary(new File("test/assets/Well_Formed.xml"));
		assertThat(returnedLibrary.getPlaylists()).isNotNull();
		assertThat(returnedLibrary.getPlaylists().size()).isEqualTo(2);
		Playlist firstPlaylist = returnedLibrary.getPlaylists().get(0);
		assertThat(firstPlaylist.getName()).isEqualTo("Regular Playlist");
		assertThat(firstPlaylist.getPlaylistId()).isEqualTo(41852);
		assertThat(firstPlaylist.getPersistentID()).isEqualTo("C7A5AD12589AC69B");
		assertThat(firstPlaylist.getMovies()).isNull();
		assertThat(firstPlaylist.isAllItems()).isEqualTo(true);
		assertThat(firstPlaylist.getPlaylistItems().size()).isEqualTo(22);
		assertThat(firstPlaylist.getPlaylistItems().get(0)).isNotNull();
	}
	
	@Test
	public void checkBadPlaylistXML() throws Exception {
		try {
			PlaylistService.getLibrary(new File("test/assets/Bad_Format.xml"));
			throw new Exception("Test should fail because of the format of the XML file.");
		} catch (UnmarshalException e) {
		}
	}

	@Test
	public void checkTrackOrdering() {
		List<Track> trackList = Track.getSortedTracksByCount((Collection<Track>) getTrackList(), TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), null);
		assertThat(trackList.get(0).getTrackId()).isEqualTo(8844);
		assertThat(trackList.get(1).getTrackId()).isEqualTo(19938);
		assertThat(trackList.get(2).getTrackId()).isEqualTo(15322);
		assertThat(trackList.get(3).getTrackId()).isEqualTo(12326);
		assertThat(trackList.get(trackList.size() - 1).getTrackId()).isEqualTo(54321);
		int count = 1;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Track track : trackList) {
			map.put(new Integer(track.getTrackId()), new Integer(count++));
		}
		assertThat(map.get(15322)).isLessThan(map.get(11716));
	}
	
	@Test
	public void checkTrackOrderingWithLimitByCount() {
		List<Track> trackList = getTrackList();
		int listSize = trackList.size();
		trackList = Track.getSortedTracksByCount((Collection<Track>) trackList, TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), listSize);
		assertThat(trackList.size()).describedAs("limit to " + (listSize - nonMusicTracks) + " tracks").isEqualTo(listSize - nonMusicTracks);

		assertThat(trackList.get(0).getTrackId()).isEqualTo(8844);
		assertThat(trackList.get(1).getTrackId()).isEqualTo(19938);
		assertThat(trackList.get(2).getTrackId()).isEqualTo(15322);
		assertThat(trackList.get(3).getTrackId()).isEqualTo(12326);
		assertThat(trackList.get(trackList.size() - 1).getTrackId()).describedAs("check the to last track").isEqualTo(54321);
		
		trackList = getTrackList();
		int initialCount = trackList.size();
		int limit = 100 + initialCount;
		trackList = Track.getSortedTracksByCount((Collection<Track>) getTrackList(), TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), limit);
		assertThat(trackList.size()).as("limit by more than available tracks").isEqualTo(initialCount - nonMusicTracks);
		
		trackList = getTrackList();
		trackList = Track.getSortedTracksByCount((Collection<Track>) getTrackList(), TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), 0);
		assertThat(trackList.size()).as("limit to 0 tracks").isEqualTo(0);
		
		trackList = getTrackList();
		trackList = Track.getSortedTracksByCount((Collection<Track>) getTrackList(), TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), null);
		assertThat(trackList.size()).as("limit with null track count").isEqualTo(initialCount - nonMusicTracks);
		
		trackList = getTrackList();
		trackList = Track.getSortedTracksByCount((Collection<Track>) getTrackList(), TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), -1);
		assertThat(trackList.size()).as("limit with negative track count").isEqualTo(0);
	}
	
	@Test
	public void checkTrackOrderingWithLimitByTime() {
		List<Track> trackList = getTrackList();
		int minutes = 22;
		trackList = Track.getSortedTracksByTime((Collection<Track>) trackList, TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), minutes);
		assertThat(trackList.size()).as("limit to " + minutes + " minutes").isEqualTo(3);
		trackList = getTrackList();
		minutes = 26;
		trackList = Track.getSortedTracksByTime((Collection<Track>) trackList, TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), minutes);
		assertThat(trackList.size()).as("limit to " + minutes + " minutes").isEqualTo(4);
		assertThat(trackList.get(0).getTrackId()).isEqualTo(8844);
		assertThat(trackList.get(1).getTrackId()).isEqualTo(19938);
		assertThat(trackList.get(2).getTrackId()).isEqualTo(15322);
		
		trackList = getTrackList();
		int initialCount = trackList.size();
		int limit = 60000;
		trackList = Track.getSortedTracksByTime((Collection<Track>) getTrackList(), TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), limit);
		assertThat(trackList.size()).as("limit to " + (limit / 60) + " hours").isEqualTo(initialCount - nonMusicTracks);
		assertThat(trackList.get(trackList.size() - 1).getTrackId()).isEqualTo(54321);
		
		trackList = getTrackList();
		trackList = Track.getSortedTracksByTime((Collection<Track>) getTrackList(), TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), 0);
		assertThat(trackList.size()).as("limit to 0 minutes").isEqualTo(0);
		
		trackList = getTrackList();
		trackList = Track.getSortedTracksByTime((Collection<Track>) getTrackList(), TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), null);
		assertThat(trackList.size()).as("limit with a null value for minutes").isEqualTo(initialCount - nonMusicTracks);
		
		trackList = getTrackList();
		trackList = Track.getSortedTracksByTime((Collection<Track>) getTrackList(), TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), -1);
		assertThat(trackList.size()).as("limit to a negative amount of minutes").isEqualTo(0);
	}

	@Test
	public void checkTrackOrderingWithLimitByCountAndTime() {
		List<Track> trackList = getTrackList();
		Integer listSize = trackList.size() - 3;
		Integer minutes = null;
		trackList = Track.getSortedTracksByCountAndTime((Collection<Track>) trackList, TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), listSize, minutes);
		assertThat(trackList.size()).describedAs("limit by count: " + listSize + ", time: " + minutes).isEqualTo(listSize);
		
		trackList = getTrackList();
		listSize = null;
		minutes = 26;
		trackList = Track.getSortedTracksByCountAndTime((Collection<Track>) trackList, TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), listSize, minutes);
		assertThat(trackList.size()).as("limit by count: " +  listSize + ", time: " + minutes).isEqualTo(4);
		
		trackList = getTrackList();
		listSize = 2;
		minutes = 26;
		trackList = Track.getSortedTracksByCountAndTime((Collection<Track>) trackList, TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), listSize, minutes);
		assertThat(trackList.size()).as("limit by count: " + listSize + ", time: " + minutes).isEqualTo(2);
		
		trackList = getTrackList();
		listSize = trackList.size();
		minutes = 26;
		trackList = Track.getSortedTracksByCountAndTime((Collection<Track>) trackList, TrackFilterType.MOST_OFTEN_PLAYED.getComparator(), listSize, minutes);
		assertThat(trackList.size()).as("limit by count: " + listSize + ", time: " + minutes).isEqualTo(4);
	}
	
	@Test
	public void checkGeneratingLibraryExportXML() throws NumberFormatException, SAXException, JAXBException, ParseException {
		File file = new File("test/assets/GeneratedLibrary.xml");
		Library returnedLibrary = PlaylistService.getLibrary(file);
		assertThat(returnedLibrary.getPlist()).isEqualTo(PlaylistService.getPlist(file));
	}

	@Test
	public void checkGeneratingPlaylistExportXML() throws NumberFormatException, SAXException, JAXBException, ParseException {
		Library returnedLibrary = PlaylistService.getLibrary(new File("test/assets/Well_Formed.xml"));
		File file = new File("test/assets/GeneratedPlaylist.xml");
		PlaylistLimit limit = new PlaylistLimit(3, null, null);
		assertThat(returnedLibrary.getCustomPlaylist(TrackFilterType.MOST_OFTEN_PLAYED, limit).getPlist())
			.isEqualTo(PlaylistService.getPlist(file));
	}

	@Test
	public void checkGeneratingM3U() throws IOException {
		List<Track> trackList = getTrackList();
		CollectionUtils.filter(trackList, Track.getMusicPredicate());
		String generatedM3U = Library.getM3U(trackList);
		String exampleM3U = FileUtils.readFileToString(new File("test/assets/Example.m3u"));
		assertThat(generatedM3U).isEqualTo(exampleM3U);
	}
}
