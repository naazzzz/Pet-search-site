document.getElementById('btn_next2').addEventListener('click',function(){
        var date =document.getElementById("date").value;
        var place =document.getElementById("place").value;
        if(!(date==="" || place==="")) {
            localStorage.setItem("date", date);
            localStorage.setItem("place", place);

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
            document.location = '/MainWindow/CreateAd?form=' + params['form'] + '&step=4';
        }
        else alert("Для перехода дальше заполните все подпункты в форме");
});
