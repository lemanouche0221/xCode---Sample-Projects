-- AlertTest.applescript

--  Created by Shane Stanley.
--  <sstanley@myriad-com.com.au>.
--  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>

-- sample code using NSAlert+MyriadHelpers category

script AlertTest
	property parent : class "NSObject"
	property mainWindow : missing value -- connect in Interface Builder
	
	-- shows NSAlert as a sheet over window mainWindow	
	on showAlert:sender
		-- create an alert
		set theAlert to current application's NSAlert's makeAlert:"An alert" buttons:{"Cancel", "OK"} |text|:"Further explanation"
		theAlert's showOver:mainWindow calling:{"alertDone:", me} -- selector takes one argument, the name of the button
	end showAlert:
	
	on alertDone:theResult -- selector called when alert closed
		log theResult -- Name of button pressed
	end alertDone:
	
	
	-- shows modal alert
	on showAlertModal:sender
		-- create an alert
		set theAlert to current application's NSAlert's makeAlert:"An alert" buttons:{"Cancel", "OK"} |text|:"Further explanation"
		theAlert's showModal() -- returns name of the button
		log result
	end showAlertModal:
	
	-- shows NSAlert as a sheet over window mainWindow giving up after 5 seconds
	on showExpiringAlert:sender
		-- create an alert
		set theAlert to current application's NSAlert's makeAlert:"An alert" buttons:{"Cancel", "OK"} |text|:"Further explanation"
		theAlert's showOver:mainWindow calling:{"expiringAlertDone:", me} wait:5 -- selector takes one argument, the name of the button
	end showExpiringAlert:
	
	on expiringAlertDone:theResult -- selector called when alert closed
		log theResult -- Name of button pressed, or "Gave up"
	end expiringAlertDone:
	
	
	-- shows modal alert giving up after 5 seconds
	on showExpiringAlertModal:sender
		-- create an alert
		set theAlert to current application's NSAlert's makeAlert:"An alert" buttons:{"Cancel", "OK"} |text|:"Further explanation"
		theAlert's showModalWait:5 -- returns name of the button, or "Gave up"
		log result
	end showExpiringAlertModal:
	
end script
