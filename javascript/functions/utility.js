function p(str){
    if(typeof str === 'undefined'){
        document.write('<br>');
    }else{
        document.write(str);
    }
}

function pl(str){
    if(typeof str === 'undefined'){
        document.write('<br>');
    }else{
        document.write(`${str} <br>`);
    }
}