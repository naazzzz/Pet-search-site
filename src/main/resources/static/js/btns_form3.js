document.getElementById('btn_next2').addEventListener('click',function(){

    localStorage.setItem("date",document.getElementById("date").value);
    localStorage.setItem("place",document.getElementById("place").value);

    let params = window
        .location
        .search
        .replace('?','')
        .split('&')
        .reduce(
            function(p,e){
                let a = e.split('=');
                p[ decodeURIComponent(a[0])] = decodeURIComponent(a[1]);
                return p;
            },
            {}
        );
    document.location='/MainWindow/CreateAd?form='+ params['form'] +'&step=4';
});
