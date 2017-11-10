/**
 * Created by lizhihua on 2017/8/4.
 */
axiosUtils={
    /**
     *
     * @param url
     * @param obj
     * @param option=json/upload,默认是form提交
     * @returns {*}
     */
    post: function (url,obj,option) {
        var headers={};
        var postData={};
        if(option==undefined||option=='form'){
            headers={'Content-Type': 'application/x-www-form-urlencoded'};
            postData=this.object2FormData(obj);
        }

        if(option=='upload'){
            headers={'Content-Type': 'multipart/form-data'};
            postData=this.object2FormData(obj);
        }else if(option=='json'){
            headers={'Content-Type': 'application/json'}
            postData=JSON.stringify(obj);
        }
        return axios.post(url,postData,{headers:headers});
    },
    /**
     * get方式请求
     * @param url
     */
    get:function(url){
        return axios.get(url)
    },
    /**
     *
     * @param obj
     * @returns {*}
     */
    object2FormData:function(obj){
        var form_data = new FormData();

        for ( var key in obj ) {
            form_data.append(key, obj[key]);
        }
        return form_data;
    },
    requestInterceptors:function () {
        axios.interceptors.request.use(function (config) {
            return config;
        },function (error) {
            return Promise.reject(error);
        });
    },
    responseInterceptors:function () {
        axios.interceptors.response.use(function (response) {
            return response;
        },function (error) {
            return error;
        });
    }
};