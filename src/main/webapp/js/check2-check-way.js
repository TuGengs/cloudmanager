//添加四位数和二维码
function showAddTime(){
	if(!document.getElementById||!document.getElementsByClassName) return false;
	var oDiv=document.getElementById("check2-container");
	var oUl=oDiv.getElementsByTagName("ul")[0];
	var oAdd=document.getElementById("add-check2-way");
	var oTime=$('.add-li').val().split(' ')[0];
	var oDate=new Date();
	var month = oDate.getMonth() + 1;
    var date = oDate.getDate();
	function changeMonth(){
		return month < 10? "0"+month : month;
	}
	/*oAdd.onclick=function(){
		var oLi=document.createElement("Li");
		var oSpan=document.createElement("span");
		var oI=document.createElement("i");
		oI.innerHTML="&#xe60d;";
		oI.style.fontFamily="iconfont";
		oI.style.fontWeight="bold";
		oI.className="delete";
		oLi.className="check2";
		oSpan.innerHTML=oTime.innerHTML;
		oSpan.style.color="#333";
		oLi.appendChild(oSpan);
		oLi.appendChild(oI);
		oUl.appendChild(oLi);
		}
	
	}*/
	function renderCheckList(id){
			var oA = document.createElement('a');
			var oLi=document.createElement("Li");
			var oSpan=document.createElement("span");
			oLi.className="check2";
			// id
			oA.setAttribute('href','../detail/'+id);
			oSpan.innerHTML=oTime;
			oLi.appendChild(oSpan);
			oA.appendChild(oLi)
			oUl.appendChild(oA);
	}
	$('#add-check2-way').click(function createCheck(){
		var check_id = $('#check_id').attr('value');
		var time = $('.add-li').val();
		var data = {'check_id':check_id,time:time};
		$.ajax({
			url: 'add',
			method: 'POST',
			data: data,
			success: function( ret ) {
				//renderCheckList(ret.data);
				location.href="../detail/"+ret.data;
				//alert("success");
				//location.reload(true);
			},
			error: function(err){
				console.error(err);
				alert('get check list error');
			}
		});
	})
	$(document).delegate(".delete", "click", function() {
		var parent = $(this).parents(".check2");
		var attrs = parent.find('a').attr('href').split('/');
		$.ajax({
			url: 'delete',
			method: 'POST',
			data: {single_id:attrs[attrs.length-1]},
			success: function( ret ) {
				parent.remove();
			},
			error: function(err){
				console.error(err);
			}
		});
        
    });	
	$(".bottom bot li").click(function(){
		alert("mmm");
		$(this).css("color","#0871e7").siblings("color","#535353");
	})
	
}
addLoadEvent(showAddTime);