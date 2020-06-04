var path = $("#path").val();
var img1="/statics/images/y.png";
var  img2="/statics/images/n.png";
var imgYes = "<img width='15px' src="+path+img1+" />";
var imgNo = "<img width='15px' src="+path+img2+" />";
/**
 * 提示信息显示
 * element:显示提示信息的元素（font）
 * css：提示样式
 * tipString:提示信息
 * status：true/false --验证是否通过
 */
function validateTip(element,css,tipString,status){
	element.css(css);
	element.html(tipString);
	
	element.prev().attr("validateStatus",status);
}
var referer = $("#referer").val();