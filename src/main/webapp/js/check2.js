var time = new Date();
var date = time.getFullYear() + '-' + (time.getMonth() +1) + '-' + time.getDate()+' '+time.getHours()+':'+time.getMinutes();
// $('#time4').val(date);
//选择日期
 $("#time").datetimePicker({
	 value:date,
    onChange: function(){
    	
    },
    onClose: function(date){
    	
    }
});

