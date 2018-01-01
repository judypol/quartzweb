<#include "../_share/_layout.ftl"/>
<@layout title='定时任务列表页面' headsection="" scriptsection="">
    <div class="page-container f-bg-f5f5f5">
        <div class="f-wma f-mt-20 f-mb-40">

            <div class="page-main">
                <div class="m-panel" style="min-height:942px;">
                    <ul class="nav-tabs">
                        <li class="z-active"><a href="/scheduler/list/1">定时任务列表</a></li>
                        <li class="add">
                            <div class="add-address">
                                <div class="buttons">
                                    <a class="u-button" href="/scheduler/taskEditPage/0">新增定时任务</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <div class="acc-record" id="taskList">
                        <div class="m-table">
                            <table width="100%">
                                <thead>
                                <tr>
                                    <th width="">任务名称</th>
                                    <th width="">定时配置</th>
                                    <th width="">冻结状态</th>
                                    <th width="">执行方</th>
                                    <th width="">执行方式</th>
                                    <th width="">最后修改时间</th>
                                    <th width="">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <template v-for="item in taskList">
                                        <tr>
                                            <td align="center">{{item.taskName}}</td>
                                            <td align="center">{{item.schedulerRule}}</td>
                                            <td align="center">
                                                <span v-if="item.frozenStatus=='UNFROZEN'">解冻</span>
                                                <span v-if="item.frozenStatus=='FROZEN'">冻结</span>
                                            </td>
                                            <td align="center">{{item.executorNo}}</td>
                                            <td align="center">{{item.sendType}}</td>
                                            <td align="center">{{item.lastModifyTime}}</td>
                                            <td align="center">
                                                <template v-if="item.frozenStatus!='RUNNING'">
                                                    <button v-if="item.frozenStatus=='FROZEN'" @click="start(item.id)">启动</button>
                                                    <template v-if="item.frozenStatus=='UNFROZEN'" >
                                                        <button @click="restart(item.id)">重启</button>
                                                        <button @click="pause(item.id)">暂停</button>
                                                    </template>
                                                    <button @click="execute(item.id)">立刻运行</button>
                                                </template>
                                                <a :href="'/scheduler/taskEditPage/'+item.id">修改</a>
                                                <a :href="'/scheduler/detail/'+item.id">详情</a>
                                            </td>
                                        </tr>
                                    </template>
                                </tbody>
                            </table>
                        </div>
                        <div class="m-page f-mt-40" id="page"></div>
                        <simplert :useIcon="false" ref="simplert">
                        </simplert>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="currentPage" value=""/>
    <input type="hidden" id="totalPage" value=""/>

    <script type="text/javascript">
        var app=new Vue({
            el:"#taskList",
            data:{
              taskList:[]
            },
            created:function(){
                var _self=this;
                axiosUtils.post('/scheduler/getTaskInfoList',{pageIndex:1})
                        .then(function(res){
                            if(res==undefined){
                                return false;
                            }
                            _self.taskList=res.data.content;
                            console.log(res);
                        })
                        .catch(function (err) {
                           console.log(err);
                        });
            },

            methods:{
                alert:function(title,onConfirm){
                    var obj={
                        title: title,
                        useConfirmBtn: true,
                        customConfirmBtnText: '确定',
                        customCloseBtnText:'取消',
                        onConfirm:onConfirm
                    };
                    this.$refs.simplert.openSimplert(obj);
                },
                pause:function(pa){
                    this.alert('确定要暂停该定时任务？',function(){
                        axiosUtils.post('/scheduler/delScheduler',{taskNo:pa})
                                .then(function (res) {
                                    console.log(res);
                                    window.location.href = '/scheduler/list/1';
                                })
                                .catch(function (err) {
                                    console.log(err);
                                });
                    });
                },
                start:function(pa){
                    this.alert('确定要启动该定时任务？',function(){
                        axiosUtils.post('/scheduler/addScheduler/',{taskNo:pa})
                                .then(function (res) {
                                    console.log(res);
                                    window.location.href = '/scheduler/list/1';
                                })
                                .catch(function (err) {
                                    console.log(err);
                                });
                    });
                },
                restartAll:function () {
                    this.alert('确定要全部重启定时任务？',function(){
                        axiosUtils.post('/scheduler/resumeSchedulerAll',{taskNo:pa})
                                .then(function (res) {
                                    console.log(res);
                                    window.location.href = '/scheduler/list/1';
                                })
                                .catch(function (err) {
                                    console.log(err);
                                })
                    });
                },
                restart:function (pa) {
                    this.alert('确定要重启该定时任务？',function () {
                        axiosUtils.post('/scheduler/resumeScheduler',{taskNo:pa})
                                .then(function (res) {
                                    console.log(res);
                                    window.location.href = '/scheduler/list/1';
                                })
                                .catch(function (err) {
                                    console.log(err);
                                })
                    });
                },
                execute:function (pa) {
                    this.alert('确定要立即执行当前任务一次？',function () {
                        axiosUtils.post('/scheduler/executeOnce',{taskNo:pa})
                                .then(function (res) {
                                    console.log(res);
                                    window.location.href = '/scheduler/list/1';
                                })
                                .catch(function (err) {
                                    console.log(err);
                                })
                    });
                }
            }
        });

        function changePage(param){
            $("#currentPage").val(param);
            window.location.href = '/scheduler/list/' + $("#currentPage").val();
        }
    </script>
</@layout>