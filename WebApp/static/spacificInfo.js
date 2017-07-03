/*
    spacificInfo.js
    
    Ethan Somes
    Sonia Moreno 
    May 15, 2017

    Search box and buttons for more info page (AsteroidOfDay.html)
 */

function getInfo(neoName){
	/*
	@param neoName: The name of the neo being searched
    Called when page is loaded
    Makes new request to api
    Calls function getInfoCallback()
    */
	var url = api_base_url + 'neo/object/' + neoName;
    xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open('get', url);

    xmlHttpRequest.onreadystatechange = function() {
            if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) { 
                getInfoCallback(neoName, xmlHttpRequest.responseText);
            } 
        }; 
    xmlHttpRequest.send(null);
}

function getInfoCallback(neoName, responseText){
	/*
	@param neoName: The name of the neo being searched
	@param responseText: The JSON string from the api
    Called when page is loaded
    Parses JSON, creates table of data about this neo
    */
	var neoDict = JSON.parse(responseText);
    var tableBody = '';

    tableBody += '<tr><td class = "tooltip"> Closest Approach Date' + 
    	'<span class="tooltiptext">' + 'Year of closest Earth approach.' + '</span></td>';
    var date = neoDict.closeapproachdate.split(" ")[0];
    tableBody += '<td>' + date +'</td></tr>';
	
    tableBody += '<tr><td class = "tooltip"> Estimated Diameter' + '<span class = "tooltiptext">' +
    	 'Estimated diameter range in meters.' +  '</span></td>';
    tableBody+= '<td>' + neoDict.estimateddi + '</td></tr>';

    tableBody += '<tr><td class = "tooltip"> Velocity Relative to the Earth (km/s)' + 
    	'<span class = "tooltiptext">' + 'Object velocity relative to the earth at close-approach.' + '</span></td>';
    tableBody+= '<td>' + neoDict.vrelative + '</td></tr>';

    tableBody += '<tr><td class = "tooltip"> CA Distance Nominal (LD | au)' + 
    	'<span class = "tooltiptext">' + 'The most likely to occur approach-distance in Lunar Distance (LD) and au.' + '</span></td>';
    tableBody+= '<td>' + neoDict.cadistance + '</td></tr>';

    tableBody += '<tr><td class = "tooltip"> CA Distance Minimum (LD | au)' + '<span class = "tooltiptext">' + 
    	'The minimum possible approach-distance in Lunar Distance (LD) and au.' + '</span></td>';
    tableBody+= '<td>' + neoDict.cadistancemin + '</td></tr>';

    tableBody += '<tr><td class = "tooltip"> Velocity infinity (km/s)' + '<span class = "tooltiptext">' + 
    	'Object velocity relative to a massless earth at close-approach.' + '</span></td>';
    tableBody+= '<td>' + neoDict.vinfinity + '</td></tr>';

    tableBody += '<tr><td class = "tooltip"> H (mag)' + '<span class = "tooltiptext">' + 
    	'Absolute magnitude of the object. Generally, a smaller magnitude is correlated with a larger object diameter.' + '</span></td>';
    tableBody+= '<td>' + neoDict.hmag+ '</td></tr>'

    var resultsTableElement = document.getElementById('results_table');
    resultsTableElement.innerHTML = tableBody;
}