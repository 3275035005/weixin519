// pages/results/results.js
const app = getApp();
var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
Page({
  data: {
    item:{}
  },

  onLoad(options) {
    call.getData('wx/getResultById/' + options.id , this.onSuccessAll, this.onFaiAll);
  },

  onSuccessAll(res) {
    if (res.code == 20000) {
      this.setData({
        item: res.data.data
      })
    }
  },
  onFaiAll() {
    help.show("网络请求失败");
  },

  // 再答一次
  toDoTestAgain(){
    wx.reLaunch({
      url: '../answer/index'
    })
  },

  // 返回首页
  toIndex(){
    wx.reLaunch({
      url: '../index/index'
    })
  },
})