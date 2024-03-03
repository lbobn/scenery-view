// pages/map/map.js
var app =  getApp();

  
Page({

  /**
   * 页面的初始数据
   */
  data: {
    latitude: 34.36616,
    longitude: 109.186207,
    scale: 13,
    // customCalloutMarkerIds: [1,2,3,4,5,6,7,8,9],
    markers: []/* [
      {
        id: 1,
        latitude: 34.36616,
        longitude: 109.186207,
        title: "图书馆",
        iconPath: "../../img/location.png",
        customCallout: {
          content: "图书馆",
          srcpath: "http://127.0.0.1:8080/image/1.jpg",
          anchorY: 0,
          anchorX: 0,
          display: 'ALWAYS'
        }
      }*/

  },

  turnToVR(marker) {
    /* 
    
    TODO
    */

    console.log(marker.detail.markerId)
    wx.showModal({
      title: '提示',
      content: '即将跳转至全景图',
      success(res) {
        if (res.confirm) {
          console.log('用户点击确定')
          wx.navigateTo({
            // url: "../vr/vr?panoramaUrl=image/img_0.png&panoramaCode=898989",
            //将景点标记的id传到VR页面
            url: "../vr/vr?markerId=" + marker.detail.markerId,
            /* events: {
              // 为指定事件添加一个监听器，获取被打开页面传送到当前页面的数据
              acceptDataFromOpenedPage: function (data) {
                console.log(data)
              },
              someEvent: function (data) {
                console.log(data)
              }
            }, */
            /* success: function (res) {
              // 通过eventChannel向被打开页面传送数据
              res.eventChannel.emit('acceptDataFromOpenerPage', { data: marker.detail })
            } */
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this
    wx.request({
      url: app.globalData.serverApi+ '/markers',
      method: 'GET',
      success(res) {
        // console.log("成功", res.data.data)
        that.setData({
          markers: res.data.data
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

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    this.onLoad()

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})