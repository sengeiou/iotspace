<template>
    <div class="vue-uploader">
        <div>
            <el-button type="primary" size="small" style="font-size: 14px" @click="add">上传模型文件</el-button>
        </div>
        <div class="file-list" v-if="files != null">
            <section class="file-item draggable-item">
                <a :href="files">{{files}}</a>
                <span class="file-remove" @click="remove()">+</span>
            </section>
        </div>
        <input type="file" @change="fileChanged" ref="file">
    </div>
</template>
<script>
    export default {
        props:["file"],
        data() {
            return {
                files: null,
                userid: sessionStorage.getItem('userId')
            }
        },
        watch:{
            file(a,b){
                console.log(a,b)
                if(this.file){
                    this.files = a;
                }
            }
        },
        mounted() {
            if(this.file){
                this.files = this.file;
            }
        },
        methods: {
          //获取上传通行证
            getOssToken(){
                var _this = this;
                this.$ajax.get(this.baseURL1+'/base/api/oss/getPolicy',{
                    params:{
                        directory:'bim'
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
            remove() {
                this.files = null;
            },
            //oss路径 传给后台
            upOssPath(path){
                let params = this.$qs.stringify({
                    userid: this.userid,
                    item: path
                })
                this.$emit("path",path);
                console.log(path)
                 this.files = path;
            },
            fileChanged() {
                const list = this.$refs.file.files
                if (list.length == 0) {
                    alert("请选择文件");
                    return;
                }
                var _this = this;
                for(var i=0;i<list.length;i++){
                    var formData = new FormData();
                    var f=list[i];
                    console.log(f)
                    var filename = _this.userid+f.name;
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
                        _this.upOssPath(fileUrl);
                        this.$refs.file.value = '';
                    }).catch(error => {
                       console.log(error)
                    })
                }
            }
        }
      }
    
    
</script>
<style>
.vue-uploader {
    width: 100%;
    display: flex;
    justify-content: flex-start;
}
.vue-uploader .file-list {
    padding: 10px 0px;
}
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