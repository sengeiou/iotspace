<template>
    <div class="vue-uploader">
        <div class="uploadBtn">
            <el-button size="mini" type="primary" @click="add">上传附件</el-button>
        </div>
        <div class="file-list">
            <section v-for="(file, index) of files" class="file-item draggable-item" :key="index">
                <span>{{file | strSplice}}</span>
                <span class="file-remove" @click="remove(index)">+</span>
            </section>
        </div>
        <input type="file" @change="fileChanged" ref="file">
    </div>
</template>
<script>
// import { Toast,Indicator } from 'mint-ui';
    export default {
        filters: {
            strSplice: function(str) {
                console.log(str.substr(str.lastIndexOf('/')))
              return str.substr(str.lastIndexOf('/'))
            }
        },
        props:['directory','flag','file'],
        data() {
            return {
                status: 'ready',
                files: []
            }
        },
        watch:{
            file(a,b){
                if(this.file != null){
                    this.files=[];
                    this.files.push(this.file);
                }
            }
        },
        mounted() {
            console.log(this.file);
            if(this.file != null){
                this.files.push(this.file);
            }
            
        //   this.getOrder();
        },
        methods: {
          //获取上传通行证
            getOssToken(){
                var _this = this;
                this.$ajax.get("/recovery/api/oss/getPolicy",{
                    params:{
                        directory: this.directory
                    }
                }).then(function(res){
                    console.log(res);
                    if(res.data.status == 260){
                        _this.aliyunOssToken = res.data;
                    }else{
                        _this.$message.error(res.data.message);
                    }
                }).catch(function(error){
                    console.log(error);
                })
            },
            //触发upfile
            add() {
                this.$refs.file.click();
                //获取oss签名
                this.getOssToken();
            },
            remove(index) {
                console.log(12312);
                this.files = [];
                this.$emit("delFile",'')
            },
            //oss路径 传给后台
            upOssPath(path){
                var _this = this;
                let params = this.$qs.stringify({
                    orderid: this.$route.query.orderId,
                    userid: sessionStorage.getItem("userId"),
                    img: path,
                    type: '文件'
                })
                this.$ajax.post("/well/api/file/creatFile",params).then(function(response){
                    console.log("oss路径上传",response)
                    if(response.data.status == 260){
                        
                    }else{
                        // Toast({
                        //     message: response.data.message,
                        //     position: 'top'
                        // });
                    }
                })
            },
            fileChanged() {
                const list = this.$refs.file.files
                if (list.length == 0) {
                    alert("请选择文件");
                    return;
                }
                // //开启加载
                // Indicator.open({
                //     text: '上传中',
                //     spinnerType: 'fading-circle'
                // });
                var _this = this;
                for(var i=0;i<list.length;i++){
                    var formData = new FormData();
                    var f=list[i];
                    var filename = new Date().getTime() + sessionStorage.getItem("userId")+f.name;
                    formData.append('key', _this.aliyunOssToken.dir + filename); //存储在oss的文件路径
                    formData.append('OSSAccessKeyId', _this.aliyunOssToken.accessid); //accessKeyId
                    formData.append('policy', _this.aliyunOssToken.policy); //policy
                    formData.append('Signature', _this.aliyunOssToken.signature); //签名
                    //如果是base64文件，那么直接把base64字符串转成blob对象进行上传就可以了
                    formData.append("file",f);
                    formData.append('success_action_status', 200); //成功后返回的操作码

                    var url = _this.aliyunOssToken.host;
                    var fileUrl = _this.aliyunOssToken.host + _this.aliyunOssToken.dir + filename;

                    this.$ajax.post(url, formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(res => {
                        _this.files.push(fileUrl);
                        if(_this.files.length >= 3){
                            _this.status = false;
                        }
                        fileUrl
                        this.$emit("fileUrl",fileUrl);
                        this.$refs.file.value = '';
                        console.log(res)
                        console.log(fileUrl);
                        //关闭加载
                        // Indicator.close();
                    }).catch(error => {
                        // Toast({
                        //     message: '上传文件失败,请重试！',
                        //     position: 'top'
                        // });
                        //关闭加载
                        // Indicator.close();
                    })
                }
            }
        }
      }
    
    
</script>
<style scoped>
/* .vue-uploader .file-list {
    padding: 10px 0px;
} */
.vue-uploader .file-list:after {
    content: '';
    display: block;
    clear: both;
    visibility: hidden;
    line-height: 0;
    height: 0;
    font-size: 0;
}
.vue-uploader .file-list .file-item {
    float: left;
    margin-left: 10px;
    position: relative;
    text-align: center;
}
.vue-uploader .file-list .file-item a{
    height: 40px;
    border-radius: 8px;
    line-height: 40px
}
.vue-uploader .file-list .file-item .file-remove {
    position: absolute;
    right: -8px;
    /* display: none; */
    top: -8px;
    width: 20px;
    height: 20px;
    font-size:20px;
    text-align: center;
    color: white;
    cursor: pointer;
    line-height: 20px;
    border-radius: 100%;
    transform: rotate(45deg);
    background: rgba(0, 0, 0, 0.5);
}
.vue-uploader .file-list .file-item:hover .file-remove {
    display: inline;
}
.vue-uploader .file-list .file-item .file-name {
    margin: 0;
    height: 40px;
    word-break: break-all;
    font-size: 14px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
}
.vue-uploader .upload-func {
    display: flex;
    padding: 10px;
    margin: 0px;
    background: #f8f8f8;
    border-top: 1px solid #ececec;
}
.vue-uploader .upload-func .progress-bar {
    flex-grow: .8;
}
.vue-uploader .upload-func .progress-bar section {
    margin-top: 5px;
    background:#00b4aa;
    border-radius: 3px;
    text-align: center; 
    color: #fff;
    font-size: 12px;
    transition: all .5s ease;
}
.vue-uploader .upload-func .operation-box {
    flex-grow: 0;
    padding-left: 10px;
}
.vue-uploader .upload-func .operation-box button {
    padding: 4px 12px;
    color: #fff;
    background: #007ACC;
    border: none;
    border-radius: 2px;
    cursor: pointer;
}
.vue-uploader > input[type="file"] {
    display: none;
}
</style>