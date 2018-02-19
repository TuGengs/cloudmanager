
$(function() {
    //删除
    var remove = 0;
    $('.remove').click(function(){
        if(!remove){
            remove = 1;
            $('.checkboxFour').show();
            $('.remove>span').text('确定');
        } else{
            remove = 0;
            $('.checkboxFour').css({display: 'none'});
            $('.remove>span').text('删除');
            var list = $('.list-item');
            var sysnums = [],
                check_id = parseInt($('#check_id')[0].value),
                eles = [];
            for(var i=0;i<list.length;i++){
               if($(list[i]).find('label').hasClass('chek')){
                   sysnums.push(parseInt($(list[i]).children()[2].value));
                   eles.push(list[i]);
               }
            }
            //$(eles).remove();
            // 
            var data={list: sysnums, check_id: check_id};
            $.ajax({
                url: 'list_delete',
                method: 'POST',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data),
                success: function(ret) { 
                	
                	ret.success && location.reload();
                },
                error: function(err){alert(err)}
            })
        }
        
    });
    //增加
    $('.jia').click(function(){
    	$('.check2-way').show();
    	var status = 0;
    	$.ajax({
    	    url: 'qrcode',
    	    method: 'GET',
    	    type: "json",
    	    data: {check_id: parseInt($('#check_id')[0].value)},
    	    success: function(ret) { 
    	    	if(ret.success){
    	    		//$('#dimension').attr('src',ret.data);
    	    		var qrcode = $('#qcode').qrcode({width:200,height:200,text:ret.data}).hide();
    	    		var canvas = qrcode.find('canvas').get(0);
    	    		$('#imgOne').attr('src',canvas.toDataURL('image/jpg'));
    	    		}else{
    	    		alert(ret.errorMessage);
    	    	}
    	    },
    	    error: function(err){alert(err);}
    	})
    	$.ajax({
            url: '../../../api/check/signin/mkcode/'+$('#check_id')[0].value,
            method: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(ret) { 
            	if(ret.success){
            		$('#four-num').html(ret.data);	
            		//
            	}else{
            		alert(ret.errorMessage);
            		$('#four-num').html('暂无信息');	
            	}
            },
            error: function(err){
            	alert(err);
            	$('#four-num').html('暂无信息');
            }
        })
        $('.check2-way-container').show();
        
    });

        //全选，全不选
    $(".all").click(function (e) {
        e.stopPropagation();
        if ($(this).find('label').hasClass('chek')) {
            $(this).parent().find('label').removeClass('chek');
        } else {
        	$(this).parent().find('label').addClass('chek');
            $(this).find('label').addClass('chek');
        }
    });
    $('.list-item').click(function(e){e.stopPropagation();})
    $('.list-item').find('label').click(function (e) {
        if($(this).hasClass('chek')){
            $(this).removeClass('chek');
        }else{
            $(this).addClass('chek');
        }
    });

    $('.banji').click(function(){
        if(!this.isShow){
            $(this).children('.list').show();
            this.isShow = true;
        } else{
            $(this).children('.list').css({display: 'none'});
            this.isShow = false;
        }
        
    });
    $('.check2-way-button').click(function(){
        $('.check2-way-container').css({display:'none'});
    })

});





        
        

