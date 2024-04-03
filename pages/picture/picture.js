var app = getApp()
Page({
  data: {
    item:{
    },
    msg1:'1',
    noramalData: [
        //   数据示例
        // {
        //   src:'http://localhost:8080/image/swiper/swiper1.jpg',
        //   title:"名称",
        //   address:"位置信息"
        // },
      ]
       
    
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var that = this;
    wx.request({
      url: app.globalData.serverApi+ '/images',
      method: 'GET',
      success(res) {
        // console.log("成功", res.data.data)
        that.setData({
          noramalData: res.data.data
        })
      },
      fail(res) {
        console.error("失败", res.data)
      },
      complete() {
        console.log("调用完成")
      }
    })
  },
})
