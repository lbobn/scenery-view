// pages / panoramView / index.js
var app = getApp();
Page({
    data: {
        isShow: false,

        url: '', //跳转的H5全景图地址
        panoramaUrl: '', //全景图地址
        panoramaCode: '', //全景图Code
        token: wx.getStorageSync('token')
    },


/**
 * 生命周期函数--监听页面加载
 */
onLoad: function (options) {
    var that = this;
    var url = app.globalData.serverApi + '/h5.html'
    //url：你要跳转的Html5全景图页面地址
    //获取map页面传来的景点id
    console.log(options.markerId)
    //根据id获取对应全景的信息（其中包含URL等信息）
    wx.request({
        url: app.globalData.serverApi + '/vr/' + options.markerId,
        data: {},
        header: {'content-type':'application/json'},
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: (result) => {
            // result.data.data.vrUrl 中即为VR的url
            // result.data.data.vhUrl 为虚拟人url
            console.log(result.data.data)
            console.log(app.globalData.userInfo.id)
            console.log(app.globalData.serverApi + '/h5.html' + '?panoramaUrl=' + result.data.data.vrUrl + '&videoUrl=' + result.data.data.vhUrl)
            that.setData({
                url: app.globalData.serverApi + '/h5.html' + '?panoramaUrl=' + result.data.data.vrUrl + '&videoUrl=' + result.data.data.vhUrl +
                '&userId=' + app.globalData.userInfo.id +
                '&sceneryId=' + options.markerId
                // panoramaUrl: result.data.data.url,
            })
            // console.log("成功后："+that.data.panoramaUrl)
        },
        fail: () => {
            console.log("失败")
        },
        complete: () => {}
    });
},

})