function unhide(contentID, buttonID) {
    var content = document.getElementById(contentID);
    if (content.classList.contains('hidden')) {
        document.getElementById("content").style.width = "50%";
        document.getElementById(buttonID).children[0].classList.add('selectedcat');
    } else {
        document.getElementById("content").style.width = "0%";
        document.getElementById(buttonID).children[0].classList.remove('selectedcat');
    }
    content.classList.toggle('unhidden');
    content.classList.toggle('hidden');

    //hide the other content
    var all = document.getElementById("content").children;
    var i;
    for (i = 0; i < all.length; i++) {
        if (all[i].id != contentID && all[i].tagName.toLowerCase() == 'div') {
            all[i].classList.add('hidden');
            all[i].classList.remove('unhidden');
        } else if (all[i].id != buttonID && all[i].tagName.toLowerCase() == 'a') {
            all[i].children[0].classList.remove('selectedcat')
        }
    }
}
