-- ObjectWithFordsTests.applescript

--  Created by Shane Stanley.
--  <sstanley@myriad-com.com.au>.
--  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>

-- sample code using ObjectWithFords superclass

script ObjectWithFordsTests
	property parent : class "ObjectWithFords"
	property imageView : missing value
	
	on classWithClassStuff:sender
		-- simple use of ford: to make NS equivalents of text, lists and records
		log "## Starting classWithClassStuff_##"
		-- use to make NSString from text
		set theNSString to my ford:"Some text" -- easier than calling stringWithString: on NSString
		log theNSString's uppercaseString() -- instance method works so it worked
		log "##"
		log "##"
		
		-- convert list to array
		set theList to {"M", "B", "Z", "Q", "A"}
		set theList to my ford:theList -- easier than calling arrayWithArray: on NSArray
		log (theList's sortedArrayUsingSelector:"compare:") -- instance method works so it worked
		log "##"
		log "##"
		
		-- convert record to dictionary
		set theRec to {firstName:"Fred", age:16}
		set theRec to my ford:theRec -- easier than calling dictionaryWithDictionary: on NSDictionary
		log (theRec's allKeys()) -- instance method works so it worked
		
	end classWithClassStuff:
	
	on dateStuff:sender
		log "## Starting dateStuff_##"
		-- make date from string; doing it the standard AS way fails in AppleScriptObjC
		set theStrings to {"2001-12-01 00:00:00", "2001-14-01 00:00:00", "2001/10/03", "blah 2010-10//3, 11;59,30 whatever"} -- order matters, delimiter doesn't
		repeat with aString in theStrings
			set theDate to (my fordDateString:aString)
			log aString & " becomes " & ((theDate as date) as text)
		end repeat
		log "##"
		log "##"
		
		-- convert AS date to NSDate
		set thisDate to current date
		log "Current date is " & (thisDate as text)
		set theNSDate to my ford:thisDate
		log theNSDate -- NSDate
		log theNSDate's timeIntervalSince1970() -- instance method works so it worked
		
		-- convert NSDate to AS date
		set aDate to ((my ford:theNSDate) as date) -- still need coercion on return
		log "AS date is " & (aDate as text) -- OK
		log "##"
		log "##"
	end dateStuff:
	
	on fileStuff:sender
		log "## Starting fileStuff_##"
		-- theClass_ method returns a class; easier (and clearer?) than "current application's NSString"
		set theURL to my (theClass_("NSURL")'s fileURLWithPath:"/blah /blah/more/blah/")
		log theURL
		log theURL's |class|() -- NSURL
		log "##"
		log "##"
		
		-- use ford_ to convert NSURL to HFS path
		set hfsPath to my ford:theURL
		log (hfsPath as text) -- hfs path
		log "##"
		log "##"
		
		-- convert from alias to NSURL
		set anAlias to "Macintosh HD:" as alias -- change to suit
		log (my ford:anAlias) -- NSURL
		log "##"
		log "##"
		
		-- convert from file to NSURL
		set theFile to choose file name
		log "AS path is " & (theFile as text)
		log (my ford:theFile) -- NSURL
	end fileStuff:
	
	on deepFordStuff:sender
		-- deepFord: does all that ford: does, but for lists/arrays and records/dictionaries it also converts any contained dates/files/aliases/data
		set someAlias to choose file with prompt "Choose any file"
		log "## Starting deepFordStuff_##"
		-- convert a list and its contents		
		set aList to {someAlias, current date}
		log (my ford:aList) -- shows array of NSAppleEventDescriptors
		log (my fordDeep:aList) -- shows array of NSURL and NSDate
		log "##"
		log "##"
		
		-- convert a record and its contents		
		set aRecord to {firstItem:someAlias, secondItem:(current date)}
		log (my ford:aRecord) -- shows dictionary with NSAppleEventDescriptors
		log (my fordDeep:aRecord) -- shows dictionary with NSURL and NSDate
		log "##"
		log "##"
		
		-- convert list of arrays and contents		
		set aList to {{firstItem:someAlias, secondItem:(current date)}, {firstItem:someAlias, secondItem:(current date)}, {firstItem:someAlias, secondItem:(current date)}, {firstItem:someAlias, secondItem:(current date)}, {firstItem:someAlias, secondItem:(current date)}}
		log (my ford:aList) -- shows NSAppleEventDescriptors
		log (my fordDeep:aList) -- shows NSURLs and NSDates
	end deepFordStuff:
	
	on dataFromiTunes:sender
		log "## Starting dataFromiTunes_##"
		-- convert data to NSData; gets data for album cover from iTunes
		## iTunes must be running and a track playing or paused
		tell application "iTunes"
			set myTrack to (current track)
			set theArts to artworks of myTrack
			if theArts = {} then
				--do nothing
				return
			else
				set theArts to item 1 of theArts
				set imageData to raw data of theArts -- get the data
			end if
		end tell
		
		set theData to my ford:imageData -- convert it to NSData
		set theImage to current application's NSImage's alloc()'s initWithData:theData -- turn it into an NSImage
		imageView's setImage:theImage -- put it in the image well
	end dataFromiTunes:
	
	on trigStuff:sender
		log "tan 30 degrees is: " & (my fordTrig:{"tan", 30, true})
		log "sin 30 degrees is: " & (my fordTrig:{"sin", 30, true})
		log "sin 60 degrees is: " & (my fordTrig:{"sin", 60, true})
		log "cos 30 degrees is: " & (my fordTrig:{"cos", 30, true})
		log "atan 30 degrees is: " & (my fordTrig:{"atan", 30, true})
		log "asin 30 degrees is: " & (my fordTrig:{"asin", 30, true})
		log "acos 30 degrees is: " & (my fordTrig:{"acos", 30, true})
		log "sin 1 radian is: " & (my fordTrig:{"sin", 1, false})
		log "log 100 is: " & (my fordTrig:{"log", 100, false})
		log "log10 100 is: " & (my fordTrig:{"log10", 100, false})
		
		
	end trigStuff:
	
end script
