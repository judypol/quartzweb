<#include "../_share/_layout.ftl"/>
<@layout title="">
<div class="page-container f-bg-f5f5f5">
    <div class="f-wma f-mt-20 f-mb-40">
        <div class="page-main">
            <div class="m-panel" style="min-height:942px;">
                <ul class="nav-tabs">
                    <li class="z-active">新增定时任务</li>
                </ul>
                <div class="layer-edit-address layer-add-address">
                    <div class="content">
                        <form id="taskForm">
                            <input value="${id}" ref="taskId" type="hidden">
                            <div class="form-edit-address f-cf">
                                <#--<div class="item">-->
                                    <#--<div class="label">任务编号：</div>-->
                                    <#--<div class="info">-->
                                        <#--<div class="u-text">-->
                                            <#--&lt;#&ndash;<input type="text" name="taskNo" id="taskNo" v-model="taskInfo.taskNo"&ndash;&gt;-->
                                                   <#--&lt;#&ndash;maxlength="50"/>&ndash;&gt;-->
                                            <#--<span id="taskNo">{taskInfo.taskNo}</span>-->
                                        <#--</div>-->
                                    <#--</div>-->
                                <#--</div>-->
                                <div class="item">
                                    <div class="label">任务名称：</div>
                                    <div class="info">
                                        <div class="u-text">
                                            <input type="text" id="taskName" name="taskName" v-model="taskInfo.taskName"
                                                   maxlength="50"/>
                                        </div>
                                        <div class="u-tips" style="display:block;" id="taskNameErr"></div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="label">定时配置：</div>
                                    <div class="info">
                                        <div class="u-text">
                                            <input type="text" id="schedulerRule" name="schedulerRule"
                                                   v-model="taskInfo.schedulerRule" maxlength="50"/><span>(如：0 0 12 * * ? 每天中午12点触发)</span>
                                        </div>
                                        <div class="u-tips" style="display:block;" id="schedulerRuleErr"></div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="label">冻结状态：</div>
                                    <div class="info">
                                        <div class="u-text">
                                            <select id="frozenStatus" name="frozenStatus"
                                                    v-model="taskInfo.frozenStatus">
                                                <option value=""></option>
                                                <option value="FROZEN" v-if="taskInfo.frozenStatus ==  'FROZEN'"
                                                        selected="selected">冻结
                                                </option>
                                                <option value="FROZEN" v-else>冻结</option>
                                                <option value="UNFROZEN" v-if="taskInfo.frozenStatus ==  'UNFROZEN'"
                                                        selected="selected">解冻
                                                </option>
                                                <option value="UNFROZEN" v-else>解冻</option>

                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="label">执行方：</div>
                                    <div class="info">
                                        <div class="u-text">
                                            <input type="text" id="executorNo" name="executorNo"
                                                   v-model="taskInfo.executorNo" maxlength="50"/>
                                        </div>
                                        <div class="u-tips" style="display:block;" id="executorNoErr"></div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="label">执行方式：</div>
                                    <div class="info">
                                        <div class="u-text">
                                            <select id="sendType" name="sendType" v-model="taskInfo.sendType">
                                            <#--<option value="mq">mq</option>-->
                                                <option value="http">rpc</option>
                                                <option value="http">http</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="label">url：</div>
                                    <div class="info">
                                        <div class="u-text">
                                            <input type="text" id="url" name="url" v-model="taskInfo.url"
                                                   maxlength="255"/>
                                        </div>
                                        <div class="u-tips" style="display:block;" id="urlErr"></div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="label">参数：</div>
                                    <div class="info">
                                        <div class="u-text">
                                            <textarea rows="10" cols="40" id="executeParamter" name="executeParamter"
                                                      maxlength="2000" v-model="taskInfo.executeParamter"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <#--<div class="item">-->
                                    <#--<div class="label">timeKey：</div>-->
                                    <#--<div class="info">-->
                                        <#--<div class="u-text">-->
                                            <#--<input type="text" id="timeKey" name="timeKey"-->
                                                   <#--v-model="taskInfo.timeKey"/><span>(如：yyyy-MM-dd HH:mm)</span>-->
                                        <#--</div>-->
                                        <#--<div class="u-tips" style="display:block;" id="timeKeyErr"></div>-->
                                    <#--</div>-->
                                <#--</div>-->
                                <div class="item">
                                    <div class="info">
                                        <input type="button" id="edit" value="保存" class="u-button" @click="save()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="button" id="closeBtn" value="关闭" class="u-button"
                                               @click="close()"/>
                                        <div class="u-tips" style="display:block;"></div>
                                    </div>
                                </div>
                            </div>
                            <simplert :useIcon="false" ref="simplert">
                            </simplert>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var app = new Vue({
        el: '#taskForm',
        data: {
            taskInfo: {
                taskNo: '',
                taskName: '',
                schedulerRule: '',
                executorNo: '',
                url: '',
                timeKey: '',
                id: '',
                sendType: 'http',
                version: '',
                executeParamter: '',
            },
        },
        mounted: function () {
            var id = this.$refs.taskId.value;
            var _self = this;
            axiosUtils.post('/scheduler/taskInfo', {id: id})
                    .then(function (res) {
                        _self.taskInfo=res.data;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
        },
        methods: {
            init: function () {
                
            },
            alert:function(title,onClose){
                var obj={
                    title: title,
                    customCloseBtnText:'确定',
                    onClose:onClose
                };
                this.$refs.simplert.openSimplert(obj);
            },
            save: function () {
                var _self=this;
                axiosUtils.post('/scheduler/edit',this.taskInfo,'json')
                        .then(function(res){
                            if(res.data.status==true){
                                _self.alert(res.data.message,function(){
                                    window.location.href = '/scheduler/list/1'
                                });
                            }else{
                                _self.alert(res.data.message,function(){
                                    window.location.href = '/scheduler/list/1'
                                });
                            }
                        })
                        .catch(function(err){
                            console.log(err);
                        });
            },
            getTaskInfo: function () {

            },
            close: function () {
                window.location.href = '/scheduler/list/1'
            }
        }
    })
</script>
</@layout>