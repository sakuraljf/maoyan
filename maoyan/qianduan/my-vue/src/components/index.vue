<template>
	<div>
	<!-- 	<h1>我是首页</h1>
		<button @click="getdata">获取数据</button> -->
		<el-container>
		  <el-main>
				<h1 style="color: red;text-align: center; margin-bottom: 20px;margin-top: 30px;">正在热映</h1>
				<router-link to="/allfilm">
					<p style="color: red;text-align: right; margin-bottom: 20px;margin-top: 30px;">全部></p>
				</router-link>
				<el-row>
				  <el-col :span="5" v-for="(item, index) in data" :key="item.filmId" :offset="1">
						<router-link :to="'/detail?filmId='+item.filmId">
				    <el-card :body-style="{ padding: '0px'}" >
				      <img :src="item.poster" class="image">
				      <div >
				        <span class="filmname">{{item.name}}</span>
								<span class="filmgrade">{{item.grade}}</span>
				        <div class="bottom clearfix">
				          购票
				        </div>
				      </div>
				    </el-card>
						</router-link>
				  </el-col>
				</el-row>
				
				<h1 style="color: red;text-align: center; margin-bottom: 20px;margin-top: 50px;">即将上映</h1>
				<el-row>
				  <el-col :span="5" v-for="(item, index) in data2" :key="item.filmId" :offset="1">
						<router-link :to="'/detail?filmId='+item.filmId">
				    <el-card :body-style="{ padding: '0px'}" >
				      <img :src="item.poster" class="image">
				      <div >
				        <span class="filmname">{{item.name}}</span>
								<span class="filmgrade">{{item.grade}}</span>
				        <div class="bottom clearfix">
				          购票
				        </div>
				      </div>
				    </el-card>
						</router-link>
				  </el-col>
				</el-row>
			</el-main>
			<el-aside width="400px">
				
				<h1 style="text-align: center;margin-top: 90px; color: red; font-size: 24px;margin-bottom: 20px;">娱乐热点</h1>
				<div class="news">
				<a href="https://www.maoyan.com/films/news/7330286" target="_blank" class="ce">1.《穿过寒冬拥抱你》周冬雨刘昊然出演 群星笑对寒冬温暖跨年</a>
				</div>
				<div class="news">
				<a href="https://www.maoyan.com/films/news/7300934" target="_blank" class="ce">反派绿魔现身 《蜘蛛侠：英雄无归》全球首张海报曝光更多反派</a>
				</div>
			  <div class="news">
					<a href="https://www.maoyan.com/films/news/7283418" target="_blank" class="ce">年度合家欢动画《蜜熊的音乐奇旅》热映中 母女和解片段引发共鸣</a>
				</div>
				<div class="news">
					<a href="https://www.maoyan.com/films/news/7269838" target="_blank" class="ce">《特种部队：蛇眼起源》发布新预告 顶尖动作场面燃爆大银幕</a>
				</div>
				<div class="news">
					<a href="https://www.maoyan.com/films/news/7305579" target="_blank" class="ce">《最后的真相》发演员特辑 黄晓明闫妮“较劲”过招</a>
				</div>
			</el-aside>
		</el-container>
     
		
	
	</div>
</template>

<script>
	export default{
		name:"index",
		 data() {
		    return {
		      data:[],
					data2:[]
		    };
		  },
			mounted() {
				var that=this;
				this.$nextTick(function(){
					this.$axios.get('api/getNowPlayingFilmList').then(
					function(response){
						console.log(response.data.data.films);
						that.data=response.data.data.films
					}).catch(
					function (error) {
					    // 处理错误情况
					    console.log(error);
					  }
					)
					//即将上映
					this.$axios.get('api/getComingSoonFilmList').then(
					function(response){
						console.log(response.data.data.films);
						that.data2=response.data.data.films
					}).catch(
					function (error) {
					    // 处理错误情况
					    console.log(error);
					  }
					)
				})
			},
		methods:{
		}
	}
</script>

<style>
	*{
		padding: 0px;
		margin: 0px;
	}
	 .time {
	    font-size: 13px;
	    color: #999;
	  }
	  
		.ce{
			text-decoration: none;
			color: #333333;
		}
		.ce:hover{
			color: red;
		}
		.news{
			margin-bottom: 20px;
			margin-left: 30px;
		}
	  /* .bottom {
	    margin-top: 13px;
	    line-height: 12px;
	  } */
	
	  .button {
	    padding: 0;
	    float: right;
	  }
	
	  .image {
	    width: 100%;
			height:230px;
	    display: block;
	  }
	  .bottom {
			color:red;
			width: 100%;
			height: 40px;
			text-align: center;
			line-height: 40px;
		}
		.bottom:hover{
			background-color: red;
			color: white;
		}
		.el-col{
			margin-bottom: 10px; 
			position: relative;
		}
		.filmname{
			position: absolute;
			top:200px;
			color: white;
			margin-left: 10px;
		}
		.filmgrade{
			position: absolute;
			top:200px;
			color: rgb(255,180,0);
			right: 1px;
		}
	  .clearfix:before,
	  .clearfix:after {
	      display: table;
	      content: "";
	  }
	  
	  .clearfix:after {
	      clear: both
	  }
		
		  .el-header, .el-footer {
		    background-color: #B3C0D1;
		    color: #333;
		    text-align: center;
		    line-height: 60px;
		  }
		  
			
		  
		  .el-main {
		    background-color: white;
		    color: #333;
				padding: 0px;
		/*    text-align: center;
		    line-height: 160px; */
		  }
		  
		  body > .el-container {
		    margin-bottom: 40px;
		  }
		  
		  .el-container:nth-child(5) .el-aside,
		  .el-container:nth-child(6) .el-aside {
		    line-height: 260px;
		  }
		  
		  .el-container:nth-child(7) .el-aside {
		    line-height: 320px;
		  }
</style>
