-- OpenSavePanelTests.applescript

--  Created by Shane Stanley.
--  <sstanley@myriad-com.com.au>.
--  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>

-- sample code using open and save panel categories

script OpenSavePanelTests
	property parent : class "NSObject"
	property mainWindow : missing value -- connect in Interface Builder
	
	## Show open panel as sheet
	-- shows NSOpenPanel over window mainWindow	
	on showOpenPanel:sender
		set thePanel to current application's NSOpenPanel's makeOpenAt:(path to desktop) |types|:{"txt", "pdf"} |files|:true multiples:false prompt:"Choose a text or PDF file:" title:"My Open Title"
		thePanel's showOver:mainWindow calling:{"openSheetDone:", me}
	end showOpenPanel:
	
	on openSheetDone:theResult -- called when panel closed
		if theResult = missing value then
			-- cancel button
			log "Cancel pressed"
		else
			log theResult -- path to file, or list of files if multiples is true
		end if
	end openSheetDone:
	
	-- show modal open panel
	on showOpenPanelModal:sender
		set thePanel to current application's NSOpenPanel's makeOpenAt:(path to desktop) |types|:{"txt", "pdf"} |files|:true multiples:false prompt:"Choose a text or PDF file:" title:"My Open Title"
		set thePath to thePanel's showModal() -- returns HFS path, or list if multiples is true
		if thePath = missing value then
			log "Cancel pressed"
		else
			log thePath
		end if
	end showOpenPanelModal:
	
	## Show save panel as sheet
	-- shows NSSavePanel over window mainWindow	
	on showSavePanel:sender
		set thePanel to current application's NSSavePanel's makeSaveAt:(path to home folder) |types|:{"txt"} |name|:"Some name" prompt:"Save the data:" title:"My Save Title"
		thePanel's showOver:mainWindow calling:{"saveSheetDone:", me}
	end showSavePanel:
	
	on saveSheetDone:theResult -- called when panel closed
		if theResult = missing value then
			-- cancel button
			log "Cancel pressed"
		else
			log theResult -- path to file
		end if
	end saveSheetDone:
	
	-- show modal save panel
	on showSavePanelModal:sender
		set thePanel to current application's NSSavePanel's makeSaveAt:(path to home folder) |types|:{"txt"} |name|:"Some name" prompt:"Save the data:" title:"My Save Title"
		set thePath to thePanel's showModal() -- returns HFS path
		if thePath = missing value then
			log "Cancel pressed"
		else
			log thePath
		end if
	end showSavePanelModal:
	
end script
