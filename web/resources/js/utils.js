function justNumbers(e){
    var keynum = window.event ? window.event.keyCode : e.which;
    if ((keynum == 8))
    return true;

    return /\d/.test(String.fromCharCode(keynum));
}