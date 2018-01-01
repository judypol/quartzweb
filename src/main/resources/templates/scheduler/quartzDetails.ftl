<#include "../_share/_layout.ftl"/>
<@layout title="定时任务详情">
<div class="page-container f-bg-f8f8f8">
    <div class="f-wma f-mt-40 f-mb-100">
        <div class="order-detail">
            <div class="m-caption">定时任务详情</div>
            <div class="m-detail">
                <div class="item">
                    <div class="label"></div>
                    <div class="info" id="taskDetails"  v-cloak>
                        <input type="hidden" value="${taskNo}" ref="taskNo"/>
                        <p><span>任务名称 ：</span><span>{{detail.taskName }}</span></p>
                        <p><span>定时配置 ：</span><span>{{detail.schedulerRule }}</span></p>
                        <p><span>冻结状态 ：</span><span>{{detail.frozenStatus }}</span></p>
                        <p><span>执行方 ：</span><span>{{detail.executorNo }}</span></p>
                        <p><span>冻结时间 ：</span><span>{{detail.frozenTime }}</span></p>
                        <p><span>解冻时间 ：</span><span>{{detail.unfrozenTime }}</span></p>
                        <p><span>执行方式 ：</span><span>{{detail.sendType }}</span></p>
                        <p><span>url ：</span><span>{{detail.url }}</span></p>
                        <p><span>执行参数 ：</span><span>{{detail.executeParamter }}</span></p>
                        <p><span>创建时间 ：</span><span>{{detail.createTime }}</span></p>
                        <p><span>修改时间 ：</span><span>{{detail.lastModifyTime }}</span></p>
                        <p><span>上一次执行时间 ：</span><span>{{detail.preExecuteTime }}</span></p>
                        <p><span>上一次执行状态 ：</span><span>{{detail.taskStatus }}</span></p>
                    </div>
                </div>
            </div>
            <div class="m-action"><a href="javascript:history.back(-1);" class="u-button">关闭</a> </div>
            <div class="m-action"><a href="/records/taskRecords/${taskNo}/1/status" class="u-button">执行记录</a> </div>
        </div>
    </div>
</div>
<script>
    var app=new Vue({
        el:'#taskDetails',
        data:{
            detail:{
                taskNo:''
            },
        },
        mounted:function () {
            var _self=this;
            axiosUtils.post('/scheduler/getTaskDetails',{taskNo:_self.$refs.taskNo.value})
                    .then(function(res){
                        _self.detail=res.data;
                    })
                    .catch(function(error){
                        console.log(error);
                    });
        }
    });
</script>
</@layout>