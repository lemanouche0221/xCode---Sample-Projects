-- CustomSheetTest.applescript

--  Created by Shane Stanley.
--  <sstanley@myriad-com.com.au>.
--  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>

-- sample code using NSWindow+MyriadHelpers category

script CustomSheetTest
	property parent : class "NSObject"
	property mainWindow : missing value -- connect in Interface Builder
	property customWindow : missing value -- connect in Interface Builder
	
	## Show custom sheet; show a window as a sheet over another window
	-- shows custom sheet customWindow over window mainWindow	
	on showCustomSheet:sender -- triggered by button in window
		customWindow's showOver:mainWindow
	end showCustomSheet:
	
	-- connected to button in customWindow
	on customSheetButtonPushed:sender -- triggered by button in sheet
		-- handler must close the sheet
		current application's NSApp's endSheet:customWindow
		-- now do our stuff
		log sender's title()
	end customSheetButtonPushed:
	
	-- shows custom sheet customWindow over window mainWindow with timeout
	on showCustomSheetGivingUp:sender -- triggered by button in window
		customWindow's showOver:mainWindow calling:{"customSheetTimedOut:", me} onlyAfter:5
	end showCustomSheetGivingUp:
	
	-- only called when window times out
	on customSheetTimedOut:returnCode
		log "Gave up"
		-- whatever
	end customSheetTimedOut:
	
end script
