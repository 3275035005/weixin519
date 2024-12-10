let counselorId = ''
Page({

    /**
     * 页面的初始数据
     */
    data: {},
    onLoad: function (options) {
        const { id } = options;
        counselorId = id
      },
    leaveButton(e){
      let id = e.currentTarget.dataset['index']
      wx.navigateTo({
        url: '/pages/online_counsel/index?flag='+id+'&counselorId='+counselorId,
      })
    },

})