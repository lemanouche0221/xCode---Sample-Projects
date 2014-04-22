package tests;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import domain.PlaylistLimit;

public class PlaylistLimitTest {

	@Test
	public void checkPlaylistLimitsFromStrings() {
		PlaylistLimit limit = new PlaylistLimit("", "", "");
		assertThat(limit.getCount()).as("Empty string for count").isNull();
		assertThat(limit.getHours()).as("Empty string for hours").isNull();
		assertThat(limit.getMinutes()).as("Empty string for minutes").isNull();
		limit = new PlaylistLimit(" 1", " 2 ", "3 ");
		assertThat(limit.getCount()).as("Number with leading space").isEqualTo(1);
		assertThat(limit.getHours()).as("Number with padding on both sides").isEqualTo(2);
		assertThat(limit.getMinutes()).as("Number with trailing space").isEqualTo(3);
	}

	@Test
	public void checkPlaylistLimitsFromIntegers() {
		PlaylistLimit limit = new PlaylistLimit(100, 1, 30);
		assertThat(limit.getCount()).as("Count as integer").isEqualTo(100);
		assertThat(limit.getHours()).as("Hours as integer").isEqualTo(1);
		assertThat(limit.getMinutes()).as("Minutes as integer").isEqualTo(30);
		assertThat(limit.getTotalMinutes()).as("Total minutes with hours and mins set").isEqualTo(90);
		limit = new PlaylistLimit(null, null, 45);
		assertThat(limit.getCount()).isNull();
		assertThat(limit.getHours()).isNull();
		assertThat(limit.getTotalMinutes()).as("Total minutes when hours are null").isEqualTo(45);
		limit = new PlaylistLimit(null, 2, null);
		assertThat(limit.getMinutes()).isNull();
		assertThat(limit.getTotalMinutes()).as("Total minutes when minutes are null").isEqualTo(120);
		limit = new PlaylistLimit(30, 3, null);
		assertThat(limit.getTotalMinutes()).isEqualTo(180);
		assertThat(limit.getCount()).isEqualTo(30);
	}
}
