if (typeof jQuery === 'undefined') {
  throw new Error('Bootstrap\'s JavaScript requires jQuery')
}
+function ($) {
  var version = $.fn.jquery.split(' ')[0].split('.')
  if ((version[0] < 2 && version[1] < 9) || (version[0] == 1 && version[1] == 9 && version[2] < 1)) {
    throw new Error('Bootstrap\'s JavaScript requires jQuery version 1.9.1 or higher')
  }
}(jQuery);

/* ========================================================================
 * collapse.js
 * ======================================================================== */


+function ($) {

var collapTitle = $('[data-toggle=accordion]');
var collapseContent = $("[data-name=content]");
collapseContent.hide();
collapseContent.each(function(indedx,ele){
  var ele = $(ele)
  if(typeof (ele.attr('active'))!='undefined'){
    ele.show();
  }
})
collapTitle.click(function(e){
  var targetName = $(e.target).attr('data-target');
  collapTitle.find('.ico').removeClass('rotate');
  $("[data-target="+targetName+"]").next().slideUp();
  if($(this).next().css("display")!="block"){
    $(this).next().slideDown();
    $(this).find('.ico').addClass('rotate')
  }
})


}(jQuery);
