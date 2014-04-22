-- Created 2013-10-14 16:05:02 +1100 by Shane Stanley
-- © 2013
use AppleScript version "2.3"
use scripting additions
use framework "Foundation"
use framework "AppKit"

-- This is an adaption of one of Sal Soghoian's Cocoa-AppleScript applet samples.

property thisProgressWindow : missing value
property thisProgressIndicator : missing value
property primaryTextField : missing value
property secondaryTextField : missing value

property hitStop : false

on createProgressWindowWithTitle:initialWindowTitle intialMessage:initialDescriptionString
	log "Create Progress Called"
	try
		set my hitStop to false
		-- create a window
		set thisProgressWindow to current application's NSWindow's alloc()'s initWithContentRect:{{100, 100}, {360, 84}} ¬
			styleMask:(current application's NSTitledWindowMask) ¬
			backing:(current application's NSBackingStoreBuffered) ¬
			defer:true
		
		-- set the properties of the new window
		thisProgressWindow's setTitle:initialWindowTitle
		thisProgressWindow's setLevel:(current application's NSFloatingWindowLevel)
		thisProgressWindow's |center|()
		
		-- create a progress indicator
		set thisProgressIndicator to current application's NSProgressIndicator's alloc()'s initWithFrame:{{10, 12}, {280, 20}}
		-- set the properties of the progress indicator
		thisProgressIndicator's setUsesThreadedAnimation:true
		thisProgressIndicator's setStyle:(current application's NSProgressIndicatorBarStyle)
		thisProgressIndicator's setDisplayedWhenStopped:true
		thisProgressIndicator's setControlSize:(current application's NSRegularControlSize)
		thisProgressIndicator's setIndeterminate:true
		thisProgressIndicator's startAnimation:thisProgressIndicator
		
		-- add the progress indicator to the content view of the window
		set thisContentView to thisProgressWindow's contentView()
		tell thisContentView to addSubview:thisProgressIndicator
		
		-- create a text field
		set primaryTextField to current application's NSTextField's alloc()'s initWithFrame:{{10, 60}, {340, 18}}
		set thisSystemFont to current application's NSFont's systemFontOfSize:13
		primaryTextField's setFont:thisSystemFont
		primaryTextField's setBordered:false
		primaryTextField's setDrawsBackground:false
		primaryTextField's setSelectable:false
		primaryTextField's setEditable:false
		
		primaryTextField's setStringValue:(initialDescriptionString)
		
		-- create a second text field
		set secondaryTextField to current application's NSTextField's alloc()'s initWithFrame:{{10, 38}, {340, 18}}
		secondaryTextField's setStringValue:" "
		secondaryTextField's setBordered:false
		secondaryTextField's setSelectable:false
		secondaryTextField's setEditable:false
		secondaryTextField's setDrawsBackground:false
		
		-- add the text fields to the content view of the window
		tell thisContentView to addSubview:primaryTextField
		tell thisContentView to addSubview:secondaryTextField
		
		-- create a button
		set thisStopButton to current application's NSButton's alloc()'s initWithFrame:{{296, 10}, {60, 24}}
		thisStopButton's setButtonType:(current application's NSMomentaryPushInButton)
		thisStopButton's setBezelStyle:(current application's NSRoundedBezelStyle)
		thisStopButton's setImagePosition:(current application's NSNoImage)
		thisStopButton's setTitle:"Stop"
		thisStopButton's setTarget:me
		thisStopButton's setAction:"cancelPressed:"
		
		-- add created button to the content view
		tell thisContentView to addSubview:thisStopButton
		
		-- display the window
		thisProgressWindow's makeKeyAndOrderFront:thisProgressWindow
		tell current application's NSThread to sleepForTimeInterval:1.0 -- allows screen update
		return true
	on error
		return false
	end try
end createProgressWindowWithTitle:intialMessage:

on readyProgressWindowWithMinimum:minimumValue withMaximum:maximumValue
	log "Ready Progress Window Called"
	try
		thisProgressIndicator's setIndeterminate:false
		thisProgressIndicator's setUsesThreadedAnimation:true
		thisProgressIndicator's setMinValue:minimumValue
		thisProgressIndicator's setMaxValue:maximumValue
		thisProgressIndicator's setDoubleValue:minimumValue
		primaryTextField's setStringValue:""
		secondaryTextField's setStringValue:""
		return true
	on error
		return false
	end try
end readyProgressWindowWithMinimum:withMaximum:

on incrementProgressWindowWithCounter:thisLoopNumber itemName:thisItemName itemCount:thisItemCount
	log "Increment Progress Called"
	if hitStop then
		closeProgressWindow()
		return false
	end if
	try
		primaryTextField's setStringValue:thisItemName
		secondaryTextField's setStringValue:((thisLoopNumber as string) & "/" & (thisItemCount as string))
		primaryTextField's display()
		secondaryTextField's display()
		thisProgressIndicator's incrementBy:1
		thisProgressWindow's makeKeyAndOrderFront:me
		return true
	on error
		return false
	end try
end incrementProgressWindowWithCounter:itemName:itemCount:

on nameFromPath:thePath
	set thePath to current application's NSString's stringWithString:thePath
	return thePath's lastPathComponent()
end nameFromPath:

on cancelPressed:sender
	log "Cancel Called"
	set my hitStop to true
end cancelPressed:

on closeProgressWindow()
	log "Close Window Called"
	tell thisProgressWindow to orderOut:thisProgressWindow
end closeProgressWindow
