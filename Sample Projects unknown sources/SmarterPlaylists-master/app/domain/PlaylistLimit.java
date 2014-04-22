package domain;

import org.apache.commons.lang3.StringUtils;

public class PlaylistLimit {
	private Integer count;
	public static final String countSuffix = "_count";
	private Integer hours;
	public static final String hoursSuffix = "_hours";
	private Integer minutes;
	public static final String minutesSuffix = "_minutes";
	
	public PlaylistLimit(Integer count, Integer hours, Integer minutes) {
		this.count = count;
		this.hours = hours;
		this.minutes = minutes;
	}
	
	public PlaylistLimit(String count, String hours, String minutes) {
		this.count = getIntegerValue(count);
		this.hours = getIntegerValue(hours);
		this.minutes = getIntegerValue(minutes);
	}
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	public Integer getTotalMinutes() {
		if (this.hours == null && this.minutes == null)
			return null;
		else if (this.hours == null)
			return this.minutes;
		else if (this.minutes == null)
			return this.hours * 60;
		else
			return (this.hours * 60) + this.minutes;
	}
	
	private Integer getIntegerValue(String value) {
		return StringUtils.isEmpty(value) || StringUtils.isEmpty(value.trim()) ? null : new Integer(value.trim());
	}
}
