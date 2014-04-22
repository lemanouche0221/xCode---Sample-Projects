--
--  MHAppDelegate.applescript
--  Myriad Helpers
--
--  Created by Shane Stanley.
--  <sstanley@myriad-com.com.au>.
--  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>
--
-- A project to test the ObjectWithFords class and sundry categories.

script MHAppDelegate
	property parent : class "NSObject"
	property firstWindow : missing value
	property secondWindow : missing value
	
	on closeFirstWindow:sender
		firstWindow's orderOut:me
		secondWindow's orderFront:me
	end closeFirstWindow:
	
	on closeSecondWindow:sender
		secondWindow's orderOut:me
		firstWindow's orderFront:me
	end closeSecondWindow:
	
end script