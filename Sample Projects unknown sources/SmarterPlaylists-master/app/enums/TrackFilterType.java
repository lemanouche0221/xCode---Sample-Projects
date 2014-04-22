package enums;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import domain.Track;

public enum TrackFilterType {
	MOST_OFTEN_PLAYED("mostoftenplayed", new Track.MostOftenPlayedComparator());
	private String code;
	private Comparator<Track> comparator;
	
	private static final String messagePrefix = "filter.label.";
	
	private TrackFilterType (String code, Comparator<Track> comparator) {
		this.code = code;
		this.comparator = comparator;
	}
	
	public static TrackFilterType get(String code) {
		for (TrackFilterType type : values())
			if (type.getCode().equals(code))
				return type;
		return null;
	}

	public String getCode() {
		return code;
	}

	public Comparator<Track> getComparator() {
		return comparator;
	}
	
	public String getMessageCode() {
		return messagePrefix + code;
	}
	
	public static List<String> getCodes() {
		List<String> codeList = new ArrayList<String>();
		for (TrackFilterType type : values())
			codeList.add(type.getCode());
		return codeList;
	}
}
