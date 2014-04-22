package services;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import play.Logger;
import play.Play;

import com.apple.itunes.Array;
import com.apple.itunes.Dict;
import com.apple.itunes.False;
import com.apple.itunes.Plist;
import com.apple.itunes.True;

import domain.Library;
import domain.Playlist;
import domain.Track;

public class PlaylistService {
	public static Library parseXMLFile(File file) throws NumberFormatException, JAXBException, ParseException, SAXException, IOException {
		Library library = getLibrary(file);
		Logger.debug(library.toString());
		return library;
	}
	private static Map<String, String> getKeysAndStringValues(Dict dict) {
		Map<String, String> keyMap = new LinkedHashMap<String, String>();
		String key = null;
        for (Object o : dict.getDictOrArrayOrData()) {
        	if (o instanceof JAXBElement) {
        		JAXBElement<?> element = (JAXBElement<?>) o;
        		if (key != null) {
        			keyMap.put(key, element.getValue().toString());
        			key = null;
        		}
        		else if (element.getName().getLocalPart().equals("key")) {
    				key = element.getValue().toString();
    			}
        	}
        	else if (o instanceof True || o instanceof False) {
        		keyMap.put(key, o instanceof True ? "true" : "false");
        		key = null;
        	}
        	else
        		key = null;
        }
		return keyMap;
	}
	private static Map<String, byte[]> getKeysAndByteValues(Dict dict) {
		Map<String, byte[]> keyMap = new LinkedHashMap<String, byte[]>();
		String key = null;
        for (Object o : dict.getDictOrArrayOrData()) {
        	if (o instanceof JAXBElement) {
        		JAXBElement<?> element = (JAXBElement<?>) o;
        		if (key != null) {
        			if (element.getName().getLocalPart().equals("data"))
        				keyMap.put(key, ((byte[])element.getValue()));
        			key = null;
        		}
        		else if (element.getName().getLocalPart().equals("key")) {
    				key = element.getValue().toString();
    			}
        	}
        	else
        		key = null;
        }
		return keyMap;
	}
	public static Plist getPlist(File file) throws SAXException, JAXBException, NumberFormatException, ParseException {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("conf"+File.separator+"iTunes.xsd"));
		JAXBContext jc = JAXBContext.newInstance(Plist.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		unmarshaller.setSchema(schema);
		Plist plist = (Plist) unmarshaller.unmarshal(file);
		return plist;
	}
	public static Plist getPlistWithLocalDTD(File file) throws SAXException, JAXBException, NumberFormatException, ParseException, IOException {
		String xml = FileUtils.readFileToString(file);
		String dtdFile = "PropertyList-1.0.dtd";
		xml = xml.replace("http://www.apple.com/DTDs/" + dtdFile, 
				"file://" + Play.application().path() + File.separator + "conf" + File.separator  + dtdFile);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File("conf"+File.separator+"iTunes.xsd"));
		JAXBContext jc = JAXBContext.newInstance(Plist.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		unmarshaller.setSchema(schema);
		Plist plist = (Plist) unmarshaller.unmarshal((new StreamSource(new StringReader(xml))));
		return plist;
	}
	public static Library getLibrary(File file) throws SAXException, JAXBException, NumberFormatException, ParseException {
		Plist plist = null;
		try {
			plist = getPlist(file);
		}
		catch (UnmarshalException e) {
			if (e.getCause() != null && 
				(e.getCause().getMessage().equals("www.apple.com") || 
				e.getCause().getMessage().equals("Network is down"))) {
				try {
					plist = getPlistWithLocalDTD(file);
				} catch (IOException ex) {
					Logger.error(ex.getMessage(), ex);
					throw new ParseException(ex.getMessage(), 0);
				}
        	}
			else
				throw e;
        }
        Library library = new Library(getKeysAndStringValues(plist.getDict()));
		library.setTracks(getTracks(plist.getDict()));
		library.getPlaylists().addAll(getPlaylists(plist.getDict()));
		return library;
	}
	private static Object getKeyElement(String key, Dict dict) {
		boolean foundElement = false;
		for (Object o : dict.getDictOrArrayOrData()) {
			if (foundElement) {
				return o;
			}
			if (o instanceof JAXBElement<?>) {
				JAXBElement<?> element = (JAXBElement<?>) o;
				if (element.getName().getLocalPart().equals("key") && element.getValue().toString().equals(key)) {
					foundElement = true;
				}
			}
		}
		return null;
	}
	private static Map<Integer, Track> getTracks(Dict dict) throws NumberFormatException, ParseException {
		Map<Integer, Track> trackMap = new LinkedHashMap <Integer, Track>();
		Object o = getKeyElement(Library.TRACKS, dict);
		if (o instanceof Dict) {
			for (Object element : ((Dict) o).getDictOrArrayOrData()) {
				if (element instanceof Dict) {
					Map<String, String> map = getKeysAndStringValues((Dict) element);
					trackMap.put(Integer.parseInt(map.get(Track.TRACK_ID)), new Track(map));
				}
			}
		}
		return trackMap;
	}
	private static List<Playlist> getPlaylists(Dict dict) {
		List<Playlist> playlists = new LinkedList<Playlist>();
		Object o = getKeyElement(Library.PLAYLISTS, dict);
		if (o instanceof Array) {
			for (Dict dictElement : ((Array) o).getDict()) {
				Map<String, String> stringMap = getKeysAndStringValues(dictElement);
				Playlist playlist = new Playlist(stringMap);
				List<Integer> trackList = new LinkedList<Integer>();
				Object arrObj = getKeyElement(Playlist.PLAYLIST_ITEMS, dictElement);
				if (arrObj instanceof Array) {
					Iterator<Dict> iter = ((Array)arrObj).getDict().iterator();
					while (iter.hasNext()) {
						Object trackObj = getKeyElement(Track.TRACK_ID, iter.next());
						if (trackObj instanceof JAXBElement<?>)
							trackList.add(new Integer(((JAXBElement<?>) trackObj).getValue().toString()));
					}
				}
				playlist.getPlaylistItems().addAll(trackList);
				Map<String, byte[]> byteMap = getKeysAndByteValues(dictElement);
				playlist.setSmartInfo(byteMap.get(Playlist.SMART_INFO));
				playlist.setSmartCriteria(byteMap.get(Playlist.SMART_CRITERIA));
				playlists.add(playlist);
			}
		}
		return playlists;
	}
}
