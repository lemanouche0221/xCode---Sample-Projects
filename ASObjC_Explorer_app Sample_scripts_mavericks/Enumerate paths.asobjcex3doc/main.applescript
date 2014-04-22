-- Created 2013-10-14 16:05:02 +1100 by Shane Stanley
-- © 2013
use AppleScript version "2.3"
use scripting additions
use framework "Foundation"

-- Example of file manager directory enumeration
on getPathsIn:folderPath
	-- make URL
	set theURL to current application's NSURL's fileURLWithPath:folderPath
	-- make file manager
	set fm to current application's NSFileManager's new()
	-- get URL enumerator
	set theEnumerator to fm's enumeratorAtURL:theURL includingPropertiesForKeys:{} options:0 errorHandler:(missing value)
	repeat with i from 1 to 50 -- arbitrary number
		-- get next URL in enumerator
		set aURL to theEnumerator's nextObject()
		if aURL is missing value then exit repeat -- missing value means there are no more
		-- log the URL's path
		log ("Path " & i & (aURL's |path|() as text))
		-- or:
		-- current application's NSLog("Path %d: %@", i, aURL's |path|()) -- note %d required for integers with NSLog(), *not* %@
	end repeat
end getPathsIn: