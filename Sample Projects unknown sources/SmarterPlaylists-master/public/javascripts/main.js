jQuery(function($) {
	enableUploadButton();
	$('#playlistUploadFile').on("change", function() {
		enableUploadButton();
	});
	function enableUploadButton() {
		var playlistUploadFile = $('#playlistUploadFile').val();
		if (playlistUploadFile !== undefined && playlistUploadFile !== "") {
			$(".playlistUploadButton").prop("disabled", false);
		}
		else {
			$(".playlistUploadButton").prop("disabled", true);
		}
	}
    $('#polyglotLanguageSwitcher').polyglotLanguageSwitcher({
		effect: 'fade',
        testMode: true,
        onChange: function(evt){
            $.ajax({
            	url: "/lang",
            	type: "POST",
            	data: {name: evt.selectedItem},
                success: function (data) {
                	location.reload(false);
                }
            });
        }
	});
    $("a.polyglot").click(function() {
    	  return false;
    });
    if (navigator.platform.toUpperCase().indexOf('MAC')>=0) {
    	$('.helper-image').attr('href', $('.helper-image').attr('href').replace("\.windows\.", ".mac.", "gi") );
    }
    $('.helper-image').magnificPopup({type:'image'});
});