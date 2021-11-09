<template>
	<div class="detailmain">
		<img :src="film.poster" class="detailimg" style="width: 232px;height: 322px;"/>
		<div class="detailbox1">
			<ul >
				<li class="detailname">{{film.name}}</li>
				<li class="detailcategory">{{film.category}}</li>
				<li class="detailnation">{{film.nation}}/{{film.runtime}}分钟</li>
			</ul>
			<el-button  icon="el-icon-lollipop" class="btn">想看</el-button>
			<el-button  icon="el-icon-star-off" class="btn">收藏</el-button>
			<slot name="btngetpiao"><el-button  class='getpiao'>
			<router-link :to="'/filmLocation?filmId='+film.filmId" class="getpiaoy">
				特惠购票
			</router-link>
			</el-button></slot>
		</div>
	
		  
		<div class="detailbox2">
			<p style="color: white;">猫眼口碑</p>
			<el-rate
			  v-model="value"
			  disabled
			  show-score
			  text-color="#ff9900"
			  score-template="{value}">
			</el-rate>
		</div>
	</div>
</template>

<script>
	export default{
		name:"filmHead",
		data(){
			return{
			film:[]	,
			value:2,
			activeName: 'first',
			}
		},
		mounted() {
				var that=this;
				var filmId=that.$route.query.filmId
				this.$nextTick(function(){
					this.$axios.get('api/getFilmInfo?filmId='+filmId).then(
					function(response){
						console.log(response.data.data.film);
						that.film=response.data.data.film;
						that.value=parseInt(response.data.data.film.grade/2);
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
		padding: 0px;
		margin: 0px;
	}
	.detailmain{
		background: -webkit-radial-gradient(top left,ellipse,#744550,#3A2F59);
		width: 100%;
		position: absolute;
		top:0px;
	}
	.detailmes{
		width: 50%;
		position:absolute;
		left: 20px;
		top:500px
	}
	ul{
		list-style-type: none;
	}
	.detailbox1{
		float: left;
		margin-top:80px;
		margin-left:20px;
		width:232px;
		height:332px;
	}
	.detailbox2{
		float: left;
		width:232px;
		margin: 10px;
		margin-top: 360px;
	}
	.detailimg{
		float: left;
		margin-top:80px;
		margin-left: 100px;
	}
	.detailname{
		font-size: 25px;
		color: white;
	}
	.detailcategory{
		color:white;
		margin-top: 20px;
	}
	.detailnation{
		color: white;
		margin-bottom: 80px;
	}
	.el-button{
		
	}
	.getpiao{
		width: 182px;
		background-color: red;
		color: white;
	}
	.btn{
		margin-bottom: 30px;
	}
	
	.el-button+.el-button {
	    margin-left: 0px;
	}
	.actor{
		display:flex;
		flex-wrap: wrap;
		
	}
	.actorbox{
		width: 30%;
		text-align: center;
		margin-bottom: 20px;
	}
	.miaoshu{
		text-align: center;
	}
	.miaoshuimg{
		width:139px;
		height:200px;
	}
	.getpiaoy{
		text-decoration: none;
		color: white;
	}
	.getpiaoy:hover{
		color: black;
	}
</style>
