-- Created 2013-10-14 16:05:02 +1100 by Shane Stanley
-- © 2013
use AppleScript version "2.3"
use scripting additions
use framework "Foundation"

-- *** This only works using Run & Log, not Run. Do not compile or run while waiting for dialog.
-- The exported lib will obviously only work with stay-open applets.

on callLater:sender
	my performSelector:"doItNow:" withObject:sender afterDelay:2 -- will show dialog after 2 seconds
end callLater:

on doItNow:sender
	sender's showDialog:"Hello World"
end doItNow: