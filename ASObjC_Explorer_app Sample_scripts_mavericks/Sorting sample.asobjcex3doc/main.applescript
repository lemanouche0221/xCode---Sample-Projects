-- Created 2013-10-14 16:02:30 +1100 by Shane Stanley
-- © 2013
use AppleScript version "2.3"
use scripting additions
use framework "Foundation"

on sortAList:aList
	set anArray to current application's NSArray's arrayWithArray:aList
	return (anArray's sortedArrayUsingSelector:"compare:") as list
end sortAList:

on arrange in order aList -- simple sort with terminology
	set anArray to current application's NSArray's arrayWithArray:aList
	return (anArray's sortedArrayUsingSelector:"compare:") as list
end arrange in order

on order text list aList case relevant caseFlag -- text sort with case option
	try
		set anArray to current application's NSArray's arrayWithArray:aList -- make array
		if caseFlag then
			set anArray to anArray's sortedArrayUsingSelector:"localizedCompare:"
		else
			set anArray to anArray's sortedArrayUsingSelector:"localizedCaseInsensitiveCompare:"
		end if
		return anArray as list
	on error
		return {} -- an empty array 
	end try
end order text list

on arrange records recordList by property theKey ascending order theOrder -- record sort
	set anArray to current application's NSArray's arrayWithArray:recordList -- make array
	set aDescriptor to current application's NSSortDescriptor's sortDescriptorWithKey:theKey ascending:theOrder -- make a sort descriptor
	return (anArray's sortedArrayUsingDescriptors:{aDescriptor}) as list
end arrange records