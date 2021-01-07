function getLocation() {
    $.getJSON("https://ipinfo.io?token=b0244f6f14547e", onLocationGot);
  }
 
  function onLocationGot(info) {
    let position = info.loc.split(",");
	document.getElementById("lat").value = position[0];
	document.getElementById("lon").value = position[1];
  }
getLocation();

/*
function getLocationGGAPI() {
    let geolocation = navigator.geolocation;
    if (geolocation) {
      geolocation.getCurrentPosition(onGeoSuccess);
    } else {
      console.log("Geolocation is not supported by this browser.");
    }
  }
  
  function onGeoSuccess(position) {
    document.getElementById("lat").value = position.coords.latitude;
	document.getElementById("lon").value = position.coords.longitude;
  }
  getLocationGGAPI();
  */