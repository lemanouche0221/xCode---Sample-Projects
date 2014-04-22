package services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import play.Logger;
import play.Play;

import com.apple.itunes.Plist;

import domain.Library;
import domain.PlaylistLimit;
import domain.Track;
import enums.TrackFilterType;

public class FileService {
	public static final String M3U_TEMP_DIRECTORY = Play.application().path() + File.separator + "tmp" + File.separator + "m3u" + File.separator;
	public static final String M3U_EXTENSION = ".m3u";
	public static final String XML_TEMP_DIRECTORY = Play.application().path() + File.separator + "tmp" + File.separator + "xml" + File.separator;
	public static final String XML_EXTENSION = ".xml";

	public static void createTempM3uPlaylistFiles(File file, Map<String, PlaylistLimit> codeMap, String uuid) throws NumberFormatException, 
		JAXBException, ParseException, SAXException, IOException {
		Library library = PlaylistService.parseXMLFile(file);
		for (Map.Entry<String, PlaylistLimit> entry : codeMap.entrySet()) {
			TrackFilterType filter = TrackFilterType.get(entry.getKey());
			Integer count = entry.getValue().getCount();
			List<Track> trackList = Track.getSortedTracksByCount(library.getTracks().values(), filter.getComparator(), count);
			String m3uContent = Library.getM3U(trackList);
			String fileName = M3U_TEMP_DIRECTORY + uuid + File.separator + entry.getKey()  + M3U_EXTENSION;
			FileUtils.writeStringToFile(new File(fileName), m3uContent, "UTF-8");
			Logger.debug("Created: " + fileName);
		}
	}

	public static void createTempXmlPlaylistFiles(File file, Map<String, PlaylistLimit> codeMap, String uuid) throws 
	IOException, NumberFormatException, JAXBException, ParseException, SAXException {
		Library library = PlaylistService.parseXMLFile(file);
		for (Map.Entry<String, PlaylistLimit> entry : codeMap.entrySet()) {
			TrackFilterType filter = TrackFilterType.get(entry.getKey());
			Plist exportPlist = library.getCustomPlaylist(filter, entry.getValue()).getPlist();
			new File(XML_TEMP_DIRECTORY + uuid + File.separator).mkdirs();
			String fileName = XML_TEMP_DIRECTORY + uuid + File.separator + entry.getKey()  + XML_EXTENSION;
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8"));			
			try {
				JAXBContext context = JAXBContext.newInstance(Plist.class);
				Marshaller marshaller = context.createMarshaller();
				marshaller.marshal(exportPlist, out);
				Logger.debug("Created: " + fileName);
			}
			catch (JAXBException e) {
				Logger.error("Error occured while unmarshalling generated playlist.", e);
				throw e;
			}
			finally {
				out.close();
			}
		}
	}

	public static List<String> getTempM3uPlaylistFiles(String uuid) {
		return getTempPlaylistFiles(M3U_TEMP_DIRECTORY, uuid, M3U_EXTENSION);
	}
	
	public static List<String> getTempXmlPlaylistFiles(String uuid) {
		return getTempPlaylistFiles(XML_TEMP_DIRECTORY, uuid, XML_EXTENSION);
	}

	private static List<String> getTempPlaylistFiles(String directory, String uuid, final String extension) {
		File downloadDir = new File(directory + uuid + File.separator);
		List<String> files = new ArrayList<String>();
		for (File file : downloadDir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(extension);
			}
		})) {
			if (file.isFile())
				files.add(file.getName());
		}
		return files;
	}

	public static File getTempM3uPlaylistFile(String uuid, String file) {
		return new File(M3U_TEMP_DIRECTORY + uuid + File.separator + file);
	}
	
	public static File getTempXmlPlaylistFile(String uuid, String file) {
		return new File(XML_TEMP_DIRECTORY + uuid + File.separator + file);
	}

	public static void deleteTempPlaylistFiles(String uuid) throws IOException {
		File dir = new File(M3U_TEMP_DIRECTORY + uuid + File.separator);
		FileUtils.deleteDirectory(dir);
		dir = new File(XML_TEMP_DIRECTORY + uuid + File.separator);
		FileUtils.deleteDirectory(dir);
	}
}
