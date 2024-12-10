function show(msg){
  wx.showToast({
    title: msg,
    icon: 'none',
    duration: 2000
  })
}
function okShow(msg){
  wx.showToast({
    title: msg,
    icon: 'success',
    duration: 2000
  })
}



/**
 * module.exports用来导出代码
 * js文件中通过var help = require("../util/help.js")  加载
 * 在引入引入文件的时候"  "里面的内容通过../../../这种类型，小程序的编译器会自动提示，因为你可能
 * 项目目录不止一级，不同的js文件对应的工具类的位置不一样
 */
module.exports.show = show;
module.exports.okShow = okShow;
