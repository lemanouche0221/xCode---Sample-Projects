-- Created 2013-10-14 16:05:02 +1100 by Shane Stanley
-- © 2013
use AppleScript version "2.3"
use scripting additions
use framework "Foundation"

on insertItem:anItem atIndex:theIndex inList:theList
	set theList to current application's NSMutableArray's arrayWithArray:theList
	theList's insertObject:anItem atIndex:(theIndex - 1)
	return theList as list
end insertItem:atIndex:inList:

on deleteItemAtIndex:theIndex inList:theList
	set theList to current application's NSMutableArray's arrayWithArray:theList
	theList's removeObjectAtIndex:(theIndex - 1)
	return theList as list
end deleteItemAtIndex:inList:

on deleteItemsAtIndexes:theIndexes inList:theList
	set theList to current application's NSMutableArray's arrayWithArray:theList
	set theSet to current application's NSMutableIndexSet's indexSet()
	repeat with anIndex in theIndexes
		(theSet's addIndex:(anIndex - 1))
	end repeat
	theList's removeObjectsAtIndexes:theSet
	return theList as list
end deleteItemsAtIndexes:inList: