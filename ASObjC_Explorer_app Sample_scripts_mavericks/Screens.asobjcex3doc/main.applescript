-- Created 2013-10-14 16:05:02 +1100 by Shane Stanley
-- © 2013
use AppleScript version "2.3"
use scripting additions
use framework "AppKit" -- for NSScreen

on mainScreenSize()
	return (current application's NSScreen's mainScreen()'s visibleFrame()'s |size|()) as list
end mainScreenSize

on primaryScreenSize()
	return ((current application's NSScreen's screens()'s objectAtIndex:0)'s visibleFrame()'s |size|()) as list
end primaryScreenSize

on main screen dimensions
	return (current application's NSScreen's mainScreen()'s visibleFrame()'s |size|()) as list
end main screen dimensions

on primary screen dimensions
	return ((current application's NSScreen's screens()'s objectAtIndex:0)'s visibleFrame()'s |size|()) as list
end primary screen dimensions

on all screen dimensions
	set theSizes to {}
	set theScreens to (current application's NSScreen's screens()) as list
	repeat with aScreen in theScreens
		set end of theSizes to (aScreen's visibleFrame()'s |size|()) as list
	end repeat
	return theSizes
end all screen dimensions