function unhide(contentID) {
    var content = document.getElementById(contentID);
    content.classList.toggle('unhidden');
    content.classList.toggle('hidden');

    //hide the other content
    var all = document.getElementById("content").children;
    var i;
    for (i = 0; i < all.length; i++) {
        if (all[i].id != contentID) {
            all[i].classList.add('hidden');
            all[i].classList.remove('unhidden');
        }
    }
}
