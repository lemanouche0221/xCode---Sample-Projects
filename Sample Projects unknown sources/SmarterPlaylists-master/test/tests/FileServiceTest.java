package tests;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.xml.sax.SAXException;

import domain.PlaylistLimit;

import services.FileService;
import enums.TrackFilterType;

public class FileServiceTest {
	private Map<String, PlaylistLimit> getCodeMap(String count){
		Map<String, PlaylistLimit> codeMap = new LinkedHashMap<String, PlaylistLimit>();
		for (String code : TrackFilterType.getCodes()) {
			codeMap.put(code, new PlaylistLimit(count, null, null));
		}
		return codeMap;
	}
	
	@Test
	public void checkLibraryParsingToM3u() throws NumberFormatException, JAXBException, ParseException, SAXException, IOException {
		running(fakeApplication(), new Runnable() {
			public void run() {
				File file = new File("test/assets/Well_Formed.xml");
				try {
					FileService.createTempM3uPlaylistFiles(file, getCodeMap("5"), "uuid_1");
					FileService.createTempM3uPlaylistFiles(file, getCodeMap(""), "uuid_2");
					FileService.createTempM3uPlaylistFiles(file, getCodeMap(" 5"), "uuid_3");
					FileService.createTempM3uPlaylistFiles(file, getCodeMap("   "), "uuid_4");
					FileService.createTempM3uPlaylistFiles(file, getCodeMap("0"), "uuid_5");
					FileService.createTempM3uPlaylistFiles(file, getCodeMap("-1"), "uuid_6");
					FileService.createTempM3uPlaylistFiles(file, getCodeMap((Integer.MAX_VALUE + 1) + ""), "uuid_7");
					
					FileService.createTempM3uPlaylistFiles(file, getCodeMap("5"), "uuid_1");
					for (String code : TrackFilterType.getCodes()) {
						File output = new File(FileService.M3U_TEMP_DIRECTORY + "uuid_1" + File.separator + 
								code + FileService.M3U_EXTENSION);
						assertThat(output.exists()).isTrue();
					}
				} catch (Exception e) {
					e.printStackTrace();
					assertThat(e).isNull();
				}
			}
		});
	}
	
	@Test
	public void checkLibraryParsingToXml() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				File file = new File("test/assets/Well_Formed.xml");
				try {
					FileService.createTempXmlPlaylistFiles(file, getCodeMap("5"), "uuid_1");
					FileService.createTempXmlPlaylistFiles(file, getCodeMap(""), "uuid_2");
					FileService.createTempXmlPlaylistFiles(file, getCodeMap(" 5"), "uuid_3");
					FileService.createTempXmlPlaylistFiles(file, getCodeMap("   "), "uuid_4");
					FileService.createTempXmlPlaylistFiles(file, getCodeMap("0"), "uuid_5");
					FileService.createTempXmlPlaylistFiles(file, getCodeMap("-1"), "uuid_6");
					FileService.createTempXmlPlaylistFiles(file, getCodeMap((Integer.MAX_VALUE + 1) + ""), "uuid_7");
					
					FileService.createTempXmlPlaylistFiles(file, getCodeMap("5"), "uuid_1");
					for (String code : TrackFilterType.getCodes()) {
						File output = new File(FileService.XML_TEMP_DIRECTORY + "uuid_1" + File.separator + 
								code + FileService.XML_EXTENSION);
						assertThat(output.exists()).isTrue();
					}
				} catch (Exception e) {
					e.printStackTrace();
					assertThat(e).isNull();
				}
			}
		});
	}
	
	@Test
	public void checkTempM3uPlaylistFilesExist() throws NumberFormatException, JAXBException, ParseException, SAXException, IOException {
		File file = new File("test/assets/Well_Formed.xml");
		String uuid = "uuid_1";
		FileService.createTempM3uPlaylistFiles(file, getCodeMap("5"), uuid);
		List<String> downloadFiles = new ArrayList<String>();
		for (String code : TrackFilterType.getCodes()) {
			downloadFiles.add(code + FileService.M3U_EXTENSION);
		}
		assertThat(FileService.getTempM3uPlaylistFiles(uuid)).isEqualTo(downloadFiles);
	}
	
	@Test
	public void checkTempXmlPlaylistFilesExist() throws NumberFormatException, JAXBException, ParseException, SAXException, IOException {
		File file = new File("test/assets/Well_Formed.xml");
		String uuid = "uuid_1";
		FileService.createTempXmlPlaylistFiles(file, getCodeMap("5"), uuid);
		List<String> downloadFiles = new ArrayList<String>();
		for (String code : TrackFilterType.getCodes()) {
			downloadFiles.add(code + FileService.XML_EXTENSION);
		}
		assertThat(FileService.getTempXmlPlaylistFiles(uuid)).isEqualTo(downloadFiles);
	}
	
	@Test
	public void checkTempM3uPlaylistFilesDeleted() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				File file = new File("test/assets/Well_Formed.xml");
				String uuid = "uuid_1";
				try {
					FileService.createTempM3uPlaylistFiles(file, getCodeMap("5"), uuid);
					FileService.deleteTempPlaylistFiles("uuid_1");
					File dir = new File(FileService.M3U_TEMP_DIRECTORY + uuid);
					assertThat(dir.exists()).isFalse();
				} catch (Exception e) {
					e.printStackTrace();
					assertThat(e).isNull();
				}
			}
		});
	}
	
	@Test
	public void checkTempXmlPlaylistFilesDeleted() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				File file = new File("test/assets/Well_Formed.xml");
				String uuid = "uuid_1";
				try {
					FileService.createTempM3uPlaylistFiles(file, getCodeMap("5"), uuid);
					FileService.deleteTempPlaylistFiles("uuid_1");
					File dir = new File(FileService.XML_TEMP_DIRECTORY + uuid);
					assertThat(dir.exists()).isFalse();
				} catch (Exception e) {
					e.printStackTrace();
					assertThat(e).isNull();
				}
			}
		});
	}

	@After
	public void removeTempDirectories() throws IOException {
		for (int i = 1; i < 8; i++) {
			FileUtils.deleteDirectory(new File(FileService.M3U_TEMP_DIRECTORY + "uuid_" + i + File.separator));
			FileUtils.deleteDirectory(new File(FileService.XML_TEMP_DIRECTORY + "uuid_" + i + File.separator));
		}
	}
}
