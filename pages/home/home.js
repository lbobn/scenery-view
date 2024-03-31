// pages/home/home.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgURLs: [app.globalData.serverApi + '/image/swiper1.png', app.globalData.serverApi+'/image/swiper2.png'],
    indicatorDots: true,
    vertical: false, //滑动方向是否为纵向
    autoplay: true,  //是否自动切换
    interval: 3500,  //自动切换时间间隔
    duration: 500,   //滑动动画时长
    activeColor: "#ffffff",  //当前选中的指示点颜色
    circular: true    // 是否采用衔接滑动

  },
  toMap:function(){
    console.log("跳转地图")
    wx.switchTab({
      url: '/pages/map/map',
      success: (result) => {
        
      },
      fail: () => {},
      complete: () => {}
    });
      
      
  },

  toDetail:function(){
    console.log("跳转详情")
    wx.navigateTo({
      url: '../list/list',
    })
  },
  toVRList:function(){
    console.log("跳转VR")
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

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