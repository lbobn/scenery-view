var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    query: {},
    spotList: [
      // {
      //   name:'图书馆',
      //   images:['http://localhost:8080/image/school.png',''],
      //   phone:190219021,
      //   address:'西安工程大学',
      //   intro:'同学们学习的地方'
      // }

    ],
    page: 1, // 当前页数
    pageSize: 10, //一页有多少个数据
    total: 0, // 总页数
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this
    var token = app.globalData.token
    // 判断是否登录
    if(app.globalData.token == null || token.length == 0){
      //不做处理
    }else{
      //如果登录了就去后端查询是否收藏
      wx.request({
        url: app.globalData.serverApi+ '/favor/list',
        method: 'GET',
        header: {
          'content-type': 'application/json', // 默认值,
          'token': app.globalData.token
        },
        success(res) {
          // console.log("成功", res.data.data)
          that.setData({
            spotList: res.data.data
          })
  
        },
        fail(res) {
          console.error("失败", res.data)
        },
        complete() {
          console.log("调用完成")
        }
      })
     }
    var that = this;
    this.setData({
      query: options
    });
    // 获取景区列表数据
    // this.getShopList();
    
  },
  turnToVR(event) {
    var id = event.currentTarget.dataset.id
    //跳转VR页
    wx.navigateTo({
      url: "../vr/vr?markerId=" + id,
    })
  },
  turnToDetail(event){
    var id = event.currentTarget.dataset.id
    // 跳转详情页
    wx.navigateTo({
      url: "../detail/detail?spotId=" + id,
    })
  }
})
