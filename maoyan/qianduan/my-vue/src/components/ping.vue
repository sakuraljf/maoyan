<template>
	<div>
		<div class="pingfen">
			<div class="pingname">电影:{{filmName}}</div>
			<form>
				<div class="block" style="margin-bottom: 10px;">
				  <span class="demonstration">为你喜欢的电影打个分吧</span>
				  <el-rate v-model="value1"></el-rate>
				</div>
			</form>
			<el-input
			  type="textarea"
			  :rows="5"
			  placeholder="请输入内容"
			  v-model="textarea">
			</el-input>
			<div class="ping" @click="ping">
				立即评价
			</div>
		</div>
		
	</div>
</template>

<script>
	export default{
		name:'ping',
		   data() {
		      return {
						value1: null,
						textarea: '',
						filmName:''
		      }
		    },
			methods:{
				ping(){
					var dingdata=this.$route.query;
					var filmId=dingdata.filmId;
					var time=dingdata.time;//这个是订单支付的time
					
					var date=new Date();
					var year=date.getFullYear()
					var mouth=date.getMonth()+1;
					var day=date.getDate();
					//时间
					var timeping=year+'年'+mouth+'月'+day+'日'//这个是评价的时间
					var value1=this.value1;
					var content=this.textarea;
					var username=sessionStorage.getItem('user');
					console.log(filmId,timeping,value1,content,username);
					//第一步，将评价存进数据库
					let param=new URLSearchParams();
					param.append('username',username);
					param.append('time',timeping);
					param.append('grade',value1);
					param.append('content',content);
					param.append('filmId',filmId);
					console.log(username,timeping,value1,content,filmId);
					this.$axios.post('apl/Lj/ping',param)
					  .then(function (response) {
					    console.log(response);
							console.log("增加评价成功");
					  })
					  .catch(function (error) {
					    console.log(error);
					  });
					//第二步，删除当前评价，用订单时间和用户名删除
					let params=new URLSearchParams();
					params.append('username',username);
					params.append('time',time);
					console.log(username,time);
					this.$axios.post('apl/Lj/Delding',params)
					  .then(function (response) {
					    console.log(response);
							console.log("删除订单成功");
					  })
					  .catch(function (error) {
					    console.log(error);
					  });
					
					// 跳到电影详情
					this.$router.push('/detail?filmId='+filmId);
				}
			},
			mounted() {
				this.$nextTick(function(){
					let dingdata=this.$route.query
					this.filmName=dingdata.filmName;
				})
			}
	}
</script>

<style>
	.pingfen{
		width: 60%;
		height: 300px;
		border: 1px dashed silver ;
		margin: auto;
		margin-top: 100px;
		overflow: hidden;
	}
	.pingname{
		margin-bottom: 20px;
	}
	.ping{
		width: 100px;
		height:36px;
		color: white;
		background-color: rgb(64,158,255);
		text-align: center;
		line-height: 36px;
		border-radius: 18px;
		float: right;
		margin-top: 20px;
	}
</style>
