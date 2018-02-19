//添加检查方式 签到检查；寝室检查；系统导入
function addCheckWay(){
	if(!document.getElementById||!document.getElementsByClassName) return false;
	var oI=document.getElementById("add-check1-way");
	var oDiv=document.getElementsByClassName("check1-way")[0];
	var oFind=document.getElementById("find");
	var oIdWay=document.getElementsByClassName("id-way")[0];
	var info=document.getElementsByClassName("check1-way1")[0];
	var n=m=l=1;
	oI.onclick=function(){
		if(m){
			oDiv.style.display="block";
			oI.style.color="#0871e7";
			m=0;
			}
		else{
			oDiv.style.display="none";
			oI.style.color="#666";
			m=1;
			} 
		}
	oFind.onclick=function(){
		if(n){
			oIdWay.style.display="block";
			oFind.style.color="#0871e7";
			n=0;
			}
		else{
			oIdWay.style.display="none";
			oFind.style.color="#666";
			n=1;
			} 
		}



	}
addLoadEvent(addCheckWay);

$('.ok').click(function(){
	var account = $('#user-name').val();
	var pass = $('#password').val();
	$.ajax({
        url: 'view/add',
        method: 'POST',
        data: {account:account,password:pass},
        success: function(ret){
        	if(ret.success){
        		location.reload(true);
        	}else{
        		alert(ret.errorMessage);
        	}
        },
        error: function(err){console.log(err)}
    })
})

