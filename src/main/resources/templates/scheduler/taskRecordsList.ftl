<#include "../_share/_layout.ftl"/>
<@layout title="定时任务记录列表页面" headsection="" scriptsection="">
<div class="page-container f-bg-f5f5f5">
    <div class="f-wma f-mt-20 f-mb-40">

        <div class="page-main">
            <div id="taskRecords" class="m-panel" style="min-height:942px;">
                <input ref="taskNo" id="taskNo" value="${taskNo}" type="hidden">
                <input ref="pageIndex" value="${pageIndex}" type="hidden">
                <input ref="status" value="${status}" type="hidden">

                <ul class="nav-tabs">
                    <li class="z-active"><a href="/records/getTaskRecords/${taskNo}/1">定时任务纪录列表</a></li>
                    <li class="add">
                        <a class="expand" href="javascript:void(0)" onclick="expand(this)"></a>
                    </li>
                </ul>
                <form id="recordForm" method="post">
                    <div class="acc-record" style="display:none;" id="query">
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="control-group">
                                    <label class="control-label">执行状态:</label>
                                    <div class="controls">
                                        <select id="status" v-model="status">
                                            <option value="">所有</option>
                                            <option value="SUCCESS" >成功</option>
                                            <option value="FAILED" >失败</option>
                                            <option value="INIT" >初始</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="add-address" style="text-align:center">
                            <div class="buttons">
                                <a class="u-button" href="javascript:void(0)" onclick="queryForm()">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a class="u-button" href="javascript:void(0)" onclick="reset()">清空</a>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="acc-record">
                    <div class="m-table">
                        <table width="100%">
                            <thead>
                            <tr>
                                <th width="">timeKeyValue</th>
                                <th width="">执行时间</th>
                                <th width="">时长</th>
                                <th width="">执行状态</th>
                                <th width="">失败数</th>
                            </tr>
                            </thead>
                            <tbody>

                                <tr v-for="task in recordList">
                                    <td align="center">{{task.timeKeyValue}}</td>
                                    <td align="center">{{task.executeTime}}</td>
                                    <td align="center">{{task.time}}</td>
                                    <td align="center">
                                        <span v-if="task.taskStatus == 'INIT'">初始</span>
                                        <span v-else-if="task.taskStatus == 'SUCCESS'">成功</span>
                                        <a href="/taskErrors/getErrors/{task.id}" v-else="{task.taskStatus == 'FAILED'}">失败</c:if></a>
                                    </td>
                                    <td align="center">{{task.failcount}}</td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                    <div class="m-page f-mt-40" id="page"></div>
                </div>


            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var app=new Vue({
        el:"#taskRecords",
        data:{
            recordList:[],
            status:'status'
        },
        mounted:function(){
            var _self=this;

            var taskNo=_self.$refs.taskNo.value;
            var pageIndex=_self.$refs.pageIndex.value;
            var status=_self.$refs.status.value;
            var url="/records/getTaskRecordsList/"+taskNo+"/"+pageIndex+"/"+status;
            axiosUtils.get(url)
                    .then(function(res){
                        if(res==undefined){
                            return false;
                        }
                        _self.recordList=res.data.content;
                        console.log(res);
                    })
                    .catch(function (err) {
                        console.log(err);
                    });
        },
    });
</script>
</@layout>
