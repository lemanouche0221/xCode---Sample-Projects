-- Created 2013-10-14 16:05:02 +1100 by Shane Stanley
-- © 2013
use AppleScript version "2.3"
use scripting additions
use framework "Foundation"

-- Simple number formatter
property theFormatter : missing value
on formatNumber:n
	if theFormatter = missing value then
		-- mkae a date formatter
		set my theFormatter to current application's NSNumberFormatter's alloc()'s init()
		tell my theFormatter -- configure the formatter
			its setLocale:(current application's NSLocale's alloc()'s initWithLocaleIdentifier:"fr")
			its setNumberStyle:(current application's NSNumberFormatterDecimalStyle)
			its setMinimumFractionDigits:1
			its setMaximumFractionDigits:5
			its setMinimumIntegerDigits:1
			its setRoundingMode:(current application's NSNumberFormatterRoundHalfUp)
			its setHasThousandSeparators:false
		end tell
	end if
	-- tell the formatter to format the number
	return (theFormatter's stringFromNumber:n) as text
end formatNumber: