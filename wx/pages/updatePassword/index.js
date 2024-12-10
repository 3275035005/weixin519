var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userId = '';

Page({

    /**
     * 页面的初始数据
     */
    data: {
        oldPassword:'',   //旧密码
        password:'',    //新密码
    },


    onLoad: function () {
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
    }
  },

   updateBtn(){
    if(this.data.oldPassword == ""){
    wx.showToast({
        title: '旧密码不能为空',
        icon: 'none',
        duration: 1000
    })
    return false;
    }
    if(this.data.password == ""){
      wx.showToast({
        title: '新密码不能为空',
        icon: 'none',
        duration: 1000
      })
      return false;
        }
        wx.showLoading({
          title: '修改中...'
        });
        call.request('wx/updatePassword',{
            oldPassword:this.data.oldPassword,
            password:this.data.password,
            id: userId
        }, this.onSuccess, this.onFail);
  },

  onSuccess(res) {
    wx.hideLoading();
    if(res.code == 20000){
      help.show('修改成功')
      setTimeout(function () { // 下单成功跳转页面
        wx.reLaunch({
          url: '/pages/wode/wode'
        })
      }, 2000)
    }else{
        help.show(res.message)
    }
  },

  onFail() {
    wx.hideLoading();
    help.show("网络请求超时,请稍后再试")
  },

})