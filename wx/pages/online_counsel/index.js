var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userId = '';

Page({

    /**
     * 页面的初始数据
     */
    data: {
        content: '',
        list: [],
        counselorId: '',
        flag: ''
    },

    onLoad: function (options) {
        const { counselorId, flag } = options;
        this.setData({
            counselorId: counselorId,
            flag: flag
        })
        this.getInit();
        if(flag == '0'){
            wx.setNavigationBarTitle({
                title: '在线咨询'
            })
        }else{
            wx.setNavigationBarTitle({
                title: '匿名咨询'
            })
        }
    },

    getInit() {
        userId = wx.getStorageSync("token")
        console.log(userId);
        // 用户信息不存在跳转登录页面
        if (userId == null || userId == undefined || userId == '') {
            // 跳转到登录页面
            wx.reLaunch({
                url: '/pages/login/index'
            })
        }
        this.getMess();
        this.getInterval();
    },

    getMess(){
        call.request('wx/getReply', {
            userId: userId,
            flag: this.data.flag,
            counselorId: this.data.counselorId
        }, this.onGetReplySuccess, this.onGetReplyFail);
    },
    getInterval(){
        let that = this;
        setInterval(()=>{
            call.request('wx/getReply', {
                userId: userId,
                flag: that.data.flag,
                counselorId: that.data.counselorId
            }, that.onGetReplySuccess, that.onGetReplyFail);
        },1000)
      },
    onGetReplySuccess(res) {
         this.setData({
            list: res.data.row
        })
      },
    
      onGetReplyFail() {
        wx.hideLoading();
        help.show("网络请求超时,请稍后再试")
      },
    //“发送”
    sendMess() {
        let that = this;
        let content = that.data.content;
        wx.showLoading({
            title: '发送中...'
          });
        call.request('wx/sendReply', {
            userId: userId,
            flag: this.data.flag,
            counselorId: this.data.counselorId,
            content: content
        }, this.onSuccess, this.onFail);
    },

    onSuccess(res) {
        this.setData({
            content: ''
        })
        this.getMess()
        wx.hideLoading();
      },
    
      onFail() {
        wx.hideLoading();
        help.show("网络请求超时,请稍后再试")
      },
})