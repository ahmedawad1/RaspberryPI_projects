	$(document).ready(function() {
		$("#button1").on("click", function() {

	        $.ajax("/api/button1", {
	            dataType: "text",
	            success: function(data, textStatus) {
	                reportResponse(data);
	            }
	        });

	    });
		
		 function reportResponse(res) {
		      document.getElementById("par").innerHTML=res;
		    }
		 
		 function submitForm() {
			 var frm= document.getElementyId("usrform");
			 frm.submit(); // Submit
		 }
		});