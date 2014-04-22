-- Created 2013-10-14 18:09:07 +1100 by Shane Stanley
-- © 2013
use AppleScript version "2.3"
use scripting additions
use framework "AppKit" -- for NSEvent

on checkModifier:keyName
	if keyName = "option" then
		set theMask to current application's NSAlternateKeyMask as integer
	else if keyName = "control" then
		set theMask to current application's NSControlKeyMask as integer
	else if keyName = "command" then
		set theMask to current application's NSCommandKeyMask as integer
	else if keyName = "shift" then
		set theMask to current application's NSShiftKeyMask as integer
	else
		return false
	end if
	set theFlag to current application's NSEvent's modifierFlags() as integer
	if ((theFlag div theMask) mod 2) = 0 then
		return false
	else
		return true
	end if
end checkModifier:

on check for modifier modifying key keyType
	if keyType = option then
		set theMask to current application's NSAlternateKeyMask as integer
	else if keyType = control then
		set theMask to current application's NSControlKeyMask as integer
	else if keyType = command then
		set theMask to current application's NSCommandKeyMask as integer
	else if keyType = shift then
		set theMask to current application's NSShiftKeyMask as integer
	else
		return false
	end if
	set theFlag to current application's NSEvent's modifierFlags() as integer
	if ((theFlag div theMask) mod 2) = 0 then
		return false
	else
		return true
	end if
end check for modifier