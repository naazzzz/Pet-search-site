var qu1=false;
var qu2=false;

function btnClick(obj){
    if(qu1==false && (obj.id=="q1_btn1" || obj.id=="q1_btn2")){
        obj.className="btn_style_active";
        qu1=true;
}
    if(qu1==true && (obj.id=="q1_btn1" || obj.id=="q1_btn2")){
    if(obj.id=="q1_btn1"){
        obj.className="btn_style_active";
        document.getElementById("q1_btn2").className="btn_style_none";
    }
    else{
        obj.className="btn_style_active";
        document.getElementById("q1_btn1").className="btn_style_none";
    }    
}

if(qu2==false && (obj.id=="q2_btn1" || obj.id=="q2_btn2")){
    obj.className="btn_style_active";
    qu2=true;
}
if(qu2==true && (obj.id=="q2_btn1" || obj.id=="q2_btn2")){
if(obj.id=="q2_btn1"){
    obj.className="btn_style_active";
    document.getElementById("q2_btn2").className="btn_style_none";
}
else{
    obj.className="btn_style_active";
    document.getElementById("q2_btn1").className="btn_style_none";
}    
}
}

document.getElementById('btn_next').addEventListener('click',function(){
    if(qu1 && qu2){
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
        document.location='/MainWindow/CreateAd?form='+ params['form'] +'&step=2';
    }
    else{
        alert("Для перехода дальше выберите все подпункты в форме")
    }
});




  