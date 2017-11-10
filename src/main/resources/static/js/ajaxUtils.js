/**对ajax的基本封装
 * Created by judy on 2017/5/30.
 */
ajaxUtils = {
    /**
     *ajax post方法,promise,
     * ajaxUtils.post(url,data).done(function(){
     * }).fail(function(){});
     * @param url
     * @param data
     * @param context
     * @param async
     * @returns {*}
     */
    post: function (url, data, context) {
        var def = $.Deferred();
        $.ajax({
            url: url,
            data: data,
            type: 'post',
            beforeSend: function () {
                console.log('beforeSend');
            },
            complete: function () {
                console.log('complete is');
            },
            context: context,
            success: function (result) {
                def.resolve(result);
                //def.done(result);
            },
            error: function (err) {
                //def.fail(err);
                def.reject(err.responseText);
            }
        });
        return def;
    },
    /**
     *ajax的get方法
     * ajaxUtils.get(url).done(function(result){})  //成功
     * .fail(function(){})
     * @param url
     * @returns {*}
     */
    get: function (url,context) {
        var def = $.Deferred();
        $.ajax({
            url: url,
            type: 'get',
            beforeSend: function () {
                console.log('beforeSend');
            },
            complete: function () {
                console.log('complete is')
            },
            context: context,
            success: function (result) {
                def.resolve(result);
            },
            error: function (err) {
                def.reject(err.responseText);
            }
        });
        return def;
    }
};