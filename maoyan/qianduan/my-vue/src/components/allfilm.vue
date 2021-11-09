<template>
	<div>
		<div class="allfilmbox">
			
			<el-col :span="5" :offset='1' v-for="item in alldata" :key="item.filmId">	<div class="film">
			<router-link :to="'/detail?filmId='+item.filmId">
				<div class="filmbox">
					<img :src="item.poster" class="filmimg" />
					<div class="juzhong">{{item.name}}</div>
					<div class="juzhong yanse">评分:{{item.grade}}</div>
				</div>
			</router-link>
				<div class="filmmes">
					<div>{{item.name}}</div>
					<div>类型:{{item.category}}</div>
					<!-- <div>{{item.actors.name}}/{{item.actors.name}}</div> -->
				</div>
			</div></el-col>
		</div>
	</div>
</template>

<script>
	export default{
		name:'allfilm',
		data(){
			return {
				alldata:[]
			}
		},
		mounted() {
			this.$nextTick(function(){
				var that=this;
				this.$axios.get('api/getNowPlayingFilmList?pageNum=1&pageSize=50').then(
				function(response){
					console.log(response.data.data.films);
					that.alldata=response.data.data.films;
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
	.allfilmbox{
		overflow: hidden;
	}
	.filmbox{
		position: absolute;
		left: 0px;
		top:0px
	}
	.film{
		height: 316px;
		width: 200px;
		position: relative;
	}
	.el-col{
		margin-bottom: 30px;
	}
	.film:hover .filmmes{
			bottom: 50px;
			opacity: 1;
	}
	.juzhong{
		text-align: center;
	}
	.yanse{
		color: orange;
	}
	.filmimg{
		height: 270px;
		width: 200px;
	}
	.filmmes{
		color: white;
		position: absolute;
		left:40px;
		opacity: 0;
		transition: all ease-in-out;
	}
</style>
