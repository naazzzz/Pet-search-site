
document.getElementById('btn_next3').addEventListener('click',function(){
    var description = document.getElementById("description").value;
    if(!description==="") {
        localStorage.setItem("description", description);
        let params = window
            .location
            .search
            .replace('?', '')
            .split('&')
            .reduce(
                function (p, e) {
                    let a = e.split('=');
                    p[decodeURIComponent(a[0])] = decodeURIComponent(a[1]);
                    return p;
                },
                {}
            );
        document.location = '/MainWindow/CreateAd?form=' + params['form'] + '&step=5';
    }
    else alert("Для перехода дальше заполните все подпункты в форме");
});
