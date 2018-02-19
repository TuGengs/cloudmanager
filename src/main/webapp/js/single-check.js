

function sort(sort_id){
	var url = document.URL;
	var reg = new RegExp("(^|&)check_id=([^&]*)(&|$)");
	var id = window.location.search.substring(1).match(reg)[2];
	window.location.href="?check_id="+id+"&sort_id="+sort_id;
	/*$.ajax({
        url: 'list',
        method: 'GET',
        data: data,
        success: function(ret){
        	
        },
        error: function(err){console.log(err)}
    })*/
}

$('.class').click(function(){
	sort(0);
});
$('.sysnum').click(function(){
	sort(1);
});
$('.uname').click(function(){
	sort(2);
});
$('.times').click(function(){
	sort(3);
});