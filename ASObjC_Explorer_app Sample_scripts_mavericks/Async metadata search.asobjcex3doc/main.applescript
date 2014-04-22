-- Created 2013-10-14 16:05:02 +1100 by Shane Stanley
-- © 2013
use AppleScript version "2.3"
use scripting additions
use framework "Foundation"
use framework "AppKit" -- for NSMetadataQuery

-- Uses performSelectorOnMainThread::: to make sure query happens on main thread.

property theSender : missing value -- stores the calling script

on doASearchFor:aString telling:sender
	set my theSender to sender -- store for later
	-- Build the search predicate
	set pred to current application's NSPredicate's predicateWithFormat:("kMDItemTextContent == '" & aString & "'")
	-- make the query
	set theQuery to current application's NSMetadataQuery's alloc()'s init()
	theQuery's setPredicate:pred
	-- set its search scope to the computer
	theQuery's setSearchScopes:{current application's NSMetadataQueryLocalComputerScope}
	-- tell the notification center to tell us when it's done
	set notifCenter to current application's NSNotificationCenter's defaultCenter()
	notifCenter's addObserver:me selector:"initialGatherDone:" |name|:(current application's NSMetadataQueryDidFinishGatheringNotification) object:theQuery
	-- start searching  
	theQuery's performSelectorOnMainThread:"startQuery" withObject:(missing value) waitUntilDone:false
	-- something for the Result window
	return "Searching"
end doASearchFor:telling:

on initialGatherDone:notification
	-- stop the query
	set theQuery to notification's object()
	theQuery's stopQuery()
	set theCount to theQuery's resultCount() as integer
	-- we can get what attributes we want here, and call back to the calling script
	theSender's showDialog:theCount
end initialGatherDone: