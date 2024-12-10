var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userId = '';
var knowledgeId = '';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    itme: {},
  },

  onLoad: function (options) {
    const { id } = options;
    knowledgeId = id
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
    call.getData('wx/getKnowledgeById?id=' + knowledgeId + "&userId=" + userId, this.onSuccessclassAll, this.onFaiclassAll);
  },
  onSuccessclassAll(res) {
    if (res.code == 20000) {
      this.setData({
        item: res.data.row
      })
    }
  },
  onFaiclassAll() {
    help.show("网络请求失败");
  },

  // 收藏
  appointmentFavoriteBtn() {
    call.request('wx/appointmentFavorite', {
        knowledgeId: this.data.item.id,
        userId: userId,
    }, this.onSuccessappointmentFavoriteBtn, this.onFaiappointmentFavoriteBtn);
  },
  onSuccessappointmentFavoriteBtn(res) {
    if (res.code == 20000) {
      help.show("加入收藏成功");
      this.getInfo();
    } else {
      help.show(res.message);
    }
  },
  onFaiappointmentFavoriteBtn() {
    help.show("网络请求失败");
  },

  //取消收藏
  cancelAppointmentFavoriteBtn() {
    call.request('wx/cancelAppointmentFavorite', {
        knowledgeId: this.data.item.id,
        userId: userId,
    },  this.onSuccesscancelAppointmentFavoriteBtn, this.onFaicancelAppointmentFavoriteBtn);
  },
  onSuccesscancelAppointmentFavoriteBtn(res) {
    if (res.code == 20000) {
      help.show("取消收藏成功");
      this.getInfo();
    } else {
      help.show(res.message);
    }
  },
  onFaicancelAppointmentFavoriteBtn() {
    help.show("网络请求失败");
  },
})