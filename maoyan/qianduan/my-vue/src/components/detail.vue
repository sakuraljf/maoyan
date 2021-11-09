<template>
	<div>
		<film-head></film-head>
		<div class="detailmes" >
			<template>
			  <el-tabs v-model="activeName" @tab-click="handleClick">
			    <el-tab-pane label="介绍" name="first">{{film.synopsis}}</el-tab-pane>
			    <el-tab-pane label="演职人员" name="second">
					<!-- 冲冲冲 -->
						<ul class="actor">
							<li class="actorbox" v-for="(item,index) in film.actors" :key=item.name >
								<img :src="item.avatarAddress" class="miaoshuimg" />
								<div >{{item.name}}</div>
								<div >{{item.role}}</div>
							</li>
							
						</ul>
					</el-tab-pane>
			  </el-tabs>
			</template>
		</div>
		
		<div class="pingboxlist">
			<div class="pingbox" v-for="item in pinglist">
				<div>{{item.username}}</div>
				<div>{{item.time}}</div>
				<el-rate
				  v-model="item.grade"
				  disabled
				  show-score
				  text-color="#ff9900"
				  score-template="{value}">
				</el-rate>
				<div>{{item.content}}</div>
			</div>
		</div>
	</div>
</template>

<script>
	import filmHead from './filmHead.vue'
	export default{
		name:"detail",
		data(){
			return{
			film:[],
			pinglist:[],
			value:2,
			activeName: 'first',
			}
		},
		mounted() {
			this.$nextTick(function(){
				var that=this;
				var filmId=that.$route.query.filmId
					this.$axios.get('api/getFilmInfo?filmId='+filmId).then(
					function(response){
						that.film=response.data.data.film;
						that.value=parseInt(response.data.data.film.grade/2);
					}).catch(
					function (error) {
					    // 处理错误情况
					    console.log(error);
					  }
					)
					
					//获得评价
					let param=new URLSearchParams();
					param.append('filmId',filmId);
					this.$axios.post('apl/Lj/getping',param)
					  .then(function (response) {
					    console.log(response.data);
							that.pinglist=response.data;
							that.pinglist.map(item=>item.grade=parseInt(item.grade));
					  })
					  .catch(function (error) {
					    console.log(error);
					  });
			})
		},
		methods:{
			 handleClick(tab, event) {
			 console.log(tab, event);
			 }
		},
		components:{
			filmHead
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
	.detailping{
		position: absolute;
		top:1200px
	}
	.pingboxlist{
		position: absolute;
		top:1100px;
	  width: 100%;
		border-top: 1px dashed silver;
	}
	.pingbox{
		width: 60%;
		height: 180px;
		border-bottom:1px dashed silver ;
	}
</style>
