<template>
	<div id="app">
		<div class="mainbox">
			<img src="../assets/maoyan.jpg" class="loginimg">
			<img src="../assets/mao.png" class="maoyan"/>
			<div class="loginbox" >
				<input type="text" name="username" placeholder="用户名" v-model="username" class="user"/>
				<input type="password" name="pwd" placeholder="密码" v-model="pwd" class="pwd"/>
				<div class="loginbtn" @click="register">注册</div>
			</div>
		</div>
		</div>
</template>

<script>
	export default{
		name:'Resiger',
		data(){
			return {
				username:'',
				pwd:''
			}
		},
		methods:{
			register(){
				let name=this.username;
				let password=this.pwd;
				let param=new URLSearchParams();
				param.append('name',name);
				param.append('password',password);
				this.$axios.post('apl/Lj/register',param)
				  .then(function (response) {
				    console.log(response);
						console.log("注册成功");
						
				  })
				  .catch(function (error) {
				    console.log(error);
				  });
			}
		}
	}
</script>

<style>
	.mainbox{
			position: relative;
			overflow: hidden;
			height: 520px;
		}
	.loginimg{
		width: 125px;
		height: 80px;
		position: absolute;
		top:20px;
		left:20px;
	}
	.loginbox{
		width: 300px;
		height: 400px;
		position: absolute;
		bottom: 0px;
		right: 200px;
	}
	.maoyan{
		width: 440px;
		position: absolute;
		top:120px;
		left:100px;
	}
	.loginbtn{
		width: 280px;
		height: 50px;
		background-color:rgb(231,54,59);
		border-radius: 5px;
		color: white;
		text-align: center;
		line-height: 50px;
	}
	.user{
		display: inline-block;
		width: 280px;
		height:40px;
		margin-bottom: 30px;
	}
	.pwd{
		display: inline-block;
		width: 280px;
		height:40px;
		margin-bottom: 50px;
	}
</style>
