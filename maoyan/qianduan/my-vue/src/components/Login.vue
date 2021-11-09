<template>
	<div id="app">
		<div class="mainbox">
			<img src="../assets/maoyan.jpg" class="loginimg">
			<img src="../assets/mao.png" class="maoyan"/>
			<div class="loginbox" v-if="issuc==false">
				<input type="text" name="username" placeholder="请输入用户名" v-model="username" class="user"/>
				<input type="password" name="pwd" placeholder="请输入密码" v-model="pwd" class="pwd"/>
				<div class="loginbtn" @click="getdata">登录</div>
			</div>
			<div v-else class="loginbox" >
				欢迎您，尊敬的{{bendiname}}
			</div>
		</div>
	</div>
</template>

<script>
	export default{
		name:'Login',
		data(){
			return {
				username:'',
				pwd:'',
				issuc:false,
				bendiname:''
			}
			},
		methods:{
			getdata(){
				let name=this.username;
				let password=this.pwd;
				let param=new URLSearchParams();
				param.append('name',name);
				param.append('password',password);
				var that=this;
				this.$axios.post('apl/Lj/login',param)
				  .then(function (response) {
				    console.log(response.data[0]==undefined);
						if(response.data[0]!==undefined){
							sessionStorage.setItem('user',name);
							sessionStorage.setItem('pwd',password);
							that.$router.go(0)
						}else{
							alert('您的用户名或密码错误，请重新登录')
						}
				  })
				  .catch(function (error) {
				    console.log(error);
				  });
			}
		},
		mounted() {
			if(sessionStorage.getItem('user')!=null){
				this.bendiname=sessionStorage.getItem('user');
				this.issuc=true;
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
