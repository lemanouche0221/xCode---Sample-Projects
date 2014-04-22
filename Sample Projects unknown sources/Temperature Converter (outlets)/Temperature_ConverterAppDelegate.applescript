
script Temperature_ConverterAppDelegate
	property input_field : missing value
	property unit_type_popup : missing value
	property fahrenheit_readout : missing value
	property celsius_readout : missing value
	property kelvin_readout : missing value
	
	on calculateTemps_(sender)
		set the unit_type to (unit_type_popup's titleOfSelectedItem()) as string
		set the input_amount to input_field's floatValue()
		
		if the unit_type is "Fahrenheit" then
			set the temp_value to the input_amount as degrees Fahrenheit
		else if the unit_type is "Celsius" then
			set the temp_value to the input_amount as degrees Celsius
		else if the unit_type is "Kelvin" then
			set the temp_value to the input_amount as degrees Kelvin
		end if
		
		tell fahrenheit_readout to setFloatValue_((temp_value as degrees Fahrenheit) as real)
		tell celsius_readout to setFloatValue_((temp_value as degrees Celsius) as real)
		tell kelvin_readout to setFloatValue_((temp_value as degrees Kelvin) as real)
	end calculateTemps_
end script