--
--  AppDelegate.applescript
--  HelloWorld
--
--  Created by Ben Waldie on 7/30/12.
--  Copyright (c) 2012 Ben Waldie. All rights reserved.
--

script AppDelegate
	property parent : class "NSObject"
	
	property theName : ""
	
	on buttonClicked_(sender)
		display alert "Hello there " & theName
	end buttonClicked_
	
	on applicationWillFinishLaunching_(aNotification)
		-- Insert code here to initialize your application before any files are opened 
	end applicationWillFinishLaunching_
	
	on applicationShouldTerminate_(sender)
		-- Insert code here to do any housekeeping before your application quits 
		return current application's NSTerminateNow
	end applicationShouldTerminate_
	
end script