
document.getElementById('btn_next3').addEventListener('click',function(){

    localStorage.setItem("description",document.getElementById("description").value);
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
    document.location='/MainWindow/CreateAd?form='+ params['form'] +'&step=5';
});
