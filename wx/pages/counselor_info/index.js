var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userId = '';
var counselorId = '';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    itme: {},
  },

  onLoad: function (options) {
    const { id } = options;
    counselorId = id
    this.getInit();

  },
  /**
   * 获取登录用户信息
   */
  getInit() {
    userId = wx.getStorageSync("token")
    // 用户信息不存在跳转登录页面
    if (userId == null || userId == undefined || userId == '') {
      // 跳转到登录页面
      wx.reLaunch({
        url: '/pages/login/index'
      })
    } else {
      this.getInfo();
    }
  },

  getInfo() {
    call.getData('wx/getCounselorById?id=' + counselorId + "&userId=" + userId, this.onSuccessclassAll, this.onFaiclassAll);
  },
  onSuccessclassAll(res) {
    if (res.code == 20000) {
      this.setData({
        item: res.data.row
      })
      console.log(this.data.item);
    }
  },
  onFaiclassAll() {
    help.show("网络请求失败");
  },

  // 跳转在线咨询
  onlineBtn(){
    wx.navigateTo({
        url: '/pages/consult/index?id='+counselorId,
      })
  },
   
})