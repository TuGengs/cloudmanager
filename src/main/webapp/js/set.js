
$(function () {
 
    //公开
    $('#gongkai').click(function () {
        $('.open').toggle();
    });
	
    //编辑密码
    $('.chu').click(function () {
        if($('.caname').attr('disabled')==='disabled'){
            this.innerHTML = "";
            $('.caname').attr({disabled: false});
        } else {
            this.innerHTML = '';
            $('.caname').attr({disabled: true});
            var password = $('.caname').val(),
                check_id = $('#check_id').val();
            $.ajax({
                url: 'setting/public/pwd_modify',
                method: 'POST',
                data: {check_id: check_id,password: password},
                success: function(ret) {
                	if(ret.success){
 	            		
 	        	   }else{
 	        		   alert(ret.errorMessage);
 	        	   }
                },
                error: function(err){alert(err)}
            })
        }
    });
    
    // 是否公开
    $('#checkboxThreeInput').click(function(e){
        var check_id = $('#check_id').val();
        var is_open = this.checked?0:1;;
        $.ajax({
            url: 'setting/public/open',
            method: 'POST',
            contentType:  "application/json; charset=utf-8",
            data: JSON.stringify({check_id: check_id,is_open: is_open}),
            success: function(ret) {
            	if(ret.success){
	            		
	        	   }else{
	        		   alert(ret.errorMessage);
	        	   }
            },
            error: function(err){alert(err)}
        })
    })
 
    //二维码
    $('#code1').click(function () {
        $('#code1').hide();
        $('#code2').show();
    });
    $('#code2').click(function () {
        $('#code2').hide();
        $('#code1').show();
    });
 
    //查看者名单
    $('#no').click(function () {
        $('.close').toggle();
    });

    $('.delete').click(function(){
        var sysnum = $(this).parent().children()[2].value,
            self = this;
            check_id = $('#check_id').val();
        //console.log($(self).parent().remove());
        $.ajax({
            url: 'setting/public/list_delete',
            method: 'POST',
            data: {check_id: check_id,user_id: sysnum},
            success: function(ret) {
            	if(ret.success){
            		$(self).parent().remove();
            	}else{
            		alert(ret.success);
            	}
            },
            error: function(err){alert(err)}
        })
    })
    function renderShuru(ret,tel){
    	console.log(ret);
        var infoLi = $('<li></li>').html(
            `<p>名称 :
                <input class = 'manager_class' type="text" value="${ret.name}" style="border-bottom: 1px solid #535353;">
            </p>
            <p>手机号码:
                <span>${tel}</span>    
            </p>
            <p>姓名 ：
                <input type="text" value="${ret.name}">
            </p>`
        )
        var checkLi = $('<li></li>').html('<i></i>').addClass('jiancha');
        var input = $('<input/>').attr({type: 'submit', class:'submit', value: '确认'});
        checkLi.click(function(){
            $("#kuang").show();
            $("#meng").show();
        })
        input.click(function(){
            var check_id = $('#check_id').val(),
                manager_class = $('.manager_class').val(),
                list = $('.list-item'),
                manager = [],
                sysnum = ret.sysnum+"",
                name = ret.name;
            for(var i=0;i<list.length;i++){
               if($(list[i]).find('label').hasClass('chek')){
                   manager.push($(list[i]).children()[2].value);
               }
            }
            $(this).parent().hide();
            $('.add').show();
            $.ajax({
                url: 'setting/help/submit',
                method: 'POST',
                data: JSON.stringify({check_id: check_id, sysnum: sysnum, manager_class: manager_class, manager: manager}),
                contentType: "application/json;charset=utf8",
                success: function(ret) {
                	if(ret.success){
                		// 提交完成 前端渲染
                		$('#helper').append(renderHelp(sysnum,manager_class,manager.length,name));
                		
                	}else{
                		alert(ret.errorMessage);
                	}
                },
                error: function(err){alert(err)}
            })
        })
//        $('.shuru').html('');
//        $('.shuru').append(infoLi).append(checkLi).append(input);
        var container = $('<ul>').addClass('shuru-next');
        container.append(infoLi).append(checkLi).append(input);
        return container;
    }
    function renderHelp(sysnum,manager_class,size,name){
    	var div = $(`<ul class="create">
    	<li class="size">名称：<span>${manager_class}</span></li><li class="size">姓名：<span>${name}</span></li><li class="size">管理者名单 ${size}人</li>
    	<input value="${sysnum}" id="sysnum" type="hidden">
    	</ul>`);
    	var span = $('<span></span>').html('X').addClass('create-delete');
    	span.click(function(){
    		$(this).parent().remove();
    		var check_id = $('#check_id').val();
    		$.ajax({
    			method: 'POST',
    			data:{check_id:check_id,user_id:sysnum},
    			url: 'setting/help/delete',
    			success: function(ret){
    				if(ret.success){
    					$(this).parent().remove();
    				}else{
    					alert(ret.errorMessage);
    				}
    			}
    		})
    	})
    	div.append(span);
    	return div;
    }
    $('.create-delete').click(function(){
    	
    	var check_id = $('#check_id').val();
    	var parent = $(this).parent();
    	var sysnum = parent.find('.sysnum').val();
		$.ajax({
			method: 'POST',
			url: 'setting/help/delete',
			data:{check_id:check_id,user_id:sysnum},
			success: function(ret){
				if(ret.success){
					parent.remove();
				}else{
					alert(ret.errorMessage);
				}
			},
			error: function(ret){
				alert(ret);
			}
		})
    })
    // shouji 
    $('.submit').click(function(){
        var tel = $('#tel').val();
        var parent = $(this).parent();
        var pp = parent.parent();
        
        // var ret = {sysnum: 123, name: 456}
        // renderShuru(ret,tel);
        $.ajax({
            url: 'setting/help/tel_get',
            method: 'GET',
            data: {tel: tel},
            success: function(ret) {
            	if(ret.success){
            		ret = JSON.parse(ret.data);
            		parent.hide();
            		pp.append(renderShuru(ret,tel));
            	}else{
            		alert(ret.errorMessage);
            	}
                
                
            },
            error: function(err){alert(err)}
        })
    })
    //分配辅助
    $('#fuzhu').click(function () {
        $('#assign').toggle();
    });
 
    $('.add').click(function () {
        $('.shuru').show();
        $(this).hide();
    });
     $('.shan').click(function () {
         $('.add').show();
         $('.shuru').hide();
     });
 
    //模态框
    $(".jiancha").click(function () {
        $("#kuang").show();
        $("#meng").show();
    });
    $(".que").find(":input").click(function () {
        $("#kuang").hide();
        $("#meng").hide();
    });
 
    //全选，全不选
    $("#all").click(function () {
        if ($(this).find('label').hasClass('chek')) {
            $(this).find('label').removeClass('chek');
            $(".checkboxFour").find('label').removeClass('chek');
        } else {
            $(this).find('label').addClass('chek');
            $(".checkboxFour").find('label').addClass('chek');
        }
    });
    $('.list').find('label').click(function () {
        console.log($(this).hasClass('chek')); //测试true or false
        if ($(this).hasClass('chek')) {
            $(this).removeClass('chek');
        } else {
            $(this).addClass('chek');
        }
    });

  //设置周期
    $('#zhouqi').click(function () {
        $('.period').toggle();
    });
    //扣分规则    
    $(".one").click(function(){
        var index =  $(".one").index(this);  
        $(".one").eq(index).find("label").toggleClass("label");
        $(".rule").eq(index).toggle();
    });
    //点击添加展开输入框
    $(".plus").click(function(){
        $(this).prev().find(".add-decutMark").show();
    });
    $(".ok").click(function(){
        /*$(".decutMark").after("<p class="decutMark">
                <em class="num">1</em>
                <em class="score">2</em><i class="decute">&#xe659;</i>
            </p>");
        $(".num").text($(".reason").val());
        $(".score").text($(".add-score").val());*/
    });
    //解散课程
    //模态框
    $("#san").click(function(){
        $(".kuang1").show();
        $("#meng1").show();
    });
    $(".btn").find(":input").click(function(){
        $(".kuang1").hide();
        $("#meng1").hide();
    });
    var calculate_size = function () {
        var BASE_FONT_SIZE = 12;

        var docEl = document.documentElement,
            clientWidth = docEl.clientWidth;
        if (clientWidth<=640){
          docEl.style.fontSize = BASE_FONT_SIZE * (clientWidth / 320) + 'px';
        } else if(clientWidth>640){
          docEl.style.fontSize = BASE_FONT_SIZE * (640 / 320) + 'px';
        }
    };

    // Abort if browser does not support addEventListener
    if (document.addEventListener) {
        var resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize';
        window.addEventListener(resizeEvt, calculate_size, false);
        document.addEventListener('DOMContentLoaded', calculate_size, false);
        calculate_size();
    }
    var time = new Date();
    var date = time.getFullYear() + '-' + (time.getMonth() +1) + '-' + time.getDate();
    // $('#time4').val(date);
    //选择日期
     $("#time4").datetimePicker({
        onChange: function(){
        
        },
        onClose: function(date){
        	console.log(date.value.slice(0,3).join('/'));
        	// value是个数组 2014/3/4
        	$.ajax({
                url: 'setting/cycle/submit',
                method: 'POST',
                data: JSON.stringify({check_id:parseInt($('#check_id').val()),time: date.value.slice(0,3).join('/')}),
                contentType: "application/json;charset=utf8",
                success: function(ret) {
                	if(ret.success){
 	            		
	 	        	   }else{
	 	        		   alert(ret.errorMessage);
	 	        	   }
                },
                error: function(err){alert(err)}
            })
        }
    });

  //选择天数
  $("#mobile").picker({
    cols: [
      {
        textAlign: 'center',
        values: ['无周期','1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31','一个月']
      }
    ],
    onChange: function(p, v, dv) {
    },
    onClose: function(p, v, d) {
    	var time = p.value[0];
    	if(time === '无周期'){
    		time = 0;
    	}else if(time==='一个月'){
    		time = -1;
    	}
    	$.ajax({
    		
            url: 'setting/cycle/submit',
            method: 'POST',
            data: JSON.stringify({check_id:parseInt($('#check_id').val()),date: parseInt(time)}),
            contentType: "application/json;charset=utf8",
            success: function(ret) {
            	if(ret.success){
	            		
        	   }else{
        		   alert(ret.errorMessage);
        	   }
                
            },
            error: function(err){alert(err)}
        })
    }
  });
  
  
  $("#late").picker({
	    cols: [
	      {
	        textAlign: 'center',
	        values: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31']
	      }
	    ],
	    onChange: function(p, v, dv) {
	    },
	    onClose: function(p, v, d) {
	    	console.log(p.value[0])
	    	
	    }
	  });
  $("#leave").picker({
		    cols: [
		 	      {
		 	        textAlign: 'center',
		 	        values: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31']
		 	      }
		 	    ],
		 	    onChange: function(p, v, dv) {
		 	    },
		 	    onClose: function(p, v, d) {
		 	    	console.log(p.value[0])
		 	    	
		 	    }
		 	  });  
  $('.decute').click(function(){
	  var self = $(this);
	  var parent = self.parent();
	  var item = parent.attr('class').split(' ')[1];
	  var num = parent.find('.num').text();
	  console.log(item);
	  $.ajax({
   		   
           url: 'setting/cycle/submit',
           method: 'POST',
           data: JSON.stringify({check_id:$('#check_id').val(),item:item,status:parseInt(0),rule:num}),
           contentType: "application/json;charset=utf8",
           success: function(ret) {
        	   if(ret.success){
        		   parent.remove();
        	   }else{
        		   alert(ret.errorMessage);
        	   }
        	   
               
           },
           error: function(err){alert(err)}
       })
	  
  });
  
  function renderDecutMark(num1,num2,item){
      var p = $('<p></p>').addClass('decutMark '+item);
      p.html(`<em class="num">${num1}</em> <em class="score">${num2}</em>`);
      var i = $('<i></i>').addClass('decute').html('');
      i.click(function(){
    	  var self = $(this);
    	  var parent = self.parent();
    	  $.ajax({
      		   
              url: 'setting/cycle/submit',
              method: 'POST',
              data: JSON.stringify({check_id:$('#check_id').val(),item:item,status:parseInt(0),rule:num1}),
              contentType: "application/json;charset=utf8",
              success: function(ret) {
           	   if(ret.success){
           		   parent.remove();
           	   }else{
           		   alert(ret.errorMessage);
           	   }
           	   
                  
              },
              error: function(err){alert(err)}
          })
      });
      p.append(i);
      return p;
  };
  $('#submit-absence').click(function(e){
      var res = $('#absence').val(),
      	lack = $('#absence-num').val();
      var container = $(this).parent().parent().find('.decutMark-container');
      container.append(renderDecutMark(res,lack,"absence"));
      $('.add-decutMark').hide();
      $.ajax({
  		
          url: 'setting/cycle/submit',
          method: 'POST',
          data: JSON.stringify({check_id:$('#check_id').val(),item:'absence',status:parseInt(1),rule:[res,lack]}),
          contentType: "application/json;charset=utf8",
          success: function(ret) {
          	
          	if(ret.success){
           		
       	   }else{
       		   alert(ret.errorMessage);
       	   }
          },
          error: function(err){alert(err)}
      })
  });
  
  $('#late-num').blur(function(){
      var res = $('#late').val(),
            lack = $(this).val();
      $('.decutMark-container').append(renderDecutMark(res,lack,"late"));
      $('.add-decutMark').hide();
      $.ajax({
  		
          url: 'setting/cycle/submit',
          method: 'POST',
          data: JSON.stringify({check_id:$('#check_id').val(),item:'late',status:parseInt(1),rule:[res,lack]}),
          contentType: "application/json;charset=utf8",
          success: function(ret) {
          	
          	if(ret.success){
           		
       	   }else{
       		   alert(ret.errorMessage);
       	   }
          },
          error: function(err){alert(err)}
      })
  });
  $('#leave-num').blur(function(){
      var res = $('#leave').val(),
            lack = $(this).val();
      $('.decutMark-container').append(renderDecutMark(res,lack,"leave"));
      $('.add-decutMark').hide();
      $.ajax({
  		
          url: 'setting/cycle/submit',
          method: 'POST',
          data: JSON.stringify({check_id:$('#check_id').val(),item:'leave',status:parseInt(1),rule:[res,lack]}),
          contentType: "application/json;charset=utf8",
          success: function(ret) {
          	
          	if(ret.success){
           		
       	   }else{
       		   alert(ret.errorMessage);
       	   }
          },
          error: function(err){alert(err)}
      })
  });
});