<template>
    <div>
        <h3>welcome to management system,{{user.username}}</h3>
    </div>
</template>

<script>
export default {
    data(){
        return{
            user:{
                username:''
            }
        }
    },
    created(){
        this.user = JSON.parse(window.sessionStorage.getItem("user"))
    }
    
}
</script>

<style lang="less" scoped>

</style>