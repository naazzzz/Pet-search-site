var qu1=false;
var qu2=false;
var qu1_select_text;
var qu2_select_text;
function btnClick(obj){
    if(qu1===false && (obj.id==="q1_btn1" || obj.id==="q1_btn2")){
        obj.className="btn_style_active";
        qu1=true;
        if(obj.id==="q1_btn1"){
            qu1_select_text="dog";
        }else qu1_select_text="cat";
}
    if(qu1===true && (obj.id==="q1_btn1" || obj.id==="q1_btn2")){
    if(obj.id==="q1_btn1"){
            qu1_select_text="dog";
        obj.className="btn_style_active";
        document.getElementById("q1_btn2").className="btn_style_none";
    }
    else{
        qu1_select_text="cat";
        obj.className="btn_style_active";
        document.getElementById("q1_btn1").className="btn_style_none";
    }    
}

if(qu2===false && (obj.id==="q2_btn1" || obj.id==="q2_btn2")){
    obj.className="btn_style_active";
    qu2=true;
    if(obj.id==="q2_btn1"){
        qu2_select_text="boy";
    }else qu2_select_text="girl";
}
if(qu2===true && (obj.id==="q2_btn1" || obj.id==="q2_btn2")){
if(obj.id==="q2_btn1"){
    qu2_select_text="boy";
    obj.className="btn_style_active";
    document.getElementById("q2_btn2").className="btn_style_none";
}
else{
    qu2_select_text="girl";
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
        localStorage.setItem("kind",qu1_select_text);
        localStorage.setItem("sex",qu2_select_text);
        document.location='/MainWindow/CreateAd?form='+ params['form'] +'&step=2';
    }
    else{
        alert("Для перехода дальше выберите все подпункты в форме")
    }
});




  