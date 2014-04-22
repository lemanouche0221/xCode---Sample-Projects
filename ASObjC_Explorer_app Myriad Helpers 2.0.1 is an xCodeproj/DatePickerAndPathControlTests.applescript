-- DatePickerAndPathControlTests.applescript

--  Created by Shane Stanley.
--  <sstanley@myriad-com.com.au>.
--  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>

-- sample code using date picker and path control categories

script DatePickerAndPathControlTests
	property parent : class "NSObject"
	property datePicker : missing value
	property pathControl : missing value
	
	-- test the date picker category
	on datePickerSet:sender
		set thisDate to current date
		log "Current date is " & (thisDate as text)
		datePicker's setDateAS:thisDate
	end datePickerSet:
	
	on datePickerGet:sender
		set theDate to (datePicker's dateAS() as date)
		log theDate as text
		log "##"
		log "##"
	end datePickerGet:
	
	-- test the path control category
	on pathControlSet:sender
		pathControl's setHFSFilepath:(path to preferences folder as text)
	end pathControlSet:
	
	on pathControlGet:sender -- triggered by dragging file over path control
		log "Path control shows " & (pathControl's |HFSFilepath|()) as text
		log "##"
		log "##"
	end pathControlGet:
	
	on pathControlSetAny:sender
		pathControl's setPathAny:(path to home folder) -- this time using an alias
	end pathControlSetAny:
	
end script
