--
--  MSAppDelegate.applescript
--  Notification Center Test
--
--  Created by Donald Southard on 8/14/12.
--  Copyright (c) 2012 MacStories. All rights reserved.
--

script MSAppDelegate
	property parent : class "NSObject"
	
	-- this property will be used to configure our notification
	property myNotification : missing value
	
	on applicationWillFinishLaunching_(aNotification)
		-- send a notification to indicate the application has started, then quit
		sendNotificationWithTitle_AndMessage_("MacStories Notification","hello world!")
		quit
	end applicationWillFinishLaunching_
	
	on applicationShouldTerminate_(sender)
		-- Insert code here to do any housekeeping before your application quits 
		return current application's NSTerminateNow
	end applicationShouldTerminate_
	
	-- Method for sending a notification based on supplied title and text
	on sendNotificationWithTitle_AndMessage_(aTitle, aMessage)
		set myNotification to current application's NSUserNotification's alloc()'s init()
		set myNotification's title to aTitle
		set myNotification's informativeText to aMessage
		current application's NSUserNotificationCenter's defaultUserNotificationCenter's deliverNotification_(myNotification)
	end sendNotification
	
end script