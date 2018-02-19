$.ajax({
    url: '/api/check/',
    method: 'GET',
    success: function( ret ) {
        var html = $('#check1-container').html(),
            list = ret.sign_check;
        for(var i = 0; i < list.length; i++){
            html += `<a href="check2/${list[i].check_id}">
                        <li class="check1"> <span>${list[i].check_name}</span> <span>${list[i].second_name}</span> </li>
                    </a> `
        }
         $('#check1-container').html(html);
    },
    error: function(err){
        console.error(err);
        alert('get check list error');
    }
});