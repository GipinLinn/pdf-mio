



function gettime(i) {
    var time = new Date(1475590959633);
   
    dateMag(time);


    var d = new Date(1475569359633);
    
    var len = d.getTime();
    // localTime GmtTime Offseti'i
    var offset = d.getTimezoneOffset() * 60000;
    
    var utcTime = len + offset;
    //TimeZone
    var dd = new Date(utcTime + 3600000 * i);
     dateMag(dd);
}

    

function dateMag(time){
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm =time.getMinutes();
    var s = time.getSeconds();
    alert(y+'-'+m+'-'+d+' '+h+':'+mm+':'+s);
}



