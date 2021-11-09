<template>
	<div>
		<film-head>
			<template v-slot:btngetpiao>
				<el-button  class='getpiao'>查看更多电影详情</el-button>
			</template>
		</film-head>
		<ul class="box2">
			<li class="yingyuan" v-for="item in Cinema" :key=item.cinemaId>
				<div>{{item.name}}</div>
				<div class="dizhi">{{item.address}}</div>
				<div>
					<span class="bluebox">退</span>
					<span class="bluebox">改签</span>
					<span class="bluebox">折扣卡</span>
				</div>
				<div class="mai">
					<router-link :to="'/Buy?cinemaId='+item.cinemaId+'&filmId='+filmId" class="getpiaoy">
						选座购票
					</router-link>
				</div>
			</li>
	
		</ul>
	</div>
</template>

<script>
	import filmHead from './filmHead.vue'
	export default{
		name:"filmLocation",
		components:{
			filmHead
		},
		data(){
			return {
				// 没有定位,模拟一下衡阳市的影院
				Cinema:[],
				filmId:0
			}
		},
		mounted() {
			this.$nextTick(function(){
				var that=this;
				var filmId=that.$route.query.filmId;
				that.filmId=filmId;
					this.$axios.get('api/getCinemaList?cityId=430400&pageNum=1&pageSize=10').then(
					function(response){
						console.log(response.data.data.cinemas);
						that.Cinema=response.data.data.cinemas;
					}).catch(
					function (error) {
					    // 处理错误情况
					    console.log(error);
					  }
					)
			})
		}
	}
</script>

<style>
	*{
		margin: 0px;
		padding: 0px;
	}
	.yingyuan{
		border: 1px dashed silver;
		margin-bottom: 5px;
		position: relative;
		list-style-type: none;
	}
	.dizhi{
		margin-top: 5px;
		color: silver;
		margin-bottom: 10px;
	}
	.bluebox{
		color:rgb(80,159,201);
		border-radius: 2px;
		border: 1px solid rgb(80,159,201);
	}
	.mai{
		color: white;
		background-color: red;
		border-radius: 5px;
		position: absolute;
		top:20px;
		right:50px;
		width: 80px;
		height: 30px;
		text-align: center;
		line-height: 30px;
	}
	.mai:hover{
		opacity: 0.5;
	}
	ul.box2{
		position: absolute;
		top: 500px;
		width: 100%;
	}
</style>
