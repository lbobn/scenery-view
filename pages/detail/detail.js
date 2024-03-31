// pages/detail/detail.js
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {  
    spot: {  
      // name: '文体馆',  
      // images: [  
      //   'http://localhost:8080/image/swiper1.png',  
      //   'http://localhost:8080/image/swiper2.png',  
      //   // ... 其他图片链接  
      // ],  
      // intro: '文化馆是县、市一级的群众文化事业单位，有的地方称文化中心、文化活动中心，作用是开展群众文化活动，并给群众文娱活动提供场所 。我国各省市基本都有文化馆，并且随着社会经济发展，人们精神文化水平的提高，越来越多的文化馆将被建设。在国外，正在兴建的很多孔子学院类似于宣传中国传统文化的文化馆。'  ,
      
    },  
    comments: [  
      // {
      //   "id": 5,
      //   "username": "Amber Jimenez",
      //   "scenery_name": "南环体育场",
      //   "comment": "To start working with your server in Navicat, you should first establish a connection               ",
      //   "like": 698,
      //   "create_time": "2024-03-30 22:04:50"
      // }
    ]  ,
    commentContent:'',
    like: false
  },  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this;
    //获取列表页传来的景点id
    console.log(options.spotId)
    // 获取景区详细信息
    wx.request({
      url: app.globalData.serverApi+ '/detail/'+options.spotId,
      method: 'GET',
      success(res) {
        // console.log("成功", res.data.data)
        that.setData({
          spot: res.data.data
        })

      },
      fail(res) {
        console.error("失败", res.data)
      },
      complete() {
        console.log("调用完成")
      }
    })

    // 获取评论数据
    wx.request({
      url: app.globalData.serverApi+ '/detail/comments/'+options.spotId,
      method: 'GET',
      success(res) {
        // console.log("成功", res.data.data)
        that.setData({
          comments: res.data.data
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

  handleCommentInput: function(e) {  
    // console.log(e.detail.value)
    this.setData({  
      commentContent: e.detail.value  
    });  
  },  
  
  submitComment: function() {  
    // 提交评论的逻辑  
    // ...  
    console.log(this.data.commentContent);
  
    // 清空输入框内容  
    this.setData({  
      commentContent: ''  
    });  
  },  
  
  handleCollect: function() {  
    // 收藏的逻辑  
    // ...  
    this.setData({  
      like: !this.data.like  
    });
  }  ,

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