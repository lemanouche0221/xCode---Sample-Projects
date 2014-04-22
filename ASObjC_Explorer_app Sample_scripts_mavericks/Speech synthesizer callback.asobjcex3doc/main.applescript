-- Created 2013-10-21 09:56:54 +1100 by Shane Stanley
-- © 2013
use AppleScript version "2.3"
use scripting additions
use framework "Foundation"
use framework "AppKit" -- for NSSpeechSynthesizer

property theSender : missing value

on startSaying:theString sender:sender
	set my theSender to sender -- store in property for later use
	set theSynth to current application's NSSpeechSynthesizer's new()
	theSynth's setDelegate:me
	theSynth's startSpeakingString:theString
end startSaying:sender:

-- delegate methods
on speechSynthesizer:anNSSpeechSynthesizer willSpeakWord:anNSRange ofString:aString
	log (aString's substringWithRange:anNSRange) as text
end speechSynthesizer:willSpeakWord:ofString:

on speechSynthesizer:anNSSpeechSynthesizer didFinishSpeaking:theFlag
	theSender's stringSpoken:(theFlag as integer)
end speechSynthesizer:didFinishSpeaking:
