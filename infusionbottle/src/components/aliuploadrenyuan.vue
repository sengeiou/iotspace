<template>
    <div class="vue-uploader">
        <div class="file-list">
            <section v-for="(file, index) of files" class="file-item draggable-item" :key="index">
                <img :src="file" alt="" ondragstart="return false;">
                <span class="file-remove" @click="remove(index)">+</span>
            </section>
            <section v-if="status == 'ready'" class="file-item">
                <div @click="add" class="add">+</div>
            </section>
        </div>
        <input type="file" @change="fileChanged" ref="file">
    </div>
</template>
<script>
    export default {
        props:["file",'fileNum','directory','server'],
        data() {
            return {
                status: 'ready',
                files: []
            }
        },
        watch:{
            file(a,b){
                this.files = []
                if(this.file != ''){
                    this.files.push(this.file);
                }
                if(this.files.length >= this.fileNum){
                    this.status = "yincang"
                }else{
                    this.status = 'ready';
                }
            }
        },
        mounted() {
            console.log(this.file)
            this.files = []
            if(this.file != ''){
                this.files.push(this.file);
            }
            if(this.files.length >= this.fileNum){
                this.status = "yincang"
            }else{
                this.status = "ready";
            }
        },
        methods: {
          //获取上传通行证
            getOssToken(){
                var _this = this;
                this.$ajax.get(this.server+'/api/oss/getPolicy',{
                    params:{
                        directory: this.directory,
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
                this.files = []
                if(this.files.length < this.fileNum){
                    this.status = "ready";
                }else{
                    this.status = false;
                }
                this.$emit('fileDel',true)

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
                    var filename = new Date()/1000*1000 + sessionStorage.getItem("userId")+".jpg";
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
                        if(_this.files.length >= _this.fileNum){
                            _this.status = false;
                        }
                        _this.$emit('upFileData',fileUrl)
                        this.$refs.file.value = '';
                    }).catch(error => {
                        _this.$message.error('上传图片失败,请重试！');
                    })
                }
            }
        }
      }
    
    
</script>
<style>
.vue-uploader {
    width: 100%;
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
    width: 200px;
    text-align: center;
}
.vue-uploader .file-list .file-item img{
    width: 200px;
    height: 180px;
    border-radius: 8px;
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
.vue-uploader .add {
    width: 200px;
    height: 160px;
    float: left;
    text-align: center;
    line-height: 160px;
    font-size: 30px;
    cursor: pointer;
    border: 1px solid #ddd;
    border-radius: 8px;
    border: 1px solid #ececec;
    background-size: 100% 100%;
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